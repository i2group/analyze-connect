#********************************************************************************
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
# ********************************************************************************

def retrieve_test_data():
    """
    Used to populate entity lists and link lists to return to ANBP.

    Returns:
        dict: A response with the entities and links.
    """
    entities = []
    links = []

    # TODO: Populate entity and link lists with some dummy data and see that
    # it is returned to ANBP

    response = []
    response['entities'] = entities
    response['links'] = links
    return response