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

package com.i2group.async;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_XML_VALUE;

import javax.validation.Valid;

import com.i2group.connector.spi.rest.transport.AsyncQueryResponse;
import com.i2group.connector.spi.rest.transport.AsyncQueryStatus;
import com.i2group.connector.spi.rest.transport.DaodRequest;
import com.i2group.connector.spi.rest.transport.I2ConnectData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/** Defines endpoints used by i2 Analyze to acquire data */
@RestController
public class ConnectorController {

  private final ExternalConnectorDataService connectorDataService;
  @Value("classpath:config.json")
  private Resource configResource;
  @Value("classpath:async-schema.xml")
  private Resource schemaResource;
  @Value("classpath:async-charting-schemes.xml")
  private Resource chartingSchemesResource;

  /**
   * Creates an instance of the ExternalConnectorDataService used to complete
   * acquires.
   *
   * @param connectorDataService The class containing operations which perform the
   *                             operation defined by the various services.
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
  @RequestMapping(method = RequestMethod.GET, value = "/charting-schemes", produces = APPLICATION_XML_VALUE)
  public Resource chartingSchemes() {
    return chartingSchemesResource;
  }

  /**
   * Defines the /async endpoint.
   *
   * @return The query response.
   */
  @RequestMapping(method = RequestMethod.POST, value = "/async", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public AsyncQueryResponse asyncAcquireService(@Valid @RequestBody DaodRequest request) {
    return connectorDataService.asyncAcquire(request.payload.conditions);
  }

  /**
   * Defines the /async/{queryId} endpoint.
   *
   * @return The status response.
   */
  @RequestMapping(method = RequestMethod.GET, value = "/async/{queryId}", produces = APPLICATION_JSON_VALUE)
  public AsyncQueryStatus asyncStatusService(@Valid @PathVariable("queryId") String queryId) {
    return connectorDataService.asyncStatus(queryId);
  }

  /**
   * Defines the /async/{queryId}/results endpoint.
   *
   * @return The results of the query.
   */
  @RequestMapping(method = RequestMethod.GET, value = "/async/{queryId}/results", produces = APPLICATION_JSON_VALUE)
  public I2ConnectData asyncResultsService(@Valid @PathVariable("queryId") String queryId) {
    return connectorDataService.asyncResults(queryId);
  }

  /**
   * Defines the /async/{queryId} endpoint.
   *
   * @return The response with the removed query.
   */
  @RequestMapping(method = RequestMethod.DELETE, value = "/async/{queryId}", produces = APPLICATION_JSON_VALUE)
  public @ResponseBody I2ConnectData asyncDeleteService(@Valid @PathVariable("queryId") String queryId) {
    return connectorDataService.asyncDelete(queryId);
  }
}
