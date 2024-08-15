# MIT License
#
# © N.Harris Computer Corporation (2024)
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

from datetime import date, datetime  # noqa: F401

from typing import List, Dict  # noqa: F401

from spi.models.base_model import Model
from spi.models.connector_seed_constraints import ConnectorSeedConstraints
from spi.models.model_async import ModelAsync
from spi import util


class Service(Model):

    def __init__(self, id=None, name=None, description=None, acquire_url=None, _async=None, auth_config_id=None, client_config_id=None, client_config_type=None, result_ids_persistent=None, result_item_type_ids=None, seed_constraints=None, validate_url=None):  # noqa: E501
        """Service

        :param id: The id of this Service.  # noqa: E501
        :type id: str
        :param name: The name of this Service.  # noqa: E501
        :type name: str
        :param description: The description of this Service.  # noqa: E501
        :type description: str
        :param acquire_url: The acquire_url of this Service.  # noqa: E501
        :type acquire_url: str
        :param _async: The _async of this Service.  # noqa: E501
        :type _async: ModelAsync
        :param auth_config_id: The auth_config_id of this Service.  # noqa: E501
        :type auth_config_id: str
        :param client_config_id: The client_config_id of this Service.  # noqa: E501
        :type client_config_id: str
        :param client_config_type: The client_config_type of this Service.  # noqa: E501
        :type client_config_type: str
        :param result_ids_persistent: The result_ids_persistent of this Service.  # noqa: E501
        :type result_ids_persistent: bool
        :param result_item_type_ids: The result_item_type_ids of this Service.  # noqa: E501
        :type result_item_type_ids: Dict[str, object]
        :param seed_constraints: The seed_constraints of this Service.  # noqa: E501
        :type seed_constraints: ConnectorSeedConstraints
        :param validate_url: The validate_url of this Service.  # noqa: E501
        :type validate_url: str
        """
        self.openapi_types = {
            'id': str,
            'name': str,
            'description': str,
            'acquire_url': str,
            '_async': ModelAsync,
            'auth_config_id': str,
            'client_config_id': str,
            'client_config_type': str,
            'result_ids_persistent': bool,
            'result_item_type_ids': Dict[str, object],
            'seed_constraints': ConnectorSeedConstraints,
            'validate_url': str
        }

        self.attribute_map = {
            'id': 'id',
            'name': 'name',
            'description': 'description',
            'acquire_url': 'acquireUrl',
            '_async': 'async',
            'auth_config_id': 'authConfigId',
            'client_config_id': 'clientConfigId',
            'client_config_type': 'clientConfigType',
            'result_ids_persistent': 'resultIdsPersistent',
            'result_item_type_ids': 'resultItemTypeIds',
            'seed_constraints': 'seedConstraints',
            'validate_url': 'validateUrl'
        }

        self._id = id
        self._name = name
        self._description = description
        self._acquire_url = acquire_url
        self.__async = _async
        self._auth_config_id = auth_config_id
        self._client_config_id = client_config_id
        self._client_config_type = client_config_type
        self._result_ids_persistent = result_ids_persistent
        self._result_item_type_ids = result_item_type_ids
        self._seed_constraints = seed_constraints
        self._validate_url = validate_url

    @classmethod
    def from_dict(cls, dikt) -> 'Service':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The Service of this Service.  # noqa: E501
        :rtype: Service
        """
        return util.deserialize_model(dikt, cls)

    @property
    def id(self) -> str:
        """Gets the id of this Service.

        The unique identifier of the service.  # noqa: E501

        :return: The id of this Service.
        :rtype: str
        """
        return self._id

    @id.setter
    def id(self, id: str):
        """Sets the id of this Service.

        The unique identifier of the service.  # noqa: E501

        :param id: The id of this Service.
        :type id: str
        """
        if id is None:
            raise ValueError("Invalid value for `id`, must not be `None`")  # noqa: E501

        self._id = id

    @property
    def name(self) -> str:
        """Gets the name of this Service.

        The name of the service, which might be displayed to users.  # noqa: E501

        :return: The name of this Service.
        :rtype: str
        """
        return self._name

    @name.setter
    def name(self, name: str):
        """Sets the name of this Service.

        The name of the service, which might be displayed to users.  # noqa: E501

        :param name: The name of this Service.
        :type name: str
        """
        if name is None:
            raise ValueError("Invalid value for `name`, must not be `None`")  # noqa: E501

        self._name = name

    @property
    def description(self) -> str:
        """Gets the description of this Service.

        A description of the service, which might be displayed to users.  # noqa: E501

        :return: The description of this Service.
        :rtype: str
        """
        return self._description

    @description.setter
    def description(self, description: str):
        """Sets the description of this Service.

        A description of the service, which might be displayed to users.  # noqa: E501

        :param description: The description of this Service.
        :type description: str
        """

        self._description = description

    @property
    def acquire_url(self) -> str:
        """Gets the acquire_url of this Service.

        The URL for the endpoint that provides result data from the service. If this is present, then **async** must not be present.  # noqa: E501

        :return: The acquire_url of this Service.
        :rtype: str
        """
        return self._acquire_url

    @acquire_url.setter
    def acquire_url(self, acquire_url: str):
        """Sets the acquire_url of this Service.

        The URL for the endpoint that provides result data from the service. If this is present, then **async** must not be present.  # noqa: E501

        :param acquire_url: The acquire_url of this Service.
        :type acquire_url: str
        """

        self._acquire_url = acquire_url

    @property
    def _async(self) -> ModelAsync:
        """Gets the _async of this Service.


        :return: The _async of this Service.
        :rtype: ModelAsync
        """
        return self.__async

    @_async.setter
    def _async(self, _async: ModelAsync):
        """Sets the _async of this Service.


        :param _async: The _async of this Service.
        :type _async: ModelAsync
        """

        self.__async = _async

    @property
    def auth_config_id(self) -> str:
        """Gets the auth_config_id of this Service.

        The identifier of the authentication configuration for the service.  # noqa: E501

        :return: The auth_config_id of this Service.
        :rtype: str
        """
        return self._auth_config_id

    @auth_config_id.setter
    def auth_config_id(self, auth_config_id: str):
        """Sets the auth_config_id of this Service.

        The identifier of the authentication configuration for the service.  # noqa: E501

        :param auth_config_id: The auth_config_id of this Service.
        :type auth_config_id: str
        """

        self._auth_config_id = auth_config_id

    @property
    def client_config_id(self) -> str:
        """Gets the client_config_id of this Service.

        The identifier of the client configuration for the service, if the type is not 'NONE'.  # noqa: E501

        :return: The client_config_id of this Service.
        :rtype: str
        """
        return self._client_config_id

    @client_config_id.setter
    def client_config_id(self, client_config_id: str):
        """Sets the client_config_id of this Service.

        The identifier of the client configuration for the service, if the type is not 'NONE'.  # noqa: E501

        :param client_config_id: The client_config_id of this Service.
        :type client_config_id: str
        """

        self._client_config_id = client_config_id

    @property
    def client_config_type(self) -> str:
        """Gets the client_config_type of this Service.

        The type of the client configuration for the service.  **Deprecated:** This field will be removed in a future release. Use the type in the clientConfig instead. Do not rely on the value of this field if it is set.  # noqa: E501

        :return: The client_config_type of this Service.
        :rtype: str
        """
        return self._client_config_type

    @client_config_type.setter
    def client_config_type(self, client_config_type: str):
        """Sets the client_config_type of this Service.

        The type of the client configuration for the service.  **Deprecated:** This field will be removed in a future release. Use the type in the clientConfig instead. Do not rely on the value of this field if it is set.  # noqa: E501

        :param client_config_type: The client_config_type of this Service.
        :type client_config_type: str
        """
        allowed_values = ["CUSTOM", "FORM", "NONE"]  # noqa: E501
        if client_config_type not in allowed_values:
            raise ValueError(
                "Invalid value for `client_config_type` ({0}), must be one of {1}"
                .format(client_config_type, allowed_values)
            )

        self._client_config_type = client_config_type

    @property
    def result_ids_persistent(self) -> bool:
        """Gets the result_ids_persistent of this Service.

        **true** if identifiers for the same data retrieved from the service are persistent from one set of results to the next; **false** otherwise.  # noqa: E501

        :return: The result_ids_persistent of this Service.
        :rtype: bool
        """
        return self._result_ids_persistent

    @result_ids_persistent.setter
    def result_ids_persistent(self, result_ids_persistent: bool):
        """Sets the result_ids_persistent of this Service.

        **true** if identifiers for the same data retrieved from the service are persistent from one set of results to the next; **false** otherwise.  # noqa: E501

        :param result_ids_persistent: The result_ids_persistent of this Service.
        :type result_ids_persistent: bool
        """

        self._result_ids_persistent = result_ids_persistent

    @property
    def result_item_type_ids(self) -> Dict[str, object]:
        """Gets the result_item_type_ids of this Service.

        The identifiers of the item types that can appear in results from the service, in a map with the following structure: **{\"_LOCATION_\": [\"_TYPEID1_\", \"_TYPEID2_\", ...], ...}**.  Here, _LOCATION_ indicates which schema the item type is defined in. Legal values are 'INFOSTORE', 'GATEWAY', and 'CONNECTOR'. If a service uses types from all three locations, the map has three elements.  # noqa: E501

        :return: The result_item_type_ids of this Service.
        :rtype: Dict[str, object]
        """
        return self._result_item_type_ids

    @result_item_type_ids.setter
    def result_item_type_ids(self, result_item_type_ids: Dict[str, object]):
        """Sets the result_item_type_ids of this Service.

        The identifiers of the item types that can appear in results from the service, in a map with the following structure: **{\"_LOCATION_\": [\"_TYPEID1_\", \"_TYPEID2_\", ...], ...}**.  Here, _LOCATION_ indicates which schema the item type is defined in. Legal values are 'INFOSTORE', 'GATEWAY', and 'CONNECTOR'. If a service uses types from all three locations, the map has three elements.  # noqa: E501

        :param result_item_type_ids: The result_item_type_ids of this Service.
        :type result_item_type_ids: Dict[str, object]
        """

        self._result_item_type_ids = result_item_type_ids

    @property
    def seed_constraints(self) -> ConnectorSeedConstraints:
        """Gets the seed_constraints of this Service.


        :return: The seed_constraints of this Service.
        :rtype: ConnectorSeedConstraints
        """
        return self._seed_constraints

    @seed_constraints.setter
    def seed_constraints(self, seed_constraints: ConnectorSeedConstraints):
        """Sets the seed_constraints of this Service.


        :param seed_constraints: The seed_constraints of this Service.
        :type seed_constraints: ConnectorSeedConstraints
        """

        self._seed_constraints = seed_constraints

    @property
    def validate_url(self) -> str:
        """Gets the validate_url of this Service.

        The URL for the endpoint that validates requests for data from the service.  # noqa: E501

        :return: The validate_url of this Service.
        :rtype: str
        """
        return self._validate_url

    @validate_url.setter
    def validate_url(self, validate_url: str):
        """Sets the validate_url of this Service.

        The URL for the endpoint that validates requests for data from the service.  # noqa: E501

        :param validate_url: The validate_url of this Service.
        :type validate_url: str
        """

        self._validate_url = validate_url
