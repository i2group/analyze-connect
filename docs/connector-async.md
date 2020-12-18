# Async Connector

The Async connector demonstrates the ability for a connector service to query data asynchronously. Asynchronous queries allow you to run multiple queries concurrently, and allow long running queries to persist after the user logs out. The example connector connects to a JSON file that is populated with sample data (people and their friends) and marshals the data into entities, links and properties.

There are a number of fields in the dataset:

| Field Name                     | Type
|--------------------------------|------------------------------
| _id_                           | String
| _forename_                     | String
| _surname_                      | String
| _dob_                          | String
| _ssn_                          | String
| _issuedDateAndTime_            | String
| _friends_                      | Array of Strings
| _image_                        | String

## Data model

The Async schema is modelled on the fields of the `people.json` data source which can be found in `async\async-connector\src\main\resources`. Each entry in the `people` object is represented by a `Person` entity and a `Friends With` link to their friends alongside the properties that are extracted from each field.

The schema (and charting schemes) for the Async connector are in the `schema` directory of this repository.



#### Entity: Person

Represents a person in the `people` object.

Where **Property Type** is the name of the schema property, **Logical Type** is the property's data type, and **Derived From** is the field of the external dataset where the property is derived from.

| Property Type                | Logical Type             | Derived From
|------------------------------|--------------------------|--------------------------
| First Name                   | SINGLE_LINE_STRING       | _forename_
| Last Name                    | SINGLE_LINE_STRING       | _surname_
| Year of Birth                | DATE                     | _dob_
| Social Security Number       | SINGLE_LINE_STRING       | _ssn_
| SSN Issued Date and Time     | DATE_AND_TIME            | _issuedDateAndTime_

#### Link

Establishes a connection between two Person entities.

| Link Type                      | Link Ends
|--------------------------------|--------------------------
| Friends With                   | Person --> Person

## Adding an async service

### 1. Adding the async service

For a general understanding about how to add a service, see [Adding a service](./add-service.md).

To define a service as asynchronous, the service object must be updated.

| Field                          | Description                                         
|--------------------------------|----------------------------------------------------
| **async**                      | Indicates that the service must be called asynchronously, and provides configuration settings. If this is present, then **acquireUrl** must not be present.

#### Asynchronous configuration

| Field                          | Description                                         
|--------------------------------|----------------------------------------------------
| **queriesResource**            | This specifies the URL for i2 Analyze to use to get data asynchronously from the service.
| **pollingIntervalInSeconds**   | (Optional) The recommended interval at which clients should poll asynchronous endpoints for changes.

The connector configuration matches the example below when it contains an async service. The full configuration of the example asynchronous connector is in `connector\async\async-connector\src\main\resources\config.json`.

```json
{
  "defaultValues": {
    "timeZoneId": "Europe/London",
    "resultIdsPersistent": true
  },
  "services": [
    {
      "id": "sample-async-service",
      "name": "Sample Connector: Async",
      "description": "A sample service that runs the sevice asynchronously",
      "async": {
        "queriesResource": "/async",
        "pollingIntervalInSeconds": 1
      },
      "clientConfigType": "NONE",
    }
  ],
  "clientConfigs": []
}
```

### 2. Implementing the acquire endpoint

**This endpoint starts an asynchronous query to fetch data from the service in the specified query resource.**

If the service in question also specifies the **validateUrl** property, then the gateway uses the **validateUrl** endpoint before it uses the **queriesResource** endpoint.

This service calls the POST method on the `{queriesResource}` endpoint and takes in a request parameter. It is a payload that the service can interpret to modify its behaviour. The service can have one of three types of client configuration:
- 'NONE' - the payload never contains conditions.
- 'FORM' - it contains conditions that have a fixed structure.
- 'CUSTOM' - the contents of the **conditions** object are free-form.

The response must contain a query ID that the client can use in subsequent requests to retrieve further information.

A sample solution has been written in **Java** and can be found in the `asyncAcquireService()` method in `ConnectorController` with it being implemented in `asyncAcquire()` in `ExternalConnectorDataService`.

### 3. Implementing the status endpoint

**This endpoint fetches the status of the specified asynchronous query from the specified queries resource.**

After a client starts an asynchronous query, it uses polling to determine its progress. The client polls the i2 Connect gateway, and the gateway calls through to the connector to retrieve its status.

