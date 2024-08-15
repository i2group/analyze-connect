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
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

/**
 * The identifier of a record in the Information Store.
 */
public class InfoStoreIdentifier implements EntityDataIdentifier {

  public String infoStoreRecordId;

  public InfoStoreIdentifier() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public InfoStoreIdentifier(String infoStoreRecordId) {
    this.infoStoreRecordId = infoStoreRecordId;
  }

  public InfoStoreIdentifier infoStoreRecordId(String infoStoreRecordId) {
    this.infoStoreRecordId = infoStoreRecordId;
    return this;
  }

  /**
   * The record identifier of an Information Store record.
   * @return infoStoreRecordId
  */
  @NotNull 
  @JsonProperty("infoStoreRecordId")
  public String getInfoStoreRecordId() {
    return infoStoreRecordId;
  }

  public void setInfoStoreRecordId(String infoStoreRecordId) {
    this.infoStoreRecordId = infoStoreRecordId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InfoStoreIdentifier infoStoreIdentifier = (InfoStoreIdentifier) o;
    return Objects.equals(this.infoStoreRecordId, infoStoreIdentifier.infoStoreRecordId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(infoStoreRecordId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InfoStoreIdentifier {\n");
    sb.append("    infoStoreRecordId: ").append(toIndentedString(infoStoreRecordId)).append("\n");
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

