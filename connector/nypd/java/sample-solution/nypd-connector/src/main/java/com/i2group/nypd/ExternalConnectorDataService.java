/********************************************************************************
# * Licensed Materials - Property of IBM
# * (C) Copyright IBM Corporation 2020. All Rights Reserved
# *
# * This program and the accompanying materials are made available under the
# * terms of the Eclipse Public License 2.0 which is available at
# * http://www.eclipse.org/legal/epl-2.0.
# *
# * US Government Users Restricted Rights - Use, duplication or
# * disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
# *
# ********************************************************************************/

package com.i2group.nypd;

import com.i2group.nypd.rest.externalsource.SocrataClient;
import com.i2group.nypd.rest.externalsource.transport.SocrataResponse;
import com.i2group.nypd.rest.transport.ConnectorResponse;
import com.i2group.nypd.rest.transport.EntityData;
import com.i2group.nypd.rest.transport.ItemFactory;
import com.i2group.nypd.rest.transport.LinkData;
import com.i2group.nypd.rest.transport.request.Payload;
import com.i2group.nypd.rest.transport.request.RequestCondition;
import com.i2group.nypd.rest.transport.request.seeded.DaodSeedEntityData;
import com.i2group.nypd.rest.transport.request.seeded.DaodSeeds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/** Used to query the NYPD complaint dataset, turning the raw data into entities and links. */
@Service
public class ExternalConnectorDataService {
  private final SocrataClient socrataClient;

  /**
   * Constructor used to initialise the Socrata client and factory objects used to retrieve
   * complaint data.
   *
   * @param baseUrl The URL of the NYPD complaint dataset.
   * @param apiToken The API token used to access the NYPD complaint dataset.
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
    params.put("limitValue", 50);
    String url = "?$limit={limitValue}";

    final SocrataResponse response = socrataClient.get(url, SocrataResponse.class, params);
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
    params.put("limitValue", 50);
    String url = "?$limit={limitValue}";

    int count = 0;
    for (RequestCondition condition : conditions) {
      url += count == 0 ? "&$where=" : "&";
      params.put(condition.id, condition.value);
      url += condition.id + "='{" + condition.id + "}'";
      count++;
    }

    final SocrataResponse response = socrataClient.get(url, SocrataResponse.class, params);

    final ConnectorResponse connectorResponse = new ConnectorResponse();
    connectorResponse.entities =
        response.stream().map(ItemFactory::createComplaint).collect(Collectors.toList());
    connectorResponse.links = Collections.emptyList();
    return connectorResponse;
  }

  /**
   * Performs a Find-Like-This operation, finding entities with similar properties to a selected
   * entity.
   *
   * @param seeds The selected entities provided by the user via the interface.
   * @return A response containing the entities and links.
   */
  public ConnectorResponse findLikeThisComplaint(DaodSeeds seeds) {
    DaodSeedEntityData seed = seeds.entities.get(0);

    final Map<String, Object> params = new HashMap<>();
    params.put("limitValue", 50);
    params.put("lawCategory", seed.properties.get("PT10"));
    String url = "?$limit={limitValue}&$where=law_cat_cd='{lawCategory}'";

    final SocrataResponse response = socrataClient.get(url, SocrataResponse.class, params);

    final ConnectorResponse connectorResponse = new ConnectorResponse();
    connectorResponse.entities =
        response.stream().map(ItemFactory::createComplaint).collect(Collectors.toList());
    connectorResponse.links = Collections.emptyList();
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
    DaodSeedEntityData seed = seeds.entities.get(0);
    String field = "", value = "";

    if (seed.typeId.equals("ET1")) {
      field = "cmplnt_num";
      value = seed.properties.get("PT1").toString();
    } else if (seed.typeId.equals("ET2")) {
      field = "addr_pct_cd";
      value = seed.properties.get("PT15").toString();
    }

    final Map<String, Object> params = new HashMap<>();
    params.put("limitValue", 50);
    params.put("field", field);
    params.put("value", value);
    String url = "?$limit={limitValue}&{field}={value}";

    final SocrataResponse socrataResponse = socrataClient.get(url, SocrataResponse.class, params);
    ConnectorResponse response = marshalItemsFromResponse(socrataResponse);
    response.links = linkToSeedIds(response, seed);

    return response;
  }

  /**
   * Performs an Expand operation and filters by specified conditions.
   *
   * @param payload The payload containing conditions and seeds provided by the user via the interface.
   * @return A response containing the entities and links.
   */
  public ConnectorResponse expandWithConditions(Payload payload) {
    DaodSeedEntityData seed = payload.seeds.entities.get(0);
    String field = "", value = "";

    if (seed.typeId.equals("ET1")) {
      field = "cmplnt_num";
      value = seed.properties.get("PT1").toString();
    } else if (seed.typeId.equals("ET2")) {
      field = "addr_pct_cd";
      value = seed.properties.get("PT15").toString();
    }

    final Map<String, Object> params = new HashMap<>();
    params.put("field", field);
    params.put("limitValue", 50);
    params.put("value", value);
    String url = "?$limit={limitValue}&{field}={value}";

    int count = 0;
    for (RequestCondition condition : payload.conditions) {
      url += count == 0 ? "&$where=" : "&";
      params.put(condition.id, condition.value);
      url += condition.id + "='{" + condition.id + "}'";
      count++;
    }

    final SocrataResponse socrataResponse = socrataClient.get(url, SocrataResponse.class, params);
    ConnectorResponse response = marshalItemsFromResponse(socrataResponse);
    response.links = linkToSeedIds(response, seed);

    return response;
  }

  /**
   * Marshal the response items into a list of entities and links. Ensures no duplicate complaints
   * or locations are included.
   *
   * @param response The resulting source records returned from the request.
   * @return The response containing entities and links.
   */
  private ConnectorResponse marshalItemsFromResponse(SocrataResponse response) {
    final List<EntityData> entities = new ArrayList<>();
    final List<LinkData> links = new ArrayList<>();

    final Map<String, EntityData> complaints = new HashMap<>();
    final Map<String, EntityData> locations = new HashMap<>();

    response.stream()
        .forEach(
            entry -> {
              EntityData complaint;
              if (complaints.containsKey(entry.complaintNum)) {
                complaint = complaints.get(entry.complaintNum);
              } else {
                complaint = ItemFactory.createComplaint(entry);
                complaints.put(entry.complaintNum, complaint);
                entities.add(complaint);
              }

              EntityData location;
              String key = entry.precinctCode + entry.boroName;
              if (locations.containsKey(key)) {
                location = locations.get(key);
              } else {
                location = ItemFactory.createLocation(entry);
                locations.put(key, location);
                entities.add(location);
              }

              EntityData suspect = ItemFactory.createSuspect(entry);
              EntityData victim = ItemFactory.createVictim(entry);
              entities.add(suspect);
              entities.add(victim);

              LinkData locationLink = ItemFactory.createLocationLink(entry, complaint, location);
              LinkData suspectLink = ItemFactory.createSuspectLink(entry, complaint, suspect);
              LinkData victimLink = ItemFactory.createVictimLink(entry, complaint, victim);
              links.add(locationLink);
              links.add(suspectLink);
              links.add(victimLink);
            });

    final ConnectorResponse connectorResponse = new ConnectorResponse();
    connectorResponse.entities = entities;
    connectorResponse.links = links;
    return connectorResponse;
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
}
