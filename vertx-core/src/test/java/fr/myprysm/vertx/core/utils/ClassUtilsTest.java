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

package fr.myprysm.vertx.core.utils;

import fr.myprysm.vertx.test.VertxTest;
import fr.myprysm.vertx.validation.ValidationResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static fr.myprysm.vertx.json.JsonHelpers.obj;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Class Utils tests")
class ClassUtilsTest implements VertxTest {


    @Test
    @DisplayName("ClassUtils should load classes")
    void itShouldLoadClasses() {
        assertThat(ClassUtils.getClass("java.lang.String")).isNotNull();
        assertThat(ClassUtils.getClass("some class", true)).isNull();
        assertThat(ClassUtils.getClass("some class")).isNull();
        assertThrows(IllegalArgumentException.class, () -> ClassUtils.getClass("some class", false));
    }

    @Test
    @DisplayName("ClassUtils should validate that a class has a static method with provided argument classes")
    void itShouldValidateThatAClassHasAStaticMethodWithProvidedArgumentClasses() {

        ValidationResult validation = ClassUtils.hasStaticMethod(String.class, "valueOf", int.class).apply(obj());
        assertThat(validation.isValid()).isTrue();

        validation = ClassUtils.hasStaticMethod(String.class, "valueOf", int.class, double.class).apply(obj());
        assertThat(validation.isValid()).isFalse();
    }
}