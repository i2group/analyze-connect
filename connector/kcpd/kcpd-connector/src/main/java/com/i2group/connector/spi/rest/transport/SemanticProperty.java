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
 * A property of a semantic seed.
 */
public class SemanticProperty {

  public LogicalType logicalType;

  public Object value;

  public String sourceSemanticTypeId;

  public SemanticProperty() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public SemanticProperty(LogicalType logicalType, Object value, String sourceSemanticTypeId) {
    this.logicalType = logicalType;
    this.value = value;
    this.sourceSemanticTypeId = sourceSemanticTypeId;
  }

  public SemanticProperty logicalType(LogicalType logicalType) {
    this.logicalType = logicalType;
    return this;
  }

  /**
   * Get logicalType
   * @return logicalType
  */
  @NotNull @Valid 
  @JsonProperty("logicalType")
  public LogicalType getLogicalType() {
    return logicalType;
  }

  public void setLogicalType(LogicalType logicalType) {
    this.logicalType = logicalType;
  }

  public SemanticProperty value(Object value) {
    this.value = value;
    return this;
  }

  /**
   * The property value.
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

  public SemanticProperty sourceSemanticTypeId(String sourceSemanticTypeId) {
    this.sourceSemanticTypeId = sourceSemanticTypeId;
    return this;
  }

  /**
   * The identifier of the semantic type in the seed record, which might be a descendant of the type in the constraint.
   * @return sourceSemanticTypeId
  */
  @NotNull 
  @JsonProperty("sourceSemanticTypeId")
  public String getSourceSemanticTypeId() {
    return sourceSemanticTypeId;
  }

  public void setSourceSemanticTypeId(String sourceSemanticTypeId) {
    this.sourceSemanticTypeId = sourceSemanticTypeId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SemanticProperty semanticProperty = (SemanticProperty) o;
    return Objects.equals(this.logicalType, semanticProperty.logicalType) &&
        Objects.equals(this.value, semanticProperty.value) &&
        Objects.equals(this.sourceSemanticTypeId, semanticProperty.sourceSemanticTypeId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(logicalType, value, sourceSemanticTypeId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SemanticProperty {\n");
    sb.append("    logicalType: ").append(toIndentedString(logicalType)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    sourceSemanticTypeId: ").append(toIndentedString(sourceSemanticTypeId)).append("\n");
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

