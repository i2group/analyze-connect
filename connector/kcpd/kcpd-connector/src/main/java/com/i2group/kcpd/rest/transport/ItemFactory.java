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

package com.i2group.kcpd.rest.transport;

import com.i2group.connector.spi.rest.transport.GeoJSONPoint;
import com.i2group.connector.spi.rest.transport.I2ConnectEntityData;
import com.i2group.connector.spi.rest.transport.I2ConnectLinkData;
import com.i2group.connector.spi.rest.transport.LinkDirection;
import com.i2group.connector.spi.rest.transport.SourceReference;
import com.i2group.connector.spi.rest.transport.SourceReferenceInfo;
import com.i2group.kcpd.rest.externalsource.transport.SocrataResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.List;

/** Used to generate entity and link objects */
public class ItemFactory {
  @Autowired
  @Value("${socrata.url}")
  private static String baseUrl;

  /**
   * Creates report from a single record of data.
   *
   * @param entry The single record from the dataset.
   * @return The created report object.
   */
  public static I2ConnectEntityData createReport(SocrataResponseData entry) {
    HashMap<String, Object> properties = new HashMap<>();

    properties.put("PT1", entry.reportNumber);
    properties.put("PT2", entry.reportDate);
    properties.put("PT3", entry.fromDate);
    properties.put("PT4", entry.toDate);
    properties.put("PT5", entry.fromTime);
    properties.put("PT6", entry.toTime);
    properties.put("PT7", entry.offense);
    properties.put("PT17", entry.offenseDescription);

    I2ConnectEntityData report = new I2ConnectEntityData();
    report.id = buildId("REP", entry);
    report.typeId = "ET1";
    report.version = 1L;
    report.properties = properties;
    report.sourceReference = generateSourceReference(entry.reportNumber);

    return report;
  }

  /**
   * Creates location from a single record of data.
   *
   * @param entry The single record from the dataset.
   * @return The created location object.
   */
  public static I2ConnectEntityData createLocation(SocrataResponseData entry) {
    HashMap<String, Object> properties = new HashMap<>();

    properties.put("PT9", entry.city);
    properties.put("PT10", entry.address);
    properties.put("PT11", entry.zipCode);

    if (entry.location != null) {
      final GeoJSONPoint geoJSONPoint = new GeoJSONPoint();
      geoJSONPoint.type = GeoJSONPoint.TypeEnum.POINT;
      geoJSONPoint.coordinates = List.of(entry.location.getLongitude(), entry.location.getLatitude());
      properties.put("PT12", geoJSONPoint);
    }

    I2ConnectEntityData location = new I2ConnectEntityData();
    location.id = "LOC" + entry.address + entry.zipCode;
    location.typeId = "ET2";
    location.version = 1L;
    location.properties = properties;
    location.sourceReference = generateSourceReference(entry.reportNumber);

    return location;
  }

  /**
   * Creates person from a single record of data.
   *
   * @param entry The single record from the dataset.
   * @return The created person object.
   */
  public static I2ConnectEntityData createPerson(SocrataResponseData entry) {
    HashMap<String, Object> properties = new HashMap<>();
    properties.put("PT13", entry.race);
    properties.put("PT14", entry.sex);
    properties.put("PT15", String.valueOf(entry.age));

    I2ConnectEntityData person = new I2ConnectEntityData();
    person.id = buildId("PER", entry); 
    person.typeId = "ET3";
    person.version = 1L;
    person.properties = properties;
    person.sourceReference = generateSourceReference(entry.reportNumber);

    return person;
  }

  /**
   * Creates a location link from a single record of data.
   *
   * @param entry The single record from the dataset.
   * @param report The report from the row of the dataset.
   * @param location The location from the row of the dataset.
   * @return The created link object.
   */
  public static I2ConnectLinkData createLocationLink(
      SocrataResponseData entry, I2ConnectEntityData report, I2ConnectEntityData location) {
    I2ConnectLinkData locationLink = new I2ConnectLinkData();
    locationLink.id = "LINKLOC" + entry.reportNumber;
    locationLink.typeId = "LT1";
    locationLink.fromEndId = report.id;
    locationLink.toEndId = location.id;
    locationLink.linkDirection = LinkDirection.WITH;
    locationLink.sourceReference = generateSourceReference(entry.reportNumber);

    return locationLink;
  }

  /**
   * Creates a suspect link from a single record of data.
   *
   * @param entry The single record from the dataset.
   * @param report The report from the row of the dataset.
   * @param suspect The suspect from the row of the dataset.
   * @return The created link object.
   */
  public static I2ConnectLinkData createSuspectLink(
      SocrataResponseData entry, I2ConnectEntityData report, I2ConnectEntityData suspect) {
    I2ConnectLinkData suspectLink = new I2ConnectLinkData();
    suspectLink.id = buildId("LINKSUS", entry);
    suspectLink.typeId = "LT2";
    suspectLink.fromEndId = suspect.id;
    suspectLink.toEndId = report.id;
    suspectLink.linkDirection = LinkDirection.WITH;
    suspectLink.sourceReference = generateSourceReference(entry.reportNumber);

    return suspectLink;
  }

