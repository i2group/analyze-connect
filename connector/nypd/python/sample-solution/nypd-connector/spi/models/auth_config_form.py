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
from spi.models.auth_config_form_field import AuthConfigFormField
from spi import util


class AuthConfigForm(Model):

    def __init__(self, description=None, fields=None):  # noqa: E501
        """AuthConfigForm

        :param description: The description of this AuthConfigForm.  # noqa: E501
        :type description: str
        :param fields: The fields of this AuthConfigForm.  # noqa: E501
        :type fields: List[AuthConfigFormField]
        """
        self.openapi_types = {
            'description': str,
            'fields': List[AuthConfigFormField]
        }

        self.attribute_map = {
            'description': 'description',
            'fields': 'fields'
        }

        self._description = description
        self._fields = fields

    @classmethod
    def from_dict(cls, dikt) -> 'AuthConfigForm':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The AuthConfigForm of this AuthConfigForm.  # noqa: E501
        :rtype: AuthConfigForm
        """
        return util.deserialize_model(dikt, cls)

    @property
    def description(self) -> str:
        """Gets the description of this AuthConfigForm.

        A description of the form and its fields, to be displayed to users.  # noqa: E501

        :return: The description of this AuthConfigForm.
        :rtype: str
        """
        return self._description

    @description.setter
    def description(self, description: str):
        """Sets the description of this AuthConfigForm.

        A description of the form and its fields, to be displayed to users.  # noqa: E501

        :param description: The description of this AuthConfigForm.
        :type description: str
        """
        if description is None:
            raise ValueError("Invalid value for `description`, must not be `None`")  # noqa: E501

        self._description = description

    @property
    def fields(self) -> List[AuthConfigFormField]:
        """Gets the fields of this AuthConfigForm.

        The fields for the form.  # noqa: E501

        :return: The fields of this AuthConfigForm.
        :rtype: List[AuthConfigFormField]
        """
        return self._fields

    @fields.setter
    def fields(self, fields: List[AuthConfigFormField]):
        """Sets the fields of this AuthConfigForm.

        The fields for the form.  # noqa: E501

        :param fields: The fields of this AuthConfigForm.
        :type fields: List[AuthConfigFormField]
        """
        if fields is None:
            raise ValueError("Invalid value for `fields`, must not be `None`")  # noqa: E501

        self._fields = fields
