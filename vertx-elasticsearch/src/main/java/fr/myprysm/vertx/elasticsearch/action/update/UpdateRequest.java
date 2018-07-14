package fr.myprysm.vertx.elasticsearch.action.update;

import fr.myprysm.vertx.elasticsearch.action.support.DocWriteRequest;
import fr.myprysm.vertx.elasticsearch.action.support.FetchSourceContext;
import fr.myprysm.vertx.elasticsearch.action.support.Script;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.index.VersionType;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@DataObject(generateConverter = true)
public class UpdateRequest extends DocWriteRequest {
    private JsonObject doc;
    private JsonObject upsert;

    private Script script;

    private List<String> fields;
    private FetchSourceContext fetchSourceContext;

    private int retryOnConflict = 0;

    private Integer waitForActiveShards;

    private boolean docAsUpsert = false;
    private boolean scriptedUpsert = false;
    private boolean detectNoop = true;

    public UpdateRequest(String index, String type, String id) {
        super(index, type, id, org.elasticsearch.action.DocWriteRequest.OpType.UPDATE);
    }

    public UpdateRequest(UpdateRequest other) {
        super(other);
        doc = other.doc;
        upsert = other.upsert;
        script = other.script;
        fields = other.fields;
        fetchSourceContext = other.fetchSourceContext;
        retryOnConflict = other.retryOnConflict;
        waitForActiveShards = other.waitForActiveShards;
        docAsUpsert = other.docAsUpsert;
        scriptedUpsert = other.scriptedUpsert;
        detectNoop = other.detectNoop;
    }

    public UpdateRequest(JsonObject json) {
        super(json);
        UpdateRequestConverter.fromJson(json, this);
    }

    public JsonObject toJson() {
        JsonObject json = super.toJson();
        UpdateRequestConverter.toJson(this, json);
        return json;
    }

    @Override
    public UpdateRequest setIndex(String index) {
        return (UpdateRequest) super.setIndex(index);
    }

    @Override
    public UpdateRequest setType(String type) {
        return (UpdateRequest) super.setType(type);
    }

    @Override
    public UpdateRequest setId(String id) {
        return (UpdateRequest) super.setId(id);
    }

    @Override
    public UpdateRequest setRouting(String routing) {
        return (UpdateRequest) super.setRouting(routing);
    }

    @Override
    public UpdateRequest setParent(String parent) {
        return (UpdateRequest) super.setParent(parent);
    }

    @Override
    public UpdateRequest setVersion(long version) {
        return (UpdateRequest) super.setVersion(version);
    }

    @Override
    public UpdateRequest setVersionType(VersionType versionType) {
        return (UpdateRequest) super.setVersionType(versionType);
    }

    @Override
    public UpdateRequest setOpType(org.elasticsearch.action.DocWriteRequest.OpType opType) {
        return (UpdateRequest) super.setOpType(opType);
    }

    @Override
    public UpdateRequest setRefreshPolicy(WriteRequest.RefreshPolicy refreshPolicy) {
        return (UpdateRequest) super.setRefreshPolicy(refreshPolicy);
    }
}
