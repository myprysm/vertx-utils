package fr.myprysm.vertx.elasticsearch.converter;

import fr.myprysm.vertx.elasticsearch.HttpHost;
import fr.myprysm.vertx.elasticsearch.action.BaseRequest;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import java.util.Map;

import static java.util.Objects.requireNonNull;

/**
 * Common converters.
 */
public interface CommonConverters {


    /**
     * Transforms an Object into a Boolean whenever possible.
     * <p>
     * Returns <code>null</code> otherwise.
     *
     * @param object the object
     * @return the boolean
     */
    static Boolean objectToBoolean(Object object) {
        if (object instanceof Boolean) {
            return (Boolean) object;
        } else if (object instanceof String) {
            return BooleanUtils.toBoolean((String) object);
        } else if (object instanceof Number) {
            return BooleanUtils.toBoolean(((Number) object).intValue());
        } else {
            return null;
        }
    }


    /**
     * Extracts the headers from a request.
     *
     * @param request the request
     * @return the headers
     */
    static Header[] headersFromRequest(BaseRequest request) {
        requireNonNull(request);

        Map<String, String> headers = request.getHeaders();
        if (headers == null) {
            return new Header[]{};
        }

        return headersFromMap(headers);
    }

    /**
     * Transforms a Map into a headers array.
     *
     * @param map the map
     * @return the headers array.
     */
    static Header[] headersFromMap(Map<String, String> map) {
        requireNonNull(map);
        return map.entrySet().stream().map(entry -> new BasicHeader(entry.getKey(), entry.getValue())).toArray(Header[]::new);
    }


    /**
     * Transforms an HttpHost DataObject into an HttpHost.
     *
     * @param host the data object host
     * @return the host
     */
    static org.apache.http.HttpHost httpHostToES(HttpHost host) {
        org.apache.http.HttpHost httpHost;
        if (host.getPort() == null && host.getProtocol() == null) {
            httpHost = org.apache.http.HttpHost.create(host.getHostname());
        } else {
            if (StringUtils.isNotBlank(host.getProtocol())) {
                httpHost = new org.apache.http.HttpHost(host.getHostname(), host.getPort(), host.getProtocol());
            } else {
                httpHost = new org.apache.http.HttpHost(host.getHostname(), host.getPort());
            }
        }

        return httpHost;
    }


}