  /**
   * Creates a victim link from a single record of data.
   *
   * @param entry The single record from the dataset.
   * @param report The report from the row of the dataset.
   * @param victim The victim from the row of the dataset.
   * @return The created link object.
   */
  public static I2ConnectLinkData createVictimLink(
      SocrataResponseData entry, I2ConnectEntityData report, I2ConnectEntityData victim) {
    I2ConnectLinkData victimLink = new I2ConnectLinkData();
    victimLink.id = buildId("LINKVIC", entry);
    victimLink.typeId = "LT3";
    victimLink.fromEndId = victim.id;
    victimLink.toEndId = report.id;
    victimLink.linkDirection = LinkDirection.WITH;
    victimLink.sourceReference = generateSourceReference(entry.reportNumber);

    return victimLink;
  }

  /**
   * Creates an arrested link from a single record of data.
   *
   * @param entry The single record from the dataset.
   * @param report The report from the row of the dataset.
   * @param arrested The arrested from the row of the dataset.
   * @return The created link object.
   */
  public static I2ConnectLinkData createArrestedLink(
      SocrataResponseData entry, I2ConnectEntityData report, I2ConnectEntityData arrested) {
    I2ConnectLinkData arrestedLink = new I2ConnectLinkData();
    arrestedLink.id = buildId("LINKARR", entry);
    arrestedLink.typeId = "LT4";
    arrestedLink.fromEndId = arrested.id;
    arrestedLink.toEndId = report.id;
    arrestedLink.linkDirection = LinkDirection.WITH;
    arrestedLink.sourceReference = generateSourceReference(entry.reportNumber);

    return arrestedLink;
  }

  /**
   * Creates a charged link from a single record of data.
   *
   * @param entry The single record from the dataset.
   * @param report The report from the row of the dataset.
   * @param charged The charged from the row of the dataset.
   * @return The created link object.
   */
  public static I2ConnectLinkData createChargedLink(
      SocrataResponseData entry, I2ConnectEntityData report, I2ConnectEntityData charged) {
    I2ConnectLinkData chargedLink = new I2ConnectLinkData();
    chargedLink.id = buildId("LINKCHA", entry);
    chargedLink.typeId = "LT5";
    chargedLink.fromEndId = charged.id;
    chargedLink.toEndId = report.id;
    chargedLink.linkDirection = LinkDirection.WITH;
    chargedLink.sourceReference = generateSourceReference(entry.reportNumber);

    return chargedLink;
  }

    /**
   * Creates a complicit link from a single record of data.
   *
   * @param entry The single record from the dataset.
   * @param report The report from the row of the dataset.
   * @param complicit The complicit from the row of the dataset.
   * @return The created link object.
   */
  public static I2ConnectLinkData createComplicitLink(
      SocrataResponseData entry, I2ConnectEntityData report, I2ConnectEntityData complicit) {
    I2ConnectLinkData complicitLink = new I2ConnectLinkData();
    complicitLink.id = buildId("LINKCMP", entry);
    complicitLink.typeId = "LT6";
    complicitLink.fromEndId = complicit.id;
    complicitLink.toEndId = report.id;
    complicitLink.linkDirection = LinkDirection.WITH;
    complicitLink.sourceReference = generateSourceReference(entry.reportNumber);

    return complicitLink;
  }

  /**
   * Generate a valid source reference, querying a link to an item of data using the unique
   * report number.
   *
   * @param reportNum The report number of the record associated with the entity.
   * @return The SourceReference object containing details of the source.
   */
  private static SourceReference generateSourceReference(String reportNum) {
    SourceReferenceInfo source = new SourceReferenceInfo();
    source.name = "KCPD Crime Data";
    source.type = "Open source data";
    source.description =
        "A source reference to the corresponding record from the KCPD Crime Data.";
    source.location = baseUrl + "?report_no=" + reportNum;

    SourceReference reference = new SourceReference();
    reference.source = source;

    return reference;
  }

  private static String buildId(String identifier, SocrataResponseData entry) {
    String involvement = entry.involvement.replace(" ", "&");
    return identifier + entry.reportNumber + "-R" + entry.race + "S"+ entry.sex + "A" + entry.age 
      + "-I" + involvement + "-C" + entry.ibrs;
  }

  public static String buildId(SocrataResponseData entry) {
    return buildId("", entry);
  }
}
