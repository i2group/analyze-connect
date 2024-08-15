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
from spi.models.client_config_config import ClientConfigConfig
from spi.models.client_config_type import ClientConfigType
from spi import util


class ClientConfig(Model):

    def __init__(self, config=None, id=None, type=None):  # noqa: E501
        """ClientConfig

        :param config: The config of this ClientConfig.  # noqa: E501
        :type config: ClientConfigConfig
        :param id: The id of this ClientConfig.  # noqa: E501
        :type id: str
        :param type: The type of this ClientConfig.  # noqa: E501
        :type type: ClientConfigType
        """
        self.openapi_types = {
            'config': ClientConfigConfig,
            'id': str,
            'type': ClientConfigType
        }

        self.attribute_map = {
            'config': 'config',
            'id': 'id',
            'type': 'type'
        }

        self._config = config
        self._id = id
        self._type = type

    @classmethod
    def from_dict(cls, dikt) -> 'ClientConfig':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The ClientConfig of this ClientConfig.  # noqa: E501
        :rtype: ClientConfig
        """
        return util.deserialize_model(dikt, cls)

    @property
    def config(self) -> ClientConfigConfig:
        """Gets the config of this ClientConfig.


        :return: The config of this ClientConfig.
        :rtype: ClientConfigConfig
        """
        return self._config

    @config.setter
    def config(self, config: ClientConfigConfig):
        """Sets the config of this ClientConfig.


        :param config: The config of this ClientConfig.
        :type config: ClientConfigConfig
        """
        if config is None:
            raise ValueError("Invalid value for `config`, must not be `None`")  # noqa: E501

        self._config = config

    @property
    def id(self) -> str:
        """Gets the id of this ClientConfig.

        The unique identifier of the client configuration.  # noqa: E501

        :return: The id of this ClientConfig.
        :rtype: str
        """
        return self._id

    @id.setter
    def id(self, id: str):
        """Sets the id of this ClientConfig.

        The unique identifier of the client configuration.  # noqa: E501

        :param id: The id of this ClientConfig.
        :type id: str
        """
        if id is None:
            raise ValueError("Invalid value for `id`, must not be `None`")  # noqa: E501

        self._id = id

    @property
    def type(self) -> ClientConfigType:
        """Gets the type of this ClientConfig.


        :return: The type of this ClientConfig.
        :rtype: ClientConfigType
        """
        return self._type

    @type.setter
    def type(self, type: ClientConfigType):
        """Sets the type of this ClientConfig.


        :param type: The type of this ClientConfig.
        :type type: ClientConfigType
        """
        if type is None:
            raise ValueError("Invalid value for `type`, must not be `None`")  # noqa: E501

        self._type = type
