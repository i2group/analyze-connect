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

const express = require("express");
const router = express.Router();
const socrataDataService = require("../helpers/socrata-data-service");

/* Acquire /all-data */
router.post("/all", async (req, res) => {
  res.status(200).json(await socrataDataService.getAllData(100));
});

/* Acquire /find-complaint */
router.post("/search", async (req, res) => {
  res
    .status(200)
    .json(
      await socrataDataService.findComplaint(50, req.body.payload.conditions)
    );
});


module.exports = router;
