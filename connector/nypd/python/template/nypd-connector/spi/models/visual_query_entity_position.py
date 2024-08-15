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


class VisualQueryEntityPosition(Model):

    def __init__(self, x=None, y=None):  # noqa: E501
        """VisualQueryEntityPosition

        :param x: The x of this VisualQueryEntityPosition.  # noqa: E501
        :type x: float
        :param y: The y of this VisualQueryEntityPosition.  # noqa: E501
        :type y: float
        """
        self.openapi_types = {
            'x': float,
            'y': float
        }

        self.attribute_map = {
            'x': 'x',
            'y': 'y'
        }

        self._x = x
        self._y = y

    @classmethod
    def from_dict(cls, dikt) -> 'VisualQueryEntityPosition':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The VisualQueryEntityPosition of this VisualQueryEntityPosition.  # noqa: E501
        :rtype: VisualQueryEntityPosition
        """
        return util.deserialize_model(dikt, cls)

    @property
    def x(self) -> float:
        """Gets the x of this VisualQueryEntityPosition.

        A coordinate or offset on the x-axis.  # noqa: E501

        :return: The x of this VisualQueryEntityPosition.
        :rtype: float
        """
        return self._x

    @x.setter
    def x(self, x: float):
        """Sets the x of this VisualQueryEntityPosition.

        A coordinate or offset on the x-axis.  # noqa: E501

        :param x: The x of this VisualQueryEntityPosition.
        :type x: float
        """
        if x is None:
            raise ValueError("Invalid value for `x`, must not be `None`")  # noqa: E501

        self._x = x

    @property
    def y(self) -> float:
        """Gets the y of this VisualQueryEntityPosition.

        A coordinate or offset on the y-axis.  # noqa: E501

        :return: The y of this VisualQueryEntityPosition.
        :rtype: float
        """
        return self._y

    @y.setter
    def y(self, y: float):
        """Sets the y of this VisualQueryEntityPosition.

        A coordinate or offset on the y-axis.  # noqa: E501

        :param y: The y of this VisualQueryEntityPosition.
        :type y: float
        """
        if y is None:
            raise ValueError("Invalid value for `y`, must not be `None`")  # noqa: E501

        self._y = y
