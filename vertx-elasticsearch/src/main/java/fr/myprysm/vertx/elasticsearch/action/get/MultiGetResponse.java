package fr.myprysm.vertx.elasticsearch.action.get;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode
@DataObject(generateConverter = true)
public class MultiGetResponse {

    private List<GetResponse> responses;
    private List<GetFailure> failures;


    public MultiGetResponse(JsonObject json) {
        MultiGetResponseConverter.fromJson(json, this);
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        MultiGetResponseConverter.toJson(this, json);
        return json;
    }
}
