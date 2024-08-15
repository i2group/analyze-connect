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
from spi import util


class ConnectorConfigBase(Model):

    def __init__(self, default_values=None, services=None, auth_configs=None, client_configs=None):  # noqa: E501
        """ConnectorConfigBase

        :param default_values: The default_values of this ConnectorConfigBase.  # noqa: E501
        :type default_values: ConnectorDefaultValues
        :param services: The services of this ConnectorConfigBase.  # noqa: E501
        :type services: List[Service]
        :param auth_configs: The auth_configs of this ConnectorConfigBase.  # noqa: E501
        :type auth_configs: List[AuthConfig]
        :param client_configs: The client_configs of this ConnectorConfigBase.  # noqa: E501
        :type client_configs: List[ClientConfig]
        """
        self.openapi_types = {
            'default_values': ConnectorDefaultValues,
            'services': List[Service],
            'auth_configs': List[AuthConfig],
            'client_configs': List[ClientConfig]
        }

        self.attribute_map = {
            'default_values': 'defaultValues',
            'services': 'services',
            'auth_configs': 'authConfigs',
            'client_configs': 'clientConfigs'
        }

        self._default_values = default_values
        self._services = services
        self._auth_configs = auth_configs
        self._client_configs = client_configs

    @classmethod
    def from_dict(cls, dikt) -> 'ConnectorConfigBase':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The ConnectorConfigBase of this ConnectorConfigBase.  # noqa: E501
        :rtype: ConnectorConfigBase
        """
        return util.deserialize_model(dikt, cls)

    @property
    def default_values(self) -> ConnectorDefaultValues:
        """Gets the default_values of this ConnectorConfigBase.


        :return: The default_values of this ConnectorConfigBase.
        :rtype: ConnectorDefaultValues
        """
        return self._default_values

    @default_values.setter
    def default_values(self, default_values: ConnectorDefaultValues):
        """Sets the default_values of this ConnectorConfigBase.


        :param default_values: The default_values of this ConnectorConfigBase.
        :type default_values: ConnectorDefaultValues
        """

        self._default_values = default_values

    @property
    def services(self) -> List[Service]:
        """Gets the services of this ConnectorConfigBase.

        The services available on the connector. This field is not valid when **userConfigUrl** is present and populated.  # noqa: E501

        :return: The services of this ConnectorConfigBase.
        :rtype: List[Service]
        """
        return self._services

    @services.setter
    def services(self, services: List[Service]):
        """Sets the services of this ConnectorConfigBase.

        The services available on the connector. This field is not valid when **userConfigUrl** is present and populated.  # noqa: E501

        :param services: The services of this ConnectorConfigBase.
        :type services: List[Service]
        """

        self._services = services

    @property
    def auth_configs(self) -> List[AuthConfig]:
        """Gets the auth_configs of this ConnectorConfigBase.

        The authentication configurations that the services on the connector use. This field is not valid when **userConfigUrl** is present and populated.  # noqa: E501

        :return: The auth_configs of this ConnectorConfigBase.
        :rtype: List[AuthConfig]
        """
        return self._auth_configs

    @auth_configs.setter
    def auth_configs(self, auth_configs: List[AuthConfig]):
        """Sets the auth_configs of this ConnectorConfigBase.

        The authentication configurations that the services on the connector use. This field is not valid when **userConfigUrl** is present and populated.  # noqa: E501

        :param auth_configs: The auth_configs of this ConnectorConfigBase.
        :type auth_configs: List[AuthConfig]
        """

        self._auth_configs = auth_configs

    @property
    def client_configs(self) -> List[ClientConfig]:
        """Gets the client_configs of this ConnectorConfigBase.

        The client configurations that the services on the connector use. This field is not valid when **userConfigUrl** is present and populated.  # noqa: E501

        :return: The client_configs of this ConnectorConfigBase.
        :rtype: List[ClientConfig]
        """
        return self._client_configs

    @client_configs.setter
    def client_configs(self, client_configs: List[ClientConfig]):
        """Sets the client_configs of this ConnectorConfigBase.

        The client configurations that the services on the connector use. This field is not valid when **userConfigUrl** is present and populated.  # noqa: E501

        :param client_configs: The client_configs of this ConnectorConfigBase.
        :type client_configs: List[ClientConfig]
        """

        self._client_configs = client_configs
