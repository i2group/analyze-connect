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


class SourceReferenceInfo(Model):

    def __init__(self, description=None, image=None, location=None, name=None, type=None):  # noqa: E501
        """SourceReferenceInfo

        :param description: The description of this SourceReferenceInfo.  # noqa: E501
        :type description: str
        :param image: The image of this SourceReferenceInfo.  # noqa: E501
        :type image: str
        :param location: The location of this SourceReferenceInfo.  # noqa: E501
        :type location: str
        :param name: The name of this SourceReferenceInfo.  # noqa: E501
        :type name: str
        :param type: The type of this SourceReferenceInfo.  # noqa: E501
        :type type: str
        """
        self.openapi_types = {
            'description': str,
            'image': str,
            'location': str,
            'name': str,
            'type': str
        }

        self.attribute_map = {
            'description': 'description',
            'image': 'image',
            'location': 'location',
            'name': 'name',
            'type': 'type'
        }

        self._description = description
        self._image = image
        self._location = location
        self._name = name
        self._type = type

    @classmethod
    def from_dict(cls, dikt) -> 'SourceReferenceInfo':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The SourceReferenceInfo of this SourceReferenceInfo.  # noqa: E501
        :rtype: SourceReferenceInfo
        """
        return util.deserialize_model(dikt, cls)

    @property
    def description(self) -> str:
        """Gets the description of this SourceReferenceInfo.

        The description of a source.  # noqa: E501

        :return: The description of this SourceReferenceInfo.
        :rtype: str
        """
        return self._description

    @description.setter
    def description(self, description: str):
        """Sets the description of this SourceReferenceInfo.

        The description of a source.  # noqa: E501

        :param description: The description of this SourceReferenceInfo.
        :type description: str
        """

        self._description = description

    @property
    def image(self) -> str:
        """Gets the image of this SourceReferenceInfo.

        The URL of an image of a source.  # noqa: E501

        :return: The image of this SourceReferenceInfo.
        :rtype: str
        """
        return self._image

    @image.setter
    def image(self, image: str):
        """Sets the image of this SourceReferenceInfo.

        The URL of an image of a source.  # noqa: E501

        :param image: The image of this SourceReferenceInfo.
        :type image: str
        """

        self._image = image

    @property
    def location(self) -> str:
        """Gets the location of this SourceReferenceInfo.

        The location of a source, which might be a URL.  # noqa: E501

        :return: The location of this SourceReferenceInfo.
        :rtype: str
        """
        return self._location

    @location.setter
    def location(self, location: str):
        """Sets the location of this SourceReferenceInfo.

        The location of a source, which might be a URL.  # noqa: E501

        :param location: The location of this SourceReferenceInfo.
        :type location: str
        """

        self._location = location

    @property
    def name(self) -> str:
        """Gets the name of this SourceReferenceInfo.

        The name of a source.  # noqa: E501

        :return: The name of this SourceReferenceInfo.
        :rtype: str
        """
        return self._name

    @name.setter
    def name(self, name: str):
        """Sets the name of this SourceReferenceInfo.

        The name of a source.  # noqa: E501

        :param name: The name of this SourceReferenceInfo.
        :type name: str
        """
        if name is None:
            raise ValueError("Invalid value for `name`, must not be `None`")  # noqa: E501

        self._name = name

    @property
    def type(self) -> str:
        """Gets the type of this SourceReferenceInfo.

        The type of a source.  # noqa: E501

        :return: The type of this SourceReferenceInfo.
        :rtype: str
        """
        return self._type

    @type.setter
    def type(self, type: str):
        """Sets the type of this SourceReferenceInfo.

        The type of a source.  # noqa: E501

        :param type: The type of this SourceReferenceInfo.
        :type type: str
        """

        self._type = type
