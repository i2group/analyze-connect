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


class VisualQueryConditionAspect(Model):

    """
    allowed enum values
    """
    DATE_AND_TIME = 'DATE_AND_TIME'
    DATE = 'DATE'
    TIME = 'TIME'
    DAY_OF_MONTH = 'DAY_OF_MONTH'
    MONTH = 'MONTH'
    QUARTER = 'QUARTER'
    YEAR = 'YEAR'
    DAY_OF_WEEK = 'DAY_OF_WEEK'
    WEEK_OF_YEAR = 'WEEK_OF_YEAR'
    def __init__(self):  # noqa: E501
        """VisualQueryConditionAspect

        """
        self.openapi_types = {
        }

        self.attribute_map = {
        }

    @classmethod
    def from_dict(cls, dikt) -> 'VisualQueryConditionAspect':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The VisualQueryConditionAspect of this VisualQueryConditionAspect.  # noqa: E501
        :rtype: VisualQueryConditionAspect
        """
        return util.deserialize_model(dikt, cls)
