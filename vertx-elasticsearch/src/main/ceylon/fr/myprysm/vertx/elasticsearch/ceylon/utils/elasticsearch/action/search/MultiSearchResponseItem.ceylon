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
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.search {
  SearchResponse,
  searchResponse_=searchResponse
}
import fr.myprysm.vertx.elasticsearch.action.search {
  MultiSearchResponseItem_=MultiSearchResponseItem
}
import ceylon.collection {
  HashMap
}
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.support {
  Failure,
  failure_=failure
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.MultiSearchResponseItem */
shared class MultiSearchResponseItem(
  shared Failure? failure = null,
  shared SearchResponse? response = null) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    if (exists failure) {
      json.put("failure", failure.toJson());
    }
    if (exists response) {
      json.put("response", response.toJson());
    }
    return json;
  }
}

shared object multiSearchResponseItem {

  shared MultiSearchResponseItem fromJson(JsonObject json) {
    Failure? failure = if (exists tmp = json.getObjectOrNull("failure")) then failure_.fromJson(tmp) else null;
    SearchResponse? response = if (exists tmp = json.getObjectOrNull("response")) then searchResponse_.fromJson(tmp) else null;
    return MultiSearchResponseItem {
      failure = failure;
      response = response;
    };
  }

  shared object toCeylon extends Converter<MultiSearchResponseItem_, MultiSearchResponseItem>() {
    shared actual MultiSearchResponseItem convert(MultiSearchResponseItem_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<MultiSearchResponseItem, MultiSearchResponseItem_>() {
    shared actual MultiSearchResponseItem_ convert(MultiSearchResponseItem src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = MultiSearchResponseItem_(json);
      return ret;
    }
  }
  shared JsonObject toJson(MultiSearchResponseItem obj) => obj.toJson();
}
