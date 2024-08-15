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
import com.i2group.connector.spi.rest.transport.LinkDirection;
import com.i2group.connector.spi.rest.transport.VisualQueryCondition;
import com.i2group.connector.spi.rest.transport.VisualQueryCountCondition;
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
 * A query link in a Visual Query structure.
 */
public class VisualQueryLink extends VisualQueryItem {

  public LinkDirection direction;

  public String fromId;

  public String toId;

  public VisualQueryLink() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public VisualQueryLink(String fromId, String toId, String id, String label, Boolean outputEnabled, TypeEnum type) {
    super(id, label, outputEnabled, type);
    this.fromId = fromId;
    this.toId = toId;
  }

  public VisualQueryLink direction(LinkDirection direction) {
    this.direction = direction;
    return this;
  }

  /**
   * Get direction
   * @return direction
  */
  @Valid 
  @JsonProperty("direction")
  public LinkDirection getDirection() {
    return direction;
  }

  public void setDirection(LinkDirection direction) {
    this.direction = direction;
  }

  public VisualQueryLink fromId(String fromId) {
    this.fromId = fromId;
    return this;
  }

  /**
   * The identifier of the query entity at the \"from\" end of this query link.
   * @return fromId
  */
  @NotNull 
  @JsonProperty("fromId")
  public String getFromId() {
    return fromId;
  }

  public void setFromId(String fromId) {
    this.fromId = fromId;
  }

  public VisualQueryLink toId(String toId) {
    this.toId = toId;
    return this;
  }

  /**
   * The identifier of the query entity at the \"to\" end of this query link.
   * @return toId
  */
  @NotNull 
  @JsonProperty("toId")
  public String getToId() {
    return toId;
  }

  public void setToId(String toId) {
    this.toId = toId;
  }

  public VisualQueryLink conditions(List<@Valid VisualQueryCondition> conditions) {
    super.conditions(conditions);
    return this;
  }

  public VisualQueryLink addConditionsItem(VisualQueryCondition conditionsItem) {
    super.addConditionsItem(conditionsItem);
    return this;
  }

  public VisualQueryLink countCondition(VisualQueryCountCondition countCondition) {
    super.countCondition(countCondition);
    return this;
  }

  public VisualQueryLink id(String id) {
    super.id(id);
    return this;
  }

  public VisualQueryLink itemTypeIds(List<String> itemTypeIds) {
    super.itemTypeIds(itemTypeIds);
    return this;
  }

  public VisualQueryLink addItemTypeIdsItem(String itemTypeIdsItem) {
    super.addItemTypeIdsItem(itemTypeIdsItem);
    return this;
  }

  public VisualQueryLink label(String label) {
    super.label(label);
    return this;
  }

  public VisualQueryLink outputEnabled(Boolean outputEnabled) {
    super.outputEnabled(outputEnabled);
    return this;
  }

  public VisualQueryLink type(TypeEnum type) {
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
    VisualQueryLink visualQueryLink = (VisualQueryLink) o;
    return Objects.equals(this.direction, visualQueryLink.direction) &&
        Objects.equals(this.fromId, visualQueryLink.fromId) &&
        Objects.equals(this.toId, visualQueryLink.toId) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(direction, fromId, toId, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VisualQueryLink {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    direction: ").append(toIndentedString(direction)).append("\n");
    sb.append("    fromId: ").append(toIndentedString(fromId)).append("\n");
    sb.append("    toId: ").append(toIndentedString(toId)).append("\n");
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

