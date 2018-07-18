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
  TermsBucket_=TermsBucket
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
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.TermsBucket */
shared class TermsBucket() extends Bucket() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    return json;
  }
}

shared object termsBucket {

  shared TermsBucket fromJson(JsonObject json) {
    return TermsBucket {
    };
  }

  shared object toCeylon extends Converter<TermsBucket_, TermsBucket>() {
    shared actual TermsBucket convert(TermsBucket_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<TermsBucket, TermsBucket_>() {
    shared actual TermsBucket_ convert(TermsBucket src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = TermsBucket_(json);
      return ret;
    }
  }
  shared JsonObject toJson(TermsBucket obj) => obj.toJson();
}
