package fr.myprysm.vertx.elasticsearch.action.get;

import fr.myprysm.vertx.elasticsearch.action.BaseRequest;
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
@EqualsAndHashCode(callSuper = true)
@DataObject(generateConverter = true)
public class GetRequest extends BaseRequest {
    public static final String DEFAULT_TYPE = "_all";

    private String index;
    private String type = DEFAULT_TYPE;
    private String id;
    private String routing;
    private String parent;
    private String preference;

    private List<String> storedFields;

    private FetchSourceContext fetchSourceContext;

    private boolean refresh = false;
    private boolean realTime = true;
    private VersionType versionType = VersionType.INTERNAL;
    private long version = Versions.MATCH_ANY;

    public GetRequest(JsonObject json) {
        super(json);
        GetRequestConverter.fromJson(json, this);
    }

    public GetRequest(String index, String type, String id) {
        this.index = index;
        this.type = type;
        this.id = id;
    }

    @Override
    public GetRequest addHeader(String headerKey, String headerValue) {
        return (GetRequest) super.addHeader(headerKey, headerValue);
    }

    public JsonObject toJson() {
        JsonObject json = super.toJson();
        GetRequestConverter.toJson(this, json);
        return json;
    }
}
