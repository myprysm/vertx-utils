import fr.myprysm.vertx.elasticsearch.action.get {
  GetRequest_=GetRequest
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
  FetchSourceContext,
  fetchSourceContext_=fetchSourceContext
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action {
  BaseRequest
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.get.GetRequest */
shared class GetRequest(
  shared FetchSourceContext? fetchSourceContext = null,
  Map<String, String>? headers = null,
  shared String? id = null,
  shared String? index = null,
  shared String? parent = null,
  shared String? preference = null,
  shared Boolean? realTime = null,
  shared Boolean? refresh = null,
  shared String? routing = null,
  shared {String*}? storedFields = null,
  shared String? type = null,
  shared Integer? version = null,
  shared String? versionType = null) extends BaseRequest(
  headers) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    if (exists fetchSourceContext) {
      json.put("fetchSourceContext", fetchSourceContext.toJson());
    }
    if (exists id) {
      json.put("id", id);
    }
    if (exists index) {
      json.put("index", index);
    }
    if (exists parent) {
      json.put("parent", parent);
    }
    if (exists preference) {
      json.put("preference", preference);
    }
    if (exists realTime) {
      json.put("realTime", realTime);
    }
    if (exists refresh) {
      json.put("refresh", refresh);
    }
    if (exists routing) {
      json.put("routing", routing);
    }
    if (exists storedFields) {
      json.put("storedFields", JsonArray(storedFields));
    }
    if (exists type) {
      json.put("type", type);
    }
    if (exists version) {
      json.put("version", version);
    }
    if (exists versionType) {
      json.put("versionType", versionType);
    }
    return json;
  }
}

shared object getRequest {

  shared GetRequest fromJson(JsonObject json) {
    FetchSourceContext? fetchSourceContext = if (exists tmp = json.getObjectOrNull("fetchSourceContext")) then fetchSourceContext_.fromJson(tmp) else null;
    Map<String, String>? headers = if (exists tmp = json.getObjectOrNull("headers")) then HashMap { for(key->val in tmp) if (is String val) key->val } else null;
    String? id = json.getStringOrNull("id");
    String? index = json.getStringOrNull("index");
    String? parent = json.getStringOrNull("parent");
    String? preference = json.getStringOrNull("preference");
    Boolean? realTime = json.getBooleanOrNull("realTime");
    Boolean? refresh = json.getBooleanOrNull("refresh");
    String? routing = json.getStringOrNull("routing");
    {String*}? storedFields = json.getArrayOrNull("storedFields")?.strings;
    String? type = json.getStringOrNull("type");
    Integer? version = json.getIntegerOrNull("version");
    String? versionType = json.getStringOrNull("versionType");
    return GetRequest {
      fetchSourceContext = fetchSourceContext;
      headers = headers;
      id = id;
      index = index;
      parent = parent;
      preference = preference;
      realTime = realTime;
      refresh = refresh;
      routing = routing;
      storedFields = storedFields;
      type = type;
      version = version;
      versionType = versionType;
    };
  }

  shared object toCeylon extends Converter<GetRequest_, GetRequest>() {
    shared actual GetRequest convert(GetRequest_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<GetRequest, GetRequest_>() {
    shared actual GetRequest_ convert(GetRequest src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = GetRequest_(json);
      return ret;
    }
  }
  shared JsonObject toJson(GetRequest obj) => obj.toJson();
}
