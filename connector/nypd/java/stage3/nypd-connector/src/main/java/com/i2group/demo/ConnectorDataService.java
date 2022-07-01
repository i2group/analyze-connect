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

package com.i2group.demo;

import com.i2group.demo.rest.transport.ConnectorResponse;
import com.i2group.demo.rest.transport.EntityData;
import com.i2group.demo.rest.transport.LinkData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/** A class which demonstrates a connector response with dummy data. */
@Service
public class ConnectorDataService {

  /**
   * Manually populate a list of entities and links to show in Analyst's Notebook Premium.
   *
   * @return A response containing entities and links.
   */
  public ConnectorResponse retrieveTestData() {
    final List<EntityData> entities = new ArrayList<>();
    final List<LinkData> links = new ArrayList<>();

    // TODO: Populate entity and link lists with some test data and see that it is returned to ANBP

    final ConnectorResponse connectorResponse = new ConnectorResponse();
    connectorResponse.entities = entities;
    connectorResponse.links = links;
    return connectorResponse;
  }
}
