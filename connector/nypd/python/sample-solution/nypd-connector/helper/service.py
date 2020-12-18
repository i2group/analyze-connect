#********************************************************************************
# * Licensed Materials - Property of IBM
# * (C) Copyright IBM Corporation 2020. All Rights Reserved
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

from helper.classes import type_ids
from helper.items import (create_complaint, create_location,
    create_victim, create_suspect, create_location_link,
    create_victim_link, create_suspect_link)

def query_external_datasource(params = ""):
    """
    Builds the request URL and queries the external datasource using specified parameters.
    
    Args:
        params (str): The string of filters to be passed as parameters in the URL.
    Raises:
        FileNotFoundError: If the application.yml file does not exist / could not be found.
    Returns:
        dict: JSON response containing all records.
    """
    with open('static/application.yml') as yml_file:
        config = yaml.safe_load(yml_file)

    base_url = config['socrata']['url']
    api_token = config['socrata']['token']

    limit = 50
    request_url = f"{base_url}?$limit={limit}{params}"

    x = requests.get(request_url, headers = { 'X-App-Token': api_token })
    return x.json()

def marshal(records, types=[], include_links=True):
    """
    Marshal the data from each record into objects of the defined entitiy
    and link classes. Populate list of entities and links with the derived
    objects.
    
    Args:
        records (list): The records from the datasource.
        types (list): List of allowed type IDs to be expected as the result.
        include_links (bool): Indicates whether the results should contain links.
    Returns:
        dict: An object with the populated entities and links.
    """
    entities = []
    links = []

    complaints = {}
    locations = {}

    for entry in records:
        if not types or type_ids['complaint'] in types:
            if entry['cmplnt_num'] in complaints:
                complaint = complaints[entry['cmplnt_num']]
            else:
                complaint = create_complaint(entry)
                complaints[entry['cmplnt_num']] = complaint
                entities.append(complaint)

        if not types or type_ids['location'] in types:
            if entry['addr_pct_cd'] in locations:
                location = locations[entry['addr_pct_cd']]
            else:
                location = create_location(entry)
                locations[entry['addr_pct_cd']] = location
                entities.append(location)
            
            if include_links:
                link_location = create_location_link(entry, complaint, location)
                links.append(link_location)

        if not types:
            victim = create_victim(entry)
            entities.append(victim)
            if include_links:
                link_victim = create_victim_link(entry, victim, complaint)
                links.append(link_victim)

        if not types and 'susp_sex' in entry:
            suspect = create_suspect(entry)
            entities.append(suspect)
            if include_links:
                link_suspect = create_suspect_link(entry, suspect, complaint)
                links.append(link_suspect)

    response = {}
    response['entities'] = entities
    response['links'] = links
    return response

def build_params(conditions, base_clause=""):
    """
    Retrieves complaints which meet specified conditions.
    Args:
        conditions (list): A list of conditions passed in from the search form.
        base_clause (str): The starter clause for the URL.
    Returns:
        str: A full string with the parameters for the URL request.
    """
    params = base_clause

    if conditions:
        for i, condition in enumerate(conditions):
            params += f"{condition.get('id')} LIKE '%{condition.get('value')}%'"
            if i < len(conditions) - 1: params += " AND "

    return params

def validate_request(conditions):
    """
    Performs validation of entered values for conditions.
    Args:
        conditions (list): A list of conditions passed in from the search form.
    Returns:
        dict: An empty response if successful. Contains a custom error message if not.
    """
    response = { 'errorMessage': None }

    for condition in conditions:
        id = condition.get('id')
        value = condition.get('value')

        if id == 'law_cat_cd':
            if len(str(value)) < 3:
                response['errorMessage'] = "Enter 3 or more characters to search by offence."
    
    return response

def impl_find_like_this_complaint(seeds):
    """
    Retrieves entities which match properties of a complaint seed.

    A Find-Like-This attempts to find other entities which are similar to the
    selected entity based on its properties.
    Args:
        seeds (dict): The seeds passed in from Analyst's Notebook Premium.
    Returns:
        dict: Object containing entities.
    """
    response = {}

    if seeds:
        properties = seeds['entities'][0]['properties']
        params = f"&$where=law_cat_cd='{properties['PT10']}'"

        records = query_external_datasource(params)
        response = marshal(records, type_ids['complaint'], False)
    
    return response

def impl_expand(seeds, initial_params=""):
    """
    Retrieves entities and links which are connected to seed.
    An Expand operation takes an entity as a seed and displays the entities and
    links connnected to that seed.
    Args:
        seeds (dict): The seeds passed in from Analyst's Notebook Premium.
        initial_params (str): Parameters that may be passed from conditional search.
    Returns:
        dict: Object containing entities and links.
    """
    response = {}

    if seeds:
        entity = seeds['entities'][0]
        properties = entity['properties']
        params = ""

        if entity['typeId'] == type_ids['location']:
            if not initial_params:
                params += f"&$where=addr_pct_cd='{properties['PT15']}'"
            else:
                params += f"{initial_params} AND addr_pct_cd='{properties['PT15']}'"

        records = query_external_datasource(params)
        response = marshal(records)

        for link in response['links']:
            source_id = entity['sourceIds'][0]['key'][2]
            if link['toEndId'] == source_id:
                link['toEndId'] = entity['seedId']
            elif link['fromEndId'] == source_id:
                link['fromEndId'] = entity['seedId']

    return response