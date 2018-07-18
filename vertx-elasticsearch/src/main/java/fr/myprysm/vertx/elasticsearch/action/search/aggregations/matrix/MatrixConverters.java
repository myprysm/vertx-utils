/*
 * Copyright 2018 the original author or the original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fr.myprysm.vertx.elasticsearch.action.search.aggregations.matrix;

import fr.myprysm.vertx.elasticsearch.converter.CommonConverters;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

import static fr.myprysm.vertx.elasticsearch.action.search.aggregations.AggregationConverters.fillCommonAggregationDataObject;
import static org.elasticsearch.search.aggregations.Aggregation.TYPED_KEYS_DELIMITER;

public interface MatrixConverters {

    static MatrixStats matrixStatsToDataObject(org.elasticsearch.search.aggregations.matrix.stats.MatrixStats esStats) {
        MatrixStats stats = new MatrixStats();
        JsonArray fields = CommonConverters.fromXContent(esStats)
                .getJsonObject(esStats.getType() + TYPED_KEYS_DELIMITER + esStats.getName(), new JsonObject())
                .getJsonArray("fields");

        if (fields != null && !fields.isEmpty()) {
            Map<String, MatrixStatsResult> results = new HashMap<>(fields.size());
            fields.stream().map(JsonObject.class::cast).map(MatrixStatsResult::new).forEach(r -> results.put(r.getName(), r));
            stats.setResults(results);
        }
        return fillCommonAggregationDataObject(stats, esStats)
                .setDocCount(esStats.getDocCount());
    }
}
