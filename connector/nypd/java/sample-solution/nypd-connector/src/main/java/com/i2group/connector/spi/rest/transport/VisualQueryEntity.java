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
import com.i2group.connector.spi.rest.transport.VisualQueryCondition;
import com.i2group.connector.spi.rest.transport.VisualQueryCountCondition;
import com.i2group.connector.spi.rest.transport.VisualQueryEntityPosition;
import com.i2group.connector.spi.rest.transport.VisualQueryItem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

/**
 * A query entity in a Visual Query structure.
 */
public class VisualQueryEntity extends VisualQueryItem {

  public VisualQueryEntityPosition position;

  public VisualQueryEntity() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public VisualQueryEntity(VisualQueryEntityPosition position, String id, String label, Boolean outputEnabled, TypeEnum type) {
    super(id, label, outputEnabled, type);
    this.position = position;
  }

  public VisualQueryEntity position(VisualQueryEntityPosition position) {
    this.position = position;
    return this;
  }

  /**
   * Get position
   * @return position
  */
  @NotNull @Valid 
  @JsonProperty("position")
  public VisualQueryEntityPosition getPosition() {
    return position;
  }

  public void setPosition(VisualQueryEntityPosition position) {
    this.position = position;
  }

  public VisualQueryEntity conditions(List<@Valid VisualQueryCondition> conditions) {
    super.conditions(conditions);
    return this;
  }

  public VisualQueryEntity addConditionsItem(VisualQueryCondition conditionsItem) {
    super.addConditionsItem(conditionsItem);
    return this;
  }

  public VisualQueryEntity countCondition(VisualQueryCountCondition countCondition) {
    super.countCondition(countCondition);
    return this;
  }

  public VisualQueryEntity id(String id) {
    super.id(id);
    return this;
  }

  public VisualQueryEntity itemTypeIds(List<String> itemTypeIds) {
    super.itemTypeIds(itemTypeIds);
    return this;
  }

  public VisualQueryEntity addItemTypeIdsItem(String itemTypeIdsItem) {
    super.addItemTypeIdsItem(itemTypeIdsItem);
    return this;
  }

  public VisualQueryEntity label(String label) {
    super.label(label);
    return this;
  }

  public VisualQueryEntity outputEnabled(Boolean outputEnabled) {
    super.outputEnabled(outputEnabled);
    return this;
  }

  public VisualQueryEntity type(TypeEnum type) {
    super.type(type);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VisualQueryEntity visualQueryEntity = (VisualQueryEntity) o;
    return Objects.equals(this.position, visualQueryEntity.position) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(position, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VisualQueryEntity {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    position: ").append(toIndentedString(position)).append("\n");
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

