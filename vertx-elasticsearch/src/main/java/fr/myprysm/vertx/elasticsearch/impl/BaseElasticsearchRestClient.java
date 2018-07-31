package fr.myprysm.vertx.elasticsearch.impl;

import fr.myprysm.vertx.elasticsearch.ClusterClient;
import fr.myprysm.vertx.elasticsearch.ElasticsearchClient;
import fr.myprysm.vertx.elasticsearch.ElasticsearchClientOptions;
import fr.myprysm.vertx.elasticsearch.IndicesClient;
import fr.myprysm.vertx.elasticsearch.action.BaseRequest;
import fr.myprysm.vertx.elasticsearch.action.main.MainConverters;
import fr.myprysm.vertx.elasticsearch.action.main.MainResponse;
import fr.myprysm.vertx.elasticsearch.converter.CommonConverters;
import fr.myprysm.vertx.elasticsearch.converter.ConverterUtils;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.shareddata.LocalMap;
import io.vertx.core.shareddata.Shareable;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

import java.util.List;

import static fr.myprysm.vertx.elasticsearch.utils.ExceptionUtils.swallowException;
import static java.util.Objects.requireNonNull;

/**
 * Base {@link ElasticsearchClient} for subclassing.
 */
@Slf4j
public abstract class BaseElasticsearchRestClient extends BaseRestClient implements ElasticsearchClient {
    /**
     * The client holder cache.
     */
    static final String DS_LOCAL_MAP_NAME = "__myprysm.ElasticsearchClient.clients";

    /**
     * The Elasticsearch client.
     */
    private final RestHighLevelClient client;

    /**
     * The client holder.
     */
    private final ClientHolder holder;

    /**
     * The indices client.
     */
    private final IndicesClient indicesClient;

    /**
     * The cluster client.
     */
    private final ClusterClient clusterClient;

    /**
     * Build a client with the provided options and client name.
     *
     * @param vertx      the current vertx instance
     * @param options    the options
     * @param clientName the client name
     */
    BaseElasticsearchRestClient(Vertx vertx, ElasticsearchClientOptions options, String clientName) {
        super(vertx, clientName);
        requireNonNull(options);
        requireNonNull(clientName);
        this.holder = lookupHolder(clientName, options);
        this.client = holder.client();
        Pair<IndicesClient, ClusterClient> clients = getClients();
        indicesClient = clients.getLeft();
        clusterClient = clients.getRight();
    }

    /**
     * Get the indices and cluster clients.
     *
     * @return the clients
     */
    abstract Pair<IndicesClient, ClusterClient> getClients();

    @Override
    public final IndicesClient indices() {
        return indicesClient;
    }

    @Override
    public final ClusterClient cluster() {
        return clusterClient;
    }

    /**
     * Get the client.
     *
     * @return the Elasticsearch client
     */
    final RestHighLevelClient client() {
        return client;
    }

    @Override
    public void ping(BaseRequest request, Handler<AsyncResult<Boolean>> handler) {
        executeSimpleRequestBlocking(request, resp -> resp, handler, client::ping);
    }

    @Override
    public void ping(Handler<AsyncResult<Boolean>> handler) {
        ping(new BaseRequest(), handler);
    }

    @Override
    public void info(BaseRequest request, Handler<AsyncResult<MainResponse>> handler) {
        executeSimpleRequestBlocking(request, MainConverters::responseToDataObject, handler, client::info);
    }

    @Override
    public void info(Handler<AsyncResult<MainResponse>> handler) {
        info(new BaseRequest(), handler);
    }

    @Override
    public void close() {
        holder.close();
    }


    //region Shared clients

    /**
     * Lookup a client holder from the name.
     * <p>
     * When no holder is found, a new one is created and registered in vertx shared data.
     *
     * @param clientName the client name
     * @param config     the client config
     * @return the client holder
     */
    private ClientHolder lookupHolder(String clientName, ElasticsearchClientOptions config) {
        synchronized (vertx()) {
            LocalMap<String, ClientHolder> map = vertx().sharedData().getLocalMap(DS_LOCAL_MAP_NAME);
            ClientHolder theHolder = map.get(clientName);
            if (theHolder == null) {
                log.debug("Creating client [{}]", clientName);
                theHolder = new ClientHolder(config, () -> removeFromMap(map, clientName));
                map.put(clientName, theHolder);
            } else {
                log.debug("Reusing client [{}]", clientName);
                theHolder.incRefCount();
            }
            return theHolder;
        }
    }

    /**
     * Remove a client holder from the shared data.
     *
     * @param map        the cache map
     * @param clientName the client name
     */
    private void removeFromMap(LocalMap<String, ClientHolder> map, String clientName) {
        synchronized (vertx()) {
            map.remove(clientName);
            if (map.isEmpty()) {
                map.close();
            }
        }
    }

    /**
     * Client holder that stays up until all {@link ElasticsearchClient} configured with the same client name are stopped.
     * <p>
     * Keeps internal tracking of the references.
     */
    static class ClientHolder implements Shareable {
        /**
         * The Elasticsearch client.
         */
        private RestHighLevelClient client;

        /**
         * The client options.
         */
        private final ElasticsearchClientOptions config;

        /**
         * Close hook.
         */
        private final Runnable closeRunner;

        /**
         * The number of remaining clients.
         */
        private int refCount = 1;

        /**
         * Build a new holder with the options and the close hook.
         * <p>
         * Hook can be null.
         *
         * @param config      the client config
         * @param closeRunner the close hook
         */
        ClientHolder(ElasticsearchClientOptions config, Runnable closeRunner) {
            this.config = config;
            this.closeRunner = closeRunner;
        }

        /**
         * Get the Elasticsearch client.
         * <p>
         * Initializes it if not already done.
         *
         * @return the client.
         */
        synchronized RestHighLevelClient client() {
            if (client == null) {
                List<HttpHost> hosts = ConverterUtils.convert(config.getHosts(), CommonConverters::httpHostToES);
                RestClientBuilder builder = RestClient
                        .builder(hosts.toArray(new HttpHost[]{}))
                        .setMaxRetryTimeoutMillis(config.getMaxRetryTimeout());

                if (config.getDefaultHeaders().size() > 0) {
                    builder.setDefaultHeaders(CommonConverters.headersFromMap(config.getDefaultHeaders()));
                }
                if (config.getPathPrefix() != null) {
                    builder.setPathPrefix(config.getPathPrefix());
                }

                client = new RestHighLevelClient(builder);
                log.info("Built new client");
            }
            return client;
        }

        /**
         * Get the client configuration.
         *
         * @return the configuration
         */
        ElasticsearchClientOptions config() {
            return new ElasticsearchClientOptions(config);
        }

        /**
         * Increase the number of opened clients.
         */
        synchronized void incRefCount() {
            refCount++;
        }

        /**
         * Close the reference.
         * <p>
         * Elasticsearch client stays up until no other client uses it.
         */
        synchronized void close() {
            if (--refCount == 0) {
                if (client != null) {
                    swallowException(() -> client.close());
                }
                if (closeRunner != null) {
                    closeRunner.run();
                }
            }
        }
    }


    //endregion
}
