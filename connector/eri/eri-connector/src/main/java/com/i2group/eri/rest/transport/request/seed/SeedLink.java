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

package com.i2group.eri.rest.transport.request.seed;

import com.i2group.eri.rest.transport.response.Link;

/**
 * An link selected on the Analyst's Notebook Premium chart.
 */
public class SeedLink extends SeedItem {
    public Object fromEndId;
    public String fromEndTypeId;
    public Object toEndId;
    public String toEndTypeId;
    public Link.Direction linkDirection;
}