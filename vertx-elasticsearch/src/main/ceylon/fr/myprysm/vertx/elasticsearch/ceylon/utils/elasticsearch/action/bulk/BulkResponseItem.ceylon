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
shared class BulkResponseItem() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    return json;
  }
}

shared object bulkResponseItem {

  shared BulkResponseItem fromJson(JsonObject json) {
    return BulkResponseItem {
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
