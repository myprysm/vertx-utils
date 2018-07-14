package fr.myprysm.vertx.elasticsearch;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.client.RestClientBuilder.DEFAULT_MAX_RETRY_TIMEOUT_MILLIS;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@DataObject(generateConverter = true)
public class ElasticsearchClientOptions {

    private boolean useNativeAsyncAPI = true;
    private Map<String, String> defaultHeaders = new HashMap<>();
    private int maxRetryTimeout = DEFAULT_MAX_RETRY_TIMEOUT_MILLIS;
    private List<HttpHost> hosts = Collections.singletonList(new HttpHost());
    private String pathPrefix;

    public ElasticsearchClientOptions(ElasticsearchClientOptions other) {
        useNativeAsyncAPI = other.useNativeAsyncAPI;
        defaultHeaders = other.defaultHeaders;
        maxRetryTimeout = other.maxRetryTimeout;
        hosts = other.hosts;
        pathPrefix = other.pathPrefix;
    }

    public ElasticsearchClientOptions(JsonObject json) {
        ElasticsearchClientOptionsConverter.fromJson(json, this);
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        ElasticsearchClientOptionsConverter.toJson(this, json);
        return json;
    }

}
