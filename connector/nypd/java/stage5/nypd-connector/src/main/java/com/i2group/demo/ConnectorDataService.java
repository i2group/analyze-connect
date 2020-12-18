/********************************************************************************
# * Licensed Materials - Property of IBM
# * (C) Copyright IBM Corporation 2020. All Rights Reserved
# *
# * This program and the accompanying materials are made available under the
# * terms of the Eclipse Public License 2.0 which is available at
# * http://www.eclipse.org/legal/epl-2.0.
# *
# * US Government Users Restricted Rights - Use, duplication or
# * disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
# *
# ********************************************************************************/

package com.i2group.demo;

import com.i2group.demo.rest.transport.ConnectorResponse;
import com.i2group.demo.rest.transport.EntityData;
import com.i2group.demo.rest.transport.LinkData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/** A class which demonstrates a connector response with dummy data. */
@Service
public class ConnectorDataService {

  /**
   * Manually populate a list of entities and links to show in Analyst's Notebook Premium.
   *
   * @return A response containing entities and links.
   */
  public ConnectorResponse retrieveTestData() {
    final List<EntityData> entities = new ArrayList<>();
    final List<LinkData> links = new ArrayList<>();

    // TODO: Populate entity and link lists with some test data and see that it is returned to ANBP

    final ConnectorResponse connectorResponse = new ConnectorResponse();
    connectorResponse.entities = entities;
    connectorResponse.links = links;
    return connectorResponse;
  }
}
