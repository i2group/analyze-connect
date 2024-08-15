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
import com.i2group.connector.spi.rest.transport.Async;
import com.i2group.connector.spi.rest.transport.ConnectorSeedConstraints;
import java.util.HashMap;
import java.util.Map;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

/**
 * A service that a connector for the i2 Connect gateway makes available.
 */
public class Service {

  public String id;

  public String name;

  public String description;

  public String acquireUrl;

  public Async async;

  public String authConfigId;

  public String clientConfigId;

  /**
   * The type of the client configuration for the service.  **Deprecated:** This field will be removed in a future release. Use the type in the clientConfig instead. Do not rely on the value of this field if it is set.
   */
  public enum ClientConfigTypeEnum {
    CUSTOM("CUSTOM"),
    
    FORM("FORM"),
    
    NONE("NONE");

    private String value;

    ClientConfigTypeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ClientConfigTypeEnum fromValue(String value) {
      for (ClientConfigTypeEnum b : ClientConfigTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @Deprecated
  public ClientConfigTypeEnum clientConfigType;

  public Boolean resultIdsPersistent;

  @Valid
  public Map<String, Object> resultItemTypeIds = new HashMap<>();

  public ConnectorSeedConstraints seedConstraints;

  public String validateUrl;

  public Service() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Service(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public Service id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The unique identifier of the service.
   * @return id
  */
  @NotNull 
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Service name(String name) {
    this.name = name;
    return this;
  }

  /**
   * The name of the service, which might be displayed to users.
   * @return name
  */
  @NotNull 
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Service description(String description) {
    this.description = description;
    return this;
  }

  /**
   * A description of the service, which might be displayed to users.
   * @return description
  */
  
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Service acquireUrl(String acquireUrl) {
    this.acquireUrl = acquireUrl;
    return this;
  }

  /**
   * The URL for the endpoint that provides result data from the service. If this is present, then **async** must not be present.
   * @return acquireUrl
  */
  
  @JsonProperty("acquireUrl")
  public String getAcquireUrl() {
    return acquireUrl;
  }

  public void setAcquireUrl(String acquireUrl) {
    this.acquireUrl = acquireUrl;
  }

  public Service async(Async async) {
    this.async = async;
    return this;
  }

  /**
   * Get async
   * @return async
  */
  @Valid 
  @JsonProperty("async")
  public Async getAsync() {
    return async;
  }

  public void setAsync(Async async) {
    this.async = async;
  }

  public Service authConfigId(String authConfigId) {
    this.authConfigId = authConfigId;
    return this;
  }

  /**
   * The identifier of the authentication configuration for the service.
   * @return authConfigId
  */
  
  @JsonProperty("authConfigId")
  public String getAuthConfigId() {
    return authConfigId;
  }

  public void setAuthConfigId(String authConfigId) {
    this.authConfigId = authConfigId;
  }

  public Service clientConfigId(String clientConfigId) {
    this.clientConfigId = clientConfigId;
    return this;
  }

  /**
   * The identifier of the client configuration for the service, if the type is not 'NONE'.
   * @return clientConfigId
  */
  
  @JsonProperty("clientConfigId")
  public String getClientConfigId() {
    return clientConfigId;
  }

  public void setClientConfigId(String clientConfigId) {
    this.clientConfigId = clientConfigId;
  }

  public Service clientConfigType(ClientConfigTypeEnum clientConfigType) {
    this.clientConfigType = clientConfigType;
    return this;
  }

  /**
   * The type of the client configuration for the service.  **Deprecated:** This field will be removed in a future release. Use the type in the clientConfig instead. Do not rely on the value of this field if it is set.
   * @return clientConfigType
   * @deprecated
  */
  
  @JsonProperty("clientConfigType")
  @Deprecated
  public ClientConfigTypeEnum getClientConfigType() {
    return clientConfigType;
  }

  /**
   * @deprecated
  */
  @Deprecated
  public void setClientConfigType(ClientConfigTypeEnum clientConfigType) {
    this.clientConfigType = clientConfigType;
  }

  public Service resultIdsPersistent(Boolean resultIdsPersistent) {
    this.resultIdsPersistent = resultIdsPersistent;
    return this;
  }

  /**
   * **true** if identifiers for the same data retrieved from the service are persistent from one set of results to the next; **false** otherwise.
   * @return resultIdsPersistent
  */
  
  @JsonProperty("resultIdsPersistent")
  public Boolean getResultIdsPersistent() {
    return resultIdsPersistent;
  }

  public void setResultIdsPersistent(Boolean resultIdsPersistent) {
    this.resultIdsPersistent = resultIdsPersistent;
  }

  public Service resultItemTypeIds(Map<String, Object> resultItemTypeIds) {
    this.resultItemTypeIds = resultItemTypeIds;
    return this;
  }

  public Service putResultItemTypeIdsItem(String key, Object resultItemTypeIdsItem) {
    if (this.resultItemTypeIds == null) {
      this.resultItemTypeIds = new HashMap<>();
    }
    this.resultItemTypeIds.put(key, resultItemTypeIdsItem);
    return this;
  }

  /**
   * The identifiers of the item types that can appear in results from the service, in a map with the following structure: **{\"_LOCATION_\": [\"_TYPEID1_\", \"_TYPEID2_\", ...], ...}**.  Here, _LOCATION_ indicates which schema the item type is defined in. Legal values are 'INFOSTORE', 'GATEWAY', and 'CONNECTOR'. If a service uses types from all three locations, the map has three elements.
   * @return resultItemTypeIds
  */
  
  @JsonProperty("resultItemTypeIds")
  public Map<String, Object> getResultItemTypeIds() {
    return resultItemTypeIds;
  }

  public void setResultItemTypeIds(Map<String, Object> resultItemTypeIds) {
    this.resultItemTypeIds = resultItemTypeIds;
  }

  public Service seedConstraints(ConnectorSeedConstraints seedConstraints) {
    this.seedConstraints = seedConstraints;
    return this;
  }

  /**
   * Get seedConstraints
   * @return seedConstraints
  */
  @Valid 
  @JsonProperty("seedConstraints")
  public ConnectorSeedConstraints getSeedConstraints() {
    return seedConstraints;
  }

  public void setSeedConstraints(ConnectorSeedConstraints seedConstraints) {
    this.seedConstraints = seedConstraints;
  }

  public Service validateUrl(String validateUrl) {
    this.validateUrl = validateUrl;
    return this;
  }

  /**
   * The URL for the endpoint that validates requests for data from the service.
   * @return validateUrl
  */
  
  @JsonProperty("validateUrl")
  public String getValidateUrl() {
    return validateUrl;
  }

  public void setValidateUrl(String validateUrl) {
    this.validateUrl = validateUrl;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Service service = (Service) o;
    return Objects.equals(this.id, service.id) &&
        Objects.equals(this.name, service.name) &&
        Objects.equals(this.description, service.description) &&
        Objects.equals(this.acquireUrl, service.acquireUrl) &&
        Objects.equals(this.async, service.async) &&
        Objects.equals(this.authConfigId, service.authConfigId) &&
        Objects.equals(this.clientConfigId, service.clientConfigId) &&
        Objects.equals(this.clientConfigType, service.clientConfigType) &&
        Objects.equals(this.resultIdsPersistent, service.resultIdsPersistent) &&
        Objects.equals(this.resultItemTypeIds, service.resultItemTypeIds) &&
        Objects.equals(this.seedConstraints, service.seedConstraints) &&
        Objects.equals(this.validateUrl, service.validateUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, acquireUrl, async, authConfigId, clientConfigId, clientConfigType, resultIdsPersistent, resultItemTypeIds, seedConstraints, validateUrl);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Service {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    acquireUrl: ").append(toIndentedString(acquireUrl)).append("\n");
    sb.append("    async: ").append(toIndentedString(async)).append("\n");
    sb.append("    authConfigId: ").append(toIndentedString(authConfigId)).append("\n");
    sb.append("    clientConfigId: ").append(toIndentedString(clientConfigId)).append("\n");
    sb.append("    clientConfigType: ").append(toIndentedString(clientConfigType)).append("\n");
    sb.append("    resultIdsPersistent: ").append(toIndentedString(resultIdsPersistent)).append("\n");
    sb.append("    resultItemTypeIds: ").append(toIndentedString(resultItemTypeIds)).append("\n");
    sb.append("    seedConstraints: ").append(toIndentedString(seedConstraints)).append("\n");
    sb.append("    validateUrl: ").append(toIndentedString(validateUrl)).append("\n");
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

