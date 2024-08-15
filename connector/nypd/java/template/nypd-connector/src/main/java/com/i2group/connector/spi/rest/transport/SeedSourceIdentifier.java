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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

/**
 * A source identifier in use as a seed identifier.
 */
public class SeedSourceIdentifier {

  @Deprecated
  public String itemTypeId;

  @Valid
  public List<String> key = new ArrayList<>();

  public String type;

  public SeedSourceIdentifier() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public SeedSourceIdentifier(List<String> key, String type) {
    this.key = key;
    this.type = type;
  }

  public SeedSourceIdentifier itemTypeId(String itemTypeId) {
    this.itemTypeId = itemTypeId;
    return this;
  }

  /**
   * The identifier of the item type of the record that has this source identifier.  **Deprecated:** This field will be removed in a future release. Use the owning record's **typeId** instead. Do not rely on the value of this field if it is set.
   * @return itemTypeId
   * @deprecated
  */
  
  @JsonProperty("itemTypeId")
  @Deprecated
  public String getItemTypeId() {
    return itemTypeId;
  }

  /**
   * @deprecated
  */
  @Deprecated
  public void setItemTypeId(String itemTypeId) {
    this.itemTypeId = itemTypeId;
  }

  public SeedSourceIdentifier key(List<String> key) {
    this.key = key;
    return this;
  }

  public SeedSourceIdentifier addKeyItem(String keyItem) {
    if (this.key == null) {
      this.key = new ArrayList<>();
    }
    this.key.add(keyItem);
    return this;
  }

  /**
   * Values that together identify some data in its original source.
   * @return key
  */
  @NotNull 
  @JsonProperty("key")
  public List<String> getKey() {
    return key;
  }

  public void setKey(List<String> key) {
    this.key = key;
  }

  public SeedSourceIdentifier type(String type) {
    this.type = type;
    return this;
  }

  /**
   * The type of this source identifier.
   * @return type
  */
  @NotNull 
  @JsonProperty("type")
  public String getType() {
    return type;
  }

  public void setType(String type) {
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
    SeedSourceIdentifier seedSourceIdentifier = (SeedSourceIdentifier) o;
    return Objects.equals(this.itemTypeId, seedSourceIdentifier.itemTypeId) &&
        Objects.equals(this.key, seedSourceIdentifier.key) &&
        Objects.equals(this.type, seedSourceIdentifier.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemTypeId, key, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SeedSourceIdentifier {\n");
    sb.append("    itemTypeId: ").append(toIndentedString(itemTypeId)).append("\n");
    sb.append("    key: ").append(toIndentedString(key)).append("\n");
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

