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
from spi.models.i2_connect_entity_data import I2ConnectEntityData
from spi.models.i2_connect_link_data import I2ConnectLinkData
from spi import util


class I2ConnectData(Model):

    def __init__(self, entities=None, error_message=None, links=None):  # noqa: E501
        """I2ConnectData

        :param entities: The entities of this I2ConnectData.  # noqa: E501
        :type entities: List[I2ConnectEntityData]
        :param error_message: The error_message of this I2ConnectData.  # noqa: E501
        :type error_message: str
        :param links: The links of this I2ConnectData.  # noqa: E501
        :type links: List[I2ConnectLinkData]
        """
        self.openapi_types = {
            'entities': List[I2ConnectEntityData],
            'error_message': str,
            'links': List[I2ConnectLinkData]
        }

        self.attribute_map = {
            'entities': 'entities',
            'error_message': 'errorMessage',
            'links': 'links'
        }

        self._entities = entities
        self._error_message = error_message
        self._links = links

    @classmethod
    def from_dict(cls, dikt) -> 'I2ConnectData':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The I2ConnectData of this I2ConnectData.  # noqa: E501
        :rtype: I2ConnectData
        """
        return util.deserialize_model(dikt, cls)

    @property
    def entities(self) -> List[I2ConnectEntityData]:
        """Gets the entities of this I2ConnectData.

        The entity data returned from a connector.  # noqa: E501

        :return: The entities of this I2ConnectData.
        :rtype: List[I2ConnectEntityData]
        """
        return self._entities

    @entities.setter
    def entities(self, entities: List[I2ConnectEntityData]):
        """Sets the entities of this I2ConnectData.

        The entity data returned from a connector.  # noqa: E501

        :param entities: The entities of this I2ConnectData.
        :type entities: List[I2ConnectEntityData]
        """

        self._entities = entities

    @property
    def error_message(self) -> str:
        """Gets the error_message of this I2ConnectData.

        An error message that might be displayed to users.  # noqa: E501

        :return: The error_message of this I2ConnectData.
        :rtype: str
        """
        return self._error_message

    @error_message.setter
    def error_message(self, error_message: str):
        """Sets the error_message of this I2ConnectData.

        An error message that might be displayed to users.  # noqa: E501

        :param error_message: The error_message of this I2ConnectData.
        :type error_message: str
        """

        self._error_message = error_message

    @property
    def links(self) -> List[I2ConnectLinkData]:
        """Gets the links of this I2ConnectData.

        The link data returned from a connector.  # noqa: E501

        :return: The links of this I2ConnectData.
        :rtype: List[I2ConnectLinkData]
        """
        return self._links

    @links.setter
    def links(self, links: List[I2ConnectLinkData]):
        """Sets the links of this I2ConnectData.

        The link data returned from a connector.  # noqa: E501

        :param links: The links of this I2ConnectData.
        :type links: List[I2ConnectLinkData]
        """

        self._links = links
