# Licensed Materials - Property of IBM
# (C) Copyright IBM Corporation 2019. All Rights Reserved
#
# This program and the accompanying materials are made available under the
# terms of the Eclipse Public License 2.0 which is available at
# http://www.eclipse.org/legal/epl-2.0.
#
# US Government Users Restricted Rights - Use, duplication or
# disclosure restricted by GSA ADP Schedule Contract with IBM Corp.

from flask import Flask
from helper.controller import controller

app = Flask(__name__)
app.config['UPLOAD_FOLDER'] = 'static'
app.register_blueprint(controller)

if __name__ == '__main__':
    app.run(host='localhost', port=9081)