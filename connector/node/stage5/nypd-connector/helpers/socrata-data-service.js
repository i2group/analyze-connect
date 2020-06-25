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

const URL = `${socrata.url}?$$app_token=${socrata.token}`;

module.exports = {
  
  /**
   * Get all data from the NYPD complaint dataset
   * @param {Integer} limitValue - Used to set the max number of rows retured from NYPD complaint dataset
   */
  getAllData: function(limitValue) {
    const url = `${URL}&$limit=${limitValue}`;
    const includeItemTypes = ["ET1", "ET2", "ET3", "LT1", "LT2", "LT3"];
    return runRequest(url, includeItemTypes);
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
    return runRequest(url, ["ET1"]);
  },

  /**
   * Find complaints with the same law catagory
   * @param {Integer} limitValue - Used to set the max number of rows retured from NYPD complaint dataset
   * @param {Object} seeds - JSON object used to extract the seeded entity
   */
  findSimilarComplaint: function(limitValue, seeds) {
    // TODO: extract seed
    // e.g const entitySeed = seeds.entities[0];
    
    // TODO: extract seed properties
    // e.g const props = entitySeed.properties;
    
    // TODO: build the URL
    // const url = `${URL}&$limit=${limitValue}&$where=law_cat_cd="${props["PT10"]}"`;
    
    // TODO: add typeIds you want to be returned (in this case complaint typeId)
    // e.g const includeItemTypes = ["ET1"];
    
    // TODO: run the request
    // return runRequest(url, includeItemTypes);
  },

  /**
   * Expand the seeded entity
   * @param {Integer} limitValue - Used to set the max number of rows retured from NYPD complaint dataset
   * @param {Object} seeds - JSON object used to extract the seeded entity
   */
  expand: async function(limitValue, seeds) {
    // TODO: extract seed
    // e.g const entitySeed = seeds.entities[0];
    
    // TODO: extract seed properties
    // e.g const props = entitySeed.properties;
    
    // TODO: extract sourceId
    // e.g const sourceId = entitySeed.sourceIds[0].key[2];
    
    // Set url based on the entity seed type ID
    // e.g let url = `${URL}&$limit=${limitValue}&$where=cmplnt_num=${props["PT1"]}`;
    
    // TODO: set includeItemTypes (in this case should be all of them exept the entity seed type id)
    // e.g const includeItemTypes = ["ET1", "ET2", "ET3", "LT1", "LT2", "LT3"];
    //     includeItemTypes.splice(includeItemTypes.indexOf(entitySeed.typeId), 1); // remove the entity seed typeId
    
    // TODO: run the request
    // const resp = await runRequest(url, includeItemTypes);
    
    // Change End Ids to the entity seed id
    // This is required for links to connect to the entity that is already on the chart
    // resp.links.forEach(link => {
    //   if (link.toEndId === sourceId) {
    //     link.toEndId = entitySeed.seedId;
    //   } else if (link.fromEndId === sourceId) {
    //     link.fromEndId = entitySeed.seedId;
    //   }
    // });
    // return resp;
  },

  
  /**
   * Expand the seeded entity with conditions
   * @param {Integer} limitValue - Used to set the max number of rows retured from NYPD complaint dataset
   * @param {Object} conditions - JSON object with conditions
   * @param {Object} seeds - JSON object used to extract the seeded entity
   */
  expandWithConditions: async function(limitValue, seeds, conditions) {
    // TODO: combine expand and findComplaint to make this work
    return { entities: [], links: [] };
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
    links = [];
  // Create Entities and Links for each row of the data received
  data.forEach(row => {
    // TODO: Create Entities and Links for each row of the data received
    //       Only create Entities and Links whose typeIds are in the includeItemTypes array
    // TODO: Add Entities and Links to the `entities` & `links` array
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
