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
from spi.models.extra_string_validation import ExtraStringValidation
from spi.models.form_logical_type import FormLogicalType
from spi.models.max_string_length import MaxStringLength
from spi.models.possible_condition_value import PossibleConditionValue
from spi import util


class FormConfigCondition(Model):

    def __init__(self, id=None, label=None, description=None, mandatory=False, logical_type=None, default_value=None, min_value=None, max_value=None, max_string_length=None, extra_string_validation=None, possible_values=None):  # noqa: E501
        """FormConfigCondition

        :param id: The id of this FormConfigCondition.  # noqa: E501
        :type id: str
        :param label: The label of this FormConfigCondition.  # noqa: E501
        :type label: str
        :param description: The description of this FormConfigCondition.  # noqa: E501
        :type description: str
        :param mandatory: The mandatory of this FormConfigCondition.  # noqa: E501
        :type mandatory: bool
        :param logical_type: The logical_type of this FormConfigCondition.  # noqa: E501
        :type logical_type: FormLogicalType
        :param default_value: The default_value of this FormConfigCondition.  # noqa: E501
        :type default_value: object
        :param min_value: The min_value of this FormConfigCondition.  # noqa: E501
        :type min_value: float
        :param max_value: The max_value of this FormConfigCondition.  # noqa: E501
        :type max_value: float
        :param max_string_length: The max_string_length of this FormConfigCondition.  # noqa: E501
        :type max_string_length: MaxStringLength
        :param extra_string_validation: The extra_string_validation of this FormConfigCondition.  # noqa: E501
        :type extra_string_validation: ExtraStringValidation
        :param possible_values: The possible_values of this FormConfigCondition.  # noqa: E501
        :type possible_values: List[PossibleConditionValue]
        """
        self.openapi_types = {
            'id': str,
            'label': str,
            'description': str,
            'mandatory': bool,
            'logical_type': FormLogicalType,
            'default_value': object,
            'min_value': float,
            'max_value': float,
            'max_string_length': MaxStringLength,
            'extra_string_validation': ExtraStringValidation,
            'possible_values': List[PossibleConditionValue]
        }

        self.attribute_map = {
            'id': 'id',
            'label': 'label',
            'description': 'description',
            'mandatory': 'mandatory',
            'logical_type': 'logicalType',
            'default_value': 'defaultValue',
            'min_value': 'minValue',
            'max_value': 'maxValue',
            'max_string_length': 'maxStringLength',
            'extra_string_validation': 'extraStringValidation',
            'possible_values': 'possibleValues'
        }

        self._id = id
        self._label = label
        self._description = description
        self._mandatory = mandatory
        self._logical_type = logical_type
        self._default_value = default_value
        self._min_value = min_value
        self._max_value = max_value
        self._max_string_length = max_string_length
        self._extra_string_validation = extra_string_validation
        self._possible_values = possible_values

    @classmethod
    def from_dict(cls, dikt) -> 'FormConfigCondition':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The FormConfigCondition of this FormConfigCondition.  # noqa: E501
        :rtype: FormConfigCondition
        """
        return util.deserialize_model(dikt, cls)

    @property
    def id(self) -> str:
        """Gets the id of this FormConfigCondition.

        The identifier of the condition.  # noqa: E501

        :return: The id of this FormConfigCondition.
        :rtype: str
        """
        return self._id

    @id.setter
    def id(self, id: str):
        """Sets the id of this FormConfigCondition.

        The identifier of the condition.  # noqa: E501

        :param id: The id of this FormConfigCondition.
        :type id: str
        """
        if id is None:
            raise ValueError("Invalid value for `id`, must not be `None`")  # noqa: E501

        self._id = id

    @property
    def label(self) -> str:
        """Gets the label of this FormConfigCondition.

        The label of the condition.  # noqa: E501

        :return: The label of this FormConfigCondition.
        :rtype: str
        """
        return self._label

    @label.setter
    def label(self, label: str):
        """Sets the label of this FormConfigCondition.

        The label of the condition.  # noqa: E501

        :param label: The label of this FormConfigCondition.
        :type label: str
        """
        if label is None:
            raise ValueError("Invalid value for `label`, must not be `None`")  # noqa: E501

        self._label = label

    @property
    def description(self) -> str:
        """Gets the description of this FormConfigCondition.

        A description of the condition, to be displayed to users.  # noqa: E501

        :return: The description of this FormConfigCondition.
        :rtype: str
        """
        return self._description

    @description.setter
    def description(self, description: str):
        """Sets the description of this FormConfigCondition.

        A description of the condition, to be displayed to users.  # noqa: E501

        :param description: The description of this FormConfigCondition.
        :type description: str
        """

        self._description = description

    @property
    def mandatory(self) -> bool:
        """Gets the mandatory of this FormConfigCondition.

        Indicates whether the condition is mandatory.  # noqa: E501

        :return: The mandatory of this FormConfigCondition.
        :rtype: bool
        """
        return self._mandatory

    @mandatory.setter
    def mandatory(self, mandatory: bool):
        """Sets the mandatory of this FormConfigCondition.

        Indicates whether the condition is mandatory.  # noqa: E501

        :param mandatory: The mandatory of this FormConfigCondition.
        :type mandatory: bool
        """

        self._mandatory = mandatory

    @property
    def logical_type(self) -> FormLogicalType:
        """Gets the logical_type of this FormConfigCondition.


        :return: The logical_type of this FormConfigCondition.
        :rtype: FormLogicalType
        """
        return self._logical_type

    @logical_type.setter
    def logical_type(self, logical_type: FormLogicalType):
        """Sets the logical_type of this FormConfigCondition.


        :param logical_type: The logical_type of this FormConfigCondition.
        :type logical_type: FormLogicalType
        """
        if logical_type is None:
            raise ValueError("Invalid value for `logical_type`, must not be `None`")  # noqa: E501

        self._logical_type = logical_type

    @property
    def default_value(self) -> object:
        """Gets the default_value of this FormConfigCondition.

        The default value for the condition, which must be formatted correctly for its logical type. For the format rules, see [Property value data formats](https://i2group.github.io/analyze-connect/content/miscellaneous/data-model.html).  # noqa: E501

        :return: The default_value of this FormConfigCondition.
        :rtype: object
        """
        return self._default_value

    @default_value.setter
    def default_value(self, default_value: object):
        """Sets the default_value of this FormConfigCondition.

        The default value for the condition, which must be formatted correctly for its logical type. For the format rules, see [Property value data formats](https://i2group.github.io/analyze-connect/content/miscellaneous/data-model.html).  # noqa: E501

        :param default_value: The default_value of this FormConfigCondition.
        :type default_value: object
        """

        self._default_value = default_value

    @property
    def min_value(self) -> float:
        """Gets the min_value of this FormConfigCondition.

        The minimum permitted numeric value in the condition, if its logical type is `DOUBLE` or `INTEGER`.  # noqa: E501

        :return: The min_value of this FormConfigCondition.
        :rtype: float
        """
        return self._min_value

    @min_value.setter
    def min_value(self, min_value: float):
        """Sets the min_value of this FormConfigCondition.

        The minimum permitted numeric value in the condition, if its logical type is `DOUBLE` or `INTEGER`.  # noqa: E501

        :param min_value: The min_value of this FormConfigCondition.
        :type min_value: float
        """

        self._min_value = min_value

    @property
    def max_value(self) -> float:
        """Gets the max_value of this FormConfigCondition.

        The maximum permitted numeric value in the condition, if its logical type is `DOUBLE` or `INTEGER`.  # noqa: E501

        :return: The max_value of this FormConfigCondition.
        :rtype: float
        """
        return self._max_value

    @max_value.setter
    def max_value(self, max_value: float):
        """Sets the max_value of this FormConfigCondition.

        The maximum permitted numeric value in the condition, if its logical type is `DOUBLE` or `INTEGER`.  # noqa: E501

        :param max_value: The max_value of this FormConfigCondition.
        :type max_value: float
        """

        self._max_value = max_value

    @property
    def max_string_length(self) -> MaxStringLength:
        """Gets the max_string_length of this FormConfigCondition.


        :return: The max_string_length of this FormConfigCondition.
        :rtype: MaxStringLength
        """
        return self._max_string_length

    @max_string_length.setter
    def max_string_length(self, max_string_length: MaxStringLength):
        """Sets the max_string_length of this FormConfigCondition.


        :param max_string_length: The max_string_length of this FormConfigCondition.
        :type max_string_length: MaxStringLength
        """

        self._max_string_length = max_string_length

    @property
    def extra_string_validation(self) -> ExtraStringValidation:
        """Gets the extra_string_validation of this FormConfigCondition.


        :return: The extra_string_validation of this FormConfigCondition.
        :rtype: ExtraStringValidation
        """
        return self._extra_string_validation

    @extra_string_validation.setter
    def extra_string_validation(self, extra_string_validation: ExtraStringValidation):
        """Sets the extra_string_validation of this FormConfigCondition.


        :param extra_string_validation: The extra_string_validation of this FormConfigCondition.
        :type extra_string_validation: ExtraStringValidation
        """

        self._extra_string_validation = extra_string_validation

    @property
    def possible_values(self) -> List[PossibleConditionValue]:
        """Gets the possible_values of this FormConfigCondition.

        Possible values for the condition, if its logical type is `SELECTED_FROM` or `SUGGESTED_FROM`.  # noqa: E501

        :return: The possible_values of this FormConfigCondition.
        :rtype: List[PossibleConditionValue]
        """
        return self._possible_values

    @possible_values.setter
    def possible_values(self, possible_values: List[PossibleConditionValue]):
        """Sets the possible_values of this FormConfigCondition.

        Possible values for the condition, if its logical type is `SELECTED_FROM` or `SUGGESTED_FROM`.  # noqa: E501

        :param possible_values: The possible_values of this FormConfigCondition.
        :type possible_values: List[PossibleConditionValue]
        """

        self._possible_values = possible_values
