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
    return runRequest(url);
  }

};

/** 
 * Run request and returns a JSON object with entities and links
 * @param {string} url - URL used to query NYPD complaint dataset
*/
async function runRequest(url) {
  const data = JSON.parse(await doRequest(url));
  const entities = [],
    links = [];
  
  data.forEach(row => {
    // TODO: Create Entities and Links for each row of the data received
    console.log(row); // remove this since it's just for debugging
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
