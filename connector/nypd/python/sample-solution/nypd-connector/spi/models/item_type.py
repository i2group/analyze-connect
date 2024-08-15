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
from spi.models.property_type import PropertyType
from spi.models.type_location import TypeLocation
from spi import util


class ItemType(Model):

    def __init__(self, display_name=None, property_types=None, semantic_type_id=None, type_id=None, type_location=None):  # noqa: E501
        """ItemType

        :param display_name: The display_name of this ItemType.  # noqa: E501
        :type display_name: str
        :param property_types: The property_types of this ItemType.  # noqa: E501
        :type property_types: Dict[str, PropertyType]
        :param semantic_type_id: The semantic_type_id of this ItemType.  # noqa: E501
        :type semantic_type_id: str
        :param type_id: The type_id of this ItemType.  # noqa: E501
        :type type_id: str
        :param type_location: The type_location of this ItemType.  # noqa: E501
        :type type_location: TypeLocation
        """
        self.openapi_types = {
            'display_name': str,
            'property_types': Dict[str, PropertyType],
            'semantic_type_id': str,
            'type_id': str,
            'type_location': TypeLocation
        }

        self.attribute_map = {
            'display_name': 'displayName',
            'property_types': 'propertyTypes',
            'semantic_type_id': 'semanticTypeId',
            'type_id': 'typeId',
            'type_location': 'typeLocation'
        }

        self._display_name = display_name
        self._property_types = property_types
        self._semantic_type_id = semantic_type_id
        self._type_id = type_id
        self._type_location = type_location

    @classmethod
    def from_dict(cls, dikt) -> 'ItemType':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The ItemType of this ItemType.  # noqa: E501
        :rtype: ItemType
        """
        return util.deserialize_model(dikt, cls)

    @property
    def display_name(self) -> str:
        """Gets the display_name of this ItemType.

        The display name for this item type.  # noqa: E501

        :return: The display_name of this ItemType.
        :rtype: str
        """
        return self._display_name

    @display_name.setter
    def display_name(self, display_name: str):
        """Sets the display_name of this ItemType.

        The display name for this item type.  # noqa: E501

        :param display_name: The display_name of this ItemType.
        :type display_name: str
        """

        self._display_name = display_name

    @property
    def property_types(self) -> Dict[str, PropertyType]:
        """Gets the property_types of this ItemType.

        The property types of this item type.  # noqa: E501

        :return: The property_types of this ItemType.
        :rtype: Dict[str, PropertyType]
        """
        return self._property_types

    @property_types.setter
    def property_types(self, property_types: Dict[str, PropertyType]):
        """Sets the property_types of this ItemType.

        The property types of this item type.  # noqa: E501

        :param property_types: The property_types of this ItemType.
        :type property_types: Dict[str, PropertyType]
        """

        self._property_types = property_types

    @property
    def semantic_type_id(self) -> str:
        """Gets the semantic_type_id of this ItemType.

        The identifier of the semantic type for this item type.  # noqa: E501

        :return: The semantic_type_id of this ItemType.
        :rtype: str
        """
        return self._semantic_type_id

    @semantic_type_id.setter
    def semantic_type_id(self, semantic_type_id: str):
        """Sets the semantic_type_id of this ItemType.

        The identifier of the semantic type for this item type.  # noqa: E501

        :param semantic_type_id: The semantic_type_id of this ItemType.
        :type semantic_type_id: str
        """

        self._semantic_type_id = semantic_type_id

    @property
    def type_id(self) -> str:
        """Gets the type_id of this ItemType.

        The identifier of this item type.  # noqa: E501

        :return: The type_id of this ItemType.
        :rtype: str
        """
        return self._type_id

    @type_id.setter
    def type_id(self, type_id: str):
        """Sets the type_id of this ItemType.

        The identifier of this item type.  # noqa: E501

        :param type_id: The type_id of this ItemType.
        :type type_id: str
        """

        self._type_id = type_id

    @property
    def type_location(self) -> TypeLocation:
        """Gets the type_location of this ItemType.


        :return: The type_location of this ItemType.
        :rtype: TypeLocation
        """
        return self._type_location

    @type_location.setter
    def type_location(self, type_location: TypeLocation):
        """Sets the type_location of this ItemType.


        :param type_location: The type_location of this ItemType.
        :type type_location: TypeLocation
        """

        self._type_location = type_location
