# Parameterised search

A parameterised search passes defined conditions that you can use to drive your searches.

If you have any problems during this task, remember to consult the
[troubleshooting guide](./troubleshoot.md).

## Configuration

### Add a new service
You will need to add a service for parameterised searches to the services array in the `config.json`.

For this service, you need to set the `clientConfigType`. In this example, you will set the value to be `FORM` so that you can specify your conditions via fields in a form that will be shown in Analyst's Notebook Premium.

```json
{
  ...
  "services": [
    {
      "id": "nypd-search-service",
      "name": "NYPD Connector: Search",
      "description": "A service with for conditional searches",
      "clientConfigType": "FORM",
      "clientConfigId": "searchForm",
      "acquireUrl": "/search",
      "resultItemTypeIds" : ["made-up-schema-type-id (e.g. ET1)"]
    }
  ]
}
```
You will need to change the `resultItemTypeIds` to reflect your own entities in your schema, for example `ET1`.
### Define search fields
You will need to define the search fields in a `clientConfigs` array at the root level of your `config.json`. For example:

```json
{
  "defaultValues": {
    ...
  },
  "services": [
    ...
  ],
  "clientConfigs": [
    {
      "id": "searchForm",
      "config": {
        "sections": [
          {
            "conditions": [
              {
                "id": "searchTerm",
                "label": "made-up-field (e.g. Complaint Number)",
                "mandatory": false,
                "logicalType": "SINGLE_LINE_STRING"
              }
            ]
          }
        ]
      }
    }
  ]
}
```

For more information on client configuration, refer to the [Knowledge Center](https://www.ibm.com/support/knowledgecenter/en/SSXVXZ_2.2.1/com.ibm.i2.connect.developer.doc/i2_connect_config_endpoint.html).

### Test search fields
Check that your fields work in Analyst's Notebook Premium, you need to tell i2 Analyze to reload it's
connectors' configurations, run:

* `setup -t updateConnectorsConfiguration`

Use Postman to run the `Reload` request (running the `Form Based Login` request if
you have not already), which will configure your changes to the topology. You can then:

* Log out and log back into Analyst's Notebook Premium to see the configuration changes and your newly-defined service.
* Try running it. You should receive an error as your condition fields have been defined but not yet implemented.

## Implementation

It's time to implement these conditions.

<details><summary><strong>Java</strong></summary>
<p>

### Add an acquire endpoint for your service
i2 Analyze knows the acquire URL decided on for this service. Now you need to add the corresponding endpoint in the connector.
* You have a template to get started with; see the `nypd-connector-4`
  directory provided. This includes:
  * An example `config.json` with a parameterised search service and search fields defined. This is just a template in case you have not already defined a new service
  * Changes to the `ConnectorController` class
  * Some extra REST transport classes
* Apply these changes to your code, either manually, or by copying the relevant
  files. If you are copying the files, you may need to change the path of the
  new method in `ConnectorController`.
* Look at how the endpoint is defined in the `ConnectorController` class and think about how you should implement this service.

</p>
</details>

<details><summary><strong>Node.js</strong></summary>
<p>

### Add an acquire endpoint for your service

i2 Analyze knows the acquire URL decided on for this service. Now you need to add the corresponding endpoint in the connector.
* You have a template to get started with; see the `nypd-connector-4`
  directory provided. This includes:
  * An example `config.json` with a parameterised search service and search fields defined. This is just a template in case you have not already defined a new service;
  * A new `validate` route;
  * A new `/search` endpoint in the `acquire` route;
* Open the code from the `nypd-connector-4` in VSCode, or any IDE of your choice, and start the connector.
* You will need to implement `findComplaint` function in `socrata-data-service.js` file and fix all TODO's.

</p>
</details>

<details><summary><strong>Python</strong></summary>
<p>

### Add an acquire endpoint for your service
i2 Analyze knows the acquire URL decided on for this service. Now you need to add the corresponding endpoint in the connector.
* You have a template to get started with; see the `nypd-connector-4`
  directory provided. This includes:
  * An example `config.json` with a parameterised search service and search fields defined. This is just a template in case you have not already defined a new service
  * Changes to the `controller.py` file
  * Some extra REST transport classes
* Apply these changes to your code, either manually, or by copying the relevant
  files. If you are copying the files, you may need to change the path of the
  new method in `controller.py`.
* Look at how the endpoint is defined in the `controller.py` class and think about how you should implement this service.

</p>
</details>

### Access conditions
You will need to parse the conditions passed in the request according to the [SPI](https://www.ibm.com/support/knowledgecenter/en/SSXVXZ_2.2.1/com.ibm.i2.connect.developer.doc/i2_connect_spi.json) and return a response containing entities and links. For an example of how your connector may receive requests, and the responses it may return, you can view some SPI examples [here](./spi-examples.md).

You will need to create basic POJOs to parse the request and access the condition information. To do that please refer to the `DaodRequest` model in the SPI. The list of conditions can be accessed via `request.payload.conditions`.

### Filter data by conditions
When you have a list of conditions, you can use their `id` and `value` fields to determine which of the entities retrieved from the datasource match the parameters given by the user in the form.

## Run your query

Update the connectors' configuration by running:

* `setup -t updateConnectorsConfiguration`

Use Postman to run the `Reload` request (running the `Form Based Login` request if
you have not already), which will configure your changes to the topology. Then:

1. Open Analyst's Notebook Premium.
2. Click on "External Search".
3. Select your parameterised search service.
4. Provide a value to the condition field and click "Run".

You should now see a resulting list of entities that satisfy your condition.


## Next steps
Now you can implement [seeded searches](./seeded-search.md).