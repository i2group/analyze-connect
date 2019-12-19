/********************************************************************************
# * Licensed Materials - Property of IBM
# * (C) Copyright IBM Corporation 2019. All Rights Reserved
# *
# * This program and the accompanying materials are made available under the
# * terms of the Eclipse Public License 2.0 which is available at
# * http://www.eclipse.org/legal/epl-2.0.
# *
# * US Government Users Restricted Rights - Use, duplication or
# * disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
# *
# ********************************************************************************/

package com.example.demo.rest.transport.request;

import java.util.List;

import com.example.demo.rest.transport.request.seeded.DaodSeeds;

import lombok.ToString;

/**
 * Standard request payload for conditions and seeds provided by the user
 */
@ToString
public class Payload {
    public List<RequestCondition> conditions;
    public DaodSeeds seeds;
}
