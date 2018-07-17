package fr.myprysm.vertx.elasticsearch.action.support;

import fr.myprysm.vertx.elasticsearch.converter.ConverterUtils;
import io.vertx.core.json.JsonArray;
import org.elasticsearch.action.support.DefaultShardOperationFailedException;
import org.elasticsearch.action.support.replication.ReplicationResponse;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Support converters.
 */
public interface SupportConverters {
    /**
     * Transforms an Elasticsearch DocWriteResponse into a DataObject response.
     *
     * @param esResponse the Elasticsearch response
     * @return the response
     */
    static DocWriteResponse docWriteToDataObjectResponse(org.elasticsearch.action.DocWriteResponse esResponse) {
        DocWriteResponse response = new DocWriteResponse(
                shardIdToDataObject(esResponse.getShardId()),
                esResponse.getIndex(),
                esResponse.getType(),
                esResponse.getId(),
                esResponse.getVersion(),
                esResponse.getSeqNo(),
                esResponse.getPrimaryTerm(),
                esResponse.forcedRefresh(),
                esResponse.getResult()
        );

        response.setShardInfo(shardInfoToDataObject(esResponse.getShardInfo()));

        return response;
    }

    /**
     * Transforms an Elasticsearch shard id into a DataObject shard id.
     *
     * @param shardId the Elasticsearch shard id
     * @return the shard id
     */
    static ShardId shardIdToDataObject(org.elasticsearch.index.shard.ShardId shardId) {
        return new ShardId(shardId.getIndexName(), shardId.getId());
    }

    /**
     * Transforms an Elasticsearch shard info into a DataObject shard info.
     *
     * @param esShardInfo the Elasticsearch shard info
     * @return the shard info
     */
    static ShardInfo shardInfoToDataObject(org.elasticsearch.action.support.replication.ReplicationResponse.ShardInfo esShardInfo) {
        ShardInfo shardInfo = new ShardInfo();
        if (esShardInfo.getFailed() > 0) {
            shardInfo.setFailures(ConverterUtils.convert(Arrays.asList(esShardInfo.getFailures()), SupportConverters::shardInfoFailureToDataObject));
        }
        return shardInfo
                .setSuccessful(esShardInfo.getSuccessful())
                .setTotal(esShardInfo.getTotal())
                .setFailed(esShardInfo.getFailed());
    }


    /**
     * Transforms a DataObject FetchSourceContext into the ElasticSearch equivalent.
     *
     * @param context the data object context
     * @return the elasticsearch context
     */
    static org.elasticsearch.search.fetch.subphase.FetchSourceContext fetchSourceContextToES(
            fr.myprysm.vertx.elasticsearch.action.support.FetchSourceContext context) {

        return new org.elasticsearch.search.fetch.subphase.FetchSourceContext(
                context.isFetchSource(),
                context.getIncludes() == null ? new String[]{} : context.getIncludes().toArray(new String[]{}),
                context.getExcludes() == null ? new String[]{} : context.getExcludes().toArray(new String[]{})
        );
    }

    /**
     * Transforms an Elasticsearch shard info failure into a DataObject failure.
     *
     * @param esFailure the Elasticsearch failure
     * @return the failure
     */
    static ShardInfoFailure shardInfoFailureToDataObject(ReplicationResponse.ShardInfo.Failure esFailure) {
        return new ShardInfoFailure(
                String.valueOf(esFailure.shardId()),
                esFailure.nodeId(),
                esFailure.status(),
                esFailure.primary(),
                esFailure.reason()
        );
    }

    static ShardFailure shardSearchFailureToDataObject(org.elasticsearch.action.search.ShardSearchFailure failure) {
        return new ShardFailure(
                failure.status(),
                failure.index(),
                failure.shardId(),
                failure.reason()
        );
    }

    static ShardFailure shardOperationFailureToDataObject(DefaultShardOperationFailedException failure) {
        return new ShardFailure(
                failure.status(),
                failure.index(),
                failure.shardId(),
                failure.reason()
        );
    }

    /**
     * Transforms a DataObject script into an Elasticsearch script.
     *
     * @param script the script
     * @return the Elasticsearch script
     */
    static org.elasticsearch.script.Script scriptToES(Script script) {
        return new org.elasticsearch.script.Script(
                script.getType(),
                script.getLang(),
                script.getIdOrCode(),
                (script.getOptions() == null) ? new HashMap<>() : script.getOptions(),
                (script.getParams() == null) ? new HashMap<>() : ConverterUtils.convert(script.getParams())
        );
    }

    /**
     * Transforms an Elasticsearch document field into a DataObject document field.
     *
     * @param esField the Elasticsearch document field
     * @return the document field
     */
    static DocumentField documentFieldToDataObject(org.elasticsearch.common.document.DocumentField esField) {
        return new DocumentField(esField.getName(), new JsonArray(esField.getValues()));
    }
}
