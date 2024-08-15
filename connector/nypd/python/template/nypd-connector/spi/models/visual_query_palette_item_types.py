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


class VisualQueryPaletteItemTypes(Model):

    def __init__(self, mode=None, item_type_ids=None):  # noqa: E501
        """VisualQueryPaletteItemTypes

        :param mode: The mode of this VisualQueryPaletteItemTypes.  # noqa: E501
        :type mode: str
        :param item_type_ids: The item_type_ids of this VisualQueryPaletteItemTypes.  # noqa: E501
        :type item_type_ids: List[str]
        """
        self.openapi_types = {
            'mode': str,
            'item_type_ids': List[str]
        }

        self.attribute_map = {
            'mode': 'mode',
            'item_type_ids': 'itemTypeIds'
        }

        self._mode = mode
        self._item_type_ids = item_type_ids

    @classmethod
    def from_dict(cls, dikt) -> 'VisualQueryPaletteItemTypes':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The VisualQueryPaletteItemTypes of this VisualQueryPaletteItemTypes.  # noqa: E501
        :rtype: VisualQueryPaletteItemTypes
        """
        return util.deserialize_model(dikt, cls)

    @property
    def mode(self) -> str:
        """Gets the mode of this VisualQueryPaletteItemTypes.

        Indicates whether to allow or deny the identified item types in the palette.  # noqa: E501

        :return: The mode of this VisualQueryPaletteItemTypes.
        :rtype: str
        """
        return self._mode

    @mode.setter
    def mode(self, mode: str):
        """Sets the mode of this VisualQueryPaletteItemTypes.

        Indicates whether to allow or deny the identified item types in the palette.  # noqa: E501

        :param mode: The mode of this VisualQueryPaletteItemTypes.
        :type mode: str
        """
        allowed_values = ["ALLOW", "DENY"]  # noqa: E501
        if mode not in allowed_values:
            raise ValueError(
                "Invalid value for `mode` ({0}), must be one of {1}"
                .format(mode, allowed_values)
            )

        self._mode = mode

    @property
    def item_type_ids(self) -> List[str]:
        """Gets the item_type_ids of this VisualQueryPaletteItemTypes.

        The identifiers of the item types that the specification affects.  # noqa: E501

        :return: The item_type_ids of this VisualQueryPaletteItemTypes.
        :rtype: List[str]
        """
        return self._item_type_ids

    @item_type_ids.setter
    def item_type_ids(self, item_type_ids: List[str]):
        """Sets the item_type_ids of this VisualQueryPaletteItemTypes.

        The identifiers of the item types that the specification affects.  # noqa: E501

        :param item_type_ids: The item_type_ids of this VisualQueryPaletteItemTypes.
        :type item_type_ids: List[str]
        """
        if item_type_ids is None:
            raise ValueError("Invalid value for `item_type_ids`, must not be `None`")  # noqa: E501

        self._item_type_ids = item_type_ids
