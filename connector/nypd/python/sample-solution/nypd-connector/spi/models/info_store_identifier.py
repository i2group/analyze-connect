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


class InfoStoreIdentifier(Model):

    def __init__(self, info_store_record_id=None):  # noqa: E501
        """InfoStoreIdentifier

        :param info_store_record_id: The info_store_record_id of this InfoStoreIdentifier.  # noqa: E501
        :type info_store_record_id: str
        """
        self.openapi_types = {
            'info_store_record_id': str
        }

        self.attribute_map = {
            'info_store_record_id': 'infoStoreRecordId'
        }

        self._info_store_record_id = info_store_record_id

    @classmethod
    def from_dict(cls, dikt) -> 'InfoStoreIdentifier':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The InfoStoreIdentifier of this InfoStoreIdentifier.  # noqa: E501
        :rtype: InfoStoreIdentifier
        """
        return util.deserialize_model(dikt, cls)

    @property
    def info_store_record_id(self) -> str:
        """Gets the info_store_record_id of this InfoStoreIdentifier.

        The record identifier of an Information Store record.  # noqa: E501

        :return: The info_store_record_id of this InfoStoreIdentifier.
        :rtype: str
        """
        return self._info_store_record_id

    @info_store_record_id.setter
    def info_store_record_id(self, info_store_record_id: str):
        """Sets the info_store_record_id of this InfoStoreIdentifier.

        The record identifier of an Information Store record.  # noqa: E501

        :param info_store_record_id: The info_store_record_id of this InfoStoreIdentifier.
        :type info_store_record_id: str
        """
        if info_store_record_id is None:
            raise ValueError("Invalid value for `info_store_record_id`, must not be `None`")  # noqa: E501

        self._info_store_record_id = info_store_record_id
