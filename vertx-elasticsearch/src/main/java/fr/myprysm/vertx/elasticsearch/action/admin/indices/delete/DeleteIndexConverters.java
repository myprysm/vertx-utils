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

/**
 * DeleteIndex request converters.
 */
public interface DeleteIndexConverters {

    /**
     * Transforms a DataObject request into an Elasticsearch request.
     *
     * @param request the request
     * @return the Elasticsearch request
     */
    static org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest requestToES(DeleteIndexRequest request) {
        org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest esRequest =
                new org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest();

        esRequest.indices(request.getIndices().toArray(new String[]{}));
        return esRequest;
    }

    /**
     * Transforms an Elasticsearch response into a DataObject response.
     *
     * @param esResponse the Elasticsearch response
     * @return the response
     */
    static DeleteIndexResponse responseToDataObject(org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse esResponse) {
        return new DeleteIndexResponse(esResponse.isAcknowledged());
    }
}
