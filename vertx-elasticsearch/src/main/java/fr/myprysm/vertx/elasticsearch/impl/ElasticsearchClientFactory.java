package fr.myprysm.vertx.elasticsearch.impl;

import fr.myprysm.vertx.elasticsearch.ElasticsearchClient;
import fr.myprysm.vertx.elasticsearch.ElasticsearchClientOptions;
import fr.myprysm.vertx.elasticsearch.ElasticsearchClientOptionsValidation;
import fr.myprysm.vertx.validation.ValidationResult;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

/**
 * Vertx Elasticsearch client factory.
 */
public final class ElasticsearchClientFactory {

    /**
     * Service class.
     */
    private ElasticsearchClientFactory() {
        //
    }

    /**
     * Create a new client from the options.
     *
     * @param vertx      the vertx instance
     * @param config     the config
     * @param clientName the client name
     * @return the client
     */
    public static ElasticsearchClient create(Vertx vertx, JsonObject config, String clientName) {
        ValidationResult result = ElasticsearchClientOptionsValidation.validate(config);
        if (!result.isValid()) {
            throw result.toException();
        }

        ElasticsearchClientOptions options = new ElasticsearchClientOptions(config);

        if (options.isUseNativeAsyncAPI()) {
            return new NativeAsyncElasticsearchRestClientImpl(vertx, options, clientName);
        } else {
            return new VertxAsyncElasticsearchRestClientImpl(vertx, options, clientName);
        }

    }

}
