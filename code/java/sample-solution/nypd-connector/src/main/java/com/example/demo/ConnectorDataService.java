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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.demo.rest.transport.ConnectorResponse;
import com.example.demo.rest.transport.EntityData;
import com.example.demo.rest.transport.LinkData;

import org.springframework.stereotype.Service;

/**
 * Used to populate entity lists and link lists to return to the ANBP
 */
@Service
public class ConnectorDataService {

    /**
     * Used to populate entity lists and link lists to return to the ANBP
     * @return A connectorResponse with the entities and links
     */
    public ConnectorResponse retrieveTestData() {
        final List<EntityData> entities = new ArrayList<>();
        final List<LinkData> links = new ArrayList<>();
    
        EntityData complaint = new EntityData();
        complaint.id = "complaint1";
        complaint.typeId = "ET1";
        complaint.version = 1L;
    
        HashMap<String, Object> propertiesComplaint = new HashMap<String, Object>();

        //Dummy data for a Complaint Number (Integer Type)
        propertiesComplaint.put("PT1", 660160752);

        //Dummy data for a Complaint Start Date (Date Type as String, but gets converted into LocalDate)
        propertiesComplaint.put("PT2", "2017-05-25");

        //Dummy data for the Offence Level (String Type)
        propertiesComplaint.put("PT10", "Felony");

        //Dummy data for the Latitude (Float Type)
        propertiesComplaint.put("PT18", 40.512460100000055);

        complaint.properties = propertiesComplaint;
        entities.add(complaint);

        final ConnectorResponse connectorResponse = new ConnectorResponse();
        connectorResponse.entities = entities;
        connectorResponse.links = links;
        return connectorResponse;
    }
}
