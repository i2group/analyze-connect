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

from flask import Blueprint, request, send_from_directory
from helper.service import query_external_datasource

controller = Blueprint('config', __name__)

@controller.route('/config')
def config():
    """
    Defines endpoint for configuration.
    """
    return send_from_directory('static', 'config.json')

@controller.route('/test-service')
def all():
    """
    Defines the /test-service endpoint which acquires the
    test-service data (entities and links)
    """
    return query_external_datasource()

@controller.route('/test-service-search')
def search():
    """
    Defines the /test-search-service endpoint which acquires the
    test-search-service data (entities and links based on conditions)
    """
    print(request.json)

    # TODO: Extract request conditions and use in request to Socrata.
    # Pass conditions into your function.

    return query_external_datasource()

@controller.route('/test-service-seeded-search')
def seeded_search():
    """
    Defines the /test-search-service endpoint which acquires the
    test-search-service data (entities and links based on conditions)
    """
    print(request.json['payload']['seeds'])

    # TODO: Extract seeded information and use it to search

    return query_external_datasource()