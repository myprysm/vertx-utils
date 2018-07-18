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
import fr.myprysm.vertx.elasticsearch.action.support {
  AcknowledgedResponse_=AcknowledgedResponse
}
import ceylon.collection {
  HashMap
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.support.AcknowledgedResponse */
" Response that indicates whether it has been acknowledged.\n"
shared class AcknowledgedResponse() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    return json;
  }
}

shared object acknowledgedResponse {

  shared AcknowledgedResponse fromJson(JsonObject json) {
    return AcknowledgedResponse {
    };
  }

  shared object toCeylon extends Converter<AcknowledgedResponse_, AcknowledgedResponse>() {
    shared actual AcknowledgedResponse convert(AcknowledgedResponse_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<AcknowledgedResponse, AcknowledgedResponse_>() {
    shared actual AcknowledgedResponse_ convert(AcknowledgedResponse src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = AcknowledgedResponse_(json);
      return ret;
    }
  }
  shared JsonObject toJson(AcknowledgedResponse obj) => obj.toJson();
}
