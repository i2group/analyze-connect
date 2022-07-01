/*
 * MIT License
 *
 * © N.Harris Computer Corporation (2022)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
