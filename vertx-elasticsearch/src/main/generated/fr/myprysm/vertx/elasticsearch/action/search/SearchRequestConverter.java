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
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.search.SearchRequest}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.search.SearchRequest} original class using Vert.x codegen.
 */
public class SearchRequestConverter {

  public static void fromJson(JsonObject json, SearchRequest obj) {
    if (json.getValue("allowPartialSearchResults") instanceof Boolean) {
      obj.setAllowPartialSearchResults((Boolean)json.getValue("allowPartialSearchResults"));
    }
    if (json.getValue("batchedReduceSize") instanceof Number) {
      obj.setBatchedReduceSize(((Number)json.getValue("batchedReduceSize")).intValue());
    }
    if (json.getValue("indices") instanceof JsonArray) {
      java.util.ArrayList<java.lang.String> list = new java.util.ArrayList<>();
      json.getJsonArray("indices").forEach( item -> {
        if (item instanceof String)
          list.add((String)item);
      });
      obj.setIndices(list);
    }
    if (json.getValue("keepAlive") instanceof Number) {
      obj.setKeepAlive(((Number)json.getValue("keepAlive")).longValue());
    }
    if (json.getValue("maxConcurrentShardRequests") instanceof Number) {
      obj.setMaxConcurrentShardRequests(((Number)json.getValue("maxConcurrentShardRequests")).intValue());
    }
    if (json.getValue("preFilterShardSize") instanceof Number) {
      obj.setPreFilterShardSize(((Number)json.getValue("preFilterShardSize")).intValue());
    }
    if (json.getValue("preference") instanceof String) {
      obj.setPreference((String)json.getValue("preference"));
    }
    if (json.getValue("requestCache") instanceof Boolean) {
      obj.setRequestCache((Boolean)json.getValue("requestCache"));
    }
    if (json.getValue("routing") instanceof String) {
      obj.setRouting((String)json.getValue("routing"));
    }
    if (json.getValue("searchType") instanceof String) {
      obj.setSearchType(org.elasticsearch.action.search.SearchType.valueOf((String)json.getValue("searchType")));
    }
    if (json.getValue("source") instanceof JsonObject) {
      obj.setSource(((JsonObject)json.getValue("source")).copy());
    }
    if (json.getValue("types") instanceof JsonArray) {
      java.util.ArrayList<java.lang.String> list = new java.util.ArrayList<>();
      json.getJsonArray("types").forEach( item -> {
        if (item instanceof String)
          list.add((String)item);
      });
      obj.setTypes(list);
    }
  }

  public static void toJson(SearchRequest obj, JsonObject json) {
    if (obj.getAllowPartialSearchResults() != null) {
      json.put("allowPartialSearchResults", obj.getAllowPartialSearchResults());
    }
    json.put("batchedReduceSize", obj.getBatchedReduceSize());
    if (obj.getIndices() != null) {
      JsonArray array = new JsonArray();
      obj.getIndices().forEach(item -> array.add(item));
      json.put("indices", array);
    }
    if (obj.getKeepAlive() != null) {
      json.put("keepAlive", obj.getKeepAlive());
    }
    json.put("maxConcurrentShardRequests", obj.getMaxConcurrentShardRequests());
    json.put("preFilterShardSize", obj.getPreFilterShardSize());
    if (obj.getPreference() != null) {
      json.put("preference", obj.getPreference());
    }
    if (obj.getRequestCache() != null) {
      json.put("requestCache", obj.getRequestCache());
    }
    if (obj.getRouting() != null) {
      json.put("routing", obj.getRouting());
    }
    if (obj.getSearchType() != null) {
      json.put("searchType", obj.getSearchType().name());
    }
    if (obj.getSource() != null) {
      json.put("source", obj.getSource());
    }
    if (obj.getTypes() != null) {
      JsonArray array = new JsonArray();
      obj.getTypes().forEach(item -> array.add(item));
      json.put("types", array);
    }
  }
}