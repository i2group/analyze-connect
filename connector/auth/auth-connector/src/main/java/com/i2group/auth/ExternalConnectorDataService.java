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

package com.i2group.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.i2group.auth.rest.transport.*;
import com.i2group.auth.rest.transport.async.AsyncQueryResponse;
import com.i2group.auth.rest.transport.async.AsyncQuerySubstatus;
import com.i2group.auth.rest.transport.async.AsyncStatusResponse;
import com.i2group.auth.rest.transport.auth.*;
import com.i2group.auth.rest.transport.request.RequestCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Used to query the dataset, turning the raw data into entities and links.
 */
@Service
public class ExternalConnectorDataService {

  private final ItemFactory itemFactory;
  private final Resource peopleResource;
  private final AsyncStatusResponse status = new AsyncStatusResponse();
  private final Map<String, ConnectorResponse> asyncResponses = new HashMap<>();
  private final Map<String, AsyncStatusResponse> asyncStatuses = new HashMap<>();

  private static final String AUTHENTICATION_REQUIRED_TYPE = "urn:uuid:264caa46-75cb" +
      "-4ac5-891a-11adeb48b6fb";
  private static final String CREDENTIAL = "foo";

  private Date startDate;
  private AsyncQuerySubstatus queryStarted;
  private CompletableFuture<Void> asyncQuery = new CompletableFuture<>();

  @Autowired
  public ExternalConnectorDataService(
      @Value("classpath:people.json") Resource resource) {
    this.itemFactory = new ItemFactory();
    this.peopleResource = resource;
  }

  /**
   * Verifies the submitted username and password credentials and responds.
   *
   * @param request The {@link AuthRequest} containing the submitted username and
   * password.
   * @return The response from {@link #verifyCredentials(boolean)}.
   */
  public ResponseEntity<?> loginUserPass(AuthRequest request) {
    final boolean predicate =
        request.username.equals(CREDENTIAL) && request.password.equals(CREDENTIAL);
    return verifyCredentials(predicate);
  }

  /**
   * Verifies the submitted API key and responds.
   *
   * @param request The {@link AuthRequest} containing the submitted API key.
   * @return The response from {@link #verifyCredentials(boolean)}.
   */
  public ResponseEntity<?> loginApiKey(AuthRequest request) {
    final boolean predicate = request.apikey.equals(CREDENTIAL);
    return verifyCredentials(predicate);
  }

  /**
   * Retrieves all data from the data source.
   *
   * @param auth The value of the Authorization header.
   * @return A response containing the entities and links.
   */
  public ResponseEntity<?> acquire(String auth) {
    final AuthValidator validationResponse = AuthValidator.validateAuth(auth);
    if (!validationResponse.valid) {
      return errorResponse(validationResponse.title, validationResponse.details);
    }
    final List<Person> people = retrievePeopleData();
    final ConnectorResponse acquireResponse = marshalItemsFromResponse(people);
    return ResponseEntity.status(200).body(acquireResponse);
  }

  /**
   * Retrieves the data. Conditions configure the mocked asynchronous behaviour.
   *
   * @param auth The value of the Authorization header.
   * @param conditions The asynchronous configuration provided via the interface.
   * @return A response containing the entities and links.
   */
  public ResponseEntity<?> asyncAcquire(String auth, List<RequestCondition> conditions) {
    final AuthValidator validationResponse = AuthValidator.validateAuth(auth);
    if (!validationResponse.valid) {
      return errorResponse(validationResponse.title, validationResponse.details);
    }

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
      updateStatus(AsyncStatusResponse.State.FAILED, queryResponse.queryId);
    } else {
      asyncQuery = CompletableFuture.runAsync(() -> {
        try {
          updateStatus(AsyncStatusResponse.State.STARTED, queryResponse.queryId);
          TimeUnit.SECONDS.sleep(customDuration);

          if (!asyncStatuses.get(queryResponse.queryId).state.equals(AsyncStatusResponse.State.FAILED)) {
            final List<Person> people = retrievePeopleData();
            final ConnectorResponse connectorResponse = marshalItemsFromResponse(people);
            asyncResponses.put(queryResponse.queryId, connectorResponse);
            updateStatus(AsyncStatusResponse.State.SUCCEEDED, queryResponse.queryId);
          }
        } catch (InterruptedException e) {
          throw new IllegalStateException(e);
        }
      });
    }

