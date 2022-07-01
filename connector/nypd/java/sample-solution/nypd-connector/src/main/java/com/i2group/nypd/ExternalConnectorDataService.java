/*
 * MIT License
 *
 * © N.Harris Computer Corporation (2022)
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
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.stream.Collectors;

/** Used to query the NYPD complaint dataset, turning the raw data into entities and links. */
@Service
public class ExternalConnectorDataService {
  private final SocrataClient socrataClient;
  private final ItemFactory itemFactory;

  private final static String LIMIT_PARAM = "?$limit={limitValue}";

  /**
   * Constructor used to initialise the Socrata client and factory objects used to retrieve
   * complaint data.
   *  @param baseUrl The URL of the NYPD complaint dataset.
   * @param apiToken The API token used to access the NYPD complaint dataset.
   */
  @Autowired
  public ExternalConnectorDataService(
      @Value("${socrata.url}") String baseUrl,
      @Value("${socrata.api.token}") String apiToken,
      @Value("classpath:nypd-dataset-webpage.png") Resource resource) {
    this.itemFactory = new ItemFactory(baseUrl, resource);
    this.socrataClient = new SocrataClient(baseUrl, apiToken);
  }

  /**
   * Query the external dataset and retrieve all data.
   *
   * @return A response containing the entities and links.
   */
  public ConnectorResponse retrieveAll() {
    final Map<String, Object> params = new HashMap<>();
    params.put("limitValue", 50);

    final SocrataResponse response = socrataClient.get(LIMIT_PARAM, SocrataResponse.class, params);
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
    final StringBuilder url = new StringBuilder(LIMIT_PARAM);

    int count = 0;
    for (RequestCondition condition : conditions) {
      params.put(condition.id, condition.value);
      url.append(count == 0 ? "&$where=" : "&")
          .append(condition.id)
          .append("='{")
          .append(condition.id)
          .append("}'");
      count++;
    }

    final SocrataResponse response = socrataClient.get(url.toString(), SocrataResponse.class, params);

    final ConnectorResponse connectorResponse = new ConnectorResponse();
    connectorResponse.entities =
        response.stream().map(itemFactory::createComplaint).collect(Collectors.toList());
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
    final DaodSeedEntityData seed = seeds.entities.get(0);

    final Map<String, Object> params = new HashMap<>();
    params.put("limitValue", 50);
    params.put("lawCategory", seed.properties.get("PT10"));

    final String url = LIMIT_PARAM + "&$where=law_cat_cd='{lawCategory}'";
    final SocrataResponse response = socrataClient.get(url, SocrataResponse.class, params);

    final ConnectorResponse connectorResponse = new ConnectorResponse();
    connectorResponse.entities =
        response.stream().map(itemFactory::createComplaint).collect(Collectors.toList());
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
    final DaodSeedEntityData seed = seeds.entities.get(0);
    final Map<String, Object> params = buildExpandParameters(seed);

    final String url = LIMIT_PARAM + "&{field}={value}";

    final SocrataResponse socrataResponse = socrataClient.get(url, SocrataResponse.class, params);
    final ConnectorResponse response = marshalItemsFromResponse(socrataResponse);
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
    final DaodSeedEntityData seed = payload.seeds.entities.get(0);
    final Map<String, Object> params = buildExpandParameters(seed);

    final StringBuilder url = new StringBuilder(LIMIT_PARAM);
    url.append("&{field}={value}");

    int count = 0;
    for (RequestCondition condition : payload.conditions) {
      url.append(count == 0 ? "&$where=" : "&");
      params.put(condition.id, condition.value);
      url.append(condition.id)
          .append("='{")
          .append(condition.id)
          .append("}'");
      count++;
    }

    final SocrataResponse socrataResponse = socrataClient.get(url.toString(), SocrataResponse.class, params);
    final ConnectorResponse response = marshalItemsFromResponse(socrataResponse);
    response.links = linkToSeedIds(response, seed);

    return response;
  }

  private Map<String, Object> buildExpandParameters(DaodSeedEntityData seed) {
    String field = "";
    String value = "";

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
    return params;
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

    response
        .forEach(
            entry -> {
              final EntityData complaint;
              if (complaints.containsKey(entry.complaintNum)) {
                complaint = complaints.get(entry.complaintNum);
              } else {
                complaint = itemFactory.createComplaint(entry);
                complaints.put(entry.complaintNum, complaint);
                entities.add(complaint);
              }

              final EntityData location;
              final String key = entry.precinctCode + entry.boroName;
              if (locations.containsKey(key)) {
                location = locations.get(key);
              } else {
                location = itemFactory.createLocation(entry);
                locations.put(key, location);
                entities.add(location);
              }

              final EntityData suspect = itemFactory.createSuspect(entry);
              final EntityData victim = itemFactory.createVictim(entry);
              entities.add(suspect);
              entities.add(victim);

              final LinkData locationLink = itemFactory.createLocationLink(entry, complaint, location);
              final LinkData suspectLink = itemFactory.createSuspectLink(entry, complaint, suspect);
              final LinkData victimLink = itemFactory.createVictimLink(entry, complaint, victim);
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
      final String sourceId = seed.sourceIds.get(0).key.get(2);
      if (link.fromEndId.equals(sourceId)) {
        link.fromEndId = seed.seedId;
      } else if (link.toEndId.equals(sourceId)) {
        link.toEndId = seed.seedId;
      }
    }

    return response.links;
  }
}
