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
from spi.models.visual_query_count_condition import VisualQueryCountCondition
from spi import util


class VisualQueryItem(Model):

    def __init__(self, conditions=None, count_condition=None, id=None, item_type_ids=None, label=None, output_enabled=None, type=None):  # noqa: E501
        """VisualQueryItem

        :param conditions: The conditions of this VisualQueryItem.  # noqa: E501
        :type conditions: List[VisualQueryCondition]
        :param count_condition: The count_condition of this VisualQueryItem.  # noqa: E501
        :type count_condition: VisualQueryCountCondition
        :param id: The id of this VisualQueryItem.  # noqa: E501
        :type id: str
        :param item_type_ids: The item_type_ids of this VisualQueryItem.  # noqa: E501
        :type item_type_ids: List[str]
        :param label: The label of this VisualQueryItem.  # noqa: E501
        :type label: str
        :param output_enabled: The output_enabled of this VisualQueryItem.  # noqa: E501
        :type output_enabled: bool
        :param type: The type of this VisualQueryItem.  # noqa: E501
        :type type: str
        """
        self.openapi_types = {
            'conditions': List[VisualQueryCondition],
            'count_condition': VisualQueryCountCondition,
            'id': str,
            'item_type_ids': List[str],
            'label': str,
            'output_enabled': bool,
            'type': str
        }

        self.attribute_map = {
            'conditions': 'conditions',
            'count_condition': 'countCondition',
            'id': 'id',
            'item_type_ids': 'itemTypeIds',
            'label': 'label',
            'output_enabled': 'outputEnabled',
            'type': 'type'
        }

        self._conditions = conditions
        self._count_condition = count_condition
        self._id = id
        self._item_type_ids = item_type_ids
        self._label = label
        self._output_enabled = output_enabled
        self._type = type

    @classmethod
    def from_dict(cls, dikt) -> 'VisualQueryItem':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The VisualQueryItem of this VisualQueryItem.  # noqa: E501
        :rtype: VisualQueryItem
        """
        return util.deserialize_model(dikt, cls)

    @property
    def conditions(self) -> List[VisualQueryCondition]:
        """Gets the conditions of this VisualQueryItem.

        The property conditions for the query item.  # noqa: E501

        :return: The conditions of this VisualQueryItem.
        :rtype: List[VisualQueryCondition]
        """
        return self._conditions

    @conditions.setter
    def conditions(self, conditions: List[VisualQueryCondition]):
        """Sets the conditions of this VisualQueryItem.

        The property conditions for the query item.  # noqa: E501

        :param conditions: The conditions of this VisualQueryItem.
        :type conditions: List[VisualQueryCondition]
        """

        self._conditions = conditions

    @property
    def count_condition(self) -> VisualQueryCountCondition:
        """Gets the count_condition of this VisualQueryItem.


        :return: The count_condition of this VisualQueryItem.
        :rtype: VisualQueryCountCondition
        """
        return self._count_condition

    @count_condition.setter
    def count_condition(self, count_condition: VisualQueryCountCondition):
        """Sets the count_condition of this VisualQueryItem.


        :param count_condition: The count_condition of this VisualQueryItem.
        :type count_condition: VisualQueryCountCondition
        """

        self._count_condition = count_condition

    @property
    def id(self) -> str:
        """Gets the id of this VisualQueryItem.

        The identifier of the query item within the search structure.  # noqa: E501

        :return: The id of this VisualQueryItem.
        :rtype: str
        """
        return self._id

    @id.setter
    def id(self, id: str):
        """Sets the id of this VisualQueryItem.

        The identifier of the query item within the search structure.  # noqa: E501

        :param id: The id of this VisualQueryItem.
        :type id: str
        """
        if id is None:
            raise ValueError("Invalid value for `id`, must not be `None`")  # noqa: E501

        self._id = id

    @property
    def item_type_ids(self) -> List[str]:
        """Gets the item_type_ids of this VisualQueryItem.

        The identifiers of the item types of records that can match the query item.  # noqa: E501

        :return: The item_type_ids of this VisualQueryItem.
        :rtype: List[str]
        """
        return self._item_type_ids

    @item_type_ids.setter
    def item_type_ids(self, item_type_ids: List[str]):
        """Sets the item_type_ids of this VisualQueryItem.

        The identifiers of the item types of records that can match the query item.  # noqa: E501

        :param item_type_ids: The item_type_ids of this VisualQueryItem.
        :type item_type_ids: List[str]
        """

        self._item_type_ids = item_type_ids

    @property
    def label(self) -> str:
        """Gets the label of this VisualQueryItem.

        The label of the query item in the visual query editor.  # noqa: E501

        :return: The label of this VisualQueryItem.
        :rtype: str
        """
        return self._label

    @label.setter
    def label(self, label: str):
        """Sets the label of this VisualQueryItem.

        The label of the query item in the visual query editor.  # noqa: E501

        :param label: The label of this VisualQueryItem.
        :type label: str
        """
        if label is None:
            raise ValueError("Invalid value for `label`, must not be `None`")  # noqa: E501

        self._label = label

    @property
    def output_enabled(self) -> bool:
        """Gets the output_enabled of this VisualQueryItem.

        Indicates whether records that match the query item are included in (**true**) or excluded from (**false**) the results.  # noqa: E501

        :return: The output_enabled of this VisualQueryItem.
        :rtype: bool
        """
        return self._output_enabled

    @output_enabled.setter
    def output_enabled(self, output_enabled: bool):
        """Sets the output_enabled of this VisualQueryItem.

        Indicates whether records that match the query item are included in (**true**) or excluded from (**false**) the results.  # noqa: E501

        :param output_enabled: The output_enabled of this VisualQueryItem.
        :type output_enabled: bool
        """
        if output_enabled is None:
            raise ValueError("Invalid value for `output_enabled`, must not be `None`")  # noqa: E501

        self._output_enabled = output_enabled

    @property
    def type(self) -> str:
        """Gets the type of this VisualQueryItem.

        The type of the query item, which is **QUERY_ENTITY** for a query entity, or **QUERY_LINK** for a query link.  # noqa: E501

        :return: The type of this VisualQueryItem.
        :rtype: str
        """
        return self._type

    @type.setter
    def type(self, type: str):
        """Sets the type of this VisualQueryItem.

        The type of the query item, which is **QUERY_ENTITY** for a query entity, or **QUERY_LINK** for a query link.  # noqa: E501

        :param type: The type of this VisualQueryItem.
        :type type: str
        """
        allowed_values = ["QUERY_ENTITY", "QUERY_LINK"]  # noqa: E501
        if type not in allowed_values:
            raise ValueError(
                "Invalid value for `type` ({0}), must be one of {1}"
                .format(type, allowed_values)
            )

        self._type = type
