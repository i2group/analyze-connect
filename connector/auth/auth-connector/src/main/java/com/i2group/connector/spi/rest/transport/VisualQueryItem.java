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
import com.i2group.connector.spi.rest.transport.VisualQueryCondition;
import com.i2group.connector.spi.rest.transport.VisualQueryCountCondition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

/**
 * A query item in a Visual Query structure.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = VisualQueryEntity.class, name = "QUERY_ENTITY"),
  @JsonSubTypes.Type(value = VisualQueryLink.class, name = "QUERY_LINK")
})

public class VisualQueryItem {

  @Valid
  public List<@Valid VisualQueryCondition> conditions;

  public VisualQueryCountCondition countCondition;

  public String id;

  @Valid
  public List<String> itemTypeIds;

  public String label;

  public Boolean outputEnabled;

  /**
   * The type of the query item, which is **QUERY_ENTITY** for a query entity, or **QUERY_LINK** for a query link.
   */
  public enum TypeEnum {
    ENTITY("QUERY_ENTITY"),
    
    LINK("QUERY_LINK");

    private String value;

    TypeEnum(String value) {
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
    public static TypeEnum fromValue(String value) {
      for (TypeEnum b : TypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public TypeEnum type;

  public VisualQueryItem() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public VisualQueryItem(String id, String label, Boolean outputEnabled, TypeEnum type) {
    this.id = id;
    this.label = label;
    this.outputEnabled = outputEnabled;
    this.type = type;
  }

  public VisualQueryItem conditions(List<@Valid VisualQueryCondition> conditions) {
    this.conditions = conditions;
    return this;
  }

  public VisualQueryItem addConditionsItem(VisualQueryCondition conditionsItem) {
    if (this.conditions == null) {
      this.conditions = new ArrayList<>();
    }
    this.conditions.add(conditionsItem);
    return this;
  }

  /**
   * The property conditions for the query item.
   * @return conditions
  */
  @Valid 
  @JsonProperty("conditions")
  public List<@Valid VisualQueryCondition> getConditions() {
    return conditions;
  }

  public void setConditions(List<@Valid VisualQueryCondition> conditions) {
    this.conditions = conditions;
  }

  public VisualQueryItem countCondition(VisualQueryCountCondition countCondition) {
    this.countCondition = countCondition;
    return this;
  }

  /**
   * Get countCondition
   * @return countCondition
  */
  @Valid 
  @JsonProperty("countCondition")
  public VisualQueryCountCondition getCountCondition() {
    return countCondition;
  }

  public void setCountCondition(VisualQueryCountCondition countCondition) {
    this.countCondition = countCondition;
  }

  public VisualQueryItem id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The identifier of the query item within the search structure.
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

  public VisualQueryItem itemTypeIds(List<String> itemTypeIds) {
    this.itemTypeIds = itemTypeIds;
    return this;
  }

  public VisualQueryItem addItemTypeIdsItem(String itemTypeIdsItem) {
    if (this.itemTypeIds == null) {
      this.itemTypeIds = new ArrayList<>();
    }
    this.itemTypeIds.add(itemTypeIdsItem);
    return this;
  }

  /**
   * The identifiers of the item types of records that can match the query item.
   * @return itemTypeIds
  */
  
  @JsonProperty("itemTypeIds")
  public List<String> getItemTypeIds() {
    return itemTypeIds;
  }

  public void setItemTypeIds(List<String> itemTypeIds) {
    this.itemTypeIds = itemTypeIds;
  }

  public VisualQueryItem label(String label) {
    this.label = label;
    return this;
  }

  /**
   * The label of the query item in the visual query editor.
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

  public VisualQueryItem outputEnabled(Boolean outputEnabled) {
    this.outputEnabled = outputEnabled;
    return this;
  }

  /**
   * Indicates whether records that match the query item are included in (**true**) or excluded from (**false**) the results.
   * @return outputEnabled
  */
  @NotNull 
  @JsonProperty("outputEnabled")
  public Boolean getOutputEnabled() {
    return outputEnabled;
  }

  public void setOutputEnabled(Boolean outputEnabled) {
    this.outputEnabled = outputEnabled;
  }

  public VisualQueryItem type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * The type of the query item, which is **QUERY_ENTITY** for a query entity, or **QUERY_LINK** for a query link.
   * @return type
  */
  @NotNull 
  @JsonProperty("type")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VisualQueryItem visualQueryItem = (VisualQueryItem) o;
    return Objects.equals(this.conditions, visualQueryItem.conditions) &&
        Objects.equals(this.countCondition, visualQueryItem.countCondition) &&
        Objects.equals(this.id, visualQueryItem.id) &&
        Objects.equals(this.itemTypeIds, visualQueryItem.itemTypeIds) &&
        Objects.equals(this.label, visualQueryItem.label) &&
        Objects.equals(this.outputEnabled, visualQueryItem.outputEnabled) &&
        Objects.equals(this.type, visualQueryItem.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(conditions, countCondition, id, itemTypeIds, label, outputEnabled, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VisualQueryItem {\n");
    sb.append("    conditions: ").append(toIndentedString(conditions)).append("\n");
    sb.append("    countCondition: ").append(toIndentedString(countCondition)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    itemTypeIds: ").append(toIndentedString(itemTypeIds)).append("\n");
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
    sb.append("    outputEnabled: ").append(toIndentedString(outputEnabled)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

