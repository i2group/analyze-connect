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

package com.i2group.kcpd.rest.transport;

import java.util.ArrayList;
import java.util.List;

public class GeospatialPoint {
    private final String type = "Point";
    private List<Double> coordinates;

    private GeospatialPoint() {}

    public static GeospatialPoint withCoordinates(double latitude, double longitude) {
        final GeospatialPoint point = new GeospatialPoint();

        point.coordinates = new ArrayList<>();
        point.coordinates.add(longitude);
        point.coordinates.add(latitude);

        return point;
    }

    public String getType() {
        return type;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }
}
