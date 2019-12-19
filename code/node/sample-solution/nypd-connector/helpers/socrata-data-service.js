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

const request = require("request");
const socrata = require("../socrata-config.js");
const items = require("./socrata-items.js");

const URL = `${socrata.url}?$$app_token=${socrata.token}`;

module.exports = {
  
  /**
   * Get all data from the NYPD complaint dataset
   * @param {Integer} limitValue - Used to set the max number of rows retured from NYPD complaint dataset
   */
  getAllData: function(limitValue) {
    const url = `${URL}&$limit=${limitValue}`;
    const includeItemTypes = items.getAllTypeIds();
    return runRequest(url, includeItemTypes);
  },

  /**
   * Expand the seeded entity
   * @param {Integer} limitValue - Used to set the max number of rows retured from NYPD complaint dataset
   * @param {Object} seeds - JSON object used to extract the seeded entity
   */
  expand: async function(limitValue, seeds) {
    const includeItemTypes = items.getAllTypeIds();
    const entitySeed = seeds.entities[0];
    const props = entitySeed.properties;
    const sourceId = entitySeed.sourceIds[0].key[2];
    // Set url based on the entity seed type ID
    let url = `${URL}&$limit=${limitValue}`;
    if (entitySeed.typeId === items.complaintTypeId) {
      url += `&$where=cmplnt_num=${props["PT1"]}`;
    } else if (entitySeed.typeId === items.locationTypeId) {
      url += `&$where=boro_nm="${props["PT16"]}" AND addr_pct_cd=${props["PT15"]}`;
    }
    includeItemTypes.splice(includeItemTypes.indexOf(entitySeed.typeId), 1);
    const resp = await runRequest(url, includeItemTypes);
    // Change End Ids to the entity seed id
    resp.links.forEach(link => {
      if (link.toEndId === sourceId) {
        link.toEndId = entitySeed.seedId;
      } else if (link.fromEndId === sourceId) {
        link.fromEndId = entitySeed.seedId;
      }
    });
    return resp;
  },

  
  /**
   * Expand the seeded entity with conditions
   * @param {Integer} limitValue - Used to set the max number of rows retured from NYPD complaint dataset
   * @param {Object} conditions - JSON object with conditions
   * @param {Object} seeds - JSON object used to extract the seeded entity
   */
  expandWithConditions: async function(limitValue, seeds, conditions) {
    const includeItemTypes = [items.locationTypeId, items.locatedAtTypeId];
    const entitySeed = seeds.entities[0];
    const props = entitySeed.properties;
    const sourceId = entitySeed.sourceIds[0].key[2];
    const person = conditions[0].value;
    // Set url based on the entity seed type ID
    const url = `${URL}&$limit=${limitValue}&$where=cmplnt_num=${props["PT1"]}`;
    if (person) {
      includeItemTypes.push(items.personTypeId);
      includeItemTypes.push(items.victimOfTypeId);
      includeItemTypes.push(items.suspectOfTypeId);
    }
    const resp = await runRequest(url, includeItemTypes);
    // Change End Ids to the entity seed id
    resp.links.forEach(link => {
      if (link.toEndId === sourceId) {
        link.toEndId = entitySeed.seedId;
      } else if (link.fromEndId === sourceId) {
        link.fromEndId = entitySeed.seedId;
      }
    });
    return resp;  
  },

  /**
   * Find complaints with the same law catagory
   * @param {Integer} limitValue - Used to set the max number of rows retured from NYPD complaint dataset
   * @param {Object} seeds - JSON object used to extract the seeded entity
   */
  findSimilarComplaint: function(limitValue, seeds) {
    const entitySeed = seeds.entities[0];
    const props = entitySeed.properties;
    const url = `${URL}&$limit=${limitValue}&$where=law_cat_cd="${props["PT10"]}"`;
    return runRequest(url, [items.complaintTypeId]);
  },

  /**
   * Find complaints with the same law category
   * @param {Integer} limitValue - Used to set the max number of rows retured from NYPD complaint dataset
   * @param {Array} conditions - List of conditions used to query NYPD complaint dataset
   */
  findComplaint: function(limitValue, conditions) {
    const boro = conditions[0].value,
      law_cat = conditions[1].value;
    const url = `${URL}&$limit=${limitValue}&$where=boro_nm="${boro}" AND law_cat_cd="${law_cat}"`;
    return runRequest(url, [items.complaintTypeId]);
  }
};

/** 
 * Run request and returns a JSON object with entities and links
 * @param {string} url - URL used to query NYPD complaint dataset
 * @param {Array} includeItemTypes - List of item type ids used to determine what items need to be returned 
*/
async function runRequest(url, includeItemTypes) {
  const data = JSON.parse(await doRequest(url));
  const entities = [],
    links = [],
    locationIds = [];
  // Create Entities and Links for each row of the data received
  data.forEach(row => {
    // Create Entity Ids
    const locationId = `Borough: ${row.boro_nm} Precinct: ${row.addr_pct_cd}`;
    const complaintId = `Complaint: ${row.cmplnt_num}`;
    const suspectId = `Suspect: ${row.cmplnt_num}`;
    const victimId = `Victim: ${row.cmplnt_num}`;
    // Create Entities
    if (
      includeItemTypes.includes(items.locationTypeId) &&
      !locationIds.includes(locationId)
    ) {
      entities.push(items.createLocation(row, locationId));
      locationIds.push(locationId);
    }
    if (includeItemTypes.includes(items.complaintTypeId)) {
      entities.push(items.createComplaint(row, complaintId));
    }
    if (includeItemTypes.includes(items.personTypeId)) {
      entities.push(items.createSuspect(row, suspectId));
    }
    if (includeItemTypes.includes(items.personTypeId)) {
      entities.push(items.createVictim(row, victimId));
    }
    // Create Links
    if (includeItemTypes.includes(items.locatedAtTypeId)) {
      links.push(items.locatedAt(row, locationId, complaintId));
    }
    if (includeItemTypes.includes(items.victimOfTypeId)) {
      links.push(items.victimOf(row, victimId, complaintId));
    }
    if (includeItemTypes.includes(items.suspectOfTypeId)) {
      links.push(items.suspectOf(row, suspectId, complaintId));
    }
  });
  return { entities, links };
}

/** 
 * Create a Promise from the request 
 * @param {string} url - URL used to query NYPD complaint dataset
*/
function doRequest(url) {
  return new Promise(function(resolve, reject) {
    request(url, function(error, res, body) {
      if (!error && res.statusCode == 200) {
        resolve(body);
      } else {
        reject(error);
      }
    });
  });
}
