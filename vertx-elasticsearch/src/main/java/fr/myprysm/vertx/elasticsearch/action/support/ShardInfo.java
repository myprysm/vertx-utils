package fr.myprysm.vertx.elasticsearch.action.support;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@DataObject(generateConverter = true)
public class ShardInfo {
    private int total;
    private int successful;
    private int failed;
    private List<ShardInfoFailure> failures;


    public ShardInfo(ShardInfo other) {
        total = other.total;
        successful = other.successful;
        failures = other.failures;
        failed = other.failed;
    }

    public ShardInfo(JsonObject json) {
        ShardInfoConverter.fromJson(json, this);
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        ShardInfoConverter.toJson(this, json);
        return json;
    }
}
