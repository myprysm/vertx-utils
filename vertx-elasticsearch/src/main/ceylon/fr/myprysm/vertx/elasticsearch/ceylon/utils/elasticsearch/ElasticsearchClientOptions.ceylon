import fr.myprysm.vertx.elasticsearch {
  ElasticsearchClientOptions_=ElasticsearchClientOptions
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
/* Generated from fr.myprysm.vertx.elasticsearch.ElasticsearchClientOptions */
shared class ElasticsearchClientOptions() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    return json;
  }
}

shared object elasticsearchClientOptions {

  shared ElasticsearchClientOptions fromJson(JsonObject json) {
    return ElasticsearchClientOptions {
    };
  }

  shared object toCeylon extends Converter<ElasticsearchClientOptions_, ElasticsearchClientOptions>() {
    shared actual ElasticsearchClientOptions convert(ElasticsearchClientOptions_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<ElasticsearchClientOptions, ElasticsearchClientOptions_>() {
    shared actual ElasticsearchClientOptions_ convert(ElasticsearchClientOptions src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = ElasticsearchClientOptions_(json);
      return ret;
    }
  }
  shared JsonObject toJson(ElasticsearchClientOptions obj) => obj.toJson();
}
