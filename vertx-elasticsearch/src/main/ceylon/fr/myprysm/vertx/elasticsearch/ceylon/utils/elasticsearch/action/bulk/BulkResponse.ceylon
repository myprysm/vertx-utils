import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.bulk {
  BulkResponseItem,
  bulkResponseItem_=bulkResponseItem
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
shared class BulkResponse(
  shared Boolean? errors = null,
  shared {BulkResponseItem*}? items = null,
  shared String? status = null,
  shared Integer? took = null) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    if (exists errors) {
      json.put("errors", errors);
    }
    if (exists items) {
      json.put("items", JsonArray(items.map(bulkResponseItem_.toJson)));
    }
    if (exists status) {
      json.put("status", status);
    }
    if (exists took) {
      json.put("took", took);
    }
    return json;
  }
}

shared object bulkResponse {

  shared BulkResponse fromJson(JsonObject json) {
    Boolean? errors = json.getBooleanOrNull("errors");
    {BulkResponseItem*}? items = json.getArrayOrNull("items")?.objects?.map(bulkResponseItem_.fromJson);
    String? status = json.getStringOrNull("status");
    Integer? took = json.getIntegerOrNull("took");
    return BulkResponse {
      errors = errors;
      items = items;
      status = status;
      took = took;
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
