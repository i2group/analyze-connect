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


class SemanticProperty(Model):

    def __init__(self, logical_type=None, value=None, source_semantic_type_id=None):  # noqa: E501
        """SemanticProperty

        :param logical_type: The logical_type of this SemanticProperty.  # noqa: E501
        :type logical_type: LogicalType
        :param value: The value of this SemanticProperty.  # noqa: E501
        :type value: object
        :param source_semantic_type_id: The source_semantic_type_id of this SemanticProperty.  # noqa: E501
        :type source_semantic_type_id: str
        """
        self.openapi_types = {
            'logical_type': LogicalType,
            'value': object,
            'source_semantic_type_id': str
        }

        self.attribute_map = {
            'logical_type': 'logicalType',
            'value': 'value',
            'source_semantic_type_id': 'sourceSemanticTypeId'
        }

        self._logical_type = logical_type
        self._value = value
        self._source_semantic_type_id = source_semantic_type_id

    @classmethod
    def from_dict(cls, dikt) -> 'SemanticProperty':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The SemanticProperty of this SemanticProperty.  # noqa: E501
        :rtype: SemanticProperty
        """
        return util.deserialize_model(dikt, cls)

    @property
    def logical_type(self) -> LogicalType:
        """Gets the logical_type of this SemanticProperty.


        :return: The logical_type of this SemanticProperty.
        :rtype: LogicalType
        """
        return self._logical_type

    @logical_type.setter
    def logical_type(self, logical_type: LogicalType):
        """Sets the logical_type of this SemanticProperty.


        :param logical_type: The logical_type of this SemanticProperty.
        :type logical_type: LogicalType
        """
        if logical_type is None:
            raise ValueError("Invalid value for `logical_type`, must not be `None`")  # noqa: E501

        self._logical_type = logical_type

    @property
    def value(self) -> object:
        """Gets the value of this SemanticProperty.

        The property value.  # noqa: E501

        :return: The value of this SemanticProperty.
        :rtype: object
        """
        return self._value

    @value.setter
    def value(self, value: object):
        """Sets the value of this SemanticProperty.

        The property value.  # noqa: E501

        :param value: The value of this SemanticProperty.
        :type value: object
        """
        if value is None:
            raise ValueError("Invalid value for `value`, must not be `None`")  # noqa: E501

        self._value = value

    @property
    def source_semantic_type_id(self) -> str:
        """Gets the source_semantic_type_id of this SemanticProperty.

        The identifier of the semantic type in the seed record, which might be a descendant of the type in the constraint.  # noqa: E501

        :return: The source_semantic_type_id of this SemanticProperty.
        :rtype: str
        """
        return self._source_semantic_type_id

    @source_semantic_type_id.setter
    def source_semantic_type_id(self, source_semantic_type_id: str):
        """Sets the source_semantic_type_id of this SemanticProperty.

        The identifier of the semantic type in the seed record, which might be a descendant of the type in the constraint.  # noqa: E501

        :param source_semantic_type_id: The source_semantic_type_id of this SemanticProperty.
        :type source_semantic_type_id: str
        """
        if source_semantic_type_id is None:
            raise ValueError("Invalid value for `source_semantic_type_id`, must not be `None`")  # noqa: E501

        self._source_semantic_type_id = source_semantic_type_id
