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

import fr.myprysm.vertx.elasticsearch.action.BaseRequest;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;

/**
 * DeleteIndexRequest.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@DataObject(generateConverter = true)
public class DeleteIndexRequest extends BaseRequest {

    /**
     * Indices.
     */
    private List<String> indices;

    /**
     * Build a new DeleteIndexRequest from another.
     *
     * @param other the other
     */
    public DeleteIndexRequest(DeleteIndexRequest other) {
        super(other);
        indices = other.indices;
    }

    /**
     * Build a new DeleteIndexRequest with the provided indice.
     *
     * @param indice the indice
     */
    public DeleteIndexRequest(String indice) {
        this.indices = Arrays.asList(indice);
    }

    /**
     * Build a new <code>DeleteIndexRequest</code> from a <code>JsonObject</code>,
     * calling parent constructor.
     *
     * @param json the <code>JsonObject</code>
     */
    public DeleteIndexRequest(JsonObject json) {
        super(json);
        DeleteIndexRequestConverter.fromJson(json, this);
    }


    @Override
    public DeleteIndexRequest addHeader(String headerKey, String headerValue) {
        return (DeleteIndexRequest) super.addHeader(headerKey, headerValue);
    }


    /**
     * Transforms the <code>DeleteIndexRequest</code> into a <code>JsonObject</code>,
     * calling parent <code>toJson</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        DeleteIndexRequestConverter.toJson(this, json);
        return json;
    }
}
