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
import com.i2group.connector.spi.rest.transport.FeatureCollection;
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
 * A geospatial condition on a query item in a Visual Query structure.
 */
public class VisualQueryGeospatialCondition extends VisualQueryCondition {

  @Valid
  public List<@Valid FeatureCollection> values;

  public VisualQueryGeospatialCondition() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public VisualQueryGeospatialCondition(String id, VisualQueryConditionOperator operator, VisualQueryConditionValueType valueType) {
    super(id, operator, valueType);
  }

  public VisualQueryGeospatialCondition values(List<@Valid FeatureCollection> values) {
    this.values = values;
    return this;
  }

  public VisualQueryGeospatialCondition addValuesItem(FeatureCollection valuesItem) {
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
  @Valid 
  @JsonProperty("values")
  public List<@Valid FeatureCollection> getValues() {
    return values;
  }

  public void setValues(List<@Valid FeatureCollection> values) {
    this.values = values;
  }

  public VisualQueryGeospatialCondition aspect(VisualQueryConditionAspect aspect) {
    super.aspect(aspect);
    return this;
  }

  public VisualQueryGeospatialCondition id(String id) {
    super.id(id);
    return this;
  }

  public VisualQueryGeospatialCondition operator(VisualQueryConditionOperator operator) {
    super.operator(operator);
    return this;
  }

  public VisualQueryGeospatialCondition valueType(VisualQueryConditionValueType valueType) {
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
    VisualQueryGeospatialCondition visualQueryGeospatialCondition = (VisualQueryGeospatialCondition) o;
    return Objects.equals(this.values, visualQueryGeospatialCondition.values) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(values, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VisualQueryGeospatialCondition {\n");
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

