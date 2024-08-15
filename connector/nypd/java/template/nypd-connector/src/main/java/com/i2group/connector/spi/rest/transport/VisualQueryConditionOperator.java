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
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The operator to use in the Visual Query condition.  Operations on logical types whose data type is `string` are case-insensitive. In the same context, a sequence of consecutive white space characters is equivalent to a single white space character.  Operations on logical types whose data type is `Calendar` perform their comparisons at the level of milliseconds.  Operations on logical types with other data types proceed as follows:  - For `DECIMAL`, `java.math.BigDecimal#doubleValue()` is used for the comparison - For `DOUBLE`, `Double#equals(Object)` is used for the comparison - For `INTEGER`, `Integer#equals(Object)` is used for the comparison - For `BOOLEAN`, `Boolean#equals(Object)` is used for the comparison
 */

public enum VisualQueryConditionOperator {
  
  STARTS_WITH("STARTS_WITH"),
  
  ENDS_WITH("ENDS_WITH"),
  
  EQUAL_TO("EQUAL_TO"),
  
  NOT_EQUAL_TO("NOT_EQUAL_TO"),
  
  GREATER_THAN("GREATER_THAN"),
  
  GREATER_THAN_OR_EQUAL_TO("GREATER_THAN_OR_EQUAL_TO"),
  
  LESS_THAN("LESS_THAN"),
  
  LESS_THAN_OR_EQUAL_TO("LESS_THAN_OR_EQUAL_TO"),
  
  BETWEEN("BETWEEN"),
  
  CONTAINS("CONTAINS"),
  
  WILDCARD_PATTERN("WILDCARD_PATTERN"),
  
  NOT_WILDCARD_PATTERN("NOT_WILDCARD_PATTERN"),
  
  IS_SET("IS_SET"),
  
  IS_NOT_SET("IS_NOT_SET"),
  
  IN_GEOSPATIAL_AREA("IN_GEOSPATIAL_AREA");

  private String value;

  VisualQueryConditionOperator(String value) {
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
  public static VisualQueryConditionOperator fromValue(String value) {
    for (VisualQueryConditionOperator b : VisualQueryConditionOperator.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

