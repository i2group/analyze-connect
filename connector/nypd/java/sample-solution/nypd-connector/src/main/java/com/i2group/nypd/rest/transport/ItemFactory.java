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

package com.i2group.nypd.rest.transport;

import com.i2group.nypd.rest.externalsource.transport.SocrataResponseData;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.HashMap;

/** Used to generate entity and link objects */
public class ItemFactory {

  private static String BASE_URL;
  private static Resource SOURCE_REFERENCE_IMAGE;

  public ItemFactory(String baseUrl, Resource image) {
    BASE_URL = baseUrl;
    SOURCE_REFERENCE_IMAGE = image;
  }

  /**
   * Creates complaint from a single record of data.
   *
   * @param entry The single record from the dataset.
   * @return The created complaint object.
   */
  public EntityData createComplaint(SocrataResponseData entry) {
    final HashMap<String, Object> properties = new HashMap<>();
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

    final EntityData complaint = new EntityData();
    complaint.id = "COMP" + entry.complaintNum;
    complaint.typeId = "ET1";
    complaint.version = 1L;
    complaint.properties = properties;
    complaint.sourceReference = generateSourceReference(entry.complaintNum);

    return complaint;
  }

  /**
   * Creates location from a single record of data.
   *
   * @param entry The single record from the dataset.
   * @return The created location object.
   */
  public EntityData createLocation(SocrataResponseData entry) {
    final HashMap<String, Object> properties = new HashMap<>();
    properties.put("PT15", entry.precinctCode);
    properties.put("PT16", entry.boroName);
    properties.put("PT18", GeospatialPoint.withCoordinates(entry.latitude, entry.longitude));

    final EntityData location = new EntityData();
    location.id = "LOC" + entry.precinctCode + entry.boroName;
    location.typeId = "ET2";
    location.version = 1L;
    location.properties = properties;
    location.sourceReference = generateSourceReference(entry.complaintNum);

    return location;
  }

  /**
   * Creates suspect from a single record of data.
   *
   * @param entry The single record from the dataset.
   * @return The created suspect object.
   */
  public EntityData createSuspect(SocrataResponseData entry) {
    final HashMap<String, Object> properties = new HashMap<>();
    properties.put("PT26", entry.suspAge);
    properties.put("PT27", entry.suspRace);
    properties.put("PT28", entry.suspSex);

    final EntityData suspect = new EntityData();
    suspect.id = "SUSP" + entry.complaintNum;
    suspect.typeId = "ET3";
    suspect.version = 1L;
    suspect.properties = properties;
    suspect.sourceReference = generateSourceReference(entry.complaintNum);

    return suspect;
  }

  /**
   * Creates victim from a single record of data.
   *
   * @param entry The single record from the dataset.
   * @return The created victim object.
   */
  public EntityData createVictim(SocrataResponseData entry) {
    final HashMap<String, Object> properties = new HashMap<>();
    properties.put("PT26", entry.vicAge);
    properties.put("PT27", entry.vicRace);
    properties.put("PT28", entry.vicSex);

    final EntityData victim = new EntityData();
    victim.id = "VIC" + entry.complaintNum;
    victim.typeId = "ET3";
    victim.version = 1L;
    victim.properties = properties;
    victim.sourceReference = generateSourceReference(entry.complaintNum);

    return victim;
  }

  /**
   * Creates a location link from a single record of data.
   *
   * @param entry The single record from the dataset.
   * @return The created link object.
   */
  public LinkData createLocationLink(
      SocrataResponseData entry, EntityData complaint, EntityData location) {
    final LinkData locationLink = new LinkData();
    locationLink.id = "LINKLOC" + entry.complaintNum;
    locationLink.typeId = "LT1";
    locationLink.fromEndId = complaint.id;
    locationLink.toEndId = location.id;
    locationLink.linkDirection = "WITH";
    locationLink.sourceReference = generateSourceReference(entry.complaintNum);

    return locationLink;
  }

  /**
   * Creates a suspect link from a single record of data.
   *
   * @param entry The single record from the dataset.
   * @return The created link object.
   */
  public LinkData createSuspectLink(
      SocrataResponseData entry, EntityData complaint, EntityData suspect) {
    final LinkData suspectLink = new LinkData();
    suspectLink.id = "LINKSUSP" + entry.complaintNum;
    suspectLink.typeId = "LT2";
    suspectLink.fromEndId = suspect.id;
    suspectLink.toEndId = complaint.id;
    suspectLink.linkDirection = "WITH";
    suspectLink.sourceReference = generateSourceReference(entry.complaintNum);

    return suspectLink;
  }

  /**
   * Creates a victim link from a single record of data.
   *
   * @param entry The single record from the dataset.
   * @return The created link object.
   */
  public LinkData createVictimLink(
      SocrataResponseData entry, EntityData complaint, EntityData victim) {
    final LinkData victimLink = new LinkData();
    victimLink.id = "LINKVIC" + entry.complaintNum;
    victimLink.typeId = "LT3";
    victimLink.fromEndId = victim.id;
    victimLink.toEndId = complaint.id;
    victimLink.linkDirection = "WITH";
    victimLink.sourceReference = generateSourceReference(entry.complaintNum);

    return victimLink;
  }

  /**
   * Generate a valid source reference, querying a link to an item of data using the unique
   * complaint number.
   *
   * @param complaintNum The complaint number of the record associated with the entity.
   * @return The SourceReference object containing details of the source.
   */
  private SourceReference generateSourceReference(String complaintNum) {
    final SourceInfo source = new SourceInfo();
    source.name = "NYPD Complaint Dataset";
    source.type = "Open source data";
    source.description =
        "A source reference to the corresponding record from the NYPD Complaint Dataset.";
    source.location = ItemFactory.BASE_URL + "?$where=cmplnt_num=" + complaintNum;

    try {
      source.image = SOURCE_REFERENCE_IMAGE.getFile().getAbsolutePath();
    } catch (IOException e) {
      source.image = null;
    }

    final SourceReference reference = new SourceReference();
    reference.id = "";
    reference.source = source;

    return reference;
  }
}
