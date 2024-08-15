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
from spi.models.connector_seed_constraint_item_type import ConnectorSeedConstraintItemType
from spi import util


class ConnectorSeedItemTypes(Model):

    def __init__(self, allowed_types=None, item_types=None):  # noqa: E501
        """ConnectorSeedItemTypes

        :param allowed_types: The allowed_types of this ConnectorSeedItemTypes.  # noqa: E501
        :type allowed_types: str
        :param item_types: The item_types of this ConnectorSeedItemTypes.  # noqa: E501
        :type item_types: List[ConnectorSeedConstraintItemType]
        """
        self.openapi_types = {
            'allowed_types': str,
            'item_types': List[ConnectorSeedConstraintItemType]
        }

        self.attribute_map = {
            'allowed_types': 'allowedTypes',
            'item_types': 'itemTypes'
        }

        self._allowed_types = allowed_types
        self._item_types = item_types

    @classmethod
    def from_dict(cls, dikt) -> 'ConnectorSeedItemTypes':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The ConnectorSeedItemTypes of this ConnectorSeedItemTypes.  # noqa: E501
        :rtype: ConnectorSeedItemTypes
        """
        return util.deserialize_model(dikt, cls)

    @property
    def allowed_types(self) -> str:
        """Gets the allowed_types of this ConnectorSeedItemTypes.

        The subset of item types by which a seed record can be constrained.  # noqa: E501

        :return: The allowed_types of this ConnectorSeedItemTypes.
        :rtype: str
        """
        return self._allowed_types

    @allowed_types.setter
    def allowed_types(self, allowed_types: str):
        """Sets the allowed_types of this ConnectorSeedItemTypes.

        The subset of item types by which a seed record can be constrained.  # noqa: E501

        :param allowed_types: The allowed_types of this ConnectorSeedItemTypes.
        :type allowed_types: str
        """
        allowed_values = ["ENTITY", "LINK"]  # noqa: E501
        if allowed_types not in allowed_values:
            raise ValueError(
                "Invalid value for `allowed_types` ({0}), must be one of {1}"
                .format(allowed_types, allowed_values)
            )

        self._allowed_types = allowed_types

    @property
    def item_types(self) -> List[ConnectorSeedConstraintItemType]:
        """Gets the item_types of this ConnectorSeedItemTypes.

        The item type constraints, which are limited to the subset in **allowedTypes**.  # noqa: E501

        :return: The item_types of this ConnectorSeedItemTypes.
        :rtype: List[ConnectorSeedConstraintItemType]
        """
        return self._item_types

    @item_types.setter
    def item_types(self, item_types: List[ConnectorSeedConstraintItemType]):
        """Sets the item_types of this ConnectorSeedItemTypes.

        The item type constraints, which are limited to the subset in **allowedTypes**.  # noqa: E501

        :param item_types: The item_types of this ConnectorSeedItemTypes.
        :type item_types: List[ConnectorSeedConstraintItemType]
        """

        self._item_types = item_types
