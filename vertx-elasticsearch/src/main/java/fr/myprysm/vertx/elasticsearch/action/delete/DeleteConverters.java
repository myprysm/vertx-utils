package fr.myprysm.vertx.elasticsearch.action.delete;

import fr.myprysm.vertx.elasticsearch.action.support.SupportConverters;
import org.elasticsearch.common.unit.TimeValue;

/**
 * Delete request converters.
 */
public interface DeleteConverters {

    /**
     * Transforms a DataObject request into an Elasticsearch request.
     *
     * @param request the request
     * @return the Elasticsearch request
     */
    static org.elasticsearch.action.delete.DeleteRequest requestToES(DeleteRequest request) {
        org.elasticsearch.action.delete.DeleteRequest esRequest = new org.elasticsearch.action.delete.DeleteRequest();

        if (request.getWaitForActiveShards() != null) {
            esRequest.waitForActiveShards(request.getWaitForActiveShards());
        }

        return esRequest.index(request.getIndex())
                .type(request.getType())
                .id(request.getId())
                .routing(request.getRouting())
                .parent(request.getParent())
                .version(request.getVersion())
                .versionType(request.getVersionType())
                .setRefreshPolicy(request.getRefreshPolicy())
                .timeout(TimeValue.timeValueMillis(request.getTimeout()));

    }

    /**
     * Transforms an Elasticsearch response into a DataObject response.
     *
     * @param esResponse the Elasticsearch response
     * @return the response
     */
    static DeleteResponse responseToDataObject(org.elasticsearch.action.delete.DeleteResponse esResponse) {
        return new DeleteResponse(SupportConverters.docWriteToDataObjectResponse(esResponse));
    }
}
