package fr.myprysm.vertx.elasticsearch.action.support;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@DataObject(generateConverter = true)
public class ReplicationResponse {

    private ShardInfo shardInfo;

    public ReplicationResponse(ReplicationResponse other) {
        shardInfo = other.shardInfo;
    }

    public ReplicationResponse(JsonObject json) {
        ReplicationResponseConverter.fromJson(json, this);
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        ReplicationResponseConverter.toJson(this, json);
        return json;
    }

}
