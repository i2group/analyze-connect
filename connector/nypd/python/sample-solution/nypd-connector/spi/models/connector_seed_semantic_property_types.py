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


class ConnectorSeedSemanticPropertyTypes(Model):

    def __init__(self, semantic_property_types=None):  # noqa: E501
        """ConnectorSeedSemanticPropertyTypes

        :param semantic_property_types: The semantic_property_types of this ConnectorSeedSemanticPropertyTypes.  # noqa: E501
        :type semantic_property_types: Dict[str, list[str]]
        """
        self.openapi_types = {
            'semantic_property_types': Dict[str, list[str]]
        }

        self.attribute_map = {
            'semantic_property_types': 'semanticPropertyTypes'
        }

        self._semantic_property_types = semantic_property_types

    @classmethod
    def from_dict(cls, dikt) -> 'ConnectorSeedSemanticPropertyTypes':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The ConnectorSeedSemanticPropertyTypes of this ConnectorSeedSemanticPropertyTypes.  # noqa: E501
        :rtype: ConnectorSeedSemanticPropertyTypes
        """
        return util.deserialize_model(dikt, cls)

    @property
    def semantic_property_types(self) -> Dict[str, list[str]]:
        """Gets the semantic_property_types of this ConnectorSeedSemanticPropertyTypes.

        The identifiers of the semantic property types, in a map with the following structure: **{\"_LABEL_\": [\"_SEMANTICTYPEID1_\", \"_SEMANTICTYPEID2_\", ...], ...}**.  Here, the _LABEL_ is a key for an ANDed group of semantic property type identifiers. Multiple groups are treated as ORed constraints on seed records.  # noqa: E501

        :return: The semantic_property_types of this ConnectorSeedSemanticPropertyTypes.
        :rtype: Dict[str, list[str]]
        """
        return self._semantic_property_types

    @semantic_property_types.setter
    def semantic_property_types(self, semantic_property_types: Dict[str, list[str]]):
        """Sets the semantic_property_types of this ConnectorSeedSemanticPropertyTypes.

        The identifiers of the semantic property types, in a map with the following structure: **{\"_LABEL_\": [\"_SEMANTICTYPEID1_\", \"_SEMANTICTYPEID2_\", ...], ...}**.  Here, the _LABEL_ is a key for an ANDed group of semantic property type identifiers. Multiple groups are treated as ORed constraints on seed records.  # noqa: E501

        :param semantic_property_types: The semantic_property_types of this ConnectorSeedSemanticPropertyTypes.
        :type semantic_property_types: Dict[str, list[str]]
        """

        self._semantic_property_types = semantic_property_types
