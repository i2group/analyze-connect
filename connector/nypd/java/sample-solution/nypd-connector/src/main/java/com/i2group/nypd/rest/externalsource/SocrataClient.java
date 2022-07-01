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

package com.i2group.nypd.rest.externalsource;

import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Connects to Socrata open APIs, setting the X-App-Token API header
 * https://dev.socrata.com/docs/app-tokens.html
 */
public class SocrataClient {
  private final RestTemplate restTemplate;
  private final String baseUrl;

  /**
   * Initialise the Socrata client.
   *
   * @param baseUrl The base URL for all requests.
   * @param apiToken The API token to be included in all requests.
   */
  public SocrataClient(String baseUrl, String apiToken) {
    if (baseUrl == null || baseUrl.isEmpty()) {
      throw new IllegalStateException("baseUrl must be specified");
    }
    if (apiToken == null || apiToken.isEmpty()) {
      throw new IllegalStateException("apiToken must be specified");
    }

    final List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
    interceptors.add(new ApiTokenInterceptor(apiToken));
    restTemplate = new RestTemplate();
    restTemplate.setInterceptors(interceptors);
    this.baseUrl = baseUrl;
  }

  /**
   * Send the request and retrieve results.
   *
   * @param url The constructed URL extension of the request.
   * @param responseType The template type of the list of POJOs to which the response is mapped.
   * @param uriVariables The map of parameters to be included within the request.
   * @return The body of the response.
   */
  public <T> T get(String url, Class<T> responseType, Map<String, ?> uriVariables) {
    try {
      final String requestUrl = baseUrl + url;
      final ResponseEntity<T> responseEntity =
          restTemplate.getForEntity(requestUrl, responseType, uriVariables);
      if (!responseEntity.getStatusCode().is2xxSuccessful()) {
        throw new IllegalStateException(
            "Request failed. Response code: " + responseEntity.getStatusCode());
      }
      return responseEntity.getBody();
    } catch (HttpClientErrorException e) {
      if (e.getStatusCode().value() == 403) {
        throw new IllegalStateException("403 response means the API_TOKEN is invalid", e);
      }
      throw e;
    }
  }

  /** For inclusion of the API token in requests. */
  private class ApiTokenInterceptor implements ClientHttpRequestInterceptor {
    private final String apiToken;

    /**
     * Initialise the interceptor.
     *
     * @param apiToken The API token to be included.
     */
    ApiTokenInterceptor(String apiToken) {
      this.apiToken = apiToken;
    }

    @Override
    public ClientHttpResponse intercept(
        HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
      request.getHeaders().add("X-App-Token", apiToken);
      return execution.execute(request, body);
    }
  }
}
