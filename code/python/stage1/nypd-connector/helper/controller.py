# Licensed Materials - Property of IBM
# (C) Copyright IBM Corporation 2019. All Rights Reserved
#
# This program and the accompanying materials are made available under the
# terms of the Eclipse Public License 2.0 which is available at
# http://www.eclipse.org/legal/epl-2.0.
#
# US Government Users Restricted Rights - Use, duplication or
# disclosure restricted by GSA ADP Schedule Contract with IBM Corp.

from flask import Blueprint, send_from_directory

controller = Blueprint('config', __name__)

@controller.route('/config')
def config():
    """
    Defines endpoint for configuration.
    
    Returns:
        file: The raw config.json file.
    """
    return send_from_directory('static', 'config.json')