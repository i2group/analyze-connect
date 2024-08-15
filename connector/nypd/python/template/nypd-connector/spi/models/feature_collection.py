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
from spi.models.feature import Feature
from spi import util


class FeatureCollection(Model):

    def __init__(self, type=None, features=None):  # noqa: E501
        """FeatureCollection

        :param type: The type of this FeatureCollection.  # noqa: E501
        :type type: str
        :param features: The features of this FeatureCollection.  # noqa: E501
        :type features: List[Feature]
        """
        self.openapi_types = {
            'type': str,
            'features': List[Feature]
        }

        self.attribute_map = {
            'type': 'type',
            'features': 'features'
        }

        self._type = type
        self._features = features

    @classmethod
    def from_dict(cls, dikt) -> 'FeatureCollection':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The FeatureCollection of this FeatureCollection.  # noqa: E501
        :rtype: FeatureCollection
        """
        return util.deserialize_model(dikt, cls)

    @property
    def type(self) -> str:
        """Gets the type of this FeatureCollection.

        The type of collection represented.  # noqa: E501

        :return: The type of this FeatureCollection.
        :rtype: str
        """
        return self._type

    @type.setter
    def type(self, type: str):
        """Sets the type of this FeatureCollection.

        The type of collection represented.  # noqa: E501

        :param type: The type of this FeatureCollection.
        :type type: str
        """
        allowed_values = ["FeatureCollection"]  # noqa: E501
        if type not in allowed_values:
            raise ValueError(
                "Invalid value for `type` ({0}), must be one of {1}"
                .format(type, allowed_values)
            )

        self._type = type

    @property
    def features(self) -> List[Feature]:
        """Gets the features of this FeatureCollection.

        A collection of GeoJSON features. The array can be empty if no features are associated with the collection.  # noqa: E501

        :return: The features of this FeatureCollection.
        :rtype: List[Feature]
        """
        return self._features

    @features.setter
    def features(self, features: List[Feature]):
        """Sets the features of this FeatureCollection.

        A collection of GeoJSON features. The array can be empty if no features are associated with the collection.  # noqa: E501

        :param features: The features of this FeatureCollection.
        :type features: List[Feature]
        """
        if features is None:
            raise ValueError("Invalid value for `features`, must not be `None`")  # noqa: E501

        self._features = features
