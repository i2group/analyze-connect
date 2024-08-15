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

package com.i2group.nypd.rest.transport;

import com.i2group.connector.spi.rest.transport.GeoJSONPoint;
import com.i2group.connector.spi.rest.transport.I2ConnectEntityData;
import com.i2group.connector.spi.rest.transport.I2ConnectLinkData;
import com.i2group.connector.spi.rest.transport.LinkDirection;
import com.i2group.connector.spi.rest.transport.SourceReference;
import com.i2group.connector.spi.rest.transport.SourceReferenceInfo;
import com.i2group.nypd.rest.externalsource.transport.SocrataResponseData;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

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
  public I2ConnectEntityData createComplaint(SocrataResponseData entry) {
    final HashMap<String, Object> properties = new HashMap<>();
    properties.put("PT1", entry.complaintNum);
    properties.put("PT2", entry.complaintStartDate);
    properties.put("PT3", entry.complaintEndDate);
    properties.put("PT4", parseStringToTime(entry.complaintStartTime));
    properties.put("PT5", parseStringToTime(entry.complaintEndTime));
    properties.put("PT6", entry.crimeStatus);
    properties.put("PT7", entry.jurisdictionCode);
    properties.put("PT8", entry.jurisdictionDesc);
    properties.put("PT9", entry.offenceClass);
    properties.put("PT10", entry.offenceLevel);
    properties.put("PT11", entry.offenceDesc);
    properties.put("PT13", entry.classDesc);
    properties.put("PT14", entry.dateReported);
    properties.put("PT29", entry.occurrenceLocation);

    final I2ConnectEntityData complaint = new I2ConnectEntityData();
    complaint.id = "COMP" + entry.complaintNum;
    complaint.typeId = "ET1";
    complaint.version = 1L;
    complaint.properties = properties;
    complaint.sourceReference = generateSourceReference(entry.complaintNum);

    return complaint;
  }

  private LocalTime parseStringToTime(String time) {
    if (time.equals("(null)")) {
      return null;
    }
    return LocalTime.parse(time);
  }

  /**
   * Creates location from a single record of data.
   *
   * @param entry The single record from the dataset.
   * @return The created location object.
   */
  public I2ConnectEntityData createLocation(SocrataResponseData entry) {
    final HashMap<String, Object> properties = new HashMap<>();
    properties.put("PT15", entry.precinctCode);
    properties.put("PT16", entry.boroName);
    final GeoJSONPoint geoJSONPoint = new GeoJSONPoint();
    geoJSONPoint.type = GeoJSONPoint.TypeEnum.POINT;
    geoJSONPoint.coordinates = List.of(entry.longitude, entry.latitude);
    properties.put("PT18", geoJSONPoint);

    final I2ConnectEntityData location = new I2ConnectEntityData();
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
  public I2ConnectEntityData createSuspect(SocrataResponseData entry) {
    final HashMap<String, Object> properties = new HashMap<>();
    properties.put("PT26", entry.suspAge);
    properties.put("PT27", entry.suspRace);
    properties.put("PT28", entry.suspSex);

    final I2ConnectEntityData suspect = new I2ConnectEntityData();
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
  public I2ConnectEntityData createVictim(SocrataResponseData entry) {
    final HashMap<String, Object> properties = new HashMap<>();
    properties.put("PT26", entry.vicAge);
    properties.put("PT27", entry.vicRace);
    properties.put("PT28", entry.vicSex);

    final I2ConnectEntityData victim = new I2ConnectEntityData();
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
  public I2ConnectLinkData createLocationLink(
      SocrataResponseData entry, I2ConnectEntityData complaint, I2ConnectEntityData location) {
    final I2ConnectLinkData locationLink = new I2ConnectLinkData();
    locationLink.id = "LINKLOC" + entry.complaintNum;
    locationLink.typeId = "LT1";
    locationLink.fromEndId = complaint.id;
    locationLink.toEndId = location.id;
    locationLink.linkDirection = LinkDirection.WITH;
    locationLink.sourceReference = generateSourceReference(entry.complaintNum);

    return locationLink;
  }

  /**
   * Creates a suspect link from a single record of data.
   *
   * @param entry The single record from the dataset.
   * @return The created link object.
   */
  public I2ConnectLinkData createSuspectLink(
      SocrataResponseData entry, I2ConnectEntityData complaint, I2ConnectEntityData suspect) {
    final I2ConnectLinkData suspectLink = new I2ConnectLinkData();
    suspectLink.id = "LINKSUSP" + entry.complaintNum;
    suspectLink.typeId = "LT2";
    suspectLink.fromEndId = suspect.id;
    suspectLink.toEndId = complaint.id;
    suspectLink.linkDirection = LinkDirection.WITH;
    suspectLink.sourceReference = generateSourceReference(entry.complaintNum);

    return suspectLink;
  }

  /**
   * Creates a victim link from a single record of data.
   *
   * @param entry The single record from the dataset.
   * @return The created link object.
   */
  public I2ConnectLinkData createVictimLink(
      SocrataResponseData entry, I2ConnectEntityData complaint, I2ConnectEntityData victim) {
    final I2ConnectLinkData victimLink = new I2ConnectLinkData();
    victimLink.id = "LINKVIC" + entry.complaintNum;
    victimLink.typeId = "LT3";
    victimLink.fromEndId = victim.id;
    victimLink.toEndId = complaint.id;
    victimLink.linkDirection = LinkDirection.WITH;
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
    final SourceReferenceInfo source = new SourceReferenceInfo();
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
    reference.source = source;

    return reference;
  }
}
