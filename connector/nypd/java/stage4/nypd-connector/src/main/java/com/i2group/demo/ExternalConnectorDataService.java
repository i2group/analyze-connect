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

package com.i2group.demo;

import com.i2group.demo.rest.externalsource.SocrataClient;
import com.i2group.demo.rest.externalsource.transport.SocrataResponse;
import com.i2group.demo.rest.transport.ConnectorResponse;
import com.i2group.demo.rest.transport.EntityData;
import com.i2group.demo.rest.transport.LinkData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Used to query the NYPD complaint dataset, turning the raw data into entities and links. */
@Service
public class ExternalConnectorDataService {

  private final SocrataClient socrataClient;

  // TODO: You must create an API Token in socrata and specify it in application.properties
  // TODO: Specify the url of the resource in application.properties

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
   * Used to query the Socrata database, turning the raw data into entities and links that can be
   * shown in ANBP
   *
   * @return A connectorResponse with the entities and links
   */
  public ConnectorResponse retrieveTestDataFromExternalSource() {
    final List<EntityData> entities = new ArrayList<>();
    final List<LinkData> links = new ArrayList<>();

    // TODO: Make request to external source and map data back to your own schema

    // Set some request parameters
    // See https://dev.socrata.com/docs/queries/ for SODA query params
    final Map<String, Object> params = new HashMap<>();
    params.put("limitValue", 1); // Only returning 1 entity for the moment. increase when ready
    final String url = "?$limit={limitValue}";

    // Make the request and map the whole response body as a string so that you can
    // see what is returned
    // TODO: Remove this since it's just for debugging
    System.out.println(socrataClient.get(url, String.class, params));

    // Make the request, map the response to an object model and print each
    // object
    final SocrataResponse response = socrataClient.get(url, SocrataResponse.class, params);
    response.stream()
        .forEach(
            entry -> {
              // TODO: Map response data to your own object model
              System.out.println("item: " + entry);
            });

    final ConnectorResponse connectorResponse = new ConnectorResponse();
    connectorResponse.entities = entities;
    connectorResponse.links = links;
    return connectorResponse;
  }
}
