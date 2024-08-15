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
import com.i2group.connector.spi.rest.transport.SecurityDimensionAndValues;
import com.i2group.connector.spi.rest.transport.SeedSourceIdentifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

/**
 * Data from a record to be used as a seed in a search operation.
 */
public class DaodSeedItemData {

  @Valid
  public List<@Valid SeedSourceIdentifier> sourceIds = new ArrayList<>();

  public String seedId;

  public String typeId;

  public String label;

  @Valid
  public Map<String, Object> properties = new HashMap<>();

  @Valid
  public List<@Valid SecurityDimensionAndValues> accessDimensionValues;

  @Valid
  public Map<String, Object> extensions = new HashMap<>();

  public DaodSeedItemData() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public DaodSeedItemData(List<@Valid SeedSourceIdentifier> sourceIds, String seedId, String typeId) {
    this.sourceIds = sourceIds;
    this.seedId = seedId;
    this.typeId = typeId;
  }

  public DaodSeedItemData sourceIds(List<@Valid SeedSourceIdentifier> sourceIds) {
    this.sourceIds = sourceIds;
    return this;
  }

  public DaodSeedItemData addSourceIdsItem(SeedSourceIdentifier sourceIdsItem) {
    if (this.sourceIds == null) {
      this.sourceIds = new ArrayList<>();
    }
    this.sourceIds.add(sourceIdsItem);
    return this;
  }

  /**
   * The source identifiers of a seed record.
   * @return sourceIds
  */
  @NotNull @Valid 
  @JsonProperty("sourceIds")
  public List<@Valid SeedSourceIdentifier> getSourceIds() {
    return sourceIds;
  }

  public void setSourceIds(List<@Valid SeedSourceIdentifier> sourceIds) {
    this.sourceIds = sourceIds;
  }

  public DaodSeedItemData seedId(String seedId) {
    this.seedId = seedId;
    return this;
  }

  /**
   * The identifier of a seed record. If the seed was provided by Analyst's Notebook Premium or i2 Notebook Web, this is an i2 Analyze record identifier.
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

  public DaodSeedItemData typeId(String typeId) {
    this.typeId = typeId;
    return this;
  }

  /**
   * The type identifier of the record identified by **seedId**.
   * @return typeId
  */
  @NotNull 
  @JsonProperty("typeId")
  public String getTypeId() {
    return typeId;
  }

  public void setTypeId(String typeId) {
    this.typeId = typeId;
  }

  public DaodSeedItemData label(String label) {
    this.label = label;
    return this;
  }

  /**
   * The label of the record identified by **seedId**.
   * @return label
  */
  
  @JsonProperty("label")
  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public DaodSeedItemData properties(Map<String, Object> properties) {
    this.properties = properties;
    return this;
  }

  public DaodSeedItemData putPropertiesItem(String key, Object propertiesItem) {
    if (this.properties == null) {
      this.properties = new HashMap<>();
    }
    this.properties.put(key, propertiesItem);
    return this;
  }

  /**
   * The property data of the record identified by **seedId**.
   * @return properties
  */
  
  @JsonProperty("properties")
  public Map<String, Object> getProperties() {
    return properties;
  }

  public void setProperties(Map<String, Object> properties) {
    this.properties = properties;
  }

  public DaodSeedItemData accessDimensionValues(List<@Valid SecurityDimensionAndValues> accessDimensionValues) {
    this.accessDimensionValues = accessDimensionValues;
    return this;
  }

  public DaodSeedItemData addAccessDimensionValuesItem(SecurityDimensionAndValues accessDimensionValuesItem) {
    if (this.accessDimensionValues == null) {
      this.accessDimensionValues = new ArrayList<>();
    }
    this.accessDimensionValues.add(accessDimensionValuesItem);
    return this;
  }

  /**
   * The security dimension values of the record identified by **seedId**.
   * @return accessDimensionValues
  */
  @Valid 
  @JsonProperty("accessDimensionValues")
  public List<@Valid SecurityDimensionAndValues> getAccessDimensionValues() {
    return accessDimensionValues;
  }

  public void setAccessDimensionValues(List<@Valid SecurityDimensionAndValues> accessDimensionValues) {
    this.accessDimensionValues = accessDimensionValues;
  }

  public DaodSeedItemData extensions(Map<String, Object> extensions) {
    this.extensions = extensions;
    return this;
  }

  public DaodSeedItemData putExtensionsItem(String key, Object extensionsItem) {
    if (this.extensions == null) {
      this.extensions = new HashMap<>();
    }
    this.extensions.put(key, extensionsItem);
    return this;
  }

  /**
   * Free-form, custom information for the record identified by **seedId**.
   * @return extensions
  */
  
  @JsonProperty("extensions")
  public Map<String, Object> getExtensions() {
    return extensions;
  }

  public void setExtensions(Map<String, Object> extensions) {
    this.extensions = extensions;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DaodSeedItemData daodSeedItemData = (DaodSeedItemData) o;
    return Objects.equals(this.sourceIds, daodSeedItemData.sourceIds) &&
        Objects.equals(this.seedId, daodSeedItemData.seedId) &&
        Objects.equals(this.typeId, daodSeedItemData.typeId) &&
        Objects.equals(this.label, daodSeedItemData.label) &&
        Objects.equals(this.properties, daodSeedItemData.properties) &&
        Objects.equals(this.accessDimensionValues, daodSeedItemData.accessDimensionValues) &&
        Objects.equals(this.extensions, daodSeedItemData.extensions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sourceIds, seedId, typeId, label, properties, accessDimensionValues, extensions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DaodSeedItemData {\n");
    sb.append("    sourceIds: ").append(toIndentedString(sourceIds)).append("\n");
    sb.append("    seedId: ").append(toIndentedString(seedId)).append("\n");
    sb.append("    typeId: ").append(toIndentedString(typeId)).append("\n");
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
    sb.append("    properties: ").append(toIndentedString(properties)).append("\n");
    sb.append("    accessDimensionValues: ").append(toIndentedString(accessDimensionValues)).append("\n");
    sb.append("    extensions: ").append(toIndentedString(extensions)).append("\n");
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

