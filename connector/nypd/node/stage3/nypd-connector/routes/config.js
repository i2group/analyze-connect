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

const express = require("express");
const router = express.Router();
const fs = require("fs");
const appRoot = require("app-root-path");

const jsonFile = appRoot + "/public/json/config.json";
const content = fs.readFileSync(jsonFile, "utf-8");
const json = JSON.parse(content);

/* GET config.json */
router.get("/config", (req, res) => {
  res.status(200).json(json);
});

module.exports = router;
