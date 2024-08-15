/*
 * MIT License
 *
 * © N.Harris Computer Corporation (2023)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.i2group.auth.rest.transport;

import com.i2group.connector.spi.rest.transport.I2ConnectEntityData;
import com.i2group.connector.spi.rest.transport.I2ConnectLinkData;
import com.i2group.connector.spi.rest.transport.LinkDirection;
import com.i2group.connector.spi.rest.transport.SchemaTypeLocation;

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
  public I2ConnectEntityData createPerson(Person person) {
    final HashMap<String, Object> properties = new HashMap<>();
    properties.put("PER1", person.forename);
    properties.put("PER2", person.surname);
    properties.put("PER3", person.dob);
    properties.put("PER4", person.ssn);
    properties.put("PER5", person.issuedDateAndTime);

    final I2ConnectEntityData entity = new I2ConnectEntityData();
    entity.id = "PER" + person.id;
    entity.typeId = "Person";
    entity.typeLocation = SchemaTypeLocation.CONNECTOR;
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
  public I2ConnectLinkData createPersonLink(I2ConnectEntityData person, I2ConnectEntityData friend) {
    final I2ConnectLinkData link = new I2ConnectLinkData();
    link.id = "LINK-PER" + person.id + "-FRIEND" + friend.id;
    link.typeId = "FriendLink";
    link.fromEndId = person.id;
    link.toEndId = friend.id;
    link.linkDirection = LinkDirection.BOTH;
    return link;
  }
}
