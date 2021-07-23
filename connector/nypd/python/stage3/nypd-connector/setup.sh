#!/bin/bash
# Licensed Materials - Property of IBM
# 5725-G22
# (C) Copyright IBM Corp. 2012, 2021 All Rights Reserved.
# US Government Users Restricted Rights - Use, duplication or
# disclosure restricted by GSA ADP Schedule Contract with IBM Corp.

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
