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


class PayloadValidationResponse(Model):

    def __init__(self, error_message=None):  # noqa: E501
        """PayloadValidationResponse

        :param error_message: The error_message of this PayloadValidationResponse.  # noqa: E501
        :type error_message: str
        """
        self.openapi_types = {
            'error_message': str
        }

        self.attribute_map = {
            'error_message': 'errorMessage'
        }

        self._error_message = error_message

    @classmethod
    def from_dict(cls, dikt) -> 'PayloadValidationResponse':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The PayloadValidationResponse of this PayloadValidationResponse.  # noqa: E501
        :rtype: PayloadValidationResponse
        """
        return util.deserialize_model(dikt, cls)

    @property
    def error_message(self) -> str:
        """Gets the error_message of this PayloadValidationResponse.

        An error message that might be displayed to users.  # noqa: E501

        :return: The error_message of this PayloadValidationResponse.
        :rtype: str
        """
        return self._error_message

    @error_message.setter
    def error_message(self, error_message: str):
        """Sets the error_message of this PayloadValidationResponse.

        An error message that might be displayed to users.  # noqa: E501

        :param error_message: The error_message of this PayloadValidationResponse.
        :type error_message: str
        """

        self._error_message = error_message
