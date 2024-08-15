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
import com.i2group.connector.spi.rest.transport.PropertyType;
import com.i2group.connector.spi.rest.transport.TypeLocation;
import java.util.HashMap;
import java.util.Map;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

/**
 * An item type from an i2 Analyze schema.
 */
public class ItemType {

  public String displayName;

  @Valid
  public Map<String, PropertyType> propertyTypes = new HashMap<>();

  public String semanticTypeId;

  public String typeId;

  public TypeLocation typeLocation;

  public ItemType displayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  /**
   * The display name for this item type.
   * @return displayName
  */
  
  @JsonProperty("displayName")
  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public ItemType propertyTypes(Map<String, PropertyType> propertyTypes) {
    this.propertyTypes = propertyTypes;
    return this;
  }

  public ItemType putPropertyTypesItem(String key, PropertyType propertyTypesItem) {
    if (this.propertyTypes == null) {
      this.propertyTypes = new HashMap<>();
    }
    this.propertyTypes.put(key, propertyTypesItem);
    return this;
  }

  /**
   * The property types of this item type.
   * @return propertyTypes
  */
  @Valid 
  @JsonProperty("propertyTypes")
  public Map<String, PropertyType> getPropertyTypes() {
    return propertyTypes;
  }

  public void setPropertyTypes(Map<String, PropertyType> propertyTypes) {
    this.propertyTypes = propertyTypes;
  }

  public ItemType semanticTypeId(String semanticTypeId) {
    this.semanticTypeId = semanticTypeId;
    return this;
  }

  /**
   * The identifier of the semantic type for this item type.
   * @return semanticTypeId
  */
  
  @JsonProperty("semanticTypeId")
  public String getSemanticTypeId() {
    return semanticTypeId;
  }

  public void setSemanticTypeId(String semanticTypeId) {
    this.semanticTypeId = semanticTypeId;
  }

  public ItemType typeId(String typeId) {
    this.typeId = typeId;
    return this;
  }

  /**
   * The identifier of this item type.
   * @return typeId
  */
  
  @JsonProperty("typeId")
  public String getTypeId() {
    return typeId;
  }

  public void setTypeId(String typeId) {
    this.typeId = typeId;
  }

  public ItemType typeLocation(TypeLocation typeLocation) {
    this.typeLocation = typeLocation;
    return this;
  }

  /**
   * Get typeLocation
   * @return typeLocation
  */
  @Valid 
  @JsonProperty("typeLocation")
  public TypeLocation getTypeLocation() {
    return typeLocation;
  }

  public void setTypeLocation(TypeLocation typeLocation) {
    this.typeLocation = typeLocation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ItemType itemType = (ItemType) o;
    return Objects.equals(this.displayName, itemType.displayName) &&
        Objects.equals(this.propertyTypes, itemType.propertyTypes) &&
        Objects.equals(this.semanticTypeId, itemType.semanticTypeId) &&
        Objects.equals(this.typeId, itemType.typeId) &&
        Objects.equals(this.typeLocation, itemType.typeLocation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(displayName, propertyTypes, semanticTypeId, typeId, typeLocation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ItemType {\n");
    sb.append("    displayName: ").append(toIndentedString(displayName)).append("\n");
    sb.append("    propertyTypes: ").append(toIndentedString(propertyTypes)).append("\n");
    sb.append("    semanticTypeId: ").append(toIndentedString(semanticTypeId)).append("\n");
    sb.append("    typeId: ").append(toIndentedString(typeId)).append("\n");
    sb.append("    typeLocation: ").append(toIndentedString(typeLocation)).append("\n");
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

