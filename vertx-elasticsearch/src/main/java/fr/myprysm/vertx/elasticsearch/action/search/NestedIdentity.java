package fr.myprysm.vertx.elasticsearch.action.search;

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
public class NestedIdentity {
    private String field;
    private int offset;
    private NestedIdentity child;

    public NestedIdentity(NestedIdentity other) {
        field = other.field;
        offset = other.offset;
        child = other.child;
    }

    /**
     * Build a new <code>NestedIdentity</code> from a <code>JsonObject</code>.
     *
     * @param json the <code>JsonObject</code>
     */
    public NestedIdentity(JsonObject json) {
        NestedIdentityConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>NestedIdentity</code> into a <code>JsonObject</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        NestedIdentityConverter.toJson(this, json);
        return json;
    }
}
