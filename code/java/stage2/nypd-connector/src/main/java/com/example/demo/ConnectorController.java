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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/** Defines endpoints used by i2 Analyze to acquire data */
@RestController
public class ConnectorController {

  private final ConnectorDataService connectorDataService;
  @Value("classpath:config.json")
  private Resource configResource;

  /**
   * Creates an instance of the ExternalConnectorDataService used to complete acquires.
   *
   * @param connectorDataService The class containing operations which perform the operation defined
   *     by the various services.
   */
  public ConnectorController(ConnectorDataService connectorDataService) {
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
   * Defines the /all endpoint which returns the dummy entities and links.
   *
   * @return The response containing all entities and links.
   */
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/all",
      consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE)
  public ConnectorResponse testService() {
    return connectorDataService.retrieveTestData();
  }
}
