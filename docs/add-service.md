# Adding a service
In this task, you add a simple service with the minimum configuration required
for your connector to be valid. For the moment, the service returns to i2
Analyze some dummy data that you create. Later on, it will retrieve real data
from the NYPD Complaint Dataset.

Remember to consult the [troubleshooting guide](./troubleshoot.md) if you face
any issues.

## Prerequisites
Before starting, ensure that you have:
* [Deployed i2 Analyze with the i2 Connect gateway](./deploy-i2-analyze.md)
* [Configured a basic connector](deploy-connector.md)

## Define the service
To make i2 Analyze aware that your service exists, you must define it in the
connector configuration returned by the config endpoint. This means editing the
`config.json` file in the `resources` directory.

### Add a services array
At the top level of the configuration, add a services array:
   ```json
   {
     "defaultValues": {
        "timeZoneId": "Europe/London",
        "resultIdsPersistent": true
     },
     "services": []
   }
   ```

### Add a service
In the services array, define a service object. The mandatory fields to provide are the following.

   | Field                | Description                                        |   
   |----------------------|----------------------------------------------------|
   | **id**               | The unique identifier of the service               |
   | **name**             | The name of the service                            |
   | **acquireUrl**       | This specifies the URL for i2 Analyze to use to get data from the service. |
   | **clientConfigType** | This specifies the type of interface a user will interact with when using the service, and takes one of the three following values: <ul><li>`NONE` - there is no interface required, the service simply runs and returns whatever data it retrieves</li><li>`FORM` - the user fills in a form to provide the service with extra information that it needs to run. This is used to provide text-search services, for example. </li><li>`CUSTOM` - specifies that a custom client configuration will be used</li></ul>In the case of `FORM` and `CUSTOM` client configurations, the specific configuration to be used must be specified by the additional field `clientConfigId` and there must be a client configuration with the given ID defined in the connector configuration. This will be covered later.</td>  |

   It is also common and recommended to add a `description` field. This appears
   to users in Analyst's Notebook Premium, so can be used to give more information about
   what the service does. You should have something like the following.
   ```json
   {
     "defaultValues": {
        "timeZoneId": "Europe/London",
        "resultIdsPersistent": true
     },
     "services": [
        {
          "id": "nypd-service",
          "name": "NYPD Connector: Get All",
          "description": "A service which retrieves all data",
          "clientConfigType": "NONE",
          "acquireUrl": "/all"
        }
     ]
   }
   ```

### Update the connector's configuration
To tell i2 Analyze to pick up these changes, you need to tell i2 Analyze to reload it's
connectors' configurations, run:

* `setup -t updateConnectorsConfiguration`

Use Postman to run the `Reload` request (running the `Form Based Login` request if
you have not already), which will configure your changes to the topology.

If this does not add the relevant changes to Analyst's Notebook, redeploy:

1. `setup -t stop`
2. `setup -t deploy`
3. `setup -t start`

### View the service in Analyst's Notebook Premium
Now that you have defined a service, you can try to use it.
1. Open Analyst's Notebook Premium and login to the i2 Analyze server. If you
   were already logged in, log out and then back in again.
2. Open External Searches. The service you have defined should appear.
3. Try to run it. Although the service is defined, since it has not yet been
   implemented, you should see a red banner appear with the message "Failed to
   open the selected query. Contact your system administrator‬."
4. To see the full error, look in the `IBM_i2_Analysis_Repository.log` found
   in `deploy\wlp\usr\servers\opal-server\logs\opal-services-daod` within the
   `i2analyze` directory. You should see a 404 (Page Not Found) Error. This is
   because an endpoint for the `acquireUrl` defined in the `config.json` has
   not been implemented.

## Implement the service
Now that i2 Analyze knows to expect the service, you had better create it.

