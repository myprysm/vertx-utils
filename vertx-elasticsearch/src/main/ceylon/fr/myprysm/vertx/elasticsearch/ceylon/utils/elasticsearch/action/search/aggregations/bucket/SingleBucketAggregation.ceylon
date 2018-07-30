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
  SingleBucketAggregation_=SingleBucketAggregation
}
import ceylon.collection {
  HashMap
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.SingleBucketAggregation */
shared class SingleBucketAggregation(
  shared Map<String, Aggregation>? aggregations = null,
  JsonObject? data = null,
  shared Integer? docCount = null,
  JsonObject? metaData = null,
  String? name = null,
  String? type = null) extends Aggregation(
  data,
  metaData,
  name,
  type) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    if (exists aggregations) {
      json.put("aggregations", JsonObject{for(k->v in aggregations) k->aggregation_.toJson(v)});
    }
    if (exists docCount) {
      json.put("docCount", docCount);
    }
    return json;
  }
}

shared object singleBucketAggregation {

  shared SingleBucketAggregation fromJson(JsonObject json) {
    Map<String, Aggregation>? aggregations = if (exists tmp = json.getObjectOrNull("aggregations")) then HashMap { for(key->val in tmp) if (is JsonObject val) key->aggregation_.fromJson(val) } else null;
    JsonObject? data = json.getObjectOrNull("data");
    Integer? docCount = json.getIntegerOrNull("docCount");
    JsonObject? metaData = json.getObjectOrNull("metaData");
    String? name = json.getStringOrNull("name");
    String? type = json.getStringOrNull("type");
    return SingleBucketAggregation {
      aggregations = aggregations;
      data = data;
      docCount = docCount;
      metaData = metaData;
      name = name;
      type = type;
    };
  }

  shared object toCeylon extends Converter<SingleBucketAggregation_, SingleBucketAggregation>() {
    shared actual SingleBucketAggregation convert(SingleBucketAggregation_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<SingleBucketAggregation, SingleBucketAggregation_>() {
    shared actual SingleBucketAggregation_ convert(SingleBucketAggregation src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = SingleBucketAggregation_(json);
      return ret;
    }
  }
  shared JsonObject toJson(SingleBucketAggregation obj) => obj.toJson();
}
