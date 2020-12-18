# New York Police Department (NYPD) Connector

The NYPD connector connects to the [NYPD Complaint Data](https://data.cityofnewyork.us/Public-Safety/NYPD-Complaint-Data-Current-Year-To-Date-/5uac-w243) and marshals the data into entities, links and properties. The dataset contains information on the felony, misdemeanor, and violation crimes reported to the New York City Police Department.

There are a number of fields in the dataset, of which the following were used:

| Column Name                                                 | Type
|-------------------------------------------------------------|----------------
| _CMPLNT\_NUM (Complaint Number)_                            | Number
| _ADDR\_PCT\_CD (Precinct Code)_                             | Number
| _BORO\_NM (Borough Name)_                                   | Plain Text
| _CMPLNT\_FR\_DT (Complaint From Date)_                      | Date & Time
| _CMPLNT\_FR\_TM (Complaint From Time)_                      | Plain Text
| _CMPLNT\_TO\_DT (Complaint To Date)_                        | Date & Time
| _CMPLNT\_TO\_TM (Complaint To Time)_                        | Plain Text
| _CRM\_ATPT\_CPTD\_CD (Crime Atempted-Completed Code)_       | Plain Text
| _HADEVELOPT (Housing Development)_                          | Plain Text
| _JURISDICTION\_CODE (Jurisdiction Code)_                    | Number
| _JURIS\_DESC (Jurisdiction Description)_                    | Plain Text
| _KY\_CD (Key Code)_                                         | Number
| _LAW\_CAT\_CD (Law Category Code)_                          | Plain Text
| _LOC\_OF\_OCCUR\_DESC (Location of Occurence Description)_  | Plain Text
| _OFNS\_DESC (Offense Description)_                          | Plain Text
| _PARKS\_NM (Park Name)_                                     | Plain Text
| _PATROL\_BORO (Patrol Borough)_                             | Plain Text
| _PD\_CD (PD Code)_                                          | Number
| _PD\_DESC (PD Description)_                                 | Plain Text
| _PREM\_TYPE_DESC (Premise Type Description)_                 | Plain Text
| _RPT\_DT (Report Date)_                                      | Date & Time
| _STATION\_NAME (Station Name)_                               | Plain Text
| _SUSP\_AGE\_GROUP (Suspect's Age Group)_                      | Plain Text
| _SUSP\_RACE (Suspect's Race)_                                | Plain Text
| _SUSP\_SEX (Suspect's Sex)_                                  | Plain Text
| _TRANSIT\_DISTRICT (Transit District)_                       | Number
| _VIC\_AGE_GROUP (Victim's Age Group)_                        | Plain Text
| _VIC\_RACE (Victim's Race)_                                  | Plain Text
| _VIC\_SEX (Victim's Sex)_                                    | Plain Text
| _Latitude_                                                  | Number
| _Longitude_                                                 | Number

## Data model

The NYPD schema models the NYPD Complaint Dataset by using its relevant fields. Each row of data can be represented by three entities (Complaint, Person and Location) and a number of appropriate links between them alongside properties extracted from each field.

The schema (and charting schemes) for the NYPD connector can be found in the `schema` directory of this repository.

Where **Property Type** is the name of the schema property, **Logical Type** is the property's data type, and **Derived From** is the field of the external dataset where the property is derived from.

#### Entity: Complaint

Represents a crime complaint.

| Property Type                | Logical Type        | Derived From
|------------------------------|---------------------|--------------
| Complaint Number             | SINGLE_LINE_STRING  | _CMPLNT\_NUM_
| Complaint Start Date         | DATE                | _CMPLNT\_FR\_DT_
| Complaint End Date           | DATE                | _CMPLNT\_TO\_DT_
| Complaint Start Time         | TIME                | _CMPLNT\_FR\_TM_
| Complaint End Time           | TIME                | _CMPLNT\_TO\_TM_
| Crime Status                 | SUGGESTED_FROM      | _CRM\_ATPT\_CPTD\_CD_
| Jurisdiction Code            | INTEGER             | _JURISDICTION\_CODE_
| Jurisdiction Description     | SINGLE_LINE_STRING  | _JURIS\_DESC_
| Offence Classification Code  | SINGLE_LINE_STRING  | _KY\_CD_
| Level of Offence             | SUGGESTED_FROM      | _LAW\_CAT\_CD_
| Offence Description          | SINGLE_LINE_STRING  | _OFNS\_DESC_
| Internal Classification Code | INTEGER             | _PD\_CD_
| Classification Description   | SINGLE_LINE_STRING  | _PD\_DESC_
| Event Date                   | DATE                | _RPT\_DT_
| Location of Occurrence       | SUGGESTED_FROM      | _LOC\_OF\_OCCUR\_DESC_

#### Entity: Location

Represents the location of a reported crime.

| Property Type                | Logical Type        | Derived From
|------------------------------|---------------------|--------------
| Precinct Code                | INTEGER             | _ADDR\_PCT\_CD_
| Borough Name                 | SINGLE_LINE_STRING  | _BORO\_NM_
| Housing Development          | SINGLE_LINE_STRING  | _HADEVELOPT_
| Park Name                    | SINGLE_LINE_STRING  | _PARKS\_NM_
| Patrol Borough               | SINGLE_LINE_STRING  | _PATROL\_BORO_
| Premises Description         | SINGLE_LINE_STRING  | _PREM\_TYP\_DESC_
| Station Name                 | SINGLE_LINE_STRING  | _STATION\_NAME_
| Transit District             | INTEGER             | _TRANSIT\_DISTRICT_
| Coordinates                  | GEOSPATIAL          | _Latitude_ & _Longitude_

#### Entity: Person

Represents a person somehow involved in a reported crime. Since each record of the NYPD Complaint Dataset has information on both victims and suspects, two Person entities are created from a single record.

| Property Type        | Logical Type         | Derived From
|----------------------|----------------------|-------------
| Age Group            | SUGGESTED_FROM       | _SUSP\_AGE\_GROUP_ or _VIC\_AGE\_GROUP_
| Race                 | SINGLE_LINE_STRING   | _SUSP\_RACE_ or _VIC\_RACE_
| Sex                  | SUGGESTED_FROM       | _SUSP\_SEX_ or _VIC\_SEX_

#### Links

Establishes some connection between a Complaint, a Location and a Person.

| Link Type            | Link Ends
|----------------------|------------------------------
| Located At           | Complaint -> Location
| Suspect Of           | Person -> Complaint
| Victim Of            | Person -> Complaint

## Setup

These instructions are for setting up and running the NYPD connector.

If you are not familiar with [deploying i2 Analyze with the i2 Connect gateway](./deploy-i2-analyze.md) and have not previously done so, you must do so now.

### 1. Add connector to topology:

In your `topology.xml` file in `toolkit\configuration\environment`, add a new `<connector-id>` element for the NYPD connector:

```xml
<wars>
  <war ... name="opal-services-daod" ... >
    ...
    <connector-ids>
      <connector-id value="nypd-connector"/>
    </connector-ids>
    ...
  </war>
</wars>
```

Additionally, add a new `<connector>` to the topology:

```xml
<ns1:topology ...>
  ...
  <connectors>
    <connector base-url="http://localhost:9081" name="NYPD Connector" id="nypd-connector"/>
  </connectors>
</ns1:topology>
```

Ensure that you're using the same port as specified in `application.properties` (`9081` by default) and that the value of the `id` attribute is the name as the `value` attribute of its corresponding `<connector-id>`.

### 2. Configure the schema

Choose whether you want to configure the NYPD schema as a connector schema or a gateway schema.

#### Connector schema

By default, your connector will be configured as a connector schema. This is due to the presence of the `schemaUrl`, `chartingSchemesUrl` and `schemaShortName` in the NYPD connector's `config.json` in `nypd\nypd-connector\src\main\resources`.

Additionally, ensure the following:

+ The connector's `config.json` **does not** contain a `gatewaySchema` property;
+ The NYPD `<connector>` element in your i2 Analyze topology **does not** contain a `gateway-schema` attribute.

See for more information on [configuring a connector schema](./connector-schema.md).

#### Gateway schema

If you want to set up the NYPD schema as a gateway schema, follow the guidelines for [configuring a gateway schema](./gateway-schema.md) using the NYPD schema and charting scheme found in the `schema` directory of this repository.

Additionally, ensure the following:

+ The connector's `config.json` in `nypd\nypd-connector\src\main\resources` **does not** contain the `schemaUrl`, `chartingSchemesUrl` and `schemaShortName` properties. These properties exist on the configuration by default; if they are present, remove them.
+ The NYPD `<connector>` element in your i2 Analyze topology **does not** contain a `schema-short-name` attribute.

### 3. Acquire Socrata token

In order to query the external datasource, a Socrata app token is required. If you do not already have a Socrata app token, you will need to generate one. Instructions on how to generate this token can be found [here](../docs/connect-to-eds.md#create-a-socrata-app-token).

In the NYPD connector's `application.properties` file at `connector\nypd\nypd-connector\src\main\resources`, add your token.

```properties
server.port=9081
socrata.url=https://data.cityofnewyork.us/resource/7x9x-zpz6.json

# API Token. Create a Socrata account and create an API Token. Paste it here
socrata.api.token=
```
    
### 3. Run the NYPD connector

To run the connector, navigate to `connector\nypd\nypd-connector` in your terminal and run the application using the following command:

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