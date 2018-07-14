package fr.myprysm.vertx.elasticsearch.action;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

/**
 * Base DataObject request.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@DataObject(generateConverter = true)
public class BaseRequest {

    /**
     * The headers.
     */
    private Map<String, String> headers;

    /**
     * Build a BaseRequest from another.
     *
     * @param other the other
     */
    public BaseRequest(BaseRequest other) {
        headers = other.headers;
    }

    /**
     * Add a header to the request.
     *
     * @param headerKey   the header key
     * @param headerValue the header value
     * @return the request
     */
    public BaseRequest addHeader(String headerKey, String headerValue) {
        safeHeaders().put(headerKey, headerValue);
        return this;
    }

    /**
     * Get safely the headers in case someone removed them.
     *
     * @return the headers map
     */
    private Map<String, String> safeHeaders() {
        if (headers == null) {
            headers = new HashMap<>();
        }
        return headers;
    }

    /**
     * Build a new <code>BaseRequest</code> from a <code>JsonObject</code>.
     *
     * @param json the <code>JsonObject</code>
     */
    public BaseRequest(JsonObject json) {
        BaseRequestConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>BaseRequest</code> into a <code>JsonObject</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        BaseRequestConverter.toJson(this, json);
        return json;
    }

}
