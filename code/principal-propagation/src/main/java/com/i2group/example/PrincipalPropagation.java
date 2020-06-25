/*********************************************************************************
# * (C) Copyright IBM Corporation 2020.
# *
# * This program and the accompanying materials are made available under the
# * terms of the Eclipse Public License 2.0 which is available at
# * http://www.eclipse.org/legal/epl-2.0.
# *
# * SPDX-License-Identifier: EPL-2.0
# *
/********************************************************************************/
package com.i2group.example;

import org.apache.http.client.methods.HttpUriRequest;

import com.ibm.websphere.security.auth.WSSubject;
import com.i2group.disco.daod.spi.IConnectorRequestModifier;

public final class PrincipalPropagation implements IConnectorRequestModifier {

	@Override
	public void modifyRequest(HttpUriRequest request) {
			final String callerPrincipal = WSSubject.getCallerPrincipal();
			request.addHeader("i2ExtensionHeader", callerPrincipal);
			
	}
}
