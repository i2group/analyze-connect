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

package com.example.demo;

import com.example.demo.rest.transport.ConnectorResponse;
import com.example.demo.rest.transport.EntityData;
import com.example.demo.rest.transport.LinkData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    Map<String, Object> complaintProps = new HashMap<>();
    complaintProps.put("PT1", 660160752); // Complaint Number (int)
    complaintProps.put("PT2", "2017-05-25"); // Complaint Start Date (String)
    complaintProps.put("PT10", "Felony"); // Offence Level (String)
    complaintProps.put("PT18", 40.512460); // Latitude (float)

    Map<String, Object> locationProps = new HashMap<>();
    locationProps.put("PT15", 48); // Precent Code Number (int)
    locationProps.put("PT16", "BRONX"); // Borough Name (String)

    EntityData complaint = new EntityData();
    complaint.id = "COMP001";
    complaint.typeId = "ET1";
    complaint.properties = complaintProps;

    EntityData location = new EntityData();
    location.id = "LOC001";
    location.typeId = "ET2";
    location.properties = locationProps;

    LinkData link = new LinkData();
    link.id = "LOCLINK001";
    link.typeId = "LT1";
    link.fromEndId = complaint.id;
    link.toEndId = location.id;
    link.linkDirection = "WITH";

    entities.add(complaint);
    entities.add(location);
    links.add(link);

    final ConnectorResponse connectorResponse = new ConnectorResponse();
    connectorResponse.entities = entities;
    connectorResponse.links = links;
    return connectorResponse;
  }
}
