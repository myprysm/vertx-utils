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

import fr.myprysm.vertx.elasticsearch.converter.ConverterUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.elasticsearch.common.unit.TimeValue;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

public interface CreateIndexConverters {

    static org.elasticsearch.action.admin.indices.create.CreateIndexRequest requestToES(CreateIndexRequest request) {
        org.elasticsearch.action.admin.indices.create.CreateIndexRequest esRequest =
                new org.elasticsearch.action.admin.indices.create.CreateIndexRequest();

        if (request.getSettings() != null) {
            esRequest.settings(ConverterUtils.convert(request.getSettings()));
        }

        if (request.getAliases() != null && !request.getAliases().isEmpty()) {
            for (Alias alias : request.getAliases()) {
                esRequest.alias(aliasToRequest(alias));
            }
        }

        if (request.getMappings() != null && !request.getMappings().isEmpty()) {
            Map<String, String> mappings = request.getMappings().entrySet()
                    .stream()
                    .map(entry -> Pair.of(entry.getKey(), entry.getValue().toString()))
                    .collect(toMap(Pair::getKey, Pair::getValue));
            esRequest.mappings().putAll(mappings);
        }

        if (request.getWaitForActiveShards() != null) {
            esRequest.waitForActiveShards(request.getWaitForActiveShards());
        }
        return esRequest
                .cause(request.getCause())
                .index(request.getIndex())
                .timeout(TimeValue.timeValueMillis(request.getTimeout()));
    }

    static org.elasticsearch.action.admin.indices.alias.Alias aliasToRequest(Alias alias) {
        return new org.elasticsearch.action.admin.indices.alias.Alias(alias.getName())
                .filter(alias.getFilter())
                .searchRouting(alias.getSearchRouting())
                .indexRouting(alias.getIndexRouting());
    }

    static CreateIndexResponse responseToDataObject(org.elasticsearch.action.admin.indices.create.CreateIndexResponse esResponse) {
        return new CreateIndexResponse(
                esResponse.isAcknowledged(),
                esResponse.isShardsAcknowledged(),
                esResponse.index()
        );
    }
}
