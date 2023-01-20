/********************************************************************************
# MIT License
#
# © N.Harris Computer Corporation (2023) 
#
# Permission is hereby granted, free of charge, to any person obtaining a copy
# of this software and associated documentation files (the "Software"), to deal
# in the Software without restriction, including without limitation the rights
# to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
# copies of the Software, and to permit persons to whom the Software is
# furnished to do so, subject to the following conditions:
#
# The above copyright notice and this permission notice shall be included in all
# copies or substantial portions of the Software.
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
# SOFTWARE.
 ********************************************************************************/

package com.i2group.auth;

import com.i2group.auth.rest.transport.ConnectorResponse;
import com.i2group.auth.rest.transport.async.AsyncQueryResponse;
import com.i2group.auth.rest.transport.async.AsyncStatusResponse;
import com.i2group.auth.rest.transport.auth.AuthRequest;
import com.i2group.auth.rest.transport.auth.AuthResponse;
import com.i2group.auth.rest.transport.auth.ProblemDetails;
import com.i2group.auth.rest.transport.request.ConnectorRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_XML_VALUE;

/**
 * Defines the endpoints used by i2 Analyze.
 */
@RestController
public class ConnectorController {

  private final ExternalConnectorDataService connectorDataService;
  private final static String AUTHORIZATION = "Authorization";

  @Value("classpath:config.json")
  private Resource configResource;
  @Value("classpath:auth-schema.xml")
  private Resource schemaResource;
  @Value("classpath:auth-charting-schemes.xml")
  private Resource chartingSchemesResource;

  /**
   * Creates an instance of the ExternalConnectorDataService used to complete acquires.
   *
   * @param connectorDataService The class containing operations which perform the
   * operation defined by the various services.
   */
  public ConnectorController(
      ExternalConnectorDataService connectorDataService) {
    this.connectorDataService = connectorDataService;
  }

  /**
   * Retrieves the connector configuration.
   *
   * @return The connector configuration.
   * @responseMessage 200 Charting scheme successfully retrieved.
   */
  @RequestMapping(method = RequestMethod.GET, value = "/config",
      produces = APPLICATION_JSON_VALUE)
  public Resource config() {
    return configResource;
  }

  /**
   * Retrieves the connector schema.
   *
   * @return The connector schema.
   * @responseMessage 200 Connector schema successfully retrieved.
   */
  @RequestMapping(method = RequestMethod.GET, value = "/schema",
      produces = APPLICATION_XML_VALUE)
  public Resource schema() {
    return schemaResource;
  }

  /**
   * Retrieves the connector charting schemes.
   *
   * @return The connector charting schemes.
   * @responseMessage 200 Connector charting schemes successfully retrieved.
   */
  @RequestMapping(method = RequestMethod.GET, value = "/charting-schemes",
      produces = APPLICATION_XML_VALUE)
  public Resource chartingSchemes() {
    return chartingSchemesResource;
  }

  /**
   * Defines the login endpoint for authenticating using a submitted username and
   * password. Responds with {@link AuthResponse} if submitted credentials are valid;
   * {@link ProblemDetails} if credentials are invalid.
   *
   * @param request The {@link AuthRequest} payload containing the submitted username and
   * password.
   * @return The login response.
   * @responseMessage 200 Submitted username and password are valid.
   * @responseMessage 401 Submitted username or password are invalid.
   */
  @RequestMapping(method = RequestMethod.POST, value = "/login/userpass",
      consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public Object loginUserPass(@Valid @RequestBody AuthRequest request) {
    return connectorDataService.loginUserPass(request);
  }

  /**
   * Defines the login endpoint for authenticating using a submitted API key. Responds
   * with {@link AuthResponse} if submitted credentials are valid; {@link ProblemDetails}
   * if credentials are invalid.
   *
   * @param request The {@link AuthRequest} payload containing the submitted API key.
   * @return The login response.
   * @responseMessage 200 Submitted API key is valid.
   * @responseMessage 401 Submitted API key is invalid.
   */
  @RequestMapping(method = RequestMethod.POST, value = "/login/apikey",
      consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public Object loginApiKey(@Valid @RequestBody AuthRequest request) {
    return connectorDataService.loginApiKey(request);
  }

  /**
   * Defines the acquire endpoint. {@link ConnectorResponse} with entities and links if
   * authorization token is valid; {@link ProblemDetails} response if authorization token
   * is invalid.
   *
   * @return The acquire response.
   * @responseMessage 200 Entities and links retrieved.
   * @responseMessage 401 Authentication token is invalid or expired.
   */
  @RequestMapping(method = RequestMethod.POST, value = "/acquire",
      consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<?> acquire(
      @RequestHeader(value = AUTHORIZATION, required = false) String auth) {
    return connectorDataService.acquire(auth);
  }

  /**
   * The endpoint called to launch an asynchronous query. Responds with {@link
   * AsyncQueryResponse} if authorization token is valid; {@link ProblemDetails} if
   * authorization token is invalid.
   *
   * @return The async acquire response.
   * @responseMessage 201 Asynchronous query started successfully.
   * @responseMessage 401 Authentication token is invalid or expired.
   */
  @RequestMapping(method = RequestMethod.POST, value = "/async",
      consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<?> asyncAcquire(
      @RequestHeader(value = AUTHORIZATION, required = false)
          String auth, @Valid @RequestBody ConnectorRequest request) {
    return connectorDataService.asyncAcquire(auth, request.payload.conditions);
  }

  /**
   * The endpoint called to retrieve the status of an running asynchronous query. Responds
   * with the {@link AsyncStatusResponse} if authorization token is valid; {@link
   * ProblemDetails} query ID if authorization token is invalid.
   *
   * @return The status response.
   * @responseMessage 200 Status of asynchronous query successfully retrieved.
   * @responseMessage 401 Authentication token is invalid or expired.
   */
  @RequestMapping(method = RequestMethod.GET, value = "/async/{queryId}",
      produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<?> asyncStatus(
      @RequestHeader(value = AUTHORIZATION, required = false)
          String auth, @Valid @PathVariable("queryId")
          String queryId) {
    return connectorDataService.asyncStatus(auth, queryId);
  }

  /**
   * The endpoint called to retrieve the results of an asynchronous query. Responds with
   * {@link ConnectorResponse} containing the results of the query; {@link ProblemDetails}
   * if authorization token is invalid.
   *
   * @return The results of the query.
   * @responseMessage 200 Results of asynchronous query successfully retrieved.
   * @responseMessage 401 Authentication token is invalid or expired.
   */
  @RequestMapping(method = RequestMethod.GET,
      value = "/async/{queryId}/results", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<?> asyncResults(
      @RequestHeader(value = AUTHORIZATION, required = false)
          String auth, @Valid @PathVariable("queryId")
          String queryId) {
    return connectorDataService.asyncResults(auth, queryId);
  }

  /**
   * The endpoint called to cancel a running query.
   *
   * @return The response with the removed query.
   * @responseMessage 204 Asynchronous query successfully canceled.
   */
  @RequestMapping(method = RequestMethod.DELETE, value = "/async/{queryId}",
      produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> asyncDelete(
      @Valid @PathVariable("queryId") String queryId) {
    return connectorDataService.asyncDelete(queryId);
  }
}
