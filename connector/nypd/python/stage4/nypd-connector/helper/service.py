# MIT License
#
# © N.Harris Computer Corporation (2023)
#
# Permission is hereby granted, free of charge, to any person obtaining a copy
# of this software and associated documentation files (the "Software"), to deal
# in the Software without restriction, including without limitation the rights
# to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
# copies of the Software, and to permit persons to whom the Software is
# furnished to do so, subject to the following conditions:
#
# The above copyright notice and this permission notice shall be included in all
# copies or substantial portions of the Software.
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
# SOFTWARE.

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