import fr.myprysm.vertx.elasticsearch.action.get {
  GetRequestItem_=GetRequestItem
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
/* Generated from fr.myprysm.vertx.elasticsearch.action.get.GetRequestItem */
shared class GetRequestItem(
  shared FetchSourceContext? fetchSourceContext = null,
  shared String? id = null,
  shared String? index = null,
  shared String? parent = null,
  shared String? routing = null,
  shared {String*}? storedFields = null,
  shared String? type = null,
  shared Integer? version = null,
  shared String? versionType = null) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
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

shared object getRequestItem {

  shared GetRequestItem fromJson(JsonObject json) {
    FetchSourceContext? fetchSourceContext = if (exists tmp = json.getObjectOrNull("fetchSourceContext")) then fetchSourceContext_.fromJson(tmp) else null;
    String? id = json.getStringOrNull("id");
    String? index = json.getStringOrNull("index");
    String? parent = json.getStringOrNull("parent");
    String? routing = json.getStringOrNull("routing");
    {String*}? storedFields = json.getArrayOrNull("storedFields")?.strings;
    String? type = json.getStringOrNull("type");
    Integer? version = json.getIntegerOrNull("version");
    String? versionType = json.getStringOrNull("versionType");
    return GetRequestItem {
      fetchSourceContext = fetchSourceContext;
      id = id;
      index = index;
      parent = parent;
      routing = routing;
      storedFields = storedFields;
      type = type;
      version = version;
      versionType = versionType;
    };
  }

  shared object toCeylon extends Converter<GetRequestItem_, GetRequestItem>() {
    shared actual GetRequestItem convert(GetRequestItem_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<GetRequestItem, GetRequestItem_>() {
    shared actual GetRequestItem_ convert(GetRequestItem src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = GetRequestItem_(json);
      return ret;
    }
  }
  shared JsonObject toJson(GetRequestItem obj) => obj.toJson();
}
