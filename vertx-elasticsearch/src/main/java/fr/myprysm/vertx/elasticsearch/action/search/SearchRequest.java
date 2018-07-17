package fr.myprysm.vertx.elasticsearch.action.search;

import fr.myprysm.vertx.elasticsearch.action.BaseRequest;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.elasticsearch.action.search.SearchType;

import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.action.search.SearchRequest.DEFAULT_PRE_FILTER_SHARD_SIZE;

/**
 * SearchRequest
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@DataObject(generateConverter = true)
public class SearchRequest extends BaseRequest {

    /**
     * The indices
     */
    private List<String> indices;
    /**
     * The types
     */
    private List<String> types;

    private String routing;
    private String preference;

    /**
     * The search source
     */
    private JsonObject source;

    private SearchType searchType;

    private Boolean allowPartialSearchResults;
    private Boolean requestCache;
    private Long keepAlive;
    private int batchedReduceSize = 512;
    private int maxConcurrentShardRequests = 0;
    private int preFilterShardSize = DEFAULT_PRE_FILTER_SHARD_SIZE;
//    private IndicesOptions indicesOptions;


    public SearchRequest(String index) {
        addIndice(index);
    }

    public SearchRequest(List<String> indices) {
        this.indices = indices;
    }

    public SearchRequest(String index, String type) {
        this(index);
        addType(type);
    }

    public SearchRequest(List<String> indices, List<String> types) {
        this(indices);
        this.types = types;
    }

    public SearchRequest(SearchRequest other) {
        super(other);
        indices = other.indices;
        types = other.types;
        routing = other.routing;
        preference = other.preference;
        source = other.source;
        searchType = other.searchType;
        keepAlive = other.keepAlive;
        batchedReduceSize = other.batchedReduceSize;
        preFilterShardSize = other.preFilterShardSize;
//        indicesOptions = other.indicesOptions;
    }

    /**
     * Build a new <code>SearchRequest</code> from a <code>JsonObject</code>,
     * calling parent constructor.
     *
     * @param json the <code>JsonObject</code>
     */
    public SearchRequest(JsonObject json) {
        super(json);
        SearchRequestConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>SearchRequest</code> into a <code>JsonObject</code>,
     * calling parent <code>toJson</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        SearchRequestConverter.toJson(this, json);
        return json;
    }

    /**
     * Adds an indice to the request
     *
     * @param indice the indice
     * @return the request
     */
    private SearchRequest addIndice(String indice) {
        safeIndices().add(indice);
        return this;
    }

    public List<String> getIndices() {
        return safeIndices();
    }

    /**
     * Get safely the indices list
     *
     * @return the list
     */
    private List<String> safeIndices() {
        if (indices == null) {
            indices = new ArrayList<>();
        }
        return indices;
    }

    /**
     * Adds a type to the request
     *
     * @param type the type
     * @return the request
     */
    private SearchRequest addType(String type) {
        safeTypes().add(type);
        return this;
    }

    public List<String> getTypes() {
        return safeTypes();
    }

    /**
     * Get safely the types list
     *
     * @return the types lsit
     */
    private List<String> safeTypes() {
        if (types == null) {
            types = new ArrayList<>();
        }

        return types;
    }

    public JsonObject getSource() {
        return safeSource();
    }

    private JsonObject safeSource() {
        if (source == null) {
            source = new JsonObject();
        }

        return source;
    }

}
