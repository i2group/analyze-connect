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
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.rest.transport.ValidationResponse;
import com.example.demo.rest.transport.request.RequestCondition;

/**
 * Defines endpoints used by i2 Analyze to acquire data
 */
@RestController
public class ConnectorController {
    @Value("classpath:config.json")
    private Resource configResource;

     /**
     * Service used to retrieve complaint data from the external source
     */
    private final ExternalConnectorDataService connectorDataService;

    /**
     * This is a constructor used to complete 'acquire' services
     * @param connectorDataService Class to create entities and links based on the NYPD Schema
     */
    public ConnectorController(ExternalConnectorDataService connectorDataService) {
        this.connectorDataService = connectorDataService;
    }

    /**
     * Defines the /config endpoint which acquires the connector configuration data
     * @return The connector configuration data
     */
    @RequestMapping(method = RequestMethod.GET, value = "/config", produces = APPLICATION_JSON_VALUE)
    public Resource config() {
        return configResource;
    }

    /**
     * Defines the /all endpoint which returns all entities and links
     * @return The entities and links provided by the service
     */
    @RequestMapping(method = RequestMethod.POST, value = "/all", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ConnectorResponse allService() {
        return connectorDataService.getAll();
    }

    /**
     * Defines the /search endpoint which acquires the entities and links based on conditions
     * @param request contains conditions provided by the user
     * @return The entities and links provided by the test search service
     */
    @RequestMapping(method = RequestMethod.POST, value = "/search", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ConnectorResponse searchService(@Valid @RequestBody ConnectorRequest request) {
        return connectorDataService.getConditional(request.payload.conditions);
    }

    /**
     * Defines the /search/validate endpoint which validates the entities and links based on conditions
     * @param request contains conditions provided by the user
     * @return A validationResponse containing an error message or nothing
     */
    @RequestMapping(method = RequestMethod.POST, value = "/search/validate", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ValidationResponse searchValidate(@Valid @RequestBody ConnectorRequest request) {
        final ValidationResponse validationResponse = new ValidationResponse();
        final List<RequestCondition> conditions = request.payload.conditions;
        final ComplaintSearch complaintSearch = ComplaintSearch.parse(conditions);
        if (complaintSearch.searchTerm == null) {
            validationResponse.errorMessage = "At least one search field must be specified";
        }
        return validationResponse;
    }
    
    /**
     * Defines the /find-like-this-complaint endpoint which acquires the find-like-this-complaint entities and links based on seeds
     * @param request contains seeds provided by the user
     * @return The entities and links provided by the test seeded search service
     */
    @RequestMapping(method = RequestMethod.POST, value = "/find-like-this-complaint", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ConnectorResponse findLikeThisService(@Valid @RequestBody ConnectorRequest request) {
        return connectorDataService.getFindLikeThis(request.payload.seeds);
    }

    /**
     * Defines the /expand endpoint which acquires the expand entities and links based on seeds
     * @param request contains seeds provided by the user
     * @return The entities and links provided by the expand service
     */
    @RequestMapping(method = RequestMethod.POST, value = "/expand", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ConnectorResponse expandService(@Valid @RequestBody ConnectorRequest request) {
        return connectorDataService.getExpand(request.payload.seeds);
    }

    /**
     * Defines the /expand-with-conditions endpoint which acquires the expand entities and links based on seeds
     * @param request Contains seeds provided by the user
     * @return The entities and links provided by the expand service
     */
    @RequestMapping(method = RequestMethod.POST, value = "/expand-with-conditions", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ConnectorResponse expandWithConditionsService(@Valid @RequestBody ConnectorRequest request) {
        return connectorDataService.getExpandWithConditions(request.payload.conditions, request.payload.seeds);
    }
}
