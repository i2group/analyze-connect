# i2 Connect example connector

The documentation for understanding, deploying, and configuring the example connector is divided into the following sections:

1. [Building an i2 Analyze schema and working with the i2 Connect gateway](./schema-design-guide.md)
2. [Deploying i2 Analyze](./deploy-i2-analyze.md)
3. [Configuring a connector](./deploy-connector.md)
4. [Creating a service](./add-service.md)
5. [Querying an external data source](./connect-to-eds.md)
6. [Implementing parameterized searches](./parameterised-search.md)
7. [Implementing seeded searches](./seeded-search.md)
8. [Implementing seeded, parameterized searches](./seeded-parameterised-search.md)
9. [Validating requests](./validation.md)
10. [Implementing Principal Propagation](./principal-propagation.md)

## Environment setup

The workshop requires the following tools and technologies:

- **IBM i2 Analyze 4.3.2**: The server that hosts the i2 Connect gateway.

- **IBM i2 Analyst's Notebook Premium 9.2.2**: The client that provides the user
  interface for interacting with your connector. The client displays the
  resulting entity and link records with their properties.

- **IBM i2 Analyze Schema Designer**: The tool that enables you to design and
  create your i2 Analyze schema. During installation of Analyst's Notebook
  Premium, ensure that you also install Schema Designer.

- **Microsoft Visual Studio Code**: The recommended IDE for developing your
  connector. You can use any IDE you like, but the Visual Studio Code Spring
  Boot Dashboard plugin can handle running and redeploying the connector for
  you. Download it [here](https://code.visualstudio.com/).

- **Postman**: An API development tool that you can use to create and execute
  requests against REST endpoints. Download Postman
  [here](https://www.getpostman.com/).

- An application framework dependent on the language who are writing the
connector with.
  - **Java (Spring Boot)**: The connector is written in Java with Spring Boot.
  Spring Boot is a Spring framework with an embedded Tomcat server that makes
  it easy to spin up a web application. No installation is required.
  - **Node.js (Express)**: The connector is written with in Node with Express.
  Express is the fast, unopinionated, minimalist web framework for Node.js that
  provides a robust set of features for web and mobile applications.
  - **Python (Flask)**: The Python connector is written and run on a Flask
  application. Flask is a micro web framework which requires no particular
  tools or libraries. No manual installation other than the specified setup
  instructions is required as the Pipfile contains it as a dependency.

## Additional resources

To help you to understand some of the tools and technologies you will use, here
are a few resources that contain more information about them:

- [i2 Analyze data model examples](./data-model.md)
- [Example requests and responses](./spi-examples.md)
- [Using Postman](./postman.md)
- [Troubleshooting](./troubleshoot.md)
- Running the connector:
   - **Java**: [in Visual Studio Code](./run-in-vscode-java.md) or [via the command line](./run-in-cmd-java.md)
   - [**Node.js**](./run-in-cmd-node.md)
   - [**Python**](./run-in-cmd-python.md)
