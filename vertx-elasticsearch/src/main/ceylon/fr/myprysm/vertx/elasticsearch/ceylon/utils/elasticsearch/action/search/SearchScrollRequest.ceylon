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
import fr.myprysm.vertx.elasticsearch.action.search {
  SearchScrollRequest_=SearchScrollRequest
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
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.SearchScrollRequest */
shared class SearchScrollRequest(
  Map<String, String>? headers = null,
  shared Integer? keepAlive = null,
  shared String? scrollId = null) extends BaseRequest(
  headers) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    if (exists keepAlive) {
      json.put("keepAlive", keepAlive);
    }
    if (exists scrollId) {
      json.put("scrollId", scrollId);
    }
    return json;
  }
}

shared object searchScrollRequest {

  shared SearchScrollRequest fromJson(JsonObject json) {
    Map<String, String>? headers = if (exists tmp = json.getObjectOrNull("headers")) then HashMap { for(key->val in tmp) if (is String val) key->val } else null;
    Integer? keepAlive = json.getIntegerOrNull("keepAlive");
    String? scrollId = json.getStringOrNull("scrollId");
    return SearchScrollRequest {
      headers = headers;
      keepAlive = keepAlive;
      scrollId = scrollId;
    };
  }

  shared object toCeylon extends Converter<SearchScrollRequest_, SearchScrollRequest>() {
    shared actual SearchScrollRequest convert(SearchScrollRequest_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<SearchScrollRequest, SearchScrollRequest_>() {
    shared actual SearchScrollRequest_ convert(SearchScrollRequest src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = SearchScrollRequest_(json);
      return ret;
    }
  }
  shared JsonObject toJson(SearchScrollRequest obj) => obj.toJson();
}
