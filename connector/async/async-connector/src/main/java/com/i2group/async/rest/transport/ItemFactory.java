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

package com.i2group.async.rest.transport;

import java.io.IOException;
import java.util.HashMap;

import com.i2group.async.rest.transport.ResponseData.Person;
import org.springframework.core.io.Resource;

/** Used to generate entity and link objects */
public class ItemFactory {

  private static Resource PEOPLE_RESOURCE;

  public ItemFactory(Resource resource) {
    PEOPLE_RESOURCE = resource;
  }

  /**
   * Creates a person from a single record of data.
   *
   * @param person The single record from the dataset.
   * @return The created person object.
   */
  public EntityData createPerson(Person person) {
    final HashMap<String, Object> properties = new HashMap<>();
    properties.put("PER1", person.forename);
    properties.put("PER2", person.surname);
    properties.put("PER3", person.dob);
    properties.put("PER4", person.ssn);
    properties.put("PER5", person.issuedDateAndTime);

    final EntityData entity = new EntityData();
    entity.id = "PER" + person.id;
    entity.typeId = "Person";
    entity.typeLocation = "CONNECTOR";
    entity.version = 1L;
    entity.properties = properties;
    entity.sourceReference = generateSourceReference();

    return entity;
  }

  /**
   * Creates a person link from a single record of data.
   *
   * @param person The person record from the dataset.
   * @param friend The friend record identified from the person record in the dataset.
   * @return The created link object.
   */
  public LinkData createPersonLink(EntityData person, EntityData friend) {
    final LinkData personLink = new LinkData();
    personLink.id = "LINK-PER" + person.id + "-FRIEND" + friend.id;
    personLink.typeId = "FriendLink";
    personLink.fromEndId = person.id;
    personLink.toEndId = friend.id;
    personLink.linkDirection = LinkData.Direction.BOTH;
    personLink.sourceReference = generateSourceReference();

    return personLink;
  }

  /**
   * Generate a valid source reference, querying a link to an item of data using the id.
   *
   * @return The SourceReference object containing details of the source.
   */
  private SourceReference generateSourceReference() {
    final SourceInfo source = new SourceInfo();
    source.name = "Example source name";
    source.type = "Example source type";
    source.description = "An example source reference from a connected data source";

    try {
      source.location = PEOPLE_RESOURCE.getFile().getAbsolutePath();
    } catch (IOException e) {
      source.location = null;
    }

    final SourceReference reference = new SourceReference();
    reference.id = "";
    reference.source = source;

    return reference;
  }
}
