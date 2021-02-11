/********************************************************************************
# * Licensed Materials - Property of IBM
# * (C) Copyright IBM Corporation 2021. All Rights Reserved
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

  /**
   * Get test data
   */
  getTestData: function() {
    const entities = [], links = [];
    const complaint = {
        id: "complaint1",
        typeId: "ET1",
        version: "1L",
        properties: [
            {
                // STRING type
                "PT1": "660160752"
            },
            {
                // STRING type (gets converted to LocalDate)
                "PT2": "2017-05-25"
            },
            {
                // STRING type
                "PT10": "Felony"
            },
            {
                // FLOAT type
                "PT18": 40.512460100000055
            }
        ]
    };
    entities.push(complaint);
    return { entities, links};
  }

};
