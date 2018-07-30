import fr.myprysm.vertx.elasticsearch.action.get {
  MultiGetResponse_=MultiGetResponse
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
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.get {
  GetFailure,
  getFailure_=getFailure,
  GetResponse,
  getResponse_=getResponse
}
import ceylon.collection {
  HashMap
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.get.MultiGetResponse */
shared class MultiGetResponse(
  shared {GetFailure*}? failures = null,
  shared {GetResponse*}? responses = null) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    if (exists failures) {
      json.put("failures", JsonArray(failures.map(getFailure_.toJson)));
    }
    if (exists responses) {
      json.put("responses", JsonArray(responses.map(getResponse_.toJson)));
    }
    return json;
  }
}

shared object multiGetResponse {

  shared MultiGetResponse fromJson(JsonObject json) {
    {GetFailure*}? failures = json.getArrayOrNull("failures")?.objects?.map(getFailure_.fromJson);
    {GetResponse*}? responses = json.getArrayOrNull("responses")?.objects?.map(getResponse_.fromJson);
    return MultiGetResponse {
      failures = failures;
      responses = responses;
    };
  }

  shared object toCeylon extends Converter<MultiGetResponse_, MultiGetResponse>() {
    shared actual MultiGetResponse convert(MultiGetResponse_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<MultiGetResponse, MultiGetResponse_>() {
    shared actual MultiGetResponse_ convert(MultiGetResponse src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = MultiGetResponse_(json);
      return ret;
    }
  }
  shared JsonObject toJson(MultiGetResponse obj) => obj.toJson();
}
