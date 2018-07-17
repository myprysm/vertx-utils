package fr.myprysm.vertx.elasticsearch.action.admin.refresh;

import fr.myprysm.vertx.elasticsearch.action.BaseRequest;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.elasticsearch.action.support.IndicesOptions;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@DataObject(generateConverter = true)
public class RefreshRequest extends BaseRequest {

    /**
     * The indices
     */
    private List<String> indices;

    /**
     * IndicesOptions
     */
    private IndicesOptions indicesOptions;

    public RefreshRequest(RefreshRequest other) {
        super(other);
        indices = other.indices;
    }

    public RefreshRequest(String index) {
        addIndex(index);
    }

    /**
     * Build a new <code>RefreshRequest</code> from a <code>JsonObject</code>,
     * calling parent constructor.
     *
     * @param json the <code>JsonObject</code>
     */
    public RefreshRequest(JsonObject json) {
        super(json);
        RefreshRequestConverter.fromJson(json, this);
    }

    private List<String> safeIndices() {
        if (indices == null) {
            indices = new ArrayList<>();
        }
        return indices;
    }

    public RefreshRequest addIndex(String index) {
        safeIndices().add(index);
        return this;
    }

    /**
     * Transforms the <code>RefreshRequest</code> into a <code>JsonObject</code>,
     * calling parent <code>toJson</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        RefreshRequestConverter.toJson(this, json);
        return json;
    }
}
