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
shared class FetchSourceContext() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    return json;
  }
}

shared object fetchSourceContext {

  shared FetchSourceContext fromJson(JsonObject json) {
    return FetchSourceContext {
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
