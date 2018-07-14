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
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Elasticsearch client working with {@link Vertx#executeBlocking(Handler, Handler)}.
 */
class VertxAsyncElasticsearchRestClientImpl extends BaseElasticsearchRestClient {

    /**
     * Build a new client with the options and the provided name.
     *
     * @param vertx      the current vertx instance
     * @param options    the options
     * @param clientName the client name
     */
    VertxAsyncElasticsearchRestClientImpl(Vertx vertx, ElasticsearchClientOptions options, String clientName) {
        super(vertx, options, clientName);
    }

    /**
     * Build a new client with the provided holder.
     *
     * @param vertx  the current vertx instance
     * @param holder the elasticsearch client holder
     */
    VertxAsyncElasticsearchRestClientImpl(Vertx vertx, ClientHolder holder) {
        super(vertx, holder);
    }

    @Override
    Pair<IndicesClient, ClusterClient> getClients() {
        return Pair.of(
                new VertxAsyncIndicesRestClientImpl(vertx(), client().indices()),
                new VertxAsyncClusterRestClientImpl(vertx(), client().cluster())
        );
    }

    @Override
    public void get(GetRequest request, Handler<AsyncResult<GetResponse>> handler) {
        executeRequestBlocking(
                request,
                GetConverters::requestToES,
                GetConverters::responseToDataObject,
                handler,
                client()::get
        );
    }

    @Override
    public void exists(GetRequest request, Handler<AsyncResult<Boolean>> handler) {
        executeRequestBlocking(
                request,
                GetConverters::requestToES,
                res -> res,
                handler,
                client()::exists
        );
    }

    @Override
    public void multiGet(MultiGetRequest request, Handler<AsyncResult<MultiGetResponse>> handler) {
        executeRequestBlocking(
                request,
                MultiGetConverters::requestToES,
                MultiGetConverters::responseToDataObject,
                handler,
                client()::multiGet
        );
    }

    @Override
    public void index(IndexRequest request, Handler<AsyncResult<IndexResponse>> handler) {
        executeRequestBlocking(
                request,
                IndexConverters::requestToES,
                IndexConverters::responseToDataObject,
                handler,
                client()::index
        );
    }

    @Override
    public void update(UpdateRequest request, Handler<AsyncResult<UpdateResponse>> handler) {
        executeRequestBlocking(
                request,
                UpdateConverters::requestToES,
                UpdateConverters::responseToDataObject,
                handler,
                client()::update
        );
    }

    @Override
    public void delete(DeleteRequest request, Handler<AsyncResult<DeleteResponse>> handler) {
        executeRequestBlocking(
                request,
                DeleteConverters::requestToES,
                DeleteConverters::responseToDataObject,
                handler,
                client()::delete
        );
    }

    @Override
    public void bulk(BulkRequest request, Handler<AsyncResult<BulkResponse>> handler) {
        executeRequestBlocking(
                request,
                BulkConverters::requestToES,
                BulkConverters::responseToDataObject,
                handler,
                client()::bulk
        );
    }
}