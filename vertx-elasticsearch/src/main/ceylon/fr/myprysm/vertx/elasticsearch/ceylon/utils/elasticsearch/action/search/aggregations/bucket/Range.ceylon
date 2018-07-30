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
  Range_=Range
}
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.search.aggregations.bucket {
  RangeBucket,
  rangeBucket_=rangeBucket
}
import ceylon.collection {
  HashMap
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.Range */
shared class Range(
  shared Map<String, RangeBucket>? buckets = null,
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
      json.put("buckets", JsonObject{for(k->v in buckets) k->rangeBucket_.toJson(v)});
    }
    return json;
  }
}

shared object range {

  shared Range fromJson(JsonObject json) {
    Map<String, RangeBucket>? buckets = if (exists tmp = json.getObjectOrNull("buckets")) then HashMap { for(key->val in tmp) if (is JsonObject val) key->rangeBucket_.fromJson(val) } else null;
    JsonObject? data = json.getObjectOrNull("data");
    JsonObject? metaData = json.getObjectOrNull("metaData");
    String? name = json.getStringOrNull("name");
    String? type = json.getStringOrNull("type");
    return Range {
      buckets = buckets;
      data = data;
      metaData = metaData;
      name = name;
      type = type;
    };
  }

  shared object toCeylon extends Converter<Range_, Range>() {
    shared actual Range convert(Range_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<Range, Range_>() {
    shared actual Range_ convert(Range src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = Range_(json);
      return ret;
    }
  }
  shared JsonObject toJson(Range obj) => obj.toJson();
}
