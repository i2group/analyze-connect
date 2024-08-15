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
import com.i2group.connector.spi.rest.transport.I2ConnectEntityData;
import com.i2group.connector.spi.rest.transport.I2ConnectLinkData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

/**
 * The results of a request for data to an i2 Connect service.
 */
public class I2ConnectData {

  @Valid
  public List<@Valid I2ConnectEntityData> entities;

  public String errorMessage;

  @Valid
  public List<@Valid I2ConnectLinkData> links;

  public I2ConnectData entities(List<@Valid I2ConnectEntityData> entities) {
    this.entities = entities;
    return this;
  }

  public I2ConnectData addEntitiesItem(I2ConnectEntityData entitiesItem) {
    if (this.entities == null) {
      this.entities = new ArrayList<>();
    }
    this.entities.add(entitiesItem);
    return this;
  }

  /**
   * The entity data returned from a connector.
   * @return entities
  */
  @Valid 
  @JsonProperty("entities")
  public List<@Valid I2ConnectEntityData> getEntities() {
    return entities;
  }

  public void setEntities(List<@Valid I2ConnectEntityData> entities) {
    this.entities = entities;
  }

  public I2ConnectData errorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
    return this;
  }

  /**
   * An error message that might be displayed to users.
   * @return errorMessage
  */
  
  @JsonProperty("errorMessage")
  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public I2ConnectData links(List<@Valid I2ConnectLinkData> links) {
    this.links = links;
    return this;
  }

  public I2ConnectData addLinksItem(I2ConnectLinkData linksItem) {
    if (this.links == null) {
      this.links = new ArrayList<>();
    }
    this.links.add(linksItem);
    return this;
  }

  /**
   * The link data returned from a connector.
   * @return links
  */
  @Valid 
  @JsonProperty("links")
  public List<@Valid I2ConnectLinkData> getLinks() {
    return links;
  }

  public void setLinks(List<@Valid I2ConnectLinkData> links) {
    this.links = links;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    I2ConnectData i2ConnectData = (I2ConnectData) o;
    return Objects.equals(this.entities, i2ConnectData.entities) &&
        Objects.equals(this.errorMessage, i2ConnectData.errorMessage) &&
        Objects.equals(this.links, i2ConnectData.links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(entities, errorMessage, links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class I2ConnectData {\n");
    sb.append("    entities: ").append(toIndentedString(entities)).append("\n");
    sb.append("    errorMessage: ").append(toIndentedString(errorMessage)).append("\n");
    sb.append("    links: ").append(toIndentedString(links)).append("\n");
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

