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

import com.example.demo.rest.externalsource.transport.SocrataResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;

/** Used to generate entity and link objects */
public class ItemFactory {
  @Autowired
  @Value("${socrata.url}")
  private static String baseUrl;

  /**
   * Creates complaint from a single record of data.
   *
   * @param entry The single record from the dataset.
   * @return The created complaint object.
   */
  public static EntityData createComplaint(SocrataResponseData entry) {
    HashMap<String, Object> properties = new HashMap<String, Object>();
    properties.put("PT1", entry.complaintNum);
    properties.put("PT2", entry.complaintStartDate);
    properties.put("PT3", entry.complaintEndDate);
    properties.put("PT4", entry.complaintStartTime);
    properties.put("PT5", entry.complaintEndTime);
    properties.put("PT6", entry.crimeStatus);
    properties.put("PT7", entry.jurisdictionCode);
    properties.put("PT8", entry.jurisdictionDesc);
    properties.put("PT9", entry.offenceClass);
    properties.put("PT10", entry.offenceLevel);
    properties.put("PT11", entry.offenceDesc);
    properties.put("PT13", entry.classDesc);
    properties.put("PT14", entry.dateReported);
    properties.put("PT29", entry.occurrenceLocation);
    properties.put("PT18", entry.latitude);
    properties.put("PT30", entry.longitude);

    EntityData complaint = new EntityData();
    complaint.id = "COMP" + entry.complaintNum;
    complaint.typeId = "ET1";
    complaint.version = 1L;
    complaint.properties = properties;
    complaint.sourceReference = generateSourceReference(complaint.id, entry.complaintNum);

    return complaint;
  }

  /**
   * Creates location from a single record of data.
   *
   * @param entry The single record from the dataset.
   * @return The created location object.
   */
  public static EntityData createLocation(SocrataResponseData entry) {
    HashMap<String, Object> properties = new HashMap<String, Object>();
    properties.put("PT15", entry.precinctCode);
    properties.put("PT16", entry.boroName);

    EntityData location = new EntityData();
    location.id = "LOC" + entry.precinctCode + entry.boroName;
    location.typeId = "ET2";
    location.version = 1L;
    location.properties = properties;
    location.sourceReference = generateSourceReference(location.id, entry.complaintNum);

    return location;
  }

  /**
   * Creates suspect from a single record of data.
   *
   * @param entry The single record from the dataset.
   * @return The created suspect object.
   */
  public static EntityData createSuspect(SocrataResponseData entry) {
    HashMap<String, Object> properties = new HashMap<String, Object>();
    properties.put("PT26", entry.suspAge);
    properties.put("PT27", entry.suspRace);
    properties.put("PT28", entry.suspSex);

    EntityData suspect = new EntityData();
    suspect.id = "SUSP" + entry.complaintNum;
    suspect.typeId = "ET3";
    suspect.version = 1L;
    suspect.properties = properties;
    suspect.sourceReference = generateSourceReference(suspect.id, entry.complaintNum);

    return suspect;
  }

  /**
   * Creates victim from a single record of data.
   *
   * @param entry The single record from the dataset.
   * @return The created victim object.
   */
  public static EntityData createVictim(SocrataResponseData entry) {
    HashMap<String, Object> properties = new HashMap<String, Object>();
    properties.put("PT26", entry.vicAge);
    properties.put("PT27", entry.vicRace);
    properties.put("PT28", entry.vicSex);

    EntityData victim = new EntityData();
    victim.id = "VIC" + entry.complaintNum;
    victim.typeId = "ET3";
    victim.version = 1L;
    victim.properties = properties;
    victim.sourceReference = generateSourceReference(victim.id, entry.complaintNum);

    return victim;
  }

  /**
   * Creates a location link from a single record of data.
   *
   * @param entry The single record from the dataset.
   * @return The created link object.
   */
  public static LinkData createLocationLink(
      SocrataResponseData entry, EntityData complaint, EntityData location) {
    LinkData locationLink = new LinkData();
    locationLink.id = "LINKLOC" + entry.complaintNum;
    locationLink.typeId = "LT1";
    locationLink.fromEndId = complaint.id;
    locationLink.toEndId = location.id;
    locationLink.linkDirection = "WITH";
    locationLink.sourceReference = generateSourceReference(locationLink.id, entry.complaintNum);

    return locationLink;
  }

  /**
   * Creates a suspect link from a single record of data.
   *
   * @param entry The single record from the dataset.
   * @return The created link object.
   */
  public static LinkData createSuspectLink(
      SocrataResponseData entry, EntityData complaint, EntityData suspect) {
    LinkData suspectLink = new LinkData();
    suspectLink.id = "LINKSUSP" + entry.complaintNum;
    suspectLink.typeId = "LT2";
    suspectLink.fromEndId = suspect.id;
    suspectLink.toEndId = complaint.id;
    suspectLink.linkDirection = "WITH";
    suspectLink.sourceReference = generateSourceReference(suspectLink.id, entry.complaintNum);

    return suspectLink;
  }

  /**
   * Creates a victim link from a single record of data.
   *
   * @param entry The single record from the dataset.
   * @return The created link object.
   */
  public static LinkData createVictimLink(
      SocrataResponseData entry, EntityData complaint, EntityData victim) {
    LinkData victimLink = new LinkData();
    victimLink.id = "LINKVIC" + entry.complaintNum;
    victimLink.typeId = "LT3";
    victimLink.fromEndId = victim.id;
    victimLink.toEndId = complaint.id;
    victimLink.linkDirection = "WITH";
    victimLink.sourceReference = generateSourceReference(victimLink.id, entry.complaintNum);

    return victimLink;
  }

  /**
   * Generate a valid source reference, querying a link to an item of data using the unique
   * complaint number.
   *
   * @param id The ID of the entity or link object.
   * @param complaintNum The complaint number of the record associated with the entity.
   * @return The SourceReference object containing details of the source.
   */
  private static SourceReference generateSourceReference(Object id, int complaintNum) {
    SourceInfo source = new SourceInfo();
    source.name = "NYPD Complaint Dataset";
    source.type = "Open source data";
    source.description =
        "A source reference to the corresponding record from the NYPD Complaint Dataset.";
    source.location = baseUrl + "?$where=cmplnt_num=" + complaintNum;
    source.image =
        "https://github.ibm.com/ibmi2/Connect-Examples/tree/master/docs/images/nypd-dataset-webpage.png?raw=true";

    SourceReference reference = new SourceReference();
    reference.id = "SR_" + id;
    reference.source = source;

    return reference;
  }
}
