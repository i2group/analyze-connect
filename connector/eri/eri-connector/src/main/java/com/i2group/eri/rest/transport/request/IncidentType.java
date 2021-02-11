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

package com.i2group.eri.rest.transport.request;

public enum IncidentType {
    ADMINISTRATION("Administration"),
    AVIATION("Aviation"),
    FIRE("Fire"),
    HAZMAT("HazMat"),
    LAW_ENFORCEMENT("Law Enforcement"),
    MARINE("Marine"),
    MEDICAL("Medical"),
    RESCUE("Rescue"),
    STRUCTURAL("Structural"),
    TRANSPORTATION("Transportation"),
    UTILITY("Utility"),
    WEATHER("Weather"),
    OTHER("Other");

    public final String value;

    IncidentType(String value) {
        this.value = value;
    }

    public static IncidentType matchIncidentType(String input) {
        for (IncidentType type : IncidentType.values()) {
            if (type.value.equals(input)) {
                return type;
            }
        }

        return IncidentType.OTHER;
    }
}
