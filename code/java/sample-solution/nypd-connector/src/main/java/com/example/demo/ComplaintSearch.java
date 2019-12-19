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

package com.example.demo;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.demo.rest.transport.request.RequestCondition;

public class ComplaintSearch {
    public String searchTerm;

    /**
     * Used to conduct a complaint search using conditions provided by the user
     * @param conditions Contains queries in the form of conditions provided by the user
     * @return The search conditions as a complaintSearch
     */
    public static ComplaintSearch parse(Collection<RequestCondition> conditions) {
        final Map<String, RequestCondition> asMap = conditions.stream().collect(Collectors.toMap(c -> c.id, c -> c));
        RequestCondition searchTerm = asMap.get("searchTerm");
        ComplaintSearch complaintSearch = new ComplaintSearch();
        if (searchTerm != null) {
            complaintSearch.searchTerm = searchTerm.value.toString();
        }
        return complaintSearch;
    }
}
