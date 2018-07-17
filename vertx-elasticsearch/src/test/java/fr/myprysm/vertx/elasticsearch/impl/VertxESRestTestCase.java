package fr.myprysm.vertx.elasticsearch.impl;

import fr.myprysm.vertx.elasticsearch.reactivex.ElasticsearchClient;
import fr.myprysm.vertx.test.VertxTest;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import io.vertx.core.Vertx;
import me.xdrop.jrand.JRand;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

public abstract class VertxESRestTestCase implements VertxTest {

    private static Vertx vertx;
    private ElasticsearchClient nativeClient;
    private static fr.myprysm.vertx.elasticsearch.ElasticsearchClient _nativeAsync;
    private RestHighLevelClient esClient;
    private static fr.myprysm.vertx.elasticsearch.ElasticsearchClient _vertxAsync;
    private ElasticsearchClient vertxClient;

    @BeforeAll
    public static void setUpVertx() {
        vertx = Vertx.vertx();
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxJavaPlugins.setComputationSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxJavaPlugins.setNewThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
    }

    @BeforeEach
    public void initElasticSearchClient() throws IOException, InterruptedException {
        if (nativeClient == null) {
            esClient = new RestHighLevelClient(RestClient.builder(getHosts()));
            BaseElasticsearchRestClient.ClientHolder holder = new CustomHolder(esClient);

            _nativeAsync = ElasticsearchClientFactory.create(vertx, true, holder);
            nativeClient = new ElasticsearchClient(_nativeAsync);

            holder.incRefCount();
            _vertxAsync = ElasticsearchClientFactory.create(vertx, false, holder);
            vertxClient = new ElasticsearchClient(_vertxAsync);
        }

        long start = System.currentTimeMillis();
        resetES();
        long stopReset = System.currentTimeMillis();
        initES();
        long stopInit = System.currentTimeMillis();

        System.out.println(String.format("%dms to reset indexes, %dms to init Elasticsearch, %dms total",
                stopReset - start,
                stopInit - stopReset,
                stopInit - start));
    }

    void initES() throws InterruptedException {

    }

    /**
     * Generates a new random in the range (inclusive).
     *
     * @param minInclusive the minimum
     * @param maxInclusive the maximum
     * @return the value
     */
    int randomIntBetween(int minInclusive, int maxInclusive) {
        return JRand.natural().range(minInclusive, maxInclusive).gen();
    }

    /**
     * Generates a new boolean.
     *
     * @return the value
     */
    boolean randomBoolean() {
        return JRand.bool().gen();
    }

    @SuppressWarnings("unchecked")
    <T> T randomFrom(T[] array) {
        return array[randomIntBetween(0, array.length - 1)];
    }

    private void resetES() throws IOException {
        esClient.indices().delete(new DeleteIndexRequest().indices("_all"));
    }

    private HttpHost[] getHosts() {
        HttpHost host = new HttpHost("localhost", 9200);
        String rawHost = System.getProperty("es.host");
        if (StringUtils.isNotBlank(rawHost)) {
            String rawPort = System.getProperty("es.port");
            if (NumberUtils.isCreatable(rawPort)) {
                Integer port = NumberUtils.createInteger(rawPort);
                if (port != null && port > 0 && port < 65536) {
                    host = new HttpHost(rawHost, port);
                } else {
                    host = new HttpHost(rawHost, 9200);
                }
            }
        }

        return new HttpHost[]{host};
    }

    ElasticsearchClient rxClient() {
        return randomBoolean() ? nativeClient : vertxClient;
    }

    RestHighLevelClient esClient() {
        return esClient;
    }

    @AfterAll
    public static void tearDownVertx() {
        _nativeAsync.close();
        _vertxAsync.close();
        vertx.close();
        RxJavaPlugins.reset();
    }

    static class CustomHolder extends BaseElasticsearchRestClient.ClientHolder {

        CustomHolder(RestHighLevelClient client) {
            super(client);
        }
    }

}
