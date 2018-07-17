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
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@DataObject(generateConverter = true)
public class Bucket {

    private String key;
    private long docCount;
    private Map<String, Aggregation> aggregations;

    public Bucket(Bucket other) {
        key = other.key;
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
     * Build a new <code>Bucket</code> from a <code>JsonObject</code>.
     *
     * @param json the <code>JsonObject</code>
     */
    public Bucket(JsonObject json) {
        BucketConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>Bucket</code> into a <code>JsonObject</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        BucketConverter.toJson(this, json);
        return json;
    }
}
