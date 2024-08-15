/*
* MIT License
*
* © N.Harris Computer Corporation (2024)
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

package com.i2group.connector.spi.rest.transport;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.i2group.connector.spi.rest.transport.DaodSeedEntityData;
import com.i2group.connector.spi.rest.transport.DaodSeedLinkData;
import com.i2group.connector.spi.rest.transport.ItemType;
import com.i2group.connector.spi.rest.transport.SemanticSeed;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

/**
 * The data from all the records that are to be used as seeds in a search operation.
 */
public class DaodSeeds {

  @Valid
  public List<@Valid ItemType> allItemTypes;

  @Valid
  public List<@Valid DaodSeedEntityData> entities;

  @Deprecated
  @Valid
  public Map<String, ItemType> itemTypes = new HashMap<>();

  @Valid
  public List<@Valid DaodSeedLinkData> links;

  @Valid
  public Map<String, List<@Valid SemanticSeed>> semanticData = new HashMap<>();

  public DaodSeeds allItemTypes(List<@Valid ItemType> allItemTypes) {
    this.allItemTypes = allItemTypes;
    return this;
  }

  public DaodSeeds addAllItemTypesItem(ItemType allItemTypesItem) {
    if (this.allItemTypes == null) {
      this.allItemTypes = new ArrayList<>();
    }
    this.allItemTypes.add(allItemTypesItem);
    return this;
  }

  /**
   * Schema information for the item types of the records that contributed to the data in **entities** and **links**, which might come from different schemas.
   * @return allItemTypes
  */
  @Valid 
  @JsonProperty("allItemTypes")
  public List<@Valid ItemType> getAllItemTypes() {
    return allItemTypes;
  }

  public void setAllItemTypes(List<@Valid ItemType> allItemTypes) {
    this.allItemTypes = allItemTypes;
  }

  public DaodSeeds entities(List<@Valid DaodSeedEntityData> entities) {
    this.entities = entities;
    return this;
  }

  public DaodSeeds addEntitiesItem(DaodSeedEntityData entitiesItem) {
    if (this.entities == null) {
      this.entities = new ArrayList<>();
    }
    this.entities.add(entitiesItem);
    return this;
  }

  /**
   * Data from the entity records that were specified as seeds for the request.
   * @return entities
  */
  @Valid 
  @JsonProperty("entities")
  public List<@Valid DaodSeedEntityData> getEntities() {
    return entities;
  }

  public void setEntities(List<@Valid DaodSeedEntityData> entities) {
    this.entities = entities;
  }

  public DaodSeeds itemTypes(Map<String, ItemType> itemTypes) {
    this.itemTypes = itemTypes;
    return this;
  }

  public DaodSeeds putItemTypesItem(String key, ItemType itemTypesItem) {
    if (this.itemTypes == null) {
      this.itemTypes = new HashMap<>();
    }
    this.itemTypes.put(key, itemTypesItem);
    return this;
  }

  /**
   * Schema information for the item types of the records that contributed to the data in **entities** and **links**, keyed by item type identifier.  **Deprecated:** This field will be removed in a future release. Use the contents of **allItemTypes** instead of this field. Do not rely on the value of this field if it is set.
   * @return itemTypes
   * @deprecated
  */
  @Valid 
  @JsonProperty("itemTypes")
  @Deprecated
  public Map<String, ItemType> getItemTypes() {
    return itemTypes;
  }

  /**
   * @deprecated
  */
  @Deprecated
  public void setItemTypes(Map<String, ItemType> itemTypes) {
    this.itemTypes = itemTypes;
  }

  public DaodSeeds links(List<@Valid DaodSeedLinkData> links) {
    this.links = links;
    return this;
  }

  public DaodSeeds addLinksItem(DaodSeedLinkData linksItem) {
    if (this.links == null) {
      this.links = new ArrayList<>();
    }
    this.links.add(linksItem);
    return this;
  }

  /**
   * Data from the link records that were specified as seeds for the request.
   * @return links
  */
  @Valid 
  @JsonProperty("links")
  public List<@Valid DaodSeedLinkData> getLinks() {
    return links;
  }

  public void setLinks(List<@Valid DaodSeedLinkData> links) {
    this.links = links;
  }

  public DaodSeeds semanticData(Map<String, List<@Valid SemanticSeed>> semanticData) {
    this.semanticData = semanticData;
    return this;
  }

  public DaodSeeds putSemanticDataItem(String key, List<@Valid SemanticSeed> semanticDataItem) {
    if (this.semanticData == null) {
      this.semanticData = new HashMap<>();
    }
    this.semanticData.put(key, semanticDataItem);
    return this;
  }

  /**
   * Data from the semantic property types that were specified as seeds for the request.
   * @return semanticData
  */
  @Valid 
  @JsonProperty("semanticData")
  public Map<String, List<@Valid SemanticSeed>> getSemanticData() {
    return semanticData;
  }

  public void setSemanticData(Map<String, List<@Valid SemanticSeed>> semanticData) {
    this.semanticData = semanticData;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DaodSeeds daodSeeds = (DaodSeeds) o;
    return Objects.equals(this.allItemTypes, daodSeeds.allItemTypes) &&
        Objects.equals(this.entities, daodSeeds.entities) &&
        Objects.equals(this.itemTypes, daodSeeds.itemTypes) &&
        Objects.equals(this.links, daodSeeds.links) &&
        Objects.equals(this.semanticData, daodSeeds.semanticData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(allItemTypes, entities, itemTypes, links, semanticData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DaodSeeds {\n");
    sb.append("    allItemTypes: ").append(toIndentedString(allItemTypes)).append("\n");
    sb.append("    entities: ").append(toIndentedString(entities)).append("\n");
    sb.append("    itemTypes: ").append(toIndentedString(itemTypes)).append("\n");
    sb.append("    links: ").append(toIndentedString(links)).append("\n");
    sb.append("    semanticData: ").append(toIndentedString(semanticData)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

