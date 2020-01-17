# Validation

You can validate your requests before sending them to the gateway to ensure requests are made in a correct format or desired form.

Remember to consult the [troubleshooting guide](./troubleshoot.md) if you need
to during this task.

## Client-side validation

Validation can be done on the client for simple checks before sending a request to the gateway, such as the presence of values for mandatory fields, or ensuring values are in the correct format. Client-side validation is implemented via the `config.json` file, in the same vein as parameterised searches. An example for a check where a certain field must start with 2 letters and 2 numbers can be shown below:

```json
{
	"conditions": [
	{
		"id": "searchTerm",
		"label": "Offence",
		"logicalType": "SINGLE_LINE_STRING",
		"mandatory": false,
		"extraStringValidation": {
			"regex": "[A-Za-z]{2}\\d{2}",
			"message": "Case number must start with 2 letters then 2 numbers"
		}
	}
	]
}
```

The `mandatory` tag in the `config.json` file specifies whether a field is required or not. The `extraStringValidation` tag allows regex validation be performed with custom error messages to be sent back to the client when a value does not comply with the rule. You can find out more [here](https://www.ibm.com/support/knowledgecenter/en/SSXVXZ_latest/com.ibm.i2.connect.developer.doc/i2_connect_spi.json).

## Server-side validation

Validation on the server-side can be done for more complex inputs.

For example, if there are 3 input fields and you require at least one to be set, but they are otherwise optional. In your connector, you can check the request contains at least one conditional value. In another case, if you have two date fields and want to support a date range search, you can validate the start date is before the end date.

```json
{
	"id": "nypd-search-service",
	"name": "NYPD Connector: Search",
	"description": "A service for conditional searches",
	"clientConfigType": "FORM",
	"clientConfigId": "searchForm",
	"acquireUrl": "/search",
	"validateUrl": "/search/validate",
	"resultItemTypeIds" : ["made-up-schema-type-id (e.g. ET1)"]
}
```

1. In your response from the configuration endpoint, add the `validateUrl` tag alongside the existing `acquireUrl` tag as shown in the snippet above. Sets its value to the location of the implementation that you will provide.
2. Implement the rules for the validate endpoint in a way that is consistent with its definition in the REST SPI documentation.
The payload that the endpoint receives in the request contains all the same seed and parameter information as the acquire endpoint receives.
3. If validation succeeds according to your rules, return a `null` response. If it fails, set the response to a simple object that contains an `errorMessage` as a string. When the i2 Connect gateway receives a non-null response, it does not subsequently send a request to the acquire endpoint.
4. Restart the i2 Analyze server or instruct the i2 Connect gateway to reload the configuration. Test that your new validation has the correct behavior.