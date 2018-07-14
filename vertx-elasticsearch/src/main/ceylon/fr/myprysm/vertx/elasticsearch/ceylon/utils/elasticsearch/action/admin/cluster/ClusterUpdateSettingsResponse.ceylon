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
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.support {
  AcknowledgedResponse
}
import fr.myprysm.vertx.elasticsearch.action.admin.cluster {
  ClusterUpdateSettingsResponse_=ClusterUpdateSettingsResponse
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsResponse */
" ClusterUpdateSettingsResponse.\n"
shared class ClusterUpdateSettingsResponse(
  Boolean? acknowledged = null,
  shared JsonObject? persistentSettings = null,
  shared JsonObject? transientSettings = null) extends AcknowledgedResponse(
  acknowledged) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    if (exists persistentSettings) {
      json.put("persistentSettings", persistentSettings);
    }
    if (exists transientSettings) {
      json.put("transientSettings", transientSettings);
    }
    return json;
  }
}

shared object clusterUpdateSettingsResponse {

  shared ClusterUpdateSettingsResponse fromJson(JsonObject json) {
    Boolean? acknowledged = json.getBooleanOrNull("acknowledged");
    JsonObject? persistentSettings = json.getObjectOrNull("persistentSettings");
    JsonObject? transientSettings = json.getObjectOrNull("transientSettings");
    return ClusterUpdateSettingsResponse {
      acknowledged = acknowledged;
      persistentSettings = persistentSettings;
      transientSettings = transientSettings;
    };
  }

  shared object toCeylon extends Converter<ClusterUpdateSettingsResponse_, ClusterUpdateSettingsResponse>() {
    shared actual ClusterUpdateSettingsResponse convert(ClusterUpdateSettingsResponse_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<ClusterUpdateSettingsResponse, ClusterUpdateSettingsResponse_>() {
    shared actual ClusterUpdateSettingsResponse_ convert(ClusterUpdateSettingsResponse src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = ClusterUpdateSettingsResponse_(json);
      return ret;
    }
  }
  shared JsonObject toJson(ClusterUpdateSettingsResponse obj) => obj.toJson();
}