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

package fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket;

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

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@DataObject
public class SingleBucketAggregation extends Aggregation {

    private long docCount;
    private Map<String, Aggregation> aggregations;

    public SingleBucketAggregation(SingleBucketAggregation other) {
        super(other);
        docCount = other.docCount;
        aggregations = other.aggregations;
    }

    @SuppressWarnings("unchecked")
    public <T extends Aggregation> T getAggregationByName(String name) {
        if (aggregations == null) {
            return null;
        }

        return (T) aggregations.get(name);

    }

    /**
     * Build a new <code>SingleBucketAggregation</code> from a <code>JsonObject</code>,
     * calling parent constructor.
     *
     * @param json the <code>JsonObject</code>
     */
    public SingleBucketAggregation(JsonObject json) {
        super(json);
        SingleBucketAggregationConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>SingleBucketAggregation</code> into a <code>JsonObject</code>,
     * calling parent <code>toJson</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        SingleBucketAggregationConverter.toJson(this, json);
        return json;
    }
}
