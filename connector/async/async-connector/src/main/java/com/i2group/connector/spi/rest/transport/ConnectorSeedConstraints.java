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
import com.i2group.connector.spi.rest.transport.ConnectorSeedTypes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

/**
 * The constraints on the seeds that users can specify, or null if the service does not support seeds.
 */
public class ConnectorSeedConstraints {

  @Valid
  public List<String> connectorIds;

  public Integer max;

  public Integer min;

  public ConnectorSeedTypes seedTypes;

  public ConnectorSeedConstraints connectorIds(List<String> connectorIds) {
    this.connectorIds = connectorIds;
    return this;
  }

  public ConnectorSeedConstraints addConnectorIdsItem(String connectorIdsItem) {
    if (this.connectorIds == null) {
      this.connectorIds = new ArrayList<>();
    }
    this.connectorIds.add(connectorIdsItem);
    return this;
  }

  /**
   * The identifiers of the connectors from which seed records can originate. This setting is valid when **seedTypes** specifies item type constraints, but not when it specifies semantic constraints.
   * @return connectorIds
  */
  
  @JsonProperty("connectorIds")
  public List<String> getConnectorIds() {
    return connectorIds;
  }

  public void setConnectorIds(List<String> connectorIds) {
    this.connectorIds = connectorIds;
  }

  public ConnectorSeedConstraints max(Integer max) {
    this.max = max;
    return this;
  }

  /**
   * The maximum number of seed records that users must specify.
   * @return max
  */
  
  @JsonProperty("max")
  public Integer getMax() {
    return max;
  }

  public void setMax(Integer max) {
    this.max = max;
  }

  public ConnectorSeedConstraints min(Integer min) {
    this.min = min;
    return this;
  }

  /**
   * The minimum number of seed records that users must specify.
   * @return min
  */
  
  @JsonProperty("min")
  public Integer getMin() {
    return min;
  }

  public void setMin(Integer min) {
    this.min = min;
  }

  public ConnectorSeedConstraints seedTypes(ConnectorSeedTypes seedTypes) {
    this.seedTypes = seedTypes;
    return this;
  }

  /**
   * Get seedTypes
   * @return seedTypes
  */
  @Valid 
  @JsonProperty("seedTypes")
  public ConnectorSeedTypes getSeedTypes() {
    return seedTypes;
  }

  public void setSeedTypes(ConnectorSeedTypes seedTypes) {
    this.seedTypes = seedTypes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConnectorSeedConstraints connectorSeedConstraints = (ConnectorSeedConstraints) o;
    return Objects.equals(this.connectorIds, connectorSeedConstraints.connectorIds) &&
        Objects.equals(this.max, connectorSeedConstraints.max) &&
        Objects.equals(this.min, connectorSeedConstraints.min) &&
        Objects.equals(this.seedTypes, connectorSeedConstraints.seedTypes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(connectorIds, max, min, seedTypes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConnectorSeedConstraints {\n");
    sb.append("    connectorIds: ").append(toIndentedString(connectorIds)).append("\n");
    sb.append("    max: ").append(toIndentedString(max)).append("\n");
    sb.append("    min: ").append(toIndentedString(min)).append("\n");
    sb.append("    seedTypes: ").append(toIndentedString(seedTypes)).append("\n");
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

