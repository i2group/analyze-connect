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
import com.i2group.connector.spi.rest.transport.VisualQueryCondition;
import com.i2group.connector.spi.rest.transport.VisualQueryConditionAspect;
import com.i2group.connector.spi.rest.transport.VisualQueryConditionOperator;
import com.i2group.connector.spi.rest.transport.VisualQueryConditionValueType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

/**
 * A string condition on a query item in a Visual Query structure.
 */
public class VisualQueryStringCondition extends VisualQueryCondition {

  @Valid
  public List<String> values;

  public VisualQueryStringCondition() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public VisualQueryStringCondition(String id, VisualQueryConditionOperator operator, VisualQueryConditionValueType valueType) {
    super(id, operator, valueType);
  }

  public VisualQueryStringCondition values(List<String> values) {
    this.values = values;
    return this;
  }

  public VisualQueryStringCondition addValuesItem(String valuesItem) {
    if (this.values == null) {
      this.values = new ArrayList<>();
    }
    this.values.add(valuesItem);
    return this;
  }

  /**
   * Get values
   * @return values
  */
  
  @JsonProperty("values")
  public List<String> getValues() {
    return values;
  }

  public void setValues(List<String> values) {
    this.values = values;
  }

  public VisualQueryStringCondition aspect(VisualQueryConditionAspect aspect) {
    super.aspect(aspect);
    return this;
  }

  public VisualQueryStringCondition id(String id) {
    super.id(id);
    return this;
  }

  public VisualQueryStringCondition operator(VisualQueryConditionOperator operator) {
    super.operator(operator);
    return this;
  }

  public VisualQueryStringCondition valueType(VisualQueryConditionValueType valueType) {
    super.valueType(valueType);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VisualQueryStringCondition visualQueryStringCondition = (VisualQueryStringCondition) o;
    return Objects.equals(this.values, visualQueryStringCondition.values) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(values, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VisualQueryStringCondition {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    values: ").append(toIndentedString(values)).append("\n");
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

