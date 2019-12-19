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
import java.util.Map;

import com.example.demo.rest.externalsource.SocrataClient;
import com.example.demo.rest.externalsource.transport.SocrataResponse;
import com.example.demo.rest.transport.ConnectorResponse;
import com.example.demo.rest.transport.EntityData;
import com.example.demo.rest.transport.LinkData;
import com.example.demo.rest.transport.request.RequestCondition;
import com.example.demo.rest.transport.request.seeded.DaodSeeds;
import com.example.demo.rest.transport.request.seeded.DaodSeedEntityData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Used to query the NYPD complaint dataset, turning the raw data into entities and links
 */
@Service
public class ExternalConnectorDataService {
    private final SocrataClient socrataClient;

    /**
     * Constructor used to initialise the Socrata client used to retrieve the complaint data
     * @param baseUrl URL of the NYPD complaint dataset
     * @param apiToken API Token used to access the NYPD complaint dataset
     */
    @Autowired
    public ExternalConnectorDataService(
            @Value("${socrata.url}") String baseUrl,
            @Value("${socrata.api.token}") String apiToken) {
        socrataClient = new SocrataClient(baseUrl, apiToken);
    }

    /**
     * Used to query the NYPD complaint dataset, turning the raw data into entities and links by retrieving all data
     * @return A ConnectorResponse containing the entities and links
     */
    public ConnectorResponse getAll() {
        final List<EntityData> entities = new ArrayList<>();
        final List<LinkData> links = new ArrayList<>();
    
        final Map<String, Object> params = new HashMap<>();
        params.put("limitValue", 100); 
        String url = "?$limit={limitValue}";

        final SocrataResponse response = socrataClient.get(url, SocrataResponse.class, params);
        response.stream().forEach(entry -> {
            EntityData complaintEntityData = new EntityData();
            complaintEntityData.id = "complaint" + entry.complaintNum;
            complaintEntityData.typeId = "ET1";
            complaintEntityData.version = 1L;
            complaintEntityData.properties = new HashMap<>();
            complaintEntityData.properties.put("PT1", entry.complaintNum);
            complaintEntityData.properties.put("PT2", entry.complaintStartDate);
            complaintEntityData.properties.put("PT3", entry.complaintEndDate);
            complaintEntityData.properties.put("PT4", entry.complaintStartTime);
            complaintEntityData.properties.put("PT5", entry.complaintEndTime);
            complaintEntityData.properties.put("PT6", entry.crimeStatus);
            complaintEntityData.properties.put("PT7", entry.jurisdictionCode);
            complaintEntityData.properties.put("PT8", entry.jurisdictionDesc);
            complaintEntityData.properties.put("PT9", entry.offenceClass);
            complaintEntityData.properties.put("PT10", entry.offenceLevel);
            complaintEntityData.properties.put("PT11", entry.offenceDesc);
            complaintEntityData.properties.put("PT12", entry.internalClassCode != null ? (int) Float.parseFloat(entry.internalClassCode) : null);
            complaintEntityData.properties.put("PT13", entry.classDesc);
            complaintEntityData.properties.put("PT14", entry.dateReported);
            complaintEntityData.properties.put("PT29", entry.occurrenceLocation);
            complaintEntityData.properties.put("PT18", entry.latitude);
            complaintEntityData.properties.put("PT30", entry.longitude);

            EntityData locationEntityData = new EntityData();
            locationEntityData.id = "location" + entry.complaintNum;
            locationEntityData.typeId = "ET2";
            locationEntityData.version = 1L;
            locationEntityData.properties = new HashMap<>();
            locationEntityData.properties.put("PT15", entry.precinctCode);
            locationEntityData.properties.put("PT16", entry.boroName);
            locationEntityData.properties.put("PT17", entry.houseDev);
            locationEntityData.properties.put("PT19", entry.parkName);
            locationEntityData.properties.put("PT20", entry.patrolBoro);
            locationEntityData.properties.put("PT21", entry.premisesDesc);
            locationEntityData.properties.put("PT22", entry.stationName);
            locationEntityData.properties.put("PT23", entry.transitDistrict != null ? (int) Float.parseFloat(entry.transitDistrict) : null);

            EntityData suspectPersonEntityData = new EntityData();
            suspectPersonEntityData.id = "suspect" + entry.complaintNum;
            suspectPersonEntityData.typeId = "ET3";
            suspectPersonEntityData.version = 1L;
            suspectPersonEntityData.properties = new HashMap<>();
            suspectPersonEntityData.properties.put("PT26", entry.suspAge);
            suspectPersonEntityData.properties.put("PT27", entry.suspRace);
            suspectPersonEntityData.properties.put("PT28", entry.suspSex);

            EntityData victimPersonEntityData = new EntityData();
            victimPersonEntityData.id = "victim" + entry.complaintNum;
            victimPersonEntityData.typeId = "ET3";
            victimPersonEntityData.version = 1L;
            victimPersonEntityData.properties = new HashMap<>();
            victimPersonEntityData.properties.put("PT26", entry.vicAge);
            victimPersonEntityData.properties.put("PT27", entry.vicRace);
            victimPersonEntityData.properties.put("PT28", entry.vicSex);

            LinkData complaintLinkData = new LinkData();
            complaintLinkData.id = String.format("complaint:location:%s:%s", complaintEntityData.id, locationEntityData.id);
            complaintLinkData.typeId = "LT1";
            complaintLinkData.fromEndId = complaintEntityData.id;
            complaintLinkData.toEndId = locationEntityData.id;
            complaintLinkData.linkDirection = "WITH";
        
            LinkData suspectLinkData = new LinkData();
            suspectLinkData.id = String.format("complaint:suspect:%s:%s", complaintEntityData.id, suspectPersonEntityData.id);
            suspectLinkData.typeId = "LT2";
            suspectLinkData.fromEndId = suspectPersonEntityData.id;
            suspectLinkData.toEndId = complaintEntityData.id;
            suspectLinkData.linkDirection = "WITH";

            LinkData victimLinkData = new LinkData();
            victimLinkData.id = String.format("complaint:victim:%s:%s", complaintEntityData.id, victimPersonEntityData.id);
            victimLinkData.typeId = "LT3";
            victimLinkData.fromEndId = victimPersonEntityData.id;
            victimLinkData.toEndId = complaintEntityData.id;
            victimLinkData.linkDirection = "WITH";

            entities.add(complaintEntityData);
            entities.add(locationEntityData);
            entities.add(suspectPersonEntityData);
            entities.add(victimPersonEntityData);

            links.add(complaintLinkData);
            links.add(suspectLinkData);
            links.add(victimLinkData);

        });

        final ConnectorResponse connectorResponse = new ConnectorResponse();
        connectorResponse.entities = entities;
        connectorResponse.links = links;
        return connectorResponse;
    }

