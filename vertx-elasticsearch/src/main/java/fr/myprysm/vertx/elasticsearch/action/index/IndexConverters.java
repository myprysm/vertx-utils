package fr.myprysm.vertx.elasticsearch.action.index;

import fr.myprysm.vertx.elasticsearch.action.support.SupportConverters;
import fr.myprysm.vertx.json.Json;
import org.elasticsearch.common.unit.TimeValue;

/**
 * Index request converters.
 */
public interface IndexConverters {

    /**
     * Transforms a DataObject request into an Elasticsearch request.
     *
     * @param request the request
     * @return the Elasticsearch request
     */
    static org.elasticsearch.action.index.IndexRequest requestToES(IndexRequest request) {
        org.elasticsearch.action.index.IndexRequest esRequest = new org.elasticsearch.action.index.IndexRequest();

        return esRequest.id(request.getId())
                .index(request.getIndex())
                .type(request.getType())
                .parent(request.getParent())
                .routing(request.getRouting())
                .source(Json.convert(request.getSource()))
                .setPipeline(request.getPipeline())
                .version(request.getVersion())
                .versionType(request.getVersionType())
                .opType(request.getOpType())
                .setRefreshPolicy(request.getRefreshPolicy())
                .timeout(TimeValue.timeValueMillis(request.getTimeout()));

    }

    /**
     * Transforms an Elasticsearch response into a DataObject response.
     *
     * @param esResponse the Elasticsearch response
     * @return the response
     */
    static IndexResponse responseToDataObject(org.elasticsearch.action.index.IndexResponse esResponse) {
        return new IndexResponse(SupportConverters.docWriteToDataObjectResponse(esResponse)).setStatus(esResponse.status());
    }
}
