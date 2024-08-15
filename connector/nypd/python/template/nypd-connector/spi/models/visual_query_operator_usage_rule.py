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
from spi.models.visual_query_condition_aspect import VisualQueryConditionAspect
from spi.models.visual_query_condition_operator import VisualQueryConditionOperator
from spi import util


class VisualQueryOperatorUsageRule(Model):

    def __init__(self, rule_type=None, item_type_id=None, property_type_ids=None, operators=None, aspects=None):  # noqa: E501
        """VisualQueryOperatorUsageRule

        :param rule_type: The rule_type of this VisualQueryOperatorUsageRule.  # noqa: E501
        :type rule_type: str
        :param item_type_id: The item_type_id of this VisualQueryOperatorUsageRule.  # noqa: E501
        :type item_type_id: str
        :param property_type_ids: The property_type_ids of this VisualQueryOperatorUsageRule.  # noqa: E501
        :type property_type_ids: List[str]
        :param operators: The operators of this VisualQueryOperatorUsageRule.  # noqa: E501
        :type operators: List[VisualQueryConditionOperator]
        :param aspects: The aspects of this VisualQueryOperatorUsageRule.  # noqa: E501
        :type aspects: List[VisualQueryConditionAspect]
        """
        self.openapi_types = {
            'rule_type': str,
            'item_type_id': str,
            'property_type_ids': List[str],
            'operators': List[VisualQueryConditionOperator],
            'aspects': List[VisualQueryConditionAspect]
        }

        self.attribute_map = {
            'rule_type': 'ruleType',
            'item_type_id': 'itemTypeId',
            'property_type_ids': 'propertyTypeIds',
            'operators': 'operators',
            'aspects': 'aspects'
        }

        self._rule_type = rule_type
        self._item_type_id = item_type_id
        self._property_type_ids = property_type_ids
        self._operators = operators
        self._aspects = aspects

    @classmethod
    def from_dict(cls, dikt) -> 'VisualQueryOperatorUsageRule':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The VisualQueryOperatorUsageRule of this VisualQueryOperatorUsageRule.  # noqa: E501
        :rtype: VisualQueryOperatorUsageRule
        """
        return util.deserialize_model(dikt, cls)

    @property
    def rule_type(self) -> str:
        """Gets the rule_type of this VisualQueryOperatorUsageRule.

        The type of the rule, which determines whether it allows or denies particular conditions.  # noqa: E501

        :return: The rule_type of this VisualQueryOperatorUsageRule.
        :rtype: str
        """
        return self._rule_type

    @rule_type.setter
    def rule_type(self, rule_type: str):
        """Sets the rule_type of this VisualQueryOperatorUsageRule.

        The type of the rule, which determines whether it allows or denies particular conditions.  # noqa: E501

        :param rule_type: The rule_type of this VisualQueryOperatorUsageRule.
        :type rule_type: str
        """
        allowed_values = ["DENY", "ALLOW"]  # noqa: E501
        if rule_type not in allowed_values:
            raise ValueError(
                "Invalid value for `rule_type` ({0}), must be one of {1}"
                .format(rule_type, allowed_values)
            )

        self._rule_type = rule_type

    @property
    def item_type_id(self) -> str:
        """Gets the item_type_id of this VisualQueryOperatorUsageRule.

        The identifier of the query item type that the rule affects.  # noqa: E501

        :return: The item_type_id of this VisualQueryOperatorUsageRule.
        :rtype: str
        """
        return self._item_type_id

    @item_type_id.setter
    def item_type_id(self, item_type_id: str):
        """Sets the item_type_id of this VisualQueryOperatorUsageRule.

        The identifier of the query item type that the rule affects.  # noqa: E501

        :param item_type_id: The item_type_id of this VisualQueryOperatorUsageRule.
        :type item_type_id: str
        """

        self._item_type_id = item_type_id

    @property
    def property_type_ids(self) -> List[str]:
        """Gets the property_type_ids of this VisualQueryOperatorUsageRule.

        The identifiers of the property types that the rule affects.  # noqa: E501

        :return: The property_type_ids of this VisualQueryOperatorUsageRule.
        :rtype: List[str]
        """
        return self._property_type_ids

    @property_type_ids.setter
    def property_type_ids(self, property_type_ids: List[str]):
        """Sets the property_type_ids of this VisualQueryOperatorUsageRule.

        The identifiers of the property types that the rule affects.  # noqa: E501

        :param property_type_ids: The property_type_ids of this VisualQueryOperatorUsageRule.
        :type property_type_ids: List[str]
        """

        self._property_type_ids = property_type_ids

    @property
    def operators(self) -> List[VisualQueryConditionOperator]:
        """Gets the operators of this VisualQueryOperatorUsageRule.

        The operators that the rule permits or prevents from appearing in conditions.  # noqa: E501

        :return: The operators of this VisualQueryOperatorUsageRule.
        :rtype: List[VisualQueryConditionOperator]
        """
        return self._operators

    @operators.setter
    def operators(self, operators: List[VisualQueryConditionOperator]):
        """Sets the operators of this VisualQueryOperatorUsageRule.

        The operators that the rule permits or prevents from appearing in conditions.  # noqa: E501

        :param operators: The operators of this VisualQueryOperatorUsageRule.
        :type operators: List[VisualQueryConditionOperator]
        """

        self._operators = operators

    @property
    def aspects(self) -> List[VisualQueryConditionAspect]:
        """Gets the aspects of this VisualQueryOperatorUsageRule.

        The aspects of the property types that the rule affects.  # noqa: E501

        :return: The aspects of this VisualQueryOperatorUsageRule.
        :rtype: List[VisualQueryConditionAspect]
        """
        return self._aspects

    @aspects.setter
    def aspects(self, aspects: List[VisualQueryConditionAspect]):
        """Sets the aspects of this VisualQueryOperatorUsageRule.

        The aspects of the property types that the rule affects.  # noqa: E501

        :param aspects: The aspects of this VisualQueryOperatorUsageRule.
        :type aspects: List[VisualQueryConditionAspect]
        """

        self._aspects = aspects
