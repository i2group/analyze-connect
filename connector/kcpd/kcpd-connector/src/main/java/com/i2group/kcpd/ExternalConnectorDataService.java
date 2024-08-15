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

package com.i2group.kcpd;

import com.i2group.connector.spi.rest.transport.DaodRequestCondition;
import com.i2group.connector.spi.rest.transport.DaodSeedEntityData;
import com.i2group.connector.spi.rest.transport.DaodSeeds;
import com.i2group.connector.spi.rest.transport.I2ConnectData;
import com.i2group.connector.spi.rest.transport.I2ConnectEntityData;
import com.i2group.connector.spi.rest.transport.I2ConnectLinkData;
import com.i2group.kcpd.rest.externalsource.SocrataClient;
import com.i2group.kcpd.rest.externalsource.transport.SocrataResponse;
import com.i2group.kcpd.rest.externalsource.transport.SocrataResponseData;
import com.i2group.kcpd.rest.transport.ItemFactory;

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
  public I2ConnectData retrieveAll() {
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
  public I2ConnectData search(List<DaodRequestCondition> conditions) {
    final Map<String, Object> params = new HashMap<>();
    params.put(LIMIT_FIELD, limitFieldValue);
    String url = BASE_URL;

    int count = 0;
    for (DaodRequestCondition condition : conditions) {
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
    final I2ConnectData connectorResponse = new I2ConnectData();

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
  public I2ConnectData findLikeThisLocation(DaodSeeds seeds) {
    final DaodSeedEntityData seed = seeds.entities.get(0);
    final Map<String, Object> params = new HashMap<>();

    params.put(LIMIT_FIELD, limitFieldValue);
    params.put("address", seed.properties.get("PT10"));
    final String url = BASE_URL + "&$where=address='{address}'";

    final SocrataResponse response = socrataClient.get(url, SocrataResponse.class, params);

    final I2ConnectData connectorResponse = new I2ConnectData();
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
  public I2ConnectData expand(DaodSeeds seeds) {
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
    I2ConnectData response = marshalItemsFromResponse(socrataResponse);
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
  private I2ConnectData marshalItemsFromResponse(SocrataResponse response) {
    final List<I2ConnectEntityData> entities = new ArrayList<>();
    final List<I2ConnectLinkData> links = new ArrayList<>();
    final Set<String> personLinks = new HashSet<>();

    final Map<String, I2ConnectEntityData> reports = new HashMap<>();
    final Map<String, I2ConnectEntityData> locations = new HashMap<>();
    final Map<String, I2ConnectEntityData> people = new HashMap<>();
    final Map<String, I2ConnectLinkData> locationLinks = new HashMap<>();

    response
        .forEach(
            entry -> {
              I2ConnectEntityData report;
              String uniqueReport = entry.reportNumber;
              if (reports.containsKey(uniqueReport)) {
                report = reports.get(uniqueReport);
              } else {
                report = ItemFactory.createReport(entry);
                reports.put(uniqueReport, report);
                entities.add(report);
              }

              I2ConnectEntityData location;
              String uniqueLocation = entry.address + entry.zipCode;
              if (locations.containsKey(uniqueLocation)) {
                location = locations.get(uniqueLocation);
              } else {
                location = ItemFactory.createLocation(entry);
                locations.put(uniqueLocation, location);
                entities.add(location);
              }

              I2ConnectEntityData person;
              String uniquePerson = ItemFactory.buildId(entry);
              if (people.containsKey(uniquePerson)) {
                person = people.get(uniquePerson);
              } else {
                person = ItemFactory.createPerson(entry);
                people.put(uniquePerson, person);
                entities.add(person);
              }

              I2ConnectLinkData locationLink;
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

    final I2ConnectData connectorResponse = new I2ConnectData();
    connectorResponse.entities = entities;
    connectorResponse.links = links;
    return connectorResponse;
  }

  private void addLinks(final List<I2ConnectLinkData> links, SocrataResponseData entry, I2ConnectEntityData report, I2ConnectEntityData person) {
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
  private List<I2ConnectLinkData> linkToSeedIds(I2ConnectData response, DaodSeedEntityData seed) {
    for (I2ConnectLinkData link : response.links) {
      String sourceId = seed.sourceIds.get(0).key.get(2);
      if (link.fromEndId.equals(sourceId)) {
        link.fromEndId = seed.seedId;
      } else if (link.toEndId.equals(sourceId)) {
        link.toEndId = seed.seedId;
      }
    }
    return response.links;
  }

  private List<I2ConnectEntityData> buildEntities(final SocrataResponse response) {
    List<String> uniqueId = new ArrayList<>();
    List<I2ConnectEntityData> entities = new ArrayList<>();

    response.forEach(entry -> {
      if (!uniqueId.contains(entry.reportNumber)) {
        I2ConnectEntityData entity = ItemFactory.createReport(entry);
        entities.add(entity);
        uniqueId.add(entry.reportNumber);
      }
    });
    return entities;
  }
}
