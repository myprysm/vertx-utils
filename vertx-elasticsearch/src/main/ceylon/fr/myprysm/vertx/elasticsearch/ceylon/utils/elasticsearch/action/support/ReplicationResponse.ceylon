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
  ReplicationResponse_=ReplicationResponse
}
import ceylon.collection {
  HashMap
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.support.ReplicationResponse */
shared class ReplicationResponse() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    return json;
  }
}

shared object replicationResponse {

  shared ReplicationResponse fromJson(JsonObject json) {
    return ReplicationResponse {
    };
  }

  shared object toCeylon extends Converter<ReplicationResponse_, ReplicationResponse>() {
    shared actual ReplicationResponse convert(ReplicationResponse_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<ReplicationResponse, ReplicationResponse_>() {
    shared actual ReplicationResponse_ convert(ReplicationResponse src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = ReplicationResponse_(json);
      return ret;
    }
  }
  shared JsonObject toJson(ReplicationResponse obj) => obj.toJson();
}
