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

import fr.myprysm.vertx.elasticsearch.action.support.AcknowledgedResponse;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@DataObject(generateConverter = true)
public class CreateIndexResponse extends AcknowledgedResponse {

    private boolean shardsAcknowledged;
    private String index;

    public CreateIndexResponse(boolean acknowledged, boolean shardsAcknowledged, String index) {
        super(acknowledged);
        this.shardsAcknowledged = shardsAcknowledged;
        this.index = index;
    }

    public CreateIndexResponse(CreateIndexResponse other) {
        super(other);
        shardsAcknowledged = other.shardsAcknowledged;
        index = other.index;
    }

    /**
     * Build a new <code>CreateIndexResponse</code> from a <code>JsonObject</code>,
     * calling parent constructor.
     *
     * @param json the <code>JsonObject</code>
     */
    public CreateIndexResponse(JsonObject json) {
        super(json);
        CreateIndexResponseConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>CreateIndexResponse</code> into a <code>JsonObject</code>,
     * calling parent <code>toJson</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        CreateIndexResponseConverter.toJson(this, json);
        return json;
    }
}
