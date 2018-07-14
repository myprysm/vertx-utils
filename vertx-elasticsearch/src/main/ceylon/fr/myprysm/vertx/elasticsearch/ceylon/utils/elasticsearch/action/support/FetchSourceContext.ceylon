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
import fr.myprysm.vertx.elasticsearch.action.support {
  FetchSourceContext_=FetchSourceContext
}
import ceylon.collection {
  HashMap
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.support.FetchSourceContext */
shared class FetchSourceContext(
  shared {String*}? excludes = null,
  shared Boolean? fetchSource = null,
  shared {String*}? includes = null) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    if (exists excludes) {
      json.put("excludes", JsonArray(excludes));
    }
    if (exists fetchSource) {
      json.put("fetchSource", fetchSource);
    }
    if (exists includes) {
      json.put("includes", JsonArray(includes));
    }
    return json;
  }
}

shared object fetchSourceContext {

  shared FetchSourceContext fromJson(JsonObject json) {
    {String*}? excludes = json.getArrayOrNull("excludes")?.strings;
    Boolean? fetchSource = json.getBooleanOrNull("fetchSource");
    {String*}? includes = json.getArrayOrNull("includes")?.strings;
    return FetchSourceContext {
      excludes = excludes;
      fetchSource = fetchSource;
      includes = includes;
    };
  }

  shared object toCeylon extends Converter<FetchSourceContext_, FetchSourceContext>() {
    shared actual FetchSourceContext convert(FetchSourceContext_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<FetchSourceContext, FetchSourceContext_>() {
    shared actual FetchSourceContext_ convert(FetchSourceContext src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = FetchSourceContext_(json);
      return ret;
    }
  }
  shared JsonObject toJson(FetchSourceContext obj) => obj.toJson();
}
