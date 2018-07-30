package fr.myprysm.vertx.elasticsearch.kotlin.action.search

import fr.myprysm.vertx.elasticsearch.action.search.SearchRequest
import org.elasticsearch.action.search.SearchType

/**
 * A function providing a DSL for building [fr.myprysm.vertx.elasticsearch.action.search.SearchRequest] objects.
 *
 * SearchRequest
 *
 * @param allowPartialSearchResults
 * @param batchedReduceSize 
 * @param headers  Add a header to the request.
 * @param indices
 * @param keepAlive
 * @param maxConcurrentShardRequests
 * @param preFilterShardSize
 * @param preference
 * @param requestCache
 * @param routing
 * @param searchType
 * @param source
 * @param types
 *
 * <p/>
 * NOTE: This function has been automatically generated from the [fr.myprysm.vertx.elasticsearch.action.search.SearchRequest original] using Vert.x codegen.
 */
fun SearchRequest(
        allowPartialSearchResults: Boolean? = null,
        batchedReduceSize: Int? = null,
        headers: Map<String, String>? = null,
        indices: Iterable<String>? = null,
        keepAlive: Long? = null,
        maxConcurrentShardRequests: Int? = null,
        preFilterShardSize: Int? = null,
        preference: String? = null,
        requestCache: Boolean? = null,
        routing: String? = null,
        searchType: SearchType? = null,
        source: io.vertx.core.json.JsonObject? = null,
        types: Iterable<String>? = null): SearchRequest = fr.myprysm.vertx.elasticsearch.action.search.SearchRequest().apply {

    if (allowPartialSearchResults != null) {
        this.setAllowPartialSearchResults(allowPartialSearchResults)
    }
    if (batchedReduceSize != null) {
        this.setBatchedReduceSize(batchedReduceSize)
    }
    if (headers != null) {
        this.setHeaders(headers)
    }
    if (indices != null) {
        this.setIndices(indices.toList())
    }
    if (keepAlive != null) {
        this.setKeepAlive(keepAlive)
    }
    if (maxConcurrentShardRequests != null) {
        this.setMaxConcurrentShardRequests(maxConcurrentShardRequests)
    }
    if (preFilterShardSize != null) {
        this.setPreFilterShardSize(preFilterShardSize)
    }
    if (preference != null) {
        this.setPreference(preference)
    }
    if (requestCache != null) {
        this.setRequestCache(requestCache)
    }
    if (routing != null) {
        this.setRouting(routing)
    }
    if (searchType != null) {
        this.setSearchType(searchType)
    }
    if (source != null) {
        this.setSource(source)
    }
    if (types != null) {
        this.setTypes(types.toList())
    }
}

