#********************************************************************************
# * Licensed Materials - Property of IBM
# * (C) Copyright IBM Corporation 2021. All Rights Reserved
# *
# * This program and the accompanying materials are made available under the
# * terms of the Eclipse Public License 2.0 which is available at
# * http://www.eclipse.org/legal/epl-2.0.
# *
# * US Government Users Restricted Rights - Use, duplication or
# * disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
# *
# ********************************************************************************

import yaml
import json
import requests

def retrieve_test_data():
    """
    Used to populate entity lists and link lists to return to ANBP.

    Returns:
        dict: A response with the entities and links.
    """
    entities = []
    links = []

    # TODO: Populate entity and link lists with some dummy data and see that
    # it is returned to ANBP

    response = []
    response['entities'] = entities
    response['links'] = links
    return response

def query_external_datasource():
    """
    Builds the request URL and queries the external datasource using specified parameters.

    Raises:
        FileNotFoundError: If the application.yml file does not exist / could not be found.
    Returns:
        dict: JSON response containing all records.
    """
    entities = []
    links = []

    with open('static/application.yml') as yml_file:
        config = yaml.safe_load(yml_file)

    base_url = config['socrata']['url']
    api_token = config['socrata']['token']

    limit = 1
    request_url = f"{base_url}?$limit={limit}"

    x = requests.get(request_url, headers = { 'X-App-Token': api_token })
    records = x.json()

    for entry in records:
        # TODO: Map response data to your own object model
        # You can remove the following print line as it's only for debugging
        print(entry)

    response = []
    response['entities'] = entities
    response['links'] = links
    return response