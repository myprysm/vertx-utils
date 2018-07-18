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

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@DataObject(generateConverter = true)
public class MatrixStatsResult {
    String name;
    Long count;
    Double mean;
    Double variance;
    Double skewness;
    Double kurtosis;
    Map<String, Double> covariance;
    Map<String, Double> correlation;

    public MatrixStatsResult(MatrixStatsResult other) {
        name = other.name;
        count = other.count;
        mean = other.mean;
        variance = other.variance;
        skewness = other.skewness;
        kurtosis = other.kurtosis;
        covariance = other.covariance;
        correlation = other.correlation;
    }

    /**
     * Build a new <code>MatrixStatsResult</code> from a <code>JsonObject</code>.
     *
     * @param json the <code>JsonObject</code>
     */
    public MatrixStatsResult(JsonObject json) {
        MatrixStatsResultConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>MatrixStatsResult</code> into a <code>JsonObject</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        MatrixStatsResultConverter.toJson(this, json);
        return json;
    }
}
