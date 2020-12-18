/********************************************************************************
# * Licensed Materials - Property of IBM
# * (C) Copyright IBM Corporation 2020. All Rights Reserved
# *
# * This program and the accompanying materials are made available under the
# * terms of the Eclipse Public License 2.0 which is available at
# * http://www.eclipse.org/legal/epl-2.0.
# *
# * US Government Users Restricted Rights - Use, duplication or
# * disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
# *
# ********************************************************************************/

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
