/********************************************************************************
# * Licensed Materials - Property of IBM
# * (C) Copyright IBM Corporation 2019. All Rights Reserved
# *
# * This program and the accompanying materials are made available under the
# * terms of the Eclipse Public License 2.0 which is available at
# * http://www.eclipse.org/legal/epl-2.0.
# *
# * US Government Users Restricted Rights - Use, duplication or
# * disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
# *
# ********************************************************************************/

package com.example.demo;

import com.example.demo.rest.externalsource.SocrataClient;
import com.example.demo.rest.externalsource.transport.SocrataResponse;
import com.example.demo.rest.transport.ConnectorResponse;
import com.example.demo.rest.transport.EntityData;
import com.example.demo.rest.transport.LinkData;
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
