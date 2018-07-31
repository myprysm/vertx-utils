package fr.myprysm.vertx.elasticsearch.impl;

import fr.myprysm.vertx.elasticsearch.action.admin.indices.create.CreateIndexConverters;
import fr.myprysm.vertx.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import fr.myprysm.vertx.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import fr.myprysm.vertx.elasticsearch.action.admin.indices.delete.DeleteIndexConverters;
import fr.myprysm.vertx.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import fr.myprysm.vertx.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import fr.myprysm.vertx.elasticsearch.action.admin.indices.get.GetIndexConverters;
import fr.myprysm.vertx.elasticsearch.action.admin.indices.get.GetIndexRequest;
import fr.myprysm.vertx.elasticsearch.action.admin.indices.mapping.put.PutMappingConverters;
import fr.myprysm.vertx.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import fr.myprysm.vertx.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import fr.myprysm.vertx.elasticsearch.action.admin.refresh.RefreshConverters;
import fr.myprysm.vertx.elasticsearch.action.admin.refresh.RefreshRequest;
import fr.myprysm.vertx.elasticsearch.action.admin.refresh.RefreshResponse;
import fr.myprysm.vertx.elasticsearch.converter.CommonConverters;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * Indices client working with {@link Vertx#executeBlocking(Handler, Handler)}.
 */
class VertxAsyncIndicesRestClientImpl extends BaseIndicesRestClient {
    /**
     * Build a new client with the official IndicesClient.
     *
     * @param vertx  the vertx instance
     * @param client the indices client instance
     * @param name   the name of the client
     */
    VertxAsyncIndicesRestClientImpl(Vertx vertx, RestHighLevelClient client, String name) {
        super(vertx, client, name);
    }

    @Override
    public void delete(DeleteIndexRequest request, Handler<AsyncResult<DeleteIndexResponse>> handler) {
        executeRequestBlocking(
                request,
                DeleteIndexConverters::requestToES,
                DeleteIndexConverters::responseToDataObject,
                handler,
                indicesClient()::delete);
    }

    @Override
    public void create(CreateIndexRequest request, Handler<AsyncResult<CreateIndexResponse>> handler) {
        executeRequestBlocking(
                request,
                CreateIndexConverters::requestToES,
                CreateIndexConverters::responseToDataObject,
                handler,
                indicesClient()::create);
    }

    @Override
    public void putMapping(PutMappingRequest request, Handler<AsyncResult<PutMappingResponse>> handler) {
        executeRequestBlocking(
                request,
                PutMappingConverters::requestToES,
                PutMappingConverters::responseToDataObject,
                handler,
                indicesClient()::putMapping);
    }

    @Override
    public void refresh(RefreshRequest request, Handler<AsyncResult<RefreshResponse>> handler) {
        executeRequestBlocking(
                request,
                RefreshConverters::requestToES,
                RefreshConverters::responseToDataObject,
                handler,
                indicesClient()::refresh
        );
    }

    @Override
    public void exists(GetIndexRequest request, Handler<AsyncResult<Boolean>> handler) {
        executeRequestBlocking(
                request,
                GetIndexConverters::requestToES,
                CommonConverters::objectToBoolean,
                handler,
                indicesClient()::exists);
    }
}