This service calls the GET method on the `{queriesResource}/{queryId}` endpoint. It queries that endpoint using the provided `queryId` to identify the current state of the query. The response must contain one of three **state** values that indicate the status of the query: 'STARTED', 'SUCCEEDED', or 'FAILED'. The response can also contain a series of **substatuses** that report progress from the underlying data source. The four valid types of substatus are 'INFORMATION', 'WARNING', 'ERROR', and 'SUCCESS'.

A sample solution has been written in **Java** and can be found in the `asyncStatusService()` method in `ConnectorController` with it being implemented in `asyncStatus()` in `ExternalConnectorDataService`.

### 4. Implementing a result endpoint

**This endpoint fetches the results of the specified asynchronous query from the specified queries resource.**

Clients can attempt to retrieve results only from an asynchronous query whose state is 'SUCCEEDED'. An attempt to fetch results from a query in any other state must fail.

This service calls the GET method on the `{queriesResource}/{queryId}/results` endpoint. It queries that endpoint using the provided `queryId` to fetch the results of the successful query. The structure of the response is identical to the response from a synchronous query.

A sample solution has been written in **Java** and can be found in the `asyncResultsService()` method in `ConnectorController` with it being implemented in `asyncResults()` in `ExternalConnectorDataService`.


### 5. Implementing a delete endpoint

**This endpoint deletes the specified asynchronous query from the specified queries resource when it is no longer needed.**

The i2 Connect gateway calls the DELETE method when a query has succeeded, been cancelled, or failed. A connector can respond to the call by cleaning up any resources associated with processing the query.

This service calls the DELETE method on the `{queriesResource}/{queryId}` endpoint. It queries that endpoint using the provided `queryId` to cancel the query and delete the `queryId`. The response must be returned with the `queryId` removed.

A sample solution has been written in **Java** and can be found in the `asyncDeleteService()` method in `ConnectorController` with it being implemented in `asyncDelete()` in `ExternalConnectorDataService`.

## Setup

These instructions are for setting up and running the Async connector. The solution uses a client configuration of type FORM to demonstrate the use of an async query. The `duration` condition is used to demonstrate the time taken for a long running query to be executed.

If you are not familiar with [deploying i2 Analyze with the i2 Connect gateway](./deploy-i2-analyze.md) and have not previously done so, you must do so now.

### 1. Add connector to topology

In your `topology.xml` file in `toolkit\configuration\environment`, add a new `<connector-id>` element for the Async connector:

```xml
<wars>
  <war ... name="opal-services-daod" ... >
    ...
    <connector-ids>
      <connector-id value="async-connector"/>
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
    <connector base-url="http://localhost:9085" name="Async Connector" id="async-connector"/>
  </connectors>
</ns1:topology>
```

Ensure that you are using the same port as specified in `application.properties` (`9085` by default) and that the value of the `id` attribute is the same as the `value` attribute of its corresponding `<connector-id>`.

### 2. Configure the schema

Choose whether you want to configure the Async schema as a connector schema or a gateway schema.

#### Connector schema

By default, your connector is configured as a connector schema. This is because `schemaUrl`, `chartingSchemesUrl` and `schemaShortName` are defined in the Async connector's `config.json` in `async\async-connector\src\main\resources`.

Additionally, ensure the following is true:

- The connector's `config.json` **does not** contain a `gatewaySchema` property.
- The Async `<connector>` element in your i2 Analyze topology **does not** contain a `gateway-schema` attribute.

For more information, see [configuring a connector schema](./connector-schema.md).

#### Gateway schema

If you want to set up the Async schema as a gateway schema, follow the guidelines for [configuring a gateway schema](./gateway-schema.md) using the Async schema and charting scheme in the `schema` directory of this repository.

Additionally, ensure the following is true:

- The connector's `config.json` in `async\async-connector\src\main\resources` **does not** contain the `schemaUrl`, `chartingSchemesUrl` and `schemaShortName` properties. These properties exist on the configuration by default. If they are present, remove them.
- The Async `<connector>` element in your i2 Analyze topology **does not** contain a `schema-short-name` attribute.

### 3. Run the Async connector

To run the connector, navigate to `connector\async\async-connector` in your terminal and run the application using the following command:

```
mvnw spring-boot:run
```

### 4. Deploy and start i2 Analyze

Deploy and start the Liberty server.

```
setup -t deployLiberty
setup -t startLiberty
```
