package fr.myprysm.vertx.elasticsearch.action.update;

import fr.myprysm.vertx.elasticsearch.action.get.GetConverters;
import fr.myprysm.vertx.elasticsearch.action.support.SupportConverters;
import fr.myprysm.vertx.elasticsearch.converter.ConverterUtils;
import org.elasticsearch.action.get.GetResponse;

/**
 * Update request converters.
 */
public interface UpdateConverters {
    /**
     * Transforms a DataObject request into an Elasticsearch request.
     *
     * @param request the request
     * @return the Elasticsearch request
     */
    static org.elasticsearch.action.update.UpdateRequest requestToES(UpdateRequest request) {
        org.elasticsearch.action.update.UpdateRequest esRequest = new org.elasticsearch.action.update.UpdateRequest();

        if (request.getWaitForActiveShards() != null) {
            esRequest.waitForActiveShards(request.getWaitForActiveShards());
        }
        if (request.getScript() != null) {
            esRequest.script(SupportConverters.scriptToES(request.getScript()));
        }

        if (request.getFetchSourceContext() != null) {
            esRequest.fetchSource(SupportConverters.fetchSourceContextToES(request.getFetchSourceContext()));
        }

        if (request.getDoc() != null) {
            esRequest.doc(ConverterUtils.convert(request.getDoc()));
        }

        if (request.getUpsert() != null) {
            esRequest.upsert(ConverterUtils.convert(request.getUpsert()));
        }

        return esRequest
                .index(request.getIndex())
                .type(request.getType())
                .id(request.getId())
                .routing(request.getRouting())
                .parent(request.getParent())
                .version(request.getVersion())
                .versionType(request.getVersionType())
                .retryOnConflict(request.getRetryOnConflict())
                .setRefreshPolicy(request.getRefreshPolicy())
                .docAsUpsert(request.isDocAsUpsert())
                .scriptedUpsert(request.isScriptedUpsert())
                .detectNoop(request.isDetectNoop());
    }

    /**
     * Transforms an Elasticsearch response into a DataObject response.
     *
     * @param esResponse the Elasticsearch response
     * @return the response
     */
    static UpdateResponse responseToDataObject(org.elasticsearch.action.update.UpdateResponse esResponse) {
        return new UpdateResponse(SupportConverters.docWriteToDataObjectResponse(esResponse))
                .setGetResult(GetConverters.responseToDataObject(new GetResponse(esResponse.getGetResult())))
                .setStatus(esResponse.status());
    }
}
