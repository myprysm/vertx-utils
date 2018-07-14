package fr.myprysm.vertx.elasticsearch.impl;

import fr.myprysm.vertx.elasticsearch.ElasticsearchClient;
import fr.myprysm.vertx.elasticsearch.ElasticsearchClientOptions;
import fr.myprysm.vertx.elasticsearch.ElasticsearchClientOptionsValidation;
import fr.myprysm.vertx.validation.ValidationResult;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import lombok.extern.slf4j.Slf4j;

/**
 * Vertx Elasticsearch client factory.
 */
@Slf4j
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

    /**
     * Create a new client from the client holder.
     * <p>
     * Used for tests.
     *
     * @param vertx          the vertx instance
     * @param useNativeAsync whether to use the native ES async API
     * @param holder         custom Client Holder
     * @return the client
     */
    static ElasticsearchClient create(Vertx vertx, boolean useNativeAsync, BaseElasticsearchRestClient.ClientHolder holder) {
        if (useNativeAsync) {
            return new NativeAsyncElasticsearchRestClientImpl(vertx, holder);
        } else {
            return new VertxAsyncElasticsearchRestClientImpl(vertx, holder);
        }

    }


}
