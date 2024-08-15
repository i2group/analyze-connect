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
from spi.models.form_logical_type import FormLogicalType
from spi import util


class DaodRequestCondition(Model):

    def __init__(self, id=None, logical_type=None, value=None):  # noqa: E501
        """DaodRequestCondition

        :param id: The id of this DaodRequestCondition.  # noqa: E501
        :type id: str
        :param logical_type: The logical_type of this DaodRequestCondition.  # noqa: E501
        :type logical_type: FormLogicalType
        :param value: The value of this DaodRequestCondition.  # noqa: E501
        :type value: object
        """
        self.openapi_types = {
            'id': str,
            'logical_type': FormLogicalType,
            'value': object
        }

        self.attribute_map = {
            'id': 'id',
            'logical_type': 'logicalType',
            'value': 'value'
        }

        self._id = id
        self._logical_type = logical_type
        self._value = value

    @classmethod
    def from_dict(cls, dikt) -> 'DaodRequestCondition':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The DaodRequestCondition of this DaodRequestCondition.  # noqa: E501
        :rtype: DaodRequestCondition
        """
        return util.deserialize_model(dikt, cls)

    @property
    def id(self) -> str:
        """Gets the id of this DaodRequestCondition.

        The identifier of the condition, as specified in the client configuration for the service.  # noqa: E501

        :return: The id of this DaodRequestCondition.
        :rtype: str
        """
        return self._id

    @id.setter
    def id(self, id: str):
        """Sets the id of this DaodRequestCondition.

        The identifier of the condition, as specified in the client configuration for the service.  # noqa: E501

        :param id: The id of this DaodRequestCondition.
        :type id: str
        """
        if id is None:
            raise ValueError("Invalid value for `id`, must not be `None`")  # noqa: E501

        self._id = id

    @property
    def logical_type(self) -> FormLogicalType:
        """Gets the logical_type of this DaodRequestCondition.


        :return: The logical_type of this DaodRequestCondition.
        :rtype: FormLogicalType
        """
        return self._logical_type

    @logical_type.setter
    def logical_type(self, logical_type: FormLogicalType):
        """Sets the logical_type of this DaodRequestCondition.


        :param logical_type: The logical_type of this DaodRequestCondition.
        :type logical_type: FormLogicalType
        """
        if logical_type is None:
            raise ValueError("Invalid value for `logical_type`, must not be `None`")  # noqa: E501

        self._logical_type = logical_type

    @property
    def value(self) -> object:
        """Gets the value of this DaodRequestCondition.

        The value that a user supplied for the condition, which must be formatted correctly for its logical type. For the format rules, see [Valid formats for i2 Connect data values](https://i2group.github.io/analyze-connect/content/miscellaneous/data-model.html).  # noqa: E501

        :return: The value of this DaodRequestCondition.
        :rtype: object
        """
        return self._value

    @value.setter
    def value(self, value: object):
        """Sets the value of this DaodRequestCondition.

        The value that a user supplied for the condition, which must be formatted correctly for its logical type. For the format rules, see [Valid formats for i2 Connect data values](https://i2group.github.io/analyze-connect/content/miscellaneous/data-model.html).  # noqa: E501

        :param value: The value of this DaodRequestCondition.
        :type value: object
        """
        if value is None:
            raise ValueError("Invalid value for `value`, must not be `None`")  # noqa: E501

        self._value = value
