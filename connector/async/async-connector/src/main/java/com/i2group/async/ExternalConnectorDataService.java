/********************************************************************************
# * Licensed Materials - Property of IBM
# * (C) Copyright IBM Corporation 2021. All Rights Reserved
# *
# * This program and the accompanying materials are made available under the
# * terms of the Eclipse Public License 2.0 which is available at
# * http://www.eclipse.org/legal/epl-2.0.
# *
# * US Government Users Restricted Rights - Use, duplication or
# * disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
# *
# ********************************************************************************/

package com.i2group.async;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.i2group.async.rest.transport.AsyncQueryResponse;
import com.i2group.async.rest.transport.AsyncQuerySubstatus;
import com.i2group.async.rest.transport.AsyncStatusResponse;
import com.i2group.async.rest.transport.ConnectorResponse;
import com.i2group.async.rest.transport.EntityData;
import com.i2group.async.rest.transport.ItemFactory;
import com.i2group.async.rest.transport.LinkData;
import com.i2group.async.rest.transport.ResponseData;
import com.i2group.async.rest.transport.AsyncQuerySubstatus.Substatus;
import com.i2group.async.rest.transport.AsyncStatusResponse.State;
import com.i2group.async.rest.transport.ResponseData.Person;
import com.i2group.async.rest.transport.request.RequestCondition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

/** Used to query the dataset, turning the raw data into entities and links. */
@Service
public class ExternalConnectorDataService {

  private final AsyncStatusResponse status = new AsyncStatusResponse();
  private final Map<String, ConnectorResponse> asyncResponses = new HashMap<>();
  private final Map<String, AsyncStatusResponse> asyncStatuses = new HashMap<>();
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
  public AsyncQueryResponse asyncAcquire(List<RequestCondition> conditions) {

    startDate = new Date();

    final AsyncQueryResponse queryResponse = new AsyncQueryResponse();
    queryResponse.queryId = UUID.randomUUID().toString();

    int duration = 10;
    boolean shouldFail = false;

    for (RequestCondition condition : conditions) {
      if (condition.id.equals("duration")) {
        duration = Integer.parseInt(condition.value);
      } else if (condition.id.equals("shouldFail")) {
        shouldFail = Boolean.parseBoolean(condition.value);
      }
    }

    final int customDuration = duration;

    if (shouldFail) {
      asyncQuery.cancel(true);
      updateStatus(State.FAILED, queryResponse.queryId);
    } else {
      asyncQuery = CompletableFuture.runAsync(() -> {
        try {
          updateStatus(State.STARTED, queryResponse.queryId);
          TimeUnit.SECONDS.sleep(customDuration);
          if (!asyncStatuses.get(queryResponse.queryId).state.equals(State.FAILED)) {
            final ResponseData responseData = retrieveData();

            final ConnectorResponse connectorResponse = marshalItemsFromResponse(responseData);
            asyncResponses.put(queryResponse.queryId, connectorResponse);

            updateStatus(State.SUCCEEDED, queryResponse.queryId);
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
  public AsyncStatusResponse asyncStatus(String queryId) {
    final AsyncStatusResponse statusResponse = new AsyncStatusResponse();
    final AsyncQuerySubstatus substatus = new AsyncQuerySubstatus();
    statusResponse.state = asyncStatuses.get(queryId).state;

    switch (statusResponse.state) {
      case STARTED:
        substatus.type = Substatus.INFORMATION;
        substatus.message = "Query started - " + startDate;
        statusResponse.substatuses = Collections.singletonList(substatus);
        queryStarted = substatus;
        break;
      case SUCCEEDED:
        substatus.type = Substatus.SUCCESS;
        substatus.message = "Query completed - " + new Date();
        statusResponse.substatuses = Arrays.asList(queryStarted, substatus);
        break;
      case FAILED:
        substatus.type = Substatus.ERROR;
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
  public ConnectorResponse asyncResults(String queryId) {
    return asyncResponses.get(queryId);
  }

  /**
   * Cancels and removes the running query.
   * 
   * @param queryId The id of the query.
   * @return The response with the removed query.
   */
  public ConnectorResponse asyncDelete(String queryId) {
    asyncQuery.cancel(true);
    updateStatus(State.FAILED, queryId);
    return asyncResponses.remove(queryId);
  }

  private void updateStatus(State state, String queryId) {
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
  private ConnectorResponse marshalItemsFromResponse(ResponseData responseData) {
    final List<EntityData> entities = new ArrayList<>();
    final List<LinkData> links = new ArrayList<>();
    final Set<String> linkIds = new HashSet<>();

    final Map<String, EntityData> people = new HashMap<>();

    responseData.person.forEach(entry -> {
      EntityData person;
      if (people.containsKey(entry.id)) {
        person = people.get(entry.id);
      } else {
        person = itemFactory.createPerson(entry);
        people.put(entry.id, person);
        entities.add(person);
      }

      entry.friends.forEach(friendId -> {
        final Person friendPerson = getPersonFromId(responseData, friendId);
        EntityData friend;

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
            LinkData personLink = itemFactory.createPersonLink(person, friend);
            links.add(personLink);
            linkIds.add(uniqueId);
            linkIds.add(alternateUniqueId);
          }
        }
      });
    });

    final ConnectorResponse connectorResponse = new ConnectorResponse();
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
