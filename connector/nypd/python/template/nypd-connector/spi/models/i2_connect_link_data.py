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
from spi.models.schema_type_location import SchemaTypeLocation
from spi.models.source_reference import SourceReference
from spi import util


class I2ConnectLinkData(Model):

    def __init__(self, id=None, properties=None, source_reference=None, type_id=None, type_location=None, version=None, from_end_id=None, to_end_id=None, link_direction=None):  # noqa: E501
        """I2ConnectLinkData

        :param id: The id of this I2ConnectLinkData.  # noqa: E501
        :type id: str
        :param properties: The properties of this I2ConnectLinkData.  # noqa: E501
        :type properties: Dict[str, object]
        :param source_reference: The source_reference of this I2ConnectLinkData.  # noqa: E501
        :type source_reference: SourceReference
        :param type_id: The type_id of this I2ConnectLinkData.  # noqa: E501
        :type type_id: str
        :param type_location: The type_location of this I2ConnectLinkData.  # noqa: E501
        :type type_location: SchemaTypeLocation
        :param version: The version of this I2ConnectLinkData.  # noqa: E501
        :type version: int
        :param from_end_id: The from_end_id of this I2ConnectLinkData.  # noqa: E501
        :type from_end_id: str
        :param to_end_id: The to_end_id of this I2ConnectLinkData.  # noqa: E501
        :type to_end_id: str
        :param link_direction: The link_direction of this I2ConnectLinkData.  # noqa: E501
        :type link_direction: LinkDirection
        """
        self.openapi_types = {
            'id': str,
            'properties': Dict[str, object],
            'source_reference': SourceReference,
            'type_id': str,
            'type_location': SchemaTypeLocation,
            'version': int,
            'from_end_id': str,
            'to_end_id': str,
            'link_direction': LinkDirection
        }

        self.attribute_map = {
            'id': 'id',
            'properties': 'properties',
            'source_reference': 'sourceReference',
            'type_id': 'typeId',
            'type_location': 'typeLocation',
            'version': 'version',
            'from_end_id': 'fromEndId',
            'to_end_id': 'toEndId',
            'link_direction': 'linkDirection'
        }

        self._id = id
        self._properties = properties
        self._source_reference = source_reference
        self._type_id = type_id
        self._type_location = type_location
        self._version = version
        self._from_end_id = from_end_id
        self._to_end_id = to_end_id
        self._link_direction = link_direction

    @classmethod
    def from_dict(cls, dikt) -> 'I2ConnectLinkData':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The I2ConnectLinkData of this I2ConnectLinkData.  # noqa: E501
        :rtype: I2ConnectLinkData
        """
        return util.deserialize_model(dikt, cls)

    @property
    def id(self) -> str:
        """Gets the id of this I2ConnectLinkData.


        :return: The id of this I2ConnectLinkData.
        :rtype: str
        """
        return self._id

    @id.setter
    def id(self, id: str):
        """Sets the id of this I2ConnectLinkData.


        :param id: The id of this I2ConnectLinkData.
        :type id: str
        """
        if id is None:
            raise ValueError("Invalid value for `id`, must not be `None`")  # noqa: E501

        self._id = id

    @property
    def properties(self) -> Dict[str, object]:
        """Gets the properties of this I2ConnectLinkData.

        The property data for a record.  # noqa: E501

        :return: The properties of this I2ConnectLinkData.
        :rtype: Dict[str, object]
        """
        return self._properties

    @properties.setter
    def properties(self, properties: Dict[str, object]):
        """Sets the properties of this I2ConnectLinkData.

        The property data for a record.  # noqa: E501

        :param properties: The properties of this I2ConnectLinkData.
        :type properties: Dict[str, object]
        """

        self._properties = properties

    @property
    def source_reference(self) -> SourceReference:
        """Gets the source_reference of this I2ConnectLinkData.


        :return: The source_reference of this I2ConnectLinkData.
        :rtype: SourceReference
        """
        return self._source_reference

    @source_reference.setter
    def source_reference(self, source_reference: SourceReference):
        """Sets the source_reference of this I2ConnectLinkData.


        :param source_reference: The source_reference of this I2ConnectLinkData.
        :type source_reference: SourceReference
        """

        self._source_reference = source_reference

    @property
    def type_id(self) -> str:
        """Gets the type_id of this I2ConnectLinkData.

        The type identifier for a record.  # noqa: E501

        :return: The type_id of this I2ConnectLinkData.
        :rtype: str
        """
        return self._type_id

    @type_id.setter
    def type_id(self, type_id: str):
        """Sets the type_id of this I2ConnectLinkData.

        The type identifier for a record.  # noqa: E501

        :param type_id: The type_id of this I2ConnectLinkData.
        :type type_id: str
        """

        self._type_id = type_id

    @property
    def type_location(self) -> SchemaTypeLocation:
        """Gets the type_location of this I2ConnectLinkData.


        :return: The type_location of this I2ConnectLinkData.
        :rtype: SchemaTypeLocation
        """
        return self._type_location

    @type_location.setter
    def type_location(self, type_location: SchemaTypeLocation):
        """Sets the type_location of this I2ConnectLinkData.


        :param type_location: The type_location of this I2ConnectLinkData.
        :type type_location: SchemaTypeLocation
        """

        self._type_location = type_location

    @property
    def version(self) -> int:
        """Gets the version of this I2ConnectLinkData.

        The version of the record.  # noqa: E501

        :return: The version of this I2ConnectLinkData.
        :rtype: int
        """
        return self._version

    @version.setter
    def version(self, version: int):
        """Sets the version of this I2ConnectLinkData.

        The version of the record.  # noqa: E501

        :param version: The version of this I2ConnectLinkData.
        :type version: int
        """

        self._version = version

    @property
    def from_end_id(self) -> str:
        """Gets the from_end_id of this I2ConnectLinkData.


        :return: The from_end_id of this I2ConnectLinkData.
        :rtype: str
        """
        return self._from_end_id

    @from_end_id.setter
    def from_end_id(self, from_end_id: str):
        """Sets the from_end_id of this I2ConnectLinkData.


        :param from_end_id: The from_end_id of this I2ConnectLinkData.
        :type from_end_id: str
        """

        self._from_end_id = from_end_id

    @property
    def to_end_id(self) -> str:
        """Gets the to_end_id of this I2ConnectLinkData.


        :return: The to_end_id of this I2ConnectLinkData.
        :rtype: str
        """
        return self._to_end_id

    @to_end_id.setter
    def to_end_id(self, to_end_id: str):
        """Sets the to_end_id of this I2ConnectLinkData.


        :param to_end_id: The to_end_id of this I2ConnectLinkData.
        :type to_end_id: str
        """

        self._to_end_id = to_end_id

    @property
    def link_direction(self) -> LinkDirection:
        """Gets the link_direction of this I2ConnectLinkData.


        :return: The link_direction of this I2ConnectLinkData.
        :rtype: LinkDirection
        """
        return self._link_direction

    @link_direction.setter
    def link_direction(self, link_direction: LinkDirection):
        """Sets the link_direction of this I2ConnectLinkData.


        :param link_direction: The link_direction of this I2ConnectLinkData.
        :type link_direction: LinkDirection
        """

        self._link_direction = link_direction
