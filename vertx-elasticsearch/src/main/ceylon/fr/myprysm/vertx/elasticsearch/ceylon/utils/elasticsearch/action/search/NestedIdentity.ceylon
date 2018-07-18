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
import fr.myprysm.vertx.elasticsearch.action.search {
  NestedIdentity_=NestedIdentity
}
import ceylon.collection {
  HashMap
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.NestedIdentity */
shared class NestedIdentity() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    return json;
  }
}

shared object nestedIdentity {

  shared NestedIdentity fromJson(JsonObject json) {
    return NestedIdentity {
    };
  }

  shared object toCeylon extends Converter<NestedIdentity_, NestedIdentity>() {
    shared actual NestedIdentity convert(NestedIdentity_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<NestedIdentity, NestedIdentity_>() {
    shared actual NestedIdentity_ convert(NestedIdentity src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = NestedIdentity_(json);
      return ret;
    }
  }
  shared JsonObject toJson(NestedIdentity obj) => obj.toJson();
}
