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

package fr.myprysm.vertx.elasticsearch.action.admin.indices.get;

import fr.myprysm.vertx.elasticsearch.action.BaseRequest;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest.Feature;
import org.elasticsearch.action.support.IndicesOptions;

import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.action.support.master.MasterNodeRequest.DEFAULT_MASTER_NODE_TIMEOUT;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@DataObject(generateConverter = true)
public class GetIndexRequest extends BaseRequest {

    private List<String> indices = new ArrayList<>();
    private List<String> types = new ArrayList<>();
    private List<Feature> features = new ArrayList<>();
    private boolean includeDefaults;
    private IndicesOptions indicesOptions;
    private boolean local;
    private long masterNodeTimeout = DEFAULT_MASTER_NODE_TIMEOUT.millis();

    public GetIndexRequest(GetIndexRequest other) {
        indices = other.indices;
        types = other.types;
        features = other.features;
        includeDefaults = other.includeDefaults;
        indicesOptions = other.indicesOptions;
        local = other.local;
        masterNodeTimeout = other.masterNodeTimeout;
    }

    public GetIndexRequest(JsonObject json) {
        GetIndexRequestConverter.fromJson(json, this);
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        GetIndexRequestConverter.toJson(this, json);
        return json;
    }

    @Override
    public GetIndexRequest addHeader(String headerKey, String headerValue) {
        return (GetIndexRequest) super.addHeader(headerKey, headerValue);
    }

}
