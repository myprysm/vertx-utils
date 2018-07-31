package fr.myprysm.vertx.elasticsearch.action.admin.cluster;

import fr.myprysm.vertx.elasticsearch.action.support.AcknowledgedResponse;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * ClusterUpdateSettingsResponse.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@DataObject
public class ClusterUpdateSettingsResponse extends AcknowledgedResponse {

    private JsonObject transientSettings;
    private JsonObject persistentSettings;

    /**
     * Build a new {@link ClusterUpdateSettingsResponse} will all attributes.
     *
     * @param acknowledged       acknowledged
     * @param transientSettings  transient settings
     * @param persistentSettings persistent settings
     */
    public ClusterUpdateSettingsResponse(boolean acknowledged, JsonObject transientSettings, JsonObject persistentSettings) {
        super(acknowledged);
        this.transientSettings = transientSettings;
        this.persistentSettings = persistentSettings;
    }

    /**
     * Build a new ClusterUpdateSettingsResponse from another.
     *
     * @param other the other
     */
    public ClusterUpdateSettingsResponse(ClusterUpdateSettingsResponse other) {
        super(other);
        transientSettings = other.transientSettings;
        persistentSettings = other.persistentSettings;
    }

    /**
     * Build a new <code>ClusterUpdateSettingsResponse</code> from a <code>JsonObject</code>,
     * calling parent constructor.
     *
     * @param json the <code>JsonObject</code>
     */
    public ClusterUpdateSettingsResponse(JsonObject json) {
        super(json);
        ClusterUpdateSettingsResponseConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>ClusterUpdateSettingsResponse</code> into a <code>JsonObject</code>,
     * calling parent <code>toJson</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        ClusterUpdateSettingsResponseConverter.toJson(this, json);
        return json;
    }
}