    /**
     * Used to query the NYPD complaint dataset conditionally, turning the raw data into entities and links
     * @param conditions Conditions provided by the user to query the NYPD complaint dataset
     * @return A response containing the entities and links
     */
    public ConnectorResponse getConditional(List <RequestCondition> conditions) {
        final List<EntityData> entities = new ArrayList<>();
        final List<LinkData> links = new ArrayList<>();

        final Map<String, Object> params = new HashMap<>();
        params.put("limitValue", 1);
        String url = "?$limit={limitValue}";
        if (conditions != null) {
            params.put("complaintNum", conditions.get(0).value);
            url += "&$where=cmplnt_num = '{complaintNum}'";
        }

        final SocrataResponse response = socrataClient.get(url, SocrataResponse.class, params);
        response.stream().forEach(entry -> {
            EntityData complaintEntityData = new EntityData();
            complaintEntityData.id = "complaint" + entry.complaintNum;
            complaintEntityData.typeId = "ET1";
            complaintEntityData.version = 1L;
            complaintEntityData.properties = new HashMap<>();
            complaintEntityData.properties.put("PT1", entry.complaintNum);
            complaintEntityData.properties.put("PT2", entry.complaintStartDate);
            complaintEntityData.properties.put("PT3", entry.complaintEndDate);
            complaintEntityData.properties.put("PT4", entry.complaintStartTime);
            complaintEntityData.properties.put("PT5", entry.complaintEndTime);
            complaintEntityData.properties.put("PT6", entry.crimeStatus);
            complaintEntityData.properties.put("PT7", entry.jurisdictionCode);
            complaintEntityData.properties.put("PT8", entry.jurisdictionDesc);
            complaintEntityData.properties.put("PT9", entry.offenceClass);
            complaintEntityData.properties.put("PT10", entry.offenceLevel);
            complaintEntityData.properties.put("PT11", entry.offenceDesc);
            complaintEntityData.properties.put("PT12", entry.internalClassCode != null ? (int) Float.parseFloat(entry.internalClassCode) : null);
            complaintEntityData.properties.put("PT13", entry.classDesc);
            complaintEntityData.properties.put("PT14", entry.dateReported);
            complaintEntityData.properties.put("PT29", entry.occurrenceLocation);
            complaintEntityData.properties.put("PT18", entry.latitude);
            complaintEntityData.properties.put("PT30", entry.longitude);
            entities.add(complaintEntityData);
        });

        final ConnectorResponse connectorResponse = new ConnectorResponse();
        connectorResponse.entities = entities;
        connectorResponse.links = links;
        return connectorResponse;
    }

