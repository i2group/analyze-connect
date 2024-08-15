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
import com.fasterxml.jackson.annotation.JsonValue;
import com.i2group.connector.spi.rest.transport.FormLogicalType;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

/**
 * A condition in a search request.
 */
public class DaodRequestCondition {

  public String id;

  public FormLogicalType logicalType;

  public Object value;

  public DaodRequestCondition() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public DaodRequestCondition(String id, FormLogicalType logicalType, Object value) {
    this.id = id;
    this.logicalType = logicalType;
    this.value = value;
  }

  public DaodRequestCondition id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The identifier of the condition, as specified in the client configuration for the service.
   * @return id
  */
  @NotNull 
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public DaodRequestCondition logicalType(FormLogicalType logicalType) {
    this.logicalType = logicalType;
    return this;
  }

  /**
   * Get logicalType
   * @return logicalType
  */
  @NotNull @Valid 
  @JsonProperty("logicalType")
  public FormLogicalType getLogicalType() {
    return logicalType;
  }

  public void setLogicalType(FormLogicalType logicalType) {
    this.logicalType = logicalType;
  }

  public DaodRequestCondition value(Object value) {
    this.value = value;
    return this;
  }

  /**
   * The value that a user supplied for the condition, which must be formatted correctly for its logical type. For the format rules, see [Valid formats for i2 Connect data values](https://i2group.github.io/analyze-connect/content/miscellaneous/data-model.html).
   * @return value
  */
  @NotNull 
  @JsonProperty("value")
  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DaodRequestCondition daodRequestCondition = (DaodRequestCondition) o;
    return Objects.equals(this.id, daodRequestCondition.id) &&
        Objects.equals(this.logicalType, daodRequestCondition.logicalType) &&
        Objects.equals(this.value, daodRequestCondition.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, logicalType, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DaodRequestCondition {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    logicalType: ").append(toIndentedString(logicalType)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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

