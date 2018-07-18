import fr.myprysm.vertx.elasticsearch.action.main {
  MainResponse_=MainResponse
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
import ceylon.collection {
  HashMap
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.main.MainResponse */
shared class MainResponse() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    return json;
  }
}

shared object mainResponse {

  shared MainResponse fromJson(JsonObject json) {
    return MainResponse {
    };
  }

  shared object toCeylon extends Converter<MainResponse_, MainResponse>() {
    shared actual MainResponse convert(MainResponse_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<MainResponse, MainResponse_>() {
    shared actual MainResponse_ convert(MainResponse src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = MainResponse_(json);
      return ret;
    }
  }
  shared JsonObject toJson(MainResponse obj) => obj.toJson();
}
