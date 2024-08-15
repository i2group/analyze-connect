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
import com.i2group.connector.spi.rest.transport.AsyncQuerySubstatus;
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
 * Information about the progress of an asynchronous query.
 */
public class AsyncQueryStatus {

  public String errorMessage;

  /**
   * The state of a query.
   */
  public enum StateEnum {
    STARTED("STARTED"),
    
    SUCCEEDED("SUCCEEDED"),
    
    FAILED("FAILED");

    private String value;

    StateEnum(String value) {
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
    public static StateEnum fromValue(String value) {
      for (StateEnum b : StateEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public StateEnum state;

  @Valid
  public List<@Valid AsyncQuerySubstatus> substatuses;

  @Valid
  public Map<String, Object> propertyBag = new HashMap<>();

  public AsyncQueryStatus errorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
    return this;
  }

  /**
   * An error message that explains why a query is in the 'FAILED' state.
   * @return errorMessage
  */
  
  @JsonProperty("errorMessage")
  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public AsyncQueryStatus state(StateEnum state) {
    this.state = state;
    return this;
  }

  /**
   * The state of a query.
   * @return state
  */
  
  @JsonProperty("state")
  public StateEnum getState() {
    return state;
  }

  public void setState(StateEnum state) {
    this.state = state;
  }

  public AsyncQueryStatus substatuses(List<@Valid AsyncQuerySubstatus> substatuses) {
    this.substatuses = substatuses;
    return this;
  }

  public AsyncQueryStatus addSubstatusesItem(AsyncQuerySubstatus substatusesItem) {
    if (this.substatuses == null) {
      this.substatuses = new ArrayList<>();
    }
    this.substatuses.add(substatusesItem);
    return this;
  }

  /**
   * More detailed information about the progress of a query.
   * @return substatuses
  */
  @Valid 
  @JsonProperty("substatuses")
  public List<@Valid AsyncQuerySubstatus> getSubstatuses() {
    return substatuses;
  }

  public void setSubstatuses(List<@Valid AsyncQuerySubstatus> substatuses) {
    this.substatuses = substatuses;
  }

  public AsyncQueryStatus propertyBag(Map<String, Object> propertyBag) {
    this.propertyBag = propertyBag;
    return this;
  }

  public AsyncQueryStatus putPropertyBagItem(String key, Object propertyBagItem) {
    if (this.propertyBag == null) {
      this.propertyBag = new HashMap<>();
    }
    this.propertyBag.put(key, propertyBagItem);
    return this;
  }

  /**
   * A store for additional information about the status of a query that can be used by custom clients.
   * @return propertyBag
  */
  
  @JsonProperty("propertyBag")
  public Map<String, Object> getPropertyBag() {
    return propertyBag;
  }

  public void setPropertyBag(Map<String, Object> propertyBag) {
    this.propertyBag = propertyBag;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AsyncQueryStatus asyncQueryStatus = (AsyncQueryStatus) o;
    return Objects.equals(this.errorMessage, asyncQueryStatus.errorMessage) &&
        Objects.equals(this.state, asyncQueryStatus.state) &&
        Objects.equals(this.substatuses, asyncQueryStatus.substatuses) &&
        Objects.equals(this.propertyBag, asyncQueryStatus.propertyBag);
  }

  @Override
  public int hashCode() {
    return Objects.hash(errorMessage, state, substatuses, propertyBag);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AsyncQueryStatus {\n");
    sb.append("    errorMessage: ").append(toIndentedString(errorMessage)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    substatuses: ").append(toIndentedString(substatuses)).append("\n");
    sb.append("    propertyBag: ").append(toIndentedString(propertyBag)).append("\n");
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

