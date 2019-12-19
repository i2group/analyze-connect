/********************************************************************************
# * Licensed Materials - Property of IBM
# * (C) Copyright IBM Corporation 2019. All Rights Reserved
# *
# * This program and the accompanying materials are made available under the
# * terms of the Eclipse Public License 2.0 which is available at
# * http://www.eclipse.org/legal/epl-2.0.
# *
# * US Government Users Restricted Rights - Use, duplication or
# * disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
# *
# ********************************************************************************/

package com.example.demo.rest.transport;

import java.util.List;

/**
 * Connector response data
 * The entities and links that form the foundations of any i2 Analyze query
 */
public class ConnectorResponse {
    public List<EntityData> entities;
    public List<LinkData> links;
}