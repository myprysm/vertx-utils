import fr.myprysm.vertx.elasticsearch.action.index {
  IndexRequest_=IndexRequest
}
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
/* Generated from fr.myprysm.vertx.elasticsearch.action.index.IndexRequest */
shared class IndexRequest(
  Map<String, String>? headers = null,
  String? id = null,
  String? index = null,
  String? opType = null,
  String? parent = null,
  shared String? pipeline = null,
  String? refreshPolicy = null,
  String? routing = null,
  shared JsonObject? source = null,
  shared Integer? timeout = null,
  String? type = null,
  Integer? version = null,
  String? versionType = null) extends DocWriteRequest(
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
    if (exists pipeline) {
      json.put("pipeline", pipeline);
    }
    if (exists source) {
      json.put("source", source);
    }
    if (exists timeout) {
      json.put("timeout", timeout);
    }
    return json;
  }
}

shared object indexRequest {

  shared IndexRequest fromJson(JsonObject json) {
    Map<String, String>? headers = if (exists tmp = json.getObjectOrNull("headers")) then HashMap { for(key->val in tmp) if (is String val) key->val } else null;
    String? id = json.getStringOrNull("id");
    String? index = json.getStringOrNull("index");
    String? opType = json.getStringOrNull("opType");
    String? parent = json.getStringOrNull("parent");
    String? pipeline = json.getStringOrNull("pipeline");
    String? refreshPolicy = json.getStringOrNull("refreshPolicy");
    String? routing = json.getStringOrNull("routing");
    JsonObject? source = json.getObjectOrNull("source");
    Integer? timeout = json.getIntegerOrNull("timeout");
    String? type = json.getStringOrNull("type");
    Integer? version = json.getIntegerOrNull("version");
    String? versionType = json.getStringOrNull("versionType");
    return IndexRequest {
      headers = headers;
      id = id;
      index = index;
      opType = opType;
      parent = parent;
      pipeline = pipeline;
      refreshPolicy = refreshPolicy;
      routing = routing;
      source = source;
      timeout = timeout;
      type = type;
      version = version;
      versionType = versionType;
    };
  }

  shared object toCeylon extends Converter<IndexRequest_, IndexRequest>() {
    shared actual IndexRequest convert(IndexRequest_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<IndexRequest, IndexRequest_>() {
    shared actual IndexRequest_ convert(IndexRequest src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = IndexRequest_(json);
      return ret;
    }
  }
  shared JsonObject toJson(IndexRequest obj) => obj.toJson();
}
