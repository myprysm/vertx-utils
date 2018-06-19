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

package fr.myprysm.vertx.core.test;

import fr.myprysm.vertx.test.VertxTest;
import io.vertx.core.Vertx;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Config Test Helpers test")
class ConfigTestHelperTest implements VertxTest {

    @Test
    @DisplayName("ConfigTestHelper should load a valid file")
    void itShouldLoadAValidFile(Vertx vertx, VertxTestContext ctx) {
        ConfigTestHelper.load(vertx, ctx, (service, consumer) -> {
            service.getObject("env.types", ctx.succeeding(obj -> ctx.completeNow()));
        });
    }
}