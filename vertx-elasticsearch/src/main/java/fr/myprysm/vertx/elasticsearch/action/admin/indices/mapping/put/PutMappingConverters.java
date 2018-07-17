package fr.myprysm.vertx.elasticsearch.action.admin.indices.mapping.put;

import fr.myprysm.vertx.elasticsearch.converter.ConverterUtils;
import org.elasticsearch.common.unit.TimeValue;

public interface PutMappingConverters {

    static org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest requestToES(PutMappingRequest request) {
        org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest esRequest =
                new org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest();

        return esRequest.indices(request.getIndices().toArray(new String[]{}))
                .type(request.getType())
                .source(ConverterUtils.convert(request.getSource()))
                .timeout(TimeValue.timeValueMillis(request.getTimeout()));
    }

    static PutMappingResponse responseToDataObject(org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse esResponse) {
        return new PutMappingResponse(esResponse.isAcknowledged());
    }
}
