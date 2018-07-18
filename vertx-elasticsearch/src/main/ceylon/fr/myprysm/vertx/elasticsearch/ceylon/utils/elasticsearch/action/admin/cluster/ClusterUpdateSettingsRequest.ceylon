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
import fr.myprysm.vertx.elasticsearch.action.admin.cluster {
  ClusterUpdateSettingsRequest_=ClusterUpdateSettingsRequest
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action {
  BaseRequest
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsRequest */
" ClusterUpdateSettingsRequest DataObject.\n"
shared class ClusterUpdateSettingsRequest(
  Map<String, String>? headers = null) extends BaseRequest(
  headers) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = super.toJson();
    return json;
  }
}

shared object clusterUpdateSettingsRequest {

  shared ClusterUpdateSettingsRequest fromJson(JsonObject json) {
    Map<String, String>? headers = if (exists tmp = json.getObjectOrNull("headers")) then HashMap { for(key->val in tmp) if (is String val) key->val } else null;
    return ClusterUpdateSettingsRequest {
      headers = headers;
    };
  }

  shared object toCeylon extends Converter<ClusterUpdateSettingsRequest_, ClusterUpdateSettingsRequest>() {
    shared actual ClusterUpdateSettingsRequest convert(ClusterUpdateSettingsRequest_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<ClusterUpdateSettingsRequest, ClusterUpdateSettingsRequest_>() {
    shared actual ClusterUpdateSettingsRequest_ convert(ClusterUpdateSettingsRequest src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = ClusterUpdateSettingsRequest_(json);
      return ret;
    }
  }
  shared JsonObject toJson(ClusterUpdateSettingsRequest obj) => obj.toJson();
}
