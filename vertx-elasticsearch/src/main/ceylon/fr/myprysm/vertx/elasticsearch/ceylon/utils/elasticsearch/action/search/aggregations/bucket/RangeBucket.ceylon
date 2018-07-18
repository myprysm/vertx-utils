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
shared class RangeBucket() extends Bucket() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    return json;
  }
}

shared object rangeBucket {

  shared RangeBucket fromJson(JsonObject json) {
    return RangeBucket {
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
