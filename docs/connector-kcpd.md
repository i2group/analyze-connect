# Kansas City Police Department (KCPD) Connector

The KCPD connector connects to the [KCPD Crime 2020 Dataset](https://data.kcmo.org/Crime/KCPD-Crime-Data-2020/vsgj-uufz/) as the external datasource and marshals the data into entities, links and properties. This dataset contains reports on the criminal incidents which the Kansas City Police Department of Missouri have recorded.

There are a number of fields in the dataset:

| Column Name          | Type
|----------------------|------------------------------
| _Report\_No_         | Plain Text
| _Reported\_Date_     | Date & Time
| _Reported\_Time_     | Plain Text
| _From\_Date_         | Date & Time
| _From\_Time_         | Plain Text
| _To\_Date_           | Date & Time
| _To\_Time_           | Plain Text
| _Offense_            | Plain Text
| _IBRS_               | Plain Text
| _Description_        | Plain Text
| _Beat_               | Plain Text
| _Address_            | Plain Text
| _City_               | Plain Text
| _Zip Code_           | Plain Text
| _Rep\_Dist_          | Plain Text
| _Area_               | Plain Text
| _DVFlag_             | Plain Text
| _Involvement_        | Plain Text
| _Race_               | Plain Text
| _Sex_                | Plain Text
| _Age_                | Number
| _Firearm Used Flag_  | Checkbox
| _Location_           | Location

## Data model

The KCPD schema models the KCPD Crime 2020 Dataset using its relevant fields. Each row of data can be represented by three entities (Report, Person and Location) and a number of appropriate links between them alongside properties extracted from each field.

The schema (and charting schemes) for the KCPD connector can be found in the `schema` directory of this repository.

Where **Property Type** is the name of the schema property, **Logical Type** is the property's data type, and **Derived From** is the field of the external dataset where the property is derived from.

#### Entity: Report

Represents a report about a crime.

| Property Type        | Logical Type        | Derived From
|----------------------|---------------------|--------------
| Report Number        | SINGLE_LINE_STRING  | _Report\_No_
| Report Date          | DATE                | _Reported\_Date_
| From Date            | DATE                | _From\_Date_
| To Date              | DATE                | _To\_Date_
| From Time            | TIME                | _From\_Time_
| To Time              | TIME                | _To\_Time_
| Offense              | SINGLE_LINE_STRING  | _Offense_
| Offense Description  | SINGLE_LINE_STRING  | _Description_
| Domestic Violence    | BOOLEAN             | _DVFlag_

#### Entity: Person

Represents a person somehow involved in a reported crime.

| Property Type        | Logical Type        | Derived From
|----------------------|---------------------|-------------
| Race                 | SINGLE_LINE_STRING  | _Race_
| Sex                  | SUGGESTED_FROM      | _Sex_
| Age                  | SUGGESTED_FROM      | _Age_


#### Entity: Location

Represents a location at which a reported crime occurred.

| Property Type        | Logical Type        | Derived From
|----------------------|---------------------|-------------
| City                 | SINGLE_LINE_STRING  | _City_
| Address              | SINGLE_LINE_STRING  | _Address_
| Zip Code             | INTEGER             | _Zip Code_
| Coordinates          | GEOSPATIAL          | _Location_

#### Links

Establishes some connection between a Report, a Location and a Person. The KCPD Dataset's _Involvement_ field is used to determine how a Person is linked to a Report.

| Link Type            | Link Ends
|----------------------|------------------------------
| Located At           | Report -> Location
| Suspect Of           | Person -> Report
| Victim Of            | Person -> Report
| Complicit In         | Person -> Report
| Arrested             | Person -> Report
| Charged              | Person -> Report

## Setup

These instructions are for setting up and running the KCPD connector.

If you are not familiar with [deploying i2 Analyze with the i2 Connect gateway](./deploy-i2-analyze.md) and have not previously done so, you must do so now.

### 1. Add connector to topology:

In your `topology.xml` file in `toolkit\configuration\environment`, add a new `<connector-id>` element for the KCPD connector:

```xml
<wars>
  <war ... name="opal-services-daod" ... >
    ...
    <connector-ids>
      <connector-id value="kcpd-connector"/>
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
    <connector base-url="http://localhost:9083" name="KCPD Connector" id="kcpd-connector"/>
  </connectors>
</ns1:topology>
```

Ensure that you're using the same port as specified in `application.properties` (`9083` by default) and that the value of the `id` attribute is the name as the `value` attribute of its corresponding `<connector-id>`.

### 2. Configure the schema

Choose whether you want to configure the KCPD schema as a connector schema or a gateway schema.

#### Connector schema

By default, your connector will be configured to use a connector schema. This is due to the presence of the `schemaUrl`, `chartingSchemesUrl` and `schemaShortName` in the KCPD connector's `config.json` in `kcpd\kcpd-connector\src\main\resources`.

Additionally, ensure the following:

+ The connector's `config.json` **does not** contain a `gatewaySchema` property;
+ The KCPD `<connector>` element in your i2 Analyze topology **does not** contain a `gateway-schema` attribute.

See for more information on [configuring a connector schema](./connector-schema.md).

#### Gateway schema

If you want to set up the KCPD schema as a gateway schema, follow the guidelines for [configuring a gateway schema](./gateway-schema.md) using the KCPD schema and charting scheme found in the `schema` directory of this repository.

Additionally, ensure the following:

+ The connector's `config.json` in `kcpd\kcpd-connector\src\main\resources` **does not** contain the `schemaUrl`, `chartingSchemesUrl` and `schemaShortName` properties. These properties exist on the configuration by default; if they are present, remove them.
+ The KCPD `<connector>` element in your i2 Analyze topology **does not** contain a `schema-short-name` attribute.

### 3. Acquire Socrata token

In order to query the external datasource, a Socrata app token is required. If you do not already have a Socrata app token, you will need to generate one. Instructions on how to generate this token can be found [here](../docs/connect-to-eds.md#create-a-socrata-app-token).

In the KCPD connector's `application.properties` file at `connector\kcpd\kcpd-connector\src\main\resources`, add your token.

```properties
server.port=9083
socrata.url=https://data.kcmo.org/resource/vsgj-uufz.json

# API Token. Create a Socrata account and create an API Token. Paste it here
socrata.api.token=
```
    
### 3. Run the KCPD connector

To run the connector, navigate to `connector\kcpd\kcpd-connector` in your terminal and run the application using the following command:

```
mvnw spring-boot:run
```

For more information on running this repository's Java connectors, see [running in command line with Java](./run-in-cmd-java.md) and [running with VSCode](./run-in-vscode-java.md).

### 4. Deploy and start i2 Analyze

Deploy and start the Liberty server.

```
setup -t deployLiberty
setup -t startLiberty
```