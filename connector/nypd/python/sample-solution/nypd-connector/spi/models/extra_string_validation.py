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


class ExtraStringValidation(Model):

    def __init__(self, regex=None, message=None):  # noqa: E501
        """ExtraStringValidation

        :param regex: The regex of this ExtraStringValidation.  # noqa: E501
        :type regex: str
        :param message: The message of this ExtraStringValidation.  # noqa: E501
        :type message: str
        """
        self.openapi_types = {
            'regex': str,
            'message': str
        }

        self.attribute_map = {
            'regex': 'regex',
            'message': 'message'
        }

        self._regex = regex
        self._message = message

    @classmethod
    def from_dict(cls, dikt) -> 'ExtraStringValidation':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The ExtraStringValidation of this ExtraStringValidation.  # noqa: E501
        :rtype: ExtraStringValidation
        """
        return util.deserialize_model(dikt, cls)

    @property
    def regex(self) -> str:
        """Gets the regex of this ExtraStringValidation.

        A regular expression that further constrains a string value in a condition.  # noqa: E501

        :return: The regex of this ExtraStringValidation.
        :rtype: str
        """
        return self._regex

    @regex.setter
    def regex(self, regex: str):
        """Sets the regex of this ExtraStringValidation.

        A regular expression that further constrains a string value in a condition.  # noqa: E501

        :param regex: The regex of this ExtraStringValidation.
        :type regex: str
        """
        if regex is None:
            raise ValueError("Invalid value for `regex`, must not be `None`")  # noqa: E501

        self._regex = regex

    @property
    def message(self) -> str:
        """Gets the message of this ExtraStringValidation.

        A message that describes the constraints to a user.  # noqa: E501

        :return: The message of this ExtraStringValidation.
        :rtype: str
        """
        return self._message

    @message.setter
    def message(self, message: str):
        """Sets the message of this ExtraStringValidation.

        A message that describes the constraints to a user.  # noqa: E501

        :param message: The message of this ExtraStringValidation.
        :type message: str
        """
        if message is None:
            raise ValueError("Invalid value for `message`, must not be `None`")  # noqa: E501

        self._message = message
