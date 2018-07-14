package fr.myprysm.vertx.elasticsearch.action.get;


import fr.myprysm.vertx.elasticsearch.action.support.FetchSourceContext;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.elasticsearch.common.lucene.uid.Versions;
import org.elasticsearch.index.VersionType;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode
@DataObject(generateConverter = true)
public class GetRequestItem {

    private String index;
    private String type;
    private String id;
    private String routing;
    private String parent;
    private List<String> storedFields;
    private long version = Versions.MATCH_ANY;
    private VersionType versionType = VersionType.INTERNAL;
    private FetchSourceContext fetchSourceContext;

    public GetRequestItem(String index, String type, String id) {
        this.index = index;
        this.type = type;
        this.id = id;
    }

    public GetRequestItem(JsonObject json) {
        GetRequestItemConverter.fromJson(json, this);
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        GetRequestItemConverter.toJson(this, json);
        return json;
    }
}
