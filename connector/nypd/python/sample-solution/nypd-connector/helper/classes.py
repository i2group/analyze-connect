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

import yaml
import os

type_ids = {
    'complaint': 'ET1',
    'location': 'ET2',
    'person': 'ET3',
    
    'located_at': 'LT1',
    'victim_of': 'LT2',
    'suspect_of': 'LT3'
}

with open('static/application.yml') as yml_file:
    config = yaml.safe_load(yml_file)

base_url = config['socrata']['url']
resource_image = os.path.abspath("static/nypd-dataset-webpage.png")

class ResponseBaseData:
    """
    A template for all entities and links.
    
    Attributes:
        id (str): A unique identifier for the entity or link.
        typeId (str): The type ID of the entity or link corresponding to the schema.
        properties (dict): The properties of the entity or link, keys corresponding to property IDs in schema.
    """
    def __init__(self, id, typeId, properties, sourceReference):
        self.id = id
        self.typeId = typeId
        self.properties = properties
        self.sourceReference = sourceReference

class Entity(ResponseBaseData):
    """
    A template for all entities, inheriting attributes from ResponseBaseData.
    """
    pass

class Link(ResponseBaseData):
    """
    A template for all links, inheriting attributes from ResponseBaseData.
    
    Attributes:
        fromEndId (str): The ID of the entity from which the link is originating from.
        toEndId (str): The ID of the entity to which the link is directed to.
        linkDirection (str): The direction of the link (i.e. WITH, BOTH, AGAINST or NONE)
    """
    def setLink(self, fromEndId, toEndId, linkDirection):
        self.fromEndId = fromEndId
        self.toEndId = toEndId
        self.linkDirection = linkDirection

class Complaint(Entity):
    """
    A base class for Complaint entities.
    
    Attributes:
        entry (dict): One record from the external datasource.
    """
    def __init__(self, entry):
        id = get_id("COMP", entry)
        super().__init__(id, type_ids['complaint'], {
            'PT1': entry.get('cmplnt_num'),
            'PT2': entry.get('cmplnt_fr_dt')[:10] if entry.get('cmplnt_fr_dt') else None,
            'PT3': entry.get('cmplnt_to_dt')[:10] if entry.get('cmplnt_to_dt') else None,
            'PT4': sanitizeTime(entry.get('cmplnt_fr_tm')),
            'PT5': sanitizeTime(entry.get('cmplnt_to_tm')) if entry.get('cmplnt_to_tm') else None,
            'PT6': entry.get('crm_atpt_cptd_cd'),
            'PT7': int(float(entry.get('jurisdiction_code'))) if entry.get('jurisdiction_code') else None,
            'PT8': entry.get('juris_desc'),
            'PT9': int(entry.get('ky_cd')) if entry.get('ky_cd') else None,
            'PT10': entry.get('law_cat_cd'),
            'PT11': entry.get('ofns_desc'),
            'PT13': entry.get('pd_desc'),
            'PT14': entry.get('rpt_dt')[:10] if entry.get('rpt_dt') else None,
            'PT29': entry.get('loc_of_occur_desc') if entry.get('loc_of_occur_desc') else None
        }, generate_source_ref(entry.get('cmplnt_num')))

class Location(Entity):
    """
    The base class for all Location entities.
    
    Attributes:
        entry (dict): One record from the external datasource.
    """
    def __init__(self, entry):
        precinct_code = entry.get('addr_pct_cd', '')
        borough_name = entry.get('boro_nm')
        id = "LOC"+ precinct_code + borough_name
        super().__init__(id, type_ids['location'], {
            'PT15': int(precinct_code) if precinct_code else None,
            'PT16': borough_name,
            'PT18': GeospatialPoint(entry.get('longitude'), entry.get('latitude')).__dict__ 
                    if entry.get('longitude') and entry.get('latitude') else None,
        }, generate_source_ref(entry.get('cmplnt_num')))

class GeospatialPoint:
    """
    Represents a point location and serializes according to the GeoJSON specification.

    Attributes:
        type (str): The type of GeoJSON entity. Will always be "Point".
        coordinates (tuple): Longitude and Latitude.
    """
    def __init__(self, longitude, latitude):
        self.type = "Point"
        self.coordinates = (longitude, latitude)

class Victim(Entity):
    """
    The base class for all Victim entities.
    
    Attributes:
        entry (dict): One record from the external datasource.
    """
    def __init__(self, entry):
        id = get_id("VIC", entry)
        super().__init__(id, type_ids['person'], {
            'PT26': entry.get('vic_age_group'),
            'PT27': entry.get('vic_race'),
            'PT28': entry.get('vic_sex')
        }, generate_source_ref(entry.get('cmplnt_num')))

class Suspect(Entity):
    """
    The base class for all Suspect entities.
    Attributes:
        entry (dict): One record from the external datasource.
    """
    def __init__(self, entry):
        id = get_id("SUSP", entry)
        super().__init__(id, type_ids['person'], {
            'PT26': entry.get('susp_age_group'),
            'PT27': entry.get('susp_race'),
            'PT28': entry.get('susp_sex')
        }, generate_source_ref(entry.get('cmplnt_num')))

class LocatedAt(Link):
    """
    A template for location links, inheriting attributes from Link.
    """
    def __init__(self, entry):
        id = get_id("LA", entry)
        super().__init__("LA" + id, type_ids['located_at'], None, generate_source_ref(entry.get('cmplnt_num')))

class VictimOf(Link):
    """
    A template for victim links, inheriting attributes from Link.
    """
    def __init__(self, entry):
        id = get_id("VO", entry)
        super().__init__("VO" + id, type_ids['victim_of'], None, generate_source_ref(entry.get('cmplnt_num')))

class SuspectOf(Link):
    """
    A template for suspect links, inheriting attributes from Link.
    """
    def __init__(self, entry):
        id = get_id("SO", entry)
        super().__init__("SO" + id, type_ids['suspect_of'], None, generate_source_ref(entry.get('cmplnt_num')))

def sanitizeTime(time):
    if time.__eq__("(null)"):
        return None

def get_id(base, entry):
    return base + entry.get('cmplnt_num')

def generate_source_ref(complaint_num):
    return {
        'source': {
            'name': "NYPD Complaint Dataset",
            'type': "Open source data",
            'description': "A source reference to the corresponding record from the NYPD Complaint Dataset.",
            'location': f"{base_url}?$where=cmplnt_num={complaint_num}",
            'image': resource_image
        }
    }