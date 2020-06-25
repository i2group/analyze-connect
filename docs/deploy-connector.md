# Deploying a connector
In this task, you deploy a minimal connector with a configuration endpoint
that defines just one service. This is the minimum required for any connector to
interact with the i2 Connect gateway.

If you have any issues during this task, the
[troubleshooting guide](./troubleshoot.md) might be helpful.

## Prerequisites
Before you start, ensure that you have:
- deployed i2 Analyze with the i2 Connect gateway, and
- defined a schema to model the data, and
- installed Analyst's Notebook Premium and connected it to your i2 Analyze
  deployment

## Adding the connector to the i2 Analyze topology
This is how to configure the i2 Analyze server so that it knows about your
connector.

### Add the connector to the topology.xml
1. In the `i2analyze` directory, navigate to the
   `toolkit\configuration\environment` directory and open the `topology.xml`
   file in a text editor.
2. In the `opal-services-daod` `<war>` element, if it is not already there,
   add a `<connector-ids>` element. Inside that, add a `<connector-id>` element
   with a `value` attribute that matches the unique identifier of your connector.
   For example:
   ```xml
   <wars>
     <war ... name="opal-services-daod" ... >
       ...
       <connector-ids>
         <connector-id value="nypd-crime-connector"/>
       </connector-ids>
       ...
     </war>
   </wars>
   ```
3. Inside the `<topology>` element, add a `<connectors>` element. In here,
   add a `<connector>` element with `id`, `name`, and `base-url` attributes.
   Choose whatever `id` and `name` you like, but set the `base-url` to
   `http://localhost:9081/`. For example:
   ```xml
   <ns1:topology ...>
     ...
     <connectors>
       <connector base-url="http://localhost:9081/" name="NYPD Crime Connector" id="nypd-crime-connector"/>
     </connectors>
   </ns1:topology>
   ```

### Reload the i2 Analyze configuration
For these changes to take effect, you need to update the i2 Analyze connectors
configuration and use Postman to make the server reload it.

1. Navigate to `i2analyze\toolkit\scripts` in your console and run:
   ```
   setup -t updateConnectorsConfiguration
   ```
2. Open Postman and run the `Form Based Login` request to authenticate
   as the default user in i2 Analyze.
3. Call the POST method on the `reload` endpoint to enact your changes to the topology.

### Use Postman to verify the changes
To confirm that you have successfully added a connector to the topology, you can
send an HTTP GET request to the i2 Analyze server. Although you don't yet
have a running connector, this ensures that you have configured i2 Analyze so that it
can find the one you will soon create.

* Open Postman and call GET on the `connectors` endpoint.

In the response, the `connectors` array includes an entry that corresponds to
the connector you just defined. However, there is also an error and a message
saying that i2 Analyze failed to retrieve configuration information for your
connector. This is because the connector doesn't exist yet!

## Deploying the connector
Now that i2 Analyze is expecting a connector, you had better create one! For the
purposes of this guide, an example Spring Boot application is provided.

### Set up the example starting point
<details><summary><strong>Java</strong></summary>
<p>

* Open the example starting code from the `stage1/nypd-connector` folder. Copy the
  `nypd-connector` directory to wherever you would like to work, and open it
  in VSCode or the IDE of your choice.
* Have a look at the contents:
  * Resources: `config.json` and `application.properties`
  * Code: a simple Spring Boot application
  The `config.json` file tells Analyst's Notebook what services are available
  and how to execute them. The `application.properties` file defines the
  server ports, the database URLs, and the API token to aid in the connection.
* Start the connector, either using [VSCode](run-in-vscode-java.md) or through the
  [command line](run-in-cmd-java.md).

</p>
</details>

<details><summary><strong>Node.js</strong></summary>
<p>

* Open the example starting code from the `stage1/nypd-connector` folder in VSCode,
  or the IDE of your choice.
* Have a look at the contents:
  * Resources: `public/json/config.json`
  * Code: a simple Express application
  The `config.json` file tells Analyst's Notebook what services are available
  and how to execute them. The `app.js` file is where the `CONTEXT_ROOT` and
  all the connector's endpoints are defined (for now, just the `/config`
  endpoint). The `routes` folder is where all defined routes are implemented
  (for now, only the `config` route).
* Start the connector through the [command line](run-in-cmd-node.md).

</p>
</details>

<details><summary><strong>Python</strong></summary>
<p>

* Open the example starting code from the `stage1/nypd-connector` folder. Copy the
  `nypd-connector` directory to wherever you would like to work, and open it
  in VSCode or the IDE of your choice.
* Have a look at the contents:
  * Resources: `config.json`
  * Code: a simple Flask application.
  The `config.json` file tells Analyst's Notebook what services are available
  and how to execute them.
* Start the connector through the [command line](run-in-cmd-python.md).

</p>
</details>

### Use Postman to investigate the config endpoint
The supplied starting code has a `config` endpoint that is ready to use:
* In Postman, call the GET method of the `config` endpoint and look at the
  output. It matches the contents of the `config.json` file that you set up
  earlier.

### Update the i2 Analyze connectors configuration
Now that you have a connector running, you need to tell i2 Analyze to reload its
connector configurations.
1. Run `setup -t updateConnectorsConfiguration`.
2. Call the POST method on the `reload` endpoint to configure your changes to
   the topology.

### View the i2 Analyze connectors configurations in Postman
* Exactly as before, open Postman and call GET on the `connectors` endpoint.

You no longer have the "Failed to retrieve configuration" error, but it has
been replaced by two others.

### Investigate in Analyst's Notebook Premium
Before you fix these issues, take a look at what happens when you try to use
your connector from Analyst's Notebook Premium.
1. Open Analyst's Notebook Premium and log in to the i2 Analyze server.
2. In the top ribbon, click the **External Searches** button.
3. Notice the banner at the top of the resulting window that says "Some
   queries are not configured correctly."
4. Click the **DETAILS** button and look at the messages. You can see that it
   corresponds to the output you saw when using the `connectors` endpoint from
   Postman.

### Unsecured protocol warning message

You might see the following warning message:

*"Configuration error for the connector with identifier '\<CONNECTOR-ID\>': The
application is communicating with the connector through a protocol that is not secure."*

You can ignore this in a development environment. This is shown because i2 Analyze and your connector
communicate via HTTP.

**In a production environment, you should secure this
connection using HTTPS.** For information about configuring the connection to use HTTPS, see [Client authenticated Secure Sockets Layer with the i2 Connect gateway](https://www.ibm.com/support/knowledgecenter/en/SSXVXZ_latest/com.ibm.i2.eia.go.live.doc/t_connect_security.html).

### Fix the timezone error
1. Look at the error message about the default timezone. You need to update the
   `config.json` file to include a timezone.
2. Use the GET method on the `core/temporal/timezones` endpoint to get a list
   of valid timezones. Add your chosen timezone to the top of the `config.json`
   file.
3. Update the i2 Analyze connector configurations again, and then use either
   Postman or Analyst's Notebook Premium to check that the problem is
   resolved.

## Adding a service
You still have the error about the connector not having any defined services. A
valid connector must have at least one service. Next, you need to
[define and implement a service](./add-service.md).
