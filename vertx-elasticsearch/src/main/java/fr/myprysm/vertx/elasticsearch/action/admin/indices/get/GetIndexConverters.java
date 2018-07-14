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

import org.elasticsearch.action.admin.indices.get.GetIndexRequest.Feature;
import org.elasticsearch.common.unit.TimeValue;

/**
 * GetIndex request converters.
 */
public interface GetIndexConverters {

    /**
     * Transforms a DataObject request into an Elasticsearch request.
     *
     * @param request the request
     * @return the Elasticsearch request
     */
    static org.elasticsearch.action.admin.indices.get.GetIndexRequest requestToES(GetIndexRequest request) {
        org.elasticsearch.action.admin.indices.get.GetIndexRequest esRequest = new org.elasticsearch.action.admin.indices.get.GetIndexRequest();

        if (request.getIndices() != null) {
            esRequest.indices(request.getIndices().toArray(new String[]{}));
        }

        if (request.getTypes() != null) {
            esRequest.types(request.getTypes().toArray(new String[]{}));
        }

        if (request.getFeatures() != null) {
            esRequest.features(request.getFeatures().toArray(new Feature[]{}));
        }

        if (request.getIndicesOptions() != null) {
            esRequest.indicesOptions(request.getIndicesOptions());
        }
        return esRequest
                .includeDefaults(request.isIncludeDefaults())
                .masterNodeTimeout(TimeValue.timeValueMillis(request.getMasterNodeTimeout()))
                .local(request.isLocal());
    }
}
