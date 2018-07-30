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
  BulkResponseItem_=BulkResponseItem
}
import ceylon.collection {
  HashMap
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.bulk.BulkResponseItem */
shared class BulkResponseItem(
  shared Boolean? failed = null,
  shared String? failure = null,
  shared String? id = null,
  shared String? index = null,
  shared String? opType = null,
  shared String? status = null,
  shared String? type = null,
  shared Integer? version = null) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    if (exists failed) {
      json.put("failed", failed);
    }
    if (exists failure) {
      json.put("failure", failure);
    }
    if (exists id) {
      json.put("id", id);
    }
    if (exists index) {
      json.put("index", index);
    }
    if (exists opType) {
      json.put("opType", opType);
    }
    if (exists status) {
      json.put("status", status);
    }
    if (exists type) {
      json.put("type", type);
    }
    if (exists version) {
      json.put("version", version);
    }
    return json;
  }
}

shared object bulkResponseItem {

  shared BulkResponseItem fromJson(JsonObject json) {
    Boolean? failed = json.getBooleanOrNull("failed");
    String? failure = json.getStringOrNull("failure");
    String? id = json.getStringOrNull("id");
    String? index = json.getStringOrNull("index");
    String? opType = json.getStringOrNull("opType");
    String? status = json.getStringOrNull("status");
    String? type = json.getStringOrNull("type");
    Integer? version = json.getIntegerOrNull("version");
    return BulkResponseItem {
      failed = failed;
      failure = failure;
      id = id;
      index = index;
      opType = opType;
      status = status;
      type = type;
      version = version;
    };
  }

  shared object toCeylon extends Converter<BulkResponseItem_, BulkResponseItem>() {
    shared actual BulkResponseItem convert(BulkResponseItem_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<BulkResponseItem, BulkResponseItem_>() {
    shared actual BulkResponseItem_ convert(BulkResponseItem src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = BulkResponseItem_(json);
      return ret;
    }
  }
  shared JsonObject toJson(BulkResponseItem obj) => obj.toJson();
}
