# Gateway schemas

A gateway schema is a type of i2 Analyze schema that defines the item types that can
be used by any number of connectors. A gateway schema allows multiple connectors to return
the same item types, which makes them useful in situations where
multiple connectors return similar data.

Gateway schemas can be used in i2 Analyze deployments that include the i2 Connect
gateway. Gateway schemas are optional, and there is no limit to the number of gateway schemas that can be
deployed. A connector can return item types from one gateway schema only.

For more information about the kinds of schema that you can deploy, see [i2 Analyze schemas](https://www.ibm.com/support/knowledgecenter/SSXVTH_latest/com.ibm.i2.understanding.doc/i2_analyze_schemas.html).


To create a gateway schema, use i2 Analyze Schema Designer. This provides an
interface for creating the XML file containing the schema and, optionally, an
additional XML file containing the charting schemes. To learn more about how to
develop i2 Analyze schemas, see [Designing an i2 Analyze schema](./schema-design-guide.md).

## Gateway schema short names

All gateway schemas have a short name that is displayed to analysts
in i2 Analyst's Notebook Premium when they interact with entity and link types
from that schema. The short name must be unique within the i2
Analyze deployment. You specify the short name when you configure the gateway schema.

For a connector to return item types defined in a gateway
schema, the connector must be assigned to that gateway schema using the short name of the schema.

## Configuring a gateway schema

To configure a gateway schema, you must have a schema XML file and provide a
short name for it. You can also provide a charting schemes XML file.

To configure a gateway schema:
1. Copy your schema and charting schemes files to the
   `configuration/fragments/common/WEB-INF/classes` directory in your i2 Analyze
   configuration.
2. Open the `ApolloServerSettingsMandatory.properties` file in the same
   directory, and add the following properties
   ```properties
   Gateway.<SCHEMA_SHORT_NAME>.SchemaResource=<SCHEMA_XML_FILE_NAME>
   Gateway.<SCHEMA_SHORT_NAME>.ChartingSchemesResource=<CHARTING_SCHEMES_XML_FILE_NAME>
   ```

For example, to configure a gateway schema that defines item types
relating to telecommunications data:

- The schema file is `telecom-schema.xml`
- The charting schemes file is `telecom-schema-charting-schemes.xml`
- The short name is `Telecom`

Add the following in `ApolloServerSettingsMandatory.properties`:

```properties
Gateway.Telecom.SchemaResource=telecom-schema.xml
Gateway.Telecom.ChartingSchemesResource=telecom-schema-charting-schemes.xml
```

## Assigning a connector to a gateway schema

For a connector to be able to return item types defined in a gateway schema,
you must assign it to the gateway schema using the short name of the gateway schema.
You can assign the connector in two ways:

- The connector can supply the short name of the gateway schema to be assigned to in the configuration that is returned from its configuration endpoint.
- You can specify the short name of the gateway schema that the connector is assigned to when defining the connector in the i2 Analyze topology (`topology.xml`).

The gateway schema that a connector is assigned to in the i2 Analyze topology takes
precedence over the one specified by the connector in its configuration.

### Assigning a connector to a gateway schema in the connector configuration

In the configuration returned from configuration endpoint, a
gateway schema short name can be specified using the `gatewaySchema` field of
the `ConnectorConfig` object. For example:

```json
{
    "gatewaySchema": "Telecom",
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

### Assigning a connector to a gateway schema in the i2 Analyze topology

To assign a connector to a gateway schema in the i2 Analyze topology, specify
the gateway schema short name in the `connector` element using the
`gateway-schema` attribute. For example:

```xml
<connector id="example-connector" name="Example Connector" base-url="http://localhost:3700/" gateway-schema="Telecom" />
```
