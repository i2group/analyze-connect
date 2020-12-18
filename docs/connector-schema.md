# Connector Schemas

A connector schema is a type of i2 Analyze schema that is provided by a connector that defines some
or all of the item types the connector can return. Connector schemas allow for connectors to be added to,
or removed from, i2 Analyze deployments more easily without the need to change
the existing Information Store or gateway schemas. They also make it easier
for connectors to be shared and used by multiple deployments.

For more information about the kinds of schema that you can deploy, see [i2 Analyze schemas](https://www.ibm.com/support/knowledgecenter/SSXVTH_latest/com.ibm.i2.understanding.doc/i2_analyze_schemas.html).

To create a connector schema, use i2 Analyze Schema Designer. Schema Designer provides an
interface for creating the XML file containing the schema and, optionally, an
additional XML file containing the charting schemes. To learn more about how to
develop i2 Analyze schemas, see [Designing an i2 Analyze schema](./schema-design-guide.md).

## Configuring a connector schema

A connector schema is provided by the connector itself. To supply a schema, a connector must provide:

- An endpoint that returns the schema in its XML form from a GET request.
- The URL for the endpoint. The URL is provided in the `schemaUrl` field of its configuration, and it must be relative to the connector's base URL.

A connector schema can optionally be supplemented with a connector charting
scheme. To supply a charting scheme, a connector must provide:

- An endpoint that returns the charting scheme in its XML form from a GET request.
- The URL for the endpoint. The URL is provided in the `chartingSchemesUrl` field of its configuration, and it must be relative to the connector's base URL.

For example, if a connector's base URL is `http://exampleconnector.com:3700`, the schema and charting schemes endpoints might be accessible at the following URLs:

- `http://exampleconnector.com:3700/schema`
- `http://exampleconnector.com:3700/charting-schemes`

Then, you add the `schemaUrl` and `chartingSchemesUrl` fields to the
configuration that is returned from the configuration endpoint. For example:

```json
{
    "schemaUrl": "/schema",
    "chartingSchemesUrl": "/charting-schemes",
	"defaultValues": {
		...
	},
	"services": [
		...
	],
	"clientConfigs": [
		...
	]
}
```

## Connector schema short names

All connector schemas have a short name that is displayed to analysts
in i2 Analyst's Notebook Premium when they interact with entity and link types
from that schema. The short name of a connector schema can
be configured in two ways:

- The connector can specify the short name the configuration that is returned
  from its configuration endpoint.
- You can specify the short name of the schema when defining the
  connector in the i2 Analyze topology (`topology.xml`).

The short name that is supplied in the i2 Analyze topology takes precedence over the
one supplied in the connector configuration. If a short name is
not specified in either location, then the ID of the connector in the i2 Analyze topology is used.

### Specifying the schema short name in the connector configuration

To specify the short name in the configuration that is returned from the configuration endpoint, use the `schemaShortName` field of the `ConnectorConfig` object. For example:

```json
{
    "schemaUrl": "/schema",
    "chartingSchemesUrl": "/chartingschemes",
    "schemaShortName": "Social Media",
	"defaultValues": {
		...
	},
	"services": [
		...
	],
	"clientConfigs": [
		...
	]
}
```

For more information about the `ConnectorConfig` object, see the
[i2 Connect Gateway REST SPI](https://www.ibm.com/support/knowledgecenter/en/SSXVTH_latest/com.ibm.i2.connect.developer.doc/i2_connect_spi.json).

### Specifying the short name in the i2 Analyze topology

To specify the short name in the i2 Analyze topology, use the `schema-short-name` attribute of the `connector` element. For example:

```xml
<connector id="example-connector" name="Example Connector" base-url="http://localhost:3700/" schema-short-name="Social Media" />
```
