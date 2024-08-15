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
import com.i2group.connector.spi.rest.transport.VisualQueryConditionAspect;
import com.i2group.connector.spi.rest.transport.VisualQueryConditionOperator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

/**
 * A rule that governs the property types and operators that can appear in the conditions of a query item.
 */
public class VisualQueryOperatorUsageRule {

  /**
   * The type of the rule, which determines whether it allows or denies particular conditions.
   */
  public enum RuleTypeEnum {
    DENY("DENY"),
    
    ALLOW("ALLOW");

    private String value;

    RuleTypeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static RuleTypeEnum fromValue(String value) {
      for (RuleTypeEnum b : RuleTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public RuleTypeEnum ruleType;

  public String itemTypeId;

  @Valid
  public List<String> propertyTypeIds;

  @Valid
  public List<@Valid VisualQueryConditionOperator> operators;

  @Valid
  public List<@Valid VisualQueryConditionAspect> aspects;

  public VisualQueryOperatorUsageRule() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public VisualQueryOperatorUsageRule(RuleTypeEnum ruleType) {
    this.ruleType = ruleType;
  }

  public VisualQueryOperatorUsageRule ruleType(RuleTypeEnum ruleType) {
    this.ruleType = ruleType;
    return this;
  }

  /**
   * The type of the rule, which determines whether it allows or denies particular conditions.
   * @return ruleType
  */
  @NotNull 
  @JsonProperty("ruleType")
  public RuleTypeEnum getRuleType() {
    return ruleType;
  }

  public void setRuleType(RuleTypeEnum ruleType) {
    this.ruleType = ruleType;
  }

  public VisualQueryOperatorUsageRule itemTypeId(String itemTypeId) {
    this.itemTypeId = itemTypeId;
    return this;
  }

  /**
   * The identifier of the query item type that the rule affects.
   * @return itemTypeId
  */
  
  @JsonProperty("itemTypeId")
  public String getItemTypeId() {
    return itemTypeId;
  }

  public void setItemTypeId(String itemTypeId) {
    this.itemTypeId = itemTypeId;
  }

  public VisualQueryOperatorUsageRule propertyTypeIds(List<String> propertyTypeIds) {
    this.propertyTypeIds = propertyTypeIds;
    return this;
  }

  public VisualQueryOperatorUsageRule addPropertyTypeIdsItem(String propertyTypeIdsItem) {
    if (this.propertyTypeIds == null) {
      this.propertyTypeIds = new ArrayList<>();
    }
    this.propertyTypeIds.add(propertyTypeIdsItem);
    return this;
  }

  /**
   * The identifiers of the property types that the rule affects.
   * @return propertyTypeIds
  */
  
  @JsonProperty("propertyTypeIds")
  public List<String> getPropertyTypeIds() {
    return propertyTypeIds;
  }

  public void setPropertyTypeIds(List<String> propertyTypeIds) {
    this.propertyTypeIds = propertyTypeIds;
  }

  public VisualQueryOperatorUsageRule operators(List<@Valid VisualQueryConditionOperator> operators) {
    this.operators = operators;
    return this;
  }

  public VisualQueryOperatorUsageRule addOperatorsItem(VisualQueryConditionOperator operatorsItem) {
    if (this.operators == null) {
      this.operators = new ArrayList<>();
    }
    this.operators.add(operatorsItem);
    return this;
  }

  /**
   * The operators that the rule permits or prevents from appearing in conditions.
   * @return operators
  */
  @Valid 
  @JsonProperty("operators")
  public List<@Valid VisualQueryConditionOperator> getOperators() {
    return operators;
  }

  public void setOperators(List<@Valid VisualQueryConditionOperator> operators) {
    this.operators = operators;
  }

  public VisualQueryOperatorUsageRule aspects(List<@Valid VisualQueryConditionAspect> aspects) {
    this.aspects = aspects;
    return this;
  }

  public VisualQueryOperatorUsageRule addAspectsItem(VisualQueryConditionAspect aspectsItem) {
    if (this.aspects == null) {
      this.aspects = new ArrayList<>();
    }
    this.aspects.add(aspectsItem);
    return this;
  }

  /**
   * The aspects of the property types that the rule affects.
   * @return aspects
  */
  @Valid 
  @JsonProperty("aspects")
  public List<@Valid VisualQueryConditionAspect> getAspects() {
    return aspects;
  }

  public void setAspects(List<@Valid VisualQueryConditionAspect> aspects) {
    this.aspects = aspects;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VisualQueryOperatorUsageRule visualQueryOperatorUsageRule = (VisualQueryOperatorUsageRule) o;
    return Objects.equals(this.ruleType, visualQueryOperatorUsageRule.ruleType) &&
        Objects.equals(this.itemTypeId, visualQueryOperatorUsageRule.itemTypeId) &&
        Objects.equals(this.propertyTypeIds, visualQueryOperatorUsageRule.propertyTypeIds) &&
        Objects.equals(this.operators, visualQueryOperatorUsageRule.operators) &&
        Objects.equals(this.aspects, visualQueryOperatorUsageRule.aspects);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ruleType, itemTypeId, propertyTypeIds, operators, aspects);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VisualQueryOperatorUsageRule {\n");
    sb.append("    ruleType: ").append(toIndentedString(ruleType)).append("\n");
    sb.append("    itemTypeId: ").append(toIndentedString(itemTypeId)).append("\n");
    sb.append("    propertyTypeIds: ").append(toIndentedString(propertyTypeIds)).append("\n");
    sb.append("    operators: ").append(toIndentedString(operators)).append("\n");
    sb.append("    aspects: ").append(toIndentedString(aspects)).append("\n");
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

