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


class AuthConfigFormField(Model):

    def __init__(self, id=None, label=None, type=None):  # noqa: E501
        """AuthConfigFormField

        :param id: The id of this AuthConfigFormField.  # noqa: E501
        :type id: str
        :param label: The label of this AuthConfigFormField.  # noqa: E501
        :type label: str
        :param type: The type of this AuthConfigFormField.  # noqa: E501
        :type type: str
        """
        self.openapi_types = {
            'id': str,
            'label': str,
            'type': str
        }

        self.attribute_map = {
            'id': 'id',
            'label': 'label',
            'type': 'type'
        }

        self._id = id
        self._label = label
        self._type = type

    @classmethod
    def from_dict(cls, dikt) -> 'AuthConfigFormField':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The AuthConfigFormField of this AuthConfigFormField.  # noqa: E501
        :rtype: AuthConfigFormField
        """
        return util.deserialize_model(dikt, cls)

    @property
    def id(self) -> str:
        """Gets the id of this AuthConfigFormField.

        The identifier of the field.  # noqa: E501

        :return: The id of this AuthConfigFormField.
        :rtype: str
        """
        return self._id

    @id.setter
    def id(self, id: str):
        """Sets the id of this AuthConfigFormField.

        The identifier of the field.  # noqa: E501

        :param id: The id of this AuthConfigFormField.
        :type id: str
        """
        if id is None:
            raise ValueError("Invalid value for `id`, must not be `None`")  # noqa: E501

        self._id = id

    @property
    def label(self) -> str:
        """Gets the label of this AuthConfigFormField.

        The field label, to be displayed to users.  # noqa: E501

        :return: The label of this AuthConfigFormField.
        :rtype: str
        """
        return self._label

    @label.setter
    def label(self, label: str):
        """Sets the label of this AuthConfigFormField.

        The field label, to be displayed to users.  # noqa: E501

        :param label: The label of this AuthConfigFormField.
        :type label: str
        """
        if label is None:
            raise ValueError("Invalid value for `label`, must not be `None`")  # noqa: E501

        self._label = label

    @property
    def type(self) -> str:
        """Gets the type of this AuthConfigFormField.

        The type of the field, which can be either 'text' or 'password'.  # noqa: E501

        :return: The type of this AuthConfigFormField.
        :rtype: str
        """
        return self._type

    @type.setter
    def type(self, type: str):
        """Sets the type of this AuthConfigFormField.

        The type of the field, which can be either 'text' or 'password'.  # noqa: E501

        :param type: The type of this AuthConfigFormField.
        :type type: str
        """
        if type is None:
            raise ValueError("Invalid value for `type`, must not be `None`")  # noqa: E501

        self._type = type
