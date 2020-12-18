# The i2 Analyze data model
All the properties defined in a schema, of both entites and links, have what is
called a logical type. This defines the kind of data that a property represents
and the format of it. Some properties, for example, respresent numerical data,
whereas others represent textual data. Furthermore, of those properties
representing numerical data, some will be integer-valued, whereas others will
have floating-point values. We use logical types to make these distinctions, and
there are fifteen of them:

* SINGLE_LINE_STRING
* MULTIPLE_LINE_STRING
* DATE
* TIME
* DATE_AND_TIME
* BOOLEAN
* INTEGER
* DOUBLE
* DECIMAL
* DOCUMENT*
* XML*
* PICTURE*
* SELECTED_FROM
* SUGGESTED_FROM
* GEOSPATIAL

*The i2 Connect gateway **does not** support the returning of properties with
logical type XML, DOCUMENT or PICTURE.

To see the logical type of a property, open the schema in IBM i2 Analyze Schema
Designer and click on a property in the left-hand pane. You should see its
logical type, along with its ID, name, and description. 

When returning entities and links to i2 Analyze from a connector, we must ensure
that the property values we assign align with the logical type of those
properties. For example, the i2 Analyze server would complain if we tried to
assign the value "fourty-two" to a property representing the age of a person,
that has logical type INTEGER. "fourty-two" is a string and should perhaps be
changed to 42. See examples below of valid data for each of the valid logical
types.

## Examples
| Logical Type         | Example(s)
|----------------------|--------------------------------------------------------
| SINGLE_LINE_STRING   | `"Up to 250 bytes of UTF-8 characters"`
| MULTIPLE_LINE_STRING | `"Up to 32700 bytes of UTF-8 characters"`
| DATE                 | `"2019-11-18"`
| TIME                 | `"16:34:11"` or `"16:34"`. Seconds are optional, but hours and minutes are not. Greater precision than seconds is not supported.
| DATE_AND_TIME        | Four pieces of information are required to specify a particular point in time: <ul><li>The date</li><li>The time as it would appear on a clock</li><li>The time zone in which the point in time is observed</li><li>Daylight Saving Time: If the point in time occurs during the hour that is repeated when clocks are turned back to end Daylight Saving Time, whether to use the first or second occurence of this time.</li></ul> There are two ways of providing this information.<ol><li>Provide just the date and time, in which case the time zone is taken to be the default time zone defined in the connector configuration. This must be specfied as a string conforming to [ISO 8601](https://en.wikipedia.org/wiki/ISO_8601) with at least minute-precision and up to millisecond-precision. For example:<ul><li>`"2019-10-29T14:23:55"`</li><li>`"2019-10-29T14:23:55.1"`</li><li>`"2019-10-29T14:23:55.123"`</li></ul><li>Provide all four pieces of information in the form ```{"localDateAndTime": "2019-10-29T14:23:55.487", "timeZoneId": "Australia/Perth", "isDST": true}```, where:<ul><li>`localDateAndTime` is a timestamp string conforming to [ISO 8601](https://en.wikipedia.org/wiki/ISO_8601) as above, again with at least minute-precision and up to millisecond-precision.</li><li>`timeZoneId` specifies the time zone in which the moment of time is observed. For a list of valid time zone IDs, see the [IANA Time Zone Database](https://www.iana.org/time-zones).</li><li>`isDST` is used to specify which occurence of the repeated hour to use if the point in time occurs when the clocks turn back at the end of Daylight Saving Time. To use the first, set it to `true`; to use the second, set it to `false`.</li></ul></li></li>
| BOOLEAN              | `true` or `false`
| INTEGER              | `42`, `-9812`
| DOUBLE               | `-213.89763`, `0.00083`
| DECIMAL              | `99.9999` (maximum of four digits after decimal point)
| SELECTED_FROM        | `"Red"`, `"Green"`, or `"Blue"` if choosing from a set of colors, for example.
| SUGGESTED_FROM       | `"Blue-ish green"`, if you need to be more specific than the suggested options.
| GEOSPATIAL           | `{"type": "Point", "coordinates": [11.1, 12.2]}`, where the coordinates are latitude and longitude.
