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

from helper.classes import (Victim, Suspect, Complaint, Location,
    LocatedAt, VictimOf, SuspectOf)

def create_complaint(entry):
    complaint = Complaint(entry)
    return complaint.__dict__

def create_location(entry):
    location = Location(entry)
    return location.__dict__

def create_victim(entry):
    victim = Victim(entry)
    return victim.__dict__

def create_suspect(entry):
    suspect = Suspect(entry)
    return suspect.__dict__

def create_location_link(entry, complaint, location):
    link = LocatedAt(entry)
    link.setLink(complaint['id'], location['id'], 'WITH')
    return link.__dict__

def create_victim_link(entry, victim, complaint):
    link = VictimOf(entry)
    link.setLink(victim['id'], complaint['id'], 'WITH')
    return link.__dict__

def create_suspect_link(entry, suspect, complaint):
    link = SuspectOf(entry)
    link.setLink(suspect['id'], complaint['id'], 'WITH')
    return link.__dict__