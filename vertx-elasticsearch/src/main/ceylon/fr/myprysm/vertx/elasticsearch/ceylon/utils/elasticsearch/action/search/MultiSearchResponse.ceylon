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
  MultiSearchResponse_=MultiSearchResponse
}
import ceylon.collection {
  HashMap
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.MultiSearchResponse */
shared class MultiSearchResponse() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    return json;
  }
}

shared object multiSearchResponse {

  shared MultiSearchResponse fromJson(JsonObject json) {
    return MultiSearchResponse {
    };
  }

  shared object toCeylon extends Converter<MultiSearchResponse_, MultiSearchResponse>() {
    shared actual MultiSearchResponse convert(MultiSearchResponse_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<MultiSearchResponse, MultiSearchResponse_>() {
    shared actual MultiSearchResponse_ convert(MultiSearchResponse src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = MultiSearchResponse_(json);
      return ret;
    }
  }
  shared JsonObject toJson(MultiSearchResponse obj) => obj.toJson();
}
