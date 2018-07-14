package fr.myprysm.vertx.elasticsearch.impl;

import fr.myprysm.vertx.elasticsearch.action.admin.indices.delete.DeleteIndexConverters;
import fr.myprysm.vertx.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import fr.myprysm.vertx.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import fr.myprysm.vertx.elasticsearch.action.admin.indices.get.GetIndexConverters;
import fr.myprysm.vertx.elasticsearch.action.admin.indices.get.GetIndexRequest;
import fr.myprysm.vertx.elasticsearch.converter.CommonConverters;
import fr.myprysm.vertx.elasticsearch.converter.Converter;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;

/**
 * Indices client working with the Elasticsearch Async API.
 */
class NativeAsyncIndicesRestClientImpl extends BaseIndicesRestClient {

    /**
     * Build a new client with the official IndicesClient.
     *
     * @param vertx  the vertx instance
     * @param client the indices client instance
     */
    NativeAsyncIndicesRestClientImpl(Vertx vertx, org.elasticsearch.client.IndicesClient client) {
        super(vertx, client);
    }

    @Override
    public void delete(DeleteIndexRequest request, Handler<AsyncResult<DeleteIndexResponse>> handler) {
        executeRequestAsync(
                request,
                DeleteIndexConverters::requestToES,
                DeleteIndexConverters::responseToDataObject,
                handler,
                client()::deleteAsync
        );
    }

    @Override
    public void exists(GetIndexRequest request, Handler<AsyncResult<Boolean>> handler) {
        executeRequestAsync(
                request,
                GetIndexConverters::requestToES,
                (Converter<Boolean, Boolean>) CommonConverters::objectToBoolean,
                handler,
                client()::existsAsync
        );
    }
}