    return ResponseEntity.status(200).body(queryResponse);
  }

  /**
   * Retrieves the status of the query.
   *
   * @param auth The value of the Authorization header.
   * @param queryId The identifier of the query.
   * @return The status response.
   */
  public ResponseEntity<?> asyncStatus(String auth, String queryId) {
    final AuthValidator validationResponse = AuthValidator.validateAuth(auth);
    if (!validationResponse.valid) {
      return errorResponse(validationResponse.title, validationResponse.details);
    }

    final AsyncStatusResponse statusResponse = new AsyncStatusResponse();
    final AsyncQuerySubstatus substatus = new AsyncQuerySubstatus();
    statusResponse.state = asyncStatuses.get(queryId).state;

    switch (statusResponse.state) {
      case STARTED:
        substatus.type = AsyncQuerySubstatus.Substatus.INFORMATION;
        substatus.message = "Query started - " + startDate;
        statusResponse.substatuses = Collections.singletonList(substatus);
        queryStarted = substatus;
        break;
      case SUCCEEDED:
        substatus.type = AsyncQuerySubstatus.Substatus.SUCCESS;
        substatus.message = "Query completed - " + new Date();
        statusResponse.substatuses = Arrays.asList(queryStarted, substatus);
        break;
      case FAILED:
        substatus.type = AsyncQuerySubstatus.Substatus.ERROR;
        substatus.message = "Query failed - " + startDate;
        statusResponse.substatuses = Collections.singletonList(substatus);
        break;
      default:
        throw new Error("Query in unknown state");
    }
    return ResponseEntity.status(200).body(statusResponse);
  }

  /**
   * Retrieves the results of the query.
   *
   * @param auth The value of the Authorization header.
   * @param queryId The identifier of the query.
   * @return The results of the query.
   */
  public ResponseEntity<?> asyncResults(String auth, String queryId) {
    final AuthValidator validationResponse = AuthValidator.validateAuth(auth);
    if (!validationResponse.valid) {
      return errorResponse(validationResponse.title, validationResponse.details);
    }
    return ResponseEntity.status(200).body(asyncResponses.get(queryId));
  }

  /**
   * Cancels and removes the running query.
   *
   * @param queryId The query identifier.
   * @return The response with the removed query.
   */
  public ResponseEntity<Void> asyncDelete(String queryId) {
    asyncQuery.cancel(true);
    updateStatus(AsyncStatusResponse.State.FAILED, queryId);
    asyncResponses.remove(queryId);
    return ResponseEntity.noContent().build();
  }

  /**
   * Verifies that submitted credentials are correct.
   *
   * @param predicate The condition for a successful response.
   * @return A response containing the generated token if credentials are valid or an *
   * error message if invalid.
   */
  private ResponseEntity<?> verifyCredentials(boolean predicate) {
    if (predicate) {
      final AuthResponse response = new AuthResponse();
      response.token = TokenManager.generateToken();
      return ResponseEntity.status(200).body(response);
    } else {
      return errorResponse("Invalid credentials.", null);
    }
  }

  /**
   * Creates a {@link ProblemDetails} error response.
   *
   * @param title The title of the error.
   * @param details The details of the error.
   * @return The {@link ProblemDetails} response body.
   */
  private ResponseEntity<ProblemDetails> errorResponse(String title, String details) {
    final HttpStatus status = HttpStatus.UNAUTHORIZED;
    final ProblemDetails problemDetails = new ProblemDetails();
    problemDetails.title = title;
    problemDetails.type = AUTHENTICATION_REQUIRED_TYPE;
    problemDetails.status = status.value();
    problemDetails.detail = details;
    return ResponseEntity.status(status).contentType(MediaType.APPLICATION_PROBLEM_JSON).body(problemDetails);
  }

  /**
   * Marshal the response items into a list of entities and links. Ensures no duplicate
   * {@link Person} entities are included.
   *
   * @param people The resulting source records returned from the request.
   * @return The response containing entities and links.
   */
  private ConnectorResponse marshalItemsFromResponse(List<Person> people) {
    final List<Entity> entities = new ArrayList<>();
    final List<Link> links = new ArrayList<>();
    final Set<String> linkIds = new HashSet<>();

    final Map<String, Entity> seenPeople = new HashMap<>();

    people.forEach(entry -> {

      final Entity person;
      if (seenPeople.containsKey(entry.id)) {
        person = seenPeople.get(entry.id);
      } else {
        person = itemFactory.createPerson(entry);
        seenPeople.put(entry.id, person);
        entities.add(person);
      }

      entry.friends.forEach(friendId -> {
        final Person friendPerson = getPersonFromId(people, friendId);
        final Entity friend;

        if (friendPerson != null) {
          if (seenPeople.containsKey(friendId)) {
            friend = seenPeople.get(friendId);
          } else {
            friend = itemFactory.createPerson(friendPerson);
            seenPeople.put(friendId, friend);
            entities.add(friend);
          }

          final String uniqueId = "LINK-PER" + person.id + "-FRIEND" + friend.id;
          final String alternateUniqueId = "LINK-PER" + friend.id + "-FRIEND" + person.id;

          if (!linkIds.contains(uniqueId) || !linkIds.contains(alternateUniqueId)) {
            final Link personLink = itemFactory.createPersonLink(person, friend);
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

  /**
   * Retrieves the list of people from the dataset.
   *
   * @return The people list.
   */
  private List<Person> retrievePeopleData() {
    final ObjectMapper object = new ObjectMapper();
    List<Person> people = new ArrayList<>();

    try {
      people = object.readValue(peopleResource.getFile(), ResponseItem.class).people;
    } catch (IOException ex) {
      System.out.println("Exception: " + ex);
    }
    return people;
  }

  /**
   * Retrieves a {@link Person} from a specified ID.
   *
   * @param people The total list of people in the dataset.
   * @param id The ID of the person to retrieve.
   * @return The {@link Person} matching the specified ID.
   */
  private Person getPersonFromId(List<Person> people, String id) {
    for (Person person : people) {
      if (person.id.equals(id)) {
        return person;
      }
    }
    return null;
  }

  /**
   * Updates the status of a query.
   *
   * @param state The state to update the query with.
   * @param queryId The ID of the query to update.
   */
  private void updateStatus(AsyncStatusResponse.State state, String queryId) {
    status.state = state;
    asyncStatuses.put(queryId, status);
  }
}
