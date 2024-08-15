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
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.LinkedHashSet;
import java.util.Set;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

/**
 * A set of values from a particular security dimension.
 */
public class SecurityDimensionAndValues {

  public String dimensionId;

  @Valid
  public Set<String> ids;

  public SecurityDimensionAndValues dimensionId(String dimensionId) {
    this.dimensionId = dimensionId;
    return this;
  }

  /**
   * The identifier of the security dimension that has the values in **ids**.
   * @return dimensionId
  */
  
  @JsonProperty("dimensionId")
  public String getDimensionId() {
    return dimensionId;
  }

  public void setDimensionId(String dimensionId) {
    this.dimensionId = dimensionId;
  }

  public SecurityDimensionAndValues ids(Set<String> ids) {
    this.ids = ids;
    return this;
  }

  public SecurityDimensionAndValues addIdsItem(String idsItem) {
    if (this.ids == null) {
      this.ids = new LinkedHashSet<>();
    }
    this.ids.add(idsItem);
    return this;
  }

  /**
   * The identifiers of values in the security dimension with **dimensionId**.
   * @return ids
  */
  
  @JsonProperty("ids")
  public Set<String> getIds() {
    return ids;
  }

  @JsonDeserialize(as = LinkedHashSet.class)
  public void setIds(Set<String> ids) {
    this.ids = ids;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SecurityDimensionAndValues securityDimensionAndValues = (SecurityDimensionAndValues) o;
    return Objects.equals(this.dimensionId, securityDimensionAndValues.dimensionId) &&
        Objects.equals(this.ids, securityDimensionAndValues.ids);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dimensionId, ids);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SecurityDimensionAndValues {\n");
    sb.append("    dimensionId: ").append(toIndentedString(dimensionId)).append("\n");
    sb.append("    ids: ").append(toIndentedString(ids)).append("\n");
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

