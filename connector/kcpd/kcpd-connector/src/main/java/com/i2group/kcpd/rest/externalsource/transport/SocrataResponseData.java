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

package com.i2group.kcpd.rest.externalsource.transport;

import com.i2group.kcpd.rest.transport.Location;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A POJO (Plain Old Java Object) for response objects from Socrata. These represent the fields in
 * the KCPD database.
 */
@ToString
public class SocrataResponseData {

  // Report
  @JsonProperty("report_no")
  public String reportNumber;

  @JsonProperty("reported_date")
  public LocalDate reportDate;

  @JsonProperty("from_date")
  public LocalDate fromDate;

  @JsonProperty("to_date")
  public LocalDate toDate;

  @JsonProperty("from_time")
  public LocalTime fromTime;

  @JsonProperty("to_time")
  public LocalTime toTime;

  @JsonProperty("offence")
  public String offense;

  @JsonProperty("description") 
  public String offenseDescription;

  @JsonProperty("ibrs")
  public String ibrs;

  @JsonProperty("dvflag")
  public String domesticViolence;

  // Location
  @JsonProperty("city")
  public String city;

  @JsonProperty("address")
  public String address;

  @JsonProperty("zip_code")
  public int zipCode;

  @JsonProperty("location")
  public Location location;

  // Person
  @JsonProperty("race")
  public String race;

  @JsonProperty("sex")
  public String sex;

  @JsonProperty("age")
  public int age;

  @JsonProperty("involvement")
  public String involvement;
}
