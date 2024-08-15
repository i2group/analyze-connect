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


class VisualQueryConditionOperator(Model):

    """
    allowed enum values
    """
    STARTS_WITH = 'STARTS_WITH'
    ENDS_WITH = 'ENDS_WITH'
    EQUAL_TO = 'EQUAL_TO'
    NOT_EQUAL_TO = 'NOT_EQUAL_TO'
    GREATER_THAN = 'GREATER_THAN'
    GREATER_THAN_OR_EQUAL_TO = 'GREATER_THAN_OR_EQUAL_TO'
    LESS_THAN = 'LESS_THAN'
    LESS_THAN_OR_EQUAL_TO = 'LESS_THAN_OR_EQUAL_TO'
    BETWEEN = 'BETWEEN'
    CONTAINS = 'CONTAINS'
    WILDCARD_PATTERN = 'WILDCARD_PATTERN'
    NOT_WILDCARD_PATTERN = 'NOT_WILDCARD_PATTERN'
    IS_SET = 'IS_SET'
    IS_NOT_SET = 'IS_NOT_SET'
    IN_GEOSPATIAL_AREA = 'IN_GEOSPATIAL_AREA'
    def __init__(self):  # noqa: E501
        """VisualQueryConditionOperator

        """
        self.openapi_types = {
        }

        self.attribute_map = {
        }

    @classmethod
    def from_dict(cls, dikt) -> 'VisualQueryConditionOperator':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The VisualQueryConditionOperator of this VisualQueryConditionOperator.  # noqa: E501
        :rtype: VisualQueryConditionOperator
        """
        return util.deserialize_model(dikt, cls)
