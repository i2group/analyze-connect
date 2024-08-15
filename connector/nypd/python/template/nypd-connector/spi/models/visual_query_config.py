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
from spi.models.visual_query_constraints import VisualQueryConstraints
from spi.models.visual_query_operator_usage_rule import VisualQueryOperatorUsageRule
from spi.models.visual_query_palette_item_types import VisualQueryPaletteItemTypes
from spi import util


class VisualQueryConfig(Model):

    def __init__(self, operator_usage_rules=None, constraints=None, palette_item_types=None):  # noqa: E501
        """VisualQueryConfig

        :param operator_usage_rules: The operator_usage_rules of this VisualQueryConfig.  # noqa: E501
        :type operator_usage_rules: List[VisualQueryOperatorUsageRule]
        :param constraints: The constraints of this VisualQueryConfig.  # noqa: E501
        :type constraints: VisualQueryConstraints
        :param palette_item_types: The palette_item_types of this VisualQueryConfig.  # noqa: E501
        :type palette_item_types: VisualQueryPaletteItemTypes
        """
        self.openapi_types = {
            'operator_usage_rules': List[VisualQueryOperatorUsageRule],
            'constraints': VisualQueryConstraints,
            'palette_item_types': VisualQueryPaletteItemTypes
        }

        self.attribute_map = {
            'operator_usage_rules': 'operatorUsageRules',
            'constraints': 'constraints',
            'palette_item_types': 'paletteItemTypes'
        }

        self._operator_usage_rules = operator_usage_rules
        self._constraints = constraints
        self._palette_item_types = palette_item_types

    @classmethod
    def from_dict(cls, dikt) -> 'VisualQueryConfig':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The VisualQueryConfig of this VisualQueryConfig.  # noqa: E501
        :rtype: VisualQueryConfig
        """
        return util.deserialize_model(dikt, cls)

    @property
    def operator_usage_rules(self) -> List[VisualQueryOperatorUsageRule]:
        """Gets the operator_usage_rules of this VisualQueryConfig.

        The rules that govern the property types and operators that can appear in Visual Query conditions.  # noqa: E501

        :return: The operator_usage_rules of this VisualQueryConfig.
        :rtype: List[VisualQueryOperatorUsageRule]
        """
        return self._operator_usage_rules

    @operator_usage_rules.setter
    def operator_usage_rules(self, operator_usage_rules: List[VisualQueryOperatorUsageRule]):
        """Sets the operator_usage_rules of this VisualQueryConfig.

        The rules that govern the property types and operators that can appear in Visual Query conditions.  # noqa: E501

        :param operator_usage_rules: The operator_usage_rules of this VisualQueryConfig.
        :type operator_usage_rules: List[VisualQueryOperatorUsageRule]
        """

        self._operator_usage_rules = operator_usage_rules

    @property
    def constraints(self) -> VisualQueryConstraints:
        """Gets the constraints of this VisualQueryConfig.


        :return: The constraints of this VisualQueryConfig.
        :rtype: VisualQueryConstraints
        """
        return self._constraints

    @constraints.setter
    def constraints(self, constraints: VisualQueryConstraints):
        """Sets the constraints of this VisualQueryConfig.


        :param constraints: The constraints of this VisualQueryConfig.
        :type constraints: VisualQueryConstraints
        """

        self._constraints = constraints

    @property
    def palette_item_types(self) -> VisualQueryPaletteItemTypes:
        """Gets the palette_item_types of this VisualQueryConfig.


        :return: The palette_item_types of this VisualQueryConfig.
        :rtype: VisualQueryPaletteItemTypes
        """
        return self._palette_item_types

    @palette_item_types.setter
    def palette_item_types(self, palette_item_types: VisualQueryPaletteItemTypes):
        """Sets the palette_item_types of this VisualQueryConfig.


        :param palette_item_types: The palette_item_types of this VisualQueryConfig.
        :type palette_item_types: VisualQueryPaletteItemTypes
        """

        self._palette_item_types = palette_item_types
