import fr.myprysm.vertx.elasticsearch.action.get {
  GetResponse_=GetResponse
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
/* Generated from fr.myprysm.vertx.elasticsearch.action.get.GetResponse */
shared class GetResponse() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    return json;
  }
}

shared object getResponse {

  shared GetResponse fromJson(JsonObject json) {
    return GetResponse {
    };
  }

  shared object toCeylon extends Converter<GetResponse_, GetResponse>() {
    shared actual GetResponse convert(GetResponse_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<GetResponse, GetResponse_>() {
    shared actual GetResponse_ convert(GetResponse src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = GetResponse_(json);
      return ret;
    }
  }
  shared JsonObject toJson(GetResponse obj) => obj.toJson();
}
