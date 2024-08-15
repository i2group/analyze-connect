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
import com.i2group.connector.spi.rest.transport.AuthConfig;
import com.i2group.connector.spi.rest.transport.ClientConfig;
import com.i2group.connector.spi.rest.transport.ConnectorDefaultValues;
import com.i2group.connector.spi.rest.transport.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

/**
 * Information about the services that a connector provides.
 */
public class ConnectorConfigBase {

  public ConnectorDefaultValues defaultValues;

  @Valid
  public List<@Valid Service> services;

  @Valid
  public List<@Valid AuthConfig> authConfigs;

  @Valid
  public List<@Valid ClientConfig> clientConfigs;

  public ConnectorConfigBase defaultValues(ConnectorDefaultValues defaultValues) {
    this.defaultValues = defaultValues;
    return this;
  }

  /**
   * Get defaultValues
   * @return defaultValues
  */
  @Valid 
  @JsonProperty("defaultValues")
  public ConnectorDefaultValues getDefaultValues() {
    return defaultValues;
  }

  public void setDefaultValues(ConnectorDefaultValues defaultValues) {
    this.defaultValues = defaultValues;
  }

  public ConnectorConfigBase services(List<@Valid Service> services) {
    this.services = services;
    return this;
  }

  public ConnectorConfigBase addServicesItem(Service servicesItem) {
    if (this.services == null) {
      this.services = new ArrayList<>();
    }
    this.services.add(servicesItem);
    return this;
  }

  /**
   * The services available on the connector. This field is not valid when **userConfigUrl** is present and populated.
   * @return services
  */
  @Valid 
  @JsonProperty("services")
  public List<@Valid Service> getServices() {
    return services;
  }

  public void setServices(List<@Valid Service> services) {
    this.services = services;
  }

  public ConnectorConfigBase authConfigs(List<@Valid AuthConfig> authConfigs) {
    this.authConfigs = authConfigs;
    return this;
  }

  public ConnectorConfigBase addAuthConfigsItem(AuthConfig authConfigsItem) {
    if (this.authConfigs == null) {
      this.authConfigs = new ArrayList<>();
    }
    this.authConfigs.add(authConfigsItem);
    return this;
  }

  /**
   * The authentication configurations that the services on the connector use. This field is not valid when **userConfigUrl** is present and populated.
   * @return authConfigs
  */
  @Valid 
  @JsonProperty("authConfigs")
  public List<@Valid AuthConfig> getAuthConfigs() {
    return authConfigs;
  }

  public void setAuthConfigs(List<@Valid AuthConfig> authConfigs) {
    this.authConfigs = authConfigs;
  }

  public ConnectorConfigBase clientConfigs(List<@Valid ClientConfig> clientConfigs) {
    this.clientConfigs = clientConfigs;
    return this;
  }

  public ConnectorConfigBase addClientConfigsItem(ClientConfig clientConfigsItem) {
    if (this.clientConfigs == null) {
      this.clientConfigs = new ArrayList<>();
    }
    this.clientConfigs.add(clientConfigsItem);
    return this;
  }

  /**
   * The client configurations that the services on the connector use. This field is not valid when **userConfigUrl** is present and populated.
   * @return clientConfigs
  */
  @Valid 
  @JsonProperty("clientConfigs")
  public List<@Valid ClientConfig> getClientConfigs() {
    return clientConfigs;
  }

  public void setClientConfigs(List<@Valid ClientConfig> clientConfigs) {
    this.clientConfigs = clientConfigs;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConnectorConfigBase connectorConfigBase = (ConnectorConfigBase) o;
    return Objects.equals(this.defaultValues, connectorConfigBase.defaultValues) &&
        Objects.equals(this.services, connectorConfigBase.services) &&
        Objects.equals(this.authConfigs, connectorConfigBase.authConfigs) &&
        Objects.equals(this.clientConfigs, connectorConfigBase.clientConfigs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(defaultValues, services, authConfigs, clientConfigs);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConnectorConfigBase {\n");
    sb.append("    defaultValues: ").append(toIndentedString(defaultValues)).append("\n");
    sb.append("    services: ").append(toIndentedString(services)).append("\n");
    sb.append("    authConfigs: ").append(toIndentedString(authConfigs)).append("\n");
    sb.append("    clientConfigs: ").append(toIndentedString(clientConfigs)).append("\n");
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

