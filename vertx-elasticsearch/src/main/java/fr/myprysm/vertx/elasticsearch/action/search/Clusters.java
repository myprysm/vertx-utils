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
public class Clusters {

    private int total;
    private int successful;
    private int skipped;

    public Clusters(Clusters other) {
        total = other.total;
        successful = other.successful;
        skipped = other.skipped;
    }

    /**
     * Build a new <code>Clusters</code> from a <code>JsonObject</code>.
     *
     * @param json the <code>JsonObject</code>
     */
    public Clusters(JsonObject json) {
        ClustersConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>Clusters</code> into a <code>JsonObject</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        ClustersConverter.toJson(this, json);
        return json;
    }

}
