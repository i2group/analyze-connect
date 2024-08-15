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
from spi.models.form_config_section import FormConfigSection
from spi import util


class FormConfig(Model):

    def __init__(self, mandatory=False, sections=None):  # noqa: E501
        """FormConfig

        :param mandatory: The mandatory of this FormConfig.  # noqa: E501
        :type mandatory: bool
        :param sections: The sections of this FormConfig.  # noqa: E501
        :type sections: List[FormConfigSection]
        """
        self.openapi_types = {
            'mandatory': bool,
            'sections': List[FormConfigSection]
        }

        self.attribute_map = {
            'mandatory': 'mandatory',
            'sections': 'sections'
        }

        self._mandatory = mandatory
        self._sections = sections

    @classmethod
    def from_dict(cls, dikt) -> 'FormConfig':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The FormConfig of this FormConfig.  # noqa: E501
        :rtype: FormConfig
        """
        return util.deserialize_model(dikt, cls)

    @property
    def mandatory(self) -> bool:
        """Gets the mandatory of this FormConfig.

        Indicates whether the form itself is mandatory, even if no conditions in the form are mandatory.  # noqa: E501

        :return: The mandatory of this FormConfig.
        :rtype: bool
        """
        return self._mandatory

    @mandatory.setter
    def mandatory(self, mandatory: bool):
        """Sets the mandatory of this FormConfig.

        Indicates whether the form itself is mandatory, even if no conditions in the form are mandatory.  # noqa: E501

        :param mandatory: The mandatory of this FormConfig.
        :type mandatory: bool
        """

        self._mandatory = mandatory

    @property
    def sections(self) -> List[FormConfigSection]:
        """Gets the sections of this FormConfig.

        The sections in the form.  # noqa: E501

        :return: The sections of this FormConfig.
        :rtype: List[FormConfigSection]
        """
        return self._sections

    @sections.setter
    def sections(self, sections: List[FormConfigSection]):
        """Sets the sections of this FormConfig.

        The sections in the form.  # noqa: E501

        :param sections: The sections of this FormConfig.
        :type sections: List[FormConfigSection]
        """
        if sections is None:
            raise ValueError("Invalid value for `sections`, must not be `None`")  # noqa: E501

        self._sections = sections
