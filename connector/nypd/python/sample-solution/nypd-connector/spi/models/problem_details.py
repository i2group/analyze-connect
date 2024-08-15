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


class ProblemDetails(Model):

    def __init__(self, detail=None, instance=None, status=None, title=None, type=None):  # noqa: E501
        """ProblemDetails

        :param detail: The detail of this ProblemDetails.  # noqa: E501
        :type detail: str
        :param instance: The instance of this ProblemDetails.  # noqa: E501
        :type instance: str
        :param status: The status of this ProblemDetails.  # noqa: E501
        :type status: int
        :param title: The title of this ProblemDetails.  # noqa: E501
        :type title: str
        :param type: The type of this ProblemDetails.  # noqa: E501
        :type type: str
        """
        self.openapi_types = {
            'detail': str,
            'instance': str,
            'status': int,
            'title': str,
            'type': str
        }

        self.attribute_map = {
            'detail': 'detail',
            'instance': 'instance',
            'status': 'status',
            'title': 'title',
            'type': 'type'
        }

        self._detail = detail
        self._instance = instance
        self._status = status
        self._title = title
        self._type = type

    @classmethod
    def from_dict(cls, dikt) -> 'ProblemDetails':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The ProblemDetails of this ProblemDetails.  # noqa: E501
        :rtype: ProblemDetails
        """
        return util.deserialize_model(dikt, cls)

    @property
    def detail(self) -> str:
        """Gets the detail of this ProblemDetails.

        Additional detail about the problem, specific to this instance of it. This information might also be displayed to the user in the form of an error message.  # noqa: E501

        :return: The detail of this ProblemDetails.
        :rtype: str
        """
        return self._detail

    @detail.setter
    def detail(self, detail: str):
        """Sets the detail of this ProblemDetails.

        Additional detail about the problem, specific to this instance of it. This information might also be displayed to the user in the form of an error message.  # noqa: E501

        :param detail: The detail of this ProblemDetails.
        :type detail: str
        """

        self._detail = detail

    @property
    def instance(self) -> str:
        """Gets the instance of this ProblemDetails.

        A URL for a resource specific to this instance of the problem. The URL can be absolute or relative to the path of the i2 Connect service.  # noqa: E501

        :return: The instance of this ProblemDetails.
        :rtype: str
        """
        return self._instance

    @instance.setter
    def instance(self, instance: str):
        """Sets the instance of this ProblemDetails.

        A URL for a resource specific to this instance of the problem. The URL can be absolute or relative to the path of the i2 Connect service.  # noqa: E501

        :param instance: The instance of this ProblemDetails.
        :type instance: str
        """

        self._instance = instance

    @property
    def status(self) -> int:
        """Gets the status of this ProblemDetails.

        The HTTP status code returned by the i2 Connect service.  # noqa: E501

        :return: The status of this ProblemDetails.
        :rtype: int
        """
        return self._status

    @status.setter
    def status(self, status: int):
        """Sets the status of this ProblemDetails.

        The HTTP status code returned by the i2 Connect service.  # noqa: E501

        :param status: The status of this ProblemDetails.
        :type status: int
        """

        self._status = status

    @property
    def title(self) -> str:
        """Gets the title of this ProblemDetails.

        The title of the problem, which might be displayed to the user.  # noqa: E501

        :return: The title of this ProblemDetails.
        :rtype: str
        """
        return self._title

    @title.setter
    def title(self, title: str):
        """Sets the title of this ProblemDetails.

        The title of the problem, which might be displayed to the user.  # noqa: E501

        :param title: The title of this ProblemDetails.
        :type title: str
        """

        self._title = title

    @property
    def type(self) -> str:
        """Gets the type of this ProblemDetails.

        A URL for a resource that describes the type of the problem. If the problem is that the service requires authentication, this field must be set to `urn:uuid:264caa46-75cb-4ac5-891a-11adeb48b6fb`, which triggers the i2 Connect gateway to prompt the user for credentials.  # noqa: E501

        :return: The type of this ProblemDetails.
        :rtype: str
        """
        return self._type

    @type.setter
    def type(self, type: str):
        """Sets the type of this ProblemDetails.

        A URL for a resource that describes the type of the problem. If the problem is that the service requires authentication, this field must be set to `urn:uuid:264caa46-75cb-4ac5-891a-11adeb48b6fb`, which triggers the i2 Connect gateway to prompt the user for credentials.  # noqa: E501

        :param type: The type of this ProblemDetails.
        :type type: str
        """

        self._type = type
