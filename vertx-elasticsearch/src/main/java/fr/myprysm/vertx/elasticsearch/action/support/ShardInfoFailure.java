package fr.myprysm.vertx.elasticsearch.action.support;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.elasticsearch.rest.RestStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@DataObject(generateConverter = true)
public class ShardInfoFailure extends Failure {

    private String shardId;
    private String nodeId;
    private RestStatus status;
    private boolean primary;

    public ShardInfoFailure(String shardId, String nodeId, RestStatus status, boolean primary, String cause) {
        super(cause);
        this.shardId = shardId;
        this.nodeId = nodeId;
        this.status = status;
        this.primary = primary;
    }

    public ShardInfoFailure(JsonObject json) {
        super(json);
        ShardInfoFailureConverter.fromJson(json, this);
    }

    public JsonObject toJson() {
        JsonObject json = super.toJson();
        ShardInfoFailureConverter.toJson(this, json);
        return json;
    }
}
