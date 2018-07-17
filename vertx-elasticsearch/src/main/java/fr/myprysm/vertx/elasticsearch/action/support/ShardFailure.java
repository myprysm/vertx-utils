package fr.myprysm.vertx.elasticsearch.action.support;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.elasticsearch.rest.RestStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@DataObject(generateConverter = true)
public class ShardFailure extends Failure {

    private RestStatus status;
    private String index;
    private int shardId;

    public ShardFailure(RestStatus status, String index, int shardId, String cause) {
        super(cause);
        this.status = status;
        this.index = index;
        this.shardId = shardId;
    }

    /**
     * Build a new <code>ShardFailure</code> from a <code>JsonObject</code>,
     * calling parent constructor.
     *
     * @param json the <code>JsonObject</code>
     */
    public ShardFailure(JsonObject json) {
        super(json);
        ShardFailureConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>ShardFailure</code> into a <code>JsonObject</code>,
     * calling parent <code>toJson</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        ShardFailureConverter.toJson(this, json);
        return json;
    }
}
