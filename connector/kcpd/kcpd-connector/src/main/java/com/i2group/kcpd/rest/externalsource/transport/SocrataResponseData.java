/*
 * MIT License
 *
 * © N.Harris Computer Corporation (2022)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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

  @JsonProperty("offense")
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
