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
import com.i2group.connector.spi.rest.transport.VisualQueryConstraints;
import com.i2group.connector.spi.rest.transport.VisualQueryOperatorUsageRule;
import com.i2group.connector.spi.rest.transport.VisualQueryPaletteItemTypes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

/**
 * The configuration that governs the behavior and content of Visual Queries.
 */
public class VisualQueryConfig implements ClientConfigConfig {

  @Valid
  public List<@Valid VisualQueryOperatorUsageRule> operatorUsageRules;

  public VisualQueryConstraints constraints;

  public VisualQueryPaletteItemTypes paletteItemTypes;

  public VisualQueryConfig operatorUsageRules(List<@Valid VisualQueryOperatorUsageRule> operatorUsageRules) {
    this.operatorUsageRules = operatorUsageRules;
    return this;
  }

  public VisualQueryConfig addOperatorUsageRulesItem(VisualQueryOperatorUsageRule operatorUsageRulesItem) {
    if (this.operatorUsageRules == null) {
      this.operatorUsageRules = new ArrayList<>();
    }
    this.operatorUsageRules.add(operatorUsageRulesItem);
    return this;
  }

  /**
   * The rules that govern the property types and operators that can appear in Visual Query conditions.
   * @return operatorUsageRules
  */
  @Valid 
  @JsonProperty("operatorUsageRules")
  public List<@Valid VisualQueryOperatorUsageRule> getOperatorUsageRules() {
    return operatorUsageRules;
  }

  public void setOperatorUsageRules(List<@Valid VisualQueryOperatorUsageRule> operatorUsageRules) {
    this.operatorUsageRules = operatorUsageRules;
  }

  public VisualQueryConfig constraints(VisualQueryConstraints constraints) {
    this.constraints = constraints;
    return this;
  }

  /**
   * Get constraints
   * @return constraints
  */
  @Valid 
  @JsonProperty("constraints")
  public VisualQueryConstraints getConstraints() {
    return constraints;
  }

  public void setConstraints(VisualQueryConstraints constraints) {
    this.constraints = constraints;
  }

  public VisualQueryConfig paletteItemTypes(VisualQueryPaletteItemTypes paletteItemTypes) {
    this.paletteItemTypes = paletteItemTypes;
    return this;
  }

  /**
   * Get paletteItemTypes
   * @return paletteItemTypes
  */
  @Valid 
  @JsonProperty("paletteItemTypes")
  public VisualQueryPaletteItemTypes getPaletteItemTypes() {
    return paletteItemTypes;
  }

  public void setPaletteItemTypes(VisualQueryPaletteItemTypes paletteItemTypes) {
    this.paletteItemTypes = paletteItemTypes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VisualQueryConfig visualQueryConfig = (VisualQueryConfig) o;
    return Objects.equals(this.operatorUsageRules, visualQueryConfig.operatorUsageRules) &&
        Objects.equals(this.constraints, visualQueryConfig.constraints) &&
        Objects.equals(this.paletteItemTypes, visualQueryConfig.paletteItemTypes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(operatorUsageRules, constraints, paletteItemTypes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VisualQueryConfig {\n");
    sb.append("    operatorUsageRules: ").append(toIndentedString(operatorUsageRules)).append("\n");
    sb.append("    constraints: ").append(toIndentedString(constraints)).append("\n");
    sb.append("    paletteItemTypes: ").append(toIndentedString(paletteItemTypes)).append("\n");
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

