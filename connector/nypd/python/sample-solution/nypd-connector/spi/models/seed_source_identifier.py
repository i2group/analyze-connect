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


class SeedSourceIdentifier(Model):

    def __init__(self, item_type_id=None, key=None, type=None):  # noqa: E501
        """SeedSourceIdentifier

        :param item_type_id: The item_type_id of this SeedSourceIdentifier.  # noqa: E501
        :type item_type_id: str
        :param key: The key of this SeedSourceIdentifier.  # noqa: E501
        :type key: List[str]
        :param type: The type of this SeedSourceIdentifier.  # noqa: E501
        :type type: str
        """
        self.openapi_types = {
            'item_type_id': str,
            'key': List[str],
            'type': str
        }

        self.attribute_map = {
            'item_type_id': 'itemTypeId',
            'key': 'key',
            'type': 'type'
        }

        self._item_type_id = item_type_id
        self._key = key
        self._type = type

    @classmethod
    def from_dict(cls, dikt) -> 'SeedSourceIdentifier':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The SeedSourceIdentifier of this SeedSourceIdentifier.  # noqa: E501
        :rtype: SeedSourceIdentifier
        """
        return util.deserialize_model(dikt, cls)

    @property
    def item_type_id(self) -> str:
        """Gets the item_type_id of this SeedSourceIdentifier.

        The identifier of the item type of the record that has this source identifier.  **Deprecated:** This field will be removed in a future release. Use the owning record's **typeId** instead. Do not rely on the value of this field if it is set.  # noqa: E501

        :return: The item_type_id of this SeedSourceIdentifier.
        :rtype: str
        """
        return self._item_type_id

    @item_type_id.setter
    def item_type_id(self, item_type_id: str):
        """Sets the item_type_id of this SeedSourceIdentifier.

        The identifier of the item type of the record that has this source identifier.  **Deprecated:** This field will be removed in a future release. Use the owning record's **typeId** instead. Do not rely on the value of this field if it is set.  # noqa: E501

        :param item_type_id: The item_type_id of this SeedSourceIdentifier.
        :type item_type_id: str
        """

        self._item_type_id = item_type_id

    @property
    def key(self) -> List[str]:
        """Gets the key of this SeedSourceIdentifier.

        Values that together identify some data in its original source.  # noqa: E501

        :return: The key of this SeedSourceIdentifier.
        :rtype: List[str]
        """
        return self._key

    @key.setter
    def key(self, key: List[str]):
        """Sets the key of this SeedSourceIdentifier.

        Values that together identify some data in its original source.  # noqa: E501

        :param key: The key of this SeedSourceIdentifier.
        :type key: List[str]
        """
        if key is None:
            raise ValueError("Invalid value for `key`, must not be `None`")  # noqa: E501

        self._key = key

    @property
    def type(self) -> str:
        """Gets the type of this SeedSourceIdentifier.

        The type of this source identifier.  # noqa: E501

        :return: The type of this SeedSourceIdentifier.
        :rtype: str
        """
        return self._type

    @type.setter
    def type(self, type: str):
        """Sets the type of this SeedSourceIdentifier.

        The type of this source identifier.  # noqa: E501

        :param type: The type of this SeedSourceIdentifier.
        :type type: str
        """
        if type is None:
            raise ValueError("Invalid value for `type`, must not be `None`")  # noqa: E501

        self._type = type
