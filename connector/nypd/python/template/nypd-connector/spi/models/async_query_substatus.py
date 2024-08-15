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


class AsyncQuerySubstatus(Model):

    def __init__(self, message=None, type=None):  # noqa: E501
        """AsyncQuerySubstatus

        :param message: The message of this AsyncQuerySubstatus.  # noqa: E501
        :type message: str
        :param type: The type of this AsyncQuerySubstatus.  # noqa: E501
        :type type: str
        """
        self.openapi_types = {
            'message': str,
            'type': str
        }

        self.attribute_map = {
            'message': 'message',
            'type': 'type'
        }

        self._message = message
        self._type = type

    @classmethod
    def from_dict(cls, dikt) -> 'AsyncQuerySubstatus':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The AsyncQuerySubstatus of this AsyncQuerySubstatus.  # noqa: E501
        :rtype: AsyncQuerySubstatus
        """
        return util.deserialize_model(dikt, cls)

    @property
    def message(self) -> str:
        """Gets the message of this AsyncQuerySubstatus.

        A progress message.  # noqa: E501

        :return: The message of this AsyncQuerySubstatus.
        :rtype: str
        """
        return self._message

    @message.setter
    def message(self, message: str):
        """Sets the message of this AsyncQuerySubstatus.

        A progress message.  # noqa: E501

        :param message: The message of this AsyncQuerySubstatus.
        :type message: str
        """
        if message is None:
            raise ValueError("Invalid value for `message`, must not be `None`")  # noqa: E501

        self._message = message

    @property
    def type(self) -> str:
        """Gets the type of this AsyncQuerySubstatus.

        The type of the message.  # noqa: E501

        :return: The type of this AsyncQuerySubstatus.
        :rtype: str
        """
        return self._type

    @type.setter
    def type(self, type: str):
        """Sets the type of this AsyncQuerySubstatus.

        The type of the message.  # noqa: E501

        :param type: The type of this AsyncQuerySubstatus.
        :type type: str
        """
        allowed_values = ["INFORMATION", "WARNING", "ERROR", "SUCCESS"]  # noqa: E501
        if type not in allowed_values:
            raise ValueError(
                "Invalid value for `type` ({0}), must be one of {1}"
                .format(type, allowed_values)
            )

        self._type = type
