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
import ceylon.collection {
  HashMap
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.Range */
shared class Range() extends Aggregation() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    return json;
  }
}

shared object range {

  shared Range fromJson(JsonObject json) {
    return Range {
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
