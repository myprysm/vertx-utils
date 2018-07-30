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
  Children_=Children
}
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.search.aggregations.bucket {
  SingleBucketAggregation
}
import ceylon.collection {
  HashMap
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.Children */
shared class Children(
  Map<String, Aggregation>? aggregations = null,
  JsonObject? data = null,
  Integer? docCount = null,
  JsonObject? metaData = null,
  String? name = null,
  String? type = null) extends SingleBucketAggregation(
  aggregations,
  data,
  docCount,
  metaData,
  name,
  type) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    return json;
  }
}

shared object children {

  shared Children fromJson(JsonObject json) {
    Map<String, Aggregation>? aggregations = if (exists tmp = json.getObjectOrNull("aggregations")) then HashMap { for(key->val in tmp) if (is JsonObject val) key->aggregation_.fromJson(val) } else null;
    JsonObject? data = json.getObjectOrNull("data");
    Integer? docCount = json.getIntegerOrNull("docCount");
    JsonObject? metaData = json.getObjectOrNull("metaData");
    String? name = json.getStringOrNull("name");
    String? type = json.getStringOrNull("type");
    return Children {
      aggregations = aggregations;
      data = data;
      docCount = docCount;
      metaData = metaData;
      name = name;
      type = type;
    };
  }

  shared object toCeylon extends Converter<Children_, Children>() {
    shared actual Children convert(Children_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<Children, Children_>() {
    shared actual Children_ convert(Children src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = Children_(json);
      return ret;
    }
  }
  shared JsonObject toJson(Children obj) => obj.toJson();
}
