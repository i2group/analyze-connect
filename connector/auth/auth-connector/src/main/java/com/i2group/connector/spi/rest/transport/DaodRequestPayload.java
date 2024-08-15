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
import com.i2group.connector.spi.rest.transport.DaodRequestCondition;
import com.i2group.connector.spi.rest.transport.DaodSeeds;
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
 * A custom payload that an i2 Connect service can use to determine what data to retrieve from a connected source.
 */
public class DaodRequestPayload {

  @Valid
  public List<@Valid DaodRequestCondition> conditions;

  public DaodSeeds seeds;

  @Valid
  public List<@Valid VisualQueryItem> visualQuery;

  public DaodRequestPayload conditions(List<@Valid DaodRequestCondition> conditions) {
    this.conditions = conditions;
    return this;
  }

  public DaodRequestPayload addConditionsItem(DaodRequestCondition conditionsItem) {
    if (this.conditions == null) {
      this.conditions = new ArrayList<>();
    }
    this.conditions.add(conditionsItem);
    return this;
  }

  /**
   * If the service uses a client configuration of type 'FORM', the conditions that a user has specified to refine their query.
   * @return conditions
  */
  @Valid 
  @JsonProperty("conditions")
  public List<@Valid DaodRequestCondition> getConditions() {
    return conditions;
  }

  public void setConditions(List<@Valid DaodRequestCondition> conditions) {
    this.conditions = conditions;
  }

  public DaodRequestPayload seeds(DaodSeeds seeds) {
    this.seeds = seeds;
    return this;
  }

  /**
   * Get seeds
   * @return seeds
  */
  @Valid 
  @JsonProperty("seeds")
  public DaodSeeds getSeeds() {
    return seeds;
  }

  public void setSeeds(DaodSeeds seeds) {
    this.seeds = seeds;
  }

  public DaodRequestPayload visualQuery(List<@Valid VisualQueryItem> visualQuery) {
    this.visualQuery = visualQuery;
    return this;
  }

  public DaodRequestPayload addVisualQueryItem(VisualQueryItem visualQueryItem) {
    if (this.visualQuery == null) {
      this.visualQuery = new ArrayList<>();
    }
    this.visualQuery.add(visualQueryItem);
    return this;
  }

  /**
   * If the service uses a client configuration of type 'VISUAL_QUERY', the Visual Query to execute, represented as an array of query items.
   * @return visualQuery
  */
  @Valid 
  @JsonProperty("visualQuery")
  public List<@Valid VisualQueryItem> getVisualQuery() {
    return visualQuery;
  }

  public void setVisualQuery(List<@Valid VisualQueryItem> visualQuery) {
    this.visualQuery = visualQuery;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DaodRequestPayload daodRequestPayload = (DaodRequestPayload) o;
    return Objects.equals(this.conditions, daodRequestPayload.conditions) &&
        Objects.equals(this.seeds, daodRequestPayload.seeds) &&
        Objects.equals(this.visualQuery, daodRequestPayload.visualQuery);
  }

  @Override
  public int hashCode() {
    return Objects.hash(conditions, seeds, visualQuery);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DaodRequestPayload {\n");
    sb.append("    conditions: ").append(toIndentedString(conditions)).append("\n");
    sb.append("    seeds: ").append(toIndentedString(seeds)).append("\n");
    sb.append("    visualQuery: ").append(toIndentedString(visualQuery)).append("\n");
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

