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
from spi.models.auth_config_form import AuthConfigForm
from spi import util


class AuthConfig(Model):

    def __init__(self, form=None, id=None, login_url=None):  # noqa: E501
        """AuthConfig

        :param form: The form of this AuthConfig.  # noqa: E501
        :type form: AuthConfigForm
        :param id: The id of this AuthConfig.  # noqa: E501
        :type id: str
        :param login_url: The login_url of this AuthConfig.  # noqa: E501
        :type login_url: str
        """
        self.openapi_types = {
            'form': AuthConfigForm,
            'id': str,
            'login_url': str
        }

        self.attribute_map = {
            'form': 'form',
            'id': 'id',
            'login_url': 'loginUrl'
        }

        self._form = form
        self._id = id
        self._login_url = login_url

    @classmethod
    def from_dict(cls, dikt) -> 'AuthConfig':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The AuthConfig of this AuthConfig.  # noqa: E501
        :rtype: AuthConfig
        """
        return util.deserialize_model(dikt, cls)

    @property
    def form(self) -> AuthConfigForm:
        """Gets the form of this AuthConfig.


        :return: The form of this AuthConfig.
        :rtype: AuthConfigForm
        """
        return self._form

    @form.setter
    def form(self, form: AuthConfigForm):
        """Sets the form of this AuthConfig.


        :param form: The form of this AuthConfig.
        :type form: AuthConfigForm
        """
        if form is None:
            raise ValueError("Invalid value for `form`, must not be `None`")  # noqa: E501

        self._form = form

    @property
    def id(self) -> str:
        """Gets the id of this AuthConfig.

        The unique identifier of the authentication configuration.  # noqa: E501

        :return: The id of this AuthConfig.
        :rtype: str
        """
        return self._id

    @id.setter
    def id(self, id: str):
        """Sets the id of this AuthConfig.

        The unique identifier of the authentication configuration.  # noqa: E501

        :param id: The id of this AuthConfig.
        :type id: str
        """
        if id is None:
            raise ValueError("Invalid value for `id`, must not be `None`")  # noqa: E501

        self._id = id

    @property
    def login_url(self) -> str:
        """Gets the login_url of this AuthConfig.

        The URL for the endpoint that provides authentication functionality.  # noqa: E501

        :return: The login_url of this AuthConfig.
        :rtype: str
        """
        return self._login_url

    @login_url.setter
    def login_url(self, login_url: str):
        """Sets the login_url of this AuthConfig.

        The URL for the endpoint that provides authentication functionality.  # noqa: E501

        :param login_url: The login_url of this AuthConfig.
        :type login_url: str
        """
        if login_url is None:
            raise ValueError("Invalid value for `login_url`, must not be `None`")  # noqa: E501

        self._login_url = login_url
