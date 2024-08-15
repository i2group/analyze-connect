/*
* MIT License
*
* © N.Harris Computer Corporation (2024)
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

package com.i2group.connector.spi.rest.transport;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

/**
 * A fully qualified date and time, including a time zone.
 */
public class DateAndTime {

  public Boolean isDST;

  public String localDateAndTime;

  public String timeZoneId;

  public DateAndTime() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public DateAndTime(Boolean isDST, String localDateAndTime, String timeZoneId) {
    this.isDST = isDST;
    this.localDateAndTime = localDateAndTime;
    this.timeZoneId = timeZoneId;
  }

  public DateAndTime isDST(Boolean isDST) {
    this.isDST = isDST;
    return this;
  }

  /**
   * Indicates whether daylight saving time is (**true**) or is not (**false**) in effect for the value in **localDateAndTime**.
   * @return isDST
  */
  @NotNull 
  @JsonProperty("isDST")
  public Boolean getIsDST() {
    return isDST;
  }

  public void setIsDST(Boolean isDST) {
    this.isDST = isDST;
  }

  public DateAndTime localDateAndTime(String localDateAndTime) {
    this.localDateAndTime = localDateAndTime;
    return this;
  }

  /**
   * The local date and time, formatted as an ISO 8601 string.
   * @return localDateAndTime
  */
  @NotNull 
  @JsonProperty("localDateAndTime")
  public String getLocalDateAndTime() {
    return localDateAndTime;
  }

  public void setLocalDateAndTime(String localDateAndTime) {
    this.localDateAndTime = localDateAndTime;
  }

  public DateAndTime timeZoneId(String timeZoneId) {
    this.timeZoneId = timeZoneId;
    return this;
  }

  /**
   * The ISO 8601 identifier for the time zone of the value in **localDateAndTime**.
   * @return timeZoneId
  */
  @NotNull 
  @JsonProperty("timeZoneId")
  public String getTimeZoneId() {
    return timeZoneId;
  }

  public void setTimeZoneId(String timeZoneId) {
    this.timeZoneId = timeZoneId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DateAndTime dateAndTime = (DateAndTime) o;
    return Objects.equals(this.isDST, dateAndTime.isDST) &&
        Objects.equals(this.localDateAndTime, dateAndTime.localDateAndTime) &&
        Objects.equals(this.timeZoneId, dateAndTime.timeZoneId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isDST, localDateAndTime, timeZoneId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DateAndTime {\n");
    sb.append("    isDST: ").append(toIndentedString(isDST)).append("\n");
    sb.append("    localDateAndTime: ").append(toIndentedString(localDateAndTime)).append("\n");
    sb.append("    timeZoneId: ").append(toIndentedString(timeZoneId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

