package fr.myprysm.vertx.elasticsearch.integration;

import com.codahale.metrics.SharedMetricRegistries;
import fr.myprysm.vertx.elasticsearch.ElasticsearchClientOptions;
import fr.myprysm.vertx.elasticsearch.HttpHost;
import fr.myprysm.vertx.elasticsearch.VertxESTestCase;
import fr.myprysm.vertx.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import fr.myprysm.vertx.elasticsearch.reactivex.ElasticsearchClient;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.dropwizard.impl.Helper;
import me.xdrop.jrand.Generator;
import me.xdrop.jrand.JRand;
import me.xdrop.jrand.model.Range;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public abstract class VertxESIntegrationTestCase extends VertxESTestCase {

    private static final String TEST_CLIENT_NAME = "vertx-elasticsearch-integration";
    private ElasticsearchClient nativeClient;
    private static fr.myprysm.vertx.elasticsearch.ElasticsearchClient _nativeAsync;
    private static fr.myprysm.vertx.elasticsearch.ElasticsearchClient _vertxAsync;
    private ElasticsearchClient vertxClient;


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

    /**
     * Can be overridden to provide data/config to elasticsearch during <code>@BeforeEach</code>, right after the reset.
     */
    void initES() {
        //
    }

    /**
     * Generates a new random integer in the range (inclusive).
     *
     * @param minInclusive the minimum
     * @param maxInclusive the maximum
     * @return the value
     */
    int randomIntBetween(int minInclusive, int maxInclusive) {
        return JRand.natural().range(minInclusive, maxInclusive).gen();
    }

    /**
     * Generates a new random long in the range (inclusive).
     *
     * @param minInclusive the minimum
     * @param maxInclusive the maximum
     * @return the value
     */
    long randomLongBetween(long minInclusive, long maxInclusive) {
        return new LongGenerator().range(minInclusive, maxInclusive).gen();
    }

    /**
     * Generates a new random float in the range (inclusive).
     *
     * @param minInclusive the minimum
     * @param maxInclusive the maximum
     * @return the value
     */
    float randomFloatBetween(float minInclusive, float maxInclusive) {
        return JRand.flt().range(minInclusive, maxInclusive).gen();
    }

    /**
     * Generates a new random double in the range (inclusive).
     *
     * @param minInclusive the minimum
     * @param maxInclusive the maximum
     * @return the value
     */
    double randomDoubleBetween(double minInclusive, double maxInclusive) {
        return JRand.dbl().range(minInclusive, maxInclusive).gen();
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
    public static void tearDownClients() {
        _nativeAsync.close();
        _vertxAsync.close();
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

    static class LongGenerator extends Generator<Long> {
        static final ThreadLocalRandom random = ThreadLocalRandom.current();

        private long min;
        private long max;

        public LongGenerator() {
            this.max = Long.MAX_VALUE - 1;
            this.min = 0;
        }

        /**
         * Set the minimum value (inclusive)
         *
         * @param min The minimum value
         * @return The same generator
         */
        public LongGenerator min(long min) {
            this.min = min;
            return this;
        }

        /**
         * Set the maximum value to return (inclusive)
         *
         * @param max The maximum value to return (inclusive)
         * @return The same generator
         */
        public LongGenerator max(long max) {
            this.max = max;
            return this;
        }

        /**
         * Set a min/max range
         *
         * @param min Minimum value to be returned (inclusive)
         * @param max Maximum value to be returned (inclusive)
         * @return The same generator
         */
        public LongGenerator range(long min, long max) {
            min(min);
            max(max);
            return this;
        }

        /**
         * Set a range starting from 0
         *
         * @param max Maximum value to be returned (inclusive)
         * @return The same generator
         */
        public LongGenerator range(long max) {
            min(0);
            max(max - 1);
            return this;
        }

        public LongGenerator range(Range<Long> rangeOption) {
            min(rangeOption.getMin());
            max(rangeOption.getMax());
            return this;
        }

        @Override
        public Long gen() {
            return random.nextLong(min, max);
        }
    }
}
