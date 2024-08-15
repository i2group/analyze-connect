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


class DateAndTime(Model):

    def __init__(self, is_dst=None, local_date_and_time=None, time_zone_id=None):  # noqa: E501
        """DateAndTime

        :param is_dst: The is_dst of this DateAndTime.  # noqa: E501
        :type is_dst: bool
        :param local_date_and_time: The local_date_and_time of this DateAndTime.  # noqa: E501
        :type local_date_and_time: str
        :param time_zone_id: The time_zone_id of this DateAndTime.  # noqa: E501
        :type time_zone_id: str
        """
        self.openapi_types = {
            'is_dst': bool,
            'local_date_and_time': str,
            'time_zone_id': str
        }

        self.attribute_map = {
            'is_dst': 'isDST',
            'local_date_and_time': 'localDateAndTime',
            'time_zone_id': 'timeZoneId'
        }

        self._is_dst = is_dst
        self._local_date_and_time = local_date_and_time
        self._time_zone_id = time_zone_id

    @classmethod
    def from_dict(cls, dikt) -> 'DateAndTime':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The DateAndTime of this DateAndTime.  # noqa: E501
        :rtype: DateAndTime
        """
        return util.deserialize_model(dikt, cls)

    @property
    def is_dst(self) -> bool:
        """Gets the is_dst of this DateAndTime.

        Indicates whether daylight saving time is (**true**) or is not (**false**) in effect for the value in **localDateAndTime**.  # noqa: E501

        :return: The is_dst of this DateAndTime.
        :rtype: bool
        """
        return self._is_dst

    @is_dst.setter
    def is_dst(self, is_dst: bool):
        """Sets the is_dst of this DateAndTime.

        Indicates whether daylight saving time is (**true**) or is not (**false**) in effect for the value in **localDateAndTime**.  # noqa: E501

        :param is_dst: The is_dst of this DateAndTime.
        :type is_dst: bool
        """
        if is_dst is None:
            raise ValueError("Invalid value for `is_dst`, must not be `None`")  # noqa: E501

        self._is_dst = is_dst

    @property
    def local_date_and_time(self) -> str:
        """Gets the local_date_and_time of this DateAndTime.

        The local date and time, formatted as an ISO 8601 string.  # noqa: E501

        :return: The local_date_and_time of this DateAndTime.
        :rtype: str
        """
        return self._local_date_and_time

    @local_date_and_time.setter
    def local_date_and_time(self, local_date_and_time: str):
        """Sets the local_date_and_time of this DateAndTime.

        The local date and time, formatted as an ISO 8601 string.  # noqa: E501

        :param local_date_and_time: The local_date_and_time of this DateAndTime.
        :type local_date_and_time: str
        """
        if local_date_and_time is None:
            raise ValueError("Invalid value for `local_date_and_time`, must not be `None`")  # noqa: E501

        self._local_date_and_time = local_date_and_time

    @property
    def time_zone_id(self) -> str:
        """Gets the time_zone_id of this DateAndTime.

        The ISO 8601 identifier for the time zone of the value in **localDateAndTime**.  # noqa: E501

        :return: The time_zone_id of this DateAndTime.
        :rtype: str
        """
        return self._time_zone_id

    @time_zone_id.setter
    def time_zone_id(self, time_zone_id: str):
        """Sets the time_zone_id of this DateAndTime.

        The ISO 8601 identifier for the time zone of the value in **localDateAndTime**.  # noqa: E501

        :param time_zone_id: The time_zone_id of this DateAndTime.
        :type time_zone_id: str
        """
        if time_zone_id is None:
            raise ValueError("Invalid value for `time_zone_id`, must not be `None`")  # noqa: E501

        self._time_zone_id = time_zone_id
