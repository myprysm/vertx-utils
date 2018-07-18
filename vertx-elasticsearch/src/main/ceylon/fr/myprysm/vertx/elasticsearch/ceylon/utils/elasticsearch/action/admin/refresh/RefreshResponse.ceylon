import fr.myprysm.vertx.elasticsearch.action.admin.refresh {
  RefreshResponse_=RefreshResponse
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
/* Generated from fr.myprysm.vertx.elasticsearch.action.admin.refresh.RefreshResponse */
shared class RefreshResponse() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    return json;
  }
}

shared object refreshResponse {

  shared RefreshResponse fromJson(JsonObject json) {
    return RefreshResponse {
    };
  }

  shared object toCeylon extends Converter<RefreshResponse_, RefreshResponse>() {
    shared actual RefreshResponse convert(RefreshResponse_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<RefreshResponse, RefreshResponse_>() {
    shared actual RefreshResponse_ convert(RefreshResponse src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = RefreshResponse_(json);
      return ret;
    }
  }
  shared JsonObject toJson(RefreshResponse obj) => obj.toJson();
}
