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
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

/**
 * The maximum length of a string in a condition, expressed as a raw size with its units.
 */
public class MaxStringLength {

  public Long size;

  /**
   * The units in which **size** is interpreted.
   */
  public enum UnitsEnum {
    UTF16CODEUNITS("utf16codeunits"),
    
    UTF8BYTES("utf8bytes");

    private String value;

    UnitsEnum(String value) {
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
    public static UnitsEnum fromValue(String value) {
      for (UnitsEnum b : UnitsEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public UnitsEnum units = UnitsEnum.UTF16CODEUNITS;

  public MaxStringLength() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public MaxStringLength(Long size) {
    this.size = size;
  }

  public MaxStringLength size(Long size) {
    this.size = size;
    return this;
  }

  /**
   * The size of the longest permitted string.
   * @return size
  */
  @NotNull 
  @JsonProperty("size")
  public Long getSize() {
    return size;
  }

  public void setSize(Long size) {
    this.size = size;
  }

  public MaxStringLength units(UnitsEnum units) {
    this.units = units;
    return this;
  }

  /**
   * The units in which **size** is interpreted.
   * @return units
  */
  
  @JsonProperty("units")
  public UnitsEnum getUnits() {
    return units;
  }

  public void setUnits(UnitsEnum units) {
    this.units = units;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MaxStringLength maxStringLength = (MaxStringLength) o;
    return Objects.equals(this.size, maxStringLength.size) &&
        Objects.equals(this.units, maxStringLength.units);
  }

  @Override
  public int hashCode() {
    return Objects.hash(size, units);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MaxStringLength {\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
    sb.append("    units: ").append(toIndentedString(units)).append("\n");
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

