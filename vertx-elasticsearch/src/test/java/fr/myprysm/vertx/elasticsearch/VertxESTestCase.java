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

package fr.myprysm.vertx.elasticsearch;

import fr.myprysm.vertx.test.VertxTest;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import io.vertx.core.Vertx;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class VertxESTestCase implements VertxTest {

    protected static Vertx vertx;

    @BeforeAll
    public static void setUpVertx() {
        vertx = Vertx.vertx();
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxJavaPlugins.setComputationSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxJavaPlugins.setNewThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
    }

    /**
     * Shorthand to assert that a single finishes without error and with a result.
     * <p>
     * The consumer allows to continue assertions on the result.
     *
     * @param single   the single
     * @param consumer the consumer to assert the result
     * @param <V>      the type of the result
     * @throws InterruptedException with {@link TestObserver#await()}
     */
    protected <V> void assertSuccessSingle(Single<V> single, Consumer<V> consumer) throws InterruptedException {
        single.test().await().assertNoErrors().assertValue(v -> {
            consumer.accept(v);
            return true;
        });
    }

    /**
     * Shorthand to assert that a test observer finishes with an error of given class (or sub-class) and
     * <p>
     * The consumer allows to continues assertions on the failure.
     *
     * @param observer       the test observer
     * @param exceptionClass the exception class (assertion and cast)
     * @param consumer       the consumer to assert the failure
     * @param <V>            the value type
     * @param <T>            the error type
     * @throws InterruptedException with {@link TestObserver#await()}
     */
    protected <V, T extends Throwable> void assertFailure(TestObserver<V> observer, Class<T> exceptionClass, Consumer<T> consumer) throws InterruptedException {
        observer.await().assertError(err -> {
            assertThat(err).isInstanceOf(exceptionClass);
            consumer.accept(exceptionClass.cast(err));
            return true;
        });
    }

    @AfterAll
    public static void tearDownVertx() {
        vertx.close();
        RxJavaPlugins.reset();
    }
}
