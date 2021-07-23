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

package com.i2group.auth.rest.transport;

import java.util.HashMap;

/**
 * Used to generate entity and link objects
 */
public class ItemFactory {

  public ItemFactory() {
  }

  /**
   * Creates a person from a single record of data.
   *
   * @param person The single record from the dataset.
   * @return The created person object.
   */
  public Entity createPerson(Person person) {
    final HashMap<String, Object> properties = new HashMap<>();
    properties.put("PER1", person.forename);
    properties.put("PER2", person.surname);
    properties.put("PER3", person.dob);
    properties.put("PER4", person.ssn);
    properties.put("PER5", person.issuedDateAndTime);

    final Entity entity = new Entity();
    entity.id = "PER" + person.id;
    entity.typeId = "Person";
    entity.typeLocation = "CONNECTOR";
    entity.properties = properties;

    return entity;
  }

  /**
   * Creates a person link from a single record of data.
   *
   * @param person The person record from the dataset.
   * @param friend The friend record identified from the person record in the dataset.
   * @return The created link object.
   */
  public Link createPersonLink(Entity person, Entity friend) {
    final Link link = new Link();
    link.id = "LINK-PER" + person.id + "-FRIEND" + friend.id;
    link.typeId = "FriendLink";
    link.fromEndId = person.id;
    link.toEndId = friend.id;
    link.linkDirection = Link.Direction.BOTH;
    return link;
  }
}
