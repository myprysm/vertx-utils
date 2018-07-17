package fr.myprysm.vertx.elasticsearch.action.admin.indices.create;

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
public class Alias {

    private String name;
    private String filter;
    private String indexRouting;
    private String searchRouting;

    /**
     * Build a new <code>Alias</code> from a <code>JsonObject</code>.
     *
     * @param json the <code>JsonObject</code>
     */
    public Alias(JsonObject json) {
        AliasConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>Alias</code> into a <code>JsonObject</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        AliasConverter.toJson(this, json);
        return json;
    }
}
