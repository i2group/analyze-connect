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
 * The information that the source reference contains.
 */
public class SourceReferenceInfo {

  public String description;

  public String image;

  public String location;

  public String name;

  public String type;

  public SourceReferenceInfo() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public SourceReferenceInfo(String name) {
    this.name = name;
  }

  public SourceReferenceInfo description(String description) {
    this.description = description;
    return this;
  }

  /**
   * The description of a source.
   * @return description
  */
  
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public SourceReferenceInfo image(String image) {
    this.image = image;
    return this;
  }

  /**
   * The URL of an image of a source.
   * @return image
  */
  
  @JsonProperty("image")
  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public SourceReferenceInfo location(String location) {
    this.location = location;
    return this;
  }

  /**
   * The location of a source, which might be a URL.
   * @return location
  */
  
  @JsonProperty("location")
  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public SourceReferenceInfo name(String name) {
    this.name = name;
    return this;
  }

  /**
   * The name of a source.
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

  public SourceReferenceInfo type(String type) {
    this.type = type;
    return this;
  }

  /**
   * The type of a source.
   * @return type
  */
  
  @JsonProperty("type")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SourceReferenceInfo sourceReferenceInfo = (SourceReferenceInfo) o;
    return Objects.equals(this.description, sourceReferenceInfo.description) &&
        Objects.equals(this.image, sourceReferenceInfo.image) &&
        Objects.equals(this.location, sourceReferenceInfo.location) &&
        Objects.equals(this.name, sourceReferenceInfo.name) &&
        Objects.equals(this.type, sourceReferenceInfo.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(description, image, location, name, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SourceReferenceInfo {\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

