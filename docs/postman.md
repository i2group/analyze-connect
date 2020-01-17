# Postman

You can use Postman collections to test the endpoint of i2 Analyze and your connector.

## Prerequisites

- Install the latest version of Postman;
- Import the Postman environments from [here](../postman/environments);
- Import the Postman collections from [here](../postman/collections);

## Testing i2 Analyze

To make sure that i2 Analyze has been configured correctly, use the `i2 Analyze` environment.
  * Select the `i2 Analyze` environment in the top right corner.

### Login to i2 Analyze
First we need to login to i2 Analyze through Postman.
  * Open the `i2 Analyze Auth` collection.
  * Run the `Form Based Login` request.
  * This will generate the token that will be used to login to i2 Analyze.

Now, after generating the token we want to test that the i2 Analyze works as expected. open `NYPD i2 Analyze` collection.

### Get all valid timezones
  * Open the `Core Resources` folder.
  * Run the `GET TimeZones` request.

You should get a 200 status code and see that i2 Analyze returns all valid timezones.

### Get all connectors
  * Open the `Daod Resource` folder.
  * Run the `Connectors` request.

You should get a 200 status code and see that i2 Analyze returns its connectors configuration.

### Reload connectors configuration
  * Open the `Daod Resource` folder.
  * Run the `Reload` request.

You should get a 200 status code and see that i2 Analyze returns its new connectors configuration.

## Testing the connector

To make sure that your connector is functional, use the `NYPD Connector` environment and the `NYPD Connector` collection.
  * Select the `NYPD Connector` environment in the top right corner.
  * Open the `NYPD Connector` collection.

### Testing the config endpoint
After adding the config endpoint you may want to test the endpoint does indeed return your `config.json`.
  * Open the `Config` folder.
  * Run the `GET Config`request.

You should get a 200 status code and see your config being returned.

### Testing acquire endpoints

After adding a new endpoint, you can use Postman to test it.
**NOTE:** The `NYPD Connector` requests will only work out of the box if you have defined and implemented
the same services provided as examples. You will need to make changes to these requests if you have created different services.
In particular, you may need to change parameters/seeds in the parameterized/seeded search requests.

#### Test Service endpoint
* Open the `Acquire` folder.
* Run the `Test Service` request.

You should get a 200 status code and see some entities and links returned.

#### Search Service endpoint
* Open the `Acquire` folder.
* Click on `Search Service` request.
* Open the body of the request and enter conditions that align with how you have implemented the service.
* Run the `Search Service` request.

You should get a 200 status code and see some entities and links returned.

#### Find Like This endpoint
* Open the `Acquire` folder.
* Click on the `Find Like This` request.
* Open the body of the request and enter a seed that aligns with how you have implemented the service.
* Run the `Find Like This` request.

You should get a 200 status code and see some entities and links returned.

#### Expand endpoint
* Open the `Acquire` folder.
* Click on the `Expand` request.
* Open the body of the request and enter a seed that aligns with how you have implemented the service.
* Run the `Expand` request.

You should get a 200 status code and see some entities and links returned.
