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
shared class Children() extends SingleBucketAggregation() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    return json;
  }
}

shared object children {

  shared Children fromJson(JsonObject json) {
    return Children {
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
