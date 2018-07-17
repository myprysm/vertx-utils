package fr.myprysm.vertx.elasticsearch.action.admin.refresh;

import fr.myprysm.vertx.elasticsearch.action.support.ShardFailure;
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
public class RefreshResponse {

    private int totalShards;
    private int successfulShards;
    private int failedShards;
    private List<ShardFailure> shardFailures;
    private RestStatus status;

    public RefreshResponse(RefreshResponse other) {
        totalShards = other.totalShards;
        successfulShards = other.successfulShards;
        failedShards = other.failedShards;
        shardFailures = other.shardFailures;
        status = other.status;
    }

    /**
     * Build a new <code>RefreshResponse</code> from a <code>JsonObject</code>.
     *
     * @param json the <code>JsonObject</code>
     */
    public RefreshResponse(JsonObject json) {
        RefreshResponseConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>RefreshResponse</code> into a <code>JsonObject</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        RefreshResponseConverter.toJson(this, json);
        return json;
    }
}
