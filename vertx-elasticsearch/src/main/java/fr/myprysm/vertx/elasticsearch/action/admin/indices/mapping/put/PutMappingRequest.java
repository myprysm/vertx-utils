package fr.myprysm.vertx.elasticsearch.action.admin.indices.mapping.put;

import fr.myprysm.vertx.elasticsearch.action.BaseRequest;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.elasticsearch.action.support.master.AcknowledgedRequest;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@DataObject(generateConverter = true)
public class PutMappingRequest extends BaseRequest {

    private List<String> indices;
    private String type;
    private JsonObject source;
    private long timeout = AcknowledgedRequest.DEFAULT_ACK_TIMEOUT.millis();

    public PutMappingRequest(String index) {
        safeIndices().add(index);
    }

    public PutMappingRequest(String index, String type) {
        this(index);
        this.type = type;
    }

    public PutMappingRequest(List<String> indices, String type) {
        this.indices = indices;
        this.type = type;
    }

    private List<String> safeIndices() {
        if (indices == null) {
            indices = new ArrayList<>();
        }

        return indices;
    }

    public List<String> getIndices() {
        return safeIndices();
    }

    /**
     * Build a new <code>PutMappingRequest</code> from a <code>JsonObject</code>.
     *
     * @param json the <code>JsonObject</code>
     */
    public PutMappingRequest(JsonObject json) {
        PutMappingRequestConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>PutMappingRequest</code> into a <code>JsonObject</code>,
     * calling parent <code>toJson</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        PutMappingRequestConverter.toJson(this, json);
        return json;
    }
}
