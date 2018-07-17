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

package fr.myprysm.vertx.elasticsearch.action.admin.indices.create;

import fr.myprysm.vertx.elasticsearch.action.BaseRequest;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.elasticsearch.action.support.master.AcknowledgedRequest.DEFAULT_ACK_TIMEOUT;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@DataObject(generateConverter = true)
public class CreateIndexRequest extends BaseRequest {

    private String cause;
    private String index;
    private JsonObject settings;

    private Map<String, JsonObject> mappings;
    private Set<Alias> aliases;

    private Integer waitForActiveShards;
    private long timeout = DEFAULT_ACK_TIMEOUT.millis();

    public CreateIndexRequest(String index) {
        this.index = index;
    }

    private Map<String, JsonObject> safeMappings() {
        if (mappings == null) {
            mappings = new HashMap<>();
        }
        return mappings;
    }

    public CreateIndexRequest addMapping(String name, JsonObject mapping) {
        safeMappings().put(name, mapping);
        return this;
    }

    /**
     * Build a new <code>CreateIndexRequest</code> from a <code>JsonObject</code>,
     * calling parent constructor.
     *
     * @param json the <code>JsonObject</code>
     */
    public CreateIndexRequest(JsonObject json) {
        super(json);
        CreateIndexRequestConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>CreateIndexRequest</code> into a <code>JsonObject</code>,
     * calling parent <code>toJson</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        CreateIndexRequestConverter.toJson(this, json);
        return json;
    }
}
