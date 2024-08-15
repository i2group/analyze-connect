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
import com.i2group.connector.spi.rest.transport.SecurityDimensionAndValues;
import com.i2group.connector.spi.rest.transport.SeedSourceIdentifier;
import com.i2group.connector.spi.rest.transport.TypeLocation;
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
 * The data from a link record to be used as a seed in a search operation.
 */
public class DaodSeedLinkData {

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

  public Object fromEndId;

  public String fromEndTypeId;

  public LinkDirection linkDirection;

  public Object toEndId;

  public String toEndTypeId;

  public TypeLocation typeLocation;

  public DaodSeedLinkData() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public DaodSeedLinkData(List<@Valid SeedSourceIdentifier> sourceIds, String seedId, String typeId, Object fromEndId, String fromEndTypeId, LinkDirection linkDirection, Object toEndId, String toEndTypeId) {
    this.sourceIds = sourceIds;
    this.seedId = seedId;
    this.typeId = typeId;
    this.fromEndId = fromEndId;
    this.fromEndTypeId = fromEndTypeId;
    this.linkDirection = linkDirection;
    this.toEndId = toEndId;
    this.toEndTypeId = toEndTypeId;
  }

  public DaodSeedLinkData sourceIds(List<@Valid SeedSourceIdentifier> sourceIds) {
    this.sourceIds = sourceIds;
    return this;
  }

