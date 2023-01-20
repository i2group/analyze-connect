/*
 * MIT License
 *
 * © N.Harris Computer Corporation (2023)
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

package com.i2group.demo.rest.externalsource.transport;

import lombok.ToString;

/**
 * A POJO (Plain Old Java Object) for response objects from Socrata. These represent the fields in
 * the NYPD database.
 */
@ToString
public class SocrataResponseData {
  // You can map properties using
  // the @JsonProperty("field_name") annotation if you want to rename them, or
  // directly refer to them to use the transport names
  //
  // The Jackson ObjectMapper can deserialize data into appropriate types in many
  // cases. For examples
  // "stringField" : "This is a string" => public String stringField;
  // "dateAndTimeField" :"2018-01-12T00:00:00.000" => public LocalDateTime dateAndTimeField;
  // "numberField" : 1 => public int numberField;
  // "optionalNumberField" : null => public Integer optionalNumberField;

  // TODO: Add response fields to this class.
}
