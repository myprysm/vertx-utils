package fr.myprysm.vertx.elasticsearch.action.bulk;

import fr.myprysm.vertx.elasticsearch.action.BaseRequest;
import fr.myprysm.vertx.elasticsearch.action.support.DocWriteRequest;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.elasticsearch.action.bulk.BulkShardRequest;
import org.elasticsearch.action.support.WriteRequest;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@DataObject(generateConverter = true)
public class BulkRequest extends BaseRequest {

    private List<DocWriteRequest> requests;
    private WriteRequest.RefreshPolicy refreshPolicy;
    private long timeout = BulkShardRequest.DEFAULT_TIMEOUT.millis();
    private Integer waitForActiveShards;


    public BulkRequest add(DocWriteRequest request) {
        safeRequests().add(request);
        return this;
    }

    private List<DocWriteRequest> safeRequests() {
        if (requests == null) {
            requests = new ArrayList<>();
        }

        return requests;
    }

    public List<DocWriteRequest> getRequests() {
        return safeRequests();
    }

    /**
     * Build a new <code>BulkRequest</code> from a <code>JsonObject</code>,
     * calling parent constructor.
     *
     * @param json the <code>JsonObject</code>
     */
    public BulkRequest(JsonObject json) {
        super(json);
        BulkRequestConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>BulkRequest</code> into a <code>JsonObject</code>,
     * calling parent <code>toJson</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        BulkRequestConverter.toJson(this, json);
        return json;
    }

}
