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
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

/**
 * The constraints that apply to Visual Query structures.
 */
public class VisualQueryConstraints {

  public Boolean outputSelectionSupported = true;

  public Integer maximumCountConditions = 1;

  public Integer maximumQueryLinkCount;

  public Integer maximumAnyEntityTypeSupported;

  public Integer maximumAnyLinkTypeSupported;

  public VisualQueryConstraints outputSelectionSupported(Boolean outputSelectionSupported) {
    this.outputSelectionSupported = outputSelectionSupported;
    return this;
  }

  /**
   * Indicates whether query items can be marked for output, so that matching records appear in search results. When this is **false**, the connector implementation controls which records appear.
   * @return outputSelectionSupported
  */
  
  @JsonProperty("outputSelectionSupported")
  public Boolean getOutputSelectionSupported() {
    return outputSelectionSupported;
  }

  public void setOutputSelectionSupported(Boolean outputSelectionSupported) {
    this.outputSelectionSupported = outputSelectionSupported;
  }

  public VisualQueryConstraints maximumCountConditions(Integer maximumCountConditions) {
    this.maximumCountConditions = maximumCountConditions;
    return this;
  }

  /**
   * The maximum number of query items that can have count conditions.
   * minimum: 0
   * @return maximumCountConditions
  */
  @Min(0) 
  @JsonProperty("maximumCountConditions")
  public Integer getMaximumCountConditions() {
    return maximumCountConditions;
  }

  public void setMaximumCountConditions(Integer maximumCountConditions) {
    this.maximumCountConditions = maximumCountConditions;
  }

  public VisualQueryConstraints maximumQueryLinkCount(Integer maximumQueryLinkCount) {
    this.maximumQueryLinkCount = maximumQueryLinkCount;
    return this;
  }

  /**
   * The maximum number of query links in a single Visual Query.
   * minimum: 0
   * @return maximumQueryLinkCount
  */
  @Min(0) 
  @JsonProperty("maximumQueryLinkCount")
  public Integer getMaximumQueryLinkCount() {
    return maximumQueryLinkCount;
  }

  public void setMaximumQueryLinkCount(Integer maximumQueryLinkCount) {
    this.maximumQueryLinkCount = maximumQueryLinkCount;
  }

  public VisualQueryConstraints maximumAnyEntityTypeSupported(Integer maximumAnyEntityTypeSupported) {
    this.maximumAnyEntityTypeSupported = maximumAnyEntityTypeSupported;
    return this;
  }

  /**
   * The maximum number of query entities that can have the 'Any' type.
   * minimum: 0
   * @return maximumAnyEntityTypeSupported
  */
  @Min(0) 
  @JsonProperty("maximumAnyEntityTypeSupported")
  public Integer getMaximumAnyEntityTypeSupported() {
    return maximumAnyEntityTypeSupported;
  }

  public void setMaximumAnyEntityTypeSupported(Integer maximumAnyEntityTypeSupported) {
    this.maximumAnyEntityTypeSupported = maximumAnyEntityTypeSupported;
  }

  public VisualQueryConstraints maximumAnyLinkTypeSupported(Integer maximumAnyLinkTypeSupported) {
    this.maximumAnyLinkTypeSupported = maximumAnyLinkTypeSupported;
    return this;
  }

  /**
   * The maximum number of query links that can have the 'Any' type.
   * minimum: 0
   * @return maximumAnyLinkTypeSupported
  */
  @Min(0) 
  @JsonProperty("maximumAnyLinkTypeSupported")
  public Integer getMaximumAnyLinkTypeSupported() {
    return maximumAnyLinkTypeSupported;
  }

  public void setMaximumAnyLinkTypeSupported(Integer maximumAnyLinkTypeSupported) {
    this.maximumAnyLinkTypeSupported = maximumAnyLinkTypeSupported;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VisualQueryConstraints visualQueryConstraints = (VisualQueryConstraints) o;
    return Objects.equals(this.outputSelectionSupported, visualQueryConstraints.outputSelectionSupported) &&
        Objects.equals(this.maximumCountConditions, visualQueryConstraints.maximumCountConditions) &&
        Objects.equals(this.maximumQueryLinkCount, visualQueryConstraints.maximumQueryLinkCount) &&
        Objects.equals(this.maximumAnyEntityTypeSupported, visualQueryConstraints.maximumAnyEntityTypeSupported) &&
        Objects.equals(this.maximumAnyLinkTypeSupported, visualQueryConstraints.maximumAnyLinkTypeSupported);
  }

  @Override
  public int hashCode() {
    return Objects.hash(outputSelectionSupported, maximumCountConditions, maximumQueryLinkCount, maximumAnyEntityTypeSupported, maximumAnyLinkTypeSupported);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VisualQueryConstraints {\n");
    sb.append("    outputSelectionSupported: ").append(toIndentedString(outputSelectionSupported)).append("\n");
    sb.append("    maximumCountConditions: ").append(toIndentedString(maximumCountConditions)).append("\n");
    sb.append("    maximumQueryLinkCount: ").append(toIndentedString(maximumQueryLinkCount)).append("\n");
    sb.append("    maximumAnyEntityTypeSupported: ").append(toIndentedString(maximumAnyEntityTypeSupported)).append("\n");
    sb.append("    maximumAnyLinkTypeSupported: ").append(toIndentedString(maximumAnyLinkTypeSupported)).append("\n");
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

