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
from spi.models.semantic_property import SemanticProperty
from spi import util


class SemanticSeed(Model):

    def __init__(self, seed_id=None, is_link=False, properties=None):  # noqa: E501
        """SemanticSeed

        :param seed_id: The seed_id of this SemanticSeed.  # noqa: E501
        :type seed_id: str
        :param is_link: The is_link of this SemanticSeed.  # noqa: E501
        :type is_link: bool
        :param properties: The properties of this SemanticSeed.  # noqa: E501
        :type properties: Dict[str, list[SemanticProperty]]
        """
        self.openapi_types = {
            'seed_id': str,
            'is_link': bool,
            'properties': Dict[str, list[SemanticProperty]]
        }

        self.attribute_map = {
            'seed_id': 'seedId',
            'is_link': 'isLink',
            'properties': 'properties'
        }

        self._seed_id = seed_id
        self._is_link = is_link
        self._properties = properties

    @classmethod
    def from_dict(cls, dikt) -> 'SemanticSeed':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The SemanticSeed of this SemanticSeed.  # noqa: E501
        :rtype: SemanticSeed
        """
        return util.deserialize_model(dikt, cls)

    @property
    def seed_id(self) -> str:
        """Gets the seed_id of this SemanticSeed.

        The identifier of the record that contains the semantic property values.  # noqa: E501

        :return: The seed_id of this SemanticSeed.
        :rtype: str
        """
        return self._seed_id

    @seed_id.setter
    def seed_id(self, seed_id: str):
        """Sets the seed_id of this SemanticSeed.

        The identifier of the record that contains the semantic property values.  # noqa: E501

        :param seed_id: The seed_id of this SemanticSeed.
        :type seed_id: str
        """
        if seed_id is None:
            raise ValueError("Invalid value for `seed_id`, must not be `None`")  # noqa: E501

        self._seed_id = seed_id

    @property
    def is_link(self) -> bool:
        """Gets the is_link of this SemanticSeed.

        Indicates whether the record identified in **seedId** is a link.  # noqa: E501

        :return: The is_link of this SemanticSeed.
        :rtype: bool
        """
        return self._is_link

    @is_link.setter
    def is_link(self, is_link: bool):
        """Sets the is_link of this SemanticSeed.

        Indicates whether the record identified in **seedId** is a link.  # noqa: E501

        :param is_link: The is_link of this SemanticSeed.
        :type is_link: bool
        """

        self._is_link = is_link

    @property
    def properties(self) -> Dict[str, list[SemanticProperty]]:
        """Gets the properties of this SemanticSeed.

        A map of property values, keyed by their semantic type GUIDs.  # noqa: E501

        :return: The properties of this SemanticSeed.
        :rtype: Dict[str, list[SemanticProperty]]
        """
        return self._properties

    @properties.setter
    def properties(self, properties: Dict[str, list[SemanticProperty]]):
        """Sets the properties of this SemanticSeed.

        A map of property values, keyed by their semantic type GUIDs.  # noqa: E501

        :param properties: The properties of this SemanticSeed.
        :type properties: Dict[str, list[SemanticProperty]]
        """
        if properties is None:
            raise ValueError("Invalid value for `properties`, must not be `None`")  # noqa: E501

        self._properties = properties
