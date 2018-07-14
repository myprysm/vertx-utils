package fr.myprysm.vertx.elasticsearch.action.get;

import fr.myprysm.vertx.elasticsearch.action.support.DocumentField;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@DataObject(generateConverter = true)
public class GetResponse {

    private boolean exists;
    private String index;
    private String type;
    private String id;
    private long version;
    private JsonObject source;
    private Map<String, DocumentField> fields;

    public GetResponse(GetResponse other) {
        exists = other.exists;
        index = other.index;
        type = other.type;
        id = other.id;
        version = other.version;
        source = other.source;
        fields = other.fields;
    }

    public GetResponse(JsonObject json) {
        GetResponseConverter.fromJson(json, this);
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        GetResponseConverter.toJson(this, json);
        return json;
    }

    public DocumentField getField(String name) {
        if (fields != null) {
            return fields.get(name);
        }
        return null;
    }
}
