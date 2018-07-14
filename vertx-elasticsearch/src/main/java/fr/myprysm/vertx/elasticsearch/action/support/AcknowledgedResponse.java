package fr.myprysm.vertx.elasticsearch.action.support;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Response that indicates whether it has been acknowledged.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@DataObject(generateConverter = true)
public class AcknowledgedResponse {

    /**
     * Status.
     */
    private boolean acknowledged;

    /**
     * Build a new AcknowledgedResponse from another.
     *
     * @param other the other
     */
    public AcknowledgedResponse(AcknowledgedResponse other) {
        acknowledged = other.acknowledged;
    }

    /**
     * Build a new <code>AcknowledgedResponse</code> from a <code>JsonObject</code>.
     *
     * @param json the <code>JsonObject</code>
     */
    public AcknowledgedResponse(JsonObject json) {
        AcknowledgedResponseConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>AcknowledgedResponse</code> into a <code>JsonObject</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        AcknowledgedResponseConverter.toJson(this, json);
        return json;
    }
}
