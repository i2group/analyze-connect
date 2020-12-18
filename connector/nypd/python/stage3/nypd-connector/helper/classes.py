#********************************************************************************
# * Licensed Materials - Property of IBM
# * (C) Copyright IBM Corporation 2020. All Rights Reserved
# *
# * This program and the accompanying materials are made available under the
# * terms of the Eclipse Public License 2.0 which is available at
# * http://www.eclipse.org/legal/epl-2.0.
# *
# * US Government Users Restricted Rights - Use, duplication or
# * disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
# *
# ********************************************************************************

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