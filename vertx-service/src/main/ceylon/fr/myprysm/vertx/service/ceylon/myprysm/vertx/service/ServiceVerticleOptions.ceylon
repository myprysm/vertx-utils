import fr.myprysm.vertx.service.ceylon.myprysm.vertx.service {
  ServiceOptions,
  serviceOptions_=serviceOptions
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
import fr.myprysm.vertx.service {
  ServiceVerticleOptions_=ServiceVerticleOptions
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.service.ServiceVerticleOptions */
shared class ServiceVerticleOptions(
  " The service map to start with the <code>ServiceVerticle</code>\n"
  shared Map<String, ServiceOptions>? services = null) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    if (exists services) {
      json.put("services", JsonObject{for(k->v in services) k->serviceOptions_.toJson(v)});
    }
    return json;
  }
}

shared object serviceVerticleOptions {

  shared ServiceVerticleOptions fromJson(JsonObject json) {
    Map<String, ServiceOptions>? services = if (exists tmp = json.getObjectOrNull("services")) then HashMap { for(key->val in tmp) if (is JsonObject val) key->serviceOptions_.fromJson(val) } else null;
    return ServiceVerticleOptions {
      services = services;
    };
  }

  shared object toCeylon extends Converter<ServiceVerticleOptions_, ServiceVerticleOptions>() {
    shared actual ServiceVerticleOptions convert(ServiceVerticleOptions_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<ServiceVerticleOptions, ServiceVerticleOptions_>() {
    shared actual ServiceVerticleOptions_ convert(ServiceVerticleOptions src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = ServiceVerticleOptions_(json);
      return ret;
    }
  }
  shared JsonObject toJson(ServiceVerticleOptions obj) => obj.toJson();
}