    /**
     * Used to query the NYPD complaint dataset to find similar entities, turning the raw data into entities and links
     * @param seeds DaodSeeds provided by the user
     * @return A response containing the entities and links
     */
    public ConnectorResponse getFindLikeThis(DaodSeeds seeds) {
        final List<EntityData> entities = new ArrayList<>();
        final List<LinkData> links = new ArrayList<>();

        List<DaodSeedEntityData> entitiesDaod = seeds.entities;
        DaodSeedEntityData seed = entitiesDaod.get(0);
        seed.properties.get("PT1").toString();

        final Map<String, Object> params = new HashMap<>();
        params.put("limitValue", 1); 
        params.put("complaintNum", seed.properties.get("PT1").toString());
        String url = "?$limit={limitValue}&cmplnt_num={complaintNum}";

        final SocrataResponse response = socrataClient.get(url, SocrataResponse.class, params);
        response.stream().forEach(entry -> {
            System.out.println("item: " + entry);
            EntityData complaintEntityData = new EntityData();
            complaintEntityData.id = "complaint" + entry.complaintNum;
            complaintEntityData.typeId = "ET1";
            complaintEntityData.version = 1L;
            complaintEntityData.properties = new HashMap<>();
            complaintEntityData.properties.put("PT1", entry.complaintNum);
            complaintEntityData.properties.put("PT2", entry.complaintStartDate);
            complaintEntityData.properties.put("PT3", entry.complaintEndDate);
            complaintEntityData.properties.put("PT4", entry.complaintStartTime);
            complaintEntityData.properties.put("PT5", entry.complaintEndTime);
            complaintEntityData.properties.put("PT6", entry.crimeStatus);
            complaintEntityData.properties.put("PT7", entry.jurisdictionCode);
            complaintEntityData.properties.put("PT8", entry.jurisdictionDesc);
            complaintEntityData.properties.put("PT9", entry.offenceClass);
            complaintEntityData.properties.put("PT10", entry.offenceLevel);
            complaintEntityData.properties.put("PT11", entry.offenceDesc);
            complaintEntityData.properties.put("PT12", entry.internalClassCode != null ? (int) Float.parseFloat(entry.internalClassCode) : null);
            complaintEntityData.properties.put("PT13", entry.classDesc);
            complaintEntityData.properties.put("PT14", entry.dateReported);
            complaintEntityData.properties.put("PT29", entry.occurrenceLocation);
            complaintEntityData.properties.put("PT18", entry.latitude);
            complaintEntityData.properties.put("PT30", entry.longitude);
            entities.add(complaintEntityData);
        });

        final ConnectorResponse connectorResponse = new ConnectorResponse();
        connectorResponse.entities = entities;
        connectorResponse.links = links;
        return connectorResponse;
    }

