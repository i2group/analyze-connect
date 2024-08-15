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

package com.i2group.eri;

import com.i2group.connector.spi.rest.transport.DaodRequestCondition;
import com.i2group.connector.spi.rest.transport.DaodSeedEntityData;
import com.i2group.connector.spi.rest.transport.DaodSeeds;
import com.i2group.connector.spi.rest.transport.I2ConnectData;
import com.i2group.connector.spi.rest.transport.I2ConnectEntityData;
import com.i2group.connector.spi.rest.transport.I2ConnectLinkData;
import com.i2group.eri.rest.externalsource.SocrataClient;
import com.i2group.eri.rest.externalsource.transport.SocrataResponse;
import com.i2group.eri.rest.transport.response.ItemFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class ExternalConnectorDataService {
    private final SocrataClient socrataClient;
    private static final String LIMIT_FIELD = "limitValue";
    private static final int LIMIT_VALUE = 50;
    private static final String BASE_QUERY = "?$limit={limitValue}";

    private static final String WHERE = "&$where=";
    private static final String LIKE = " like ";
    private static final String IS = "=";
    private static final String AND = " AND ";

    private static final String FIELD_INCIDENT_TYPE = "incident_type";
    private static final String FIELD_BOROUGH = "borough";
    private static final String FIELD_ADDRESS = "location";

    /**
     * Constructor used to initialise the Socrata client used to retrieve
     * external data.
     *
     * @param baseUrl  The URL of the Emergency Response Incident dataset.
     * @param apiToken The API token required to access the dataset.
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
    public I2ConnectData all() {
        final Map<String, Object> params = new HashMap<>();
        params.put(LIMIT_FIELD, LIMIT_VALUE);

        final SocrataResponse response = socrataClient.get(BASE_QUERY, SocrataResponse.class,
            params);
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
        params.put(LIMIT_FIELD, LIMIT_VALUE);
        String url = BASE_QUERY;

        int count = 0;
        for (DaodRequestCondition condition : conditions) {
            if (condition.value != null) {
                url += count == 0 ? WHERE : AND;
                params.put(condition.id, condition.value);
                url += condition.id + LIKE + "'{" + condition.id + "}%'";
                count++;
            }
        }

        final SocrataResponse response = socrataClient.get(url, SocrataResponse.class, params);
        final AtomicInteger index = new AtomicInteger();

        final I2ConnectData connectorResponse = new I2ConnectData();
        connectorResponse.entities = response.stream()
            .map(entry -> ItemFactory.createIncident(entry,
                entry.getIncidentKey(index.getAndIncrement())))
            .collect(Collectors.toList());
        return connectorResponse;
    }

    /**
     * Performs a Find-Like-This operation, finding incidents with similar
     * properties to a selected incident.
     *
     * @param seeds The selected entities provided by the user via the
     *              interface.
     * @return A response containing the entities and links.
     */
    public I2ConnectData findLikeThisIncident(DaodSeeds seeds) {
        DaodSeedEntityData seed = seeds.entities.get(0);

        final Map<String, Object> params = new HashMap<>();
        params.put(LIMIT_FIELD, LIMIT_VALUE);
        params.put("incidentType", seed.properties.get("PT1"));
        String url = BASE_QUERY + WHERE + FIELD_INCIDENT_TYPE + LIKE + "'{incidentType}%'";

        final SocrataResponse response = socrataClient.get(url, SocrataResponse.class, params);
        final AtomicInteger index = new AtomicInteger();

        final I2ConnectData connectorResponse = new I2ConnectData();
        connectorResponse.entities = response.stream()
            .map(entry -> ItemFactory.createIncident(entry,
                entry.getIncidentKey(index.getAndIncrement())))
            .collect(Collectors.toList());
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

        final Map<String, Object> params = new HashMap<>();
        params.put(LIMIT_FIELD, LIMIT_VALUE);
        String url = BASE_QUERY;

        if (seed.typeId.equals("ET1")) {
            final String incidentType = seed.properties.get("PT1")
                .toString();
            final String incidentSubtype = seed.properties.get("PT2")
                .toString();
            params.put("field", FIELD_INCIDENT_TYPE);
            params.put("value", String.format("%s-%s", incidentType, incidentSubtype));
            url += WHERE + "{field}" + IS + "'{value}'";
        } else if (seed.typeId.equals("ET2")) {
            final String borough = seed.properties.get("PT7")
                .toString();
            final String address = seed.properties.get("PT8")
                .toString();
            params.put("field_borough", FIELD_BOROUGH);
            params.put("field_address", FIELD_ADDRESS);
            params.put("value_borough", borough);
            params.put("value_address", address);
            url += WHERE + "{field_borough}" + IS + "'{value_borough}'" + AND + "{field_address}" + IS + "'{value_address}'";
        }

        final SocrataResponse socrataResponse = socrataClient.get(url, SocrataResponse.class,
            params);
        final I2ConnectData response = marshalItemsFromResponse(socrataResponse);
        response.links = linkToSeedIds(response, seed);

        return response;
    }

    /**
     * Marshal the response items into a list of entities and links. Ensures no duplicate
     * incidents or locations are included.
     *
     * @param response The resulting source records returned from the request.
     * @return The response containing entities and links.
     */
    private I2ConnectData marshalItemsFromResponse(SocrataResponse response) {
        final List<I2ConnectEntityData> entities = new ArrayList<>();
        final List<I2ConnectLinkData> links = new ArrayList<>();

        final Map<String, I2ConnectEntityData> incidents = new HashMap<>();
        final Map<String, I2ConnectEntityData> locations = new HashMap<>();

        final AtomicInteger count = new AtomicInteger();

        response.forEach(entry -> {
            count.getAndIncrement();

          I2ConnectEntityData incident;
            final String incidentKey = entry.getIncidentKey();
            if (incidents.containsKey(incidentKey)) {
                incident = incidents.get(incidentKey);
            } else {
                incident = ItemFactory.createIncident(entry, incidentKey);
                incidents.put(incidentKey, incident);
                entities.add(incident);
            }

          I2ConnectEntityData location;
            final String locationKey = entry.getLocationKey();
            if (locations.containsKey(locationKey)) {
                location = locations.get(locationKey);
            } else {
                location = ItemFactory.createLocation(entry, locationKey);
                locations.put(locationKey, location);
                entities.add(location);
            }

            final I2ConnectLinkData locationLink = ItemFactory.createLocationLink(incident, location,
                count.get());
            links.add(locationLink);
        });

        final I2ConnectData connectorResponse = new I2ConnectData();
        connectorResponse.entities = entities;
        connectorResponse.links = links;
        return connectorResponse;
    }

    /**
     * Match link ends to seed identifiers.
     *
     * @param response The response containing the marshaled entities and links.
     * @param seed     The selected entity provided by the user via the interface.
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
}
