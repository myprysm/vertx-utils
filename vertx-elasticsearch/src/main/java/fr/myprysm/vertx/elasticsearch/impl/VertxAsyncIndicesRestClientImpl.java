package fr.myprysm.vertx.elasticsearch.impl;

import fr.myprysm.vertx.elasticsearch.action.admin.indices.delete.DeleteIndexConverters;
import fr.myprysm.vertx.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import fr.myprysm.vertx.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import fr.myprysm.vertx.elasticsearch.action.admin.indices.get.GetIndexConverters;
import fr.myprysm.vertx.elasticsearch.action.admin.indices.get.GetIndexRequest;
import fr.myprysm.vertx.elasticsearch.converter.CommonConverters;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import org.elasticsearch.client.IndicesClient;

/**
 * Indices client working with {@link Vertx#executeBlocking(Handler, Handler)}.
 */
class VertxAsyncIndicesRestClientImpl extends BaseIndicesRestClient {
    /**
     * Build a new client with the official IndicesClient.
     *
     * @param vertx  the vertx instance
     * @param client the indices client instance
     */
    VertxAsyncIndicesRestClientImpl(Vertx vertx, IndicesClient client) {
        super(vertx, client);
    }

    @Override
    public void delete(DeleteIndexRequest request, Handler<AsyncResult<DeleteIndexResponse>> handler) {
        executeRequestBlocking(
                request,
                DeleteIndexConverters::requestToES,
                DeleteIndexConverters::responseToDataObject,
                handler,
                client()::delete);
    }

    @Override
    public void exists(GetIndexRequest request, Handler<AsyncResult<Boolean>> handler) {
        this.executeRequestBlocking(
                request,
                GetIndexConverters::requestToES,
                CommonConverters::objectToBoolean,
                handler,
                client()::exists);
    }
}
