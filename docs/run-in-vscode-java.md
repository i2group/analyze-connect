# Running the connector in VS Code
To run your connector directly from VS Code, ensure you have installed the
Spring Boot Extension Pack for VSCode, either via the Extensions menu in VSCode,
or using [this link](https://marketplace.visualstudio.com/items?itemName=Pivotal.vscode-boot-dev-pack)
Then, with your project directory open in VS Code:
1. There should be a Spring Boot Dashboard panel at the bottom, where the "demo"
   app should appear.
2. Right-click on the 'demo' app and click Run. The resulting output in the
   debug console should print the URL for the connector

The app automatically restarts whenever files are updated, which helps speed
up the development/test cycle.

The screenshot below shows the Spring Boot Dashboard panel with the "demo" app
appearing where you should expect to see it.

![Spring Boot in VS Code](./images/vscode.png)
