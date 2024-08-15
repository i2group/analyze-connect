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


class SourceIdentifier(Model):

    def __init__(self, key=None, type=None):  # noqa: E501
        """SourceIdentifier

        :param key: The key of this SourceIdentifier.  # noqa: E501
        :type key: List[str]
        :param type: The type of this SourceIdentifier.  # noqa: E501
        :type type: str
        """
        self.openapi_types = {
            'key': List[str],
            'type': str
        }

        self.attribute_map = {
            'key': 'key',
            'type': 'type'
        }

        self._key = key
        self._type = type

    @classmethod
    def from_dict(cls, dikt) -> 'SourceIdentifier':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The SourceIdentifier of this SourceIdentifier.  # noqa: E501
        :rtype: SourceIdentifier
        """
        return util.deserialize_model(dikt, cls)

    @property
    def key(self) -> List[str]:
        """Gets the key of this SourceIdentifier.

        Values that together identify some data in its original source.  # noqa: E501

        :return: The key of this SourceIdentifier.
        :rtype: List[str]
        """
        return self._key

    @key.setter
    def key(self, key: List[str]):
        """Sets the key of this SourceIdentifier.

        Values that together identify some data in its original source.  # noqa: E501

        :param key: The key of this SourceIdentifier.
        :type key: List[str]
        """
        if key is None:
            raise ValueError("Invalid value for `key`, must not be `None`")  # noqa: E501

        self._key = key

    @property
    def type(self) -> str:
        """Gets the type of this SourceIdentifier.

        The type of this source identifier.  # noqa: E501

        :return: The type of this SourceIdentifier.
        :rtype: str
        """
        return self._type

    @type.setter
    def type(self, type: str):
        """Sets the type of this SourceIdentifier.

        The type of this source identifier.  # noqa: E501

        :param type: The type of this SourceIdentifier.
        :type type: str
        """
        if type is None:
            raise ValueError("Invalid value for `type`, must not be `None`")  # noqa: E501

        self._type = type
