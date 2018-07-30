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
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.search {
  NestedIdentity,
  nestedIdentity_=nestedIdentity
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
shared class NestedIdentity(
  shared NestedIdentity? child = null,
  shared String? field = null,
  shared Integer? offset = null) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    if (exists child) {
      json.put("child", child.toJson());
    }
    if (exists field) {
      json.put("field", field);
    }
    if (exists offset) {
      json.put("offset", offset);
    }
    return json;
  }
}

shared object nestedIdentity {

  shared NestedIdentity fromJson(JsonObject json) {
    NestedIdentity? child = if (exists tmp = json.getObjectOrNull("child")) then this.fromJson(tmp) else null;
    String? field = json.getStringOrNull("field");
    Integer? offset = json.getIntegerOrNull("offset");
    return NestedIdentity {
      child = child;
      field = field;
      offset = offset;
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
