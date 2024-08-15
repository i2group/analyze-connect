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
from spi.models.connector_seed_types import ConnectorSeedTypes
from spi import util


class ConnectorSeedConstraints(Model):

    def __init__(self, connector_ids=None, max=None, min=None, seed_types=None):  # noqa: E501
        """ConnectorSeedConstraints

        :param connector_ids: The connector_ids of this ConnectorSeedConstraints.  # noqa: E501
        :type connector_ids: List[str]
        :param max: The max of this ConnectorSeedConstraints.  # noqa: E501
        :type max: int
        :param min: The min of this ConnectorSeedConstraints.  # noqa: E501
        :type min: int
        :param seed_types: The seed_types of this ConnectorSeedConstraints.  # noqa: E501
        :type seed_types: ConnectorSeedTypes
        """
        self.openapi_types = {
            'connector_ids': List[str],
            'max': int,
            'min': int,
            'seed_types': ConnectorSeedTypes
        }

        self.attribute_map = {
            'connector_ids': 'connectorIds',
            'max': 'max',
            'min': 'min',
            'seed_types': 'seedTypes'
        }

        self._connector_ids = connector_ids
        self._max = max
        self._min = min
        self._seed_types = seed_types

    @classmethod
    def from_dict(cls, dikt) -> 'ConnectorSeedConstraints':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The ConnectorSeedConstraints of this ConnectorSeedConstraints.  # noqa: E501
        :rtype: ConnectorSeedConstraints
        """
        return util.deserialize_model(dikt, cls)

    @property
    def connector_ids(self) -> List[str]:
        """Gets the connector_ids of this ConnectorSeedConstraints.

        The identifiers of the connectors from which seed records can originate. This setting is valid when **seedTypes** specifies item type constraints, but not when it specifies semantic constraints.  # noqa: E501

        :return: The connector_ids of this ConnectorSeedConstraints.
        :rtype: List[str]
        """
        return self._connector_ids

    @connector_ids.setter
    def connector_ids(self, connector_ids: List[str]):
        """Sets the connector_ids of this ConnectorSeedConstraints.

        The identifiers of the connectors from which seed records can originate. This setting is valid when **seedTypes** specifies item type constraints, but not when it specifies semantic constraints.  # noqa: E501

        :param connector_ids: The connector_ids of this ConnectorSeedConstraints.
        :type connector_ids: List[str]
        """

        self._connector_ids = connector_ids

    @property
    def max(self) -> int:
        """Gets the max of this ConnectorSeedConstraints.

        The maximum number of seed records that users must specify.  # noqa: E501

        :return: The max of this ConnectorSeedConstraints.
        :rtype: int
        """
        return self._max

    @max.setter
    def max(self, max: int):
        """Sets the max of this ConnectorSeedConstraints.

        The maximum number of seed records that users must specify.  # noqa: E501

        :param max: The max of this ConnectorSeedConstraints.
        :type max: int
        """

        self._max = max

    @property
    def min(self) -> int:
        """Gets the min of this ConnectorSeedConstraints.

        The minimum number of seed records that users must specify.  # noqa: E501

        :return: The min of this ConnectorSeedConstraints.
        :rtype: int
        """
        return self._min

    @min.setter
    def min(self, min: int):
        """Sets the min of this ConnectorSeedConstraints.

        The minimum number of seed records that users must specify.  # noqa: E501

        :param min: The min of this ConnectorSeedConstraints.
        :type min: int
        """

        self._min = min

    @property
    def seed_types(self) -> ConnectorSeedTypes:
        """Gets the seed_types of this ConnectorSeedConstraints.


        :return: The seed_types of this ConnectorSeedConstraints.
        :rtype: ConnectorSeedTypes
        """
        return self._seed_types

    @seed_types.setter
    def seed_types(self, seed_types: ConnectorSeedTypes):
        """Sets the seed_types of this ConnectorSeedConstraints.


        :param seed_types: The seed_types of this ConnectorSeedConstraints.
        :type seed_types: ConnectorSeedTypes
        """

        self._seed_types = seed_types
