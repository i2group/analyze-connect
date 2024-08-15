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
from spi.models.geometry import Geometry
from spi import util


class GeoJSONPoint(Model):

    def __init__(self, type=None, coordinates=None):  # noqa: E501
        """GeoJSONPoint

        :param type: The type of this GeoJSONPoint.  # noqa: E501
        :type type: str
        :param coordinates: The coordinates of this GeoJSONPoint.  # noqa: E501
        :type coordinates: List[float]
        """
        self.openapi_types = {
            'type': str,
            'coordinates': List[float]
        }

        self.attribute_map = {
            'type': 'type',
            'coordinates': 'coordinates'
        }

        self._type = type
        self._coordinates = coordinates

    @classmethod
    def from_dict(cls, dikt) -> 'GeoJSONPoint':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The GeoJSONPoint of this GeoJSONPoint.  # noqa: E501
        :rtype: GeoJSONPoint
        """
        return util.deserialize_model(dikt, cls)

    @property
    def type(self) -> str:
        """Gets the type of this GeoJSONPoint.

        The type of this shape.  # noqa: E501

        :return: The type of this GeoJSONPoint.
        :rtype: str
        """
        return self._type

    @type.setter
    def type(self, type: str):
        """Sets the type of this GeoJSONPoint.

        The type of this shape.  # noqa: E501

        :param type: The type of this GeoJSONPoint.
        :type type: str
        """
        allowed_values = ["Point", "Polygon", "MultiPolygon"]  # noqa: E501
        if type not in allowed_values:
            raise ValueError(
                "Invalid value for `type` ({0}), must be one of {1}"
                .format(type, allowed_values)
            )

        self._type = type

    @property
    def coordinates(self) -> List[float]:
        """Gets the coordinates of this GeoJSONPoint.

        An array in which the first element represents longitude, the second represents latitude, and the (optional) third represents altitude.  # noqa: E501

        :return: The coordinates of this GeoJSONPoint.
        :rtype: List[float]
        """
        return self._coordinates

    @coordinates.setter
    def coordinates(self, coordinates: List[float]):
        """Sets the coordinates of this GeoJSONPoint.

        An array in which the first element represents longitude, the second represents latitude, and the (optional) third represents altitude.  # noqa: E501

        :param coordinates: The coordinates of this GeoJSONPoint.
        :type coordinates: List[float]
        """
        if coordinates is None:
            raise ValueError("Invalid value for `coordinates`, must not be `None`")  # noqa: E501
        if coordinates is not None and len(coordinates) > 3:
            raise ValueError("Invalid value for `coordinates`, number of items must be less than or equal to `3`")  # noqa: E501
        if coordinates is not None and len(coordinates) < 2:
            raise ValueError("Invalid value for `coordinates`, number of items must be greater than or equal to `2`")  # noqa: E501

        self._coordinates = coordinates
