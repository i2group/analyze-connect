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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

/**
 * The specification of which item types appear in the Visual Query palette.
 */
public class VisualQueryPaletteItemTypes {

  /**
   * Indicates whether to allow or deny the identified item types in the palette.
   */
  public enum ModeEnum {
    ALLOW("ALLOW"),
    
    DENY("DENY");

    private String value;

    ModeEnum(String value) {
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
    public static ModeEnum fromValue(String value) {
      for (ModeEnum b : ModeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public ModeEnum mode;

  @Valid
  public List<String> itemTypeIds = new ArrayList<>();

  public VisualQueryPaletteItemTypes() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public VisualQueryPaletteItemTypes(ModeEnum mode, List<String> itemTypeIds) {
    this.mode = mode;
    this.itemTypeIds = itemTypeIds;
  }

  public VisualQueryPaletteItemTypes mode(ModeEnum mode) {
    this.mode = mode;
    return this;
  }

  /**
   * Indicates whether to allow or deny the identified item types in the palette.
   * @return mode
  */
  @NotNull 
  @JsonProperty("mode")
  public ModeEnum getMode() {
    return mode;
  }

  public void setMode(ModeEnum mode) {
    this.mode = mode;
  }

  public VisualQueryPaletteItemTypes itemTypeIds(List<String> itemTypeIds) {
    this.itemTypeIds = itemTypeIds;
    return this;
  }

  public VisualQueryPaletteItemTypes addItemTypeIdsItem(String itemTypeIdsItem) {
    if (this.itemTypeIds == null) {
      this.itemTypeIds = new ArrayList<>();
    }
    this.itemTypeIds.add(itemTypeIdsItem);
    return this;
  }

  /**
   * The identifiers of the item types that the specification affects.
   * @return itemTypeIds
  */
  @NotNull 
  @JsonProperty("itemTypeIds")
  public List<String> getItemTypeIds() {
    return itemTypeIds;
  }

  public void setItemTypeIds(List<String> itemTypeIds) {
    this.itemTypeIds = itemTypeIds;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VisualQueryPaletteItemTypes visualQueryPaletteItemTypes = (VisualQueryPaletteItemTypes) o;
    return Objects.equals(this.mode, visualQueryPaletteItemTypes.mode) &&
        Objects.equals(this.itemTypeIds, visualQueryPaletteItemTypes.itemTypeIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mode, itemTypeIds);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VisualQueryPaletteItemTypes {\n");
    sb.append("    mode: ").append(toIndentedString(mode)).append("\n");
    sb.append("    itemTypeIds: ").append(toIndentedString(itemTypeIds)).append("\n");
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

