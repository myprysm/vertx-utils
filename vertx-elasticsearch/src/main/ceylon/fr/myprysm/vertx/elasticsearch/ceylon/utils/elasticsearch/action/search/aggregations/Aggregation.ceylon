import fr.myprysm.vertx.elasticsearch.action.search.aggregations {
  Aggregation_=Aggregation
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
import ceylon.collection {
  HashMap
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.aggregations.Aggregation */
shared class Aggregation(
  shared JsonObject? data = null,
  shared JsonObject? metaData = null,
  shared String? name = null,
  shared String? type = null) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    if (exists data) {
      json.put("data", data);
    }
    if (exists metaData) {
      json.put("metaData", metaData);
    }
    if (exists name) {
      json.put("name", name);
    }
    if (exists type) {
      json.put("type", type);
    }
    return json;
  }
}

shared object aggregation {

  shared Aggregation fromJson(JsonObject json) {
    JsonObject? data = json.getObjectOrNull("data");
    JsonObject? metaData = json.getObjectOrNull("metaData");
    String? name = json.getStringOrNull("name");
    String? type = json.getStringOrNull("type");
    return Aggregation {
      data = data;
      metaData = metaData;
      name = name;
      type = type;
    };
  }

  shared object toCeylon extends Converter<Aggregation_, Aggregation>() {
    shared actual Aggregation convert(Aggregation_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<Aggregation, Aggregation_>() {
    shared actual Aggregation_ convert(Aggregation src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = Aggregation_(json);
      return ret;
    }
  }
  shared JsonObject toJson(Aggregation obj) => obj.toJson();
}
