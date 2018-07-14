package fr.myprysm.vertx.elasticsearch;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * HttpHost.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@DataObject(generateConverter = true)
public class HttpHost {
    /**
     * Default protocol.
     */
    public static final String DEFAULT_PROTOCOL = "http";

    /**
     * Default port.
     */
    public static final Integer DEFAULT_PORT = 9200;

    /**
     * Default host.
     */
    public static final String DEFAULT_HOSTNAME = "localhost";

    /**
     * Protocol.
     */
    private String protocol = DEFAULT_PROTOCOL;

    /**
     * Port.
     */
    private Integer port = DEFAULT_PORT;

    /**
     * Hostname.
     */
    private String hostname = DEFAULT_HOSTNAME;


    /**
     * Build a new HttpHost from another.
     *
     * @param other the other
     */
    public HttpHost(HttpHost other) {
        protocol = other.protocol;
        port = other.port;
        hostname = other.hostname;
    }

    /**
     * Build a new <code>HttpHost</code> from a <code>JsonObject</code>.
     *
     * @param json the <code>JsonObject</code>
     */
    public HttpHost(JsonObject json) {
        HttpHostConverter.fromJson(json, this);
    }

    /**
     * Transforms the <code>HttpHost</code> into a <code>JsonObject</code>.
     *
     * @return the <code>JsonObject</code>
     */
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        HttpHostConverter.toJson(this, json);
        return json;
    }
}
