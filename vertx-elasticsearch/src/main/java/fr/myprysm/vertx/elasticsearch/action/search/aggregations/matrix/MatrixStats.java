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

import fr.myprysm.vertx.elasticsearch.action.search.aggregations.Aggregation;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@DataObject(generateConverter = true)
public class MatrixStats extends Aggregation {

    private long docCount;
    private Map<String, MatrixStatsResult> results;

    public MatrixStats(MatrixStats other) {
        super(other);
        docCount = other.docCount;
        results = other.results;
    }

    /**
     * Build a new <code>MatrixStats</code> from a <code>JsonObject</code>,
     * calling parent constructor.
     *
     * @param json the <code>JsonObject</code>
     */
    public MatrixStats(JsonObject json) {
        super(json);
        MatrixStatsConverter.fromJson(json, this);
    }

    public long getFieldCount(String field) {
        return get(field).map(MatrixStatsResult::getCount).orElse(0L);
    }

    public Double getMean(String field) {
        return get(field).map(MatrixStatsResult::getMean).orElse(null);
    }

    public Double getVariance(String field) {
        return get(field).map(MatrixStatsResult::getVariance).orElse(null);
    }

    public Double getSkewness(String field) {
        return get(field).map(MatrixStatsResult::getSkewness).orElse(null);
    }

    public Double getKurtosis(String field) {
        return get(field).map(MatrixStatsResult::getKurtosis).orElse(null);
    }

    public Double getCovariance(String fieldX, String fieldY) {
        if (fieldX.equals(fieldY)) {
            return get(fieldX).map(MatrixStatsResult::getVariance).orElse(null);
        }

        return getValFromUpperTriangularMatrix(fieldX, fieldY, MatrixStatsResult::getCovariance);
    }

    public Double getCorrelation(String fieldX, String fieldY) {
        if (fieldX.equals(fieldY)) {
            return 1.0;
        }
        return getValFromUpperTriangularMatrix(fieldX, fieldY, MatrixStatsResult::getCorrelation);
    }


    private Optional<MatrixStatsResult> get(String field) {
        if (results == null || field == null || !results.containsKey(field)) {
            return Optional.empty();
        }

        return Optional.ofNullable(results.get(field));
    }

    /**
     * return the value for two fields in an upper triangular matrix, regardless of row col location.
     */
    private Double getValFromUpperTriangularMatrix(String fieldX, String fieldY, Function<MatrixStatsResult, Map<String, Double>> extractor) {
        // for the co-value to exist, one of the two (or both) fields has to be a row key
        if (!results.containsKey(fieldX) && !results.containsKey(fieldY)) {
            return null;
        } else if (results.containsKey(fieldX)) {
            // fieldX exists as a row key
            Map<String, Double> extracted = extractor.apply(results.get(fieldX));
            if (extracted != null && extracted.containsKey(fieldY)) {
                // fieldY exists as a col key to fieldX
                return extracted.get(fieldY);
            } else if ((extracted = extractor.apply(results.get(fieldY))) != null) {
                // otherwise fieldX is the col key to fieldY
                return extracted.get(fieldX);
            }
        } else if (results.containsKey(fieldY) && extractor.apply(results.get(fieldY)) != null) {
            // fieldX did not exist as a row key, it must be a col key
            return extractor.apply(results.get(fieldY)).get(fieldX);
        }
        return null;
    }

    /**
     * Transforms the <code>MatrixStats</code> into a <code>JsonObject</code>,
     * calling parent <code>toJson</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        MatrixStatsConverter.toJson(this, json);
        return json;
    }

}
