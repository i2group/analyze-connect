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

import com.i2group.connector.spi.rest.transport.GeoJSONPoint;
import com.i2group.connector.spi.rest.transport.I2ConnectData;
import com.i2group.connector.spi.rest.transport.I2ConnectEntityData;
import com.i2group.connector.spi.rest.transport.I2ConnectLinkData;
import com.i2group.connector.spi.rest.transport.LinkDirection;
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
  public I2ConnectData retrieveTestData() {
    final List<I2ConnectEntityData> entities = new ArrayList<>();
    final List<I2ConnectLinkData> links = new ArrayList<>();

    Map<String, Object> complaintProps = new HashMap<>();
    complaintProps.put("PT1", "660160752"); // Complaint Number (String)
    complaintProps.put("PT2", "2017-05-25"); // Complaint Start Date (String)
    complaintProps.put("PT10", "Felony"); // Offence Level (String)

    Map<String, Object> locationProps = new HashMap<>();
    locationProps.put("PT15", 48); // Precinct Code Number (int)
    locationProps.put("PT16", "BRONX"); // Borough Name (String)
    final GeoJSONPoint geoJSONPoint = new GeoJSONPoint();
    geoJSONPoint.type = GeoJSONPoint.TypeEnum.POINT;
    geoJSONPoint.coordinates = List.of(-73.924942, 40.8103523);
    locationProps.put("PT18", geoJSONPoint);

    I2ConnectEntityData complaint = new I2ConnectEntityData();
    complaint.id = "COMP001";
    complaint.typeId = "ET1";
    complaint.properties = complaintProps;

    I2ConnectEntityData location = new I2ConnectEntityData();
    location.id = "LOC001";
    location.typeId = "ET2";
    location.properties = locationProps;

    I2ConnectLinkData link = new I2ConnectLinkData();
    link.id = "LOCLINK001";
    link.typeId = "LT1";
    link.fromEndId = complaint.id;
    link.toEndId = location.id;
    link.linkDirection = LinkDirection.WITH;

    entities.add(complaint);
    entities.add(location);
    links.add(link);

    final I2ConnectData connectorResponse = new I2ConnectData();
    connectorResponse.entities = entities;
    connectorResponse.links = links;
    return connectorResponse;
  }
}
