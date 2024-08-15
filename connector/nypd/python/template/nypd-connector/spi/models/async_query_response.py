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


class AsyncQueryResponse(Model):

    def __init__(self, query_id=None):  # noqa: E501
        """AsyncQueryResponse

        :param query_id: The query_id of this AsyncQueryResponse.  # noqa: E501
        :type query_id: str
        """
        self.openapi_types = {
            'query_id': str
        }

        self.attribute_map = {
            'query_id': 'queryId'
        }

        self._query_id = query_id

    @classmethod
    def from_dict(cls, dikt) -> 'AsyncQueryResponse':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The AsyncQueryResponse of this AsyncQueryResponse.  # noqa: E501
        :rtype: AsyncQueryResponse
        """
        return util.deserialize_model(dikt, cls)

    @property
    def query_id(self) -> str:
        """Gets the query_id of this AsyncQueryResponse.

        The identifier of the query.  # noqa: E501

        :return: The query_id of this AsyncQueryResponse.
        :rtype: str
        """
        return self._query_id

    @query_id.setter
    def query_id(self, query_id: str):
        """Sets the query_id of this AsyncQueryResponse.

        The identifier of the query.  # noqa: E501

        :param query_id: The query_id of this AsyncQueryResponse.
        :type query_id: str
        """
        if query_id is None:
            raise ValueError("Invalid value for `query_id`, must not be `None`")  # noqa: E501

        self._query_id = query_id
