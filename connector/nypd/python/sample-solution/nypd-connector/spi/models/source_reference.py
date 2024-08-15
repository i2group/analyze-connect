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
from spi.models.source_reference_info import SourceReferenceInfo
from spi import util


class SourceReference(Model):

    def __init__(self, source=None):  # noqa: E501
        """SourceReference

        :param source: The source of this SourceReference.  # noqa: E501
        :type source: SourceReferenceInfo
        """
        self.openapi_types = {
            'source': SourceReferenceInfo
        }

        self.attribute_map = {
            'source': 'source'
        }

        self._source = source

    @classmethod
    def from_dict(cls, dikt) -> 'SourceReference':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The SourceReference of this SourceReference.  # noqa: E501
        :rtype: SourceReference
        """
        return util.deserialize_model(dikt, cls)

    @property
    def source(self) -> SourceReferenceInfo:
        """Gets the source of this SourceReference.


        :return: The source of this SourceReference.
        :rtype: SourceReferenceInfo
        """
        return self._source

    @source.setter
    def source(self, source: SourceReferenceInfo):
        """Sets the source of this SourceReference.


        :param source: The source of this SourceReference.
        :type source: SourceReferenceInfo
        """
        if source is None:
            raise ValueError("Invalid value for `source`, must not be `None`")  # noqa: E501

        self._source = source
