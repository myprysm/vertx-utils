package fr.myprysm.vertx.elasticsearch.action.delete;

import fr.myprysm.vertx.elasticsearch.action.support.DocWriteRequest;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.support.replication.ReplicationRequest;
import org.elasticsearch.index.VersionType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@DataObject(generateConverter = true)
public class DeleteRequest extends DocWriteRequest {
    private Integer waitForActiveShards;
    private long timeout = ReplicationRequest.DEFAULT_TIMEOUT.millis();

    /**
     * Build a request from another.
     *
     * @param other the other
     */
    public DeleteRequest(DeleteRequest other) {
        super(other);
        waitForActiveShards = other.waitForActiveShards;
        timeout = other.timeout;
    }

    /**
     * Build a request with the provided index, type and id.
     *
     * @param index the index
     * @param type  the type
     * @param id    the id
     */
    public DeleteRequest(String index, String type, String id) {
        super(index, type, id, org.elasticsearch.action.DocWriteRequest.OpType.DELETE);
    }

    /**
     * Build a new <code>DeleteRequest</code> from a <code>JsonObject</code>,
     * calling parent constructor.
     *
     * @param json the <code>JsonObject</code>
     */
    public DeleteRequest(JsonObject json) {
        super(json);
        DeleteRequestConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>DeleteRequest</code> into a <code>JsonObject</code>,
     * calling parent <code>toJson</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        DeleteRequestConverter.toJson(this, json);
        return json;
    }

    @Override
    public DeleteRequest setIndex(String index) {
        return (DeleteRequest) super.setIndex(index);
    }

    @Override
    public DeleteRequest setType(String type) {
        return (DeleteRequest) super.setType(type);
    }

    @Override
    public DeleteRequest setId(String id) {
        return (DeleteRequest) super.setId(id);
    }

    @Override
    public DeleteRequest setRouting(String routing) {
        return (DeleteRequest) super.setRouting(routing);
    }

    @Override
    public DeleteRequest setParent(String parent) {
        return (DeleteRequest) super.setParent(parent);
    }

    @Override
    public DeleteRequest setVersion(long version) {
        return (DeleteRequest) super.setVersion(version);
    }

    @Override
    public DeleteRequest setVersionType(VersionType versionType) {
        return (DeleteRequest) super.setVersionType(versionType);
    }

    @Override
    public DeleteRequest setOpType(org.elasticsearch.action.DocWriteRequest.OpType opType) {
        return (DeleteRequest) super.setOpType(opType);
    }

    @Override
    public DeleteRequest setRefreshPolicy(WriteRequest.RefreshPolicy refreshPolicy) {
        return (DeleteRequest) super.setRefreshPolicy(refreshPolicy);
    }
}
