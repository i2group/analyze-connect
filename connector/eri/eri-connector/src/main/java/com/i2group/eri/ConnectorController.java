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

package com.i2group.eri;

import com.i2group.eri.rest.transport.request.ConnectorRequest;
import com.i2group.eri.rest.transport.response.ConnectorResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
public class ConnectorController {
    private final ExternalConnectorDataService connectorDataService;

    @Value("classpath:config.json")
    private Resource configResource;

    @Value("classpath:emergency-response-incidents-schema.xml")
    private Resource schemaResource;

    @Value("classpath:emergency-response-incidents-schema-charting-schemes.xml")
    private Resource chartingSchemesResource;

    /**
     * Creates an instance of the ExternalConnectorDataService used to
     * complete acquire requests.
     *
     * @param connectorDataService The service which performs operations on external data..
     */
    public ConnectorController(ExternalConnectorDataService connectorDataService) {
        this.connectorDataService = connectorDataService;
    }

    /**
     * Defines the /config endpoint which retrieves the connector's configuration.
     *
     * @return The connector configuration.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/config", produces =
        APPLICATION_JSON_VALUE)
    public Resource config() {
        return configResource;
    }

    /**
     * Defines the /schema endpoint which retrieves the connector schema.
     *
     * @return The connector schema.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/schema", produces =
        APPLICATION_JSON_VALUE)
    public Resource schema() {
        return schemaResource;
    }
    /**
     * Defines the /charting-schemes endpoint which retrieves the connector's charting schemes.
     *
     * @return The connector's charting schemes resource.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/charting-schemes", produces =
        APPLICATION_JSON_VALUE)
    public Resource chartingSchemes() {
        return chartingSchemesResource;
    }

    /**
     * Defines the /all endpoint which returns all entities and links from the data source.
     *
     * @return The response containing all entities and links.
     */
    @RequestMapping(method = RequestMethod.POST, value = "/all", consumes =
        APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ConnectorResponse all() {
        return connectorDataService.all();
    }

    /**
     * Defines the /search endpoint which retrieves entities and links based on defined conditions.
     *
     * @param request The request containing the payload.
     * @return The resulting entities and links after a conditional search.
     */
    @RequestMapping(method = RequestMethod.POST, value = "/search", consumes =
        APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ConnectorResponse searchService(@Valid @RequestBody ConnectorRequest request) {
        return connectorDataService.search(request.payload.conditions);
    }

    /**
     * Defines the /find-like-this-incident endpoint which finds incidents
     * similar to a selected incident.
     *
     * @param request The request containing the payload.
     * @return The entities found which are similar to the selected incident.
     */
    @RequestMapping(method = RequestMethod.POST, value = "/find-like-this-incident", consumes =
        APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ConnectorResponse findLikeThis(@Valid @RequestBody ConnectorRequest request) {
        return connectorDataService.findLikeThisIncident(request.payload.seeds);
    }

    /**
     * Defines the /expand endpoint which finds entities and links connected to selected incident.
     *
     * @param request The request containing the payload.
     * @return The resulting entities and links connected to the selected entities.
     */
    @RequestMapping(method = RequestMethod.POST, value = "/expand", consumes =
        APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ConnectorResponse expand(@Valid @RequestBody ConnectorRequest request) {
        return connectorDataService.expand(request.payload.seeds);
    }
}
