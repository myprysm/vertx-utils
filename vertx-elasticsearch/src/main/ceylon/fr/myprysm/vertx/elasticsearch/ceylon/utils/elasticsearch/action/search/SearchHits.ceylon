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
  SearchHits_=SearchHits
}
import ceylon.collection {
  HashMap
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.SearchHits */
shared class SearchHits() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    return json;
  }
}

shared object searchHits {

  shared SearchHits fromJson(JsonObject json) {
    return SearchHits {
    };
  }

  shared object toCeylon extends Converter<SearchHits_, SearchHits>() {
    shared actual SearchHits convert(SearchHits_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<SearchHits, SearchHits_>() {
    shared actual SearchHits_ convert(SearchHits src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = SearchHits_(json);
      return ret;
    }
  }
  shared JsonObject toJson(SearchHits obj) => obj.toJson();
}
