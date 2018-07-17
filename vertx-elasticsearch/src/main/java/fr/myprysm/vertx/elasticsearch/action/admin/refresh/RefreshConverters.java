package fr.myprysm.vertx.elasticsearch.action.admin.refresh;

import fr.myprysm.vertx.elasticsearch.action.support.SupportConverters;
import fr.myprysm.vertx.elasticsearch.converter.ConverterUtils;

import java.util.Arrays;

public interface RefreshConverters {

    static org.elasticsearch.action.admin.indices.refresh.RefreshRequest requestToES(RefreshRequest request) {
        org.elasticsearch.action.admin.indices.refresh.RefreshRequest esRequest = new org.elasticsearch.action.admin.indices.refresh.RefreshRequest();
        if (request.getIndicesOptions() != null) {
            esRequest.indicesOptions(request.getIndicesOptions());
        }

        return esRequest.indices(request.getIndices().toArray(new String[]{}));
    }

    static RefreshResponse responseToDataObject(org.elasticsearch.action.admin.indices.refresh.RefreshResponse esResponse) {
        RefreshResponse response = new RefreshResponse();

        if (esResponse.getShardFailures().length > 0) {
            response.setShardFailures(ConverterUtils.convert(
                    Arrays.asList(esResponse.getShardFailures()),
                    SupportConverters::shardOperationFailureToDataObject
            ));
        }

        return response.setFailedShards(esResponse.getFailedShards())
                .setSuccessfulShards(esResponse.getSuccessfulShards())
                .setTotalShards(esResponse.getTotalShards())
                .setStatus(esResponse.getStatus());
    }
}
