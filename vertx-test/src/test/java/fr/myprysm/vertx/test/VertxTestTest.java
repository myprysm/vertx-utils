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

package fr.myprysm.vertx.test;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("VertxTest tests")
class VertxTestTest implements VertxTest {

    @Test
    @DisplayName("It should load a string resource from a file")
    void itShouldLoadStringFromFile() {
        assertThat(stringFromFile("string.txt")).isEqualTo("Hello, World!\n");
    }

    @Test
    @DisplayName("It should return an empty string with unexisting resource")
    void itShouldReturnEmptyStringWithUnexistingResource() {
        assertThat(stringFromFile("void")).isEqualTo("");
    }


    @Test
    @DisplayName("It should load a json object from a file")
    void itShouldLoadObjectFromFile() {
        assertThat(objectFromFile("object.json")).isEqualTo(new JsonObject().put("foo", "bar"));
    }

    @Test
    @DisplayName("It should return an empty object with wrong resource")
    void itShouldReturnEmptyObjectWithWrongResource() {
        assertThat(objectFromFile("array.json")).isEqualTo(new JsonObject());
    }

    @Test
    @DisplayName("It should return an empty object with unexisting resource")
    void itShouldReturnEmptyObjectWithUnexistingResource() {
        assertThat(objectFromFile("void")).isEqualTo(new JsonObject());
    }

    @Test
    @DisplayName("It should load a json array from a file")
    void itShouldLoadArrayFromFile() {
        assertThat(arrayFromFile("array.json")).isEqualTo(new JsonArray()
                .add(new JsonObject().put("foo", "bar"))
                .add(new JsonObject().put("hello", "world"))
        );
    }

    @Test
    @DisplayName("It should return an empty array with wrong resource")
    void itShouldReturnEmptyArrayWithWrongResource() {
        assertThat(arrayFromFile("object.json")).isEqualTo(new JsonArray());
    }

    @Test
    @DisplayName("It should return an empty array with unexisting resource")
    void itShouldReturnEmptyArrayWithUnexistingResource() {
        assertThat(arrayFromFile("void")).isEqualTo(new JsonArray());
    }
}