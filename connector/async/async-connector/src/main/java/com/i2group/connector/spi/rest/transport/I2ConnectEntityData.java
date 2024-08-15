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
 * An entity in the results of a request for data to an i2 Connect service.
 */
public class I2ConnectEntityData {

  public Object id;

  @Valid
  public Map<String, Object> properties = new HashMap<>();

  public SourceReference sourceReference;

  public String typeId;

  public SchemaTypeLocation typeLocation;

  public Long version;

  public I2ConnectEntityData() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public I2ConnectEntityData(Object id) {
    this.id = id;
  }

  public I2ConnectEntityData id(Object id) {
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

  public I2ConnectEntityData properties(Map<String, Object> properties) {
    this.properties = properties;
    return this;
  }

  public I2ConnectEntityData putPropertiesItem(String key, Object propertiesItem) {
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

  public I2ConnectEntityData sourceReference(SourceReference sourceReference) {
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

  public I2ConnectEntityData typeId(String typeId) {
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

  public I2ConnectEntityData typeLocation(SchemaTypeLocation typeLocation) {
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

  public I2ConnectEntityData version(Long version) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    I2ConnectEntityData i2ConnectEntityData = (I2ConnectEntityData) o;
    return Objects.equals(this.id, i2ConnectEntityData.id) &&
        Objects.equals(this.properties, i2ConnectEntityData.properties) &&
        Objects.equals(this.sourceReference, i2ConnectEntityData.sourceReference) &&
        Objects.equals(this.typeId, i2ConnectEntityData.typeId) &&
        Objects.equals(this.typeLocation, i2ConnectEntityData.typeLocation) &&
        Objects.equals(this.version, i2ConnectEntityData.version);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, properties, sourceReference, typeId, typeLocation, version);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class I2ConnectEntityData {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    properties: ").append(toIndentedString(properties)).append("\n");
    sb.append("    sourceReference: ").append(toIndentedString(sourceReference)).append("\n");
    sb.append("    typeId: ").append(toIndentedString(typeId)).append("\n");
    sb.append("    typeLocation: ").append(toIndentedString(typeLocation)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
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

