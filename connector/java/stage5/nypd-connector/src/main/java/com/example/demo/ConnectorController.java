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

import com.example.demo.rest.transport.ConnectorResponse;
import com.example.demo.rest.transport.request.ConnectorRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/** Defines endpoints used by i2 Analyze to acquire data */
@RestController
public class ConnectorController {

  private final ExternalConnectorDataService connectorDataService;
  @Value("classpath:config.json")
  private Resource configResource;

  /** Creates an instance of the ExternalConnectorDataService used to complete aquires */
  public ConnectorController(ExternalConnectorDataService connectorDataService) {
    this.connectorDataService = connectorDataService;
  }

  /**
   * Defines the /config endpoint which acquires the connector configuration data.
   *
   * @return The config.json file.
   */
  @RequestMapping(method = RequestMethod.GET, value = "/config", produces = APPLICATION_JSON_VALUE)
  public Resource config() {
    return configResource;
  }

  /**
   * Defines the /all endpoint which returns all entities and links.
   *
   * @return The response containing all entities and links.
   */
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/all",
      consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE)
  public ConnectorResponse testService() {
    return connectorDataService.retrieveTestDataFromExternalSource();
  }

  /**
   * Defines the /search endpoint which acquires the search data (entities and links based on
   * conditions)
   *
   * @param request Contains conditions provided by the user
   * @return The entities and links provided by the test search service
   */
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/search",
      consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE)
  public ConnectorResponse testSearchService(@Valid @RequestBody ConnectorRequest request) {
    // TODO: Extract request conditions and use in request to Socrata.
    System.out.println(request);
    return connectorDataService.retrieveTestDataFromExternalSource(/*search params*/ );
  }

  /**
   * Defines the seeded search endpoint which acquires the seeded search data (entities and links
   * based on seeds)
   *
   * @param request The request containing the payload.
   * @return The resulting entities and links connected to the selected entities.
   */
  @RequestMapping(
      method = RequestMethod.POST,
      value = "SEEDED SEARCH ACQUIRE URL GOES HERE",
      consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE)
  public ConnectorResponse testSeededSearchService(@Valid @RequestBody ConnectorRequest request) {
    // TODO: Extract seed information and use it to search
    System.out.println(request.payload.seeds);
    return connectorDataService.retrieveTestDataFromExternalSource(/*seeds*/ );
  }
}
