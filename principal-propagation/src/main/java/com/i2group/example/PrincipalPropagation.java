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

import com.i2group.disco.daod.spi.IConnectorRequestModifier;
import com.ibm.websphere.security.auth.WSSubject;
import org.apache.http.client.methods.HttpUriRequest;

import java.util.HashMap;
import java.util.Map;

public final class PrincipalPropagation implements IConnectorRequestModifier {

    private final String I2_EXTENSION_HEADER = "i2ExtensionHeader";

    @Override
    public void modifyRequest(HttpUriRequest request) {
        final String callerPrincipal = WSSubject.getCallerPrincipal();
        request.addHeader(I2_EXTENSION_HEADER, callerPrincipal);
    }

    @Override
    public State modifyAsynchronousRequest(HttpUriRequest request, State state) {
        State createdState = null;

        if (state == null) {
            // If state == null, this is the first call. WSSubject.getCallerPrincipal() will be
            // in scope along with the caller request if needed.
            final String callerPrincipal = WSSubject.getCallerPrincipal();
            final Map<String, String> principalMap = new HashMap<String, String>();
            principalMap.put(I2_EXTENSION_HEADER, callerPrincipal);
            createdState = new State(principalMap);
        } else {
            createdState = state;
        }

        request.addHeader(I2_EXTENSION_HEADER, createdState.getValueMap()
            .get(I2_EXTENSION_HEADER));

        return createdState;
    }
}
