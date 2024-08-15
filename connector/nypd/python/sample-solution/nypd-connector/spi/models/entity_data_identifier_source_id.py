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
from spi.models.source_identifier import SourceIdentifier
from spi import util


class EntityDataIdentifierSourceId(Model):

    def __init__(self, source_id=None):  # noqa: E501
        """EntityDataIdentifierSourceId

        :param source_id: The source_id of this EntityDataIdentifierSourceId.  # noqa: E501
        :type source_id: SourceIdentifier
        """
        self.openapi_types = {
            'source_id': SourceIdentifier
        }

        self.attribute_map = {
            'source_id': 'sourceId'
        }

        self._source_id = source_id

    @classmethod
    def from_dict(cls, dikt) -> 'EntityDataIdentifierSourceId':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The EntityDataIdentifierSourceId of this EntityDataIdentifierSourceId.  # noqa: E501
        :rtype: EntityDataIdentifierSourceId
        """
        return util.deserialize_model(dikt, cls)

    @property
    def source_id(self) -> SourceIdentifier:
        """Gets the source_id of this EntityDataIdentifierSourceId.


        :return: The source_id of this EntityDataIdentifierSourceId.
        :rtype: SourceIdentifier
        """
        return self._source_id

    @source_id.setter
    def source_id(self, source_id: SourceIdentifier):
        """Sets the source_id of this EntityDataIdentifierSourceId.


        :param source_id: The source_id of this EntityDataIdentifierSourceId.
        :type source_id: SourceIdentifier
        """
        if source_id is None:
            raise ValueError("Invalid value for `source_id`, must not be `None`")  # noqa: E501

        self._source_id = source_id
