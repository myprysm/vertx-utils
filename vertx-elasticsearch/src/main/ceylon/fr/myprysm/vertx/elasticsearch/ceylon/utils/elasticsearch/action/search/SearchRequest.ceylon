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
  SearchRequest_=SearchRequest
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
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.SearchRequest */
" SearchRequest\n"
shared class SearchRequest(
  Map<String, String>? headers = null,
  shared {String*}? indices = null,
  shared JsonObject? source = null,
  shared {String*}? types = null) extends BaseRequest(
  headers) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    if (exists indices) {
      json.put("indices", JsonArray(indices));
    }
    if (exists source) {
      json.put("source", source);
    }
    if (exists types) {
      json.put("types", JsonArray(types));
    }
    return json;
  }
}

shared object searchRequest {

  shared SearchRequest fromJson(JsonObject json) {
    Map<String, String>? headers = if (exists tmp = json.getObjectOrNull("headers")) then HashMap { for(key->val in tmp) if (is String val) key->val } else null;
    {String*}? indices = json.getArrayOrNull("indices")?.strings;
    JsonObject? source = json.getObjectOrNull("source");
    {String*}? types = json.getArrayOrNull("types")?.strings;
    return SearchRequest {
      headers = headers;
      indices = indices;
      source = source;
      types = types;
    };
  }

  shared object toCeylon extends Converter<SearchRequest_, SearchRequest>() {
    shared actual SearchRequest convert(SearchRequest_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<SearchRequest, SearchRequest_>() {
    shared actual SearchRequest_ convert(SearchRequest src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = SearchRequest_(json);
      return ret;
    }
  }
  shared JsonObject toJson(SearchRequest obj) => obj.toJson();
}
