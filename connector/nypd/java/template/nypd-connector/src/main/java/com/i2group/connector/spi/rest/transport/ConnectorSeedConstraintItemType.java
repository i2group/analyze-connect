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
import com.i2group.connector.spi.rest.transport.SchemaTypeLocation;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

/**
 * A seed constraint that is based on the item type of seed records.
 */
public class ConnectorSeedConstraintItemType {

  public String id;

  public Integer max;

  public Integer min;

  public SchemaTypeLocation typeLocation;

  public ConnectorSeedConstraintItemType() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ConnectorSeedConstraintItemType(String id) {
    this.id = id;
  }

  public ConnectorSeedConstraintItemType id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The identifier of the item type.
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

  public ConnectorSeedConstraintItemType max(Integer max) {
    this.max = max;
    return this;
  }

  /**
   * The maximum number of seed records of this type.
   * @return max
  */
  
  @JsonProperty("max")
  public Integer getMax() {
    return max;
  }

  public void setMax(Integer max) {
    this.max = max;
  }

  public ConnectorSeedConstraintItemType min(Integer min) {
    this.min = min;
    return this;
  }

  /**
   * The minimum number of seed records of this type.
   * @return min
  */
  
  @JsonProperty("min")
  public Integer getMin() {
    return min;
  }

  public void setMin(Integer min) {
    this.min = min;
  }

  public ConnectorSeedConstraintItemType typeLocation(SchemaTypeLocation typeLocation) {
    this.typeLocation = typeLocation;
    return this;
  }

  /**
   * Get typeLocation
   * @return typeLocation
  */
  @Valid 
  @JsonProperty("typeLocation")
  public SchemaTypeLocation getTypeLocation() {
    return typeLocation;
  }

  public void setTypeLocation(SchemaTypeLocation typeLocation) {
    this.typeLocation = typeLocation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConnectorSeedConstraintItemType connectorSeedConstraintItemType = (ConnectorSeedConstraintItemType) o;
    return Objects.equals(this.id, connectorSeedConstraintItemType.id) &&
        Objects.equals(this.max, connectorSeedConstraintItemType.max) &&
        Objects.equals(this.min, connectorSeedConstraintItemType.min) &&
        Objects.equals(this.typeLocation, connectorSeedConstraintItemType.typeLocation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, max, min, typeLocation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConnectorSeedConstraintItemType {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    max: ").append(toIndentedString(max)).append("\n");
    sb.append("    min: ").append(toIndentedString(min)).append("\n");
    sb.append("    typeLocation: ").append(toIndentedString(typeLocation)).append("\n");
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

