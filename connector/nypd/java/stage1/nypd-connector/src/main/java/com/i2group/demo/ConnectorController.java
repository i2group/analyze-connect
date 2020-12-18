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

package com.i2group.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/** Defines endpoints used by i2 Analyze to acquire data */
@RestController
public class ConnectorController {

  @Value("classpath:config.json")
  private Resource configResource;

  public ConnectorController() {}

  /**
   * Defines the /config endpoint which acquires the connector configuration data.
   *
   * @return The config.json file.
   */
  @RequestMapping(method = RequestMethod.GET, value = "/config", produces = APPLICATION_JSON_VALUE)
  public Resource config() {
    return configResource;
  }
}
