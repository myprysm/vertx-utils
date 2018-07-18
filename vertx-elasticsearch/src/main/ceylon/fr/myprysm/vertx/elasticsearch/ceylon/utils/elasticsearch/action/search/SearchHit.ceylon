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
  SearchHit_=SearchHit
}
import ceylon.collection {
  HashMap
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.SearchHit */
shared class SearchHit() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    return json;
  }
}

shared object searchHit {

  shared SearchHit fromJson(JsonObject json) {
    return SearchHit {
    };
  }

  shared object toCeylon extends Converter<SearchHit_, SearchHit>() {
    shared actual SearchHit convert(SearchHit_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<SearchHit, SearchHit_>() {
    shared actual SearchHit_ convert(SearchHit src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = SearchHit_(json);
      return ret;
    }
  }
  shared JsonObject toJson(SearchHit obj) => obj.toJson();
}
