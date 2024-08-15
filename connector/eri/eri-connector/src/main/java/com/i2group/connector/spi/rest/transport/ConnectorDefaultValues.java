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
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

/**
 * Settings for clients to apply to the data they receive, if the data does not indicate otherwise.
 */
public class ConnectorDefaultValues {

  public String entityTypeId;

  public SchemaTypeLocation entityTypeLocation;

  public LinkDirection linkDirection;

  public String linkTypeId;

  public SchemaTypeLocation linkTypeLocation;

  public Boolean resultIdsPersistent;

  public String timeZoneId;

  public ConnectorDefaultValues entityTypeId(String entityTypeId) {
    this.entityTypeId = entityTypeId;
    return this;
  }

  /**
   * The identifier of the default entity type to apply to retrieved data.
   * @return entityTypeId
  */
  
  @JsonProperty("entityTypeId")
  public String getEntityTypeId() {
    return entityTypeId;
  }

  public void setEntityTypeId(String entityTypeId) {
    this.entityTypeId = entityTypeId;
  }

  public ConnectorDefaultValues entityTypeLocation(SchemaTypeLocation entityTypeLocation) {
    this.entityTypeLocation = entityTypeLocation;
    return this;
  }

  /**
   * Get entityTypeLocation
   * @return entityTypeLocation
  */
  @Valid 
  @JsonProperty("entityTypeLocation")
  public SchemaTypeLocation getEntityTypeLocation() {
    return entityTypeLocation;
  }

  public void setEntityTypeLocation(SchemaTypeLocation entityTypeLocation) {
    this.entityTypeLocation = entityTypeLocation;
  }

  public ConnectorDefaultValues linkDirection(LinkDirection linkDirection) {
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

  public ConnectorDefaultValues linkTypeId(String linkTypeId) {
    this.linkTypeId = linkTypeId;
    return this;
  }

  /**
   * The identifier of the default link type to apply to retrieved data.
   * @return linkTypeId
  */
  
  @JsonProperty("linkTypeId")
  public String getLinkTypeId() {
    return linkTypeId;
  }

  public void setLinkTypeId(String linkTypeId) {
    this.linkTypeId = linkTypeId;
  }

  public ConnectorDefaultValues linkTypeLocation(SchemaTypeLocation linkTypeLocation) {
    this.linkTypeLocation = linkTypeLocation;
    return this;
  }

  /**
   * Get linkTypeLocation
   * @return linkTypeLocation
  */
  @Valid 
  @JsonProperty("linkTypeLocation")
  public SchemaTypeLocation getLinkTypeLocation() {
    return linkTypeLocation;
  }

  public void setLinkTypeLocation(SchemaTypeLocation linkTypeLocation) {
    this.linkTypeLocation = linkTypeLocation;
  }

  public ConnectorDefaultValues resultIdsPersistent(Boolean resultIdsPersistent) {
    this.resultIdsPersistent = resultIdsPersistent;
    return this;
  }

  /**
   * The default indicator of whether identifiers for the same retrieved data are (**true**) or are not (**false**) persistent from one set of results to the next.
   * @return resultIdsPersistent
  */
  
  @JsonProperty("resultIdsPersistent")
  public Boolean getResultIdsPersistent() {
    return resultIdsPersistent;
  }

  public void setResultIdsPersistent(Boolean resultIdsPersistent) {
    this.resultIdsPersistent = resultIdsPersistent;
  }

  public ConnectorDefaultValues timeZoneId(String timeZoneId) {
    this.timeZoneId = timeZoneId;
    return this;
  }

  /**
   * The identifier of the default time zone to apply to retrieved data, which itself defaults to UTC if no value is specified.
   * @return timeZoneId
  */
  
  @JsonProperty("timeZoneId")
  public String getTimeZoneId() {
    return timeZoneId;
  }

  public void setTimeZoneId(String timeZoneId) {
    this.timeZoneId = timeZoneId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConnectorDefaultValues connectorDefaultValues = (ConnectorDefaultValues) o;
    return Objects.equals(this.entityTypeId, connectorDefaultValues.entityTypeId) &&
        Objects.equals(this.entityTypeLocation, connectorDefaultValues.entityTypeLocation) &&
        Objects.equals(this.linkDirection, connectorDefaultValues.linkDirection) &&
        Objects.equals(this.linkTypeId, connectorDefaultValues.linkTypeId) &&
        Objects.equals(this.linkTypeLocation, connectorDefaultValues.linkTypeLocation) &&
        Objects.equals(this.resultIdsPersistent, connectorDefaultValues.resultIdsPersistent) &&
        Objects.equals(this.timeZoneId, connectorDefaultValues.timeZoneId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(entityTypeId, entityTypeLocation, linkDirection, linkTypeId, linkTypeLocation, resultIdsPersistent, timeZoneId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConnectorDefaultValues {\n");
    sb.append("    entityTypeId: ").append(toIndentedString(entityTypeId)).append("\n");
    sb.append("    entityTypeLocation: ").append(toIndentedString(entityTypeLocation)).append("\n");
    sb.append("    linkDirection: ").append(toIndentedString(linkDirection)).append("\n");
    sb.append("    linkTypeId: ").append(toIndentedString(linkTypeId)).append("\n");
    sb.append("    linkTypeLocation: ").append(toIndentedString(linkTypeLocation)).append("\n");
    sb.append("    resultIdsPersistent: ").append(toIndentedString(resultIdsPersistent)).append("\n");
    sb.append("    timeZoneId: ").append(toIndentedString(timeZoneId)).append("\n");
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

