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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonValue;
import com.i2group.connector.spi.rest.transport.VisualQueryConditionAspect;
import com.i2group.connector.spi.rest.transport.VisualQueryConditionOperator;
import com.i2group.connector.spi.rest.transport.VisualQueryConditionValueType;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

/**
 * A condition on a query item in a Visual Query structure.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "valueType", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = VisualQueryBooleanCondition.class, name = "BOOLEAN"),
  @JsonSubTypes.Type(value = VisualQueryDateAndTimeCondition.class, name = "DATE_AND_TIME"),
  @JsonSubTypes.Type(value = VisualQueryGeospatialCondition.class, name = "GEOSPATIAL"),
  @JsonSubTypes.Type(value = VisualQueryNumericCondition.class, name = "NUMBER"),
  @JsonSubTypes.Type(value = VisualQueryStringCondition.class, name = "STRING")
})

public class VisualQueryCondition {

  public VisualQueryConditionAspect aspect;

  public String id;

  public VisualQueryConditionOperator operator;

  public VisualQueryConditionValueType valueType;

  public VisualQueryCondition() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public VisualQueryCondition(String id, VisualQueryConditionOperator operator, VisualQueryConditionValueType valueType) {
    this.id = id;
    this.operator = operator;
    this.valueType = valueType;
  }

  public VisualQueryCondition aspect(VisualQueryConditionAspect aspect) {
    this.aspect = aspect;
    return this;
  }

  /**
   * Get aspect
   * @return aspect
  */
  @Valid 
  @JsonProperty("aspect")
  public VisualQueryConditionAspect getAspect() {
    return aspect;
  }

  public void setAspect(VisualQueryConditionAspect aspect) {
    this.aspect = aspect;
  }

  public VisualQueryCondition id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The identifier of the property or metadata type to use in this query condition.
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

  public VisualQueryCondition operator(VisualQueryConditionOperator operator) {
    this.operator = operator;
    return this;
  }

  /**
   * Get operator
   * @return operator
  */
  @NotNull @Valid 
  @JsonProperty("operator")
  public VisualQueryConditionOperator getOperator() {
    return operator;
  }

  public void setOperator(VisualQueryConditionOperator operator) {
    this.operator = operator;
  }

  public VisualQueryCondition valueType(VisualQueryConditionValueType valueType) {
    this.valueType = valueType;
    return this;
  }

  /**
   * Get valueType
   * @return valueType
  */
  @NotNull @Valid 
  @JsonProperty("valueType")
  public VisualQueryConditionValueType getValueType() {
    return valueType;
  }

  public void setValueType(VisualQueryConditionValueType valueType) {
    this.valueType = valueType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VisualQueryCondition visualQueryCondition = (VisualQueryCondition) o;
    return Objects.equals(this.aspect, visualQueryCondition.aspect) &&
        Objects.equals(this.id, visualQueryCondition.id) &&
        Objects.equals(this.operator, visualQueryCondition.operator) &&
        Objects.equals(this.valueType, visualQueryCondition.valueType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(aspect, id, operator, valueType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VisualQueryCondition {\n");
    sb.append("    aspect: ").append(toIndentedString(aspect)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    operator: ").append(toIndentedString(operator)).append("\n");
    sb.append("    valueType: ").append(toIndentedString(valueType)).append("\n");
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

