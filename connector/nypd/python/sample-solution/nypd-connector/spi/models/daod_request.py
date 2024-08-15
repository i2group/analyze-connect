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
from spi.models.daod_request_payload import DaodRequestPayload
from spi import util


class DaodRequest(Model):

    def __init__(self, payload=None):  # noqa: E501
        """DaodRequest

        :param payload: The payload of this DaodRequest.  # noqa: E501
        :type payload: DaodRequestPayload
        """
        self.openapi_types = {
            'payload': DaodRequestPayload
        }

        self.attribute_map = {
            'payload': 'payload'
        }

        self._payload = payload

    @classmethod
    def from_dict(cls, dikt) -> 'DaodRequest':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The DaodRequest of this DaodRequest.  # noqa: E501
        :rtype: DaodRequest
        """
        return util.deserialize_model(dikt, cls)

    @property
    def payload(self) -> DaodRequestPayload:
        """Gets the payload of this DaodRequest.


        :return: The payload of this DaodRequest.
        :rtype: DaodRequestPayload
        """
        return self._payload

    @payload.setter
    def payload(self, payload: DaodRequestPayload):
        """Sets the payload of this DaodRequest.


        :param payload: The payload of this DaodRequest.
        :type payload: DaodRequestPayload
        """
        if payload is None:
            raise ValueError("Invalid value for `payload`, must not be `None`")  # noqa: E501

        self._payload = payload
