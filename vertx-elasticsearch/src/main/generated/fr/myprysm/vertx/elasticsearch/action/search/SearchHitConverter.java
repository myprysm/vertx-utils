/*
 * Copyright (c) 2014 Red Hat, Inc. and others
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package fr.myprysm.vertx.elasticsearch.action.search;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.search.SearchHit}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.search.SearchHit} original class using Vert.x codegen.
 */
public class SearchHitConverter {

  public static void fromJson(JsonObject json, SearchHit obj) {
    if (json.getValue("clusterAlias") instanceof String) {
      obj.setClusterAlias((String)json.getValue("clusterAlias"));
    }
    if (json.getValue("explanation") instanceof JsonObject) {
      obj.setExplanation(new fr.myprysm.vertx.elasticsearch.action.support.Explanation((JsonObject)json.getValue("explanation")));
    }
    if (json.getValue("fields") instanceof JsonObject) {
      java.util.Map<String, fr.myprysm.vertx.elasticsearch.action.support.DocumentField> map = new java.util.LinkedHashMap<>();
      json.getJsonObject("fields").forEach(entry -> {
        if (entry.getValue() instanceof JsonObject)
          map.put(entry.getKey(), new fr.myprysm.vertx.elasticsearch.action.support.DocumentField((JsonObject)entry.getValue()));
      });
      obj.setFields(map);
    }
    if (json.getValue("id") instanceof String) {
      obj.setId((String)json.getValue("id"));
    }
    if (json.getValue("index") instanceof String) {
      obj.setIndex((String)json.getValue("index"));
    }
    if (json.getValue("innerHits") instanceof JsonObject) {
      java.util.Map<String, fr.myprysm.vertx.elasticsearch.action.search.SearchHits> map = new java.util.LinkedHashMap<>();
      json.getJsonObject("innerHits").forEach(entry -> {
        if (entry.getValue() instanceof JsonObject)
          map.put(entry.getKey(), new fr.myprysm.vertx.elasticsearch.action.search.SearchHits((JsonObject)entry.getValue()));
      });
      obj.setInnerHits(map);
    }
    if (json.getValue("matchedQueries") instanceof JsonArray) {
      java.util.ArrayList<java.lang.String> list = new java.util.ArrayList<>();
      json.getJsonArray("matchedQueries").forEach( item -> {
        if (item instanceof String)
          list.add((String)item);
      });
      obj.setMatchedQueries(list);
    }
    if (json.getValue("nestedIdentity") instanceof JsonObject) {
      obj.setNestedIdentity(new fr.myprysm.vertx.elasticsearch.action.search.NestedIdentity((JsonObject)json.getValue("nestedIdentity")));
    }
    if (json.getValue("score") instanceof Number) {
      obj.setScore(((Number)json.getValue("score")).floatValue());
    }
    if (json.getValue("sortValues") instanceof JsonArray) {
      obj.setSortValues(((JsonArray)json.getValue("sortValues")).copy());
    }
    if (json.getValue("source") instanceof JsonObject) {
      obj.setSource(((JsonObject)json.getValue("source")).copy());
    }
    if (json.getValue("type") instanceof String) {
      obj.setType((String)json.getValue("type"));
    }
    if (json.getValue("version") instanceof Number) {
      obj.setVersion(((Number)json.getValue("version")).longValue());
    }
  }

  public static void toJson(SearchHit obj, JsonObject json) {
    if (obj.getClusterAlias() != null) {
      json.put("clusterAlias", obj.getClusterAlias());
    }
    if (obj.getExplanation() != null) {
      json.put("explanation", obj.getExplanation().toJson());
    }
    if (obj.getFields() != null) {
      JsonObject map = new JsonObject();
      obj.getFields().forEach((key,value) -> map.put(key, value.toJson()));
      json.put("fields", map);
    }
    if (obj.getId() != null) {
      json.put("id", obj.getId());
    }
    if (obj.getIndex() != null) {
      json.put("index", obj.getIndex());
    }
    if (obj.getInnerHits() != null) {
      JsonObject map = new JsonObject();
      obj.getInnerHits().forEach((key,value) -> map.put(key, value.toJson()));
      json.put("innerHits", map);
    }
    if (obj.getMatchedQueries() != null) {
      JsonArray array = new JsonArray();
      obj.getMatchedQueries().forEach(item -> array.add(item));
      json.put("matchedQueries", array);
    }
    if (obj.getNestedIdentity() != null) {
      json.put("nestedIdentity", obj.getNestedIdentity().toJson());
    }
    json.put("score", obj.getScore());
    if (obj.getSortValues() != null) {
      json.put("sortValues", obj.getSortValues());
    }
    if (obj.getSource() != null) {
      json.put("source", obj.getSource());
    }
    if (obj.getType() != null) {
      json.put("type", obj.getType());
    }
    json.put("version", obj.getVersion());
  }
}