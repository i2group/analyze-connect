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

from helper.classes import type_ids
from helper.service import (query_external_datasource, marshal, build_params,
    impl_expand, impl_find_like_this_complaint, validate_request)

controller = Blueprint('config', __name__)

@controller.route('/config')
def config():
    return send_from_directory('static', 'config.json')

@controller.route('/schema')
def schema():
    return send_from_directory('static', 'nypd-complaint-data-schema.xml')

@controller.route('/charting-schemes')
def charting_schemes():
    return send_from_directory('static', 'nypd-complaint-data-schema-charting-schemes.xml')

@controller.route('/all', methods=['GET', 'POST'])
def all():
    records = query_external_datasource()
    response = marshal(records)
    return response

@controller.route('/search', methods=['POST'])
def search():
    conditions = request.json['payload']['conditions']

    params = build_params(conditions, "&$where=")
    records = query_external_datasource(params)

    response = marshal(records, type_ids['complaint'], False)
    return response

@controller.route('/search/validate', methods=['POST'])
def search_validate():
    conditions = request.json['payload']['conditions']
    response = validate_request(conditions)
    return response

@controller.route('/find-like-this-complaint', methods=['POST'])
def find_like_this():
    seeds = request.json['payload']['seeds']
    response = impl_find_like_this_complaint(seeds)
    return response

@controller.route('/expand', methods=['POST'])
def expand():
    seeds = request.json['payload']['seeds']
    response = impl_expand(seeds)
    return response

@controller.route('/expand-with-conditions', methods=['POST'])
def expand_with_conditions():
    payload = request.json['payload']
    conditions = payload['conditions']
    seeds = payload['seeds']

    params = build_params(conditions, "&$where=")
    response = impl_expand(seeds, initial_params=params)

    return response

@controller.route('/expand-with-conditions/validate', methods=['POST'])
def expand_validate():
    conditions = request.json['payload']['conditions']
    response = validate_request(conditions)
    return response