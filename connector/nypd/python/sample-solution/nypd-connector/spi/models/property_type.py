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
from spi.models.logical_type import LogicalType
from spi import util


class PropertyType(Model):

    def __init__(self, display_name=None, logical_type=None, semantic_type_id=None):  # noqa: E501
        """PropertyType

        :param display_name: The display_name of this PropertyType.  # noqa: E501
        :type display_name: str
        :param logical_type: The logical_type of this PropertyType.  # noqa: E501
        :type logical_type: LogicalType
        :param semantic_type_id: The semantic_type_id of this PropertyType.  # noqa: E501
        :type semantic_type_id: str
        """
        self.openapi_types = {
            'display_name': str,
            'logical_type': LogicalType,
            'semantic_type_id': str
        }

        self.attribute_map = {
            'display_name': 'displayName',
            'logical_type': 'logicalType',
            'semantic_type_id': 'semanticTypeId'
        }

        self._display_name = display_name
        self._logical_type = logical_type
        self._semantic_type_id = semantic_type_id

    @classmethod
    def from_dict(cls, dikt) -> 'PropertyType':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The PropertyType of this PropertyType.  # noqa: E501
        :rtype: PropertyType
        """
        return util.deserialize_model(dikt, cls)

    @property
    def display_name(self) -> str:
        """Gets the display_name of this PropertyType.

        The display name for this property type.  # noqa: E501

        :return: The display_name of this PropertyType.
        :rtype: str
        """
        return self._display_name

    @display_name.setter
    def display_name(self, display_name: str):
        """Sets the display_name of this PropertyType.

        The display name for this property type.  # noqa: E501

        :param display_name: The display_name of this PropertyType.
        :type display_name: str
        """

        self._display_name = display_name

    @property
    def logical_type(self) -> LogicalType:
        """Gets the logical_type of this PropertyType.


        :return: The logical_type of this PropertyType.
        :rtype: LogicalType
        """
        return self._logical_type

    @logical_type.setter
    def logical_type(self, logical_type: LogicalType):
        """Sets the logical_type of this PropertyType.


        :param logical_type: The logical_type of this PropertyType.
        :type logical_type: LogicalType
        """

        self._logical_type = logical_type

    @property
    def semantic_type_id(self) -> str:
        """Gets the semantic_type_id of this PropertyType.

        The identifier of the semantic type for this property type.  # noqa: E501

        :return: The semantic_type_id of this PropertyType.
        :rtype: str
        """
        return self._semantic_type_id

    @semantic_type_id.setter
    def semantic_type_id(self, semantic_type_id: str):
        """Sets the semantic_type_id of this PropertyType.

        The identifier of the semantic type for this property type.  # noqa: E501

        :param semantic_type_id: The semantic_type_id of this PropertyType.
        :type semantic_type_id: str
        """

        self._semantic_type_id = semantic_type_id
