/*
 * MIT License
 *
 * © N.Harris Computer Corporation (2022)
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
    final SourceReferenceInfo source = new SourceReferenceInfo();
    source.name = "Example source name";
    source.type = "Example source type";
    source.description = "An example source reference from a connected data source";

    try {
      source.location = PEOPLE_RESOURCE.getFile().getAbsolutePath();
    } catch (IOException e) {
      source.location = null;
    }

    final SourceReference reference = new SourceReference();
    reference.source = source;

    return reference;
  }
}
