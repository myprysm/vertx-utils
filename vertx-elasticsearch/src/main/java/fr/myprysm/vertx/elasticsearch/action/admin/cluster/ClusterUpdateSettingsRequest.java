package fr.myprysm.vertx.elasticsearch.action.admin.cluster;

import fr.myprysm.vertx.elasticsearch.action.BaseRequest;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * ClusterUpdateSettingsRequest DataObject.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@DataObject(generateConverter = true)
public class ClusterUpdateSettingsRequest extends BaseRequest {

    /**
     * Transient settings.
     */
    private JsonObject transientSettings = new JsonObject();

    /**
     * Persistent settings.
     */
    private JsonObject persistentSettings = new JsonObject();

    /**
     * Build a new ClusterUpdateSettingsRequest from another.
     *
     * @param other the other
     */
    public ClusterUpdateSettingsRequest(ClusterUpdateSettingsRequest other) {
        super(other);
        transientSettings = other.transientSettings;
        persistentSettings = other.persistentSettings;
    }

    /**
     * Build a new <code>ClusterUpdateSettingsRequest</code> from a <code>JsonObject</code>,
     * calling parent constructor.
     *
     * @param json the <code>JsonObject</code>
     */
    public ClusterUpdateSettingsRequest(JsonObject json) {
        super(json);
        ClusterUpdateSettingsRequestConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>ClusterUpdateSettingsRequest</code> into a <code>JsonObject</code>,
     * calling parent <code>toJson</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        ClusterUpdateSettingsRequestConverter.toJson(this, json);
        return json;
    }
}
