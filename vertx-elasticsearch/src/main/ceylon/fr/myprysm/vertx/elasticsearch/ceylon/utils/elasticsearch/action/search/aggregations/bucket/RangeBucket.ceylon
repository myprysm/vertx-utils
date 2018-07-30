import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.search.aggregations {
  Aggregation,
  aggregation_=aggregation
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
import fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket {
  RangeBucket_=RangeBucket
}
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.search.aggregations.bucket {
  Bucket
}
import ceylon.collection {
  HashMap
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.RangeBucket */
shared class RangeBucket(
  Map<String, Aggregation>? aggregations = null,
  Integer? docCount = null,
  shared String? fromAsString = null,
  String? key = null,
  shared String? toAsString = null) extends Bucket(
  aggregations,
  docCount,
  key) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    if (exists fromAsString) {
      json.put("fromAsString", fromAsString);
    }
    if (exists toAsString) {
      json.put("toAsString", toAsString);
    }
    return json;
  }
}

shared object rangeBucket {

  shared RangeBucket fromJson(JsonObject json) {
    Map<String, Aggregation>? aggregations = if (exists tmp = json.getObjectOrNull("aggregations")) then HashMap { for(key->val in tmp) if (is JsonObject val) key->aggregation_.fromJson(val) } else null;
    Integer? docCount = json.getIntegerOrNull("docCount");
    String? fromAsString = json.getStringOrNull("fromAsString");
    String? key = json.getStringOrNull("key");
    String? toAsString = json.getStringOrNull("toAsString");
    return RangeBucket {
      aggregations = aggregations;
      docCount = docCount;
      fromAsString = fromAsString;
      key = key;
      toAsString = toAsString;
    };
  }

  shared object toCeylon extends Converter<RangeBucket_, RangeBucket>() {
    shared actual RangeBucket convert(RangeBucket_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<RangeBucket, RangeBucket_>() {
    shared actual RangeBucket_ convert(RangeBucket src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = RangeBucket_(json);
      return ret;
    }
  }
  shared JsonObject toJson(RangeBucket obj) => obj.toJson();
}
