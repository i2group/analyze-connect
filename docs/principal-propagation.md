# Principal propagation

i2 Analyze contains an extension point that enables you to return additional headers to any connectors from the i2 Connect gateway. The extension point requires you to write an implementation of `IConnectorRequestModifier.java`. The implementation must be packaged into a JAR file and deployed to i2 Analyze. The following example demonstrates how to use Maven to do this and how to configure i2Analyze to use your implementation.

## Overview

For i2 Analyze to use your implementation, complete the following tasks:

1. Create and package your implementation of `IConnectorRequestModifier.java`
2. Configure i2 Analyze to use your packaged implementation
4. Redeploy and restart Liberty to update your deployment

## Prerequisites

- Java installed

## The principal propagation project

Update the value of the `<toolkitLocation>` element in [pom.xml](../code/principal-propagation/pom.xml) file to reference the installation location of i2Analyze. For example, `<toolkitLocation>C:\IBM\i2Analyze</toolkitLocation>`.  
This location is used by Maven to locate the JAR files to pull into the local Maven repository and where to place the packaged JAR file that contains your implementation.

To set up the Maven environment, open a terminal in the `principal-propagation` directory.

1. Initialize the Maven dependencies:
    > `mvnw initialize`
    
    This pulls in JAR files from the i2 Analyze deployment toolkit into your local Maven repository.
2. Compile and install the project:
    > `mvnw clean install`

At this point, you could start editing the implementation in [PrincipalPropagation.java](..\code\principal-propagation\src\main\java\com\i2group\example\PrincipalPropagation.java). For the example implementation, do not make any changes.

## Deploy the principal propagation implementation

When the implementation is complete, package the class into a JAR file.

Open a terminal in the `principal-propagation` folder and run the following command:

> `mvnw package`

The Maven `package` command creates a JAR file from the the `PrincipalPropagation.java` class and puts it in the `toolkit/configuration/fragments/opal-services/WEB-INF/lib/` directory of the i2 Analyze toolkit specified in the `<toolkitLocation>` element.

## Configuring i2Analyze

For i2Analyze to use the `PrincipalPropagation.jar`, it must be specified in the `DiscoServerSettingsCommon.properties` file.

Add the following line to the end of the `toolkit/configuration/fragments/opal-services/WEB-INF/lib/classes/DiscoServerSettingsCommon.properties` file:

> ConnectorRequestModifier=com.i2group.example.PrincipalPropagation

## Redeploy and Restart Liberty

1. Stop Liberty:
    >`setup -t stopLiberty`
2. Redeploy Liberty:
    >`setup -t deployLiberty`
3. Start Liberty:
    >`setup -t startLiberty`

Any connectors receive an extra header named `i2ExtensionHeader` in responses from the i2 Connect gateway.

For example:

```
{
    'content-type': 'application/json',
    'accept-language': 'en-GB-u-ca-gregory',
    i2ExtensionHeader: 'Jenny',
    'content-length': '92',
    host: 'localhost:3700',
    connection: 'Keep-Alive',
    'user-agent': 'Apache-HttpClient/4.5.6 (Java/1.8.0_232)',
    'accept-encoding': 'gzip,deflate'
}
```
