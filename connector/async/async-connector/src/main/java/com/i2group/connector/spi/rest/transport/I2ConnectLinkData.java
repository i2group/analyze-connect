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
import com.i2group.connector.spi.rest.transport.SchemaTypeLocation;
import com.i2group.connector.spi.rest.transport.SourceReference;
import java.util.HashMap;
import java.util.Map;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

/**
 * A link in the results of a request for data to an i2 Connect service.
 */
public class I2ConnectLinkData {

  public Object id;

  @Valid
  public Map<String, Object> properties = new HashMap<>();

  public SourceReference sourceReference;

  public String typeId;

  public SchemaTypeLocation typeLocation;

  public Long version;

  public Object fromEndId;

  public Object toEndId;

  public LinkDirection linkDirection;

  public I2ConnectLinkData() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public I2ConnectLinkData(Object id) {
    this.id = id;
  }

  public I2ConnectLinkData id(Object id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @NotNull 
  @JsonProperty("id")
  public Object getId() {
    return id;
  }

  public void setId(Object id) {
    this.id = id;
  }

  public I2ConnectLinkData properties(Map<String, Object> properties) {
    this.properties = properties;
    return this;
  }

  public I2ConnectLinkData putPropertiesItem(String key, Object propertiesItem) {
    if (this.properties == null) {
      this.properties = new HashMap<>();
    }
    this.properties.put(key, propertiesItem);
    return this;
  }

  /**
   * The property data for a record.
   * @return properties
  */
  
  @JsonProperty("properties")
  public Map<String, Object> getProperties() {
    return properties;
  }

  public void setProperties(Map<String, Object> properties) {
    this.properties = properties;
  }

  public I2ConnectLinkData sourceReference(SourceReference sourceReference) {
    this.sourceReference = sourceReference;
    return this;
  }

  /**
   * Get sourceReference
   * @return sourceReference
  */
  @Valid 
  @JsonProperty("sourceReference")
  public SourceReference getSourceReference() {
    return sourceReference;
  }

  public void setSourceReference(SourceReference sourceReference) {
    this.sourceReference = sourceReference;
  }

  public I2ConnectLinkData typeId(String typeId) {
    this.typeId = typeId;
    return this;
  }

  /**
   * The type identifier for a record.
   * @return typeId
  */
  
  @JsonProperty("typeId")
  public String getTypeId() {
    return typeId;
  }

  public void setTypeId(String typeId) {
    this.typeId = typeId;
  }

  public I2ConnectLinkData typeLocation(SchemaTypeLocation typeLocation) {
    this.typeLocation = typeLocation;
    return this;
  }

  /**
   * Get typeLocation
   * @return typeLocation
  */
  @Valid 
  @JsonProperty("typeLocation")
  public SchemaTypeLocation getTypeLocation() {
    return typeLocation;
  }

  public void setTypeLocation(SchemaTypeLocation typeLocation) {
    this.typeLocation = typeLocation;
  }

  public I2ConnectLinkData version(Long version) {
    this.version = version;
    return this;
  }

  /**
   * The version of the record.
   * @return version
  */
  
  @JsonProperty("version")
  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public I2ConnectLinkData fromEndId(Object fromEndId) {
    this.fromEndId = fromEndId;
    return this;
  }

  /**
   * Get fromEndId
   * @return fromEndId
  */
  
  @JsonProperty("fromEndId")
  public Object getFromEndId() {
    return fromEndId;
  }

  public void setFromEndId(Object fromEndId) {
    this.fromEndId = fromEndId;
  }

  public I2ConnectLinkData toEndId(Object toEndId) {
    this.toEndId = toEndId;
    return this;
  }

  /**
   * Get toEndId
   * @return toEndId
  */
  
  @JsonProperty("toEndId")
  public Object getToEndId() {
    return toEndId;
  }

  public void setToEndId(Object toEndId) {
    this.toEndId = toEndId;
  }

  public I2ConnectLinkData linkDirection(LinkDirection linkDirection) {
    this.linkDirection = linkDirection;
    return this;
  }

  /**
   * Get linkDirection
   * @return linkDirection
  */
  @Valid 
  @JsonProperty("linkDirection")
  public LinkDirection getLinkDirection() {
    return linkDirection;
  }

  public void setLinkDirection(LinkDirection linkDirection) {
    this.linkDirection = linkDirection;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    I2ConnectLinkData i2ConnectLinkData = (I2ConnectLinkData) o;
    return Objects.equals(this.id, i2ConnectLinkData.id) &&
        Objects.equals(this.properties, i2ConnectLinkData.properties) &&
        Objects.equals(this.sourceReference, i2ConnectLinkData.sourceReference) &&
        Objects.equals(this.typeId, i2ConnectLinkData.typeId) &&
        Objects.equals(this.typeLocation, i2ConnectLinkData.typeLocation) &&
        Objects.equals(this.version, i2ConnectLinkData.version) &&
        Objects.equals(this.fromEndId, i2ConnectLinkData.fromEndId) &&
        Objects.equals(this.toEndId, i2ConnectLinkData.toEndId) &&
        Objects.equals(this.linkDirection, i2ConnectLinkData.linkDirection);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, properties, sourceReference, typeId, typeLocation, version, fromEndId, toEndId, linkDirection);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class I2ConnectLinkData {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    properties: ").append(toIndentedString(properties)).append("\n");
    sb.append("    sourceReference: ").append(toIndentedString(sourceReference)).append("\n");
    sb.append("    typeId: ").append(toIndentedString(typeId)).append("\n");
    sb.append("    typeLocation: ").append(toIndentedString(typeLocation)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    fromEndId: ").append(toIndentedString(fromEndId)).append("\n");
    sb.append("    toEndId: ").append(toIndentedString(toEndId)).append("\n");
    sb.append("    linkDirection: ").append(toIndentedString(linkDirection)).append("\n");
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

