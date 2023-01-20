# MIT License
#
# © N.Harris Computer Corporation (2023)
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

class ResponseBaseData:
    """
    A template for all entities and links.
    
    Attributes:
        id (str): A unique identifier for the entity or link.
        typeId (str): The type ID of the entity or link corresponding to the schema.
        properties (dict): The properties of the entity or link, keys corresponding to property IDs in schema.
    """
    def __init__(self, id, typeId, properties):
        self.id = id
        self.typeId = typeId
        self.properties = properties

class Entity(ResponseBaseData):
    """
    A template for all entities, inheriting attributes from ResponseBaseData.
    """
    pass

class Link(ResponseBaseData):
    """
    A template for all links, inheriting attributes from ResponseBaseData.

    TODO: Complete this class according to the SPI.
    """