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


class Feature(Model):

    def __init__(self, type=None, geometry=None, id=None, properties=None):  # noqa: E501
        """Feature

        :param type: The type of this Feature.  # noqa: E501
        :type type: str
        :param geometry: The geometry of this Feature.  # noqa: E501
        :type geometry: Geometry
        :param id: The id of this Feature.  # noqa: E501
        :type id: str
        :param properties: The properties of this Feature.  # noqa: E501
        :type properties: Dict[str, str]
        """
        self.openapi_types = {
            'type': str,
            'geometry': Geometry,
            'id': str,
            'properties': Dict[str, str]
        }

        self.attribute_map = {
            'type': 'type',
            'geometry': 'geometry',
            'id': 'id',
            'properties': 'properties'
        }

        self._type = type
        self._geometry = geometry
        self._id = id
        self._properties = properties

    @classmethod
    def from_dict(cls, dikt) -> 'Feature':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The Feature of this Feature.  # noqa: E501
        :rtype: Feature
        """
        return util.deserialize_model(dikt, cls)

    @property
    def type(self) -> str:
        """Gets the type of this Feature.

        The type of object represented.  # noqa: E501

        :return: The type of this Feature.
        :rtype: str
        """
        return self._type

    @type.setter
    def type(self, type: str):
        """Sets the type of this Feature.

        The type of object represented.  # noqa: E501

        :param type: The type of this Feature.
        :type type: str
        """
        allowed_values = ["Feature"]  # noqa: E501
        if type not in allowed_values:
            raise ValueError(
                "Invalid value for `type` ({0}), must be one of {1}"
                .format(type, allowed_values)
            )

        self._type = type

    @property
    def geometry(self) -> Geometry:
        """Gets the geometry of this Feature.


        :return: The geometry of this Feature.
        :rtype: Geometry
        """
        return self._geometry

    @geometry.setter
    def geometry(self, geometry: Geometry):
        """Sets the geometry of this Feature.


        :param geometry: The geometry of this Feature.
        :type geometry: Geometry
        """
        if geometry is None:
            raise ValueError("Invalid value for `geometry`, must not be `None`")  # noqa: E501

        self._geometry = geometry

    @property
    def id(self) -> str:
        """Gets the id of this Feature.

        The feature identifier.  # noqa: E501

        :return: The id of this Feature.
        :rtype: str
        """
        return self._id

    @id.setter
    def id(self, id: str):
        """Sets the id of this Feature.

        The feature identifier.  # noqa: E501

        :param id: The id of this Feature.
        :type id: str
        """

        self._id = id

    @property
    def properties(self) -> Dict[str, str]:
        """Gets the properties of this Feature.

        Represents a holding object where properties associated to the feature can be added.  # noqa: E501

        :return: The properties of this Feature.
        :rtype: Dict[str, str]
        """
        return self._properties

    @properties.setter
    def properties(self, properties: Dict[str, str]):
        """Sets the properties of this Feature.

        Represents a holding object where properties associated to the feature can be added.  # noqa: E501

        :param properties: The properties of this Feature.
        :type properties: Dict[str, str]
        """

        self._properties = properties
