# Seeded search

A seeded search passes information from an entity on the chart to the connector so that you can use that information to drive your searches. In order to pass an entity as a seed, select an entity in Analyst's Notebook Premium and open "External Search".

Again, if you face any issues during this task, remember to consult the
[troubleshooting guide](./troubleshoot.md).

## Configuration

You need to configure a service to allow for seeded searches.

### Add a new service

You will need to add a new service for seeded searches to the services array in the `config.json`. You will also need to add `seedConstraints` which define the entities allowed by the seeded search. For example:

```json
{
  "services": [
    ...
    {
      "id": "nypd-find-like-this-complaint-service",
      "name": "NYPD Connector: Find like this Complaint",
      "description": "A service which finds a similar complaint",
      "resultItemTypeIds": ["made-up-schema-type-id (e.g. ET1)"],
      "clientConfigType": "NONE",
      "acquireUrl": "/find-like-this-complaint",
      "seedConstraints":{
        "min":1,
        "max":1,
        "seedTypes":{
          "allowedTypes":"ENTITY",
            "itemTypes":[
            {
              "id":"made-up-schema-type-id (e.g. ET1)",
              "min":1,
              "max":1
            }
          ]
        }
      }
    },
    {
      "id": "nypd-expand-service",
      "name": "NYPD Connector: Expand",
      "description": "A service which executes an Expand operation on a seed",
      "clientConfigType": "NONE",
      "acquireUrl": "/expand",
      "seedConstraints": {
        "min": 1,
        "max": 1,
          "seedTypes": {
          "allowedTypes": "ENTITY",
          "itemTypes": [
            {
              "id": "made-up-schema-type-id (e.g. ET1)"
            },
            {
              "id": "made-up-schema-type-id (e.g. ET1)"
            }
          ]
        }
      }
    }
  ]
}
```

### Check service in Analyst's Notebook Premium

Check that your service now appears in the list of defined services in Analyst's Notebook Premium.

Update connectors' configuration by running:

* `setup -t updateConnectorsConfiguration`

Use Postman to run the `Reload` request (running the `Form Based Login` request if
you have not already), which will configure your changes to the topology. Then:

* Log out and log back into Analyst's Notebook Premium to see the configuration changes and your newly-defined service.
(If the service does not show up, try unchecking the `Hide queries whose requirements are not met` box).
* Try running it by pre-clicking on an entity. You should receive an error as your seeded search has been defined but not yet implemented.

## Implementation

It's time to implement these conditions.

### Add an acquire endpoint for your service
<details><summary><strong>Java</strong></summary>
<p>

i2 Analyze knows the acquire URL decided on for this service. Now you need to
add the corresponding endpoint in the connector.
* You have a template to get started with; see the `nypd-connector-5` folder
  provided. This includes:
  * An example `config.json` with a seeded search service defined. This is just a template in case you have not already defined a new service.
  * Changes to `ConnectorController`.
  * Some new REST transport classes.
* Apply the code changes, either manually or by copying the relevant files over. You may need to change the path of the new method in `ConnectorController` if copying the files completely.
* Notice how the endpoint is defined in the `ConnectorController` class.

</p>
</details>

<details><summary><strong>Node.js</strong></summary>
<p>

i2 Analyze knows the acquire URL decided on for this service. Now you need to
add the corresponding endpoint in the connector.
* You have a template to get started with; see the `nypd-connector-5` folder
  provided. This includes:
  * An example `config.json` with a seeded search service defined. This is just a template in case you have not already defined a new service;
  * Changes to `acquire` route to reflect changes in `config.json`;
  * Chagnes to `socrata-data-service.js` file to have all the functions needed for `acquire` route;
* Open the code from the `nypd-connector-5` in VSCode, or any IDE of your choice, and start the connector.
* You will need to implement functions defined in `socrata-data-service.js` file and fix all TODO's.

</p>
</details>

<details><summary><strong>Python</strong></summary>
<p>

i2 Analyze knows the acquire URL decided on for this service. Now you need to
add the corresponding endpoint in the connector.
* You have a template to get started with; see the `nypd-connector-5` folder
  provided. This includes:
  * An example `config.json` with a seeded search service defined. This is just a template in case you have not already defined a new service.
  * Changes to `controller.py`.
  * Some new REST transport classes.
* Apply the code changes, either manually or by copying the relevant files over. You may need to change the path of the new method in `controller.py` if copying the files completely.
* Notice how the endpoint is defined in the `controller.py` class.

</p>
</details>

### Access seeds
You will need to manipulate a seed passed into the request according to the [SPI](https://www.ibm.com/support/knowledgecenter/en/SSXVXZ_2.2.1/com.ibm.i2.connect.developer.doc/i2_connect_spi.json) and return a response containing entities and links. You will also need to create basic POJOs to parse the request and access the seed information.

### Filter data based on seed
How you will use the seed depends on what you are trying to achieve.

#### Find Like This seeded search
* A `Find Like This` query looks at the property values of a selected record and searches for data in the external source that has the same or similar property values.
* For this service you will need to filter out your entities based on the matching properties of the seed entity that can be accessed via `request.payload.seeds.entities.get(0)`.
* ***NOTE***: Do not return the entity that was passed as the `seed`.

#### Expand seeded search
* An `Expand` query takes an entity as a seed and returns a list of entities and links that are connected to the seed.
* For this service you will need to find *all links* connected to the seed entity that can be accessed via `request.payload.seeds.entities.get(0)`. Then you will need to find *all entities* connected to these links.
* Finally, you need to make sure that the link is pointing to the `seedId`.
* To do that, you will need to change `toEndId` or `fromEndId` to the `seedId` that can be accessed via `request.payload.seeds.entities.get(0).sourceIds.get(0).key.get(2)`.

## Run your query

Update connectors' configuration by running:

* `setup -t updateConnectorsConfiguration`

Use Postman to run the `Reload` request (running the `Form Based Login` request if
you have not already), which will configure your changes to the topology.

1. Open Analyst's Notebook Premium.
2. Select an entity on the chart.
3. Click on "External Search".
4. Click on your seeded search service to run it.

You should now see a resulting list of entities which are connected to the entity you initially selected.

## Next steps
Next, you can combine what you've learned from these past two sections to implement [seeded parameterised searches](./seeded-parameterised-search.md).