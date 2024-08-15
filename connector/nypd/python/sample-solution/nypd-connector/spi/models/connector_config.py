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
from spi.models.auth_config import AuthConfig
from spi.models.client_config import ClientConfig
from spi.models.connector_default_values import ConnectorDefaultValues
from spi.models.service import Service
import re
from spi import util


class ConnectorConfig(Model):

    def __init__(self, default_values=None, services=None, auth_configs=None, client_configs=None, version=None, charting_schemes_url=None, gateway_schema=None, schema_short_name=None, schema_url=None, user_config_url=None):  # noqa: E501
        """ConnectorConfig

        :param default_values: The default_values of this ConnectorConfig.  # noqa: E501
        :type default_values: ConnectorDefaultValues
        :param services: The services of this ConnectorConfig.  # noqa: E501
        :type services: List[Service]
        :param auth_configs: The auth_configs of this ConnectorConfig.  # noqa: E501
        :type auth_configs: List[AuthConfig]
        :param client_configs: The client_configs of this ConnectorConfig.  # noqa: E501
        :type client_configs: List[ClientConfig]
        :param version: The version of this ConnectorConfig.  # noqa: E501
        :type version: str
        :param charting_schemes_url: The charting_schemes_url of this ConnectorConfig.  # noqa: E501
        :type charting_schemes_url: str
        :param gateway_schema: The gateway_schema of this ConnectorConfig.  # noqa: E501
        :type gateway_schema: str
        :param schema_short_name: The schema_short_name of this ConnectorConfig.  # noqa: E501
        :type schema_short_name: str
        :param schema_url: The schema_url of this ConnectorConfig.  # noqa: E501
        :type schema_url: str
        :param user_config_url: The user_config_url of this ConnectorConfig.  # noqa: E501
        :type user_config_url: str
        """
        self.openapi_types = {
            'default_values': ConnectorDefaultValues,
            'services': List[Service],
            'auth_configs': List[AuthConfig],
            'client_configs': List[ClientConfig],
            'version': str,
            'charting_schemes_url': str,
            'gateway_schema': str,
            'schema_short_name': str,
            'schema_url': str,
            'user_config_url': str
        }

        self.attribute_map = {
            'default_values': 'defaultValues',
            'services': 'services',
            'auth_configs': 'authConfigs',
            'client_configs': 'clientConfigs',
            'version': 'version',
            'charting_schemes_url': 'chartingSchemesUrl',
            'gateway_schema': 'gatewaySchema',
            'schema_short_name': 'schemaShortName',
            'schema_url': 'schemaUrl',
            'user_config_url': 'userConfigUrl'
        }

        self._default_values = default_values
        self._services = services
        self._auth_configs = auth_configs
        self._client_configs = client_configs
        self._version = version
        self._charting_schemes_url = charting_schemes_url
        self._gateway_schema = gateway_schema
        self._schema_short_name = schema_short_name
        self._schema_url = schema_url
        self._user_config_url = user_config_url

    @classmethod
    def from_dict(cls, dikt) -> 'ConnectorConfig':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The ConnectorConfig of this ConnectorConfig.  # noqa: E501
        :rtype: ConnectorConfig
        """
        return util.deserialize_model(dikt, cls)

    @property
    def default_values(self) -> ConnectorDefaultValues:
        """Gets the default_values of this ConnectorConfig.


        :return: The default_values of this ConnectorConfig.
        :rtype: ConnectorDefaultValues
        """
        return self._default_values

    @default_values.setter
    def default_values(self, default_values: ConnectorDefaultValues):
        """Sets the default_values of this ConnectorConfig.


        :param default_values: The default_values of this ConnectorConfig.
        :type default_values: ConnectorDefaultValues
        """

        self._default_values = default_values

    @property
    def services(self) -> List[Service]:
        """Gets the services of this ConnectorConfig.

        The services available on the connector. This field is not valid when **userConfigUrl** is present and populated.  # noqa: E501

        :return: The services of this ConnectorConfig.
        :rtype: List[Service]
        """
        return self._services

    @services.setter
    def services(self, services: List[Service]):
        """Sets the services of this ConnectorConfig.

        The services available on the connector. This field is not valid when **userConfigUrl** is present and populated.  # noqa: E501

        :param services: The services of this ConnectorConfig.
        :type services: List[Service]
        """

        self._services = services

    @property
    def auth_configs(self) -> List[AuthConfig]:
        """Gets the auth_configs of this ConnectorConfig.

        The authentication configurations that the services on the connector use. This field is not valid when **userConfigUrl** is present and populated.  # noqa: E501

        :return: The auth_configs of this ConnectorConfig.
        :rtype: List[AuthConfig]
        """
        return self._auth_configs

    @auth_configs.setter
    def auth_configs(self, auth_configs: List[AuthConfig]):
        """Sets the auth_configs of this ConnectorConfig.

        The authentication configurations that the services on the connector use. This field is not valid when **userConfigUrl** is present and populated.  # noqa: E501

        :param auth_configs: The auth_configs of this ConnectorConfig.
        :type auth_configs: List[AuthConfig]
        """

        self._auth_configs = auth_configs

    @property
    def client_configs(self) -> List[ClientConfig]:
        """Gets the client_configs of this ConnectorConfig.

        The client configurations that the services on the connector use. This field is not valid when **userConfigUrl** is present and populated.  # noqa: E501

        :return: The client_configs of this ConnectorConfig.
        :rtype: List[ClientConfig]
        """
        return self._client_configs

    @client_configs.setter
    def client_configs(self, client_configs: List[ClientConfig]):
        """Sets the client_configs of this ConnectorConfig.

        The client configurations that the services on the connector use. This field is not valid when **userConfigUrl** is present and populated.  # noqa: E501

        :param client_configs: The client_configs of this ConnectorConfig.
        :type client_configs: List[ClientConfig]
        """

        self._client_configs = client_configs

    @property
    def version(self) -> str:
        """Gets the version of this ConnectorConfig.

        The minimum SPI version that the connector requires. If no value is specified, the server assumes that the connector requires version `1.0`.  # noqa: E501

        :return: The version of this ConnectorConfig.
        :rtype: str
        """
        return self._version

    @version.setter
    def version(self, version: str):
        """Sets the version of this ConnectorConfig.

        The minimum SPI version that the connector requires. If no value is specified, the server assumes that the connector requires version `1.0`.  # noqa: E501

        :param version: The version of this ConnectorConfig.
        :type version: str
        """
        if version is not None and not re.search(r'\d+\.\d+', version):  # noqa: E501
            raise ValueError("Invalid value for `version`, must be a follow pattern or equal to `/\d+\.\d+/`")  # noqa: E501

        self._version = version

    @property
    def charting_schemes_url(self) -> str:
        """Gets the charting_schemes_url of this ConnectorConfig.

        The URL from which to retrieve a charting scheme for the connector, which can be absolute or relative to its base URL. No value means that there is no charting scheme.  # noqa: E501

        :return: The charting_schemes_url of this ConnectorConfig.
        :rtype: str
        """
        return self._charting_schemes_url

    @charting_schemes_url.setter
    def charting_schemes_url(self, charting_schemes_url: str):
        """Sets the charting_schemes_url of this ConnectorConfig.

        The URL from which to retrieve a charting scheme for the connector, which can be absolute or relative to its base URL. No value means that there is no charting scheme.  # noqa: E501

        :param charting_schemes_url: The charting_schemes_url of this ConnectorConfig.
        :type charting_schemes_url: str
        """

        self._charting_schemes_url = charting_schemes_url

    @property
    def gateway_schema(self) -> str:
        """Gets the gateway_schema of this ConnectorConfig.

        The short name of the gateway schema whose types the connector uses, or no value if the connector does not use a gateway schema. The topology for the deployment can override the gateway schema specified here.  # noqa: E501

        :return: The gateway_schema of this ConnectorConfig.
        :rtype: str
        """
        return self._gateway_schema

    @gateway_schema.setter
    def gateway_schema(self, gateway_schema: str):
        """Sets the gateway_schema of this ConnectorConfig.

        The short name of the gateway schema whose types the connector uses, or no value if the connector does not use a gateway schema. The topology for the deployment can override the gateway schema specified here.  # noqa: E501

        :param gateway_schema: The gateway_schema of this ConnectorConfig.
        :type gateway_schema: str
        """

        self._gateway_schema = gateway_schema

    @property
    def schema_short_name(self) -> str:
        """Gets the schema_short_name of this ConnectorConfig.

        The short name for the connector schema, or no value when there is no connector schema. The topology for the deployment can override the name specified here.  # noqa: E501

        :return: The schema_short_name of this ConnectorConfig.
        :rtype: str
        """
        return self._schema_short_name

    @schema_short_name.setter
    def schema_short_name(self, schema_short_name: str):
        """Sets the schema_short_name of this ConnectorConfig.

        The short name for the connector schema, or no value when there is no connector schema. The topology for the deployment can override the name specified here.  # noqa: E501

        :param schema_short_name: The schema_short_name of this ConnectorConfig.
        :type schema_short_name: str
        """

        self._schema_short_name = schema_short_name

    @property
    def schema_url(self) -> str:
        """Gets the schema_url of this ConnectorConfig.

        The URL from which to retrieve a schema for the connector, which can be absolute or relative to its base URL. No value means that there is no schema.  # noqa: E501

        :return: The schema_url of this ConnectorConfig.
        :rtype: str
        """
        return self._schema_url

    @schema_url.setter
    def schema_url(self, schema_url: str):
        """Sets the schema_url of this ConnectorConfig.

        The URL from which to retrieve a schema for the connector, which can be absolute or relative to its base URL. No value means that there is no schema.  # noqa: E501

        :param schema_url: The schema_url of this ConnectorConfig.
        :type schema_url: str
        """

        self._schema_url = schema_url

    @property
    def user_config_url(self) -> str:
        """Gets the user_config_url of this ConnectorConfig.

        The URL from which to retrieve user-specific configuration settings, which can be absolute or relative to the connector's base URL. No value means that there is no user-specific configuration.  When this field is present and populated, **schemaUrl**, **chartingSchemesUrl**, **schemaShortName**, and **gatewaySchema** can also be present, but no other fields are valid.  # noqa: E501

        :return: The user_config_url of this ConnectorConfig.
        :rtype: str
        """
        return self._user_config_url

    @user_config_url.setter
    def user_config_url(self, user_config_url: str):
        """Sets the user_config_url of this ConnectorConfig.

        The URL from which to retrieve user-specific configuration settings, which can be absolute or relative to the connector's base URL. No value means that there is no user-specific configuration.  When this field is present and populated, **schemaUrl**, **chartingSchemesUrl**, **schemaShortName**, and **gatewaySchema** can also be present, but no other fields are valid.  # noqa: E501

        :param user_config_url: The user_config_url of this ConnectorConfig.
        :type user_config_url: str
        """

        self._user_config_url = user_config_url
