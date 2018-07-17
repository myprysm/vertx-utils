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
  SearchHits,
  searchHits_=searchHits,
  NestedIdentity,
  nestedIdentity_=nestedIdentity
}
import fr.myprysm.vertx.elasticsearch.action.search {
  SearchHit_=SearchHit
}
import ceylon.collection {
  HashMap
}
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.support {
  Explanation,
  explanation_=explanation,
  DocumentField,
  documentField_=documentField
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.SearchHit */
shared class SearchHit(
  shared String? clusterAlias = null,
  shared Explanation? explanation = null,
  shared Map<String, DocumentField>? fields = null,
  shared String? id = null,
  shared String? index = null,
  shared Map<String, SearchHits>? innerHits = null,
  shared {String*}? matchedQueries = null,
  shared NestedIdentity? nestedIdentity = null,
  shared Float? score = null,
  shared JsonArray? sortValues = null,
  shared JsonObject? source = null,
  shared String? type = null,
  shared Integer? version = null) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    if (exists clusterAlias) {
      json.put("clusterAlias", clusterAlias);
    }
    if (exists explanation) {
      json.put("explanation", explanation.toJson());
    }
    if (exists fields) {
      json.put("fields", JsonObject{for(k->v in fields) k->documentField_.toJson(v)});
    }
    if (exists id) {
      json.put("id", id);
    }
    if (exists index) {
      json.put("index", index);
    }
    if (exists innerHits) {
      json.put("innerHits", JsonObject{for(k->v in innerHits) k->searchHits_.toJson(v)});
    }
    if (exists matchedQueries) {
      json.put("matchedQueries", JsonArray(matchedQueries));
    }
    if (exists nestedIdentity) {
      json.put("nestedIdentity", nestedIdentity.toJson());
    }
    if (exists score) {
      json.put("score", score);
    }
    if (exists sortValues) {
      json.put("sortValues", sortValues);
    }
    if (exists source) {
      json.put("source", source);
    }
    if (exists type) {
      json.put("type", type);
    }
    if (exists version) {
      json.put("version", version);
    }
    return json;
  }
}

shared object searchHit {

  shared SearchHit fromJson(JsonObject json) {
    String? clusterAlias = json.getStringOrNull("clusterAlias");
    Explanation? explanation = if (exists tmp = json.getObjectOrNull("explanation")) then explanation_.fromJson(tmp) else null;
    Map<String, DocumentField>? fields = if (exists tmp = json.getObjectOrNull("fields")) then HashMap { for(key->val in tmp) if (is JsonObject val) key->documentField_.fromJson(val) } else null;
    String? id = json.getStringOrNull("id");
    String? index = json.getStringOrNull("index");
    Map<String, SearchHits>? innerHits = if (exists tmp = json.getObjectOrNull("innerHits")) then HashMap { for(key->val in tmp) if (is JsonObject val) key->searchHits_.fromJson(val) } else null;
    {String*}? matchedQueries = json.getArrayOrNull("matchedQueries")?.strings;
    NestedIdentity? nestedIdentity = if (exists tmp = json.getObjectOrNull("nestedIdentity")) then nestedIdentity_.fromJson(tmp) else null;
    Float? score = json.getFloatOrNull("score");
    JsonArray? sortValues = json.getArrayOrNull("sortValues");
    JsonObject? source = json.getObjectOrNull("source");
    String? type = json.getStringOrNull("type");
    Integer? version = json.getIntegerOrNull("version");
    return SearchHit {
      clusterAlias = clusterAlias;
      explanation = explanation;
      fields = fields;
      id = id;
      index = index;
      innerHits = innerHits;
      matchedQueries = matchedQueries;
      nestedIdentity = nestedIdentity;
      score = score;
      sortValues = sortValues;
      source = source;
      type = type;
      version = version;
    };
  }

  shared object toCeylon extends Converter<SearchHit_, SearchHit>() {
    shared actual SearchHit convert(SearchHit_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<SearchHit, SearchHit_>() {
    shared actual SearchHit_ convert(SearchHit src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = SearchHit_(json);
      return ret;
    }
  }
  shared JsonObject toJson(SearchHit obj) => obj.toJson();
}