    /**
     * Used to query the NYPD complaint dataset by expanding a certain entity, turning the raw data into entities and links
     * @param seeds DaodSeeds provided by the user
     * @return A response containing the entities and links
     */
    public ConnectorResponse getExpand(DaodSeeds seeds) {
        final List<EntityData> entities = new ArrayList<>();
        final List<LinkData> links = new ArrayList<>();

        List<DaodSeedEntityData> entitiesDaod = seeds.entities;
        DaodSeedEntityData seed = entitiesDaod.get(0);

        final Map<String, Object> params = new HashMap<>();
        String query = "";
        String field = "";

        if (seed.typeId.equals("ET1")) {
            query = seed.properties.get("PT1").toString();
            field = "cmplnt_num";
        }
        else if (seed.typeId.equals("ET2")) {
            query = seed.properties.get("PT16").toString();
            field = "boro_nm";
        }

        params.put("field", field);
        params.put("limitValue", 10);
        params.put("whereClause", query);
        String url = "?$limit={limitValue}&{field}={whereClause}";

        final SocrataResponse response = socrataClient.get(url, SocrataResponse.class, params);
        response.stream().forEach(entry -> {
            EntityData complaintEntityData = new EntityData();
            complaintEntityData.id = "complaint" + entry.complaintNum;
            complaintEntityData.typeId = "ET1";
            complaintEntityData.version = 1L;
            complaintEntityData.properties = new HashMap<>();
            complaintEntityData.properties.put("PT1", entry.complaintNum);
            complaintEntityData.properties.put("PT2", entry.complaintStartDate);
            complaintEntityData.properties.put("PT3", entry.complaintEndDate);
            complaintEntityData.properties.put("PT4", entry.complaintStartTime);
            complaintEntityData.properties.put("PT5", entry.complaintEndTime);
            complaintEntityData.properties.put("PT6", entry.crimeStatus);
            complaintEntityData.properties.put("PT7", entry.jurisdictionCode);
            complaintEntityData.properties.put("PT8", entry.jurisdictionDesc);
            complaintEntityData.properties.put("PT9", entry.offenceClass);
            complaintEntityData.properties.put("PT10", entry.offenceLevel);
            complaintEntityData.properties.put("PT11", entry.offenceDesc);
            complaintEntityData.properties.put("PT12", entry.internalClassCode != null ? (int) Float.parseFloat(entry.internalClassCode) : null);
            complaintEntityData.properties.put("PT13", entry.classDesc);
            complaintEntityData.properties.put("PT14", entry.dateReported);
            complaintEntityData.properties.put("PT29", entry.occurrenceLocation);
            complaintEntityData.properties.put("PT18", entry.latitude);
            complaintEntityData.properties.put("PT30", entry.longitude);
            entities.add(complaintEntityData);

            EntityData locationEntityData = new EntityData();
            locationEntityData.id = "location" + entry.complaintNum;
            locationEntityData.typeId = "ET2";
            locationEntityData.version = 1L;
            locationEntityData.properties = new HashMap<>();
            locationEntityData.properties.put("PT15", entry.precinctCode);
            locationEntityData.properties.put("PT16", entry.boroName);
            locationEntityData.properties.put("PT17", entry.houseDev);
            locationEntityData.properties.put("PT19", entry.parkName);
            locationEntityData.properties.put("PT20", entry.patrolBoro);
            locationEntityData.properties.put("PT21", entry.premisesDesc);
            locationEntityData.properties.put("PT22", entry.stationName);
            locationEntityData.properties.put("PT23", entry.transitDistrict != null ? (int) Float.parseFloat(entry.transitDistrict) : null);
            entities.add(locationEntityData);

            LinkData complaintLinkData = new LinkData();
            complaintLinkData.id = String.format("complaint:location:%s:%s", complaintEntityData.id, locationEntityData.id);
            complaintLinkData.typeId = "LT1";
            complaintLinkData.toEndId = locationEntityData.id;
            complaintLinkData.fromEndId = complaintEntityData.id;
            complaintLinkData.linkDirection = "WITH";
            links.add(complaintLinkData);

        });

        final ConnectorResponse connectorResponse = new ConnectorResponse();
        connectorResponse.entities = entities;
        connectorResponse.links = links;
        return connectorResponse;
    }

