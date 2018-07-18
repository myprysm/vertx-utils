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

package fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket;

import fr.myprysm.vertx.elasticsearch.action.search.aggregations.Aggregation;
import fr.myprysm.vertx.elasticsearch.action.search.aggregations.AggregationConverters;
import io.vertx.core.json.JsonObject;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Converter for {@link Bucket}.
 * <p>
 * NOTE: This class has been automatically generated from the {@link Bucket} original class using Vert.x codegen.
 */
public class BucketConverter {

    @SuppressWarnings("Duplicates")
    public static void fromJson(JsonObject json, Bucket obj) {
        if (json.getValue("aggregations") instanceof JsonObject) {
            Map<String, Aggregation> map = new LinkedHashMap<>();
            json.getJsonObject("aggregations").forEach(entry -> {
                if (entry.getValue() instanceof JsonObject)
                    map.put(entry.getKey(), AggregationConverters.fromJsonObject((JsonObject) entry.getValue()));
            });
            obj.setAggregations(map);
        }
        if (json.getValue("docCount") instanceof Number) {
            obj.setDocCount(((Number) json.getValue("docCount")).longValue());
        }
        if (json.getValue("key") instanceof String) {
            obj.setKey((String) json.getValue("key"));
        }
    }

    public static void toJson(Bucket obj, JsonObject json) {
        if (obj.getAggregations() != null) {
            JsonObject map = new JsonObject();
            obj.getAggregations().forEach((key, value) -> map.put(key, value.toJson()));
            json.put("aggregations", map);
        }
        json.put("docCount", obj.getDocCount());
        if (obj.getKey() != null) {
            json.put("key", obj.getKey());
        }
    }
}