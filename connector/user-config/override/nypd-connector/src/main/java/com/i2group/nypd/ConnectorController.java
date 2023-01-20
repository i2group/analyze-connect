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

package com.i2group.nypd;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.i2group.nypd.rest.transport.ConnectorResponse;
import com.i2group.nypd.rest.transport.ValidationResponse;
import com.i2group.nypd.rest.transport.request.ConnectorRequest;
import com.i2group.nypd.rest.transport.request.RequestCondition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_XML_VALUE;

/** Defines endpoints used by i2 Analyze to acquire data */
@RestController
public class ConnectorController {

  private final ExternalConnectorDataService connectorDataService;
  @Value("classpath:global-config.json")
  private Resource globalConfigResource;

  @Value("classpath:analyst-config.json")
  private Resource analystConfigResource;

  @Value("classpath:other-config.json")
  private Resource otherConfigResource;

  @Value("classpath:nypd-complaint-data-schema.xml")
  private Resource schemaResource;
  @Value("classpath:nypd-complaint-data-schema-charting-schemes.xml")
  private Resource chartingSchemesResource;

  @Autowired
  private ObjectMapper objectMapper;

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
   * Defines the /config endpoint which provides global connector configuration data.
   *
   * @return The global configuration.
   */
  @RequestMapping(method = RequestMethod.GET, value = "/config", produces = APPLICATION_JSON_VALUE)
  public Resource config() {
    return globalConfigResource;
  }

  /**
   * Defines the /userconfig endpoint, which provides user-specific connector configuration data.
   *
   * @return The user-specific configuration.
   */
  @RequestMapping(method = RequestMethod.GET, value = "/userconfig", produces = APPLICATION_JSON_VALUE)
  public Resource userConfig(@RequestHeader(value = "I2-Principal", required = false) String principalName,
      @RequestHeader(value = "I2-Display-Name", required = false) String displayName,
      @RequestHeader(value = "I2-User-Groups", required = false) String userGroups,
      @RequestHeader(value = "I2-Security-Permissions", required = false) String securityPermissions,
      @RequestHeader(value = "I2-Command-Access-Permissions", required = false) String cacPermissions) {

    final List<?> decodedGroups = decode(userGroups, List.class);
    if (decodedGroups != null && decodedGroups.contains("Analyst")) {
      System.out.println(principalName + " is an Analyst");
      return analystConfigResource;
    } else {
      System.out.println(principalName + " is not an Analyst");
      return otherConfigResource;
    }
  }

  private <T> T decode(String header, Class<T> type) {
    if (header == null) {
      return null;
    }
    final byte[] decoded = Base64.getDecoder().decode(header);
    try {
      return objectMapper.readValue(decoded, type);
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
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
    final ValidationResponse validationResponse = new ValidationResponse();
    final List<RequestCondition> conditions = request.payload.conditions;
    final boolean conditionPresent = conditions.stream()
        .anyMatch(condition -> condition.value != null);

    if (!conditionPresent) {
      validationResponse.errorMessage = "At least one search field should have a specified value.";
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
}
