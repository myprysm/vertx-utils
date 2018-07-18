import fr.myprysm.vertx.elasticsearch.action.get {
  MultiGetResponse_=MultiGetResponse
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
/* Generated from fr.myprysm.vertx.elasticsearch.action.get.MultiGetResponse */
shared class MultiGetResponse() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    return json;
  }
}

shared object multiGetResponse {

  shared MultiGetResponse fromJson(JsonObject json) {
    return MultiGetResponse {
    };
  }

  shared object toCeylon extends Converter<MultiGetResponse_, MultiGetResponse>() {
    shared actual MultiGetResponse convert(MultiGetResponse_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<MultiGetResponse, MultiGetResponse_>() {
    shared actual MultiGetResponse_ convert(MultiGetResponse src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = MultiGetResponse_(json);
      return ret;
    }
  }
  shared JsonObject toJson(MultiGetResponse obj) => obj.toJson();
}
