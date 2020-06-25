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

    // TODO: parse `conditions` and get boro and law_cat values
    
    // TODO: build the url
    // e.g const url = `${URL}&$limit=${limitValue}&$where=boro_nm="${boro}" AND law_cat_cd="${law_cat}"`;

    const includeItemTypes = []; // TODO: add typeIds you want to be returned (in this case complaint typeId)
    // e.g includeItemTypes = ["ET1"]

    return runRequest(url, includeItemTypes);
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
