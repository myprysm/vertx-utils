package fr.myprysm.vertx.elasticsearch.action.support;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.elasticsearch.script.ScriptType;

import java.util.HashMap;
import java.util.Map;

import static org.elasticsearch.script.Script.DEFAULT_SCRIPT_LANG;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@DataObject(generateConverter = true)
public class Script {

    private ScriptType type;
    private String idOrCode;
    private String lang = DEFAULT_SCRIPT_LANG;
    private Map<String, String> options = new HashMap<>();
    private JsonObject params = new JsonObject();

    public Script(Script other) {
        type = other.type;
        idOrCode = other.idOrCode;
        lang = other.lang;
        options = other.options;
        params = other.params;
    }

    public Script(JsonObject json) {
        ScriptConverter.fromJson(json, this);
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        ScriptConverter.toJson(this, json);
        return json;
    }

    public Script fromId(String id) {
        return new Script()
                .setType(ScriptType.STORED)
                .setIdOrCode(id);
    }

    public Script fromSource(String source) {
        return new Script()
                .setType(ScriptType.INLINE)
                .setIdOrCode(source);
    }
}
