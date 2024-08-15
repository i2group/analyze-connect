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
import com.i2group.connector.spi.rest.transport.AuthConfigForm;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

/**
 * Information about how to authenticate the users of a connector.
 */
public class AuthConfig {

  public AuthConfigForm form;

  public String id;

  public String loginUrl;

  public AuthConfig() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public AuthConfig(AuthConfigForm form, String id, String loginUrl) {
    this.form = form;
    this.id = id;
    this.loginUrl = loginUrl;
  }

  public AuthConfig form(AuthConfigForm form) {
    this.form = form;
    return this;
  }

  /**
   * Get form
   * @return form
  */
  @NotNull @Valid 
  @JsonProperty("form")
  public AuthConfigForm getForm() {
    return form;
  }

  public void setForm(AuthConfigForm form) {
    this.form = form;
  }

  public AuthConfig id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The unique identifier of the authentication configuration.
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

  public AuthConfig loginUrl(String loginUrl) {
    this.loginUrl = loginUrl;
    return this;
  }

  /**
   * The URL for the endpoint that provides authentication functionality.
   * @return loginUrl
  */
  @NotNull 
  @JsonProperty("loginUrl")
  public String getLoginUrl() {
    return loginUrl;
  }

  public void setLoginUrl(String loginUrl) {
    this.loginUrl = loginUrl;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AuthConfig authConfig = (AuthConfig) o;
    return Objects.equals(this.form, authConfig.form) &&
        Objects.equals(this.id, authConfig.id) &&
        Objects.equals(this.loginUrl, authConfig.loginUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(form, id, loginUrl);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AuthConfig {\n");
    sb.append("    form: ").append(toIndentedString(form)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    loginUrl: ").append(toIndentedString(loginUrl)).append("\n");
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

