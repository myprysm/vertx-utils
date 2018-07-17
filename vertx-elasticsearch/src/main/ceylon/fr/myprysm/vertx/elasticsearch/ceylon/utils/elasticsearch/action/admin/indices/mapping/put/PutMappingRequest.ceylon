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
import fr.myprysm.vertx.elasticsearch.action.admin.indices.mapping.put {
  PutMappingRequest_=PutMappingRequest
}
import ceylon.collection {
  HashMap
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action {
  BaseRequest
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest */
shared class PutMappingRequest(
  Map<String, String>? headers = null,
  shared {String*}? indices = null,
  shared JsonObject? source = null,
  shared Integer? timeout = null,
  shared String? type = null) extends BaseRequest(
  headers) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    if (exists indices) {
      json.put("indices", JsonArray(indices));
    }
    if (exists source) {
      json.put("source", source);
    }
    if (exists timeout) {
      json.put("timeout", timeout);
    }
    if (exists type) {
      json.put("type", type);
    }
    return json;
  }
}

shared object putMappingRequest {

  shared PutMappingRequest fromJson(JsonObject json) {
    Map<String, String>? headers = if (exists tmp = json.getObjectOrNull("headers")) then HashMap { for(key->val in tmp) if (is String val) key->val } else null;
    {String*}? indices = json.getArrayOrNull("indices")?.strings;
    JsonObject? source = json.getObjectOrNull("source");
    Integer? timeout = json.getIntegerOrNull("timeout");
    String? type = json.getStringOrNull("type");
    return PutMappingRequest {
      headers = headers;
      indices = indices;
      source = source;
      timeout = timeout;
      type = type;
    };
  }

  shared object toCeylon extends Converter<PutMappingRequest_, PutMappingRequest>() {
    shared actual PutMappingRequest convert(PutMappingRequest_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<PutMappingRequest, PutMappingRequest_>() {
    shared actual PutMappingRequest_ convert(PutMappingRequest src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = PutMappingRequest_(json);
      return ret;
    }
  }
  shared JsonObject toJson(PutMappingRequest obj) => obj.toJson();
}