### Look at the SPI
The SPI you need to implement is documented in the
[Knowledge Centre](https://www.ibm.com/support/knowledgecenter/en/SSXVXZ_latest/com.ibm.i2.connect.developer.doc/i2_connect_spi.json).
Think about the relevant objects you will need to implement in order to build
a service that returns a fixed set of entities and links that you will create.
   
### Add an acquire endpoint
i2 Analyze knows the acquire URL for this service. Now you need to add an
endpoint for it in the connector.
<details><summary><strong>Java</strong></summary>
<p>

1. A template has been provided to get started with; see the `stage2/nypd-connector`
   directory. This includes:
   * an example configuration `config.json` with a service defined
   * some changes to `ConnectorController`;
   * a new class `ConnectorDataService`; and
   * some REST transport classes.
2. Apply these changes to your code, either manually, or by copying the files
   over directly. You may need to change the path of the new method in
   `ConnectorController` to match the acquireUrl of your service. You need not
   copy the example `config.json` if you have defined your service correctly.
   You can also just use the directory provided as it is setup to be used straight
   away.
3. Look at how the endpoint is defined in the `ConnectorController` class and
   the code that produces the response that is returned to i2 Analyze.

</p>
</details>

<details><summary><strong>Node.js</strong></summary>
<p>

1. A template has been provided to get started with; see the `stage2/nypd-connector`
   directory. This includes:
   * an example configuration `config.json` with a service defined;
   * a new acquire route in `app.js`;
   * a new acquire route with a `/test` endpoint in the `routes` folder;
   * a new file `/helpers/data-service.js` containing functions for acquiring data;
2. Open the code from the `stage2/nypd-connector` in VSCode, or any IDE of your choice, 
  and start the connector.

</p>
</details>

<details><summary><strong>Python</strong></summary>
<p>

1. A template has been provided to get started with; see the `stage2/nypd-connector`
   directory. This includes:
   * an example configuration `config.json` with a service defined
   * some additions to `controller.py`;
   * a new file for service functions, `service.py`; and
   * a new file `classes.py` containing some REST transport classes.
2. Apply these changes to your code, either manually, or by copying the files
   over directly. You may need to change the path of the new method in
   `controller.py` to match the acquireUrl of your service. You need not
   copy the example `config.json` if you have defined your service correctly.
   You can also just use the directory provided as it is setup to be used straight
   away.
3. Look at how the endpoint is defined in the `controller.py` class and
   the code that produces the response that is returned to i2 Analyze.

</p>
</details>

### Test the service in Analyst's Notebook Premium
1. Redeploy the connector. Depending on how you are running the connector, this
   may be done automatically for you when changes are made.
2. Re-run the service in  Analyst's Notebook Premium via the External Searches
   window.
3. You should no longer get a 404 Error, but the search will return nothing

### Return some data
<details><summary><strong>Java</strong></summary>
<p>

1. Make changes so that the service returns some entities and links - just
   create some dummy data for now. You will need to complete the implementation
   of the `LinkData` class provided.
2. Test your changes in Analyst's Notebook in the same way.
3. If you don't see what you expect to, or you come across an error,
   investigate the `IBM_i2_Analysis_Repository.log` in the `deploy\wlp\usr\servers\opal-server\logs\opal-services-daod` directory.

</p>
</details>

<details><summary><strong>Node.js</strong></summary>
<p>

1. Make changes so that the service returns some entities and links - just
   create some dummy data for now.
2. Test your changes in Analyst's Notebook in the same way.
3. If you don't see what you expect to, or you come across an error,
   investigate the `IBM_i2_Analysis_Repository.log` in the `deploy\wlp\usr\servers\opal-server\logs\opal-services-daod` directory.
 
</p>
</details>

<details><summary><strong>Python</strong></summary>
<p>

1. Make changes so that the service returns some entities and links - just
   create some dummy data for now. You will need to complete the implementation
   of the `Link` class in `classes.py`. Ensure you are importing the classes into `service.py`.
2. Test your changes in Analyst's Notebook in the same way.
3. If you don't see what you expect to, or you come across an error,
   investigate the `IBM_i2_Analysis_Repository.log` in the `deploy\wlp\usr\servers\opal-server\logs\opal-services-daod` directory.

</p>
</details>

It is vital that you return property values
in the format expected, which is defined by the logical type of each property.
See the [data model examples](./data-model.md) for examples of each supported
logical type.

## Querying data from an external source.
Now that you have a basic connector with a working simple service, you can make
it more useful by [returning real data](./connect-to-eds.md).
