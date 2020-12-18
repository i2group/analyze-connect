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

package com.i2group.async.rest.transport;

import java.util.HashMap;

import com.i2group.async.rest.transport.ResponseData.Person;

/** Used to generate entity and link objects */
public class ItemFactory {

  /**
   * Creates a person from a single record of data.
   *
   * @param person The single record from the dataset.
   * @return The created person object.
   */
  public static EntityData createPerson(Person person) {
    HashMap<String, Object> properties = new HashMap<String, Object>();
    properties.put("PER1", person.forename);
    properties.put("PER2", person.surname);
    properties.put("PER3", person.dob);
    properties.put("PER4", person.ssn);
    properties.put("PER5", person.issuedDateAndTime);

    EntityData entity = new EntityData();
    entity.id = "PER" + person.id;
    entity.typeId = "Person";
    entity.typeLocation = "CONNECTOR";
    entity.version = 1L;
    entity.properties = properties;
    entity.sourceReference = generateSourceReference(entity.id, person.id);

    return entity;
  }

  /**
   * Creates a person link from a single record of data.
   *
   * @param entry The single record from the dataset.
   * @param person The person record from the dataset.
   * @param friend The friend record identified from the person record in the dataset.
   * @return The created link object.
   */
  public static LinkData createPersonLink(Person entry, EntityData person, EntityData friend) {
    LinkData personLink = new LinkData();
    personLink.id = "LINK-PER" + person.id + "-FRIEND" + friend.id;
    personLink.typeId = "FriendLink";
    personLink.fromEndId = person.id;
    personLink.toEndId = friend.id;
    personLink.linkDirection = LinkData.Direction.BOTH;
    personLink.sourceReference = generateSourceReference(personLink.id, entry.id);

    return personLink;
  }

  /**
   * Generate a valid source reference, querying a link to an item of data using the id.
   *
   * @param id The ID of the entity or link object.
   * @param entryId The id of the record associated with the entity.
   * @return The SourceReference object containing details of the source.
   */
  private static SourceReference generateSourceReference(Object id, String entryId) {
    SourceInfo source = new SourceInfo();
    source.name = "Example source name";
    source.type = "Example source type";
    source.description = 
        "An example source reference from a connected data source";
    source.location =  "http://localhost:9085/person/" + entryId;

    SourceReference reference = new SourceReference();
    reference.id = "SR_" + id;
    reference.source = source;

    return reference;
  }
}
