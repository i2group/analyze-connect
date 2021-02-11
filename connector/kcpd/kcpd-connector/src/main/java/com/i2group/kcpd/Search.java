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

package com.i2group.kcpd;

import com.i2group.kcpd.rest.transport.request.RequestCondition;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Search {
  public String searchTerm;

  private Search() {}

  /**
   * Used to conduct a search using conditions provided by the user
   *
   * @param conditions Contains queries in the form of conditions provided by the user
   * @return The search conditions as a search
   */
  public static Search parse(Collection<RequestCondition> conditions) {
    final Map<String, RequestCondition> asMap =
        conditions.stream().collect(Collectors.toMap(c -> c.id, c -> c));
    Search search = new Search();
    
    Set<String> searchId = asMap.keySet();

    searchId.iterator().forEachRemaining(id -> {
      switch(id) {
        case "report_no":
          search.searchTerm = asMap.get("report_no").value.toString();
          break;
        case "offense":
          search.searchTerm = asMap.get("offense").value.toString();
          break;
        case "zip_code":
          search.searchTerm = asMap.get("zip_code").value.toString();
          break;
        default:
          break;
      }
    });
    return search;
  }
}
