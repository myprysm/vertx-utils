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
import fr.myprysm.vertx.elasticsearch.action.bulk {
  BulkResponse_=BulkResponse
}
import ceylon.collection {
  HashMap
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.bulk.BulkResponse */
shared class BulkResponse() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    return json;
  }
}

shared object bulkResponse {

  shared BulkResponse fromJson(JsonObject json) {
    return BulkResponse {
    };
  }

  shared object toCeylon extends Converter<BulkResponse_, BulkResponse>() {
    shared actual BulkResponse convert(BulkResponse_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<BulkResponse, BulkResponse_>() {
    shared actual BulkResponse_ convert(BulkResponse src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = BulkResponse_(json);
      return ret;
    }
  }
  shared JsonObject toJson(BulkResponse obj) => obj.toJson();
}
