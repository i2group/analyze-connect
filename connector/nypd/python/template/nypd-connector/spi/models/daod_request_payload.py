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
from spi.models.daod_request_condition import DaodRequestCondition
from spi.models.daod_seeds import DaodSeeds
from spi.models.visual_query_item import VisualQueryItem
from spi import util


class DaodRequestPayload(Model):

    def __init__(self, conditions=None, seeds=None, visual_query=None):  # noqa: E501
        """DaodRequestPayload

        :param conditions: The conditions of this DaodRequestPayload.  # noqa: E501
        :type conditions: List[DaodRequestCondition]
        :param seeds: The seeds of this DaodRequestPayload.  # noqa: E501
        :type seeds: DaodSeeds
        :param visual_query: The visual_query of this DaodRequestPayload.  # noqa: E501
        :type visual_query: List[VisualQueryItem]
        """
        self.openapi_types = {
            'conditions': List[DaodRequestCondition],
            'seeds': DaodSeeds,
            'visual_query': List[VisualQueryItem]
        }

        self.attribute_map = {
            'conditions': 'conditions',
            'seeds': 'seeds',
            'visual_query': 'visualQuery'
        }

        self._conditions = conditions
        self._seeds = seeds
        self._visual_query = visual_query

    @classmethod
    def from_dict(cls, dikt) -> 'DaodRequestPayload':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The DaodRequestPayload of this DaodRequestPayload.  # noqa: E501
        :rtype: DaodRequestPayload
        """
        return util.deserialize_model(dikt, cls)

    @property
    def conditions(self) -> List[DaodRequestCondition]:
        """Gets the conditions of this DaodRequestPayload.

        If the service uses a client configuration of type 'FORM', the conditions that a user has specified to refine their query.  # noqa: E501

        :return: The conditions of this DaodRequestPayload.
        :rtype: List[DaodRequestCondition]
        """
        return self._conditions

    @conditions.setter
    def conditions(self, conditions: List[DaodRequestCondition]):
        """Sets the conditions of this DaodRequestPayload.

        If the service uses a client configuration of type 'FORM', the conditions that a user has specified to refine their query.  # noqa: E501

        :param conditions: The conditions of this DaodRequestPayload.
        :type conditions: List[DaodRequestCondition]
        """

        self._conditions = conditions

    @property
    def seeds(self) -> DaodSeeds:
        """Gets the seeds of this DaodRequestPayload.


        :return: The seeds of this DaodRequestPayload.
        :rtype: DaodSeeds
        """
        return self._seeds

    @seeds.setter
    def seeds(self, seeds: DaodSeeds):
        """Sets the seeds of this DaodRequestPayload.


        :param seeds: The seeds of this DaodRequestPayload.
        :type seeds: DaodSeeds
        """

        self._seeds = seeds

    @property
    def visual_query(self) -> List[VisualQueryItem]:
        """Gets the visual_query of this DaodRequestPayload.

        If the service uses a client configuration of type 'VISUAL_QUERY', the Visual Query to execute, represented as an array of query items.  # noqa: E501

        :return: The visual_query of this DaodRequestPayload.
        :rtype: List[VisualQueryItem]
        """
        return self._visual_query

    @visual_query.setter
    def visual_query(self, visual_query: List[VisualQueryItem]):
        """Sets the visual_query of this DaodRequestPayload.

        If the service uses a client configuration of type 'VISUAL_QUERY', the Visual Query to execute, represented as an array of query items.  # noqa: E501

        :param visual_query: The visual_query of this DaodRequestPayload.
        :type visual_query: List[VisualQueryItem]
        """

        self._visual_query = visual_query
