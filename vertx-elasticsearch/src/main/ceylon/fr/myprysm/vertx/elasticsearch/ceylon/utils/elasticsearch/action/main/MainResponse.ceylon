import fr.myprysm.vertx.elasticsearch.action.main {
  MainResponse_=MainResponse
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
/* Generated from fr.myprysm.vertx.elasticsearch.action.main.MainResponse */
shared class MainResponse(
  shared Boolean? available = null,
  shared String? build = null,
  shared String? clusterName = null,
  shared String? clusterUuid = null,
  shared String? nodeName = null,
  shared String? version = null) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    if (exists available) {
      json.put("available", available);
    }
    if (exists build) {
      json.put("build", build);
    }
    if (exists clusterName) {
      json.put("clusterName", clusterName);
    }
    if (exists clusterUuid) {
      json.put("clusterUuid", clusterUuid);
    }
    if (exists nodeName) {
      json.put("nodeName", nodeName);
    }
    if (exists version) {
      json.put("version", version);
    }
    return json;
  }
}

shared object mainResponse {

  shared MainResponse fromJson(JsonObject json) {
    Boolean? available = json.getBooleanOrNull("available");
    String? build = json.getStringOrNull("build");
    String? clusterName = json.getStringOrNull("clusterName");
    String? clusterUuid = json.getStringOrNull("clusterUuid");
    String? nodeName = json.getStringOrNull("nodeName");
    String? version = json.getStringOrNull("version");
    return MainResponse {
      available = available;
      build = build;
      clusterName = clusterName;
      clusterUuid = clusterUuid;
      nodeName = nodeName;
      version = version;
    };
  }

  shared object toCeylon extends Converter<MainResponse_, MainResponse>() {
    shared actual MainResponse convert(MainResponse_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<MainResponse, MainResponse_>() {
    shared actual MainResponse_ convert(MainResponse src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = MainResponse_(json);
      return ret;
    }
  }
  shared JsonObject toJson(MainResponse obj) => obj.toJson();
}
