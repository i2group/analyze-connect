# Running the Python connector
The example Python connector is a simple Flask application built on Python 3.7.4.
It is imperative that you have Python installed and added to `PATH`. Older versions of Python may not work.

In the project directory (where the `Pipfile` is), use the commands below to install dependencies and get
your connector up-and-running.

## Installation

If you're running Windows, you can install all required dependencies and create a virtual environment by running this setup batch script in the `nypd-connector` working directory:

```
./setup.bat
```

Alternatively, run this sequence of the instructions (that the above batch script performs):

1. Install `pipenv`, the virtual environment and package manager.
    ```
    pip install pipenv
    ```
2. Activate your virtual environment.
    ```
    pipenv --python [python-version]
    ```
3. Synchronize the environments dependencies with that of the Pipfile.
    ```
    pipenv sync
    ```

You'll need to do this each time you're in a new Python working directory to ensure dependencies are installed and that your virtual environment is activated.

## Starting and stopping the application

To start the connector application, run the following command in the working directory:

```
pipenv run python app.py
```

Simply press `Ctrl+C` to stop the application.