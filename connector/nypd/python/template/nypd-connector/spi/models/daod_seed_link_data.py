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
from spi.models.security_dimension_and_values import SecurityDimensionAndValues
from spi.models.seed_source_identifier import SeedSourceIdentifier
from spi.models.type_location import TypeLocation
from spi import util


class DaodSeedLinkData(Model):

    def __init__(self, source_ids=None, seed_id=None, type_id=None, label=None, properties=None, access_dimension_values=None, extensions=None, from_end_id=None, from_end_type_id=None, link_direction=None, to_end_id=None, to_end_type_id=None, type_location=None):  # noqa: E501
        """DaodSeedLinkData

        :param source_ids: The source_ids of this DaodSeedLinkData.  # noqa: E501
        :type source_ids: List[SeedSourceIdentifier]
        :param seed_id: The seed_id of this DaodSeedLinkData.  # noqa: E501
        :type seed_id: str
        :param type_id: The type_id of this DaodSeedLinkData.  # noqa: E501
        :type type_id: str
        :param label: The label of this DaodSeedLinkData.  # noqa: E501
        :type label: str
        :param properties: The properties of this DaodSeedLinkData.  # noqa: E501
        :type properties: Dict[str, object]
        :param access_dimension_values: The access_dimension_values of this DaodSeedLinkData.  # noqa: E501
        :type access_dimension_values: List[SecurityDimensionAndValues]
        :param extensions: The extensions of this DaodSeedLinkData.  # noqa: E501
        :type extensions: Dict[str, object]
        :param from_end_id: The from_end_id of this DaodSeedLinkData.  # noqa: E501
        :type from_end_id: object
        :param from_end_type_id: The from_end_type_id of this DaodSeedLinkData.  # noqa: E501
        :type from_end_type_id: str
        :param link_direction: The link_direction of this DaodSeedLinkData.  # noqa: E501
        :type link_direction: LinkDirection
        :param to_end_id: The to_end_id of this DaodSeedLinkData.  # noqa: E501
        :type to_end_id: object
        :param to_end_type_id: The to_end_type_id of this DaodSeedLinkData.  # noqa: E501
        :type to_end_type_id: str
        :param type_location: The type_location of this DaodSeedLinkData.  # noqa: E501
        :type type_location: TypeLocation
        """
        self.openapi_types = {
            'source_ids': List[SeedSourceIdentifier],
            'seed_id': str,
            'type_id': str,
            'label': str,
            'properties': Dict[str, object],
            'access_dimension_values': List[SecurityDimensionAndValues],
            'extensions': Dict[str, object],
            'from_end_id': object,
            'from_end_type_id': str,
            'link_direction': LinkDirection,
            'to_end_id': object,
            'to_end_type_id': str,
            'type_location': TypeLocation
        }

        self.attribute_map = {
            'source_ids': 'sourceIds',
            'seed_id': 'seedId',
            'type_id': 'typeId',
            'label': 'label',
            'properties': 'properties',
            'access_dimension_values': 'accessDimensionValues',
            'extensions': 'extensions',
            'from_end_id': 'fromEndId',
            'from_end_type_id': 'fromEndTypeId',
            'link_direction': 'linkDirection',
            'to_end_id': 'toEndId',
            'to_end_type_id': 'toEndTypeId',
            'type_location': 'typeLocation'
        }

        self._source_ids = source_ids
        self._seed_id = seed_id
        self._type_id = type_id
        self._label = label
        self._properties = properties
        self._access_dimension_values = access_dimension_values
        self._extensions = extensions
        self._from_end_id = from_end_id
        self._from_end_type_id = from_end_type_id
        self._link_direction = link_direction
        self._to_end_id = to_end_id
        self._to_end_type_id = to_end_type_id
        self._type_location = type_location

    @classmethod
    def from_dict(cls, dikt) -> 'DaodSeedLinkData':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The DaodSeedLinkData of this DaodSeedLinkData.  # noqa: E501
        :rtype: DaodSeedLinkData
        """
        return util.deserialize_model(dikt, cls)

    @property
    def source_ids(self) -> List[SeedSourceIdentifier]:
        """Gets the source_ids of this DaodSeedLinkData.

        The source identifiers of a seed record.  # noqa: E501

        :return: The source_ids of this DaodSeedLinkData.
        :rtype: List[SeedSourceIdentifier]
        """
        return self._source_ids

    @source_ids.setter
    def source_ids(self, source_ids: List[SeedSourceIdentifier]):
        """Sets the source_ids of this DaodSeedLinkData.

        The source identifiers of a seed record.  # noqa: E501

        :param source_ids: The source_ids of this DaodSeedLinkData.
        :type source_ids: List[SeedSourceIdentifier]
        """
        if source_ids is None:
            raise ValueError("Invalid value for `source_ids`, must not be `None`")  # noqa: E501

        self._source_ids = source_ids

    @property
    def seed_id(self) -> str:
        """Gets the seed_id of this DaodSeedLinkData.

        The identifier of a seed record. If the seed was provided by Analyst's Notebook Premium or i2 Notebook Web, this is an i2 Analyze record identifier.  # noqa: E501

        :return: The seed_id of this DaodSeedLinkData.
        :rtype: str
        """
        return self._seed_id

    @seed_id.setter
    def seed_id(self, seed_id: str):
        """Sets the seed_id of this DaodSeedLinkData.

        The identifier of a seed record. If the seed was provided by Analyst's Notebook Premium or i2 Notebook Web, this is an i2 Analyze record identifier.  # noqa: E501

        :param seed_id: The seed_id of this DaodSeedLinkData.
        :type seed_id: str
        """
        if seed_id is None:
            raise ValueError("Invalid value for `seed_id`, must not be `None`")  # noqa: E501

        self._seed_id = seed_id

    @property
    def type_id(self) -> str:
        """Gets the type_id of this DaodSeedLinkData.

        The type identifier of the record identified by **seedId**.  # noqa: E501

        :return: The type_id of this DaodSeedLinkData.
        :rtype: str
        """
        return self._type_id

    @type_id.setter
    def type_id(self, type_id: str):
        """Sets the type_id of this DaodSeedLinkData.

        The type identifier of the record identified by **seedId**.  # noqa: E501

        :param type_id: The type_id of this DaodSeedLinkData.
        :type type_id: str
        """
        if type_id is None:
            raise ValueError("Invalid value for `type_id`, must not be `None`")  # noqa: E501

        self._type_id = type_id

    @property
    def label(self) -> str:
        """Gets the label of this DaodSeedLinkData.

        The label of the record identified by **seedId**.  # noqa: E501

        :return: The label of this DaodSeedLinkData.
        :rtype: str
        """
        return self._label

    @label.setter
    def label(self, label: str):
        """Sets the label of this DaodSeedLinkData.

        The label of the record identified by **seedId**.  # noqa: E501

        :param label: The label of this DaodSeedLinkData.
        :type label: str
        """

        self._label = label

    @property
    def properties(self) -> Dict[str, object]:
        """Gets the properties of this DaodSeedLinkData.

        The property data of the record identified by **seedId**.  # noqa: E501

        :return: The properties of this DaodSeedLinkData.
        :rtype: Dict[str, object]
        """
        return self._properties

    @properties.setter
    def properties(self, properties: Dict[str, object]):
        """Sets the properties of this DaodSeedLinkData.

        The property data of the record identified by **seedId**.  # noqa: E501

        :param properties: The properties of this DaodSeedLinkData.
        :type properties: Dict[str, object]
        """

        self._properties = properties

    @property
    def access_dimension_values(self) -> List[SecurityDimensionAndValues]:
        """Gets the access_dimension_values of this DaodSeedLinkData.

        The security dimension values of the record identified by **seedId**.  # noqa: E501

        :return: The access_dimension_values of this DaodSeedLinkData.
        :rtype: List[SecurityDimensionAndValues]
        """
        return self._access_dimension_values

    @access_dimension_values.setter
    def access_dimension_values(self, access_dimension_values: List[SecurityDimensionAndValues]):
        """Sets the access_dimension_values of this DaodSeedLinkData.

        The security dimension values of the record identified by **seedId**.  # noqa: E501

        :param access_dimension_values: The access_dimension_values of this DaodSeedLinkData.
        :type access_dimension_values: List[SecurityDimensionAndValues]
        """

        self._access_dimension_values = access_dimension_values

    @property
    def extensions(self) -> Dict[str, object]:
        """Gets the extensions of this DaodSeedLinkData.

        Free-form, custom information for the record identified by **seedId**.  # noqa: E501

        :return: The extensions of this DaodSeedLinkData.
        :rtype: Dict[str, object]
        """
        return self._extensions

    @extensions.setter
    def extensions(self, extensions: Dict[str, object]):
        """Sets the extensions of this DaodSeedLinkData.

        Free-form, custom information for the record identified by **seedId**.  # noqa: E501

        :param extensions: The extensions of this DaodSeedLinkData.
        :type extensions: Dict[str, object]
        """

        self._extensions = extensions

    @property
    def from_end_id(self) -> object:
        """Gets the from_end_id of this DaodSeedLinkData.

        The identifier of the record at the 'from' end of the link record identified by **seedId**.  # noqa: E501

        :return: The from_end_id of this DaodSeedLinkData.
        :rtype: object
        """
        return self._from_end_id

    @from_end_id.setter
    def from_end_id(self, from_end_id: object):
        """Sets the from_end_id of this DaodSeedLinkData.

        The identifier of the record at the 'from' end of the link record identified by **seedId**.  # noqa: E501

        :param from_end_id: The from_end_id of this DaodSeedLinkData.
        :type from_end_id: object
        """
        if from_end_id is None:
            raise ValueError("Invalid value for `from_end_id`, must not be `None`")  # noqa: E501

        self._from_end_id = from_end_id

    @property
    def from_end_type_id(self) -> str:
        """Gets the from_end_type_id of this DaodSeedLinkData.

        The type identifier of the record at the 'from' end of the link record identified by **seedId**.  # noqa: E501

        :return: The from_end_type_id of this DaodSeedLinkData.
        :rtype: str
        """
        return self._from_end_type_id

    @from_end_type_id.setter
    def from_end_type_id(self, from_end_type_id: str):
        """Sets the from_end_type_id of this DaodSeedLinkData.

        The type identifier of the record at the 'from' end of the link record identified by **seedId**.  # noqa: E501

        :param from_end_type_id: The from_end_type_id of this DaodSeedLinkData.
        :type from_end_type_id: str
        """
        if from_end_type_id is None:
            raise ValueError("Invalid value for `from_end_type_id`, must not be `None`")  # noqa: E501

        self._from_end_type_id = from_end_type_id

    @property
    def link_direction(self) -> LinkDirection:
        """Gets the link_direction of this DaodSeedLinkData.


        :return: The link_direction of this DaodSeedLinkData.
        :rtype: LinkDirection
        """
        return self._link_direction

    @link_direction.setter
    def link_direction(self, link_direction: LinkDirection):
        """Sets the link_direction of this DaodSeedLinkData.


        :param link_direction: The link_direction of this DaodSeedLinkData.
        :type link_direction: LinkDirection
        """
        if link_direction is None:
            raise ValueError("Invalid value for `link_direction`, must not be `None`")  # noqa: E501

        self._link_direction = link_direction

    @property
    def to_end_id(self) -> object:
        """Gets the to_end_id of this DaodSeedLinkData.

        The identifier of the record at the 'to' end of the link record identified by **seedId**.  # noqa: E501

        :return: The to_end_id of this DaodSeedLinkData.
        :rtype: object
        """
        return self._to_end_id

    @to_end_id.setter
    def to_end_id(self, to_end_id: object):
        """Sets the to_end_id of this DaodSeedLinkData.

        The identifier of the record at the 'to' end of the link record identified by **seedId**.  # noqa: E501

        :param to_end_id: The to_end_id of this DaodSeedLinkData.
        :type to_end_id: object
        """
        if to_end_id is None:
            raise ValueError("Invalid value for `to_end_id`, must not be `None`")  # noqa: E501

        self._to_end_id = to_end_id

    @property
    def to_end_type_id(self) -> str:
        """Gets the to_end_type_id of this DaodSeedLinkData.

        The type identifier of the record at the 'to' end of the link record identified by **seedId**.  # noqa: E501

        :return: The to_end_type_id of this DaodSeedLinkData.
        :rtype: str
        """
        return self._to_end_type_id

    @to_end_type_id.setter
    def to_end_type_id(self, to_end_type_id: str):
        """Sets the to_end_type_id of this DaodSeedLinkData.

        The type identifier of the record at the 'to' end of the link record identified by **seedId**.  # noqa: E501

        :param to_end_type_id: The to_end_type_id of this DaodSeedLinkData.
        :type to_end_type_id: str
        """
        if to_end_type_id is None:
            raise ValueError("Invalid value for `to_end_type_id`, must not be `None`")  # noqa: E501

        self._to_end_type_id = to_end_type_id

    @property
    def type_location(self) -> TypeLocation:
        """Gets the type_location of this DaodSeedLinkData.


        :return: The type_location of this DaodSeedLinkData.
        :rtype: TypeLocation
        """
        return self._type_location

    @type_location.setter
    def type_location(self, type_location: TypeLocation):
        """Sets the type_location of this DaodSeedLinkData.


        :param type_location: The type_location of this DaodSeedLinkData.
        :type type_location: TypeLocation
        """

        self._type_location = type_location
