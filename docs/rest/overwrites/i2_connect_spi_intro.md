---
uid: i2 Connect gateway REST SPI/1.0
---

This documentation describes the server programming interface that you must implement when you write a connector to an external data source. Clients discover the URLs of the three connector endpoints through a combination of the topology file for the deployment and your SPI implementation.

The **{configurationUrl}** comes from the topology file. By default, the i2 Connect gateway makes a POST request to your connector at the URL that you specify in the **base-url** attribute of the **<connector>** element. If the value is **http://localhost:3700/**, the request comes to **http://localhost:3700/config**.

Your response from the **{configurationUrl}** can contain information about one or more services. The information for each service contains, in its **acquireUrl** and (optional) **validateUrl** fields, the URLs of the **{acquireUrl}** and **{validateUrl}** endpoints described here.
