package fr.myprysm.vertx.elasticsearch.action.bulk;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.rest.RestStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@DataObject(generateConverter = true)
public class BulkResponseItem {

    private DocWriteRequest.OpType opType;
    private RestStatus status;
    private String index;
    private String type;
    private String id;
    private Long version;
    private boolean failed;
    private String failure;

    public BulkResponseItem(BulkResponseItem other) {
        opType = other.opType;
        status = other.status;
        index = other.index;
        type = other.type;
        id = other.id;
        version = other.version;
        failed = other.failed;
        failure = other.failure;
    }

    /**
     * Build a new <code>BulkResponseItem</code> from a <code>JsonObject</code>.
     *
     * @param json the <code>JsonObject</code>
     */
    public BulkResponseItem(JsonObject json) {
        BulkResponseItemConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>BulkResponseItem</code> into a <code>JsonObject</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        BulkResponseItemConverter.toJson(this, json);
        return json;
    }
}
