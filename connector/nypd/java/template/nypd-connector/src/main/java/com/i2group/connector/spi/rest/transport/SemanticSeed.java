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
import com.i2group.connector.spi.rest.transport.SemanticProperty;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

/**
 * The properties of a record in use as a semantic seed.
 */
public class SemanticSeed {

  public String seedId;

  public Boolean isLink = false;

  @Valid
  public Map<String, Set<@Valid SemanticProperty>> properties = new HashMap<>();

  public SemanticSeed() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public SemanticSeed(String seedId, Map<String, Set<@Valid SemanticProperty>> properties) {
    this.seedId = seedId;
    this.properties = properties;
  }

  public SemanticSeed seedId(String seedId) {
    this.seedId = seedId;
    return this;
  }

  /**
   * The identifier of the record that contains the semantic property values.
   * @return seedId
  */
  @NotNull 
  @JsonProperty("seedId")
  public String getSeedId() {
    return seedId;
  }

  public void setSeedId(String seedId) {
    this.seedId = seedId;
  }

  public SemanticSeed isLink(Boolean isLink) {
    this.isLink = isLink;
    return this;
  }

  /**
   * Indicates whether the record identified in **seedId** is a link.
   * @return isLink
  */
  
  @JsonProperty("isLink")
  public Boolean getIsLink() {
    return isLink;
  }

  public void setIsLink(Boolean isLink) {
    this.isLink = isLink;
  }

  public SemanticSeed properties(Map<String, Set<@Valid SemanticProperty>> properties) {
    this.properties = properties;
    return this;
  }

  public SemanticSeed putPropertiesItem(String key, Set<@Valid SemanticProperty> propertiesItem) {
    if (this.properties == null) {
      this.properties = new HashMap<>();
    }
    this.properties.put(key, propertiesItem);
    return this;
  }

  /**
   * A map of property values, keyed by their semantic type GUIDs.
   * @return properties
  */
  @NotNull @Valid 
  @JsonProperty("properties")
  public Map<String, Set<@Valid SemanticProperty>> getProperties() {
    return properties;
  }

  public void setProperties(Map<String, Set<@Valid SemanticProperty>> properties) {
    this.properties = properties;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SemanticSeed semanticSeed = (SemanticSeed) o;
    return Objects.equals(this.seedId, semanticSeed.seedId) &&
        Objects.equals(this.isLink, semanticSeed.isLink) &&
        Objects.equals(this.properties, semanticSeed.properties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(seedId, isLink, properties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SemanticSeed {\n");
    sb.append("    seedId: ").append(toIndentedString(seedId)).append("\n");
    sb.append("    isLink: ").append(toIndentedString(isLink)).append("\n");
    sb.append("    properties: ").append(toIndentedString(properties)).append("\n");
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

