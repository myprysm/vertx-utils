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

package fr.myprysm.vertx.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Json {

    public static final ObjectMapper mapper;
    public static final ObjectMapper prettyMapper;

    /*
     * Binds deserializers for JsonArray and JsonObject as this is not already done by Vert.x
     */
    static {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(JsonObject.class, new JsonObjectDeserializer());
        module.addDeserializer(JsonArray.class, new JsonArrayDeserializer());

        mapper = io.vertx.core.json.Json.mapper;
        prettyMapper = io.vertx.core.json.Json.prettyMapper;

        mapper.registerModule(module);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        prettyMapper.registerModule(module);
        prettyMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    }

    private Json() {

    }

    /**
     * Converts a {@link JsonObject} to a {@link Map}
     *
     * @param json the object to convert
     * @return the map
     */
    public static Map<String, Object> convert(JsonObject json) {
        return mapper.convertValue(json, new TypeReference<Map<String, Object>>() {
        });
    }

    /**
     * Converts a {@link JsonArray} to a {@link List} of the specified type.
     *
     * @param jsonArray the array to convert
     * @return the list
     */
    public static <T> List<T> convert(JsonArray jsonArray) {
        return mapper.convertValue(jsonArray, new TypeReference<List<T>>() {
        });
    }

    private static class JsonObjectDeserializer extends JsonDeserializer<JsonObject> {

        @Override
        @SuppressWarnings("unchecked")
        public JsonObject deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            return new JsonObject(p.readValueAs(Map.class));
        }
    }

    private static class JsonArrayDeserializer extends JsonDeserializer<JsonArray> {
        @Override
        public JsonArray deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            return new JsonArray(p.readValueAs(List.class));
        }
    }
}
