import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.admin.indices.create {
  Alias,
  alias_=alias
}
import fr.myprysm.vertx.elasticsearch.action.admin.indices.create {
  CreateIndexRequest_=CreateIndexRequest
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
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action {
  BaseRequest
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.admin.indices.create.CreateIndexRequest */
shared class CreateIndexRequest(
  shared {Alias*}? aliases = null,
  shared String? cause = null,
  Map<String, String>? headers = null,
  shared String? index = null,
  shared Map<String, JsonObject>? mappings = null,
  shared JsonObject? settings = null,
  shared Integer? timeout = null,
  shared Integer? waitForActiveShards = null) extends BaseRequest(
  headers) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    if (exists aliases) {
      throw Exception("not yet implemented");
    }
    if (exists cause) {
      json.put("cause", cause);
    }
    if (exists index) {
      json.put("index", index);
    }
    if (exists mappings) {
      json.put("mappings", JsonObject(mappings));
    }
    if (exists settings) {
      json.put("settings", settings);
    }
    if (exists timeout) {
      json.put("timeout", timeout);
    }
    if (exists waitForActiveShards) {
      json.put("waitForActiveShards", waitForActiveShards);
    }
    return json;
  }
}

shared object createIndexRequest {

  shared CreateIndexRequest fromJson(JsonObject json) {
    {Alias*}? aliases = null /* fr.myprysm.vertx.elasticsearch.action.admin.indices.create.Alias not handled */;
    String? cause = json.getStringOrNull("cause");
    Map<String, String>? headers = if (exists tmp = json.getObjectOrNull("headers")) then HashMap { for(key->val in tmp) if (is String val) key->val } else null;
    String? index = json.getStringOrNull("index");
    Map<String, JsonObject>? mappings = if (exists tmp = json.getObjectOrNull("mappings")) then HashMap { for(key->val in tmp) if (is JsonObject val) key->val } else null;
    JsonObject? settings = json.getObjectOrNull("settings");
    Integer? timeout = json.getIntegerOrNull("timeout");
    Integer? waitForActiveShards = json.getIntegerOrNull("waitForActiveShards");
    return CreateIndexRequest {
      aliases = aliases;
      cause = cause;
      headers = headers;
      index = index;
      mappings = mappings;
      settings = settings;
      timeout = timeout;
      waitForActiveShards = waitForActiveShards;
    };
  }

  shared object toCeylon extends Converter<CreateIndexRequest_, CreateIndexRequest>() {
    shared actual CreateIndexRequest convert(CreateIndexRequest_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<CreateIndexRequest, CreateIndexRequest_>() {
    shared actual CreateIndexRequest_ convert(CreateIndexRequest src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = CreateIndexRequest_(json);
      return ret;
    }
  }
  shared JsonObject toJson(CreateIndexRequest obj) => obj.toJson();
}
