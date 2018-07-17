package fr.myprysm.vertx.elasticsearch.action.search;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.elasticsearch.rest.RestStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@DataObject(generateConverter = true)
public class ClearScrollResponse {

    private boolean succeeded;
    private int numFreed;
    private RestStatus status;

    public ClearScrollResponse(ClearScrollResponse other) {
        succeeded = other.succeeded;
        numFreed = other.numFreed;
        status = other.status;
    }

    /**
     * Build a new <code>ClearScrollResponse</code> from a <code>JsonObject</code>.
     *
     * @param json the <code>JsonObject</code>
     */
    public ClearScrollResponse(JsonObject json) {
        ClearScrollResponseConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>ClearScrollResponse</code> into a <code>JsonObject</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        ClearScrollResponseConverter.toJson(this, json);
        return json;
    }
}
