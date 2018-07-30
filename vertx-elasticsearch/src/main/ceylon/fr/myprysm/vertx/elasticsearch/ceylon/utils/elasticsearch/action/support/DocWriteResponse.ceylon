import ceylon.json {
  JsonObject=Object,
  JsonArray=Array,
  parse
}
import io.vertx.lang.ceylon {
  BaseDataObject,
  Converter,
  ToJava
}
import fr.myprysm.vertx.elasticsearch.action.support {
  DocWriteResponse_=DocWriteResponse
}
import ceylon.collection {
  HashMap
}
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.support {
  ShardId,
  shardId_=shardId,
  ShardInfo,
  shardInfo_=shardInfo,
  ReplicationResponse
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.support.DocWriteResponse */
shared class DocWriteResponse(
  shared Boolean? forcedRefresh = null,
  shared String? id = null,
  shared String? index = null,
  shared Integer? primaryTerm = null,
  shared String? result = null,
  shared Integer? seqNo = null,
  shared ShardId? shardId = null,
  ShardInfo? shardInfo = null,
  shared String? type = null,
  shared Integer? version = null) extends ReplicationResponse(
  shardInfo) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    if (exists forcedRefresh) {
      json.put("forcedRefresh", forcedRefresh);
    }
    if (exists id) {
      json.put("id", id);
    }
    if (exists index) {
      json.put("index", index);
    }
    if (exists primaryTerm) {
      json.put("primaryTerm", primaryTerm);
    }
    if (exists result) {
      json.put("result", result);
    }
    if (exists seqNo) {
      json.put("seqNo", seqNo);
    }
    if (exists shardId) {
      json.put("shardId", shardId.toJson());
    }
    if (exists type) {
      json.put("type", type);
    }
    if (exists version) {
      json.put("version", version);
    }
    return json;
  }
}

shared object docWriteResponse {

  shared DocWriteResponse fromJson(JsonObject json) {
    Boolean? forcedRefresh = json.getBooleanOrNull("forcedRefresh");
    String? id = json.getStringOrNull("id");
    String? index = json.getStringOrNull("index");
    Integer? primaryTerm = json.getIntegerOrNull("primaryTerm");
    String? result = json.getStringOrNull("result");
    Integer? seqNo = json.getIntegerOrNull("seqNo");
    ShardId? shardId = if (exists tmp = json.getObjectOrNull("shardId")) then shardId_.fromJson(tmp) else null;
    ShardInfo? shardInfo = if (exists tmp = json.getObjectOrNull("shardInfo")) then shardInfo_.fromJson(tmp) else null;
    String? type = json.getStringOrNull("type");
    Integer? version = json.getIntegerOrNull("version");
    return DocWriteResponse {
      forcedRefresh = forcedRefresh;
      id = id;
      index = index;
      primaryTerm = primaryTerm;
      result = result;
      seqNo = seqNo;
      shardId = shardId;
      shardInfo = shardInfo;
      type = type;
      version = version;
    };
  }

  shared object toCeylon extends Converter<DocWriteResponse_, DocWriteResponse>() {
    shared actual DocWriteResponse convert(DocWriteResponse_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<DocWriteResponse, DocWriteResponse_>() {
    shared actual DocWriteResponse_ convert(DocWriteResponse src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = DocWriteResponse_(json);
      return ret;
    }
  }
  shared JsonObject toJson(DocWriteResponse obj) => obj.toJson();
}
