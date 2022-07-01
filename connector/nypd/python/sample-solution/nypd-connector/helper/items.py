# MIT License
#
# © N.Harris Computer Corporation (2022)
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