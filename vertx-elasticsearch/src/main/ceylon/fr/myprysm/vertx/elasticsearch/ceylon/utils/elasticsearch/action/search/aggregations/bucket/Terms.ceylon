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
  Terms_=Terms
}
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.search.aggregations.bucket {
  TermsBucket,
  termsBucket_=termsBucket
}
import ceylon.collection {
  HashMap
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.Terms */
shared class Terms(
  shared Map<String, TermsBucket>? buckets = null,
  JsonObject? data = null,
  shared Integer? docCountError = null,
  JsonObject? metaData = null,
  String? name = null,
  shared Integer? sumOfOtherDocCounts = null,
  String? type = null) extends Aggregation(
  data,
  metaData,
  name,
  type) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    if (exists buckets) {
      json.put("buckets", JsonObject{for(k->v in buckets) k->termsBucket_.toJson(v)});
    }
    if (exists docCountError) {
      json.put("docCountError", docCountError);
    }
    if (exists sumOfOtherDocCounts) {
      json.put("sumOfOtherDocCounts", sumOfOtherDocCounts);
    }
    return json;
  }
}

shared object terms {

  shared Terms fromJson(JsonObject json) {
    Map<String, TermsBucket>? buckets = if (exists tmp = json.getObjectOrNull("buckets")) then HashMap { for(key->val in tmp) if (is JsonObject val) key->termsBucket_.fromJson(val) } else null;
    JsonObject? data = json.getObjectOrNull("data");
    Integer? docCountError = json.getIntegerOrNull("docCountError");
    JsonObject? metaData = json.getObjectOrNull("metaData");
    String? name = json.getStringOrNull("name");
    Integer? sumOfOtherDocCounts = json.getIntegerOrNull("sumOfOtherDocCounts");
    String? type = json.getStringOrNull("type");
    return Terms {
      buckets = buckets;
      data = data;
      docCountError = docCountError;
      metaData = metaData;
      name = name;
      sumOfOtherDocCounts = sumOfOtherDocCounts;
      type = type;
    };
  }

  shared object toCeylon extends Converter<Terms_, Terms>() {
    shared actual Terms convert(Terms_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<Terms, Terms_>() {
    shared actual Terms_ convert(Terms src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = Terms_(json);
      return ret;
    }
  }
  shared JsonObject toJson(Terms obj) => obj.toJson();
}
