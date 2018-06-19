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

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.junit5.Checkpoint;
import io.vertx.junit5.VertxTestContext;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("QuickAssert tests")
class QuickAssertTest implements VertxTest, QuickAssert {


    @Test
    @DisplayName("It should equality on objects")
    void itShouldAssertEquality(VertxTestContext ctx) {
        Checkpoint cp = ctx.checkpoint();
        Future<JsonObject> future = Future.succeededFuture(new JsonObject().put("foo", "bar"));


        future.setHandler(assertEquals(new JsonObject().put("foo", "bar"), cp, ctx));
    }

    @Test
    @DisplayName("It should assert list size")
    void itShouldAssertListSize(VertxTestContext ctx) {
        Checkpoint cp = ctx.checkpoint();
        Future<List<Integer>> future = Future.succeededFuture(Lists.newArrayList(1));


        future.setHandler(assertSize(1, cp, ctx));
    }

    @Test
    @DisplayName("It should assert exception class")
    void itShouldAssertExceptionClass(VertxTestContext ctx) {
        Checkpoint cp = ctx.checkpoint(2);
        Future<Object> future = Future.failedFuture(new NullPointerException("some message"));

        future.setHandler(assertThrows(NullPointerException.class, cp, ctx));
        future.setHandler(assertThrows(NullPointerException.class, "some message", cp, ctx));
    }

    @Test
    @DisplayName("It should assert success")
    void itShouldAssertSuccess(VertxTestContext ctx) {
        Checkpoint cp = ctx.checkpoint(2);
        Future<Object> future = Future.succeededFuture();

        future.setHandler(assertSuccess(cp, ctx));
        future.setHandler(assertSuccess(zoid -> {
            // do nothing for the test...
        }, cp, ctx));
    }

    @Test
    @DisplayName("It should assert failure")
    void itShouldAssertFailure(VertxTestContext ctx) {
        Checkpoint cp = ctx.checkpoint(2);
        Future<Object> future = Future.failedFuture(new NullPointerException("some message"));

        future.setHandler(assertFail(cp, ctx));
        future.setHandler(assertFail(err -> {
            // do nothing for the test...
            assertThat(err).isInstanceOf(NullPointerException.class);
            assertThat(err.getMessage()).isEqualTo("some message");
        }, cp, ctx));
    }
}