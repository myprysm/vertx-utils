package fr.myprysm.vertx.elasticsearch.action.admin.cluster;

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
        return null;
    }

    /**
     * Transforms an Elasticsearch response into a DataObject response.
     *
     * @param esResponse the Elasticsearch response
     * @return the response
     */
    static ClusterUpdateSettingsResponse responseToDataObject(
            org.elasticsearch.action.admin.cluster.settings.ClusterUpdateSettingsResponse esResponse) {
        return null;
    }
}
