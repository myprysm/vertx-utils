package fr.myprysm.vertx.elasticsearch.action.main;

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
public class MainResponse {

    private String nodeName;
    private String version;
    private String clusterName;
    private String clusterUuid;
    private String build;
    private boolean available;

    public MainResponse(JsonObject json) {
        MainResponseConverter.fromJson(json, this);
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        MainResponseConverter.toJson(this, json);
        return json;
    }

}
