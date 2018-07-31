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

package fr.myprysm.vertx.elasticsearch.impl;

import com.google.common.collect.ImmutableMap;
import fr.myprysm.vertx.elasticsearch.ElasticsearchClientOptions;
import fr.myprysm.vertx.elasticsearch.HttpHost;
import fr.myprysm.vertx.elasticsearch.reactivex.ElasticsearchClient;
import fr.myprysm.vertx.validation.ValidationException;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.shareddata.LocalMap;
import io.vertx.junit5.VertxTestContext;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.rest.RestStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;

import static fr.myprysm.vertx.elasticsearch.impl.BaseElasticsearchRestClient.DS_LOCAL_MAP_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

abstract class AbstractESClientTest extends VertxESUnitTestCase {

    private static final String MOCK = "mock";
    private static final String INDICES_MOCK = path(MOCK, "indices");
    private static final String CLUSTER_MOCK = path(MOCK, "cluster");
    private static final String CLIENT_NAME = "unit-test-client";

    private ElasticsearchClient rxClient;

    @BeforeEach
    void initClient() {
        rxClient = new ElasticsearchClient(getClient(CLIENT_NAME, getOptions()));
    }

    @AfterEach
    void closeClient() {
        rxClient.close();
    }

    final ElasticsearchClient rxClient() {
        return rxClient;
    }

    abstract boolean useNativeAsyncAPI();

    private ElasticsearchClientOptions getOptions() {
        return new ElasticsearchClientOptions()
                .setHosts(Collections.singletonList(new HttpHost().setPort(port())))
                .setUseNativeAsyncAPI(useNativeAsyncAPI());
    }

    private fr.myprysm.vertx.elasticsearch.ElasticsearchClient getClient(String name, ElasticsearchClientOptions options) {
        return fr.myprysm.vertx.elasticsearch.ElasticsearchClient.createShared(vertx, options, name);
    }

    @Nested
    class BaseElasticsearchRestClientTest {

        @Test
        @DisplayName("It should share rest client and close the local map when no client is running anymore")
        void itShouldCloseLocalData() {
            ElasticsearchClient otherClient = new ElasticsearchClient(getClient(CLIENT_NAME, getOptions()));
            assertThat(vertx.sharedData().getLocalMap(DS_LOCAL_MAP_NAME)).hasSize(1);
            otherClient.close();
            assertThat(vertx.sharedData().getLocalMap(DS_LOCAL_MAP_NAME)).hasSize(1);
            rxClient.close();
            assertThat(vertx.sharedData().getLocalMap(DS_LOCAL_MAP_NAME)).isEmpty();
        }

        @Test
        @DisplayName("It should not create a client with invalid options")
        void itShouldNotCreateWithInvalidOptions() {
            ElasticsearchClientOptions options = getOptions().setMaxRetryTimeout(-1);
            assertThatThrownBy(() -> getClient("bad-retry-timeout", options))
                    .isInstanceOf(ValidationException.class)
                    .hasMessageContaining("maxRetryTimeout")
                    .hasMessageContaining("0");
        }

        @Test
        @DisplayName("It should use path prefix")
        void itShouldUsePathPrefix() throws InterruptedException {
            ElasticsearchClientOptions options = getOptions().setPathPrefix("/prefix-test");
            ElasticsearchClient client = new ElasticsearchClient(getClient("test-prefix-path", options));
            registerHandler(HttpMethod.HEAD, "/prefix-test", event -> setStatus(event, RestStatus.OK).end());

            assertSuccessSingle(client.rxPing(), status -> assertThat(status).isTrue());
            client.close();
        }

        @Test
        @DisplayName("It should send default headers")
        void itShouldSendDefaultHeaders(VertxTestContext ctx) throws InterruptedException {
            ElasticsearchClientOptions options = getOptions().setDefaultHeaders(ImmutableMap.of("x-custom-header", "x-custom-value"));
            ElasticsearchClient client = new ElasticsearchClient(getClient("test-default-headers", options));
            registerHandler(HttpMethod.HEAD, "/", event -> {
                setStatus(event, RestStatus.OK).end();
                ctx.verify(() -> assertThat(event.request().headers().contains("x-custom-header", "x-custom-value", true)).isTrue());
            });
            assertSuccessSingle(client.rxPing(), status -> assertThat(status).isTrue());
            client.close();
            ctx.completeNow();
        }

        @Test
        @DisplayName("It should fail when timeout is exceeded")
        void itShouldTimeout() throws InterruptedException {
            ElasticsearchClientOptions options = getOptions().setMaxRetryTimeout(50);
            ElasticsearchClient client = new ElasticsearchClient(getClient("test-timeout", options));

            registerHandler(HttpMethod.GET, "/", event -> vertx.setTimer(100, timer -> setStatus(event, RestStatus.OK).end()));

            assertFailure(client.rxInfo().test(), IOException.class, err -> assertThat(err).hasMessage("listener timeout after waiting for [50] ms"));
            client.close();
        }

        @Test
        @DisplayName("It should store the client holder in vertx shared data")
        void itShouldStoreTheClientHolder() {
            LocalMap<String, BaseElasticsearchRestClient.ClientHolder> map = vertx.sharedData().getLocalMap(DS_LOCAL_MAP_NAME);
            BaseElasticsearchRestClient.ClientHolder theHolder = map.get(CLIENT_NAME);
            assertThat(theHolder.client()).isNotNull();
            assertThat(theHolder.config().getHosts()).contains(new HttpHost().setPort(port()));
        }

        @Test
        @DisplayName("It should ping")
        void itShouldPing() throws InterruptedException {
            assertSuccessSingle(rxClient.rxPing(), pong -> assertThat(pong).isFalse());

            noContent(HttpMethod.HEAD, "/", RestStatus.OK);
            assertSuccessSingle(rxClient.rxPing(), pong -> assertThat(pong).isTrue());
        }

        @Test
        @DisplayName("It should get info from the cluster")
        void ItShouldGetInfo() throws InterruptedException {
            assertFailure(rxClient.rxInfo().test(), ElasticsearchException.class, err -> assertThat(err).hasMessage("Unable to parse response body"));

            json(HttpMethod.GET, "/", RestStatus.OK, jsonResource(path("mock"), "info"));
            assertSuccessSingle(rxClient.rxInfo(), info -> assertThat(info.getClusterName()).isEqualTo("es.mediasearch"));
        }
    }
}
