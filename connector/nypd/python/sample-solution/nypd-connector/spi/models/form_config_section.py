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
from spi.models.form_config_condition import FormConfigCondition
from spi import util


class FormConfigSection(Model):

    def __init__(self, title=None, conditions=None):  # noqa: E501
        """FormConfigSection

        :param title: The title of this FormConfigSection.  # noqa: E501
        :type title: str
        :param conditions: The conditions of this FormConfigSection.  # noqa: E501
        :type conditions: List[FormConfigCondition]
        """
        self.openapi_types = {
            'title': str,
            'conditions': List[FormConfigCondition]
        }

        self.attribute_map = {
            'title': 'title',
            'conditions': 'conditions'
        }

        self._title = title
        self._conditions = conditions

    @classmethod
    def from_dict(cls, dikt) -> 'FormConfigSection':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The FormConfigSection of this FormConfigSection.  # noqa: E501
        :rtype: FormConfigSection
        """
        return util.deserialize_model(dikt, cls)

    @property
    def title(self) -> str:
        """Gets the title of this FormConfigSection.

        The title of the section.  # noqa: E501

        :return: The title of this FormConfigSection.
        :rtype: str
        """
        return self._title

    @title.setter
    def title(self, title: str):
        """Sets the title of this FormConfigSection.

        The title of the section.  # noqa: E501

        :param title: The title of this FormConfigSection.
        :type title: str
        """

        self._title = title

    @property
    def conditions(self) -> List[FormConfigCondition]:
        """Gets the conditions of this FormConfigSection.

        The conditions in the section.  # noqa: E501

        :return: The conditions of this FormConfigSection.
        :rtype: List[FormConfigCondition]
        """
        return self._conditions

    @conditions.setter
    def conditions(self, conditions: List[FormConfigCondition]):
        """Sets the conditions of this FormConfigSection.

        The conditions in the section.  # noqa: E501

        :param conditions: The conditions of this FormConfigSection.
        :type conditions: List[FormConfigCondition]
        """
        if conditions is None:
            raise ValueError("Invalid value for `conditions`, must not be `None`")  # noqa: E501

        self._conditions = conditions
