package fr.myprysm.vertx.elasticsearch.action.admin.indices.mapping.put;

import fr.myprysm.vertx.elasticsearch.action.support.AcknowledgedResponse;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.NoArgsConstructor;

/**
 * PutMappingResponse.
 */
@NoArgsConstructor
@DataObject
public class PutMappingResponse extends AcknowledgedResponse {

    /**
     * Build a new response with the status.
     *
     * @param acknowledged the status
     */
    public PutMappingResponse(boolean acknowledged) {
        super(acknowledged);
    }

    /**
     * Build a new response from another.
     *
     * @param other the other
     */
    public PutMappingResponse(PutMappingResponse other) {
        super(other);
    }

    /**
     * Build a new <code>PutMappingResponse</code> from a <code>JsonObject</code>,
     * calling parent constructor.
     *
     * @param json the <code>JsonObject</code>
     */
    public PutMappingResponse(JsonObject json) {
        super(json);
    }
}