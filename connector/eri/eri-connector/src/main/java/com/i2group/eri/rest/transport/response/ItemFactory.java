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

package com.i2group.eri.rest.transport.response;

import com.i2group.eri.rest.externalsource.transport.SocrataResponseData;
import com.i2group.eri.rest.transport.request.IncidentType;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemFactory {

    private static final Pattern pattern = Pattern.compile("([A-Za-z\\s]+)-(" + ".*)");

    /**
     * Creates an incident from a single record of data.
     *
     * @param entry The single record from the dataset.
     * @param id The identifier for the row of the dataset.
     * @return The created incident object.
     */
    public static Entity createIncident(SocrataResponseData entry, String id) {
        final Map<String, Object> properties = new HashMap<>();

        final Matcher matcher = pattern.matcher(entry.incidentType);
        if (matcher.find()) {
            final IncidentType incidentType = IncidentType.matchIncidentType(matcher.group(1));
            final String incidentSubtype = matcher.group(2);

            properties.put("PT1", incidentType.value);
            properties.put("PT2", incidentSubtype);
            properties.put("PT3", entry.creationDateTime.toLocalDate());
            properties.put("PT4", entry.creationDateTime.toLocalTime());

            if (entry.closedDateTime != null) {
                properties.put("PT5", entry.closedDateTime.toLocalDate());
                properties.put("PT6", entry.closedDateTime.toLocalTime());
            }
        }

        final Entity incident = new Entity();
        incident.id = "INC" + id;
        incident.typeId = "ET1";
        incident.properties = properties;

        return incident;
    }

    /**
     * Creates a location from a single record of data.
     *
     * @param entry The single record from the dataset.
     * @param id The identifier for the row of the dataset.
     * @return The created location object.
     */
    public static Entity createLocation(SocrataResponseData entry, String id) {
        final Map<String, Object> properties = new HashMap<>();
        properties.put("PT7", entry.borough);
        properties.put("PT8", entry.address);

        if (entry.latitude != null) {
            final Geospatial geospatial = Geospatial.withCoordinates(entry.latitude,
                entry.longitude);
            properties.put("PT9", geospatial);
        }

        final Entity location = new Entity();
        location.id = "LOC" + id;
        location.typeId = "ET2";
        location.properties = properties;

        return location;
    }

    /**
     * Creates a link from an incident to a location.
     *
     * @param incident The incident from the row of the dataset.
     * @param location The location from the row of the dataset.
     * @param id The identifier for the row of the dataset.
     * @return The created link object.
     */
    public static Link createLocationLink(Entity incident, Entity location, int id) {
        final Link link = new Link();
        link.id = "LINKLOC" + id;
        link.typeId = "LT1";
        link.fromEndId = incident.id;
        link.toEndId = location.id;
        link.linkDirection = Link.Direction.WITH;
        return link;
    }
}
