/*
 * MIT License
 *
 * © N.Harris Computer Corporation (2023)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.i2group.async;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.i2group.async.rest.transport.ItemFactory;

import com.i2group.async.rest.transport.ResponseData;
import com.i2group.connector.spi.rest.transport.*;
import com.i2group.connector.spi.rest.transport.AsyncQueryStatus.StateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import static com.i2group.async.rest.transport.ResponseData.*;
import static com.i2group.connector.spi.rest.transport.AsyncQuerySubstatus.*;

/** Used to query the dataset, turning the raw data into entities and links. */
@Service
public class ExternalConnectorDataService {

  private final AsyncQueryStatus status = new AsyncQueryStatus();
  private final Map<String, I2ConnectData> asyncResponses = new HashMap<>();
  private final Map<String, AsyncQueryStatus> asyncStatuses = new HashMap<>();
  private final ItemFactory itemFactory;
  private final Resource peopleResource;

  private Date startDate;
  private AsyncQuerySubstatus queryStarted;
  private CompletableFuture<Void> asyncQuery = new CompletableFuture<>();

  @Autowired
  public ExternalConnectorDataService(
      @Value("classpath:people.json") Resource resource) {
    this.itemFactory = new ItemFactory(resource);
    this.peopleResource = resource;
  }

  /**
   * Retrieves the data matching certain specified conditions.
   *
   * @param conditions The conditions provided by the user via the interface.
   * @return A response containing the entities and links.
   */
  public AsyncQueryResponse asyncAcquire(List<DaodRequestCondition> conditions) {

    startDate = new Date();

    final AsyncQueryResponse queryResponse = new AsyncQueryResponse();
    queryResponse.queryId = UUID.randomUUID().toString();

    int duration = 10;
    boolean shouldFail = false;

    for (DaodRequestCondition condition : conditions) {
      if (condition.id.equals("duration")) {
        duration = Integer.parseInt(condition.value.toString());
      } else if (condition.id.equals("shouldFail")) {
        shouldFail = Boolean.parseBoolean(condition.value.toString());
      }
    }

    final int customDuration = duration;

    if (shouldFail) {
      asyncQuery.cancel(true);
      updateStatus(StateEnum.FAILED, queryResponse.queryId);
    } else {
      asyncQuery = CompletableFuture.runAsync(() -> {
        try {
          updateStatus(StateEnum.STARTED, queryResponse.queryId);
          TimeUnit.SECONDS.sleep(customDuration);
          if (!asyncStatuses.get(queryResponse.queryId).state.equals(StateEnum.FAILED)) {
            final ResponseData responseData = retrieveData();

            final I2ConnectData connectorResponse = marshalItemsFromResponse(responseData);
            asyncResponses.put(queryResponse.queryId, connectorResponse);

            updateStatus(StateEnum.SUCCEEDED, queryResponse.queryId);
          }
        } catch (InterruptedException e) {
          throw new IllegalStateException(e);
        }
      });
    }

    return queryResponse;
  }

  /**
   * Retrieves the status of the query.
   * 
   * @param queryId The id of the query.
   * @return The status response.
   */
  public AsyncQueryStatus asyncStatus(String queryId) {
    final AsyncQueryStatus statusResponse = new AsyncQueryStatus();
    final AsyncQuerySubstatus substatus = new AsyncQuerySubstatus();
    statusResponse.state = asyncStatuses.get(queryId).state;

    switch (statusResponse.state) {
      case STARTED:
        substatus.type = TypeEnum.INFORMATION;
        substatus.message = "Query started - " + startDate;
        statusResponse.substatuses = Collections.singletonList(substatus);
        queryStarted = substatus;
        break;
      case SUCCEEDED:
        substatus.type = TypeEnum.SUCCESS;
        substatus.message = "Query completed - " + new Date();
        statusResponse.substatuses = Arrays.asList(queryStarted, substatus);
        break;
      case FAILED:
        substatus.type = TypeEnum.ERROR;
        substatus.message = "Query failed - " + startDate;
        statusResponse.substatuses = Collections.singletonList(substatus);
        break;
      default:
        throw new Error("Query in unkown state");
    }
    return statusResponse;
  }

  /**
   * Retrieves the results of the query.
   * 
   * @param queryId The id of the query.
   * @return The results of the query.
   */
  public I2ConnectData asyncResults(String queryId) {
    return asyncResponses.get(queryId);
  }

  /**
   * Cancels and removes the running query.
   * 
   * @param queryId The id of the query.
   * @return The response with the removed query.
   */
  public I2ConnectData asyncDelete(String queryId) {
    asyncQuery.cancel(true);
    updateStatus(StateEnum.FAILED, queryId);
    return asyncResponses.remove(queryId);
  }

  private void updateStatus(StateEnum state, String queryId) {
    status.state = state;
    asyncStatuses.put(queryId, status);
  }

  private ResponseData retrieveData() {
    final ObjectMapper object = new ObjectMapper();
    ResponseData responseData = null;

    try {
      responseData = object.readValue(peopleResource.getFile(), ResponseData.class);
    } catch (IOException ex) {
      System.out.println("Exception: " + ex);
    }
    return responseData;
  }

  /**
   * Marshal the response items into a list of entities and links. Ensures no
   * duplicate complaints or locations are included.
   *
   * @param responseData The resulting source records returned from the request.
   * @return The response containing entities and links.
   */
  private I2ConnectData marshalItemsFromResponse(ResponseData responseData) {
    final List<I2ConnectEntityData> entities = new ArrayList<>();
    final List<I2ConnectLinkData> links = new ArrayList<>();
    final Set<String> linkIds = new HashSet<>();

    final Map<String, I2ConnectEntityData> people = new HashMap<>();

    responseData.person.forEach(entry -> {
      I2ConnectEntityData person;
      if (people.containsKey(entry.id)) {
        person = people.get(entry.id);
      } else {
        person = itemFactory.createPerson(entry);
        people.put(entry.id, person);
        entities.add(person);
      }

      entry.friends.forEach(friendId -> {
        final Person friendPerson = getPersonFromId(responseData, friendId);
        I2ConnectEntityData friend;

        if (friendPerson != null) {
          if (people.containsKey(friendId)) {
            friend = people.get(friendId);
          } else {
            friend = itemFactory.createPerson(friendPerson);
            people.put(friendId, friend);
            entities.add(friend);
          }

          final String uniqueId = "LINK-PER" + person.id + "-FRIEND" + friend.id;
          final String alternateUniqueId = "LINK-PER" + friend.id + "-FRIEND" + person.id;
          if (!linkIds.contains(uniqueId) || !linkIds.contains(alternateUniqueId)) {
            I2ConnectLinkData personLink = itemFactory.createPersonLink(person, friend);
            links.add(personLink);
            linkIds.add(uniqueId);
            linkIds.add(alternateUniqueId);
          }
        }
      });
    });

    final I2ConnectData connectorResponse = new I2ConnectData();
    connectorResponse.entities = entities;
    connectorResponse.links = links;
    return connectorResponse;
  }

  private Person getPersonFromId(ResponseData data, String id) {
    for (Person person : data.person) {
      if (person.id.equals(id)) {
        return person;
      }
    }
    return null;
  }
}
