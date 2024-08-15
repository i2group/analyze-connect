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
from spi.models.schema_type_location import SchemaTypeLocation
from spi import util


class ConnectorSeedConstraintItemType(Model):

    def __init__(self, id=None, max=None, min=None, type_location=None):  # noqa: E501
        """ConnectorSeedConstraintItemType

        :param id: The id of this ConnectorSeedConstraintItemType.  # noqa: E501
        :type id: str
        :param max: The max of this ConnectorSeedConstraintItemType.  # noqa: E501
        :type max: int
        :param min: The min of this ConnectorSeedConstraintItemType.  # noqa: E501
        :type min: int
        :param type_location: The type_location of this ConnectorSeedConstraintItemType.  # noqa: E501
        :type type_location: SchemaTypeLocation
        """
        self.openapi_types = {
            'id': str,
            'max': int,
            'min': int,
            'type_location': SchemaTypeLocation
        }

        self.attribute_map = {
            'id': 'id',
            'max': 'max',
            'min': 'min',
            'type_location': 'typeLocation'
        }

        self._id = id
        self._max = max
        self._min = min
        self._type_location = type_location

    @classmethod
    def from_dict(cls, dikt) -> 'ConnectorSeedConstraintItemType':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The ConnectorSeedConstraintItemType of this ConnectorSeedConstraintItemType.  # noqa: E501
        :rtype: ConnectorSeedConstraintItemType
        """
        return util.deserialize_model(dikt, cls)

    @property
    def id(self) -> str:
        """Gets the id of this ConnectorSeedConstraintItemType.

        The identifier of the item type.  # noqa: E501

        :return: The id of this ConnectorSeedConstraintItemType.
        :rtype: str
        """
        return self._id

    @id.setter
    def id(self, id: str):
        """Sets the id of this ConnectorSeedConstraintItemType.

        The identifier of the item type.  # noqa: E501

        :param id: The id of this ConnectorSeedConstraintItemType.
        :type id: str
        """
        if id is None:
            raise ValueError("Invalid value for `id`, must not be `None`")  # noqa: E501

        self._id = id

    @property
    def max(self) -> int:
        """Gets the max of this ConnectorSeedConstraintItemType.

        The maximum number of seed records of this type.  # noqa: E501

        :return: The max of this ConnectorSeedConstraintItemType.
        :rtype: int
        """
        return self._max

    @max.setter
    def max(self, max: int):
        """Sets the max of this ConnectorSeedConstraintItemType.

        The maximum number of seed records of this type.  # noqa: E501

        :param max: The max of this ConnectorSeedConstraintItemType.
        :type max: int
        """

        self._max = max

    @property
    def min(self) -> int:
        """Gets the min of this ConnectorSeedConstraintItemType.

        The minimum number of seed records of this type.  # noqa: E501

        :return: The min of this ConnectorSeedConstraintItemType.
        :rtype: int
        """
        return self._min

    @min.setter
    def min(self, min: int):
        """Sets the min of this ConnectorSeedConstraintItemType.

        The minimum number of seed records of this type.  # noqa: E501

        :param min: The min of this ConnectorSeedConstraintItemType.
        :type min: int
        """

        self._min = min

    @property
    def type_location(self) -> SchemaTypeLocation:
        """Gets the type_location of this ConnectorSeedConstraintItemType.


        :return: The type_location of this ConnectorSeedConstraintItemType.
        :rtype: SchemaTypeLocation
        """
        return self._type_location

    @type_location.setter
    def type_location(self, type_location: SchemaTypeLocation):
        """Sets the type_location of this ConnectorSeedConstraintItemType.


        :param type_location: The type_location of this ConnectorSeedConstraintItemType.
        :type type_location: SchemaTypeLocation
        """

        self._type_location = type_location
