/*
 * MIT License
 *
 * © N.Harris Computer Corporation (2022)
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

package com.i2group.nypd;

import com.i2group.nypd.rest.transport.ConnectorResponse;
import com.i2group.nypd.rest.transport.ValidationResponse;
import com.i2group.nypd.rest.transport.request.ConnectorRequest;
import com.i2group.nypd.rest.transport.request.RequestCondition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_XML_VALUE;

/** Defines endpoints used by i2 Analyze to acquire data */
@RestController
public class ConnectorController {

  private static final double CONNECTOR_VERSION = 1.0;
  private final ExternalConnectorDataService connectorDataService;
  @Value("classpath:config.json")
  private Resource configResource;
  @Value("classpath:nypd-complaint-data-schema.xml")
  private Resource schemaResource;
  @Value("classpath:nypd-complaint-data-schema-charting-schemes.xml")
  private Resource chartingSchemesResource;

  /**
   * Creates an instance of the ExternalConnectorDataService used to complete acquires.
   *
   * @param connectorDataService The class containing operations which perform the operation defined
   *                             by the various services.
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
  public Resource config(@RequestHeader(value = "I2-Spi-Versions", required = false) List<Double> gatewaySupportedVersions) {
    System.out.println("Gateway supported versions: " + gatewaySupportedVersions);

    if (gatewaySupportedVersions.contains(CONNECTOR_VERSION)) {
      System.out.println("Loading connector version " + CONNECTOR_VERSION);
    } else {
      System.out.print("The gateway does not support connector version " + CONNECTOR_VERSION);
    }
    return configResource;
  }

  /**
   * Defines the endpoint used to retrieve the connector schema.
   *
   * @return The connector schema.
   */
  @RequestMapping(method = RequestMethod.GET, value = "/schema", produces = APPLICATION_XML_VALUE)
  public Resource schema() {
    return schemaResource;
  }

  /**
   * Defines the endpoint used to retrieve the connector charting schemes.
   *
   * @return The connector charting schemes.
   */
  @RequestMapping(method = RequestMethod.GET, value = "/charting-schemes",
      produces = APPLICATION_XML_VALUE)
  public Resource chartingSchemes() {
    return chartingSchemesResource;
  }

  /**
   * Defines the /all endpoint which returns all entities and links.
   *
   * @return The response containing all entities and links.
   */
  @RequestMapping(method = RequestMethod.POST, value = "/all", consumes = APPLICATION_JSON_VALUE,
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
  @RequestMapping(method = RequestMethod.POST, value = "/search", consumes = APPLICATION_JSON_VALUE,
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
  @RequestMapping(method = RequestMethod.POST, value = "/search/validate",
      consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public ValidationResponse searchValidate(@Valid @RequestBody ConnectorRequest request) {
    return validateSearchForm(request);
  }

  /**
   * Defines the /find-like-this-complaint endpoint which finds complaints similar to a selected
   * complaint.
   *
   * @param request The request containing the payload.
   * @return The entities found which are similar to the selected complaint.
   */
  @RequestMapping(method = RequestMethod.POST, value = "/find-like-this-complaint",
      consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
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
  @RequestMapping(method = RequestMethod.POST, value = "/expand", consumes = APPLICATION_JSON_VALUE,
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
  @RequestMapping(method = RequestMethod.POST, value = "/expand-with-conditions",
      consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public ConnectorResponse expandWithConditionsService(
      @Valid @RequestBody ConnectorRequest request) {
    return connectorDataService.expandWithConditions(request.payload);
  }

  /**
   * Defines the /expand-with-conditions/validate endpoint which validates the condition inputs.
   *
   * @param request The request containing the payload.
   * @return A response containing an error message or nothing.
   */
  @RequestMapping(method = RequestMethod.POST, value = "/expand-with-conditions/validate",
      consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public ValidationResponse expandWithConditionsValidate(
      @Valid @RequestBody ConnectorRequest request) {
    return validateSearchForm(request);
  }

  private ValidationResponse validateSearchForm(ConnectorRequest request) {
    final ValidationResponse validationResponse = new ValidationResponse();
    final List<RequestCondition> conditions = request.payload.conditions;
    final boolean conditionPresent = conditions.stream()
        .anyMatch(condition -> condition.value != null);

    if (!conditionPresent) {
      validationResponse.errorMessage = "At least one search field should have a specified value.";
    }
    return validationResponse;
  }
}
