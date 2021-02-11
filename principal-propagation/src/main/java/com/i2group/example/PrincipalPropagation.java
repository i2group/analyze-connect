/********************************************************************************
# * Licensed Materials - Property of IBM
# * (C) Copyright IBM Corporation 2021. All Rights Reserved
# *
# * This program and the accompanying materials are made available under the
# * terms of the Eclipse Public License 2.0 which is available at
# * http://www.eclipse.org/legal/epl-2.0.
# *
# * US Government Users Restricted Rights - Use, duplication or
# * disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
# *
# ********************************************************************************/
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
