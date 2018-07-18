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
import ceylon.collection {
  HashMap
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.Terms */
shared class Terms() extends Aggregation() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    return json;
  }
}

shared object terms {

  shared Terms fromJson(JsonObject json) {
    return Terms {
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
