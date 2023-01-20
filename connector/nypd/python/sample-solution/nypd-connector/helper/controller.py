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

from flask import Blueprint, request, send_from_directory

from helper.classes import type_ids
from helper.service import (query_external_datasource, marshal, build_params,
    impl_expand, impl_find_like_this_complaint, validate_request)

controller = Blueprint('config', __name__)
connector_major_version = '1'
connector_minor_version = '0'

@controller.route('/config')
def config():
    gateway_supported_versions = request.headers['I2-Spi-Versions']
    print('Gateway supported versions: ' + gateway_supported_versions)

    for supported_version in gateway_supported_versions.split(','):
        supported_version_parts = supported_version.split('.')
        supported_major_version = supported_version_parts[0]
        supported_minor_version = supported_version_parts[1]

        connector_version = connector_major_version + '.' + connector_minor_version

        if ((connector_major_version == supported_major_version) and (connector_minor_version <= supported_minor_version)):
            print('The gateway supports connector version ' + connector_version)
        else:
            print('The gateway does not support connector version ' + connector_version)

    return send_from_directory('static', 'config.json')

@controller.route('/schema')
def schema():
    return send_from_directory('static', 'nypd-complaint-data-schema.xml')

@controller.route('/charting-schemes')
def charting_schemes():
    return send_from_directory('static', 'nypd-complaint-data-schema-charting-schemes.xml')

@controller.route('/all', methods=['POST'])
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