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
import com.i2group.connector.spi.rest.transport.ExtraStringValidation;
import com.i2group.connector.spi.rest.transport.FormLogicalType;
import com.i2group.connector.spi.rest.transport.MaxStringLength;
import com.i2group.connector.spi.rest.transport.PossibleConditionValue;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

/**
 * A condition in a form.
 */
public class FormConfigCondition {

  public String id;

  public String label;

  public String description;

  public Boolean mandatory = false;

  public FormLogicalType logicalType;

  public Object defaultValue;

  public BigDecimal minValue;

  public BigDecimal maxValue;

  public MaxStringLength maxStringLength;

  public ExtraStringValidation extraStringValidation;

  @Valid
  public List<@Valid PossibleConditionValue> possibleValues;

  public FormConfigCondition() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public FormConfigCondition(String id, String label, FormLogicalType logicalType) {
    this.id = id;
    this.label = label;
    this.logicalType = logicalType;
  }

  public FormConfigCondition id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The identifier of the condition.
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

  public FormConfigCondition label(String label) {
    this.label = label;
    return this;
  }

  /**
   * The label of the condition.
   * @return label
  */
  @NotNull 
  @JsonProperty("label")
  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public FormConfigCondition description(String description) {
    this.description = description;
    return this;
  }

  /**
   * A description of the condition, to be displayed to users.
   * @return description
  */
  
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public FormConfigCondition mandatory(Boolean mandatory) {
    this.mandatory = mandatory;
    return this;
  }

  /**
   * Indicates whether the condition is mandatory.
   * @return mandatory
  */
  
  @JsonProperty("mandatory")
  public Boolean getMandatory() {
    return mandatory;
  }

  public void setMandatory(Boolean mandatory) {
    this.mandatory = mandatory;
  }

  public FormConfigCondition logicalType(FormLogicalType logicalType) {
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

  public FormConfigCondition defaultValue(Object defaultValue) {
    this.defaultValue = defaultValue;
    return this;
  }

  /**
   * The default value for the condition, which must be formatted correctly for its logical type. For the format rules, see [Property value data formats](https://i2group.github.io/analyze-connect/content/miscellaneous/data-model.html).
   * @return defaultValue
  */
  
  @JsonProperty("defaultValue")
  public Object getDefaultValue() {
    return defaultValue;
  }

  public void setDefaultValue(Object defaultValue) {
    this.defaultValue = defaultValue;
  }

  public FormConfigCondition minValue(BigDecimal minValue) {
    this.minValue = minValue;
    return this;
  }

  /**
   * The minimum permitted numeric value in the condition, if its logical type is `DOUBLE` or `INTEGER`.
   * @return minValue
  */
  @Valid 
  @JsonProperty("minValue")
  public BigDecimal getMinValue() {
    return minValue;
  }

  public void setMinValue(BigDecimal minValue) {
    this.minValue = minValue;
  }

  public FormConfigCondition maxValue(BigDecimal maxValue) {
    this.maxValue = maxValue;
    return this;
  }

  /**
   * The maximum permitted numeric value in the condition, if its logical type is `DOUBLE` or `INTEGER`.
   * @return maxValue
  */
  @Valid 
  @JsonProperty("maxValue")
  public BigDecimal getMaxValue() {
    return maxValue;
  }

  public void setMaxValue(BigDecimal maxValue) {
    this.maxValue = maxValue;
  }

  public FormConfigCondition maxStringLength(MaxStringLength maxStringLength) {
    this.maxStringLength = maxStringLength;
    return this;
  }

  /**
   * Get maxStringLength
   * @return maxStringLength
  */
  @Valid 
  @JsonProperty("maxStringLength")
  public MaxStringLength getMaxStringLength() {
    return maxStringLength;
  }

  public void setMaxStringLength(MaxStringLength maxStringLength) {
    this.maxStringLength = maxStringLength;
  }

  public FormConfigCondition extraStringValidation(ExtraStringValidation extraStringValidation) {
    this.extraStringValidation = extraStringValidation;
    return this;
  }

  /**
   * Get extraStringValidation
   * @return extraStringValidation
  */
  @Valid 
  @JsonProperty("extraStringValidation")
  public ExtraStringValidation getExtraStringValidation() {
    return extraStringValidation;
  }

  public void setExtraStringValidation(ExtraStringValidation extraStringValidation) {
    this.extraStringValidation = extraStringValidation;
  }

  public FormConfigCondition possibleValues(List<@Valid PossibleConditionValue> possibleValues) {
    this.possibleValues = possibleValues;
    return this;
  }

  public FormConfigCondition addPossibleValuesItem(PossibleConditionValue possibleValuesItem) {
    if (this.possibleValues == null) {
      this.possibleValues = new ArrayList<>();
    }
    this.possibleValues.add(possibleValuesItem);
    return this;
  }

  /**
   * Possible values for the condition, if its logical type is `SELECTED_FROM` or `SUGGESTED_FROM`.
   * @return possibleValues
  */
  @Valid 
  @JsonProperty("possibleValues")
  public List<@Valid PossibleConditionValue> getPossibleValues() {
    return possibleValues;
  }

  public void setPossibleValues(List<@Valid PossibleConditionValue> possibleValues) {
    this.possibleValues = possibleValues;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FormConfigCondition formConfigCondition = (FormConfigCondition) o;
    return Objects.equals(this.id, formConfigCondition.id) &&
        Objects.equals(this.label, formConfigCondition.label) &&
        Objects.equals(this.description, formConfigCondition.description) &&
        Objects.equals(this.mandatory, formConfigCondition.mandatory) &&
        Objects.equals(this.logicalType, formConfigCondition.logicalType) &&
        Objects.equals(this.defaultValue, formConfigCondition.defaultValue) &&
        Objects.equals(this.minValue, formConfigCondition.minValue) &&
        Objects.equals(this.maxValue, formConfigCondition.maxValue) &&
        Objects.equals(this.maxStringLength, formConfigCondition.maxStringLength) &&
        Objects.equals(this.extraStringValidation, formConfigCondition.extraStringValidation) &&
        Objects.equals(this.possibleValues, formConfigCondition.possibleValues);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, label, description, mandatory, logicalType, defaultValue, minValue, maxValue, maxStringLength, extraStringValidation, possibleValues);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FormConfigCondition {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    mandatory: ").append(toIndentedString(mandatory)).append("\n");
    sb.append("    logicalType: ").append(toIndentedString(logicalType)).append("\n");
    sb.append("    defaultValue: ").append(toIndentedString(defaultValue)).append("\n");
    sb.append("    minValue: ").append(toIndentedString(minValue)).append("\n");
    sb.append("    maxValue: ").append(toIndentedString(maxValue)).append("\n");
    sb.append("    maxStringLength: ").append(toIndentedString(maxStringLength)).append("\n");
    sb.append("    extraStringValidation: ").append(toIndentedString(extraStringValidation)).append("\n");
    sb.append("    possibleValues: ").append(toIndentedString(possibleValues)).append("\n");
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

