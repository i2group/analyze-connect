/********************************************************************************
# * Licensed Materials - Property of IBM
# * (C) Copyright IBM Corporation 2019. All Rights Reserved
# *
# * This program and the accompanying materials are made available under the
# * terms of the Eclipse Public License 2.0 which is available at
# * http://www.eclipse.org/legal/epl-2.0.
# *
# * US Government Users Restricted Rights - Use, duplication or
# * disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
# *
# ********************************************************************************/

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
        PT1: parseInt(data.cmplnt_num, 10),
        PT6: data.crm_atpt_cptd_cd,
        PT9: parseInt(data.ky_cd, 10),
        PT10: data.law_cat_cd,
        PT11: data.ofns_desc,
        PT7: parseInt(data.jurisdiction_code, 10)
      }
    };
  },

  createLocation: function(data, id) {
    return {
      id,
      typeId: this.locationTypeId,
      properties: {
        PT15: parseInt(data.addr_pct_cd, 10),
        PT16: data.boro_nm
      }
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
      }
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
      }
    };
  },

  locatedAt: function(data, locationId, complaintId) {
    return {
      id: `ComplaintToLocation: ${data.cmplnt_num}`,
      typeId: this.locatedAtTypeId,
      fromEndId: complaintId,
      toEndId: locationId,
      linkDirection: "WITH"
    };
  },

  victimOf: function(data, victimId, complaintId) {
    return {
      id: `ComplaintToVictim: ${data.cmplnt_num}`,
      typeId: this.victimOfTypeId,
      toEndId: complaintId,
      fromEndId: victimId,
      linkDirection: "WITH"
    };
  },

  suspectOf: function(data, suspectId, complaintId) {
    return {
      id: `ComplaintToSuspect: ${data.cmplnt_num}`,
      typeId: this.suspectOfTypeId,
      toEndId: complaintId,
      fromEndId: suspectId,
      linkDirection: "WITH"
    };
  }
};
