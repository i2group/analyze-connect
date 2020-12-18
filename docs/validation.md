# Validation

You can validate requests at the gateway to ensure they are in the correct form before sending them to the respective service.

Consult the [troubleshooting guide](./troubleshoot.md) if needed during this task.

## Client-side validation

Validation can be performed on the client for simple input checks before sending a request to the gateway, such as ensuring the presence of values for mandatory fields, or verifying that input values are in the correct format.

Client-side validation is configured via the connector's configuration in the same way as parameterized searches. Below is an example configuration for validating that input values for the 'Offence' field start with two letters and two numbers:

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

The `mandatory` property in the `config.json` file specifies whether a field is required or not.

The `extraStringValidation` property property allows regex validation be performed with custom error messages to be sent back to the client when a value does not comply with the rule.

You can find out more on accepted condition properties by looking at the `/{configurationUrl}` endpoint definition in the [i2 Connect Gateway REST SPI](https://www.ibm.com/support/knowledgecenter/en/SSXVTH_latest/com.ibm.i2.connect.developer.doc/i2_connect_spi.json).

## Server-side validation

Validation can be performed on the server for more complex inputs.

For example, if there are 3 input fields and you require at least one to be set but they are otherwise optional. In your connector, you can check that the user has defined at least one condition in the request.

In another case, if you have two date fields and want to support searching a range of dates, you can validate that the start date is before the end date.

### 1. Add the validation endpoint to the connector configuration

In your connector configuration, add the `validateUrl` property after the existing `acquireUrl` property as shown in the snippet below. Set the value to the endpoint where you will implement your server-side validation logic.

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

### 2. Implement the validation endpoint

In your code, implement the server-side logic for the validate endpoint using the conditions in the request. The payload that the endpoint receives in the request contains all the same condition and seed information that the acquire endpoint receives.

If validation succeeds according to your logic, return a 200 response code. The body of the response must either be an empty object or an object containing an `errorMessage` with a `null` value. For example:

```json
{
	"errorMessage": null
}
```

If it fails, return an object containing an `errorMessage` with your error message:

```json
{
	"errorMessage": "This is the error message displayed."
}
```

When the i2 Connect gateway receives a non-null `errorMessage`, it does not subsequently send a request to the acquire endpoint.

More information can be found looking at the `/{validationUrl}` endpoint definition in the [i2 Connect Gateway REST SPI](https://www.ibm.com/support/knowledgecenter/en/SSXVTH_latest/com.ibm.i2.connect.developer.doc/i2_connect_spi.json).

### 3. Reload the i2 Connect gateway

Instruct the i2 Connect gateway to reload the configuration. Test that your new validation has the correct behavior.