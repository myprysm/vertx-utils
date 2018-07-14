package fr.myprysm.vertx.elasticsearch.impl;

import fr.myprysm.vertx.elasticsearch.ClusterClient;
import fr.myprysm.vertx.elasticsearch.ElasticsearchClientOptions;
import fr.myprysm.vertx.elasticsearch.IndicesClient;
import fr.myprysm.vertx.elasticsearch.action.bulk.BulkConverters;
import fr.myprysm.vertx.elasticsearch.action.bulk.BulkRequest;
import fr.myprysm.vertx.elasticsearch.action.bulk.BulkResponse;
import fr.myprysm.vertx.elasticsearch.action.delete.DeleteConverters;
import fr.myprysm.vertx.elasticsearch.action.delete.DeleteRequest;
import fr.myprysm.vertx.elasticsearch.action.delete.DeleteResponse;
import fr.myprysm.vertx.elasticsearch.action.get.GetConverters;
import fr.myprysm.vertx.elasticsearch.action.get.GetRequest;
import fr.myprysm.vertx.elasticsearch.action.get.GetResponse;
import fr.myprysm.vertx.elasticsearch.action.get.MultiGetConverters;
import fr.myprysm.vertx.elasticsearch.action.get.MultiGetRequest;
import fr.myprysm.vertx.elasticsearch.action.get.MultiGetResponse;
import fr.myprysm.vertx.elasticsearch.action.index.IndexConverters;
import fr.myprysm.vertx.elasticsearch.action.index.IndexRequest;
import fr.myprysm.vertx.elasticsearch.action.index.IndexResponse;
import fr.myprysm.vertx.elasticsearch.action.update.UpdateConverters;
import fr.myprysm.vertx.elasticsearch.action.update.UpdateRequest;
import fr.myprysm.vertx.elasticsearch.action.update.UpdateResponse;
import fr.myprysm.vertx.elasticsearch.converter.CommonConverters;
import fr.myprysm.vertx.elasticsearch.converter.Converter;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Elasticsearch client working with the Elasticsearch Async API.
 */
class NativeAsyncElasticsearchRestClientImpl extends BaseElasticsearchRestClient {

    /**
     * Build a new client with the options and the provided name.
     *
     * @param vertx      the current vertx instance
     * @param options    the options
     * @param clientName the client name
     */
    NativeAsyncElasticsearchRestClientImpl(Vertx vertx, ElasticsearchClientOptions options, String clientName) {
        super(vertx, options, clientName);
    }

    /**
     * Build a new client with the provided holder.
     *
     * @param vertx  the current vertx instance
     * @param holder the elasticsearch client holder
     */
    NativeAsyncElasticsearchRestClientImpl(Vertx vertx, ClientHolder holder) {
        super(vertx, holder);
    }

    @Override
    Pair<IndicesClient, ClusterClient> getClients() {
        return Pair.of(
                new NativeAsyncIndicesRestClientImpl(vertx(), client().indices()),
                new NativeAsyncClusterRestClientImpl(vertx(), client().cluster())
        );
    }


    @Override
    public void get(GetRequest request, Handler<AsyncResult<GetResponse>> handler) {
        executeRequestAsync(
                request,
                GetConverters::requestToES,
                GetConverters::responseToDataObject,
                handler,
                client()::getAsync
        );
    }

    @Override
    public void exists(GetRequest request, Handler<AsyncResult<Boolean>> handler) {
        executeRequestAsync(
                request,
                GetConverters::requestToES,
                (Converter<Boolean, Boolean>) CommonConverters::objectToBoolean,
                handler,
                client()::existsAsync
        );
    }

    @Override
    public void multiGet(MultiGetRequest request, Handler<AsyncResult<MultiGetResponse>> handler) {
        executeRequestAsync(
                request,
                MultiGetConverters::requestToES,
                MultiGetConverters::responseToDataObject,
                handler,
                client()::multiGetAsync
        );
    }

    @Override
    public void index(IndexRequest request, Handler<AsyncResult<IndexResponse>> handler) {
        executeRequestAsync(
                request,
                IndexConverters::requestToES,
                IndexConverters::responseToDataObject,
                handler,
                client()::indexAsync
        );
    }

    @Override
    public void update(UpdateRequest request, Handler<AsyncResult<UpdateResponse>> handler) {
        executeRequestAsync(
                request,
                UpdateConverters::requestToES,
                UpdateConverters::responseToDataObject,
                handler,
                client()::updateAsync
        );
    }

    @Override
    public void delete(DeleteRequest request, Handler<AsyncResult<DeleteResponse>> handler) {
        executeRequestAsync(
                request,
                DeleteConverters::requestToES,
                DeleteConverters::responseToDataObject,
                handler,
                client()::deleteAsync
        );
    }

    @Override
    public void bulk(BulkRequest request, Handler<AsyncResult<BulkResponse>> handler) {
        executeRequestAsync(
                request,
                BulkConverters::requestToES,
                BulkConverters::responseToDataObject,
                handler,
                client()::bulkAsync
        );
    }

}