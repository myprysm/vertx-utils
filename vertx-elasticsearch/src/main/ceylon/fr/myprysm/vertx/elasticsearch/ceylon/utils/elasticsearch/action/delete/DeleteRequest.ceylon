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
import fr.myprysm.vertx.elasticsearch.action.delete {
  DeleteRequest_=DeleteRequest
}
import ceylon.collection {
  HashMap
}
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.support {
  DocWriteRequest
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.delete.DeleteRequest */
shared class DeleteRequest(
  Map<String, String>? headers = null,
  String? id = null,
  String? index = null,
  String? opType = null,
  String? parent = null,
  String? refreshPolicy = null,
  String? routing = null,
  shared Integer? timeout = null,
  String? type = null,
  Integer? version = null,
  String? versionType = null,
  shared Integer? waitForActiveShards = null) extends DocWriteRequest(
  headers,
  id,
  index,
  opType,
  parent,
  refreshPolicy,
  routing,
  type,
  version,
  versionType) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    if (exists timeout) {
      json.put("timeout", timeout);
    }
    if (exists waitForActiveShards) {
      json.put("waitForActiveShards", waitForActiveShards);
    }
    return json;
  }
}

shared object deleteRequest {

  shared DeleteRequest fromJson(JsonObject json) {
    Map<String, String>? headers = if (exists tmp = json.getObjectOrNull("headers")) then HashMap { for(key->val in tmp) if (is String val) key->val } else null;
    String? id = json.getStringOrNull("id");
    String? index = json.getStringOrNull("index");
    String? opType = json.getStringOrNull("opType");
    String? parent = json.getStringOrNull("parent");
    String? refreshPolicy = json.getStringOrNull("refreshPolicy");
    String? routing = json.getStringOrNull("routing");
    Integer? timeout = json.getIntegerOrNull("timeout");
    String? type = json.getStringOrNull("type");
    Integer? version = json.getIntegerOrNull("version");
    String? versionType = json.getStringOrNull("versionType");
    Integer? waitForActiveShards = json.getIntegerOrNull("waitForActiveShards");
    return DeleteRequest {
      headers = headers;
      id = id;
      index = index;
      opType = opType;
      parent = parent;
      refreshPolicy = refreshPolicy;
      routing = routing;
      timeout = timeout;
      type = type;
      version = version;
      versionType = versionType;
      waitForActiveShards = waitForActiveShards;
    };
  }

  shared object toCeylon extends Converter<DeleteRequest_, DeleteRequest>() {
    shared actual DeleteRequest convert(DeleteRequest_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<DeleteRequest, DeleteRequest_>() {
    shared actual DeleteRequest_ convert(DeleteRequest src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = DeleteRequest_(json);
      return ret;
    }
  }
  shared JsonObject toJson(DeleteRequest obj) => obj.toJson();
}
