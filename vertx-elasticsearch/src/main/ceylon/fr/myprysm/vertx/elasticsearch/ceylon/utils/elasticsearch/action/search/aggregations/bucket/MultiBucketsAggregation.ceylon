import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.search.aggregations {
  Aggregation
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
  MultiBucketsAggregation_=MultiBucketsAggregation
}
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.search.aggregations.bucket {
  Bucket,
  bucket_=bucket
}
import ceylon.collection {
  HashMap
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.MultiBucketsAggregation */
shared class MultiBucketsAggregation(
  shared Map<String, Bucket>? buckets = null,
  JsonObject? data = null,
  JsonObject? metaData = null,
  String? name = null,
  String? type = null) extends Aggregation(
  data,
  metaData,
  name,
  type) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    if (exists buckets) {
      json.put("buckets", JsonObject{for(k->v in buckets) k->bucket_.toJson(v)});
    }
    return json;
  }
}

shared object multiBucketsAggregation {

  shared MultiBucketsAggregation fromJson(JsonObject json) {
    Map<String, Bucket>? buckets = if (exists tmp = json.getObjectOrNull("buckets")) then HashMap { for(key->val in tmp) if (is JsonObject val) key->bucket_.fromJson(val) } else null;
    JsonObject? data = json.getObjectOrNull("data");
    JsonObject? metaData = json.getObjectOrNull("metaData");
    String? name = json.getStringOrNull("name");
    String? type = json.getStringOrNull("type");
    return MultiBucketsAggregation {
      buckets = buckets;
      data = data;
      metaData = metaData;
      name = name;
      type = type;
    };
  }

  shared object toCeylon extends Converter<MultiBucketsAggregation_, MultiBucketsAggregation>() {
    shared actual MultiBucketsAggregation convert(MultiBucketsAggregation_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<MultiBucketsAggregation, MultiBucketsAggregation_>() {
    shared actual MultiBucketsAggregation_ convert(MultiBucketsAggregation src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = MultiBucketsAggregation_(json);
      return ret;
    }
  }
  shared JsonObject toJson(MultiBucketsAggregation obj) => obj.toJson();
}