    /**
     * Used to query the NYPD complaint dataset by expanding a certain entity based on conditions, turning the raw data into entities and links
     * @param conditions Conditions provided by the user to query the NYPD complaint dataset
     * @param seeds DaodSeeds provided by the user
     * @return A response containing the entities and links
     */
    public ConnectorResponse getExpandWithConditions(List <RequestCondition> conditions, DaodSeeds seeds) {
        final List<EntityData> entities = new ArrayList<>();
        final List<LinkData> links = new ArrayList<>();

        List<DaodSeedEntityData> entitiesDaod = seeds.entities;
        DaodSeedEntityData seed = entitiesDaod.get(0);

        final Map<String, Object> params = new HashMap<>();
        String query = "";
        String field = "";

        if (seed.typeId.equals("ET1")) {
            query = seed.properties.get("PT1").toString();
            field = "cmplnt_num";
        }
        else if (seed.typeId.equals("ET2")) {
            query = seed.properties.get("PT16").toString();
            field = "boro_nm";
        }

        params.put("field", field);
        params.put("limitValue", 10); 
        params.put("whereClause", query);
        String url = "?$limit={limitValue}&{field}={whereClause}";
        if (conditions != null) {
            params.put("boroName", conditions.get(0).value);
            url += "&$where=boro_nm = '{boroName}'";
        }

        final SocrataResponse response = socrataClient.get(url, SocrataResponse.class, params);
        response.stream().forEach(entry -> {
            EntityData complaintEntityData = new EntityData();
            complaintEntityData.id = "complaint" + entry.complaintNum;
            complaintEntityData.typeId = "ET1";
            complaintEntityData.version = 1L;
            complaintEntityData.properties = new HashMap<>();
            complaintEntityData.properties.put("PT1", entry.complaintNum);
            complaintEntityData.properties.put("PT2", entry.complaintStartDate);
            complaintEntityData.properties.put("PT3", entry.complaintEndDate);
            complaintEntityData.properties.put("PT4", entry.complaintStartTime);
            complaintEntityData.properties.put("PT5", entry.complaintEndTime);
            complaintEntityData.properties.put("PT6", entry.crimeStatus);
            complaintEntityData.properties.put("PT7", entry.jurisdictionCode);
            complaintEntityData.properties.put("PT8", entry.jurisdictionDesc);
            complaintEntityData.properties.put("PT9", entry.offenceClass);
            complaintEntityData.properties.put("PT10", entry.offenceLevel);
            complaintEntityData.properties.put("PT11", entry.offenceDesc);
            complaintEntityData.properties.put("PT12", entry.internalClassCode != null ? (int) Float.parseFloat(entry.internalClassCode) : null);
            complaintEntityData.properties.put("PT13", entry.classDesc);
            complaintEntityData.properties.put("PT14", entry.dateReported);
            complaintEntityData.properties.put("PT29", entry.occurrenceLocation);
            complaintEntityData.properties.put("PT18", entry.latitude);
            complaintEntityData.properties.put("PT30", entry.longitude);
            entities.add(complaintEntityData);

            EntityData locationEntityData = new EntityData();
            locationEntityData.id = "location" + entry.complaintNum;
            locationEntityData.typeId = "ET2";
            locationEntityData.version = 1L;
            locationEntityData.properties = new HashMap<>();
            locationEntityData.properties.put("PT15", entry.precinctCode);
            locationEntityData.properties.put("PT16", entry.boroName);
            locationEntityData.properties.put("PT17", entry.houseDev);
            locationEntityData.properties.put("PT19", entry.parkName);
            locationEntityData.properties.put("PT20", entry.patrolBoro);
            locationEntityData.properties.put("PT21", entry.premisesDesc);
            locationEntityData.properties.put("PT22", entry.stationName);
            locationEntityData.properties.put("PT23", entry.transitDistrict != null ? (int) Float.parseFloat(entry.transitDistrict) : null);
            entities.add(locationEntityData);

            LinkData complaintLinkData = new LinkData();
            complaintLinkData.id = String.format("complaint:location:%s:%s", complaintEntityData.id, locationEntityData.id);
            complaintLinkData.typeId = "LT1";
            complaintLinkData.toEndId = locationEntityData.id;
            complaintLinkData.fromEndId = complaintEntityData.id;
            complaintLinkData.linkDirection = "WITH";

        });

        final ConnectorResponse connectorResponse = new ConnectorResponse();
        connectorResponse.entities = entities;
        connectorResponse.links = links;
        return connectorResponse;
    }

}
