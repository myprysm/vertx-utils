package fr.myprysm.vertx.elasticsearch.action.update;

import fr.myprysm.vertx.elasticsearch.action.get.GetResponse;
import fr.myprysm.vertx.elasticsearch.action.support.DocWriteResponse;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.elasticsearch.rest.RestStatus;

/**
 * Update response from Elasticsearch.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@DataObject(generateConverter = true)
public class UpdateResponse extends DocWriteResponse {

    /**
     * The result.
     */
    private GetResponse getResult;

    /**
     * The response status.
     */
    private RestStatus status;

    /**
     * Build a new <code>UpdateResponse</code> from another.
     *
     * @param other the response
     */
    public UpdateResponse(UpdateResponse other) {
        super(other);
        getResult = other.getResult;
        status = other.status;
    }

    /**
     * Build a new <code>UpdateResponse</code> from a <code>DocWriteResponse</code>.
     *
     * @param other the response
     */
    public UpdateResponse(DocWriteResponse other) {
        super(other);
    }

    /**
     * Build a new <code>UpdateResponse</code> from a <code>JsonObject</code>,
     * calling parent constructor.
     *
     * @param json the <code>JsonObject</code>
     */
    public UpdateResponse(JsonObject json) {
        super(json);
        UpdateResponseConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>UpdateResponse</code> into a <code>JsonObject</code>,
     * calling parent <code>toJson</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        UpdateResponseConverter.toJson(this, json);
        return json;
    }
}
