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
from spi.models.link_direction import LinkDirection
from spi.models.visual_query_condition import VisualQueryCondition
from spi.models.visual_query_count_condition import VisualQueryCountCondition
from spi.models.visual_query_item import VisualQueryItem
from spi import util


class VisualQueryLink(Model):

    def __init__(self, conditions=None, count_condition=None, id=None, item_type_ids=None, label=None, output_enabled=None, type=None, direction=None, from_id=None, to_id=None):  # noqa: E501
        """VisualQueryLink

        :param conditions: The conditions of this VisualQueryLink.  # noqa: E501
        :type conditions: List[VisualQueryCondition]
        :param count_condition: The count_condition of this VisualQueryLink.  # noqa: E501
        :type count_condition: VisualQueryCountCondition
        :param id: The id of this VisualQueryLink.  # noqa: E501
        :type id: str
        :param item_type_ids: The item_type_ids of this VisualQueryLink.  # noqa: E501
        :type item_type_ids: List[str]
        :param label: The label of this VisualQueryLink.  # noqa: E501
        :type label: str
        :param output_enabled: The output_enabled of this VisualQueryLink.  # noqa: E501
        :type output_enabled: bool
        :param type: The type of this VisualQueryLink.  # noqa: E501
        :type type: str
        :param direction: The direction of this VisualQueryLink.  # noqa: E501
        :type direction: LinkDirection
        :param from_id: The from_id of this VisualQueryLink.  # noqa: E501
        :type from_id: str
        :param to_id: The to_id of this VisualQueryLink.  # noqa: E501
        :type to_id: str
        """
        self.openapi_types = {
            'conditions': List[VisualQueryCondition],
            'count_condition': VisualQueryCountCondition,
            'id': str,
            'item_type_ids': List[str],
            'label': str,
            'output_enabled': bool,
            'type': str,
            'direction': LinkDirection,
            'from_id': str,
            'to_id': str
        }

        self.attribute_map = {
            'conditions': 'conditions',
            'count_condition': 'countCondition',
            'id': 'id',
            'item_type_ids': 'itemTypeIds',
            'label': 'label',
            'output_enabled': 'outputEnabled',
            'type': 'type',
            'direction': 'direction',
            'from_id': 'fromId',
            'to_id': 'toId'
        }

        self._conditions = conditions
        self._count_condition = count_condition
        self._id = id
        self._item_type_ids = item_type_ids
        self._label = label
        self._output_enabled = output_enabled
        self._type = type
        self._direction = direction
        self._from_id = from_id
        self._to_id = to_id

    @classmethod
    def from_dict(cls, dikt) -> 'VisualQueryLink':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The VisualQueryLink of this VisualQueryLink.  # noqa: E501
        :rtype: VisualQueryLink
        """
        return util.deserialize_model(dikt, cls)

    @property
    def conditions(self) -> List[VisualQueryCondition]:
        """Gets the conditions of this VisualQueryLink.

        The property conditions for the query item.  # noqa: E501

        :return: The conditions of this VisualQueryLink.
        :rtype: List[VisualQueryCondition]
        """
        return self._conditions

    @conditions.setter
    def conditions(self, conditions: List[VisualQueryCondition]):
        """Sets the conditions of this VisualQueryLink.

        The property conditions for the query item.  # noqa: E501

        :param conditions: The conditions of this VisualQueryLink.
        :type conditions: List[VisualQueryCondition]
        """

        self._conditions = conditions

    @property
    def count_condition(self) -> VisualQueryCountCondition:
        """Gets the count_condition of this VisualQueryLink.


        :return: The count_condition of this VisualQueryLink.
        :rtype: VisualQueryCountCondition
        """
        return self._count_condition

    @count_condition.setter
    def count_condition(self, count_condition: VisualQueryCountCondition):
        """Sets the count_condition of this VisualQueryLink.


        :param count_condition: The count_condition of this VisualQueryLink.
        :type count_condition: VisualQueryCountCondition
        """

        self._count_condition = count_condition

    @property
    def id(self) -> str:
        """Gets the id of this VisualQueryLink.

        The identifier of the query item within the search structure.  # noqa: E501

        :return: The id of this VisualQueryLink.
        :rtype: str
        """
        return self._id

    @id.setter
    def id(self, id: str):
        """Sets the id of this VisualQueryLink.

        The identifier of the query item within the search structure.  # noqa: E501

        :param id: The id of this VisualQueryLink.
        :type id: str
        """
        if id is None:
            raise ValueError("Invalid value for `id`, must not be `None`")  # noqa: E501

        self._id = id

    @property
    def item_type_ids(self) -> List[str]:
        """Gets the item_type_ids of this VisualQueryLink.

        The identifiers of the item types of records that can match the query item.  # noqa: E501

        :return: The item_type_ids of this VisualQueryLink.
        :rtype: List[str]
        """
        return self._item_type_ids

    @item_type_ids.setter
    def item_type_ids(self, item_type_ids: List[str]):
        """Sets the item_type_ids of this VisualQueryLink.

        The identifiers of the item types of records that can match the query item.  # noqa: E501

        :param item_type_ids: The item_type_ids of this VisualQueryLink.
        :type item_type_ids: List[str]
        """

        self._item_type_ids = item_type_ids

    @property
    def label(self) -> str:
        """Gets the label of this VisualQueryLink.

        The label of the query item in the visual query editor.  # noqa: E501

        :return: The label of this VisualQueryLink.
        :rtype: str
        """
        return self._label

    @label.setter
    def label(self, label: str):
        """Sets the label of this VisualQueryLink.

        The label of the query item in the visual query editor.  # noqa: E501

        :param label: The label of this VisualQueryLink.
        :type label: str
        """
        if label is None:
            raise ValueError("Invalid value for `label`, must not be `None`")  # noqa: E501

        self._label = label

    @property
    def output_enabled(self) -> bool:
        """Gets the output_enabled of this VisualQueryLink.

        Indicates whether records that match the query item are included in (**true**) or excluded from (**false**) the results.  # noqa: E501

        :return: The output_enabled of this VisualQueryLink.
        :rtype: bool
        """
        return self._output_enabled

    @output_enabled.setter
    def output_enabled(self, output_enabled: bool):
        """Sets the output_enabled of this VisualQueryLink.

        Indicates whether records that match the query item are included in (**true**) or excluded from (**false**) the results.  # noqa: E501

        :param output_enabled: The output_enabled of this VisualQueryLink.
        :type output_enabled: bool
        """
        if output_enabled is None:
            raise ValueError("Invalid value for `output_enabled`, must not be `None`")  # noqa: E501

        self._output_enabled = output_enabled

    @property
    def type(self) -> str:
        """Gets the type of this VisualQueryLink.

        The type of the query item, which is **QUERY_ENTITY** for a query entity, or **QUERY_LINK** for a query link.  # noqa: E501

        :return: The type of this VisualQueryLink.
        :rtype: str
        """
        return self._type

    @type.setter
    def type(self, type: str):
        """Sets the type of this VisualQueryLink.

        The type of the query item, which is **QUERY_ENTITY** for a query entity, or **QUERY_LINK** for a query link.  # noqa: E501

        :param type: The type of this VisualQueryLink.
        :type type: str
        """
        allowed_values = ["QUERY_ENTITY", "QUERY_LINK"]  # noqa: E501
        if type not in allowed_values:
            raise ValueError(
                "Invalid value for `type` ({0}), must be one of {1}"
                .format(type, allowed_values)
            )

        self._type = type

    @property
    def direction(self) -> LinkDirection:
        """Gets the direction of this VisualQueryLink.


        :return: The direction of this VisualQueryLink.
        :rtype: LinkDirection
        """
        return self._direction

    @direction.setter
    def direction(self, direction: LinkDirection):
        """Sets the direction of this VisualQueryLink.


        :param direction: The direction of this VisualQueryLink.
        :type direction: LinkDirection
        """

        self._direction = direction

    @property
    def from_id(self) -> str:
        """Gets the from_id of this VisualQueryLink.

        The identifier of the query entity at the \"from\" end of this query link.  # noqa: E501

        :return: The from_id of this VisualQueryLink.
        :rtype: str
        """
        return self._from_id

    @from_id.setter
    def from_id(self, from_id: str):
        """Sets the from_id of this VisualQueryLink.

        The identifier of the query entity at the \"from\" end of this query link.  # noqa: E501

        :param from_id: The from_id of this VisualQueryLink.
        :type from_id: str
        """
        if from_id is None:
            raise ValueError("Invalid value for `from_id`, must not be `None`")  # noqa: E501

        self._from_id = from_id

    @property
    def to_id(self) -> str:
        """Gets the to_id of this VisualQueryLink.

        The identifier of the query entity at the \"to\" end of this query link.  # noqa: E501

        :return: The to_id of this VisualQueryLink.
        :rtype: str
        """
        return self._to_id

    @to_id.setter
    def to_id(self, to_id: str):
        """Sets the to_id of this VisualQueryLink.

        The identifier of the query entity at the \"to\" end of this query link.  # noqa: E501

        :param to_id: The to_id of this VisualQueryLink.
        :type to_id: str
        """
        if to_id is None:
            raise ValueError("Invalid value for `to_id`, must not be `None`")  # noqa: E501

        self._to_id = to_id
