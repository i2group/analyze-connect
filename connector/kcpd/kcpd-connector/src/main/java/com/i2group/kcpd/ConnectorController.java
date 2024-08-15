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

package com.i2group.kcpd;

import com.i2group.connector.spi.rest.transport.DaodRequest;
import com.i2group.connector.spi.rest.transport.DaodRequestCondition;
import com.i2group.connector.spi.rest.transport.I2ConnectData;
import com.i2group.connector.spi.rest.transport.PayloadValidationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_XML_VALUE;

import java.util.List;

import javax.validation.Valid;

/** Defines endpoints used by i2 Analyze to acquire data */
@RestController
public class ConnectorController {

  private final ExternalConnectorDataService connectorDataService;
  @Value("classpath:config.json")
  private Resource configResource;
  @Value("classpath:kcpd-crime-data-schema.xml")
  private Resource schemaResource;
  @Value("classpath:kcpd-crime-data-schema-charting-schemes.xml")
  private Resource chartingSchemesResource;

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
   * @return The connector configuration.
   */
  @RequestMapping(method = RequestMethod.GET, value = "/config", produces = APPLICATION_JSON_VALUE)
  public Resource config() {
    return configResource;
  }

  /**
   * Defines the /schema endpoint used to retrieve the connector schema.
   * 
   * @return The connector schema.
   */
  @RequestMapping(method = RequestMethod.GET, value = "/schema", produces = APPLICATION_XML_VALUE)
  public Resource schema() {
    return schemaResource;
  }

  /**
   * Defines the /charting-schemes endpoint used to retrieve the connector charting schemes.
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
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/all",
      consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE)
  public I2ConnectData allService() {
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
  public I2ConnectData searchService(@Valid @RequestBody DaodRequest request) {
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
  public PayloadValidationResponse searchValidate(@Valid @RequestBody DaodRequest request) {
    final PayloadValidationResponse validationResponse = new PayloadValidationResponse();
    final List<DaodRequestCondition> conditions = request.payload.conditions;
    final Search search = Search.parse(conditions);
    if (search.searchTerm == null) {
      validationResponse.errorMessage = "At least one search field must be specified";
    }
    return validationResponse;
  }

  /**
   * Defines the /find-like-this-location endpoint which finds reports that are 
   * located at the selected location.
   *
   * @param request The request containing the payload.
   * @return The entities found which have the same location as the selected location.
   */
  @RequestMapping(method = RequestMethod.POST, value = "/find-like-this-location",
      consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public I2ConnectData findLikeThisLocationService(
      @Valid @RequestBody DaodRequest request) {
    return connectorDataService.findLikeThisLocation(request.payload.seeds);
  }

  /**
   * Defines the /expand endpoint which finds entities and links connected to selected reports.
   *
   * @param request The request containing the payload.
   * @return The resulting entities and links connected to the selected entities.
   */
  @RequestMapping(method = RequestMethod.POST, value = "/expand", consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE)
  public I2ConnectData expandService(@Valid @RequestBody DaodRequest request) {
    return connectorDataService.expand(request.payload.seeds);
  }
}
