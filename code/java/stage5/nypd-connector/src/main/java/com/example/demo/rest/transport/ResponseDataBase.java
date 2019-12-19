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

package  com.example.demo.rest.transport;

import java.util.Map;

/**
 * Base POJO (Plain Old Java Object) for response data
 * Defines the base properties (variables) for entities and links
 */
public abstract class ResponseDataBase {
  public Object id;
  public String typeId;
  public Long version;
  public Map<String, Object> properties;
}
