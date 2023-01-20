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

package com.i2group.eri.rest.externalsource.transport;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

/**
 * A representation of the the fields from the the Emergency Response
 * Incidents dataset.
 */
public class SocrataResponseData {

    // Incident
    @JsonProperty("incident_type")
    public String incidentType;

    @JsonProperty("creation_date")
    public LocalDateTime creationDateTime;

    @JsonProperty("closed_date")
    public LocalDateTime closedDateTime;

    @JsonProperty("location")
    public String address;

    @JsonProperty("borough")
    public String borough;

    @JsonProperty("latitude")
    public Float latitude;

    @JsonProperty("longitude")
    public Float longitude;

    public String getIncidentKey() {
        return incidentType;
    }

    public String getIncidentKey(int index) {
        return incidentType + index;
    }

    public String getLocationKey() {
        return borough + address;
    }

}
