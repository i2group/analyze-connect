# Connect to an external datasource

Here, you will connect to the NYPD Complaint Dataset as your external datasource and marshal the data into entities, links, and properties so that you can return results which can be displayed in Analyst's Notebook Premium.

Again, use the [troubleshooting guide](./troubleshoot.md) if you need to.

## Create a Socrata app token

You need an app token which will allow you make unlimited requests to Socrata's API (within reason). If you don't use an app token, the APIs will throttle by IP address.
1. Visit [this link](https://data.cityofnewyork.us/profile/app_tokens) to register your account.
2. Click on your name in the top right to access **My Profile**.
3. Click on **Edit Account Settings**.
4. In the side-pane, click on **Developer Settings**.
5. At the bottom of the page, click **Create New App Token**, specify your own "Application Name" and "Description" and save.

If you leave the site for any reason, you can always retrieve your app token again by logging into your account again.

## Query the external datasource
### Retrieve the raw data

<details><summary><strong>Java</strong></summary>
<p>

Look at the version of code in the `nypd-connector-3` directory. There are changes to `ConnectorController` and the `application.properties` file. Apply these to your code manually or copy over these two files. If you're copying them, you may need to change the paths of the endpoints defined in `ConnectorController`.

In `application.properties` shown below, specify the NYPD Complaint Dataset API resource for the `socrata.url` key as the URL in the comment and your Socrata API Token for the `socrata.api.token` key.

```properties
server.port=9081

# Resource URL, for example https://data.cityofnewyork.us/resource/7x9x-zpz6.json
socrata.url=

# API Token. Create a Socrata account and create an API Token. Paste it here
socrata.api.token=
```

You need to implement the `ExternalConnectorDataService` and `SocrataResponseData` classes so that they retrieve data from the NYPD Complaint Dataset and use it to create entities and links to be returned to i2 Analyze. It should not be necessary to modify the example `SocrataClient`.

The dataset can be queried using **SoQL** (Socrata Query Language). To do this, you must construct a URL with specified parameters (if necessary) to retrieve the data. By default, a `$limit` parameter has been set to the value of `1` to restrict the number of records retrieved. It's best to keep this value small to reduce the response time of each request until you are more comfortable with SoQL.

```java
final Map<String, Object> params = new HashMap<>();
params.put("limitValue", 1); // Only returning 1 entity for the moment. increase when ready
final String url = "?$limit={limitValue}";

// Make the request and map the whole response body as a string so that you can
// see what is returned
// TODO: Remove this since it's just for debugging
System.out.println(socrataClient.get(url, String.class, params));
```
</p>
</details>

<details><summary><strong>Node.js</strong></summary>
<p>

* Look at the version of code in the `nypd-connector-3` directory. This includes:
  * Changes to `acquire.js` route;
  * A new `socrata-config.js` file;
  * A new `socrata-data-service.js` file;
* `socrata-config.js` is where the NYPD Complaint Dataset API resource and the Socrata token are defined.
  ```js
  module.exports = {
    url: "https://data.cityofnewyork.us/resource/7x9x-zpz6.json",
    token: "SET YOUR TOKEN"
  };
  ```
* `socrata-data-service.js` contains functions to query NYPD Complaint Dataset.
  The dataset can be queried using **SoQL** (Socrata Query Language). To do this, you must construct a URL with specified parameters (if necessary) to retrieve the data.
  By default, a `$limit` parameter has been set to the value of `1` to restrict the number of records retrieved. It's best to keep this value small to reduce the response time of each request until you are more comfortable with SoQL.
  ```js
  const URL = `${socrata.url}?$$app_token=${socrata.token}&$limit=${limitValue}`;
  ```
* Open the code from the `nypd-connector-3` in VSCode, or any IDE of your choice,  and start the connector.
* You need to map the data received into entities and links and return them to i2 Analyze.

</p>
</details>

<details><summary><strong>Python</strong></summary>
<p>

Look at the version of code in the `nypd-connector-3` directory. Changes have been made to `controller.py` and there is now an additional resource file: `application.yml`. Apply these to your code manually or copy over these two files. If you're copying them, you may need to change the paths of the endpoints defined in `controller.py`.

In `application.yml` shown below, specify the NYPD Complaint Dataset API resource for the `socrata.url` key as the URL in the comment and your Socrata API Token for the `socrata.token` key.

```properties
socrata:
  url: https://data.cityofnewyork.us/resource/7x9x-zpz6.json
  token: # Replace with Socrata API token
```

You need to implement the `query_external_datasource` function in `service.py` so that it retrieves data from the NYPD Complaint Dataset and uses it to create entities and links to be returned to i2 Analyze.

The dataset can be queried using **SoQL** (Socrata Query Language). To do this, you must construct a URL with specified parameters (if necessary) to retrieve the data. By default, a `$limit` parameter has been set to the value of `1` to restrict the number of records retrieved. It's best to keep this value small to reduce the response time of each request until you are more comfortable with SoQL.

```python
with open('static/application.yml') as yml_file:
    config = yaml.safe_load(yml_file)

base_url = config['socrata']['url']
api_token = config['socrata']['token']

limit = 1
request_url = f"{base_url}?$limit={limit}"

x = requests.get(request_url, headers = { 'X-App-Token': api_token })
```

</p>
</details>

#### (Optional) Verify the data

It's worth testing that you are successfully querying the data and returning results. Print the returned value to the console and check that it matches with the data you see when you make a request to the acquire endpoint via Postman.

### Marshal the data to objects

To make it easier to create entities and links using the data retrieved, you can create a class (Java or Python) or JavaScript object (Node.js) to represent a single row of the dataset. This will have a field for each of the columns of the data. You can then write a function that serialises the incoming data into a collection of these objects.

Note that in Java, there exists a library which makes this process much easier: `jackson-annotations`.

#### (Optional) Verify your marshalling function

Test that you are successfully marshalling the data. You should be able to assert against the properties of your object to verify the expected and actual results are equal.

### Extract entities and links from objects

You can create entities and links from the objects that represent rows of the dataset and define their properties using the relevant fields.

Implement this extraction; deriving entities from each record as well as establishing links between them.

Create a response object with a list of entities and links to be returned. You need to take care not to duplicate entities in the list. Also take care again to assign property values in the correct format. Refer to the [data model examples](./data-model.md) again if you need.

#### (Optional) Validate your return response

Verify that the response returned from your function is valid and is as expected.

## View results in Analyst's Notebook Premium

You should now be able to log into Analyst's Notebook Premium and run your query. If there are any errors, you may want to check that your schema is in the right shape, that your data is clean and that there are no missing values.

## Next steps
Next, you can [configure your own parameterised search](./parameterised-search.md).
