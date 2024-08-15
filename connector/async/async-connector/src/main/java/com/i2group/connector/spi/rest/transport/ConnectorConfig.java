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
 * Complete information about a connector for the i2 Connect gateway.
 */
public class ConnectorConfig {

  public ConnectorDefaultValues defaultValues;

  @Valid
  public List<@Valid Service> services;

  @Valid
  public List<@Valid AuthConfig> authConfigs;

  @Valid
  public List<@Valid ClientConfig> clientConfigs;

  public String version;

  public String chartingSchemesUrl;

  public String gatewaySchema;

  public String schemaShortName;

  public String schemaUrl;

  public String userConfigUrl;

  public ConnectorConfig defaultValues(ConnectorDefaultValues defaultValues) {
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

  public ConnectorConfig services(List<@Valid Service> services) {
    this.services = services;
    return this;
  }

  public ConnectorConfig addServicesItem(Service servicesItem) {
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

  public ConnectorConfig authConfigs(List<@Valid AuthConfig> authConfigs) {
    this.authConfigs = authConfigs;
    return this;
  }

  public ConnectorConfig addAuthConfigsItem(AuthConfig authConfigsItem) {
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

  public ConnectorConfig clientConfigs(List<@Valid ClientConfig> clientConfigs) {
    this.clientConfigs = clientConfigs;
    return this;
  }

  public ConnectorConfig addClientConfigsItem(ClientConfig clientConfigsItem) {
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

  public ConnectorConfig version(String version) {
    this.version = version;
    return this;
  }

  /**
   * The minimum SPI version that the connector requires. If no value is specified, the server assumes that the connector requires version `1.0`.
   * @return version
  */
  @Pattern(regexp = "\\d+\\.\\d+") 
  @JsonProperty("version")
  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public ConnectorConfig chartingSchemesUrl(String chartingSchemesUrl) {
    this.chartingSchemesUrl = chartingSchemesUrl;
    return this;
  }

  /**
   * The URL from which to retrieve a charting scheme for the connector, which can be absolute or relative to its base URL. No value means that there is no charting scheme.
   * @return chartingSchemesUrl
  */
  
  @JsonProperty("chartingSchemesUrl")
  public String getChartingSchemesUrl() {
    return chartingSchemesUrl;
  }

  public void setChartingSchemesUrl(String chartingSchemesUrl) {
    this.chartingSchemesUrl = chartingSchemesUrl;
  }

  public ConnectorConfig gatewaySchema(String gatewaySchema) {
    this.gatewaySchema = gatewaySchema;
    return this;
  }

  /**
   * The short name of the gateway schema whose types the connector uses, or no value if the connector does not use a gateway schema. The topology for the deployment can override the gateway schema specified here.
   * @return gatewaySchema
  */
  
  @JsonProperty("gatewaySchema")
  public String getGatewaySchema() {
    return gatewaySchema;
  }

  public void setGatewaySchema(String gatewaySchema) {
    this.gatewaySchema = gatewaySchema;
  }

  public ConnectorConfig schemaShortName(String schemaShortName) {
    this.schemaShortName = schemaShortName;
    return this;
  }

  /**
   * The short name for the connector schema, or no value when there is no connector schema. The topology for the deployment can override the name specified here.
   * @return schemaShortName
  */
  
  @JsonProperty("schemaShortName")
  public String getSchemaShortName() {
    return schemaShortName;
  }

  public void setSchemaShortName(String schemaShortName) {
    this.schemaShortName = schemaShortName;
  }

  public ConnectorConfig schemaUrl(String schemaUrl) {
    this.schemaUrl = schemaUrl;
    return this;
  }

  /**
   * The URL from which to retrieve a schema for the connector, which can be absolute or relative to its base URL. No value means that there is no schema.
   * @return schemaUrl
  */
  
  @JsonProperty("schemaUrl")
  public String getSchemaUrl() {
    return schemaUrl;
  }

  public void setSchemaUrl(String schemaUrl) {
    this.schemaUrl = schemaUrl;
  }

  public ConnectorConfig userConfigUrl(String userConfigUrl) {
    this.userConfigUrl = userConfigUrl;
    return this;
  }

  /**
   * The URL from which to retrieve user-specific configuration settings, which can be absolute or relative to the connector's base URL. No value means that there is no user-specific configuration.  When this field is present and populated, **schemaUrl**, **chartingSchemesUrl**, **schemaShortName**, and **gatewaySchema** can also be present, but no other fields are valid.
   * @return userConfigUrl
  */
  
  @JsonProperty("userConfigUrl")
  public String getUserConfigUrl() {
    return userConfigUrl;
  }

  public void setUserConfigUrl(String userConfigUrl) {
    this.userConfigUrl = userConfigUrl;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConnectorConfig connectorConfig = (ConnectorConfig) o;
    return Objects.equals(this.defaultValues, connectorConfig.defaultValues) &&
        Objects.equals(this.services, connectorConfig.services) &&
        Objects.equals(this.authConfigs, connectorConfig.authConfigs) &&
        Objects.equals(this.clientConfigs, connectorConfig.clientConfigs) &&
        Objects.equals(this.version, connectorConfig.version) &&
        Objects.equals(this.chartingSchemesUrl, connectorConfig.chartingSchemesUrl) &&
        Objects.equals(this.gatewaySchema, connectorConfig.gatewaySchema) &&
        Objects.equals(this.schemaShortName, connectorConfig.schemaShortName) &&
        Objects.equals(this.schemaUrl, connectorConfig.schemaUrl) &&
        Objects.equals(this.userConfigUrl, connectorConfig.userConfigUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(defaultValues, services, authConfigs, clientConfigs, version, chartingSchemesUrl, gatewaySchema, schemaShortName, schemaUrl, userConfigUrl);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConnectorConfig {\n");
    sb.append("    defaultValues: ").append(toIndentedString(defaultValues)).append("\n");
    sb.append("    services: ").append(toIndentedString(services)).append("\n");
    sb.append("    authConfigs: ").append(toIndentedString(authConfigs)).append("\n");
    sb.append("    clientConfigs: ").append(toIndentedString(clientConfigs)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    chartingSchemesUrl: ").append(toIndentedString(chartingSchemesUrl)).append("\n");
    sb.append("    gatewaySchema: ").append(toIndentedString(gatewaySchema)).append("\n");
    sb.append("    schemaShortName: ").append(toIndentedString(schemaShortName)).append("\n");
    sb.append("    schemaUrl: ").append(toIndentedString(schemaUrl)).append("\n");
    sb.append("    userConfigUrl: ").append(toIndentedString(userConfigUrl)).append("\n");
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

