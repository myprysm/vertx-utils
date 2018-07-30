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
  Bucket_=Bucket
}
import ceylon.collection {
  HashMap
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.Bucket */
shared class Bucket(
  shared Map<String, Aggregation>? aggregations = null,
  shared Integer? docCount = null,
  shared String? key = null) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    if (exists aggregations) {
      json.put("aggregations", JsonObject{for(k->v in aggregations) k->aggregation_.toJson(v)});
    }
    if (exists docCount) {
      json.put("docCount", docCount);
    }
    if (exists key) {
      json.put("key", key);
    }
    return json;
  }
}

shared object bucket {

  shared Bucket fromJson(JsonObject json) {
    Map<String, Aggregation>? aggregations = if (exists tmp = json.getObjectOrNull("aggregations")) then HashMap { for(key->val in tmp) if (is JsonObject val) key->aggregation_.fromJson(val) } else null;
    Integer? docCount = json.getIntegerOrNull("docCount");
    String? key = json.getStringOrNull("key");
    return Bucket {
      aggregations = aggregations;
      docCount = docCount;
      key = key;
    };
  }

  shared object toCeylon extends Converter<Bucket_, Bucket>() {
    shared actual Bucket convert(Bucket_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<Bucket, Bucket_>() {
    shared actual Bucket_ convert(Bucket src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = Bucket_(json);
      return ret;
    }
  }
  shared JsonObject toJson(Bucket obj) => obj.toJson();
}
