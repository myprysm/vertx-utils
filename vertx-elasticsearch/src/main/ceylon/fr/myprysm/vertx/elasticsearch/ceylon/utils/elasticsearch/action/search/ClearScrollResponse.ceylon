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
  ClearScrollResponse_=ClearScrollResponse
}
import ceylon.collection {
  HashMap
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.ClearScrollResponse */
shared class ClearScrollResponse() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    return json;
  }
}

shared object clearScrollResponse {

  shared ClearScrollResponse fromJson(JsonObject json) {
    return ClearScrollResponse {
    };
  }

  shared object toCeylon extends Converter<ClearScrollResponse_, ClearScrollResponse>() {
    shared actual ClearScrollResponse convert(ClearScrollResponse_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<ClearScrollResponse, ClearScrollResponse_>() {
    shared actual ClearScrollResponse_ convert(ClearScrollResponse src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = ClearScrollResponse_(json);
      return ret;
    }
  }
  shared JsonObject toJson(ClearScrollResponse obj) => obj.toJson();
}
