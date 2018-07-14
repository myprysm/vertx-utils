package fr.myprysm.vertx.elasticsearch.action.get;

import fr.myprysm.vertx.elasticsearch.action.support.SupportConverters;
import fr.myprysm.vertx.elasticsearch.converter.ConverterUtils;
import io.vertx.core.json.JsonObject;

/**
 * Get request converters.
 */
public interface GetConverters {

    /**
     * Transforms a DataObject request into an Elasticsearch request.
     *
     * @param request the request
     * @return the Elasticsearch request
     */
    static org.elasticsearch.action.get.GetRequest requestToES(GetRequest request) {
        org.elasticsearch.action.get.GetRequest esRequest = new org.elasticsearch.action.get.GetRequest(
                request.getIndex(),
                request.getType(),
                request.getId()
        );

        if (request.getFetchSourceContext() != null) {
            esRequest.fetchSourceContext(SupportConverters.fetchSourceContextToES(request.getFetchSourceContext()));
        }

        if (request.getStoredFields() != null) {
            esRequest.storedFields(request.getStoredFields().toArray(new String[]{}));
        }

        return esRequest
                .routing(request.getRouting())
                .parent(request.getParent())
                .preference(request.getPreference())
                .refresh(request.isRefresh())
                .realtime(request.isRealTime())
                .versionType(request.getVersionType())
                .version(request.getVersion());
    }

    /**
     * Transforms an Elasticsearch response into a DataObject response.
     *
     * @param esResponse the Elasticsearch response
     * @return the response
     */
    static GetResponse responseToDataObject(org.elasticsearch.action.get.GetResponse esResponse) {
        try {
            GetResponse response = new GetResponse()
                    .setExists(esResponse.isExists())
                    .setId(esResponse.getId())
                    .setType(esResponse.getType())
                    .setIndex(esResponse.getIndex())
                    .setVersion(esResponse.getVersion());

            if (!esResponse.isSourceEmpty()) {
                response.setSource(new JsonObject(esResponse.getSource()));
            }

            if (esResponse.getFields() != null && esResponse.getFields().size() > 0) {
                response.setFields(ConverterUtils.convert(esResponse.getFields(), SupportConverters::documentFieldToDataObject));
            }

            return response;
        } catch (NullPointerException exc) {
            return null;
        }
    }
}
