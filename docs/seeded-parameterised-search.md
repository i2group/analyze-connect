# Seeded parameterised search

Simply put, a seeded parameterised search is a combination of seeded and parameterised searches. This type of search passes information from an entity on the chart to the connector together with conditions that are used to drive searches.

Should you have any problems during this task, please consult the
[troubleshooting guide](./troubleshoot.md).

## Configuration

You need to configure a service to allow for seeded parameterised searches.

### Add a new service

You will need to set the `clientConfigType` and `clientConfigId` values similar to configuring parameterised search configuration. You will also need to add `seedConstraints` to define constraints on the seed similar to configuring seeded search. For example:

```json
{
  "services": [
    ...
    {
      "id": "nypd-expand-with-conditions",
      "name": "NYPD Connector: Expand with Conditions",
      "description": "A service which executes an Expand operation on a seed with conditions",
      "clientConfigType": "FORM",
      "clientConfigId": "expandForm",
      "acquireUrl": "/expand-with-conditions",
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

You will also need to provide a `clientConfig` with the `id` set in the service configuration. It should look something like this:
```json
{
  ...
  "clientConfigs": [
    {
      "id": "expandForm",
      "config": {
      "sections": [
        {
          "conditions": [
          {
            "id": "made-up-id (e.g. searchBorough)",
            "label": "made-up-field (e.g. Borough)",
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
You can change these `conditions` to relate to your own schema and what you want to search for.

### Check the service in Analyst's Notebook Premium

Let's check that your service now appears in the list of defined services in Analyst's Notebook Premium.

Update connectors' configuration by running:

* `setup -t updateConnectorsConfiguration`

Use Postman to run the `Reload` request (running the `Form Based Login` request if
you have not already), which will configure your changes to the topology. Then:

* Log out and log back into Analyst's Notebook Premium to see the configuration changes and our newly-defined service.
* Try running it. You should receive an error as your seeded parameterised search has been defined but not yet implemented.

## Implementation

It's time to implement the seeded parameterised search.

### Add an acquire endpoint for your service
In the same fashion as the other services you have defined, add an acquire endpoint for this service in your controller file.

### Access conditions and seeds
You will need to manipulate the seeds and conditions passed in the request according to the [SPI](https://www.ibm.com/support/knowledgecenter/en/SSXVXZ_latest/com.ibm.i2.connect.developer.doc/i2_connect_spi.json) and return a response containing entities and links. You will need to create basic POJOs to parse the request and access the condition and seed information contained in `request.payload`.

### Filter data based conditions and seeds
An `Expand With Conditions` query takes an entity as a seed and returns a list of entities and links that are connected to the seed that satisfy the list of conditions provided by the user.
For this service you will need to find *all links* connected to the seed entity that also satisfy your `conditions`. In the example, only links that had been created after the `date` provided by the user were return. Then you will need to find *all entities* connected to these links. Finally, you need to make sure that the link is pointing to the `seedId`. To do that, you will need to change `toEndId` or `fromEndId` to the `seedId` that can be accessed via `request.payload.seeds.entities.get(0).sourceIds.get(0).key.get(2)`.

## Run your query

Update connectors' configuration by running:

* `setup -t updateConnectorsConfiguration`

Use Postman to run the `Reload` request (running the `Form Based Login` request if
you have not already), which will configure your changes to the topology. Then:

1. Open Analyst's Notebook.
2. Select an entity on the chart.
3. Click on "External Search".
4. Click on your seeded parameterised search service.
5. Provide a value to the condition field and click "Run".

You should now see a resulting list of entities which are connected to the entity you initially selected and also satisfy the conditions you defined.

## Next steps
Now that you've completed this, you can look into [validating your requests](./validation.md).