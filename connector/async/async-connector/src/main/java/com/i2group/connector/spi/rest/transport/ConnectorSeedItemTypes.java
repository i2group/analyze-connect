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
import com.i2group.connector.spi.rest.transport.ConnectorSeedConstraintItemType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

/**
 * The item types that records must have in order to be used as seeds.
 */
public class ConnectorSeedItemTypes implements ConnectorSeedTypes {

  /**
   * The subset of item types by which a seed record can be constrained.
   */
  public enum AllowedTypesEnum {
    ENTITY("ENTITY"),
    
    LINK("LINK");

    private String value;

    AllowedTypesEnum(String value) {
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
    public static AllowedTypesEnum fromValue(String value) {
      for (AllowedTypesEnum b : AllowedTypesEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public AllowedTypesEnum allowedTypes;

  @Valid
  public List<@Valid ConnectorSeedConstraintItemType> itemTypes;

  public ConnectorSeedItemTypes allowedTypes(AllowedTypesEnum allowedTypes) {
    this.allowedTypes = allowedTypes;
    return this;
  }

  /**
   * The subset of item types by which a seed record can be constrained.
   * @return allowedTypes
  */
  
  @JsonProperty("allowedTypes")
  public AllowedTypesEnum getAllowedTypes() {
    return allowedTypes;
  }

  public void setAllowedTypes(AllowedTypesEnum allowedTypes) {
    this.allowedTypes = allowedTypes;
  }

  public ConnectorSeedItemTypes itemTypes(List<@Valid ConnectorSeedConstraintItemType> itemTypes) {
    this.itemTypes = itemTypes;
    return this;
  }

  public ConnectorSeedItemTypes addItemTypesItem(ConnectorSeedConstraintItemType itemTypesItem) {
    if (this.itemTypes == null) {
      this.itemTypes = new ArrayList<>();
    }
    this.itemTypes.add(itemTypesItem);
    return this;
  }

  /**
   * The item type constraints, which are limited to the subset in **allowedTypes**.
   * @return itemTypes
  */
  @Valid 
  @JsonProperty("itemTypes")
  public List<@Valid ConnectorSeedConstraintItemType> getItemTypes() {
    return itemTypes;
  }

  public void setItemTypes(List<@Valid ConnectorSeedConstraintItemType> itemTypes) {
    this.itemTypes = itemTypes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConnectorSeedItemTypes connectorSeedItemTypes = (ConnectorSeedItemTypes) o;
    return Objects.equals(this.allowedTypes, connectorSeedItemTypes.allowedTypes) &&
        Objects.equals(this.itemTypes, connectorSeedItemTypes.itemTypes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(allowedTypes, itemTypes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConnectorSeedItemTypes {\n");
    sb.append("    allowedTypes: ").append(toIndentedString(allowedTypes)).append("\n");
    sb.append("    itemTypes: ").append(toIndentedString(itemTypes)).append("\n");
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

