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
    public void create(CreateIndexRequest request, Handler<AsyncResult<CreateIndexResponse>> handler) {
        executeRequestAsync(
                request,
                CreateIndexConverters::requestToES,
                CreateIndexConverters::responseToDataObject,
                handler,
                client()::createAsync
        );
    }

    @Override
    public void putMapping(PutMappingRequest request, Handler<AsyncResult<PutMappingResponse>> handler) {
        executeRequestAsync(
                request,
                PutMappingConverters::requestToES,
                PutMappingConverters::responseToDataObject,
                handler,
                client()::putMappingAsync
        );
    }

    @Override
    public void refresh(RefreshRequest request, Handler<AsyncResult<RefreshResponse>> handler) {
        executeRequestAsync(
                request,
                RefreshConverters::requestToES,
                RefreshConverters::responseToDataObject,
                handler,
                client()::refreshAsync
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
