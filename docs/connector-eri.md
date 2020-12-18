# Emergency Response Incidents (ERI) Connector

The ERI connector connects to the [Emergency Response Incidents Dataset](https://data.cityofnewyork.us/Public-Safety/Emergency-Response-Incidents/pasr-j7fb) and marshals the data into entities, links and properties. The dataset contains types and locations of incidents which the Office of Emergency Management of New York City have responded to.

There are seven fields in the dataset:

| Column Name          | Type
|----------------------|------------------------------
| _Incident Type_      | Plain Text
| _Location_           | Plain Text
| _Borough_            | Plain Text
| _Creation Date_      | Date & Time
| _Closed Date_        | Date & Time
| _Latitude_           | Number
| _Longitude_          | Number

## Data model

The ERI schema models the Emergency Response Incidents Dataset by using all seven fields. Each row of data can be represented by two entities (Incident and Location) and a single link between them (Located At) alongside properties extracted from each field.

The schema (and charting schemes) for the ERI connector can be found in the `schema` directory of this repository.

#### Entity: Incident

Represents a reported incident.

Where **Property Type** is the name of the schema property, **Logical Type** is the property's data type, and **Derived From** is the field of the external dataset where the property is derived from.

| Property Type        | Logical Type           | Derived From
|----------------------|------------------------|--------------
| Incident Type        | SELECTED_FROM **\***   | _Incident Type_
| Incident Subtype     | SINGLE_LINE_STRING     | _Incident Type_
| Creation Date        | DATE                   | _Creation Date_
| Creation Time        | TIME                   | _Creation Date_
| Closed Date          | DATE                   | _Closed Date_
| Closed Time          | TIME                   | _Closed Date_

**\*** The possible values for Incident Type are: *Administration, Aviation, Fire, HazMat, Law Enforcement, Marine,
Medical, Rescue, Structural, Transportation, Utility, Weather* and *Other*.

#### Entity: Location

Represents the location at which the incident occurred.

| Property Type        | Logical Type           | Derived From
|----------------------|------------------------|--------------
| Borough              | SINGLE_LINE_STRING     | _Borough_
| Address              | SINGLE_LINE_STRING     | _Location_
| Coordinates          | GEOSPATIAL             | _Longitude_ & _Latitude_

#### Link

Associates an incident with the location at which it is reported to have occurred.

| Link Type            | Link Ends
|----------------------|------------------------------
| Located At           | Incident -> Location

## Setup

These instructions are for setting up and running the ERI connector.

If you are not familiar with [deploying i2 Analyze with the i2 Connect gateway](./deploy-i2-analyze.md) and have not previously done so, you must do so now.

### 1. Add connector to topology:

In your `topology.xml` file in `toolkit\configuration\environment`, add a new `<connector-id>` element for the ERI connector:

```xml
<wars>
  <war ... name="opal-services-daod" ... >
    ...
    <connector-ids>
      <connector-id value="eri-connector"/>
    </connector-ids>
    ...
  </war>
</wars>
```

Additionally, add a new `<connector>` element to the topology:

```xml
<ns1:topology ...>
  ...
  <connectors>
    <connector base-url="http://localhost:9084" name="ERI Connector" id="eri-connector"/>
  </connectors>
</ns1:topology>
```

Ensure that you're using the same port as specified in `application.properties` (`9084` by default) and that the value of the `id` attribute is the name as the `value` attribute of its corresponding `<connector-id>`.

### 2. Configure the schema

Choose whether you want to configure the ERI schema as a connector schema or a gateway schema.

#### Connector schema

By default, your connector is configured to use a connector schema. This is due to the presence of the `schemaUrl`, `chartingSchemesUrl` and `schemaShortName` in the ERI connector's `config.json` in `eri\eri-connector\src\main\resources`.

Additionally, ensure the following:

+ The connector's `config.json` **does not** contain a `gatewaySchema` property;
+ The ERI `<connector>` element in your i2 Analyze topology **does not** contain a `gateway-schema` attribute.

See for more information on [configuring a connector schema](./connector-schema.md).

#### Gateway schema

If you want to set up the ERI schema as a gateway schema, follow the guidelines for [configuring a gateway schema](./gateway-schema.md) using the ERI schema and charting scheme found in the `schema` directory of this repository.

Additionally, ensure the following is true:

+ The connector's `config.json` in `eri\eri-connector\src\main\resources` **does not** contain the `schemaUrl`, `chartingSchemesUrl` and `schemaShortName` properties. These properties exist on the configuration by default; if they are present, remove them.
+ The ERI `<connector>` element in your i2 Analyze topology **does not** contain a `schema-short-name` attribute.

### 3. Acquire Socrata token

In order to query the external datasource, a Socrata app token is required. If you do not already have a Socrata app token, you will need to generate one. Instructions on how to generate this token can be found [here](../docs/connect-to-eds.md#create-a-socrata-app-token).

In the ERI connector's `application.properties` file at `connector\eri\eri-connector\src\main\resources`, add your token.

```properties
server.port=9084
socrata.url=https://data.cityofnewyork.us/resource/pasr-j7fb.json

# API Token. Create a Socrata account and create an API Token. Paste it here
socrata.api.token=
```
    
### 4. Run the ERI connector

To run the connector, navigate to `connector\eri\eri-connector` in your terminal and run the application using the following command:

```
mvnw spring-boot:run
```

For more information about running this repository's Java connectors, see [running in command line with Java](./run-in-cmd-java.md) and [running with VSCode](./run-in-vscode-java.md).

### 5. Deploy and start i2 Analyze

Deploy and start the Liberty server.

```
setup -t deployLiberty
setup -t startLiberty
```