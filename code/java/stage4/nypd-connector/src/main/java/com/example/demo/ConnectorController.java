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

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import javax.validation.Valid;

import com.example.demo.rest.transport.ConnectorResponse;
import com.example.demo.rest.transport.request.ConnectorRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Defines endpoints used by i2 Analyze to acquire data
 */
@RestController
public class ConnectorController {
    @Value("classpath:config.json")
    private Resource configResource;

    /**
     * Socrata external source
     */
    private final ExternalConnectorDataService connectorDataService;

    /**
     * Create an instance of the ExternalConnectorDataService used to complete aquires
     */
    public ConnectorController(ExternalConnectorDataService connectorDataService) {
        this.connectorDataService = connectorDataService;
    }

    /**
     * Defines the /config endpoint which acquires the config file
     * @return The config.json file
     */
    @RequestMapping(method = RequestMethod.GET, value = "/config", produces = APPLICATION_JSON_VALUE)
    public Resource config() {
        return configResource;
    }

    /**
     * Defines the /all endpoint which acquires the data (entities and links)
     * @return The entities and links provided by the test service
     */
    @RequestMapping(method = RequestMethod.POST, value = "/all", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ConnectorResponse testService() {
        return connectorDataService.retrieveTestDataFromExternalSource();
    }

    /**
     * Defines the /search endpoint which acquires the search data (entities and links based on conditions)
     * @param request Contains conditions provided by the user
     * @return The entities and links provided by the test search service
     */
    @RequestMapping(method = RequestMethod.POST, value = "/search", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ConnectorResponse testSearchService(@Valid @RequestBody ConnectorRequest request) {
        // TODO: Extract requst conditions and use in request to Socrata.
        System.out.println(request);
        return connectorDataService.retrieveTestDataFromExternalSource(/*search params*/);
    }
}
