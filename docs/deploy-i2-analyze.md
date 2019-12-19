# Deploying i2 Analyze
This guide walks you through how to deploy IBM i2 Analyze.

## Prerequisites
Before you start, ensure that you have:
- installed i2 Analyze, and
- [defined a schema](schema-design-guide.md) to model the data.

## License acknowledgement
- Open the `license_acknowledgement` file in your i2 Analyze directory.
- Set the value to `ACCEPT`. It should now look like this:
  ```
  LIC_AGREEMENT = ACCEPT
  ```
## Configuration
### Create the configuration directory
1. In your i2 Analyze directory, navigate to
   `toolkit\examples\configurations\daod-opal`.
2. Copy the `configuration` directory to the `toolkit` directory. This provides
   a starting point for a deployment that includes only the i2 Connect gateway.

### Specify the credentials for deployment
1. Using a text editor, open the
   `toolkit\configuration\environment\credentials.properties` file.
2. Specify a user name and password to use for the Solr indexes in the
   `solr.user-name` and `solr.password` properties.
3. Enter the password to encrypt LTPA tokens in the `ltpakeys.password`
   property.
4. Save and close the file.

### Command access control
To gain access to certain features, including the ability to use Postman to reload
the connector when making schema changes, you need to copy and modify some files
in the i2 Analyze deployment:

1. Navigate to the `toolkit\configuration\examples\security-schema` directory and
   copy the file named `example-command-access-control-daod.xml`.
2. Navigate to the `toolkit\configuration\fragments\opal-services\WEB-INF\classes`
   directory and paste the file from the previous step.
3. Open the `DiscoServerSettingsCommon.properties` file and add the name of file you
   just copied to the `CommandAccessControlResource` field, including the `.xml` extension.
4. Save and close the properties file.

### Configure the schema
1. Copy your schema and charting schemes to the
  `toolkit\configuration\fragments\common\WEB-INF\classes` directory.
2. Copy `example-dynamic-security-schema.xml` from
  `toolkit\configuration\examples\security-schema` to the
  `toolkit\configuration\fragments\common\WEB-INF\classes` directory as well.
3. Update the `ApolloServerSettingsMandatory.properties` file in the same
   directory to point to your schema files by setting the following properties:

    ```properties
    SchemaResource=schema-filename.xml
    ChartingSchemesResource=charting-schemes-filename.xml
    DynamicSecuritySchemaResource=example-dynamic-security-schema.xml
    ```

### Generate the default configuration
For the purposes of this guide, only a basic configuration is required, so you
can use the default.

1. Open the `toolkit\scripts` directory in a command prompt.
2. To populate some of the mandatory settings with default values, run:
   ```sh
   setup -t generateDefaults
   ```
3. In the `i2analyze` directory, navigate to the
  `toolkit\configuration\environment` directory and open the `topology.xml`
  file in a text editor.
4. Replace every instance of the `host-name` tag with the value `localhost`.
   There will be three to change in total. This will be used to test your
   application in Postman.

## Deployment
1. Open the `toolkit\scripts` directory in a command prompt.
2. To deploy i2 Analyze with the configuration you have just created, run
   ```sh
   setup -t deploy
   ```
3. To add an example user whose name and password are both "Jenny", run:
   ```sh
   setup -t ensureExampleUserRegistry
   ```
4. To start i2 Analyze, run
   ```sh
   setup -t start
   ```

You can now connect Analyst's Notebook Premium to i2 Analyze. The output of the
start command includes the URL to use for the i2 Analyze server.

This will be the best time to test the schema you created in the previous step, or
the existing one if you are using the example schema provided. You can open Analyst's
Notebook and start to drag/drop entities and links onto your chart, testing different
charting schemes and seeing what labels appear and how they are formatted. For these
changes to take effect, you need to update the i2 Analyze connectors
configuration and run the internal `Reload` command via Postman.

First, navigate to `i2analyze\toolkit\scripts` in your console and run:
```sh
setup -t updateConnectorsConfiguration
```
Next you need to open Postman and run the `Form Based Login` request. This will
authenticate you as the default user in i2 Analyze, then you can run the `Reload`
request which will configure your schema changes to the topology. (NOTE: You will need
to perform a reload command for every iteration of your schema design, otherwise the changes
will not take effect).

You now have a running i2 Analyze deployment, if you are happy with your schema you can
now [deploy a connector](./deploy-connector.md). However, if there are changes you wish
to make to the schema and associated charting schemes, you can run yourself through the
[schema design guide](./schema-design-guide.md) again.
