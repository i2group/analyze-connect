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
import com.example.demo.rest.transport.ValidationResponse;
import com.example.demo.rest.transport.request.ConnectorRequest;
import com.example.demo.rest.transport.request.RequestCondition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/** Defines endpoints used by i2 Analyze to acquire data */
@RestController
public class ConnectorController {

  private final ExternalConnectorDataService connectorDataService;
  @Value("classpath:config.json")
  private Resource configResource;

  /**
   * Creates an instance of the ExternalConnectorDataService used to complete acquires.
   *
   * @param connectorDataService The class containing operations which perform the operation defined
   *     by the various services.
   */
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
  public ConnectorResponse allService() {
    return connectorDataService.retrieveAll();
  }

  /**
   * Defines the /search endpoint which retrieves entities and links based on conditions.
   *
   * @param request The request containing the payload.
   * @return The resulting entities and links after a conditional search.
   */
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/search",
      consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE)
  public ConnectorResponse searchService(@Valid @RequestBody ConnectorRequest request) {
    return connectorDataService.search(request.payload.conditions);
  }

  /**
   * Defines the /search/validate endpoint which validates the condition inputs.
   *
   * @param request The request containing the payload.
   * @return A response containing an error message or nothing.
   */
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/search/validate",
      consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE)
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
   * Defines the /find-like-this-complaint endpoint which finds complaints similar to a selected
   * complaint.
   *
   * @param request The request containing the payload.
   * @return The entities found which are similar to the selected complaint.
   */
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/find-like-this-complaint",
      consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE)
  public ConnectorResponse findLikeThisComplaintService(
      @Valid @RequestBody ConnectorRequest request) {
    return connectorDataService.findLikeThisComplaint(request.payload.seeds);
  }

  /**
   * Defines the /expand endpoint which finds entities and links connected to selected entities.
   *
   * @param request The request containing the payload.
   * @return The resulting entities and links connected to the selected entities.
   */
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/expand",
      consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE)
  public ConnectorResponse expandService(@Valid @RequestBody ConnectorRequest request) {
    return connectorDataService.expand(request.payload.seeds);
  }

  /**
   * Defines the /expand-with-conditions endpoint which finds entities and links connected to
   * selected entities and also satisfies passed conditions.
   *
   * @param request The request containing the payload.
   * @return The resulting entities and links which meet the requirements.
   */
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/expand-with-conditions",
      consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE)
  public ConnectorResponse expandWithConditionsService(
      @Valid @RequestBody ConnectorRequest request) {
    return connectorDataService.expandWithConditions(request.payload);
  }
}
