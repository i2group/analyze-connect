# Licensed Materials - Property of IBM
# (C) Copyright IBM Corporation 2019. All Rights Reserved
#
# This program and the accompanying materials are made available under the
# terms of the Eclipse Public License 2.0 which is available at
# http://www.eclipse.org/legal/epl-2.0.
#
# US Government Users Restricted Rights - Use, duplication or
# disclosure restricted by GSA ADP Schedule Contract with IBM Corp.

import yaml

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
            'PT1': int(entry.get('cmplnt_num')),
            'PT2': entry.get('cmplnt_fr_dt')[:10] if entry.get('cmplnt_fr_dt') else None,
            'PT3': entry.get('cmplnt_to_dt')[:10] if entry.get('cmplnt_to_dt') else None,
            'PT4': entry.get('cmplnt_fr_tm'),
            'PT5': entry.get('cmplnt_to_tm') if entry.get('cmplnt_to_tm') else None,
            'PT6': entry.get('crm_atpt_cptd_cd'),
            'PT7': int(float(entry.get('jurisdiction_code'))) if entry.get('jurisdiction_code') else None,
            'PT8': entry.get('juris_desc'),
            'PT9': int(entry.get('ky_cd')) if entry.get('ky_cd') else None,
            'PT10': entry.get('law_cat_cd'),
            'PT11': entry.get('ofns_desc'),
            'PT12': int(float(entry.get('pd_cd'))) if entry.get('pd_cd') else None,
            'PT13': entry.get('pd_desc'),
            'PT14': entry.get('rpt_dt')[:10] if entry.get('rpt_dt') else None,
            'PT29': entry.get('loc_of_occur_desc') if entry.get('loc_of_occur_desc') else None,
            'PT18': float(entry.get('latitude')) if entry.get('latitude') else None,
            'PT30': float(entry.get('longitude')) if entry.get('longitude') else None,
        }, generate_source_ref(id, entry.get('cmplnt_num')))

class Location(Entity):
    """
    The base class for all Location entities.
    
    Attributes:
        entry (dict): One record from the external datasource.
    """
    def __init__(self, entry):
        id = get_id("LOC", entry)
        super().__init__(id, type_ids['location'], {
            'PT15': int(entry.get('addr_pct_cd')) if entry.get('addr_pct_cd') else None,
            'PT16': entry.get('boro_nm'),
            'PT17': entry.get('hadevelopt'),
            'PT19': entry.get('parks_nm'),
            'PT20': entry.get('patrol_boro'),
            'PT21': entry.get('prem_typ_desc'),
            'PT22': entry.get('station_name'),
            'PT23': int(float(entry.get('transit_district'))) if entry.get('transit_district') else None,
        }, generate_source_ref(id, entry.get('cmplnt_num')))

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
        }, generate_source_ref(id, entry.get('cmplnt_num')))

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
        }, generate_source_ref(id, entry.get('cmplnt_num')))

class LocatedAt(Link):
    """
    A template for location links, inheriting attributes from Link.
    """
    def __init__(self, entry):
        id = get_id("LA", entry)
        super().__init__("LA" + id, type_ids['located_at'], None, generate_source_ref(id, entry.get('cmplnt_num')))

class VictimOf(Link):
    """
    A template for victim links, inheriting attributes from Link.
    """
    def __init__(self, entry):
        id = get_id("VO", entry)
        super().__init__("VO" + id, type_ids['victim_of'], None, generate_source_ref(id, entry.get('cmplnt_num')))

class SuspectOf(Link):
    """
    A template for suspect links, inheriting attributes from Link.
    """
    def __init__(self, entry):
        id = get_id("SO", entry)
        super().__init__("SO" + id, type_ids['suspect_of'], None, generate_source_ref(id, entry.get('cmplnt_num')))

def get_id(base, entry):
    id = int(entry.get('cmplnt_num'))
    return base + str(id)

def generate_source_ref(id, complaint_num):
    return {
        'id': 'SR_' + str(id),
        'source': {
            'name': "NYPD Complaint Dataset",
            'type': "Open source data",
            'description': "A source reference to the corresponding record from the NYPD Complaint Dataset.",
            'location': f"{base_url}?$where=cmplnt_num={complaint_num}",
            'image': "https://github.ibm.com/ibmi2/Connect-Examples/tree/master/docs/images/nypd-dataset-webpage.png?raw=true"
        }
    }