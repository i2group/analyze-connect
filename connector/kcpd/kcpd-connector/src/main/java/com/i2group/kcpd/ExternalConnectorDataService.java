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

package com.i2group.kcpd;

import com.i2group.kcpd.rest.externalsource.SocrataClient;
import com.i2group.kcpd.rest.externalsource.transport.SocrataResponse;
import com.i2group.kcpd.rest.externalsource.transport.SocrataResponseData;
import com.i2group.kcpd.rest.transport.ConnectorResponse;
import com.i2group.kcpd.rest.transport.EntityData;
import com.i2group.kcpd.rest.transport.ItemFactory;
import com.i2group.kcpd.rest.transport.LinkData;
import com.i2group.kcpd.rest.transport.request.RequestCondition;
import com.i2group.kcpd.rest.transport.request.seeded.DaodSeedEntityData;
import com.i2group.kcpd.rest.transport.request.seeded.DaodSeeds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/** Used to query the KCPD Crime Data, turning the raw data into entities and links. */
@Service
public class ExternalConnectorDataService {
  private static final String LIMIT_FIELD = "limitValue";
  private static final String BASE_URL = "?$limit={" + LIMIT_FIELD + "}";
  private final SocrataClient socrataClient;
  private final int limitFieldValue = 50;

  /**
   * Constructor used to initialise the Socrata client and factory objects used to retrieve
   * report data.
   *
   * @param baseUrl The URL of the KCPD crime data.
   * @param apiToken The API token used to access the KCPD crime data.
   */
  @Autowired
  public ExternalConnectorDataService(
      @Value("${socrata.url}") String baseUrl, @Value("${socrata.api.token}") String apiToken) {
    socrataClient = new SocrataClient(baseUrl, apiToken);
  }

  /**
   * Query the external dataset and retrieve all data.
   *
   * @return A response containing the entities and links.
   */
  public ConnectorResponse retrieveAll() {
    final Map<String, Object> params = new HashMap<>();
    params.put(LIMIT_FIELD, limitFieldValue);

    final SocrataResponse response = socrataClient.get(BASE_URL, SocrataResponse.class, params);
    return marshalItemsFromResponse(response);
  }

  /**
   * Retrieve data matching certain specified conditions.
   *
   * @param conditions The conditions provided by the user via the interface.
   * @return A response containing the entities and links.
   */
  public ConnectorResponse search(List<RequestCondition> conditions) {
    final Map<String, Object> params = new HashMap<>();
    params.put(LIMIT_FIELD, limitFieldValue);
    String url = BASE_URL;

    int count = 0;
    for (RequestCondition condition : conditions) {
      url += count == 0 ? "&$where=" : " AND ";
      params.put(condition.id, condition.value);
      if (condition.id.equals("offense")) {
        url += condition.id + " like '%{" + condition.id + "}%'";
      } else {
        url += condition.id + "='{" + condition.id + "}'";
      }
      count++;
    }

    final SocrataResponse response = socrataClient.get(url, SocrataResponse.class, params);
    final ConnectorResponse connectorResponse = new ConnectorResponse();

    connectorResponse.entities = buildEntities(response);

    return connectorResponse;
  }

  /**
   * Performs a Find-Like-This-Location operation, finding entities with similar properties to a selected
   * entity.
   *
   * @param seeds The selected entities provided by the user via the interface.
   * @return A response containing the entities and links.
   */
  public ConnectorResponse findLikeThisLocation(DaodSeeds seeds) {
    final DaodSeedEntityData seed = seeds.entities.get(0);
    final Map<String, Object> params = new HashMap<>();

    params.put(LIMIT_FIELD, limitFieldValue);
    params.put("address", seed.properties.get("PT10"));
    final String url = BASE_URL + "&$where=address='{address}'";

    final SocrataResponse response = socrataClient.get(url, SocrataResponse.class, params);

    final ConnectorResponse connectorResponse = new ConnectorResponse();
    connectorResponse.entities = buildEntities(response);
    return connectorResponse;
  }

  
  /**
   * Performs an Expand operation on selected entities, finding entities which stem from the same
   * source records.
   *
   * @param seeds The selected entities provided by the user via the interface.
   * @return A response containing the entities and links.
   */
  public ConnectorResponse expand(DaodSeeds seeds) {
    final DaodSeedEntityData seed = seeds.entities.get(0);
    String field = "", value = "";

    if (seed.typeId.equals("ET1")) {
      field = "report_no";
      value = seed.properties.get("PT1").toString();
    } else if (seed.typeId.equals("ET2")) {
      field = "address";
      value = seed.properties.get("PT10").toString();
    }

    final Map<String, Object> params = new HashMap<>();
    params.put(LIMIT_FIELD, limitFieldValue);
    params.put("field", field);
    params.put("value", value);
    final String url = BASE_URL + "&{field}={value}";

    final SocrataResponse socrataResponse = socrataClient.get(url, SocrataResponse.class, params);
    ConnectorResponse response = marshalItemsFromResponse(socrataResponse);
    response.links = linkToSeedIds(response, seed);

    return response;
  }

