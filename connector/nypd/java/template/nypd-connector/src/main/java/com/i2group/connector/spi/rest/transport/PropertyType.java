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
import com.i2group.connector.spi.rest.transport.LogicalType;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

/**
 * A property type of an item type from an i2 Analyze schema.
 */
public class PropertyType {

  public String displayName;

  public LogicalType logicalType;

  public String semanticTypeId;

  public PropertyType displayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  /**
   * The display name for this property type.
   * @return displayName
  */
  
  @JsonProperty("displayName")
  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public PropertyType logicalType(LogicalType logicalType) {
    this.logicalType = logicalType;
    return this;
  }

  /**
   * Get logicalType
   * @return logicalType
  */
  @Valid 
  @JsonProperty("logicalType")
  public LogicalType getLogicalType() {
    return logicalType;
  }

  public void setLogicalType(LogicalType logicalType) {
    this.logicalType = logicalType;
  }

  public PropertyType semanticTypeId(String semanticTypeId) {
    this.semanticTypeId = semanticTypeId;
    return this;
  }

  /**
   * The identifier of the semantic type for this property type.
   * @return semanticTypeId
  */
  
  @JsonProperty("semanticTypeId")
  public String getSemanticTypeId() {
    return semanticTypeId;
  }

  public void setSemanticTypeId(String semanticTypeId) {
    this.semanticTypeId = semanticTypeId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PropertyType propertyType = (PropertyType) o;
    return Objects.equals(this.displayName, propertyType.displayName) &&
        Objects.equals(this.logicalType, propertyType.logicalType) &&
        Objects.equals(this.semanticTypeId, propertyType.semanticTypeId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(displayName, logicalType, semanticTypeId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PropertyType {\n");
    sb.append("    displayName: ").append(toIndentedString(displayName)).append("\n");
    sb.append("    logicalType: ").append(toIndentedString(logicalType)).append("\n");
    sb.append("    semanticTypeId: ").append(toIndentedString(semanticTypeId)).append("\n");
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

