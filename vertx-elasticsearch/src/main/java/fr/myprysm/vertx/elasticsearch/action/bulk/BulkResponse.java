package fr.myprysm.vertx.elasticsearch.action.bulk;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.elasticsearch.rest.RestStatus;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@DataObject(generateConverter = true)
public class BulkResponse {

    private List<BulkResponseItem> items;
    private boolean errors;
    private long took;
    private RestStatus status;

    /**
     * Build a new <code>BulkResponse</code> from a <code>JsonObject</code>.
     *
     * @param json the <code>JsonObject</code>
     */
    public BulkResponse(JsonObject json) {
        BulkResponseConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>BulkResponse</code> into a <code>JsonObject</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        BulkResponseConverter.toJson(this, json);
        return json;
    }
}