  public DaodSeedLinkData addSourceIdsItem(SeedSourceIdentifier sourceIdsItem) {
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

  public DaodSeedLinkData seedId(String seedId) {
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

  public DaodSeedLinkData typeId(String typeId) {
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

  public DaodSeedLinkData label(String label) {
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

  public DaodSeedLinkData properties(Map<String, Object> properties) {
    this.properties = properties;
    return this;
  }

  public DaodSeedLinkData putPropertiesItem(String key, Object propertiesItem) {
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

  public DaodSeedLinkData accessDimensionValues(List<@Valid SecurityDimensionAndValues> accessDimensionValues) {
    this.accessDimensionValues = accessDimensionValues;
    return this;
  }

  public DaodSeedLinkData addAccessDimensionValuesItem(SecurityDimensionAndValues accessDimensionValuesItem) {
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

  public DaodSeedLinkData extensions(Map<String, Object> extensions) {
    this.extensions = extensions;
    return this;
  }

  public DaodSeedLinkData putExtensionsItem(String key, Object extensionsItem) {
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

  public DaodSeedLinkData fromEndId(Object fromEndId) {
    this.fromEndId = fromEndId;
    return this;
  }

  /**
   * The identifier of the record at the 'from' end of the link record identified by **seedId**.
   * @return fromEndId
  */
  @NotNull 
  @JsonProperty("fromEndId")
  public Object getFromEndId() {
    return fromEndId;
  }

  public void setFromEndId(Object fromEndId) {
    this.fromEndId = fromEndId;
  }

  public DaodSeedLinkData fromEndTypeId(String fromEndTypeId) {
    this.fromEndTypeId = fromEndTypeId;
    return this;
  }

  /**
   * The type identifier of the record at the 'from' end of the link record identified by **seedId**.
   * @return fromEndTypeId
  */
  @NotNull 
  @JsonProperty("fromEndTypeId")
  public String getFromEndTypeId() {
    return fromEndTypeId;
  }

  public void setFromEndTypeId(String fromEndTypeId) {
    this.fromEndTypeId = fromEndTypeId;
  }

  public DaodSeedLinkData linkDirection(LinkDirection linkDirection) {
    this.linkDirection = linkDirection;
    return this;
  }

  /**
   * Get linkDirection
   * @return linkDirection
  */
  @NotNull @Valid 
  @JsonProperty("linkDirection")
  public LinkDirection getLinkDirection() {
    return linkDirection;
  }

  public void setLinkDirection(LinkDirection linkDirection) {
    this.linkDirection = linkDirection;
  }

  public DaodSeedLinkData toEndId(Object toEndId) {
    this.toEndId = toEndId;
    return this;
  }

  /**
   * The identifier of the record at the 'to' end of the link record identified by **seedId**.
   * @return toEndId
  */
  @NotNull 
  @JsonProperty("toEndId")
  public Object getToEndId() {
    return toEndId;
  }

  public void setToEndId(Object toEndId) {
    this.toEndId = toEndId;
  }

  public DaodSeedLinkData toEndTypeId(String toEndTypeId) {
    this.toEndTypeId = toEndTypeId;
    return this;
  }

  /**
   * The type identifier of the record at the 'to' end of the link record identified by **seedId**.
   * @return toEndTypeId
  */
  @NotNull 
  @JsonProperty("toEndTypeId")
  public String getToEndTypeId() {
    return toEndTypeId;
  }

  public void setToEndTypeId(String toEndTypeId) {
    this.toEndTypeId = toEndTypeId;
  }

  public DaodSeedLinkData typeLocation(TypeLocation typeLocation) {
    this.typeLocation = typeLocation;
    return this;
  }

  /**
   * Get typeLocation
   * @return typeLocation
  */
  @Valid 
  @JsonProperty("typeLocation")
  public TypeLocation getTypeLocation() {
    return typeLocation;
  }

  public void setTypeLocation(TypeLocation typeLocation) {
    this.typeLocation = typeLocation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DaodSeedLinkData daodSeedLinkData = (DaodSeedLinkData) o;
    return Objects.equals(this.sourceIds, daodSeedLinkData.sourceIds) &&
        Objects.equals(this.seedId, daodSeedLinkData.seedId) &&
        Objects.equals(this.typeId, daodSeedLinkData.typeId) &&
        Objects.equals(this.label, daodSeedLinkData.label) &&
        Objects.equals(this.properties, daodSeedLinkData.properties) &&
        Objects.equals(this.accessDimensionValues, daodSeedLinkData.accessDimensionValues) &&
        Objects.equals(this.extensions, daodSeedLinkData.extensions) &&
        Objects.equals(this.fromEndId, daodSeedLinkData.fromEndId) &&
        Objects.equals(this.fromEndTypeId, daodSeedLinkData.fromEndTypeId) &&
        Objects.equals(this.linkDirection, daodSeedLinkData.linkDirection) &&
        Objects.equals(this.toEndId, daodSeedLinkData.toEndId) &&
        Objects.equals(this.toEndTypeId, daodSeedLinkData.toEndTypeId) &&
        Objects.equals(this.typeLocation, daodSeedLinkData.typeLocation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sourceIds, seedId, typeId, label, properties, accessDimensionValues, extensions, fromEndId, fromEndTypeId, linkDirection, toEndId, toEndTypeId, typeLocation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DaodSeedLinkData {\n");
    sb.append("    sourceIds: ").append(toIndentedString(sourceIds)).append("\n");
    sb.append("    seedId: ").append(toIndentedString(seedId)).append("\n");
    sb.append("    typeId: ").append(toIndentedString(typeId)).append("\n");
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
    sb.append("    properties: ").append(toIndentedString(properties)).append("\n");
    sb.append("    accessDimensionValues: ").append(toIndentedString(accessDimensionValues)).append("\n");
    sb.append("    extensions: ").append(toIndentedString(extensions)).append("\n");
    sb.append("    fromEndId: ").append(toIndentedString(fromEndId)).append("\n");
    sb.append("    fromEndTypeId: ").append(toIndentedString(fromEndTypeId)).append("\n");
    sb.append("    linkDirection: ").append(toIndentedString(linkDirection)).append("\n");
    sb.append("    toEndId: ").append(toIndentedString(toEndId)).append("\n");
    sb.append("    toEndTypeId: ").append(toIndentedString(toEndTypeId)).append("\n");
    sb.append("    typeLocation: ").append(toIndentedString(typeLocation)).append("\n");
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

