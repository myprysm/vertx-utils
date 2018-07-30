import fr.myprysm.vertx.elasticsearch.action.admin.indices.create {
  Alias_=Alias
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
/* Generated from fr.myprysm.vertx.elasticsearch.action.admin.indices.create.Alias */
shared class Alias(
  shared String? filter = null,
  shared String? indexRouting = null,
  shared String? name = null,
  shared String? searchRouting = null) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    if (exists filter) {
      json.put("filter", filter);
    }
    if (exists indexRouting) {
      json.put("indexRouting", indexRouting);
    }
    if (exists name) {
      json.put("name", name);
    }
    if (exists searchRouting) {
      json.put("searchRouting", searchRouting);
    }
    return json;
  }
}

shared object alias {

  shared Alias fromJson(JsonObject json) {
    String? filter = json.getStringOrNull("filter");
    String? indexRouting = json.getStringOrNull("indexRouting");
    String? name = json.getStringOrNull("name");
    String? searchRouting = json.getStringOrNull("searchRouting");
    return Alias {
      filter = filter;
      indexRouting = indexRouting;
      name = name;
      searchRouting = searchRouting;
    };
  }

  shared object toCeylon extends Converter<Alias_, Alias>() {
    shared actual Alias convert(Alias_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<Alias, Alias_>() {
    shared actual Alias_ convert(Alias src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = Alias_(json);
      return ret;
    }
  }
  shared JsonObject toJson(Alias obj) => obj.toJson();
}
