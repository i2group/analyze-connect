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


class AuthResponse(Model):

    def __init__(self, token=None):  # noqa: E501
        """AuthResponse

        :param token: The token of this AuthResponse.  # noqa: E501
        :type token: str
        """
        self.openapi_types = {
            'token': str
        }

        self.attribute_map = {
            'token': 'token'
        }

        self._token = token

    @classmethod
    def from_dict(cls, dikt) -> 'AuthResponse':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The AuthResponse of this AuthResponse.  # noqa: E501
        :rtype: AuthResponse
        """
        return util.deserialize_model(dikt, cls)

    @property
    def token(self) -> str:
        """Gets the token of this AuthResponse.

        A token that can authenticate the caller in future requests to any service that uses the same authentication config.  # noqa: E501

        :return: The token of this AuthResponse.
        :rtype: str
        """
        return self._token

    @token.setter
    def token(self, token: str):
        """Sets the token of this AuthResponse.

        A token that can authenticate the caller in future requests to any service that uses the same authentication config.  # noqa: E501

        :param token: The token of this AuthResponse.
        :type token: str
        """
        if token is None:
            raise ValueError("Invalid value for `token`, must not be `None`")  # noqa: E501

        self._token = token
