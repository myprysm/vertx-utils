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

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.junit5.VertxExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Json/Jackson test")
@ExtendWith(VertxExtension.class)
class JsonTest {

    @Test
    @DisplayName("It should convert json object to map")
    void itShouldConvertJsonObjectToMap() {
        JsonObject json = new JsonObject().put("foo", "bar").put("hello", 123);
        assertThat(json.getMap()).isEqualTo(Json.convert(json));
    }

    @Test
    @DisplayName("It should should not convert null values")
    void itShouldNotConvertNullValues() {
        assertThat(Json.convert(new JsonObject().put("nullEntry", (String) null))).isEmpty();
    }

    @Test
    @DisplayName("It should should convert null values")
    void itShouldConvertNullValues() {
        assertThat(Json.convertWithNullValues(new JsonObject().put("nullEntry", (String) null))).containsEntry("nullEntry", null);
    }

    @Test
    @DisplayName("It should convert json object to map")
    void itShouldDeserializeJsonObject() {

        try {
            JsonObject json = Json.mapper.readValue("{\"foo\": \"bar\"}", JsonObject.class);
            assertThat(json).isEqualTo(new JsonObject().put("foo", "bar"));
        } catch (IOException e) {
            //
        }

    }

    @Test
    @DisplayName("It should convert json object to map")
    void itShouldConvertJsonArrayToList() {
        JsonArray json = new JsonArray().add("one").add("two").add("three");
        assertThat(json.getList()).isEqualTo(Json.convert(json));
    }

    @Test
    @DisplayName("It should convert json object to map")
    void itShouldDeserializeJsonArray() {

        try {
            JsonArray json = Json.mapper.readValue("[\"one\",\"two\",\"three\"]", JsonArray.class);
            assertThat(json).isEqualTo(new JsonArray().add("one").add("two").add("three"));
        } catch (IOException e) {
            //
        }

    }

}