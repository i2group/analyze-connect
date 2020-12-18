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

package com.i2group.kcpd.rest.transport.request.seeded;

import java.util.List;
import java.util.Map;

/** Seeded search request data containing variables describing how to define seeded data. */
public abstract class SeedDataBase {
  public List<Object> accessDimensionValues;
  public Object extensions;
  public String label;
  public Map<String, ?> properties;
  public Object seedId;
  public List<SourceId> sourceIds;
  public String typeId;
}
