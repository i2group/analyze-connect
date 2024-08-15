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
from spi.models.async_query_substatus import AsyncQuerySubstatus
from spi import util


class AsyncQueryStatus(Model):

    def __init__(self, error_message=None, state=None, substatuses=None, property_bag=None):  # noqa: E501
        """AsyncQueryStatus

        :param error_message: The error_message of this AsyncQueryStatus.  # noqa: E501
        :type error_message: str
        :param state: The state of this AsyncQueryStatus.  # noqa: E501
        :type state: str
        :param substatuses: The substatuses of this AsyncQueryStatus.  # noqa: E501
        :type substatuses: List[AsyncQuerySubstatus]
        :param property_bag: The property_bag of this AsyncQueryStatus.  # noqa: E501
        :type property_bag: Dict[str, object]
        """
        self.openapi_types = {
            'error_message': str,
            'state': str,
            'substatuses': List[AsyncQuerySubstatus],
            'property_bag': Dict[str, object]
        }

        self.attribute_map = {
            'error_message': 'errorMessage',
            'state': 'state',
            'substatuses': 'substatuses',
            'property_bag': 'propertyBag'
        }

        self._error_message = error_message
        self._state = state
        self._substatuses = substatuses
        self._property_bag = property_bag

    @classmethod
    def from_dict(cls, dikt) -> 'AsyncQueryStatus':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The AsyncQueryStatus of this AsyncQueryStatus.  # noqa: E501
        :rtype: AsyncQueryStatus
        """
        return util.deserialize_model(dikt, cls)

    @property
    def error_message(self) -> str:
        """Gets the error_message of this AsyncQueryStatus.

        An error message that explains why a query is in the 'FAILED' state.  # noqa: E501

        :return: The error_message of this AsyncQueryStatus.
        :rtype: str
        """
        return self._error_message

    @error_message.setter
    def error_message(self, error_message: str):
        """Sets the error_message of this AsyncQueryStatus.

        An error message that explains why a query is in the 'FAILED' state.  # noqa: E501

        :param error_message: The error_message of this AsyncQueryStatus.
        :type error_message: str
        """

        self._error_message = error_message

    @property
    def state(self) -> str:
        """Gets the state of this AsyncQueryStatus.

        The state of a query.  # noqa: E501

        :return: The state of this AsyncQueryStatus.
        :rtype: str
        """
        return self._state

    @state.setter
    def state(self, state: str):
        """Sets the state of this AsyncQueryStatus.

        The state of a query.  # noqa: E501

        :param state: The state of this AsyncQueryStatus.
        :type state: str
        """
        allowed_values = ["STARTED", "SUCCEEDED", "FAILED"]  # noqa: E501
        if state not in allowed_values:
            raise ValueError(
                "Invalid value for `state` ({0}), must be one of {1}"
                .format(state, allowed_values)
            )

        self._state = state

    @property
    def substatuses(self) -> List[AsyncQuerySubstatus]:
        """Gets the substatuses of this AsyncQueryStatus.

        More detailed information about the progress of a query.  # noqa: E501

        :return: The substatuses of this AsyncQueryStatus.
        :rtype: List[AsyncQuerySubstatus]
        """
        return self._substatuses

    @substatuses.setter
    def substatuses(self, substatuses: List[AsyncQuerySubstatus]):
        """Sets the substatuses of this AsyncQueryStatus.

        More detailed information about the progress of a query.  # noqa: E501

        :param substatuses: The substatuses of this AsyncQueryStatus.
        :type substatuses: List[AsyncQuerySubstatus]
        """

        self._substatuses = substatuses

    @property
    def property_bag(self) -> Dict[str, object]:
        """Gets the property_bag of this AsyncQueryStatus.

        A store for additional information about the status of a query that can be used by custom clients.  # noqa: E501

        :return: The property_bag of this AsyncQueryStatus.
        :rtype: Dict[str, object]
        """
        return self._property_bag

    @property_bag.setter
    def property_bag(self, property_bag: Dict[str, object]):
        """Sets the property_bag of this AsyncQueryStatus.

        A store for additional information about the status of a query that can be used by custom clients.  # noqa: E501

        :param property_bag: The property_bag of this AsyncQueryStatus.
        :type property_bag: Dict[str, object]
        """

        self._property_bag = property_bag
