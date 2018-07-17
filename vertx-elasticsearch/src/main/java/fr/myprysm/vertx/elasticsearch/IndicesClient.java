package fr.myprysm.vertx.elasticsearch;

import fr.myprysm.vertx.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import fr.myprysm.vertx.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import fr.myprysm.vertx.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import fr.myprysm.vertx.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import fr.myprysm.vertx.elasticsearch.action.admin.indices.get.GetIndexRequest;
import fr.myprysm.vertx.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import fr.myprysm.vertx.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import fr.myprysm.vertx.elasticsearch.action.admin.refresh.RefreshRequest;
import fr.myprysm.vertx.elasticsearch.action.admin.refresh.RefreshResponse;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

/**
 * Vertx Elasticsearch indices client.
 * <p>
 * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/indices.html">Indices API on elastic.co</a>
 */
@VertxGen
public interface IndicesClient {

    /**
     * Asynchronously deletes an index using the Delete Index API.
     * <p>
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/indices-delete-index.html">
     * Delete Index API on elastic.co</a>
     *
     * @param request the request
     * @param handler the handler
     */
    void delete(DeleteIndexRequest request, Handler<AsyncResult<DeleteIndexResponse>> handler);

    void create(CreateIndexRequest request, Handler<AsyncResult<CreateIndexResponse>> handler);

    //
    void putMapping(PutMappingRequest request, Handler<AsyncResult<PutMappingResponse>> handler);

    //
//    void updateAliases(IndicesAliasesRequest request, Handler<AsyncResult<IndicesAliasesResponse>> handler);
//
//    void open(OpenIndexRequest request, Handler<AsyncResult<OpenIndexResponse>> handler);
//
//    void close(CloseIndexRequest request, Handler<AsyncResult<CloseIndexResponse>> handler);
//
//    void existsAlias(GetAliasesRequest request, Handler<AsyncResult<GetAliasesResponse>> handler);
//
    void refresh(RefreshRequest request, Handler<AsyncResult<RefreshResponse>> handler);
//
//    void flush(FlushRequest request, Handler<AsyncResult<FlushResponse>> handler);
//
//    void forceMerge(ForceMergeRequest request, Handler<AsyncResult<ForceMergeResponse>> handler);
//
//    void clearCache(ClearIndicesCacheRequest request, Handler<AsyncResult<ClearIndicesCacheResponse>> handler);
//

    /**
     * Asynchronously checks if one or more aliases exist using the Aliases Exist API.
     * <p>
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/indices-aliases.html">
     * Indices Aliases API on elastic.co</a>
     *
     * @param request the request
     * @param handler the handler
     */
    void exists(GetIndexRequest request, Handler<AsyncResult<Boolean>> handler);
//
//    void shrink(ResizeRequest request, Handler<AsyncResult<ResizeResponse>> handler);
//
//    void split(ResizeRequest request, Handler<AsyncResult<ResizeResponse>> handler);
//
//    void rollover(RolloverRequest request, Handler<AsyncResult<RolloverResponse>> handler);
//
//    void putSettings(UpdateSettingsRequest request, Handler<AsyncResult<UpdateSettingsResponse>> handler);

}
