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


class SecurityDimensionAndValues(Model):

    def __init__(self, dimension_id=None, ids=None):  # noqa: E501
        """SecurityDimensionAndValues

        :param dimension_id: The dimension_id of this SecurityDimensionAndValues.  # noqa: E501
        :type dimension_id: str
        :param ids: The ids of this SecurityDimensionAndValues.  # noqa: E501
        :type ids: list[str]
        """
        self.openapi_types = {
            'dimension_id': str,
            'ids': list[str]
        }

        self.attribute_map = {
            'dimension_id': 'dimensionId',
            'ids': 'ids'
        }

        self._dimension_id = dimension_id
        self._ids = ids

    @classmethod
    def from_dict(cls, dikt) -> 'SecurityDimensionAndValues':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The SecurityDimensionAndValues of this SecurityDimensionAndValues.  # noqa: E501
        :rtype: SecurityDimensionAndValues
        """
        return util.deserialize_model(dikt, cls)

    @property
    def dimension_id(self) -> str:
        """Gets the dimension_id of this SecurityDimensionAndValues.

        The identifier of the security dimension that has the values in **ids**.  # noqa: E501

        :return: The dimension_id of this SecurityDimensionAndValues.
        :rtype: str
        """
        return self._dimension_id

    @dimension_id.setter
    def dimension_id(self, dimension_id: str):
        """Sets the dimension_id of this SecurityDimensionAndValues.

        The identifier of the security dimension that has the values in **ids**.  # noqa: E501

        :param dimension_id: The dimension_id of this SecurityDimensionAndValues.
        :type dimension_id: str
        """

        self._dimension_id = dimension_id

    @property
    def ids(self) -> list[str]:
        """Gets the ids of this SecurityDimensionAndValues.

        The identifiers of values in the security dimension with **dimensionId**.  # noqa: E501

        :return: The ids of this SecurityDimensionAndValues.
        :rtype: list[str]
        """
        return self._ids

    @ids.setter
    def ids(self, ids: list[str]):
        """Sets the ids of this SecurityDimensionAndValues.

        The identifiers of values in the security dimension with **dimensionId**.  # noqa: E501

        :param ids: The ids of this SecurityDimensionAndValues.
        :type ids: list[str]
        """

        self._ids = ids
