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
  shared Boolean? allowPartialSearchResults = null,
  shared Integer? batchedReduceSize = null,
  Map<String, String>? headers = null,
  shared {String*}? indices = null,
  shared Integer? keepAlive = null,
  shared Integer? maxConcurrentShardRequests = null,
  shared Integer? preFilterShardSize = null,
  shared String? preference = null,
  shared Boolean? requestCache = null,
  shared String? routing = null,
  shared String? searchType = null,
  shared JsonObject? source = null,
  shared {String*}? types = null) extends BaseRequest(
  headers) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    if (exists allowPartialSearchResults) {
      json.put("allowPartialSearchResults", allowPartialSearchResults);
    }
    if (exists batchedReduceSize) {
      json.put("batchedReduceSize", batchedReduceSize);
    }
    if (exists indices) {
      json.put("indices", JsonArray(indices));
    }
    if (exists keepAlive) {
      json.put("keepAlive", keepAlive);
    }
    if (exists maxConcurrentShardRequests) {
      json.put("maxConcurrentShardRequests", maxConcurrentShardRequests);
    }
    if (exists preFilterShardSize) {
      json.put("preFilterShardSize", preFilterShardSize);
    }
    if (exists preference) {
      json.put("preference", preference);
    }
    if (exists requestCache) {
      json.put("requestCache", requestCache);
    }
    if (exists routing) {
      json.put("routing", routing);
    }
    if (exists searchType) {
      json.put("searchType", searchType);
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
    Boolean? allowPartialSearchResults = json.getBooleanOrNull("allowPartialSearchResults");
    Integer? batchedReduceSize = json.getIntegerOrNull("batchedReduceSize");
    Map<String, String>? headers = if (exists tmp = json.getObjectOrNull("headers")) then HashMap { for(key->val in tmp) if (is String val) key->val } else null;
    {String*}? indices = json.getArrayOrNull("indices")?.strings;
    Integer? keepAlive = json.getIntegerOrNull("keepAlive");
    Integer? maxConcurrentShardRequests = json.getIntegerOrNull("maxConcurrentShardRequests");
    Integer? preFilterShardSize = json.getIntegerOrNull("preFilterShardSize");
    String? preference = json.getStringOrNull("preference");
    Boolean? requestCache = json.getBooleanOrNull("requestCache");
    String? routing = json.getStringOrNull("routing");
    String? searchType = json.getStringOrNull("searchType");
    JsonObject? source = json.getObjectOrNull("source");
    {String*}? types = json.getArrayOrNull("types")?.strings;
    return SearchRequest {
      allowPartialSearchResults = allowPartialSearchResults;
      batchedReduceSize = batchedReduceSize;
      headers = headers;
      indices = indices;
      keepAlive = keepAlive;
      maxConcurrentShardRequests = maxConcurrentShardRequests;
      preFilterShardSize = preFilterShardSize;
      preference = preference;
      requestCache = requestCache;
      routing = routing;
      searchType = searchType;
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
