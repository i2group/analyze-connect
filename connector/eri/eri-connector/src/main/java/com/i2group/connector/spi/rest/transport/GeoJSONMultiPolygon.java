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
import com.i2group.connector.spi.rest.transport.Geometry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

/**
 * The definitions of several polygons in geospace.
 */
public class GeoJSONMultiPolygon extends Geometry {

  @Valid
  public List<List<List<@Valid List<Double>>>> coordinates = new ArrayList<>();

  public GeoJSONMultiPolygon() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public GeoJSONMultiPolygon(List<List<List<@Valid List<Double>>>> coordinates, TypeEnum type) {
    super(type);
    this.coordinates = coordinates;
  }

  public GeoJSONMultiPolygon coordinates(List<List<List<@Valid List<Double>>>> coordinates) {
    this.coordinates = coordinates;
    return this;
  }

  public GeoJSONMultiPolygon addCoordinatesItem(List<List<@Valid List<Double>>> coordinatesItem) {
    if (this.coordinates == null) {
      this.coordinates = new ArrayList<>();
    }
    this.coordinates.add(coordinatesItem);
    return this;
  }

  /**
   * The geospatial coordinates of the vertices of polygons.
   * @return coordinates
  */
  @NotNull @Valid @Size(min = 1) 
  @JsonProperty("coordinates")
  public List<List<List<@Valid List<Double>>>> getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(List<List<List<@Valid List<Double>>>> coordinates) {
    this.coordinates = coordinates;
  }

  public GeoJSONMultiPolygon type(TypeEnum type) {
    super.type(type);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GeoJSONMultiPolygon geoJSONMultiPolygon = (GeoJSONMultiPolygon) o;
    return Objects.equals(this.coordinates, geoJSONMultiPolygon.coordinates) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(coordinates, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GeoJSONMultiPolygon {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    coordinates: ").append(toIndentedString(coordinates)).append("\n");
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

