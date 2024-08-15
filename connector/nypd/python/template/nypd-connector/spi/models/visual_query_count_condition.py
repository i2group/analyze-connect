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
from spi.models.visual_query_count_condition_operator import VisualQueryCountConditionOperator
from spi import util


class VisualQueryCountCondition(Model):

    def __init__(self, operator=None, values=None, query_item_ids=None):  # noqa: E501
        """VisualQueryCountCondition

        :param operator: The operator of this VisualQueryCountCondition.  # noqa: E501
        :type operator: VisualQueryCountConditionOperator
        :param values: The values of this VisualQueryCountCondition.  # noqa: E501
        :type values: List[int]
        :param query_item_ids: The query_item_ids of this VisualQueryCountCondition.  # noqa: E501
        :type query_item_ids: List[str]
        """
        self.openapi_types = {
            'operator': VisualQueryCountConditionOperator,
            'values': List[int],
            'query_item_ids': List[str]
        }

        self.attribute_map = {
            'operator': 'operator',
            'values': 'values',
            'query_item_ids': 'queryItemIds'
        }

        self._operator = operator
        self._values = values
        self._query_item_ids = query_item_ids

    @classmethod
    def from_dict(cls, dikt) -> 'VisualQueryCountCondition':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The VisualQueryCountCondition of this VisualQueryCountCondition.  # noqa: E501
        :rtype: VisualQueryCountCondition
        """
        return util.deserialize_model(dikt, cls)

    @property
    def operator(self) -> VisualQueryCountConditionOperator:
        """Gets the operator of this VisualQueryCountCondition.


        :return: The operator of this VisualQueryCountCondition.
        :rtype: VisualQueryCountConditionOperator
        """
        return self._operator

    @operator.setter
    def operator(self, operator: VisualQueryCountConditionOperator):
        """Sets the operator of this VisualQueryCountCondition.


        :param operator: The operator of this VisualQueryCountCondition.
        :type operator: VisualQueryCountConditionOperator
        """
        if operator is None:
            raise ValueError("Invalid value for `operator`, must not be `None`")  # noqa: E501

        self._operator = operator

    @property
    def values(self) -> List[int]:
        """Gets the values of this VisualQueryCountCondition.

        The values to use in this count condition. Unless **operator** is **BETWEEN**, this array contains exactly one value.  # noqa: E501

        :return: The values of this VisualQueryCountCondition.
        :rtype: List[int]
        """
        return self._values

    @values.setter
    def values(self, values: List[int]):
        """Sets the values of this VisualQueryCountCondition.

        The values to use in this count condition. Unless **operator** is **BETWEEN**, this array contains exactly one value.  # noqa: E501

        :param values: The values of this VisualQueryCountCondition.
        :type values: List[int]
        """

        self._values = values

    @property
    def query_item_ids(self) -> List[str]:
        """Gets the query_item_ids of this VisualQueryCountCondition.

        The identifiers of the other query items that are bound by this count condition.  # noqa: E501

        :return: The query_item_ids of this VisualQueryCountCondition.
        :rtype: List[str]
        """
        return self._query_item_ids

    @query_item_ids.setter
    def query_item_ids(self, query_item_ids: List[str]):
        """Sets the query_item_ids of this VisualQueryCountCondition.

        The identifiers of the other query items that are bound by this count condition.  # noqa: E501

        :param query_item_ids: The query_item_ids of this VisualQueryCountCondition.
        :type query_item_ids: List[str]
        """
        if query_item_ids is not None and len(query_item_ids) < 1:
            raise ValueError("Invalid value for `query_item_ids`, number of items must be greater than or equal to `1`")  # noqa: E501

        self._query_item_ids = query_item_ids
