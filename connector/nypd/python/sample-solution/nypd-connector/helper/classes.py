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

from spi.models.i2_connect_entity_data import I2ConnectEntityData
from spi.models.i2_connect_link_data import I2ConnectLinkData
from spi.models.link_direction import LinkDirection
from spi.models.source_reference import SourceReference
from spi.models.source_reference_info import SourceReferenceInfo
from spi.models.geo_json_point import GeoJSONPoint

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

class Complaint(I2ConnectEntityData):
    """
    A base class for Complaint entities.
    
    Attributes:
        entry (dict): One record from the external datasource.
    """
    def __init__(self, entry):
        id = get_id("COMP", entry)
        super().__init__(id=id,
                         type_id=type_ids['complaint'],
                         properties={
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
                         },
                         source_reference=generate_source_ref(entry.get('cmplnt_num')))

class Location(I2ConnectEntityData):
    """
    The base class for all Location entities.
    
    Attributes:
        entry (dict): One record from the external datasource.
    """
    def __init__(self, entry):
        precinct_code = entry.get('addr_pct_cd', '')
        borough_name = entry.get('boro_nm')
        id = "LOC" + precinct_code + borough_name
        super().__init__(id=id,
                         type_id=type_ids['location'],
                         properties={
                            'PT15': int(precinct_code) if precinct_code else None,
                            'PT16': borough_name,
                            'PT18': set_coordinates(entry)
                         },
                         source_reference=generate_source_ref(entry.get('cmplnt_num')))

class Victim(I2ConnectEntityData):
    """
    The base class for all Victim entities.
    
    Attributes:
        entry (dict): One record from the external datasource.
    """
    def __init__(self, entry):
        id = get_id("VIC", entry)
        super().__init__(id=id,
                         type_id=type_ids['person'],
                         properties={
                            'PT26': entry.get('vic_age_group'),
                            'PT27': entry.get('vic_race'),
                            'PT28': entry.get('vic_sex')
                         },
                         source_reference=generate_source_ref(entry.get('cmplnt_num')))

class Suspect(I2ConnectEntityData):
    """
    The base class for all Suspect entities.
    Attributes:
        entry (dict): One record from the external datasource.
    """
    def __init__(self, entry):
        id = get_id("SUSP", entry)
        super().__init__(id=id,
                         type_id=type_ids['person'],
                         properties={
                            'PT26': entry.get('susp_age_group'),
                            'PT27': entry.get('susp_race'),
                            'PT28': entry.get('susp_sex')
                         },
                         source_reference=generate_source_ref(entry.get('cmplnt_num')))

class LocatedAt(I2ConnectLinkData):
    """
    A template for location links, inheriting attributes from Link.
    """
    def __init__(self, entry, from_end_id, to_end_id):
        id = get_id("LA", entry)
        super().__init__(id=id,
                         type_id=type_ids['located_at'],
                         source_reference=generate_source_ref(entry.get('cmplnt_num')),
                         from_end_id=from_end_id,
                         to_end_id=to_end_id,
                         link_direction=LinkDirection.WITH)

class VictimOf(I2ConnectLinkData):
    """
    A template for victim links, inheriting attributes from Link.
    """
    def __init__(self, entry, from_end_id, to_end_id):
        id = get_id("VO", entry)
        super().__init__(id=id,
                         type_id=type_ids['victim_of'],
                         source_reference=generate_source_ref(entry.get('cmplnt_num')),
                         from_end_id=from_end_id,
                         to_end_id=to_end_id,
                         link_direction=LinkDirection.WITH)

class SuspectOf(I2ConnectLinkData):
    """
    A template for suspect links, inheriting attributes from Link.
    """
    def __init__(self, entry, from_end_id, to_end_id):
        id = get_id("SO", entry)
        super().__init__(id=id,
                         type_id=type_ids['suspect_of'],
                         source_reference=generate_source_ref(entry.get('cmplnt_num')),
                         from_end_id=from_end_id,
                         to_end_id=to_end_id,
                         link_direction=LinkDirection.WITH)

def sanitizeTime(time):
    if time.__eq__("(null)"):
        return None

def get_id(base, entry):
    return base + entry.get('cmplnt_num')

def generate_source_ref(complaint_num):
    source_reference_info = SourceReferenceInfo(
        name="NYPD Complaint Dataset",
        type="Open source data",
        description="A source reference to the corresponding record from the NYPD Complaint Dataset.",
        location=f"{base_url}?$where=cmplnt_num={complaint_num}",
        image=resource_image
    )
    return SourceReference(source_reference_info)

def set_coordinates(entry):
    longitude = entry.get('longitude')
    latitude = entry.get('latitude')

    if longitude and latitude:
        return GeoJSONPoint('Point', [entry.get('longitude'), entry.get('latitude')]).to_dict()
    else:
        return None
