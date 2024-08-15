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


class MaxStringLength(Model):

    def __init__(self, size=None, units='utf16codeunits'):  # noqa: E501
        """MaxStringLength

        :param size: The size of this MaxStringLength.  # noqa: E501
        :type size: int
        :param units: The units of this MaxStringLength.  # noqa: E501
        :type units: str
        """
        self.openapi_types = {
            'size': int,
            'units': str
        }

        self.attribute_map = {
            'size': 'size',
            'units': 'units'
        }

        self._size = size
        self._units = units

    @classmethod
    def from_dict(cls, dikt) -> 'MaxStringLength':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The MaxStringLength of this MaxStringLength.  # noqa: E501
        :rtype: MaxStringLength
        """
        return util.deserialize_model(dikt, cls)

    @property
    def size(self) -> int:
        """Gets the size of this MaxStringLength.

        The size of the longest permitted string.  # noqa: E501

        :return: The size of this MaxStringLength.
        :rtype: int
        """
        return self._size

    @size.setter
    def size(self, size: int):
        """Sets the size of this MaxStringLength.

        The size of the longest permitted string.  # noqa: E501

        :param size: The size of this MaxStringLength.
        :type size: int
        """
        if size is None:
            raise ValueError("Invalid value for `size`, must not be `None`")  # noqa: E501

        self._size = size

    @property
    def units(self) -> str:
        """Gets the units of this MaxStringLength.

        The units in which **size** is interpreted.  # noqa: E501

        :return: The units of this MaxStringLength.
        :rtype: str
        """
        return self._units

    @units.setter
    def units(self, units: str):
        """Sets the units of this MaxStringLength.

        The units in which **size** is interpreted.  # noqa: E501

        :param units: The units of this MaxStringLength.
        :type units: str
        """
        allowed_values = ["utf16codeunits", "utf8bytes"]  # noqa: E501
        if units not in allowed_values:
            raise ValueError(
                "Invalid value for `units` ({0}), must be one of {1}"
                .format(units, allowed_values)
            )

        self._units = units
