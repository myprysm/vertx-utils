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
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.search {
  SearchRequest,
  searchRequest_=searchRequest
}
import fr.myprysm.vertx.elasticsearch.action.search {
  MultiSearchRequest_=MultiSearchRequest
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
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.MultiSearchRequest */
shared class MultiSearchRequest(
  Map<String, String>? headers = null,
  shared {SearchRequest*}? requests = null) extends BaseRequest(
  headers) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    if (exists requests) {
      json.put("requests", JsonArray(requests.map(searchRequest_.toJson)));
    }
    return json;
  }
}

shared object multiSearchRequest {

  shared MultiSearchRequest fromJson(JsonObject json) {
    Map<String, String>? headers = if (exists tmp = json.getObjectOrNull("headers")) then HashMap { for(key->val in tmp) if (is String val) key->val } else null;
    {SearchRequest*}? requests = json.getArrayOrNull("requests")?.objects?.map(searchRequest_.fromJson);
    return MultiSearchRequest {
      headers = headers;
      requests = requests;
    };
  }

  shared object toCeylon extends Converter<MultiSearchRequest_, MultiSearchRequest>() {
    shared actual MultiSearchRequest convert(MultiSearchRequest_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<MultiSearchRequest, MultiSearchRequest_>() {
    shared actual MultiSearchRequest_ convert(MultiSearchRequest src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = MultiSearchRequest_(json);
      return ret;
    }
  }
  shared JsonObject toJson(MultiSearchRequest obj) => obj.toJson();
}
