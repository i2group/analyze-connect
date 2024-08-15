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
from spi.models.form_config import FormConfig
from spi.models.form_config_section import FormConfigSection
from spi.models.visual_query_config import VisualQueryConfig
from spi.models.visual_query_constraints import VisualQueryConstraints
from spi.models.visual_query_operator_usage_rule import VisualQueryOperatorUsageRule
from spi.models.visual_query_palette_item_types import VisualQueryPaletteItemTypes
from spi import util


class ClientConfigConfig(Model):

    def __init__(self, mandatory=False, sections=None, operator_usage_rules=None, constraints=None, palette_item_types=None):  # noqa: E501
        """ClientConfigConfig

        :param mandatory: The mandatory of this ClientConfigConfig.  # noqa: E501
        :type mandatory: bool
        :param sections: The sections of this ClientConfigConfig.  # noqa: E501
        :type sections: List[FormConfigSection]
        :param operator_usage_rules: The operator_usage_rules of this ClientConfigConfig.  # noqa: E501
        :type operator_usage_rules: List[VisualQueryOperatorUsageRule]
        :param constraints: The constraints of this ClientConfigConfig.  # noqa: E501
        :type constraints: VisualQueryConstraints
        :param palette_item_types: The palette_item_types of this ClientConfigConfig.  # noqa: E501
        :type palette_item_types: VisualQueryPaletteItemTypes
        """
        self.openapi_types = {
            'mandatory': bool,
            'sections': List[FormConfigSection],
            'operator_usage_rules': List[VisualQueryOperatorUsageRule],
            'constraints': VisualQueryConstraints,
            'palette_item_types': VisualQueryPaletteItemTypes
        }

        self.attribute_map = {
            'mandatory': 'mandatory',
            'sections': 'sections',
            'operator_usage_rules': 'operatorUsageRules',
            'constraints': 'constraints',
            'palette_item_types': 'paletteItemTypes'
        }

        self._mandatory = mandatory
        self._sections = sections
        self._operator_usage_rules = operator_usage_rules
        self._constraints = constraints
        self._palette_item_types = palette_item_types

    @classmethod
    def from_dict(cls, dikt) -> 'ClientConfigConfig':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The ClientConfig_config of this ClientConfigConfig.  # noqa: E501
        :rtype: ClientConfigConfig
        """
        return util.deserialize_model(dikt, cls)

    @property
    def mandatory(self) -> bool:
        """Gets the mandatory of this ClientConfigConfig.

        Indicates whether the form itself is mandatory, even if no conditions in the form are mandatory.  # noqa: E501

        :return: The mandatory of this ClientConfigConfig.
        :rtype: bool
        """
        return self._mandatory

    @mandatory.setter
    def mandatory(self, mandatory: bool):
        """Sets the mandatory of this ClientConfigConfig.

        Indicates whether the form itself is mandatory, even if no conditions in the form are mandatory.  # noqa: E501

        :param mandatory: The mandatory of this ClientConfigConfig.
        :type mandatory: bool
        """

        self._mandatory = mandatory

    @property
    def sections(self) -> List[FormConfigSection]:
        """Gets the sections of this ClientConfigConfig.

        The sections in the form.  # noqa: E501

        :return: The sections of this ClientConfigConfig.
        :rtype: List[FormConfigSection]
        """
        return self._sections

    @sections.setter
    def sections(self, sections: List[FormConfigSection]):
        """Sets the sections of this ClientConfigConfig.

        The sections in the form.  # noqa: E501

        :param sections: The sections of this ClientConfigConfig.
        :type sections: List[FormConfigSection]
        """
        if sections is None:
            raise ValueError("Invalid value for `sections`, must not be `None`")  # noqa: E501

        self._sections = sections

    @property
    def operator_usage_rules(self) -> List[VisualQueryOperatorUsageRule]:
        """Gets the operator_usage_rules of this ClientConfigConfig.

        The rules that govern the property types and operators that can appear in Visual Query conditions.  # noqa: E501

        :return: The operator_usage_rules of this ClientConfigConfig.
        :rtype: List[VisualQueryOperatorUsageRule]
        """
        return self._operator_usage_rules

    @operator_usage_rules.setter
    def operator_usage_rules(self, operator_usage_rules: List[VisualQueryOperatorUsageRule]):
        """Sets the operator_usage_rules of this ClientConfigConfig.

        The rules that govern the property types and operators that can appear in Visual Query conditions.  # noqa: E501

        :param operator_usage_rules: The operator_usage_rules of this ClientConfigConfig.
        :type operator_usage_rules: List[VisualQueryOperatorUsageRule]
        """

        self._operator_usage_rules = operator_usage_rules

    @property
    def constraints(self) -> VisualQueryConstraints:
        """Gets the constraints of this ClientConfigConfig.


        :return: The constraints of this ClientConfigConfig.
        :rtype: VisualQueryConstraints
        """
        return self._constraints

    @constraints.setter
    def constraints(self, constraints: VisualQueryConstraints):
        """Sets the constraints of this ClientConfigConfig.


        :param constraints: The constraints of this ClientConfigConfig.
        :type constraints: VisualQueryConstraints
        """

        self._constraints = constraints

    @property
    def palette_item_types(self) -> VisualQueryPaletteItemTypes:
        """Gets the palette_item_types of this ClientConfigConfig.


        :return: The palette_item_types of this ClientConfigConfig.
        :rtype: VisualQueryPaletteItemTypes
        """
        return self._palette_item_types

    @palette_item_types.setter
    def palette_item_types(self, palette_item_types: VisualQueryPaletteItemTypes):
        """Sets the palette_item_types of this ClientConfigConfig.


        :param palette_item_types: The palette_item_types of this ClientConfigConfig.
        :type palette_item_types: VisualQueryPaletteItemTypes
        """

        self._palette_item_types = palette_item_types
