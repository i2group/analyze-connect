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


class ModelAsync(Model):

    def __init__(self, polling_interval_in_seconds=None, queries_resource=None):  # noqa: E501
        """ModelAsync

        :param polling_interval_in_seconds: The polling_interval_in_seconds of this ModelAsync.  # noqa: E501
        :type polling_interval_in_seconds: int
        :param queries_resource: The queries_resource of this ModelAsync.  # noqa: E501
        :type queries_resource: str
        """
        self.openapi_types = {
            'polling_interval_in_seconds': int,
            'queries_resource': str
        }

        self.attribute_map = {
            'polling_interval_in_seconds': 'pollingIntervalInSeconds',
            'queries_resource': 'queriesResource'
        }

        self._polling_interval_in_seconds = polling_interval_in_seconds
        self._queries_resource = queries_resource

    @classmethod
    def from_dict(cls, dikt) -> 'ModelAsync':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The Async of this ModelAsync.  # noqa: E501
        :rtype: ModelAsync
        """
        return util.deserialize_model(dikt, cls)

    @property
    def polling_interval_in_seconds(self) -> int:
        """Gets the polling_interval_in_seconds of this ModelAsync.

        The preferred period with which the i2 Connect gateway should poll endpoints for changes.  # noqa: E501

        :return: The polling_interval_in_seconds of this ModelAsync.
        :rtype: int
        """
        return self._polling_interval_in_seconds

    @polling_interval_in_seconds.setter
    def polling_interval_in_seconds(self, polling_interval_in_seconds: int):
        """Sets the polling_interval_in_seconds of this ModelAsync.

        The preferred period with which the i2 Connect gateway should poll endpoints for changes.  # noqa: E501

        :param polling_interval_in_seconds: The polling_interval_in_seconds of this ModelAsync.
        :type polling_interval_in_seconds: int
        """
        if polling_interval_in_seconds is not None and polling_interval_in_seconds < 1:  # noqa: E501
            raise ValueError("Invalid value for `polling_interval_in_seconds`, must be a value greater than or equal to `1`")  # noqa: E501

        self._polling_interval_in_seconds = polling_interval_in_seconds

    @property
    def queries_resource(self) -> str:
        """Gets the queries_resource of this ModelAsync.

        The base URL of the endpoints for the request mechanism to use.  # noqa: E501

        :return: The queries_resource of this ModelAsync.
        :rtype: str
        """
        return self._queries_resource

    @queries_resource.setter
    def queries_resource(self, queries_resource: str):
        """Sets the queries_resource of this ModelAsync.

        The base URL of the endpoints for the request mechanism to use.  # noqa: E501

        :param queries_resource: The queries_resource of this ModelAsync.
        :type queries_resource: str
        """

        self._queries_resource = queries_resource
