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

import fr.myprysm.vertx.elasticsearch.action.search.aggregations.Aggregation;
import fr.myprysm.vertx.elasticsearch.action.search.aggregations.AggregationConverters;
import fr.myprysm.vertx.elasticsearch.action.search.suggest.SuggestConverters;
import fr.myprysm.vertx.elasticsearch.action.search.suggest.Suggestion;
import fr.myprysm.vertx.elasticsearch.action.support.ShardFailure;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Converter for {@link SearchResponse}.
 * <p>
 * NOTE: This class has been automatically generated from the {@link SearchResponse} original class using Vert.x codegen.
 */
public class SearchResponseConverter {

    public static void fromJson(JsonObject json, SearchResponse obj) {
        if (json.getValue("aggregations") instanceof JsonObject) {
            Map<String, Aggregation> map = new LinkedHashMap<>();
            json.getJsonObject("aggregations").forEach(entry -> {
                if (entry.getValue() instanceof JsonObject) {
                    map.put(entry.getKey(), AggregationConverters.fromJsonObject((JsonObject) entry.getValue()));
                }
            });
            obj.setAggregations(map);
        }
        if (json.getValue("clusters") instanceof JsonObject) {
            obj.setClusters(new Clusters((JsonObject) json.getValue("clusters")));
        }
        if (json.getValue("hits") instanceof JsonObject) {
            obj.setHits(new SearchHits((JsonObject) json.getValue("hits")));
        }
        if (json.getValue("numReducePhases") instanceof Number) {
            obj.setNumReducePhases(((Number) json.getValue("numReducePhases")).intValue());
        }
        if (json.getValue("profileResults") instanceof JsonObject) {
            Map<String, JsonObject> map = new LinkedHashMap<>();
            json.getJsonObject("profileResults").forEach(entry -> {
                if (entry.getValue() instanceof JsonObject)
                    map.put(entry.getKey(), ((JsonObject) entry.getValue()).copy());
            });
            obj.setProfileResults(map);
        }
        if (json.getValue("scrollId") instanceof String) {
            obj.setScrollId((String) json.getValue("scrollId"));
        }
        if (json.getValue("shardFailures") instanceof JsonArray) {
            ArrayList<ShardFailure> list = new ArrayList<>();
            json.getJsonArray("shardFailures").forEach(item -> {
                if (item instanceof JsonObject)
                    list.add(new ShardFailure((JsonObject) item));
            });
            obj.setShardFailures(list);
        }
        if (json.getValue("skippedShards") instanceof Number) {
            obj.setSkippedShards(((Number) json.getValue("skippedShards")).intValue());
        }
        if (json.getValue("successfulShards") instanceof Number) {
            obj.setSuccessfulShards(((Number) json.getValue("successfulShards")).intValue());
        }
        if (json.getValue("suggest") instanceof JsonObject) {
            Map<String, Suggestion> map = new LinkedHashMap<>();
            json.getJsonObject("suggest").forEach(entry -> {
                if (entry.getValue() instanceof JsonObject) {
                    map.put(entry.getKey(), SuggestConverters.fromJsonObject((JsonObject) entry.getValue()));
                }
            });
            obj.setSuggest(map);
        }
        if (json.getValue("terminatedEarly") instanceof Boolean) {
            obj.setTerminatedEarly((Boolean) json.getValue("terminatedEarly"));
        }
        if (json.getValue("timedOut") instanceof Boolean) {
            obj.setTimedOut((Boolean) json.getValue("timedOut"));
        }
        if (json.getValue("tookInMillis") instanceof Number) {
            obj.setTookInMillis(((Number) json.getValue("tookInMillis")).longValue());
        }
        if (json.getValue("totalShards") instanceof Number) {
            obj.setTotalShards(((Number) json.getValue("totalShards")).intValue());
        }
    }

    public static void toJson(SearchResponse obj, JsonObject json) {
        if (obj.getAggregations() != null) {
            JsonObject map = new JsonObject();
            obj.getAggregations().forEach((key, value) -> map.put(key, value.toJson()));
            json.put("aggregations", map);
        }
        if (obj.getClusters() != null) {
            json.put("clusters", obj.getClusters().toJson());
        }
        if (obj.getHits() != null) {
            json.put("hits", obj.getHits().toJson());
        }
        json.put("numReducePhases", obj.getNumReducePhases());
        if (obj.getProfileResults() != null) {
            JsonObject map = new JsonObject();
            obj.getProfileResults().forEach(map::put);
            json.put("profileResults", map);
        }
        if (obj.getScrollId() != null) {
            json.put("scrollId", obj.getScrollId());
        }
        if (obj.getShardFailures() != null) {
            JsonArray array = new JsonArray();
            obj.getShardFailures().forEach(item -> array.add(item.toJson()));
            json.put("shardFailures", array);
        }
        json.put("skippedShards", obj.getSkippedShards());
        json.put("successfulShards", obj.getSuccessfulShards());
        if (obj.getSuggest() != null) {
            JsonObject map = new JsonObject();
            obj.getSuggest().forEach((key, value) -> map.put(key, value.toJson()));
            json.put("suggest", map);
        }
        if (obj.getTerminatedEarly() != null) {
            json.put("terminatedEarly", obj.getTerminatedEarly());
        }
        json.put("timedOut", obj.isTimedOut());
        json.put("tookInMillis", obj.getTookInMillis());
        json.put("totalShards", obj.getTotalShards());
    }
}