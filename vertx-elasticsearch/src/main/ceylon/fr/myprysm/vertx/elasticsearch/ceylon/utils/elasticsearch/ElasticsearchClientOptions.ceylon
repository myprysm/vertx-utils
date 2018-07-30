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
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch {
  HttpHost,
  httpHost_=httpHost
}
import ceylon.collection {
  HashMap
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.ElasticsearchClientOptions */
shared class ElasticsearchClientOptions(
  shared Map<String, String>? defaultHeaders = null,
  shared {HttpHost*}? hosts = null,
  shared Integer? maxRetryTimeout = null,
  shared String? pathPrefix = null,
  shared Boolean? useNativeAsyncAPI = null) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    if (exists defaultHeaders) {
      json.put("defaultHeaders", JsonObject(defaultHeaders));
    }
    if (exists hosts) {
      json.put("hosts", JsonArray(hosts.map(httpHost_.toJson)));
    }
    if (exists maxRetryTimeout) {
      json.put("maxRetryTimeout", maxRetryTimeout);
    }
    if (exists pathPrefix) {
      json.put("pathPrefix", pathPrefix);
    }
    if (exists useNativeAsyncAPI) {
      json.put("useNativeAsyncAPI", useNativeAsyncAPI);
    }
    return json;
  }
}

shared object elasticsearchClientOptions {

  shared ElasticsearchClientOptions fromJson(JsonObject json) {
    Map<String, String>? defaultHeaders = if (exists tmp = json.getObjectOrNull("defaultHeaders")) then HashMap { for(key->val in tmp) if (is String val) key->val } else null;
    {HttpHost*}? hosts = json.getArrayOrNull("hosts")?.objects?.map(httpHost_.fromJson);
    Integer? maxRetryTimeout = json.getIntegerOrNull("maxRetryTimeout");
    String? pathPrefix = json.getStringOrNull("pathPrefix");
    Boolean? useNativeAsyncAPI = json.getBooleanOrNull("useNativeAsyncAPI");
    return ElasticsearchClientOptions {
      defaultHeaders = defaultHeaders;
      hosts = hosts;
      maxRetryTimeout = maxRetryTimeout;
      pathPrefix = pathPrefix;
      useNativeAsyncAPI = useNativeAsyncAPI;
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
