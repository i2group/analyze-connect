#!/bin/bash
# i2, i2 Group, the i2 Group logo, and i2group.com are trademarks of N.Harris Computer Corporation.
# © N.Harris Computer Corporation (2022)

set -e

VERSION="$(python -V)"
NUMBER="${VERSION/Python }"

echo "Detected Python v$NUMBER..."

echo "Installing pipenv..."
pip install pipenv

echo "Running python version..."
pipenv --python $NUMBER

echo "Creating Piplock.file..."
pipenv lock

echo "Syncing dependencies..."
pipenv sync