  /**
   * Marshal the response items into a list of entities and links. Ensures no duplicate reports
   * or locations are included.
   *
   * @param response The resulting source records returned from the request.
   * @return The response containing entities and links.
   */
  private ConnectorResponse marshalItemsFromResponse(SocrataResponse response) {
    final List<EntityData> entities = new ArrayList<>();
    final List<LinkData> links = new ArrayList<>();
    final Set<String> personLinks = new HashSet<>();

    final Map<String, EntityData> reports = new HashMap<>();
    final Map<String, EntityData> locations = new HashMap<>();
    final Map<String, EntityData> people = new HashMap<>();
    final Map<String, LinkData> locationLinks = new HashMap<>();

    response
        .forEach(
            entry -> {
              EntityData report;
              String uniqueReport = entry.reportNumber;
              if (reports.containsKey(uniqueReport)) {
                report = reports.get(uniqueReport);
              } else {
                report = ItemFactory.createReport(entry);
                reports.put(uniqueReport, report);
                entities.add(report);
              }

              EntityData location;
              String uniqueLocation = entry.address + entry.zipCode;
              if (locations.containsKey(uniqueLocation)) {
                location = locations.get(uniqueLocation);
              } else {
                location = ItemFactory.createLocation(entry);
                locations.put(uniqueLocation, location);
                entities.add(location);
              }

              EntityData person;
              String uniquePerson = ItemFactory.buildId(entry);
              if (people.containsKey(uniquePerson)) {
                person = people.get(uniquePerson);
              } else {
                person = ItemFactory.createPerson(entry);
                people.put(uniquePerson, person);
                entities.add(person);
              }

              LinkData locationLink;
              String uniqueLocationLink = entry.reportNumber;
              if (locationLinks.containsKey(uniqueLocationLink)) {
                locationLink = locationLinks.get(uniqueLocationLink);
              } else {
                locationLink = ItemFactory.createLocationLink(entry, report, location);
                locationLinks.put(uniqueLocationLink, locationLink);
                links.add(locationLink);
              }

              String uniquePersonLink = ItemFactory.buildId(entry);
              if (!personLinks.contains(uniquePersonLink)) {
                addLinks(links, entry, report, person);
                personLinks.add(uniquePersonLink);
              } 
            });

    final ConnectorResponse connectorResponse = new ConnectorResponse();
    connectorResponse.entities = entities;
    connectorResponse.links = links;
    return connectorResponse;
  }

  private void addLinks(final List<LinkData> links, SocrataResponseData entry, EntityData report, EntityData person) {
    List<String> involvementList = Arrays.asList(entry.involvement.split("\\s+"));

    involvementList.forEach(involvement -> {
      switch(involvement) {
        case "SUS":
          links.add(ItemFactory.createSuspectLink(entry, report, person));
          break;
        case "VIC":
          links.add(ItemFactory.createVictimLink(entry, report, person));
          break;
        case "ARR":
          links.add(ItemFactory.createArrestedLink(entry, report, person));
          break;
        case "CHA":
          links.add(ItemFactory.createChargedLink(entry, report, person));
          break;
        case "CMP":
          links.add(ItemFactory.createComplicitLink(entry, report, person));
          break;
      }
    });
  }

    /**
   * Match link ends to seed identifiers.
   *
   * @param response The response containing the marshaled entities and links.
   * @param seed The selected entity provided by the user via the interface.
   * @return The list of links matched to the corresponding seed identifiers.
   */
  private List<LinkData> linkToSeedIds(ConnectorResponse response, DaodSeedEntityData seed) {
    for (LinkData link : response.links) {
      String sourceId = seed.sourceIds.get(0).key.get(2);
      if (link.fromEndId.equals(sourceId)) {
        link.fromEndId = seed.seedId;
      } else if (link.toEndId.equals(sourceId)) {
        link.toEndId = seed.seedId;
      }
    }
    return response.links;
  }

  private List<EntityData> buildEntities(final SocrataResponse response) {
    List<String> uniqueId = new ArrayList<>();
    List<EntityData> entities = new ArrayList<>();

    response.forEach(entry -> {
      if (!uniqueId.contains(entry.reportNumber)) {
        EntityData entity = ItemFactory.createReport(entry);
        entities.add(entity);
        uniqueId.add(entry.reportNumber);
      }
    });
    return entities;
  }
}
