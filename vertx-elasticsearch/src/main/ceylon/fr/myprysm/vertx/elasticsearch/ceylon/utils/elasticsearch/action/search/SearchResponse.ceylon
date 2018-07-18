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
  SearchResponse_=SearchResponse
}
import ceylon.collection {
  HashMap
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.SearchResponse */
shared class SearchResponse() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    return json;
  }
}

shared object searchResponse {

  shared SearchResponse fromJson(JsonObject json) {
    return SearchResponse {
    };
  }

  shared object toCeylon extends Converter<SearchResponse_, SearchResponse>() {
    shared actual SearchResponse convert(SearchResponse_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<SearchResponse, SearchResponse_>() {
    shared actual SearchResponse_ convert(SearchResponse src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = SearchResponse_(json);
      return ret;
    }
  }
  shared JsonObject toJson(SearchResponse obj) => obj.toJson();
}
