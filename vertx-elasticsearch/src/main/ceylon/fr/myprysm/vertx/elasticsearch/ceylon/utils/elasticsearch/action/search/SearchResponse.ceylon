import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.search.suggest {
  Suggestion,
  suggestion_=suggestion
}
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.search.aggregations {
  Aggregation,
  aggregation_=aggregation
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
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.search {
  Clusters,
  clusters_=clusters,
  SearchHits,
  searchHits_=searchHits
}
import fr.myprysm.vertx.elasticsearch.action.search {
  SearchResponse_=SearchResponse
}
import ceylon.collection {
  HashMap
}
import fr.myprysm.vertx.elasticsearch.ceylon.utils.elasticsearch.action.support {
  ShardFailure,
  shardFailure_=shardFailure
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from fr.myprysm.vertx.elasticsearch.action.search.SearchResponse */
shared class SearchResponse(
  shared Map<String, Aggregation>? aggregations = null,
  shared Clusters? clusters = null,
  shared Integer? failedShards = null,
  shared SearchHits? hits = null,
  shared Integer? numReducePhases = null,
  shared Map<String, JsonObject>? profileResults = null,
  shared String? scrollId = null,
  shared {ShardFailure*}? shardFailures = null,
  shared Integer? skippedShards = null,
  shared Integer? successfulShards = null,
  shared Map<String, Suggestion>? suggest = null,
  shared Boolean? terminatedEarly = null,
  shared Boolean? timedOut = null,
  shared Integer? tookInMillis = null,
  shared Integer? totalShards = null) satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    if (exists aggregations) {
      json.put("aggregations", JsonObject{for(k->v in aggregations) k->aggregation_.toJson(v)});
    }
    if (exists clusters) {
      json.put("clusters", clusters.toJson());
    }
    if (exists failedShards) {
      json.put("failedShards", failedShards);
    }
    if (exists hits) {
      json.put("hits", hits.toJson());
    }
    if (exists numReducePhases) {
      json.put("numReducePhases", numReducePhases);
    }
    if (exists profileResults) {
      json.put("profileResults", JsonObject(profileResults));
    }
    if (exists scrollId) {
      json.put("scrollId", scrollId);
    }
    if (exists shardFailures) {
      json.put("shardFailures", JsonArray(shardFailures.map(shardFailure_.toJson)));
    }
    if (exists skippedShards) {
      json.put("skippedShards", skippedShards);
    }
    if (exists successfulShards) {
      json.put("successfulShards", successfulShards);
    }
    if (exists suggest) {
      json.put("suggest", JsonObject{for(k->v in suggest) k->suggestion_.toJson(v)});
    }
    if (exists terminatedEarly) {
      json.put("terminatedEarly", terminatedEarly);
    }
    if (exists timedOut) {
      json.put("timedOut", timedOut);
    }
    if (exists tookInMillis) {
      json.put("tookInMillis", tookInMillis);
    }
    if (exists totalShards) {
      json.put("totalShards", totalShards);
    }
    return json;
  }
}

shared object searchResponse {

  shared SearchResponse fromJson(JsonObject json) {
    Map<String, Aggregation>? aggregations = if (exists tmp = json.getObjectOrNull("aggregations")) then HashMap { for(key->val in tmp) if (is JsonObject val) key->aggregation_.fromJson(val) } else null;
    Clusters? clusters = if (exists tmp = json.getObjectOrNull("clusters")) then clusters_.fromJson(tmp) else null;
    Integer? failedShards = json.getIntegerOrNull("failedShards");
    SearchHits? hits = if (exists tmp = json.getObjectOrNull("hits")) then searchHits_.fromJson(tmp) else null;
    Integer? numReducePhases = json.getIntegerOrNull("numReducePhases");
    Map<String, JsonObject>? profileResults = if (exists tmp = json.getObjectOrNull("profileResults")) then HashMap { for(key->val in tmp) if (is JsonObject val) key->val } else null;
    String? scrollId = json.getStringOrNull("scrollId");
    {ShardFailure*}? shardFailures = json.getArrayOrNull("shardFailures")?.objects?.map(shardFailure_.fromJson);
    Integer? skippedShards = json.getIntegerOrNull("skippedShards");
    Integer? successfulShards = json.getIntegerOrNull("successfulShards");
    Map<String, Suggestion>? suggest = if (exists tmp = json.getObjectOrNull("suggest")) then HashMap { for(key->val in tmp) if (is JsonObject val) key->suggestion_.fromJson(val) } else null;
    Boolean? terminatedEarly = json.getBooleanOrNull("terminatedEarly");
    Boolean? timedOut = json.getBooleanOrNull("timedOut");
    Integer? tookInMillis = json.getIntegerOrNull("tookInMillis");
    Integer? totalShards = json.getIntegerOrNull("totalShards");
    return SearchResponse {
      aggregations = aggregations;
      clusters = clusters;
      failedShards = failedShards;
      hits = hits;
      numReducePhases = numReducePhases;
      profileResults = profileResults;
      scrollId = scrollId;
      shardFailures = shardFailures;
      skippedShards = skippedShards;
      successfulShards = successfulShards;
      suggest = suggest;
      terminatedEarly = terminatedEarly;
      timedOut = timedOut;
      tookInMillis = tookInMillis;
      totalShards = totalShards;
    };
  }

  shared object toCeylon extends Converter<SearchResponse_, SearchResponse>() {
    shared actual SearchResponse convert(SearchResponse_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<SearchResponse, SearchResponse_>() {
    shared actual SearchResponse_ convert(SearchResponse src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = SearchResponse_(json);
      return ret;
    }
  }
  shared JsonObject toJson(SearchResponse obj) => obj.toJson();
}
