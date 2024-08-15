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
from spi import util


class ConnectorDefaultValues(Model):

    def __init__(self, entity_type_id=None, entity_type_location=None, link_direction=None, link_type_id=None, link_type_location=None, result_ids_persistent=None, time_zone_id=None):  # noqa: E501
        """ConnectorDefaultValues

        :param entity_type_id: The entity_type_id of this ConnectorDefaultValues.  # noqa: E501
        :type entity_type_id: str
        :param entity_type_location: The entity_type_location of this ConnectorDefaultValues.  # noqa: E501
        :type entity_type_location: SchemaTypeLocation
        :param link_direction: The link_direction of this ConnectorDefaultValues.  # noqa: E501
        :type link_direction: LinkDirection
        :param link_type_id: The link_type_id of this ConnectorDefaultValues.  # noqa: E501
        :type link_type_id: str
        :param link_type_location: The link_type_location of this ConnectorDefaultValues.  # noqa: E501
        :type link_type_location: SchemaTypeLocation
        :param result_ids_persistent: The result_ids_persistent of this ConnectorDefaultValues.  # noqa: E501
        :type result_ids_persistent: bool
        :param time_zone_id: The time_zone_id of this ConnectorDefaultValues.  # noqa: E501
        :type time_zone_id: str
        """
        self.openapi_types = {
            'entity_type_id': str,
            'entity_type_location': SchemaTypeLocation,
            'link_direction': LinkDirection,
            'link_type_id': str,
            'link_type_location': SchemaTypeLocation,
            'result_ids_persistent': bool,
            'time_zone_id': str
        }

        self.attribute_map = {
            'entity_type_id': 'entityTypeId',
            'entity_type_location': 'entityTypeLocation',
            'link_direction': 'linkDirection',
            'link_type_id': 'linkTypeId',
            'link_type_location': 'linkTypeLocation',
            'result_ids_persistent': 'resultIdsPersistent',
            'time_zone_id': 'timeZoneId'
        }

        self._entity_type_id = entity_type_id
        self._entity_type_location = entity_type_location
        self._link_direction = link_direction
        self._link_type_id = link_type_id
        self._link_type_location = link_type_location
        self._result_ids_persistent = result_ids_persistent
        self._time_zone_id = time_zone_id

    @classmethod
    def from_dict(cls, dikt) -> 'ConnectorDefaultValues':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The ConnectorDefaultValues of this ConnectorDefaultValues.  # noqa: E501
        :rtype: ConnectorDefaultValues
        """
        return util.deserialize_model(dikt, cls)

    @property
    def entity_type_id(self) -> str:
        """Gets the entity_type_id of this ConnectorDefaultValues.

        The identifier of the default entity type to apply to retrieved data.  # noqa: E501

        :return: The entity_type_id of this ConnectorDefaultValues.
        :rtype: str
        """
        return self._entity_type_id

    @entity_type_id.setter
    def entity_type_id(self, entity_type_id: str):
        """Sets the entity_type_id of this ConnectorDefaultValues.

        The identifier of the default entity type to apply to retrieved data.  # noqa: E501

        :param entity_type_id: The entity_type_id of this ConnectorDefaultValues.
        :type entity_type_id: str
        """

        self._entity_type_id = entity_type_id

    @property
    def entity_type_location(self) -> SchemaTypeLocation:
        """Gets the entity_type_location of this ConnectorDefaultValues.


        :return: The entity_type_location of this ConnectorDefaultValues.
        :rtype: SchemaTypeLocation
        """
        return self._entity_type_location

    @entity_type_location.setter
    def entity_type_location(self, entity_type_location: SchemaTypeLocation):
        """Sets the entity_type_location of this ConnectorDefaultValues.


        :param entity_type_location: The entity_type_location of this ConnectorDefaultValues.
        :type entity_type_location: SchemaTypeLocation
        """

        self._entity_type_location = entity_type_location

    @property
    def link_direction(self) -> LinkDirection:
        """Gets the link_direction of this ConnectorDefaultValues.


        :return: The link_direction of this ConnectorDefaultValues.
        :rtype: LinkDirection
        """
        return self._link_direction

    @link_direction.setter
    def link_direction(self, link_direction: LinkDirection):
        """Sets the link_direction of this ConnectorDefaultValues.


        :param link_direction: The link_direction of this ConnectorDefaultValues.
        :type link_direction: LinkDirection
        """

        self._link_direction = link_direction

    @property
    def link_type_id(self) -> str:
        """Gets the link_type_id of this ConnectorDefaultValues.

        The identifier of the default link type to apply to retrieved data.  # noqa: E501

        :return: The link_type_id of this ConnectorDefaultValues.
        :rtype: str
        """
        return self._link_type_id

    @link_type_id.setter
    def link_type_id(self, link_type_id: str):
        """Sets the link_type_id of this ConnectorDefaultValues.

        The identifier of the default link type to apply to retrieved data.  # noqa: E501

        :param link_type_id: The link_type_id of this ConnectorDefaultValues.
        :type link_type_id: str
        """

        self._link_type_id = link_type_id

    @property
    def link_type_location(self) -> SchemaTypeLocation:
        """Gets the link_type_location of this ConnectorDefaultValues.


        :return: The link_type_location of this ConnectorDefaultValues.
        :rtype: SchemaTypeLocation
        """
        return self._link_type_location

    @link_type_location.setter
    def link_type_location(self, link_type_location: SchemaTypeLocation):
        """Sets the link_type_location of this ConnectorDefaultValues.


        :param link_type_location: The link_type_location of this ConnectorDefaultValues.
        :type link_type_location: SchemaTypeLocation
        """

        self._link_type_location = link_type_location

    @property
    def result_ids_persistent(self) -> bool:
        """Gets the result_ids_persistent of this ConnectorDefaultValues.

        The default indicator of whether identifiers for the same retrieved data are (**true**) or are not (**false**) persistent from one set of results to the next.  # noqa: E501

        :return: The result_ids_persistent of this ConnectorDefaultValues.
        :rtype: bool
        """
        return self._result_ids_persistent

    @result_ids_persistent.setter
    def result_ids_persistent(self, result_ids_persistent: bool):
        """Sets the result_ids_persistent of this ConnectorDefaultValues.

        The default indicator of whether identifiers for the same retrieved data are (**true**) or are not (**false**) persistent from one set of results to the next.  # noqa: E501

        :param result_ids_persistent: The result_ids_persistent of this ConnectorDefaultValues.
        :type result_ids_persistent: bool
        """

        self._result_ids_persistent = result_ids_persistent

    @property
    def time_zone_id(self) -> str:
        """Gets the time_zone_id of this ConnectorDefaultValues.

        The identifier of the default time zone to apply to retrieved data, which itself defaults to UTC if no value is specified.  # noqa: E501

        :return: The time_zone_id of this ConnectorDefaultValues.
        :rtype: str
        """
        return self._time_zone_id

    @time_zone_id.setter
    def time_zone_id(self, time_zone_id: str):
        """Sets the time_zone_id of this ConnectorDefaultValues.

        The identifier of the default time zone to apply to retrieved data, which itself defaults to UTC if no value is specified.  # noqa: E501

        :param time_zone_id: The time_zone_id of this ConnectorDefaultValues.
        :type time_zone_id: str
        """

        self._time_zone_id = time_zone_id
