import fr.myprysm.vertx.elasticsearch.action.get {
  MultiGetRequest_=MultiGetRequest
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
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.get {
  GetRequestItem,
  getRequestItem_=getRequestItem
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
/* Generated from fr.myprysm.vertx.elasticsearch.action.get.MultiGetRequest */
shared class MultiGetRequest(
  Map<String, String>? headers = null,
  shared {GetRequestItem*}? items = null) extends BaseRequest(
  headers) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    if (exists items) {
      json.put("items", JsonArray(items.map(getRequestItem_.toJson)));
    }
    return json;
  }
}

shared object multiGetRequest {

  shared MultiGetRequest fromJson(JsonObject json) {
    Map<String, String>? headers = if (exists tmp = json.getObjectOrNull("headers")) then HashMap { for(key->val in tmp) if (is String val) key->val } else null;
    {GetRequestItem*}? items = json.getArrayOrNull("items")?.objects?.map(getRequestItem_.fromJson);
    return MultiGetRequest {
      headers = headers;
      items = items;
    };
  }

  shared object toCeylon extends Converter<MultiGetRequest_, MultiGetRequest>() {
    shared actual MultiGetRequest convert(MultiGetRequest_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<MultiGetRequest, MultiGetRequest_>() {
    shared actual MultiGetRequest_ convert(MultiGetRequest src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = MultiGetRequest_(json);
      return ret;
    }
  }
  shared JsonObject toJson(MultiGetRequest obj) => obj.toJson();
}
