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
import fr.myprysm.vertx.elasticsearch.action.update {
  UpdateRequest_=UpdateRequest
}
import ceylon.collection {
  HashMap
}
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.support {
  FetchSourceContext,
  fetchSourceContext_=fetchSourceContext,
  Script,
  script_=script,
  DocWriteRequest
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.update.UpdateRequest */
shared class UpdateRequest(
  shared Boolean? detectNoop = null,
  shared JsonObject? doc = null,
  shared Boolean? docAsUpsert = null,
  shared FetchSourceContext? fetchSourceContext = null,
  shared {String*}? fields = null,
  Map<String, String>? headers = null,
  String? id = null,
  String? index = null,
  String? opType = null,
  String? parent = null,
  String? refreshPolicy = null,
  shared Integer? retryOnConflict = null,
  String? routing = null,
  shared Script? script = null,
  shared Boolean? scriptedUpsert = null,
  String? type = null,
  shared JsonObject? upsert = null,
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
    if (exists detectNoop) {
      json.put("detectNoop", detectNoop);
    }
    if (exists doc) {
      json.put("doc", doc);
    }
    if (exists docAsUpsert) {
      json.put("docAsUpsert", docAsUpsert);
    }
    if (exists fetchSourceContext) {
      json.put("fetchSourceContext", fetchSourceContext.toJson());
    }
    if (exists fields) {
      json.put("fields", JsonArray(fields));
    }
    if (exists retryOnConflict) {
      json.put("retryOnConflict", retryOnConflict);
    }
    if (exists script) {
      json.put("script", script.toJson());
    }
    if (exists scriptedUpsert) {
      json.put("scriptedUpsert", scriptedUpsert);
    }
    if (exists upsert) {
      json.put("upsert", upsert);
    }
    if (exists waitForActiveShards) {
      json.put("waitForActiveShards", waitForActiveShards);
    }
    return json;
  }
}

shared object updateRequest {

  shared UpdateRequest fromJson(JsonObject json) {
    Boolean? detectNoop = json.getBooleanOrNull("detectNoop");
    JsonObject? doc = json.getObjectOrNull("doc");
    Boolean? docAsUpsert = json.getBooleanOrNull("docAsUpsert");
    FetchSourceContext? fetchSourceContext = if (exists tmp = json.getObjectOrNull("fetchSourceContext")) then fetchSourceContext_.fromJson(tmp) else null;
    {String*}? fields = json.getArrayOrNull("fields")?.strings;
    Map<String, String>? headers = if (exists tmp = json.getObjectOrNull("headers")) then HashMap { for(key->val in tmp) if (is String val) key->val } else null;
    String? id = json.getStringOrNull("id");
    String? index = json.getStringOrNull("index");
    String? opType = json.getStringOrNull("opType");
    String? parent = json.getStringOrNull("parent");
    String? refreshPolicy = json.getStringOrNull("refreshPolicy");
    Integer? retryOnConflict = json.getIntegerOrNull("retryOnConflict");
    String? routing = json.getStringOrNull("routing");
    Script? script = if (exists tmp = json.getObjectOrNull("script")) then script_.fromJson(tmp) else null;
    Boolean? scriptedUpsert = json.getBooleanOrNull("scriptedUpsert");
    String? type = json.getStringOrNull("type");
    JsonObject? upsert = json.getObjectOrNull("upsert");
    Integer? version = json.getIntegerOrNull("version");
    String? versionType = json.getStringOrNull("versionType");
    Integer? waitForActiveShards = json.getIntegerOrNull("waitForActiveShards");
    return UpdateRequest {
      detectNoop = detectNoop;
      doc = doc;
      docAsUpsert = docAsUpsert;
      fetchSourceContext = fetchSourceContext;
      fields = fields;
      headers = headers;
      id = id;
      index = index;
      opType = opType;
      parent = parent;
      refreshPolicy = refreshPolicy;
      retryOnConflict = retryOnConflict;
      routing = routing;
      script = script;
      scriptedUpsert = scriptedUpsert;
      type = type;
      upsert = upsert;
      version = version;
      versionType = versionType;
      waitForActiveShards = waitForActiveShards;
    };
  }

  shared object toCeylon extends Converter<UpdateRequest_, UpdateRequest>() {
    shared actual UpdateRequest convert(UpdateRequest_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<UpdateRequest, UpdateRequest_>() {
    shared actual UpdateRequest_ convert(UpdateRequest src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = UpdateRequest_(json);
      return ret;
    }
  }
  shared JsonObject toJson(UpdateRequest obj) => obj.toJson();
}
