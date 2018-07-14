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
  BulkRequest_=BulkRequest
}
import ceylon.collection {
  HashMap
}
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.support {
  DocWriteRequest,
  docWriteRequest_=docWriteRequest
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action {
  BaseRequest
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.bulk.BulkRequest */
shared class BulkRequest(
  Map<String, String>? headers = null,
  shared String? refreshPolicy = null,
  shared {DocWriteRequest*}? requests = null,
  shared Integer? timeout = null,
  shared Integer? waitForActiveShards = null) extends BaseRequest(
  headers) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    if (exists refreshPolicy) {
      json.put("refreshPolicy", refreshPolicy);
    }
    if (exists requests) {
      json.put("requests", JsonArray(requests.map(docWriteRequest_.toJson)));
    }
    if (exists timeout) {
      json.put("timeout", timeout);
    }
    if (exists waitForActiveShards) {
      json.put("waitForActiveShards", waitForActiveShards);
    }
    return json;
  }
}

shared object bulkRequest {

  shared BulkRequest fromJson(JsonObject json) {
    Map<String, String>? headers = if (exists tmp = json.getObjectOrNull("headers")) then HashMap { for(key->val in tmp) if (is String val) key->val } else null;
    String? refreshPolicy = json.getStringOrNull("refreshPolicy");
    {DocWriteRequest*}? requests = json.getArrayOrNull("requests")?.objects?.map(docWriteRequest_.fromJson);
    Integer? timeout = json.getIntegerOrNull("timeout");
    Integer? waitForActiveShards = json.getIntegerOrNull("waitForActiveShards");
    return BulkRequest {
      headers = headers;
      refreshPolicy = refreshPolicy;
      requests = requests;
      timeout = timeout;
      waitForActiveShards = waitForActiveShards;
    };
  }

  shared object toCeylon extends Converter<BulkRequest_, BulkRequest>() {
    shared actual BulkRequest convert(BulkRequest_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<BulkRequest, BulkRequest_>() {
    shared actual BulkRequest_ convert(BulkRequest src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = BulkRequest_(json);
      return ret;
    }
  }
  shared JsonObject toJson(BulkRequest obj) => obj.toJson();
}