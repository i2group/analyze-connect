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
import com.i2group.connector.spi.rest.transport.VisualQueryCountConditionOperator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

/**
 * A count condition on a query item in a Visual Query structure.
 */
public class VisualQueryCountCondition {

  public VisualQueryCountConditionOperator operator;

  @Valid
  public List<Integer> values;

  @Valid
  public List<String> queryItemIds;

  public VisualQueryCountCondition() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public VisualQueryCountCondition(VisualQueryCountConditionOperator operator) {
    this.operator = operator;
  }

  public VisualQueryCountCondition operator(VisualQueryCountConditionOperator operator) {
    this.operator = operator;
    return this;
  }

  /**
   * Get operator
   * @return operator
  */
  @NotNull @Valid 
  @JsonProperty("operator")
  public VisualQueryCountConditionOperator getOperator() {
    return operator;
  }

  public void setOperator(VisualQueryCountConditionOperator operator) {
    this.operator = operator;
  }

  public VisualQueryCountCondition values(List<Integer> values) {
    this.values = values;
    return this;
  }

  public VisualQueryCountCondition addValuesItem(Integer valuesItem) {
    if (this.values == null) {
      this.values = new ArrayList<>();
    }
    this.values.add(valuesItem);
    return this;
  }

  /**
   * The values to use in this count condition. Unless **operator** is **BETWEEN**, this array contains exactly one value.
   * @return values
  */
  
  @JsonProperty("values")
  public List<Integer> getValues() {
    return values;
  }

  public void setValues(List<Integer> values) {
    this.values = values;
  }

  public VisualQueryCountCondition queryItemIds(List<String> queryItemIds) {
    this.queryItemIds = queryItemIds;
    return this;
  }

  public VisualQueryCountCondition addQueryItemIdsItem(String queryItemIdsItem) {
    if (this.queryItemIds == null) {
      this.queryItemIds = new ArrayList<>();
    }
    this.queryItemIds.add(queryItemIdsItem);
    return this;
  }

  /**
   * The identifiers of the other query items that are bound by this count condition.
   * @return queryItemIds
  */
  @Size(min = 1) 
  @JsonProperty("queryItemIds")
  public List<String> getQueryItemIds() {
    return queryItemIds;
  }

  public void setQueryItemIds(List<String> queryItemIds) {
    this.queryItemIds = queryItemIds;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VisualQueryCountCondition visualQueryCountCondition = (VisualQueryCountCondition) o;
    return Objects.equals(this.operator, visualQueryCountCondition.operator) &&
        Objects.equals(this.values, visualQueryCountCondition.values) &&
        Objects.equals(this.queryItemIds, visualQueryCountCondition.queryItemIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(operator, values, queryItemIds);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VisualQueryCountCondition {\n");
    sb.append("    operator: ").append(toIndentedString(operator)).append("\n");
    sb.append("    values: ").append(toIndentedString(values)).append("\n");
    sb.append("    queryItemIds: ").append(toIndentedString(queryItemIds)).append("\n");
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

