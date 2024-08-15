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


class GeoJSONMultiPolygon(Model):

    def __init__(self, type=None, coordinates=None):  # noqa: E501
        """GeoJSONMultiPolygon

        :param type: The type of this GeoJSONMultiPolygon.  # noqa: E501
        :type type: str
        :param coordinates: The coordinates of this GeoJSONMultiPolygon.  # noqa: E501
        :type coordinates: List[List[List[List[float]]]]
        """
        self.openapi_types = {
            'type': str,
            'coordinates': List[List[List[List[float]]]]
        }

        self.attribute_map = {
            'type': 'type',
            'coordinates': 'coordinates'
        }

        self._type = type
        self._coordinates = coordinates

    @classmethod
    def from_dict(cls, dikt) -> 'GeoJSONMultiPolygon':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The GeoJSONMultiPolygon of this GeoJSONMultiPolygon.  # noqa: E501
        :rtype: GeoJSONMultiPolygon
        """
        return util.deserialize_model(dikt, cls)

    @property
    def type(self) -> str:
        """Gets the type of this GeoJSONMultiPolygon.

        The type of this shape.  # noqa: E501

        :return: The type of this GeoJSONMultiPolygon.
        :rtype: str
        """
        return self._type

    @type.setter
    def type(self, type: str):
        """Sets the type of this GeoJSONMultiPolygon.

        The type of this shape.  # noqa: E501

        :param type: The type of this GeoJSONMultiPolygon.
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
    def coordinates(self) -> List[List[List[List[float]]]]:
        """Gets the coordinates of this GeoJSONMultiPolygon.

        The geospatial coordinates of the vertices of polygons.  # noqa: E501

        :return: The coordinates of this GeoJSONMultiPolygon.
        :rtype: List[List[List[List[float]]]]
        """
        return self._coordinates

    @coordinates.setter
    def coordinates(self, coordinates: List[List[List[List[float]]]]):
        """Sets the coordinates of this GeoJSONMultiPolygon.

        The geospatial coordinates of the vertices of polygons.  # noqa: E501

        :param coordinates: The coordinates of this GeoJSONMultiPolygon.
        :type coordinates: List[List[List[List[float]]]]
        """
        if coordinates is None:
            raise ValueError("Invalid value for `coordinates`, must not be `None`")  # noqa: E501
        if coordinates is not None and len(coordinates) < 1:
            raise ValueError("Invalid value for `coordinates`, number of items must be greater than or equal to `1`")  # noqa: E501

        self._coordinates = coordinates
