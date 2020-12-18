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

package com.i2group.async.rest.transport;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.ToString;

/**
 * A POJO (Plain Old Java Object) for objects from the people.json data source.
 */
@ToString
public class ResponseData {
  // Person
  @JsonProperty("people")
  public List<Person> person;

  public static class Person {
    @JsonProperty("id")
    public String id;
  
    @JsonProperty("forename")
    public String forename;
  
    @JsonProperty("surname")
    public String surname;
  
    @JsonProperty("dob")
    public String dob;
  
    @JsonProperty("ssn")
    public String ssn;
  
    @JsonProperty("issuedDateAndTime")
    public String issuedDateAndTime;
  
    @JsonProperty("friends")
    public List<String> friends;
  
    @JsonProperty("image")
    public String image;
  }
}
