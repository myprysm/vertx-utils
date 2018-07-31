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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NativeESClientTest extends AbstractESClientTest {
    @Override
    boolean useNativeAsyncAPI() {
        return true;
    }

    @Test
    @DisplayName("It should provide native async cluster and indices clients")
    void itShouldProvideNativeAsyncClients() {
        assertThat(rxClient().getDelegate()).isInstanceOf(NativeAsyncElasticsearchRestClientImpl.class);
        assertThat(rxClient().indices().getDelegate()).isInstanceOf(NativeAsyncIndicesRestClientImpl.class);
        assertThat(rxClient().cluster().getDelegate()).isInstanceOf(NativeAsyncClusterRestClientImpl.class);
    }
}
