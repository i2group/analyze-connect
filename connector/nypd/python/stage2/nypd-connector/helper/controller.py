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

from flask import Blueprint, send_from_directory
from helper.service import retrieve_test_data

controller = Blueprint('config', __name__)

@controller.route('/config')
def config():
    """
    Defines endpoint for configuration.
    """
    return send_from_directory('static', 'config.json')

@controller.route('/test-service', methods=['POST'])
def test():
    """
    Defines the /test-service endpoint which acquires the
    test-service data (entities and links)
    """
    return retrieve_test_data()