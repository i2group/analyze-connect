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


class VisualQueryConstraints(Model):

    def __init__(self, output_selection_supported=True, maximum_count_conditions=1, maximum_query_link_count=None, maximum_any_entity_type_supported=None, maximum_any_link_type_supported=None):  # noqa: E501
        """VisualQueryConstraints

        :param output_selection_supported: The output_selection_supported of this VisualQueryConstraints.  # noqa: E501
        :type output_selection_supported: bool
        :param maximum_count_conditions: The maximum_count_conditions of this VisualQueryConstraints.  # noqa: E501
        :type maximum_count_conditions: int
        :param maximum_query_link_count: The maximum_query_link_count of this VisualQueryConstraints.  # noqa: E501
        :type maximum_query_link_count: int
        :param maximum_any_entity_type_supported: The maximum_any_entity_type_supported of this VisualQueryConstraints.  # noqa: E501
        :type maximum_any_entity_type_supported: int
        :param maximum_any_link_type_supported: The maximum_any_link_type_supported of this VisualQueryConstraints.  # noqa: E501
        :type maximum_any_link_type_supported: int
        """
        self.openapi_types = {
            'output_selection_supported': bool,
            'maximum_count_conditions': int,
            'maximum_query_link_count': int,
            'maximum_any_entity_type_supported': int,
            'maximum_any_link_type_supported': int
        }

        self.attribute_map = {
            'output_selection_supported': 'outputSelectionSupported',
            'maximum_count_conditions': 'maximumCountConditions',
            'maximum_query_link_count': 'maximumQueryLinkCount',
            'maximum_any_entity_type_supported': 'maximumAnyEntityTypeSupported',
            'maximum_any_link_type_supported': 'maximumAnyLinkTypeSupported'
        }

        self._output_selection_supported = output_selection_supported
        self._maximum_count_conditions = maximum_count_conditions
        self._maximum_query_link_count = maximum_query_link_count
        self._maximum_any_entity_type_supported = maximum_any_entity_type_supported
        self._maximum_any_link_type_supported = maximum_any_link_type_supported

    @classmethod
    def from_dict(cls, dikt) -> 'VisualQueryConstraints':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The VisualQueryConstraints of this VisualQueryConstraints.  # noqa: E501
        :rtype: VisualQueryConstraints
        """
        return util.deserialize_model(dikt, cls)

    @property
    def output_selection_supported(self) -> bool:
        """Gets the output_selection_supported of this VisualQueryConstraints.

        Indicates whether query items can be marked for output, so that matching records appear in search results. When this is **false**, the connector implementation controls which records appear.  # noqa: E501

        :return: The output_selection_supported of this VisualQueryConstraints.
        :rtype: bool
        """
        return self._output_selection_supported

    @output_selection_supported.setter
    def output_selection_supported(self, output_selection_supported: bool):
        """Sets the output_selection_supported of this VisualQueryConstraints.

        Indicates whether query items can be marked for output, so that matching records appear in search results. When this is **false**, the connector implementation controls which records appear.  # noqa: E501

        :param output_selection_supported: The output_selection_supported of this VisualQueryConstraints.
        :type output_selection_supported: bool
        """

        self._output_selection_supported = output_selection_supported

    @property
    def maximum_count_conditions(self) -> int:
        """Gets the maximum_count_conditions of this VisualQueryConstraints.

        The maximum number of query items that can have count conditions.  # noqa: E501

        :return: The maximum_count_conditions of this VisualQueryConstraints.
        :rtype: int
        """
        return self._maximum_count_conditions

    @maximum_count_conditions.setter
    def maximum_count_conditions(self, maximum_count_conditions: int):
        """Sets the maximum_count_conditions of this VisualQueryConstraints.

        The maximum number of query items that can have count conditions.  # noqa: E501

        :param maximum_count_conditions: The maximum_count_conditions of this VisualQueryConstraints.
        :type maximum_count_conditions: int
        """
        if maximum_count_conditions is not None and maximum_count_conditions < 0:  # noqa: E501
            raise ValueError("Invalid value for `maximum_count_conditions`, must be a value greater than or equal to `0`")  # noqa: E501

        self._maximum_count_conditions = maximum_count_conditions

    @property
    def maximum_query_link_count(self) -> int:
        """Gets the maximum_query_link_count of this VisualQueryConstraints.

        The maximum number of query links in a single Visual Query.  # noqa: E501

        :return: The maximum_query_link_count of this VisualQueryConstraints.
        :rtype: int
        """
        return self._maximum_query_link_count

    @maximum_query_link_count.setter
    def maximum_query_link_count(self, maximum_query_link_count: int):
        """Sets the maximum_query_link_count of this VisualQueryConstraints.

        The maximum number of query links in a single Visual Query.  # noqa: E501

        :param maximum_query_link_count: The maximum_query_link_count of this VisualQueryConstraints.
        :type maximum_query_link_count: int
        """
        if maximum_query_link_count is not None and maximum_query_link_count < 0:  # noqa: E501
            raise ValueError("Invalid value for `maximum_query_link_count`, must be a value greater than or equal to `0`")  # noqa: E501

        self._maximum_query_link_count = maximum_query_link_count

    @property
    def maximum_any_entity_type_supported(self) -> int:
        """Gets the maximum_any_entity_type_supported of this VisualQueryConstraints.

        The maximum number of query entities that can have the 'Any' type.  # noqa: E501

        :return: The maximum_any_entity_type_supported of this VisualQueryConstraints.
        :rtype: int
        """
        return self._maximum_any_entity_type_supported

    @maximum_any_entity_type_supported.setter
    def maximum_any_entity_type_supported(self, maximum_any_entity_type_supported: int):
        """Sets the maximum_any_entity_type_supported of this VisualQueryConstraints.

        The maximum number of query entities that can have the 'Any' type.  # noqa: E501

        :param maximum_any_entity_type_supported: The maximum_any_entity_type_supported of this VisualQueryConstraints.
        :type maximum_any_entity_type_supported: int
        """
        if maximum_any_entity_type_supported is not None and maximum_any_entity_type_supported < 0:  # noqa: E501
            raise ValueError("Invalid value for `maximum_any_entity_type_supported`, must be a value greater than or equal to `0`")  # noqa: E501

        self._maximum_any_entity_type_supported = maximum_any_entity_type_supported

    @property
    def maximum_any_link_type_supported(self) -> int:
        """Gets the maximum_any_link_type_supported of this VisualQueryConstraints.

        The maximum number of query links that can have the 'Any' type.  # noqa: E501

        :return: The maximum_any_link_type_supported of this VisualQueryConstraints.
        :rtype: int
        """
        return self._maximum_any_link_type_supported

    @maximum_any_link_type_supported.setter
    def maximum_any_link_type_supported(self, maximum_any_link_type_supported: int):
        """Sets the maximum_any_link_type_supported of this VisualQueryConstraints.

        The maximum number of query links that can have the 'Any' type.  # noqa: E501

        :param maximum_any_link_type_supported: The maximum_any_link_type_supported of this VisualQueryConstraints.
        :type maximum_any_link_type_supported: int
        """
        if maximum_any_link_type_supported is not None and maximum_any_link_type_supported < 0:  # noqa: E501
            raise ValueError("Invalid value for `maximum_any_link_type_supported`, must be a value greater than or equal to `0`")  # noqa: E501

        self._maximum_any_link_type_supported = maximum_any_link_type_supported
