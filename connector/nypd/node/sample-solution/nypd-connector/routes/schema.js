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

const express = require("express");
const router = express.Router();
const appRoot = require("app-root-path");

const schemaFile = appRoot + "/public/xml/nypd-complaint-data-schema.xml";

/* GET schema xml file. */
router.get("/schema", (req, res) => {
  res.status(200).type("application/xml").sendFile(schemaFile);
});

module.exports = router;
