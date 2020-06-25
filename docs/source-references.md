# Source References

You might want to include source references in entity and link data to preserve the route back to the external source. Entity and link objects support the inclusion of source reference objects.

The structure of a source reference object is as follows:

```js
{
	"id": String,
	"source": {
		"name": String,
		"type": String,
		"description": String,
		"location": String,
		"image": String
	},
	"userModifiable": Boolean
}
```

Note that the `source` field and its corresponding `name` field are the only mandatory fields.

#### Example

The following JSON object shows how a source references fits into the structure of an entity object:

```json
{
	"id": "123",
	"typeId": "ET1",
	"version": 1,
	"properties": {
		"PT16": "MANHATTAN"
	},
	"sourceReference": {
		"id": "SR01",
		"source": {
			"name": "Source Dataset",
			"type": "Open source data",
			"description": "A source reference to the corresponding record from the dataset.",
			"location": "https://data.cityofnewyork.us/resource/7x9x-zpz6.json?$where=cmplnt_num=123456789",
			"image": "https://github.ibm.com/ibmi2/Connect-Examples/tree/master/docs/images/nypd-dataset-webpage.png?raw=true"
		},
		"userModifiable": false
	}
}
```
