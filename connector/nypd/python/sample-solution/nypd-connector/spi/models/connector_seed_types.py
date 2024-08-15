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
from spi.models.connector_seed_item_types import ConnectorSeedItemTypes
from spi.models.connector_seed_semantic_property_types import ConnectorSeedSemanticPropertyTypes
from spi import util


class ConnectorSeedTypes(Model):

    def __init__(self, allowed_types=None, item_types=None, semantic_property_types=None):  # noqa: E501
        """ConnectorSeedTypes

        :param allowed_types: The allowed_types of this ConnectorSeedTypes.  # noqa: E501
        :type allowed_types: str
        :param item_types: The item_types of this ConnectorSeedTypes.  # noqa: E501
        :type item_types: List[ConnectorSeedConstraintItemType]
        :param semantic_property_types: The semantic_property_types of this ConnectorSeedTypes.  # noqa: E501
        :type semantic_property_types: Dict[str, list[str]]
        """
        self.openapi_types = {
            'allowed_types': str,
            'item_types': List[ConnectorSeedConstraintItemType],
            'semantic_property_types': Dict[str, list[str]]
        }

        self.attribute_map = {
            'allowed_types': 'allowedTypes',
            'item_types': 'itemTypes',
            'semantic_property_types': 'semanticPropertyTypes'
        }

        self._allowed_types = allowed_types
        self._item_types = item_types
        self._semantic_property_types = semantic_property_types

    @classmethod
    def from_dict(cls, dikt) -> 'ConnectorSeedTypes':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The ConnectorSeedTypes of this ConnectorSeedTypes.  # noqa: E501
        :rtype: ConnectorSeedTypes
        """
        return util.deserialize_model(dikt, cls)

    @property
    def allowed_types(self) -> str:
        """Gets the allowed_types of this ConnectorSeedTypes.

        The subset of item types by which a seed record can be constrained.  # noqa: E501

        :return: The allowed_types of this ConnectorSeedTypes.
        :rtype: str
        """
        return self._allowed_types

    @allowed_types.setter
    def allowed_types(self, allowed_types: str):
        """Sets the allowed_types of this ConnectorSeedTypes.

        The subset of item types by which a seed record can be constrained.  # noqa: E501

        :param allowed_types: The allowed_types of this ConnectorSeedTypes.
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
        """Gets the item_types of this ConnectorSeedTypes.

        The item type constraints, which are limited to the subset in **allowedTypes**.  # noqa: E501

        :return: The item_types of this ConnectorSeedTypes.
        :rtype: List[ConnectorSeedConstraintItemType]
        """
        return self._item_types

    @item_types.setter
    def item_types(self, item_types: List[ConnectorSeedConstraintItemType]):
        """Sets the item_types of this ConnectorSeedTypes.

        The item type constraints, which are limited to the subset in **allowedTypes**.  # noqa: E501

        :param item_types: The item_types of this ConnectorSeedTypes.
        :type item_types: List[ConnectorSeedConstraintItemType]
        """

        self._item_types = item_types

    @property
    def semantic_property_types(self) -> Dict[str, list[str]]:
        """Gets the semantic_property_types of this ConnectorSeedTypes.

        The identifiers of the semantic property types, in a map with the following structure: **{\"_LABEL_\": [\"_SEMANTICTYPEID1_\", \"_SEMANTICTYPEID2_\", ...], ...}**.  Here, the _LABEL_ is a key for an ANDed group of semantic property type identifiers. Multiple groups are treated as ORed constraints on seed records.  # noqa: E501

        :return: The semantic_property_types of this ConnectorSeedTypes.
        :rtype: Dict[str, list[str]]
        """
        return self._semantic_property_types

    @semantic_property_types.setter
    def semantic_property_types(self, semantic_property_types: Dict[str, list[str]]):
        """Sets the semantic_property_types of this ConnectorSeedTypes.

        The identifiers of the semantic property types, in a map with the following structure: **{\"_LABEL_\": [\"_SEMANTICTYPEID1_\", \"_SEMANTICTYPEID2_\", ...], ...}**.  Here, the _LABEL_ is a key for an ANDed group of semantic property type identifiers. Multiple groups are treated as ORed constraints on seed records.  # noqa: E501

        :param semantic_property_types: The semantic_property_types of this ConnectorSeedTypes.
        :type semantic_property_types: Dict[str, list[str]]
        """

        self._semantic_property_types = semantic_property_types
