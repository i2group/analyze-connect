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
from spi.models.daod_seed_entity_data import DaodSeedEntityData
from spi.models.daod_seed_link_data import DaodSeedLinkData
from spi.models.item_type import ItemType
from spi.models.semantic_seed import SemanticSeed
from spi import util


class DaodSeeds(Model):

    def __init__(self, all_item_types=None, entities=None, item_types=None, links=None, semantic_data=None):  # noqa: E501
        """DaodSeeds

        :param all_item_types: The all_item_types of this DaodSeeds.  # noqa: E501
        :type all_item_types: List[ItemType]
        :param entities: The entities of this DaodSeeds.  # noqa: E501
        :type entities: List[DaodSeedEntityData]
        :param item_types: The item_types of this DaodSeeds.  # noqa: E501
        :type item_types: Dict[str, ItemType]
        :param links: The links of this DaodSeeds.  # noqa: E501
        :type links: List[DaodSeedLinkData]
        :param semantic_data: The semantic_data of this DaodSeeds.  # noqa: E501
        :type semantic_data: Dict[str, List[SemanticSeed]]
        """
        self.openapi_types = {
            'all_item_types': List[ItemType],
            'entities': List[DaodSeedEntityData],
            'item_types': Dict[str, ItemType],
            'links': List[DaodSeedLinkData],
            'semantic_data': Dict[str, List[SemanticSeed]]
        }

        self.attribute_map = {
            'all_item_types': 'allItemTypes',
            'entities': 'entities',
            'item_types': 'itemTypes',
            'links': 'links',
            'semantic_data': 'semanticData'
        }

        self._all_item_types = all_item_types
        self._entities = entities
        self._item_types = item_types
        self._links = links
        self._semantic_data = semantic_data

    @classmethod
    def from_dict(cls, dikt) -> 'DaodSeeds':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The DaodSeeds of this DaodSeeds.  # noqa: E501
        :rtype: DaodSeeds
        """
        return util.deserialize_model(dikt, cls)

    @property
    def all_item_types(self) -> List[ItemType]:
        """Gets the all_item_types of this DaodSeeds.

        Schema information for the item types of the records that contributed to the data in **entities** and **links**, which might come from different schemas.  # noqa: E501

        :return: The all_item_types of this DaodSeeds.
        :rtype: List[ItemType]
        """
        return self._all_item_types

    @all_item_types.setter
    def all_item_types(self, all_item_types: List[ItemType]):
        """Sets the all_item_types of this DaodSeeds.

        Schema information for the item types of the records that contributed to the data in **entities** and **links**, which might come from different schemas.  # noqa: E501

        :param all_item_types: The all_item_types of this DaodSeeds.
        :type all_item_types: List[ItemType]
        """

        self._all_item_types = all_item_types

    @property
    def entities(self) -> List[DaodSeedEntityData]:
        """Gets the entities of this DaodSeeds.

        Data from the entity records that were specified as seeds for the request.  # noqa: E501

        :return: The entities of this DaodSeeds.
        :rtype: List[DaodSeedEntityData]
        """
        return self._entities

    @entities.setter
    def entities(self, entities: List[DaodSeedEntityData]):
        """Sets the entities of this DaodSeeds.

        Data from the entity records that were specified as seeds for the request.  # noqa: E501

        :param entities: The entities of this DaodSeeds.
        :type entities: List[DaodSeedEntityData]
        """

        self._entities = entities

    @property
    def item_types(self) -> Dict[str, ItemType]:
        """Gets the item_types of this DaodSeeds.

        Schema information for the item types of the records that contributed to the data in **entities** and **links**, keyed by item type identifier.  **Deprecated:** This field will be removed in a future release. Use the contents of **allItemTypes** instead of this field. Do not rely on the value of this field if it is set.  # noqa: E501

        :return: The item_types of this DaodSeeds.
        :rtype: Dict[str, ItemType]
        """
        return self._item_types

    @item_types.setter
    def item_types(self, item_types: Dict[str, ItemType]):
        """Sets the item_types of this DaodSeeds.

        Schema information for the item types of the records that contributed to the data in **entities** and **links**, keyed by item type identifier.  **Deprecated:** This field will be removed in a future release. Use the contents of **allItemTypes** instead of this field. Do not rely on the value of this field if it is set.  # noqa: E501

        :param item_types: The item_types of this DaodSeeds.
        :type item_types: Dict[str, ItemType]
        """

        self._item_types = item_types

    @property
    def links(self) -> List[DaodSeedLinkData]:
        """Gets the links of this DaodSeeds.

        Data from the link records that were specified as seeds for the request.  # noqa: E501

        :return: The links of this DaodSeeds.
        :rtype: List[DaodSeedLinkData]
        """
        return self._links

    @links.setter
    def links(self, links: List[DaodSeedLinkData]):
        """Sets the links of this DaodSeeds.

        Data from the link records that were specified as seeds for the request.  # noqa: E501

        :param links: The links of this DaodSeeds.
        :type links: List[DaodSeedLinkData]
        """

        self._links = links

    @property
    def semantic_data(self) -> Dict[str, List[SemanticSeed]]:
        """Gets the semantic_data of this DaodSeeds.

        Data from the semantic property types that were specified as seeds for the request.  # noqa: E501

        :return: The semantic_data of this DaodSeeds.
        :rtype: Dict[str, List[SemanticSeed]]
        """
        return self._semantic_data

    @semantic_data.setter
    def semantic_data(self, semantic_data: Dict[str, List[SemanticSeed]]):
        """Sets the semantic_data of this DaodSeeds.

        Data from the semantic property types that were specified as seeds for the request.  # noqa: E501

        :param semantic_data: The semantic_data of this DaodSeeds.
        :type semantic_data: Dict[str, List[SemanticSeed]]
        """

        self._semantic_data = semantic_data
