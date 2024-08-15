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
from spi.models.visual_query_condition import VisualQueryCondition
from spi.models.visual_query_condition_aspect import VisualQueryConditionAspect
from spi.models.visual_query_condition_operator import VisualQueryConditionOperator
from spi.models.visual_query_condition_value_type import VisualQueryConditionValueType
from spi import util


class VisualQueryNumericCondition(Model):

    def __init__(self, aspect=None, id=None, operator=None, value_type=None, values=None):  # noqa: E501
        """VisualQueryNumericCondition

        :param aspect: The aspect of this VisualQueryNumericCondition.  # noqa: E501
        :type aspect: VisualQueryConditionAspect
        :param id: The id of this VisualQueryNumericCondition.  # noqa: E501
        :type id: str
        :param operator: The operator of this VisualQueryNumericCondition.  # noqa: E501
        :type operator: VisualQueryConditionOperator
        :param value_type: The value_type of this VisualQueryNumericCondition.  # noqa: E501
        :type value_type: VisualQueryConditionValueType
        :param values: The values of this VisualQueryNumericCondition.  # noqa: E501
        :type values: List[float]
        """
        self.openapi_types = {
            'aspect': VisualQueryConditionAspect,
            'id': str,
            'operator': VisualQueryConditionOperator,
            'value_type': VisualQueryConditionValueType,
            'values': List[float]
        }

        self.attribute_map = {
            'aspect': 'aspect',
            'id': 'id',
            'operator': 'operator',
            'value_type': 'valueType',
            'values': 'values'
        }

        self._aspect = aspect
        self._id = id
        self._operator = operator
        self._value_type = value_type
        self._values = values

    @classmethod
    def from_dict(cls, dikt) -> 'VisualQueryNumericCondition':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The VisualQueryNumericCondition of this VisualQueryNumericCondition.  # noqa: E501
        :rtype: VisualQueryNumericCondition
        """
        return util.deserialize_model(dikt, cls)

    @property
    def aspect(self) -> VisualQueryConditionAspect:
        """Gets the aspect of this VisualQueryNumericCondition.


        :return: The aspect of this VisualQueryNumericCondition.
        :rtype: VisualQueryConditionAspect
        """
        return self._aspect

    @aspect.setter
    def aspect(self, aspect: VisualQueryConditionAspect):
        """Sets the aspect of this VisualQueryNumericCondition.


        :param aspect: The aspect of this VisualQueryNumericCondition.
        :type aspect: VisualQueryConditionAspect
        """

        self._aspect = aspect

    @property
    def id(self) -> str:
        """Gets the id of this VisualQueryNumericCondition.

        The identifier of the property or metadata type to use in this query condition.  # noqa: E501

        :return: The id of this VisualQueryNumericCondition.
        :rtype: str
        """
        return self._id

    @id.setter
    def id(self, id: str):
        """Sets the id of this VisualQueryNumericCondition.

        The identifier of the property or metadata type to use in this query condition.  # noqa: E501

        :param id: The id of this VisualQueryNumericCondition.
        :type id: str
        """
        if id is None:
            raise ValueError("Invalid value for `id`, must not be `None`")  # noqa: E501

        self._id = id

    @property
    def operator(self) -> VisualQueryConditionOperator:
        """Gets the operator of this VisualQueryNumericCondition.


        :return: The operator of this VisualQueryNumericCondition.
        :rtype: VisualQueryConditionOperator
        """
        return self._operator

    @operator.setter
    def operator(self, operator: VisualQueryConditionOperator):
        """Sets the operator of this VisualQueryNumericCondition.


        :param operator: The operator of this VisualQueryNumericCondition.
        :type operator: VisualQueryConditionOperator
        """
        if operator is None:
            raise ValueError("Invalid value for `operator`, must not be `None`")  # noqa: E501

        self._operator = operator

    @property
    def value_type(self) -> VisualQueryConditionValueType:
        """Gets the value_type of this VisualQueryNumericCondition.


        :return: The value_type of this VisualQueryNumericCondition.
        :rtype: VisualQueryConditionValueType
        """
        return self._value_type

    @value_type.setter
    def value_type(self, value_type: VisualQueryConditionValueType):
        """Sets the value_type of this VisualQueryNumericCondition.


        :param value_type: The value_type of this VisualQueryNumericCondition.
        :type value_type: VisualQueryConditionValueType
        """
        if value_type is None:
            raise ValueError("Invalid value for `value_type`, must not be `None`")  # noqa: E501

        self._value_type = value_type

    @property
    def values(self) -> List[float]:
        """Gets the values of this VisualQueryNumericCondition.


        :return: The values of this VisualQueryNumericCondition.
        :rtype: List[float]
        """
        return self._values

    @values.setter
    def values(self, values: List[float]):
        """Sets the values of this VisualQueryNumericCondition.


        :param values: The values of this VisualQueryNumericCondition.
        :type values: List[float]
        """

        self._values = values
