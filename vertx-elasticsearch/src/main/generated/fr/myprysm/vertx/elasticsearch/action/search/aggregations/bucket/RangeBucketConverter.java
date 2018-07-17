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

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.RangeBucket}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.RangeBucket} original class using Vert.x codegen.
 */
public class RangeBucketConverter {

  public static void fromJson(JsonObject json, RangeBucket obj) {
    if (json.getValue("fromAsString") instanceof String) {
      obj.setFromAsString((String)json.getValue("fromAsString"));
    }
    if (json.getValue("toAsString") instanceof String) {
      obj.setToAsString((String)json.getValue("toAsString"));
    }
  }

  public static void toJson(RangeBucket obj, JsonObject json) {
    if (obj.getFromAsString() != null) {
      json.put("fromAsString", obj.getFromAsString());
    }
    if (obj.getToAsString() != null) {
      json.put("toAsString", obj.getToAsString());
    }
  }
}