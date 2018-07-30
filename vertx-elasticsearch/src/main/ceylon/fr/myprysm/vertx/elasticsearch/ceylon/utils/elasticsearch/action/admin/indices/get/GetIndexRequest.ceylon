import fr.myprysm.vertx.elasticsearch.action.admin.indices.get {
  GetIndexRequest_=GetIndexRequest
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
/* Generated from fr.myprysm.vertx.elasticsearch.action.admin.indices.get.GetIndexRequest */
shared class GetIndexRequest(
  shared {String*}? features = null,
  Map<String, String>? headers = null,
  shared Boolean? includeDefaults = null,
  shared {String*}? indices = null,
  shared Boolean? local = null,
  shared Integer? masterNodeTimeout = null,
  shared {String*}? types = null) extends BaseRequest(
  headers) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    if (exists features) {
      json.put("features", JsonArray(features));
    }
    if (exists includeDefaults) {
      json.put("includeDefaults", includeDefaults);
    }
    if (exists indices) {
      json.put("indices", JsonArray(indices));
    }
    if (exists local) {
      json.put("local", local);
    }
    if (exists masterNodeTimeout) {
      json.put("masterNodeTimeout", masterNodeTimeout);
    }
    if (exists types) {
      json.put("types", JsonArray(types));
    }
    return json;
  }
}

shared object getIndexRequest {

  shared GetIndexRequest fromJson(JsonObject json) {
    {String*}? features = json.getArrayOrNull("features")?.strings;
    Map<String, String>? headers = if (exists tmp = json.getObjectOrNull("headers")) then HashMap { for(key->val in tmp) if (is String val) key->val } else null;
    Boolean? includeDefaults = json.getBooleanOrNull("includeDefaults");
    {String*}? indices = json.getArrayOrNull("indices")?.strings;
    Boolean? local = json.getBooleanOrNull("local");
    Integer? masterNodeTimeout = json.getIntegerOrNull("masterNodeTimeout");
    {String*}? types = json.getArrayOrNull("types")?.strings;
    return GetIndexRequest {
      features = features;
      headers = headers;
      includeDefaults = includeDefaults;
      indices = indices;
      local = local;
      masterNodeTimeout = masterNodeTimeout;
      types = types;
    };
  }

  shared object toCeylon extends Converter<GetIndexRequest_, GetIndexRequest>() {
    shared actual GetIndexRequest convert(GetIndexRequest_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<GetIndexRequest, GetIndexRequest_>() {
    shared actual GetIndexRequest_ convert(GetIndexRequest src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = GetIndexRequest_(json);
      return ret;
    }
  }
  shared JsonObject toJson(GetIndexRequest obj) => obj.toJson();
}
