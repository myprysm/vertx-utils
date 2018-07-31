package fr.myprysm.vertx.elasticsearch.action.admin.cluster;

import fr.myprysm.vertx.elasticsearch.converter.CommonConverters;
import fr.myprysm.vertx.json.Json;

/**
 * ClusterUpdateSettings request converters.
 */
public interface ClusterUpdateSettingsConverters {
    /**
     * Transforms a DataObject request into an Elasticsearch request.
     *
     * @param request the request
     * @return the Elasticsearch request
     */
    static org.elasticsearch.action.admin.cluster.settings.ClusterUpdateSettingsRequest requestToES(ClusterUpdateSettingsRequest request) {
        org.elasticsearch.action.admin.cluster.settings.ClusterUpdateSettingsRequest esRequest =
                new org.elasticsearch.action.admin.cluster.settings.ClusterUpdateSettingsRequest();

        if (request.getPersistentSettings() != null && !request.getPersistentSettings().isEmpty()) {
            esRequest.persistentSettings(Json.convertWithNullValues(request.getPersistentSettings()));
        }

        if (request.getTransientSettings() != null && !request.getTransientSettings().isEmpty()) {
            esRequest.transientSettings(Json.convertWithNullValues(request.getTransientSettings()));
        }

        return esRequest;
    }

    /**
     * Transforms an Elasticsearch response into a DataObject response.
     *
     * @param esResponse the Elasticsearch response
     * @return the response
     */
    static ClusterUpdateSettingsResponse responseToDataObject(
            org.elasticsearch.action.admin.cluster.settings.ClusterUpdateSettingsResponse esResponse) {

        return new ClusterUpdateSettingsResponse(
                esResponse.isAcknowledged(),
                CommonConverters.fromXContent(esResponse.getTransientSettings()),
                CommonConverters.fromXContent(esResponse.getPersistentSettings())
        );
    }
}
