package fr.myprysm.vertx.elasticsearch.action.index;

import fr.myprysm.vertx.elasticsearch.action.support.DocWriteRequest;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.support.replication.ReplicationRequest;
import org.elasticsearch.index.VersionType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@DataObject(generateConverter = true)
public class IndexRequest extends DocWriteRequest {

    private JsonObject source;
    private String pipeline;
    private long timeout = ReplicationRequest.DEFAULT_TIMEOUT.millis();

    public IndexRequest(IndexRequest other) {
        super(other);
        source = other.source;
        pipeline = other.pipeline;
        timeout = other.timeout;
    }

    public IndexRequest(String index, String type) {
        this(index, type, null);
    }

    public IndexRequest(String index, String type, String id) {
        super(index, type, id, org.elasticsearch.action.DocWriteRequest.OpType.INDEX);
    }

    public IndexRequest(JsonObject json) {
        super(json);
        IndexRequestConverter.fromJson(json, this);
    }


    public JsonObject toJson() {
        JsonObject json = super.toJson();
        IndexRequestConverter.toJson(this, json);
        return json;
    }

    @Override
    public IndexRequest setIndex(String index) {
        return (IndexRequest) super.setIndex(index);
    }

    @Override
    public IndexRequest setType(String type) {
        return (IndexRequest) super.setType(type);
    }

    @Override
    public IndexRequest setId(String id) {
        return (IndexRequest) super.setId(id);
    }

    @Override
    public IndexRequest setRouting(String routing) {
        return (IndexRequest) super.setRouting(routing);
    }

    @Override
    public IndexRequest setParent(String parent) {
        return (IndexRequest) super.setParent(parent);
    }

    @Override
    public IndexRequest setVersion(long version) {
        return (IndexRequest) super.setVersion(version);
    }

    @Override
    public IndexRequest setVersionType(VersionType versionType) {
        return (IndexRequest) super.setVersionType(versionType);
    }

    @Override
    public IndexRequest setOpType(org.elasticsearch.action.DocWriteRequest.OpType opType) {
        return (IndexRequest) super.setOpType(opType);
    }

    @Override
    public IndexRequest setRefreshPolicy(WriteRequest.RefreshPolicy refreshPolicy) {
        return (IndexRequest) super.setRefreshPolicy(refreshPolicy);
    }
}
