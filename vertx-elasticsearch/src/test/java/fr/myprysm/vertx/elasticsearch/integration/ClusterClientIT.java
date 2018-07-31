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

package fr.myprysm.vertx.elasticsearch.integration;

import fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsRequest;
import io.vertx.core.json.JsonObject;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.cluster.routing.allocation.decider.EnableAllocationDecider;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.indices.recovery.RecoverySettings;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static fr.myprysm.vertx.elasticsearch.converter.CommonConverters.FLAT_SETTINGS;
import static fr.myprysm.vertx.elasticsearch.converter.CommonConverters.fromXContent;
import static fr.myprysm.vertx.json.JsonHelpers.extractObject;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.rest.RestStatus.BAD_REQUEST;

class ClusterClientIT extends VertxESIntegrationTestCase {

    @Test
    void testClusterPutSettings() throws InterruptedException {
        final String transientSettingKey = RecoverySettings.INDICES_RECOVERY_MAX_BYTES_PER_SEC_SETTING.getKey();
        final int transientSettingValue = 10;

        final String persistentSettingKey = EnableAllocationDecider.CLUSTER_ROUTING_ALLOCATION_ENABLE_SETTING.getKey();
        final String persistentSettingValue = EnableAllocationDecider.Allocation.NONE.name();

        Settings transientSettings = Settings.builder().put(transientSettingKey, transientSettingValue, ByteSizeUnit.BYTES).build();
        Map<String, Object> map = new HashMap<>();
        map.put(persistentSettingKey, persistentSettingValue);

        ClusterUpdateSettingsRequest setRequest = new ClusterUpdateSettingsRequest();
        setRequest.setTransientSettings(fromXContent(transientSettings, FLAT_SETTINGS));
        setRequest.setPersistentSettings(new JsonObject(map));

        assertSuccessSingle(rxClient().cluster().rxPutSettings(setRequest), setResponse -> {
            assertThat(setResponse.isAcknowledged()).isTrue();

            assertThat(extractObject(setResponse.getTransientSettings(), transientSettingKey))
                    .isNotEmpty()
                    .contains(transientSettingValue + ByteSizeUnit.BYTES.getSuffix());

            assertThat(extractObject(setResponse.getTransientSettings(), persistentSettingKey)).isEmpty();

            assertThat(extractObject(setResponse.getPersistentSettings(), persistentSettingKey))
                    .isNotEmpty()
                    .contains(persistentSettingValue);
            assertThat(extractObject(setResponse.getPersistentSettings(), transientSettingKey)).isEmpty();
        });

        assertSuccessSingle(rxClient().cluster().rxGetSettings(), getResponse -> {
            assertThat(extractObject(getResponse.getTransientSettings(), transientSettingKey))
                    .isNotEmpty()
                    .contains(transientSettingValue + ByteSizeUnit.BYTES.getSuffix());

            assertThat(extractObject(getResponse.getPersistentSettings(), persistentSettingKey))
                    .isNotEmpty()
                    .contains(persistentSettingValue);
        });

        ClusterUpdateSettingsRequest resetRequest = new ClusterUpdateSettingsRequest();
        resetRequest.setTransientSettings(fromXContent(Settings.builder().putNull(transientSettingKey).build(), FLAT_SETTINGS));
        resetRequest.setPersistentSettings(new JsonObject("{\"" + persistentSettingKey + "\": null }"));

        assertSuccessSingle(rxClient().cluster().rxPutSettings(resetRequest), setResponse -> {
            assertThat(setResponse.isAcknowledged()).isTrue();
            assertThat(extractObject(setResponse.getTransientSettings(), transientSettingKey)).isEmpty();
            assertThat(extractObject(setResponse.getPersistentSettings(), persistentSettingKey)).isEmpty();
        });

        assertSuccessSingle(rxClient().cluster().rxGetSettings(), getResponse -> {
            assertThat(extractObject(getResponse.getTransientSettings(), transientSettingKey)).isEmpty();
            assertThat(extractObject(getResponse.getPersistentSettings(), persistentSettingKey)).isEmpty();
        });
    }

    @Test
    void testClusterUpdateSettingNonExistent() throws InterruptedException {
        String setting = "no_idea_what_you_are_talking_about";
        int value = 10;
        ClusterUpdateSettingsRequest clusterUpdateSettingsRequest = new ClusterUpdateSettingsRequest();
        clusterUpdateSettingsRequest.setTransientSettings(fromXContent(Settings.builder().put(setting, value).build()));
        assertFailure(rxClient().cluster().rxPutSettings(clusterUpdateSettingsRequest).test(), ElasticsearchException.class, err -> {
            assertThat(err.status()).isEqualTo(BAD_REQUEST);
            assertThat(err).hasMessage(
                    "Elasticsearch exception [type=illegal_argument_exception, reason=transient setting ["
                            + setting
                            + "], not recognized]"
            );

        });
    }
}
