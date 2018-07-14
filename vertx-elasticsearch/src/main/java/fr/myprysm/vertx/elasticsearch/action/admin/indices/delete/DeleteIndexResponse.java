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

package fr.myprysm.vertx.elasticsearch.action.admin.indices.delete;

import fr.myprysm.vertx.elasticsearch.action.support.AcknowledgedResponse;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.NoArgsConstructor;

/**
 * DeleteIndexResponse.
 */
@NoArgsConstructor
@DataObject
public class DeleteIndexResponse extends AcknowledgedResponse {

    /**
     * Build a new response with the status.
     *
     * @param acknowledged the status
     */
    public DeleteIndexResponse(boolean acknowledged) {
        super(acknowledged);
    }

    /**
     * Build a new response from another.
     *
     * @param other the other
     */
    public DeleteIndexResponse(DeleteIndexResponse other) {
        super(other);
    }

    /**
     * Build a new <code>DeleteIndexResponse</code> from a <code>JsonObject</code>,
     * calling parent constructor.
     *
     * @param json the <code>JsonObject</code>
     */
    public DeleteIndexResponse(JsonObject json) {
        super(json);
    }
}
