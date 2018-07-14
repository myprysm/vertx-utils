package fr.myprysm.vertx.elasticsearch.action.support;

import fr.myprysm.vertx.elasticsearch.action.BaseRequest;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.elasticsearch.action.DocWriteRequest.OpType;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.common.lucene.uid.Versions;
import org.elasticsearch.index.VersionType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@DataObject(generateConverter = true)
public class DocWriteRequest extends BaseRequest {

    private String index;
    private String type;
    private String id;
    private String routing;
    private String parent;
    private long version = Versions.MATCH_ANY;
    private VersionType versionType = VersionType.INTERNAL;
    private OpType opType = OpType.INDEX;
    private WriteRequest.RefreshPolicy refreshPolicy = WriteRequest.RefreshPolicy.NONE;

    public DocWriteRequest(DocWriteRequest other) {
        super(other);
        index = other.index;
        type = other.type;
        id = other.id;
        routing = other.routing;
        parent = other.parent;
        version = other.version;
        versionType = other.versionType;
        opType = other.opType;
        refreshPolicy = other.refreshPolicy;
    }

    public DocWriteRequest(String index, String type, OpType opType) {
        this(index, type, null, opType);
    }

    public DocWriteRequest(String index, String type, String id, OpType opType) {
        this.index = index;
        this.type = type;
        this.id = id;
        this.opType = opType;
    }

    /**
     * Build a new <code>DocWriteRequest</code> from a <code>JsonObject</code>,
     * calling parent constructor.
     *
     * @param json the <code>JsonObject</code>
     */
    public DocWriteRequest(JsonObject json) {
        super(json);
        DocWriteRequestConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>DocWriteRequest</code> into a <code>JsonObject</code>,
     * calling parent <code>toJson</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        DocWriteRequestConverter.toJson(this, json);
        return json;
    }
}
