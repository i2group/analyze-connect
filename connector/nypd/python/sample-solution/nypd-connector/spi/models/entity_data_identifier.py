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
from spi.models.entity_data_identifier_source_id import EntityDataIdentifierSourceId
from spi.models.info_store_identifier import InfoStoreIdentifier
from spi.models.source_identifier import SourceIdentifier
from spi import util


class EntityDataIdentifier(Model):

    def __init__(self, source_id=None, info_store_record_id=None):  # noqa: E501
        """EntityDataIdentifier

        :param source_id: The source_id of this EntityDataIdentifier.  # noqa: E501
        :type source_id: SourceIdentifier
        :param info_store_record_id: The info_store_record_id of this EntityDataIdentifier.  # noqa: E501
        :type info_store_record_id: str
        """
        self.openapi_types = {
            'source_id': SourceIdentifier,
            'info_store_record_id': str
        }

        self.attribute_map = {
            'source_id': 'sourceId',
            'info_store_record_id': 'infoStoreRecordId'
        }

        self._source_id = source_id
        self._info_store_record_id = info_store_record_id

    @classmethod
    def from_dict(cls, dikt) -> 'EntityDataIdentifier':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The EntityDataIdentifier of this EntityDataIdentifier.  # noqa: E501
        :rtype: EntityDataIdentifier
        """
        return util.deserialize_model(dikt, cls)

    @property
    def source_id(self) -> SourceIdentifier:
        """Gets the source_id of this EntityDataIdentifier.


        :return: The source_id of this EntityDataIdentifier.
        :rtype: SourceIdentifier
        """
        return self._source_id

    @source_id.setter
    def source_id(self, source_id: SourceIdentifier):
        """Sets the source_id of this EntityDataIdentifier.


        :param source_id: The source_id of this EntityDataIdentifier.
        :type source_id: SourceIdentifier
        """
        if source_id is None:
            raise ValueError("Invalid value for `source_id`, must not be `None`")  # noqa: E501

        self._source_id = source_id

    @property
    def info_store_record_id(self) -> str:
        """Gets the info_store_record_id of this EntityDataIdentifier.

        The record identifier of an Information Store record.  # noqa: E501

        :return: The info_store_record_id of this EntityDataIdentifier.
        :rtype: str
        """
        return self._info_store_record_id

    @info_store_record_id.setter
    def info_store_record_id(self, info_store_record_id: str):
        """Sets the info_store_record_id of this EntityDataIdentifier.

        The record identifier of an Information Store record.  # noqa: E501

        :param info_store_record_id: The info_store_record_id of this EntityDataIdentifier.
        :type info_store_record_id: str
        """
        if info_store_record_id is None:
            raise ValueError("Invalid value for `info_store_record_id`, must not be `None`")  # noqa: E501

        self._info_store_record_id = info_store_record_id
