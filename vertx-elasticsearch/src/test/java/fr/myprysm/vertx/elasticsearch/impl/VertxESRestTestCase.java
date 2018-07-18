package fr.myprysm.vertx.elasticsearch.impl;

import com.codahale.metrics.SharedMetricRegistries;
import fr.myprysm.vertx.elasticsearch.ElasticsearchClientOptions;
import fr.myprysm.vertx.elasticsearch.HttpHost;
import fr.myprysm.vertx.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import fr.myprysm.vertx.elasticsearch.reactivex.ElasticsearchClient;
import fr.myprysm.vertx.test.VertxTest;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.dropwizard.impl.Helper;
import me.xdrop.jrand.JRand;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public abstract class VertxESRestTestCase implements VertxTest {

    private static final String TEST_CLIENT_NAME = "vertx-elasticsearch-integration";
    private static Vertx vertx;
    private ElasticsearchClient nativeClient;
    private static fr.myprysm.vertx.elasticsearch.ElasticsearchClient _nativeAsync;
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
    public void initElasticSearchClient() throws InterruptedException {
        if (nativeClient == null) {
            ElasticsearchClientOptions options = new ElasticsearchClientOptions().setHosts(getHosts());

            _nativeAsync = fr.myprysm.vertx.elasticsearch.ElasticsearchClient.createShared(vertx, options, TEST_CLIENT_NAME);
            nativeClient = new ElasticsearchClient(_nativeAsync);

            options.setUseNativeAsyncAPI(false);
            _vertxAsync = fr.myprysm.vertx.elasticsearch.ElasticsearchClient.createShared(vertx, options, TEST_CLIENT_NAME);
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

    private void resetES() throws InterruptedException {
        rxClient()
                .indices()
                .rxDelete(new DeleteIndexRequest("_all"))
                .test()
                .await()
                .assertNoErrors();
    }

    private List<HttpHost> getHosts() {
        HttpHost host = new HttpHost();
        String rawHost = System.getProperty("es.host");
        if (StringUtils.isNotBlank(rawHost)) {
            host.setHostname(rawHost);
            String rawPort = System.getProperty("es.port");
            if (NumberUtils.isCreatable(rawPort)) {
                Integer port = NumberUtils.createInteger(rawPort);
                if (port != null && port > 0 && port < 65536) {
                    host.setPort(port);
                }
            }
        }

        return Collections.singletonList(host);
    }

    ElasticsearchClient rxClient() {
        return randomBoolean() ? nativeClient : vertxClient;
    }

    @AfterAll
    public static void tearDownVertx() {
        _nativeAsync.close();
        _vertxAsync.close();
        vertx.close();
        RxJavaPlugins.reset();
    }

    @AfterAll
    public static void logClientMetrics() {
        Map<String, Object> map = SharedMetricRegistries.getOrCreate(TEST_CLIENT_NAME)
                .getMetrics()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> Helper.convertMetric(e.getValue(), TimeUnit.SECONDS, TimeUnit.MILLISECONDS)));
        System.out.println(new JsonObject(map));
    }
}
