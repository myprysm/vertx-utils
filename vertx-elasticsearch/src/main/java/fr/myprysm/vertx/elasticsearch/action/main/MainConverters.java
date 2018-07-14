package fr.myprysm.vertx.elasticsearch.action.main;

/**
 * Main request converters.
 */
public interface MainConverters {

    /**
     * Transforms an Elasticsearch response into a DataObject response.
     *
     * @param esResponse the Elasticsearch response
     * @return the response
     */
    static MainResponse responseToDataObject(org.elasticsearch.action.main.MainResponse esResponse) {
        return new MainResponse(
                esResponse.getNodeName(),
                esResponse.getVersion().toString(),
                esResponse.getClusterName().value(),
                esResponse.getClusterUuid(),
                esResponse.getBuild().toString(),
                esResponse.isAvailable()
        );
    }
}
