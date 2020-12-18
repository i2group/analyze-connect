/********************************************************************************
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
# ********************************************************************************/

const socrata = require("../socrata-config.js");

module.exports = {
  complaintTypeId: "ET1",
  locationTypeId: "ET2",
  personTypeId: "ET3",
  locatedAtTypeId: "LT1",
  victimOfTypeId: "LT2",
  suspectOfTypeId: "LT3",

  getAllEntityTypeIds: function() {
    return [this.complaintTypeId, this.locationTypeId, this.personTypeId];
  },

  getAllLinkTypeIds: function() {
    return [this.locatedAtTypeId, this.victimOfTypeId, this.suspectOfTypeId];
  },

  getAllTypeIds: function() {
    let allEntityTypeIds = this.getAllEntityTypeIds();
    let allLinkTypeIds = this.getAllLinkTypeIds();
    return allEntityTypeIds.concat(allLinkTypeIds);
  },

  createComplaint: function(data, id) {
    return {
      id,
      typeId: this.complaintTypeId,
      properties: {
        PT1: data.cmplnt_num,
        PT6: data.crm_atpt_cptd_cd,
        PT9: parseInt(data.ky_cd, 10),
        PT10: data.law_cat_cd,
        PT11: data.ofns_desc,
        PT7: parseInt(data.jurisdiction_code, 10)
      },
      sourceReference: getExampleSourceRef(id, data.cmplnt_num)
    };
  },

  createLocation: function(data, id) {
    return {
      id,
      typeId: this.locationTypeId,
      properties: {
        PT15: parseInt(data.addr_pct_cd, 10),
        PT16: data.boro_nm,
        PT18: {
          type: "Point",
          coordinates: [data.latitude, data.longitude]
        }
      },
      sourceReference: getExampleSourceRef(id, data.cmplnt_num)
    };
  },

  createVictim: function(data, id) {
    return {
      id,
      typeId: this.personTypeId,
      properties: {
        PT26: data.vic_age_group,
        PT27: data.vic_race,
        PT28: data.vic_sex
      },
      sourceReference: getExampleSourceRef(id, data.cmplnt_num)
    };
  },

  createSuspect: function(data, id) {
    return {
      id,
      typeId: this.personTypeId,
      properties: {
        PT26: data.susp_age_group,
        PT27: data.susp_race,
        PT28: data.susp_sex
      },
      sourceReference: getExampleSourceRef(id, data.cmplnt_num)
    };
  },

  locatedAt: function(data, locationId, complaintId) {
    const id = `ComplaintToLocation: ${data.cmplnt_num}`;
    return {
      id,
      typeId: this.locatedAtTypeId,
      fromEndId: complaintId,
      toEndId: locationId,
      linkDirection: "WITH",
      sourceReference: getExampleSourceRef(id, data.cmplnt_num)
    };
  },

  victimOf: function(data, victimId, complaintId) {
    const id = `ComplaintToVictim: ${data.cmplnt_num}`;
    return {
      id,
      typeId: this.victimOfTypeId,
      toEndId: complaintId,
      fromEndId: victimId,
      linkDirection: "WITH",
      sourceReference: getExampleSourceRef(id, data.cmplnt_num)
    };
  },

  suspectOf: function(data, suspectId, complaintId) {
    const id = `ComplaintToSuspect: ${data.cmplnt_num}`;
    return {
      id,
      typeId: this.suspectOfTypeId,
      toEndId: complaintId,
      fromEndId: suspectId,
      linkDirection: "WITH",
      sourceReference: getExampleSourceRef(id, data.cmplnt_num)
    };
  }
};

function getExampleSourceRef(id, complaintNum){
  return {
    id: `SR_${id}`,
    source: {
      name: "NYPD Complaint Dataset",
      type: "Open source data",
      description: "A source reference to the corresponding record from the NYPD Complaint Dataset.",
      location: `${socrata.url}?$where=cmplnt_num=${complaintNum}`,
      image: "https://github.ibm.com/ibmi2/Connect-Examples/tree/master/docs/images/nypd-dataset-webpage.png?raw=true"
    }
  }
}