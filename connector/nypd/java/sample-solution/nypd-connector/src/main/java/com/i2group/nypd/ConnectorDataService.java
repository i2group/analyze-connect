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

package com.i2group.nypd;

import com.i2group.nypd.rest.transport.ConnectorResponse;
import com.i2group.nypd.rest.transport.EntityData;
import com.i2group.nypd.rest.transport.GeospatialPoint;
import com.i2group.nypd.rest.transport.LinkData;
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
    complaintProps.put("PT1", "660160752"); // Complaint Number (String)
    complaintProps.put("PT2", "2017-05-25"); // Complaint Start Date (String)
    complaintProps.put("PT10", "Felony"); // Offence Level (String)

    Map<String, Object> locationProps = new HashMap<>();
    locationProps.put("PT15", 48); // Precinct Code Number (int)
    locationProps.put("PT16", "BRONX"); // Borough Name (String)
    locationProps.put("PT18", GeospatialPoint.withCoordinates(-73.924942, 40.8103523));

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
