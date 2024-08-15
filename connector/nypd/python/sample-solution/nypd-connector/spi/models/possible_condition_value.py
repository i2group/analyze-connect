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
from spi import util


class PossibleConditionValue(Model):

    def __init__(self, value=None, display_name=None):  # noqa: E501
        """PossibleConditionValue

        :param value: The value of this PossibleConditionValue.  # noqa: E501
        :type value: str
        :param display_name: The display_name of this PossibleConditionValue.  # noqa: E501
        :type display_name: str
        """
        self.openapi_types = {
            'value': str,
            'display_name': str
        }

        self.attribute_map = {
            'value': 'value',
            'display_name': 'displayName'
        }

        self._value = value
        self._display_name = display_name

    @classmethod
    def from_dict(cls, dikt) -> 'PossibleConditionValue':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The PossibleConditionValue of this PossibleConditionValue.  # noqa: E501
        :rtype: PossibleConditionValue
        """
        return util.deserialize_model(dikt, cls)

    @property
    def value(self) -> str:
        """Gets the value of this PossibleConditionValue.

        A value for a `SELECTED_FROM` or `SUGGESTED_FROM` condition.  # noqa: E501

        :return: The value of this PossibleConditionValue.
        :rtype: str
        """
        return self._value

    @value.setter
    def value(self, value: str):
        """Sets the value of this PossibleConditionValue.

        A value for a `SELECTED_FROM` or `SUGGESTED_FROM` condition.  # noqa: E501

        :param value: The value of this PossibleConditionValue.
        :type value: str
        """
        if value is None:
            raise ValueError("Invalid value for `value`, must not be `None`")  # noqa: E501

        self._value = value

    @property
    def display_name(self) -> str:
        """Gets the display_name of this PossibleConditionValue.

        The display name of the value, which might be displayed to users in place of the value itself.  # noqa: E501

        :return: The display_name of this PossibleConditionValue.
        :rtype: str
        """
        return self._display_name

    @display_name.setter
    def display_name(self, display_name: str):
        """Sets the display_name of this PossibleConditionValue.

        The display name of the value, which might be displayed to users in place of the value itself.  # noqa: E501

        :param display_name: The display_name of this PossibleConditionValue.
        :type display_name: str
        """

        self._display_name = display_name
