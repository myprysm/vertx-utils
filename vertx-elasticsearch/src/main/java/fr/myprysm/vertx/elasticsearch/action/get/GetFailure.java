package fr.myprysm.vertx.elasticsearch.action.get;

import fr.myprysm.vertx.elasticsearch.action.support.Failure;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@DataObject(generateConverter = true)
public class GetFailure extends Failure {

    private String index;
    private String type;
    private String id;

    public GetFailure(String index, String type, String id, String cause) {
        super(cause);
        this.index = index;
        this.type = type;
        this.id = id;
    }

    public GetFailure(JsonObject json) {
        super(json);
        GetFailureConverter.fromJson(json, this);
    }

    public JsonObject toJson() {
        JsonObject json = super.toJson();
        GetFailureConverter.toJson(this, json);
        return json;
    }
}
