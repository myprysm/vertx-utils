import fr.myprysm.vertx.elasticsearch {
  HttpHost_=HttpHost
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
/* Generated from fr.myprysm.vertx.elasticsearch.HttpHost */
" HttpHost.\n"
shared class HttpHost() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    return json;
  }
}

shared object httpHost {

  shared HttpHost fromJson(JsonObject json) {
    return HttpHost {
    };
  }

  shared object toCeylon extends Converter<HttpHost_, HttpHost>() {
    shared actual HttpHost convert(HttpHost_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<HttpHost, HttpHost_>() {
    shared actual HttpHost_ convert(HttpHost src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = HttpHost_(json);
      return ret;
    }
  }
  shared JsonObject toJson(HttpHost obj) => obj.toJson();
}
