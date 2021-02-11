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

package com.i2group.demo.rest.externalsource.transport;

import lombok.ToString;

/**
 * A POJO (Plain Old Java Object) for response objects from Socrata. These represent the fields in
 * the NYPD database.
 */
@ToString
public class SocrataResponseData {
  // You can map properties using
  // the @JsonProperty("field_name") annotation if you want to rename them, or
  // directly refer to them to use the transport names
  //
  // The Jackson ObjectMapper can deserialize data into appropriate types in many
  // cases. For examples
  // "stringField" : "This is a string" => public String stringField;
  // "dateAndTimeField" :"2018-01-12T00:00:00.000" => public LocalDateTime dateAndTimeField;
  // "numberField" : 1 => public int numberField;
  // "optionalNumberField" : null => public Integer optionalNumberField;

  // TODO: Add response fields to this class.
}
