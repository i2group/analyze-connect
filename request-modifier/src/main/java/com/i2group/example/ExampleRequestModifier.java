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
package com.i2group.example;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.http.client.methods.HttpUriRequest;

import com.i2group.disco.daod.spi.IConnectorRequestModifier;

public final class ExampleRequestModifier implements IConnectorRequestModifier {

    // This header will be added to all requests with the value taken from EXAMPLE_CONFIG
    // or DEFAULT_VALUE based on the ID of the connector being used.
    // A real implementation could retrieve the value from a configuration file or other source such as a database or secrets store.
    private static final String EXAMPLE_VALUE_HEADER = "Example-Value-Header";
    private static final Map<String, String> EXAMPLE_CONFIG = Map.of(
          "example-connector", "exampleConValue",
          "async-connector", "asyncConValue",
          "nypd-connector", "nypdConValue");
    private static final String DEFAULT_VALUE = "defaultValue";

    // This header will be added to all requests for async queries with a unique value generated for each query to demonstrate the use of State.
    private static final String EXAMPLE_ASYNC_HEADER = "Example-Async-State-Header";

    @Override
    @Deprecated
    public void modifyRequest(HttpUriRequest request) {
        // This method is not used when the method below is implemented but is still required by the interface.
        // It can just be left empty.
    }

    @Override
    public void modifyRequest(HttpUriRequest request, IRequestContext requestContext) {
      // The request context can be used to retrieve information relevant to the current connector / service / query
      // Here we use the connector ID to determine the value to set for the EXAMPLE_VALUE_HEADER
      final String context = EXAMPLE_CONFIG.getOrDefault(requestContext.getConnectorId(), DEFAULT_VALUE);
      request.addHeader(EXAMPLE_VALUE_HEADER, context);
    }

    @Override
    public State modifyAsynchronousRequest(HttpUriRequest request, State state, IRequestContext requestContext) {
        // Apply the modifications made for synchronous requests:
        modifyRequest(request, requestContext);

        // Async State:
        // The State can be used to stash information at the time an async query is initiated so that is available
        // on subsequent requests for the same async query. To illustrate the concept a random id is generated and
        // sent for all requests related to the same query.

        State createdState = null;

        if (state == null) {
            // If state == null, this is the first call for this async query.
            final String serverGeneratedId = UUID.randomUUID().toString();
            final Map<String, String> valueMap = new HashMap<String, String>();
            valueMap.put(EXAMPLE_ASYNC_HEADER, serverGeneratedId);
            createdState = new State(valueMap);
        } else {
            createdState = state;
        }

        final String idHeaderValue = createdState.getValueMap().get(EXAMPLE_ASYNC_HEADER);
        request.addHeader(EXAMPLE_ASYNC_HEADER, idHeaderValue);

        return createdState;
    }
}
