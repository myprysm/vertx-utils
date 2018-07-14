package fr.myprysm.vertx.elasticsearch;

import fr.myprysm.vertx.validation.JsonValidation;
import fr.myprysm.vertx.validation.ValidationResult;
import io.vertx.core.json.JsonObject;

import static fr.myprysm.vertx.validation.JsonValidation.*;

/**
 * Validation for {@link ElasticsearchClientOptions}.
 */
public interface ElasticsearchClientOptionsValidation {
    /**
     * Minimum exclusive acceptable port.
     */
    Long MIN_PORT = 0L;

    /**
     * Maximum exclusive acceptable port.
     */
    Long MAX_PORT = 65536L;

    /**
     * Validate the json options.
     *
     * @param options the options
     * @return the result
     */
    static ValidationResult validate(JsonObject options) {
        return isNull("useNativeAsyncAPI").or(isBoolean("useNativeAsyncAPI"))
                .and(isNull("pathPrefix").or(isString("pathPrefix")))
                .and(isNull("maxRetryTimeout").or(gt("maxRetryTimeout", 0L)))
                .and(isNull("defaultHeaders").or(mapOf("defaultHeaders", String.class)))
                .and(nestedArray("hosts", validateHost()))
                .apply(options);
    }


    /**
     * Validate an http host.
     * <p>
     * Expects at least an host.
     *
     * @return the chain
     */
    static JsonValidation validateHost() {
        return portInRange()
                .and(protocolValid())
                .and(isString("host"));
    }

    /**
     * Validate the protocol if any.
     *
     * @return the chain
     */
    static JsonValidation protocolValid() {
        return isNull("protocol").or(matches("protocol", "https?"));
    }

    /**
     * Validate that the port is in range if any.
     *
     * @return the chain
     */
    static JsonValidation portInRange() {
        return isNull("port")
                .or(gt("port", MIN_PORT).and(lt("port", MAX_PORT)));
    }
}
