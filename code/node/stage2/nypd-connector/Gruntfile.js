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

"use strict";

const request = require("request");

module.exports = grunt => {
  // show elapsed time at the end
  require("time-grunt")(grunt);
  // load all grunt tasks
  require("load-grunt-tasks")(grunt);

  const reloadPort = 35729;
  let files;

  grunt.initConfig({
    pkg: grunt.file.readJSON("package.json"),
    develop: {
      server: {
        file: "bin/www"
      }
    },
    watch: {
      options: {
        nospawn: true,
        livereload: reloadPort
      },
      server: {
        files: ["bin/www", "app.js", "routes/*.js"],
        tasks: ["develop", "delayed-livereload"]
      },
      js: {
        files: ["public/js/*.js"],
        options: {
          livereload: reloadPort
        }
      },
      css: {
        files: ["public/css/*.css"],
        options: {
          livereload: reloadPort
        }
      },
      views: {
        files: ["views/*.ejs"],
        options: {
          livereload: reloadPort
        }
      }
    }
  });

  grunt.config.requires("watch.server.files");
  files = grunt.config("watch.server.files");
  files = grunt.file.expand(files);

  grunt.registerTask(
    "delayed-livereload",
    "Live reload after the node server has restarted.",
    () => {
      const done = this.async();
      setTimeout(() => {
        request.get(
          "http://localhost:" +
            reloadPort +
            "/changed?files=" +
            files.join(","),
          (err, res) => {
            const reloaded = !err && res.statusCode === 200;
            if (reloaded) {
              grunt.log.ok("Delayed live reload successful.");
            } else {
              grunt.log.error("Unable to make a delayed live reload.");
            }
            done(reloaded);
          }
        );
      }, 500);
    }
  );

  grunt.registerTask("default", ["develop", "watch"]);
};
