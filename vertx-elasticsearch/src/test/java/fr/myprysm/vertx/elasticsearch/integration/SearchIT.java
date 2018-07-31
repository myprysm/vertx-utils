/*
 * Copyright 2018 the original author or the original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fr.myprysm.vertx.elasticsearch.integration;

import fr.myprysm.vertx.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import fr.myprysm.vertx.elasticsearch.action.bulk.BulkRequest;
import fr.myprysm.vertx.elasticsearch.action.index.IndexRequest;
import fr.myprysm.vertx.elasticsearch.action.search.ClearScrollRequest;
import fr.myprysm.vertx.elasticsearch.action.search.Clusters;
import fr.myprysm.vertx.elasticsearch.action.search.MultiSearchConverters;
import fr.myprysm.vertx.elasticsearch.action.search.MultiSearchResponseItem;
import fr.myprysm.vertx.elasticsearch.action.search.SearchConverters;
import fr.myprysm.vertx.elasticsearch.action.search.SearchHit;
import fr.myprysm.vertx.elasticsearch.action.search.SearchResponse;
import fr.myprysm.vertx.elasticsearch.action.search.SearchScrollRequest;
import fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.Bucket;
import fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.Children;
import fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.Filter;
import fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.Filters;
import fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.GeoHashGrid;
import fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.Histogram;
import fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.Range;
import fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.RangeBucket;
import fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.Terms;
import fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.TermsBucket;
import fr.myprysm.vertx.elasticsearch.action.search.aggregations.matrix.MatrixStats;
import fr.myprysm.vertx.elasticsearch.action.search.suggest.PhraseEntry;
import fr.myprysm.vertx.elasticsearch.action.search.suggest.PhraseOption;
import fr.myprysm.vertx.elasticsearch.action.search.suggest.PhraseSuggestion;
import fr.myprysm.vertx.elasticsearch.action.search.suggest.Suggestion;
import fr.myprysm.vertx.elasticsearch.action.support.DocWriteRequest;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.search.MultiSearchRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.ScriptQueryBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.join.aggregations.ChildrenAggregationBuilder;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.filter.FilterAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.filter.FiltersAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.filter.FiltersAggregator;
import org.elasticsearch.search.aggregations.bucket.geogrid.GeoGridAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.range.RangeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.matrix.stats.MatrixStatsAggregationBuilder;
import org.elasticsearch.search.aggregations.support.ValueType;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.phrase.PhraseSuggestionBuilder;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.search.aggregations.AggregationBuilders.*;

class SearchIT extends VertxESIntegrationTestCase {

    void initData() throws InterruptedException {
        JsonObject doc1 = new JsonObject("{\"type\":\"type1\", \"num\":10, \"num2\":50}");
        JsonObject doc2 = new JsonObject("{\"type\":\"type1\", \"num\":20, \"num2\":40}");
        JsonObject doc3 = new JsonObject("{\"type\":\"type1\", \"num\":50, \"num2\":35}");
        JsonObject doc4 = new JsonObject("{\"type\":\"type2\", \"num\":100, \"num2\":10}");
        JsonObject doc5 = new JsonObject("{\"type\":\"type2\", \"num\":100, \"num2\":10}");

        rxClient().rxBulk(
                new BulkRequest().setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL)
                        .add(new IndexRequest("index", "type", "1").setSource(doc1))
                        .add(new IndexRequest("index", "type", "2").setSource(doc2))
                        .add(new IndexRequest("index", "type", "3").setSource(doc3))
                        .add(new IndexRequest("index", "type", "4").setSource(doc4))
                        .add(new IndexRequest("index", "type", "5").setSource(doc5))
                        .add(new IndexRequest("index1", "type", "1").setSource(new JsonObject("{\"field\":\"value1\"}")))
                        .add(new IndexRequest("index1", "type", "2").setSource(new JsonObject("{\"field\":\"value2\"}")))
                        .add(new IndexRequest("index2", "type", "3").setSource(new JsonObject("{\"field\":\"value1\"}")))
                        .add(new IndexRequest("index2", "type", "4").setSource(new JsonObject("{\"field\":\"value2\"}")))
                        .add(new IndexRequest("index3", "type", "5").setSource(new JsonObject("{\"field\":\"value1\"}")))
                        .add(new IndexRequest("index3", "type", "6").setSource(new JsonObject("{\"field\":\"value2\"}")))
        )
                .test()
                .await()
                .assertNoErrors();

    }

    @Test
    void testSearchNoQuery() throws InterruptedException {
        initData();
        SearchRequest searchRequest = new SearchRequest("index");
        rxClient().rxSearch(SearchConverters.requestToDataObject(searchRequest))
                .test()
                .await()
                .assertNoErrors()
                .assertValue(searchResponse -> {
                    assertSearchHeader(searchResponse);
                    assertThat(searchResponse.getAggregations()).isNull();
                    assertThat(searchResponse.getSuggest()).isNull();
                    assertThat(searchResponse.getProfileResults()).isNull();
                    assertThat(searchResponse.getHits().getTotalHits()).isEqualTo(5);
                    assertThat(searchResponse.getHits().getHits()).hasSize(5);
                    for (SearchHit searchHit : searchResponse.getHits().getHits()) {
                        assertThat(searchHit.getIndex()).isEqualTo("index");
                        assertThat(searchHit.getType()).isEqualTo("type");
                        assertThat(Integer.valueOf(searchHit.getId())).isBetween(1, 5);
                        assertThat(searchHit.getScore()).isEqualTo(1.0f);
                        assertThat(searchHit.getVersion()).isEqualTo(-1L);
                        assertThat(searchHit.getSource()).isNotNull();
                        assertThat(searchHit.getSource()).hasSize(3);
                        assertThat(searchHit.getSource().getValue("type")).isNotNull();
                        assertThat(searchHit.getSource().getValue("num")).isNotNull();
                        assertThat(searchHit.getSource().getValue("num2")).isNotNull();
                    }
                    return true;
                })
        ;

    }

    @Test
    void testSearchMatchQuery() throws InterruptedException {
        initData();
        SearchRequest searchRequest = new SearchRequest("index");
        searchRequest.source(new SearchSourceBuilder().query(new MatchQueryBuilder("num", 10)));
        rxClient().rxSearch(SearchConverters.requestToDataObject(searchRequest))
                .test()
                .await()
                .assertNoErrors()
                .assertValue(searchResponse -> {
                    assertSearchHeader(searchResponse);
                    assertThat(searchResponse.getAggregations()).isNull();
                    assertThat(searchResponse.getSuggest()).isNull();
                    assertThat(searchResponse.getProfileResults()).isNull();
                    assertThat(searchResponse.getHits().getTotalHits()).isEqualTo(1);
                    assertThat(searchResponse.getHits().getHits()).hasSize(1);
                    assertThat(searchResponse.getHits().getMaxScore()).isGreaterThan(0f);
                    SearchHit searchHit = searchResponse.getHits().getHits().get(0);
                    assertThat(searchHit.getIndex()).isEqualTo("index");
                    assertThat(searchHit.getType()).isEqualTo("type");
                    assertThat(searchHit.getId()).isEqualTo("1");
                    assertThat(searchHit.getScore()).isGreaterThan(0f);
                    assertThat(searchHit.getVersion()).isEqualTo(-1L);
                    assertThat(searchHit.getSource()).isNotNull();
                    assertThat(searchHit.getSource()).hasSize(3);
                    assertThat(searchHit.getSource().getString("type")).isEqualTo("type1");
                    assertThat(searchHit.getSource().getInteger("num2")).isEqualTo(50);
                    return true;
                });
    }

    @Test
    void testSearchWithTermsAgg() throws InterruptedException {
        initData();
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.aggregation(new TermsAggregationBuilder("agg1", ValueType.STRING).field("type.keyword"));
        searchSourceBuilder.size(0);
        searchRequest.source(searchSourceBuilder);
        rxClient().rxSearch(SearchConverters.requestToDataObject(searchRequest))
                .test()
                .await()
                .assertNoErrors()
                .assertValue(searchResponse -> {
                    assertSearchHeader(searchResponse);
                    assertThat(searchResponse.getSuggest()).isNull();
                    assertThat(searchResponse.getProfileResults()).isNull();
                    assertThat(searchResponse.getHits().getHits()).isEmpty();
                    assertThat(searchResponse.getHits().getMaxScore()).isEqualTo(0f);
                    Terms termsAgg = searchResponse.getAggregationByName("agg1");
                    assertThat(termsAgg.getName()).isEqualTo("agg1");
                    assertThat(termsAgg.getType()).isEqualTo("sterms");
                    assertThat(termsAgg.getBuckets()).hasSize(2);

                    TermsBucket bucket = termsAgg.getBucketByKey("type1");
                    assertThat(bucket.getKey()).isEqualTo("type1");
                    assertThat(bucket.getDocCount()).isEqualTo(3);

                    bucket = termsAgg.getBucketByKey("type2");
                    assertThat(bucket.getKey()).isEqualTo("type2");
                    assertThat(bucket.getDocCount()).isEqualTo(2);
                    return true;
                });
    }

    @Test
    void testSearchWithRangeAgg() throws InterruptedException {
        initData();
        {
            SearchRequest searchRequest = new SearchRequest();
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.aggregation(new RangeAggregationBuilder("agg1").field("num"));
            searchSourceBuilder.size(0);
            searchRequest.source(searchSourceBuilder);

            rxClient().rxSearch(SearchConverters.requestToDataObject(searchRequest))
                    .test()
                    .await()
                    .assertError(error -> {
                        assertThat(error).isInstanceOf(ElasticsearchException.class);
                        ElasticsearchException exception = (ElasticsearchException) error;
                        assertThat(exception.status()).isEqualTo(RestStatus.BAD_REQUEST);
                        return true;
                    });

        }

        SearchRequest searchRequest = new SearchRequest("index");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.aggregation(new RangeAggregationBuilder("agg1").field("num")
                .addRange("first", 0, 30).addRange("second", 31, 200));
        searchSourceBuilder.size(0);
        searchRequest.source(searchSourceBuilder);
        rxClient().rxSearch(SearchConverters.requestToDataObject(searchRequest))
                .test()
                .await()
                .assertNoErrors()
                .assertValue(searchResponse -> {
                    assertSearchHeader(searchResponse);
                    assertThat(searchResponse.getSuggest()).isNull();
                    assertThat(searchResponse.getProfileResults()).isNull();
                    assertThat(searchResponse.getHits().getTotalHits()).isEqualTo(5);
                    assertThat(searchResponse.getHits().getHits()).isEmpty();
                    assertThat(searchResponse.getHits().getMaxScore()).isEqualTo(0f);
                    Range rangeAgg = searchResponse.getAggregationByName("agg1");
                    assertThat(rangeAgg.getName()).isEqualTo("agg1");
                    assertThat(rangeAgg.getBuckets()).hasSize(2);
                    {
                        RangeBucket bucket = rangeAgg.getBucketByKey("first");
                        assertThat(bucket.getKey()).isEqualTo("first");
                        assertThat(bucket.getDocCount()).isEqualTo(2);
                    }
                    {
                        RangeBucket bucket = rangeAgg.getBucketByKey("second");
                        assertThat(bucket.getKey()).isEqualTo("second");
                        assertThat(bucket.getDocCount()).isEqualTo(3);
                    }
                    return true;
                });
    }

    @Test
    void testSearchWithTermsAndRangeAgg() throws InterruptedException {
        initData();
        SearchRequest searchRequest = new SearchRequest("index");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        TermsAggregationBuilder agg = new TermsAggregationBuilder("agg1", ValueType.STRING).field("type.keyword");
        agg.subAggregation(new RangeAggregationBuilder("subagg").field("num")
                .addRange("first", 0, 30).addRange("second", 31, 200));
        searchSourceBuilder.aggregation(agg);
        searchSourceBuilder.size(0);
        searchRequest.source(searchSourceBuilder);
        rxClient().rxSearch(SearchConverters.requestToDataObject(searchRequest))
                .test()
                .await()
                .assertNoErrors()
                .assertValue(searchResponse -> {
                    assertSearchHeader(searchResponse);
                    assertThat(searchResponse.getSuggest()).isNull();
                    assertThat(searchResponse.getProfileResults()).isNull();
                    assertThat(searchResponse.getHits().getHits()).isEmpty();
                    assertThat(searchResponse.getHits().getMaxScore()).isEqualTo(0f);
                    Terms termsAgg = searchResponse.getAggregationByName("agg1");
                    assertThat(termsAgg.getName()).isEqualTo("agg1");
                    assertThat(termsAgg.getBuckets()).hasSize(2);

                    TermsBucket type1 = termsAgg.getBucketByKey("type1");
                    assertThat(type1.getKey()).isEqualTo("type1");
                    assertThat(type1.getDocCount()).isEqualTo(3L);
                    assertThat(type1.getAggregations()).hasSize(1);
                    {
                        Range rangeAgg = type1.getAggregationByName("subagg");
                        assertThat(rangeAgg.getBuckets()).hasSize(2);
                        {
                            RangeBucket bucket = rangeAgg.getBucketByKey("first");
                            assertThat(bucket.getKey()).isEqualTo("first");
                            assertThat(bucket.getDocCount()).isEqualTo(2);
                        }
                        {
                            RangeBucket bucket = rangeAgg.getBucketByKey("second");
                            assertThat(bucket.getKey()).isEqualTo("second");
                            assertThat(bucket.getDocCount()).isEqualTo(1);
                        }
                    }

                    TermsBucket type2 = termsAgg.getBucketByKey("type2");
                    assertThat(type2.getKey()).isEqualTo("type2");
                    assertThat(type2.getDocCount()).isEqualTo(2L);
                    assertThat(type2.getAggregations()).hasSize(1);
                    {
                        Range rangeAgg = type2.getAggregationByName("subagg");
                        assertThat(rangeAgg.getBuckets()).hasSize(2);
                        {
                            RangeBucket bucket = rangeAgg.getBucketByKey("first");
                            assertThat(bucket.getKey()).isEqualTo("first");
                            assertThat(bucket.getDocCount()).isEqualTo(0);
                        }
                        {
                            RangeBucket bucket = rangeAgg.getBucketByKey("second");
                            assertThat(bucket.getKey()).isEqualTo("second");
                            assertThat(bucket.getDocCount()).isEqualTo(2);
                        }
                    }
                    return true;
                });
    }

    @Test
    void testSearchWithMatrixStats() throws InterruptedException {
        initData();
        SearchRequest searchRequest = new SearchRequest("index");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.aggregation(new MatrixStatsAggregationBuilder("agg1").fields(Arrays.asList("num", "num2")));
        searchSourceBuilder.size(0);
        searchRequest.source(searchSourceBuilder);
        rxClient().rxSearch(SearchConverters.requestToDataObject(searchRequest))
                .test()
                .await()
                .assertNoErrors()
                .assertValue(searchResponse -> {
                    assertSearchHeader(searchResponse);
                    assertThat(searchResponse.getSuggest()).isNull();
                    assertThat(searchResponse.getProfileResults()).isNull();
                    assertThat(searchResponse.getHits().getTotalHits()).isEqualTo(5L);
                    assertThat(searchResponse.getHits().getHits()).isEmpty();
                    assertThat(searchResponse.getHits().getMaxScore()).isEqualTo(0F);
                    assertThat(searchResponse.getAggregations()).hasSize(1);
                    MatrixStats matrixStats = searchResponse.getAggregationByName("agg1");
                    assertThat(matrixStats.getFieldCount("num")).isEqualTo(5L);
                    assertThat(matrixStats.getMean("num")).isEqualTo(56D);
                    assertThat(matrixStats.getVariance("num")).isEqualTo(1830D);
                    assertThat(matrixStats.getSkewness("num")).isEqualTo(0.09340198804973057D);
                    assertThat(matrixStats.getKurtosis("num")).isEqualTo(1.2741646510794589D);
                    assertThat(matrixStats.getFieldCount("num2")).isEqualTo(5L);
                    assertThat(matrixStats.getMean("num2")).isEqualTo(29D);
                    assertThat(matrixStats.getVariance("num2")).isEqualTo(330D);
                    assertThat(matrixStats.getSkewness("num2")).isEqualTo(-0.13568039346585542D);
                    assertThat(matrixStats.getKurtosis("num2")).isEqualTo(1.3517561983471074D);
                    assertThat(matrixStats.getCovariance("num", "num2")).isEqualTo(-767.5D);
                    assertThat(matrixStats.getCorrelation("num", "num2")).isEqualTo(-0.9876336291667923D);
                    return true;
                });
    }


    @Test
    void testSearchWithGeoHashGrid() throws InterruptedException {
        final String indexName = "museums";

        JsonObject geoMapping = new JsonObject("{\n" +
                "            \"properties\": {\n" +
                "                \"location\": {\n" +
                "                    \"type\": \"geo_point\"\n" +
                "                }\n" +
                "            }\n" +
                "        }");

        rxClient().indices().rxCreate(new CreateIndexRequest(indexName).addMapping("doc", geoMapping))
                .test()
                .await()
                .assertNoErrors();
        JsonObject museum1 = new JsonObject("{\"location\": \"52.374081,4.912350\", \"name\": \"NEMO Science Museum\"}");
        JsonObject museum2 = new JsonObject("{\"location\": \"52.369219,4.901618\", \"name\": \"Museum Het Rembrandthuis\"}");
        JsonObject museum3 = new JsonObject("{\"location\": \"52.371667,4.914722\", \"name\": \"Nederlands Scheepvaartmuseum\"}");
        JsonObject museum4 = new JsonObject("{\"location\": \"51.222900,4.405200\", \"name\": \"Letterenhuis\"}");
        JsonObject museum5 = new JsonObject("{\"location\": \"48.861111,2.336389\", \"name\": \"Musée du Louvre\"}");
        JsonObject museum6 = new JsonObject("{\"location\": \"48.860000,2.327000\", \"name\": \"Musée d'Orsay\"}");

        rxClient().rxBulk(new BulkRequest()
                .setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL)
                .add(new IndexRequest(indexName, "doc", "1").setSource(museum1))
                .add(new IndexRequest(indexName, "doc", "2").setSource(museum2))
                .add(new IndexRequest(indexName, "doc", "3").setSource(museum3))
                .add(new IndexRequest(indexName, "doc", "4").setSource(museum4))
                .add(new IndexRequest(indexName, "doc", "5").setSource(museum5))
                .add(new IndexRequest(indexName, "doc", "6").setSource(museum6))
        )
                .test()
                .await()
                .assertNoErrors();

        {
            SearchRequest searchRequest = new SearchRequest(indexName);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.aggregation(new GeoGridAggregationBuilder("large-grid").field("location").precision(3));
            searchSourceBuilder.size(0);
            searchRequest.source(searchSourceBuilder);
            rxClient().rxSearch(SearchConverters.requestToDataObject(searchRequest))
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(searchResponse -> {
                        assertSearchHeader(searchResponse);
                        assertThat(searchResponse.getSuggest()).isNull();
                        assertThat(searchResponse.getProfileResults()).isNull();
                        assertThat(searchResponse.getHits().getTotalHits()).isEqualTo(6L);
                        assertThat(searchResponse.getHits().getHits()).isEmpty();
                        assertThat(searchResponse.getHits().getMaxScore()).isEqualTo(0F);
                        assertThat(searchResponse.getAggregations()).hasSize(1);
                        GeoHashGrid geoAgg = searchResponse.getAggregationByName("large-grid");
                        assertThat(geoAgg.getBuckets()).isNotNull();
                        assertThat(geoAgg.getBuckets()).hasSize(3);

                        for (Bucket bucket : geoAgg.getBuckets().values()) {
                            assertThat(bucket.getKey()).isIn("u17", "u09", "u15");
                            assertThat(bucket.getDocCount()).isBetween(1L, 3L);
                            assertThat(bucket.getAggregations()).isNull();
                        }

                        return true;
                    });
        }
        {
            SearchRequest searchRequest = new SearchRequest(indexName);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            FilterAggregationBuilder filter = new FilterAggregationBuilder(
                    "zoomed-in",
                    QueryBuilders.geoBoundingBoxQuery("location").setCorners(new GeoPoint(52.4, 4.9), new GeoPoint(52.3, 5.0))
            );
            filter.subAggregation(new GeoGridAggregationBuilder("zoom1").field("location").precision(8));
            searchSourceBuilder.aggregation(filter);
            searchSourceBuilder.size(0);
            searchRequest.source(searchSourceBuilder);
            rxClient().rxSearch(SearchConverters.requestToDataObject(searchRequest))
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(searchResponse -> {
                        assertSearchHeader(searchResponse);
                        assertThat(searchResponse.getSuggest()).isNull();
                        assertThat(searchResponse.getProfileResults()).isNull();
                        assertThat(searchResponse.getHits().getTotalHits()).isEqualTo(6L);
                        assertThat(searchResponse.getHits().getHits()).isEmpty();
                        assertThat(searchResponse.getHits().getMaxScore()).isEqualTo(0F);
                        assertThat(searchResponse.getAggregations()).hasSize(1);
                        Filter filterAgg = searchResponse.getAggregationByName("zoomed-in");
                        assertThat(filterAgg.getDocCount()).isEqualTo(3L);
                        assertThat(filterAgg.getAggregations()).isNotNull();
                        assertThat(filterAgg.getAggregations()).hasSize(1);
                        GeoHashGrid geoAgg = filterAgg.getAggregationByName("zoom1");
                        assertThat(geoAgg.getBuckets()).isNotNull();
                        assertThat(geoAgg.getBuckets()).hasSize(3);

                        for (Bucket bucket : geoAgg.getBuckets().values()) {
                            assertThat(bucket.getKey()).isIn("u173zy3j", "u173zvfz", "u173zt90");
                            assertThat(bucket.getDocCount()).isEqualTo(1L);
                            assertThat(bucket.getAggregations()).isNull();
                        }

                        return true;
                    });
        }
    }

    @Test
    void testSearchWithHistogram() throws InterruptedException {
        final String indexName = "products";
        int docCount = randomIntBetween(10, 100);
        AtomicLong zero = new AtomicLong();
        AtomicLong five = new AtomicLong();
        AtomicLong ten = new AtomicLong();
        AtomicLong fifteen = new AtomicLong();
        AtomicLong twenty = new AtomicLong();
        AtomicLong twentyFive = new AtomicLong();
        AtomicLong thirty = new AtomicLong();
        BulkRequest bulkRequest = new BulkRequest().setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
        for (int id = 1; id <= docCount; id++) {
            int price = randomIntBetween(5, 30);

            if (price < 5) {
                zero.getAndIncrement();
            } else if (price < 10) {
                five.getAndIncrement();
            } else if (price < 15) {
                ten.getAndIncrement();
            } else if (price < 20) {
                fifteen.getAndIncrement();
            } else if (price < 25) {
                twenty.getAndIncrement();
            } else if (price < 30) {
                twentyFive.getAndIncrement();
            } else if (price < 35) {
                thirty.getAndIncrement();
            }

            bulkRequest.add(new IndexRequest(indexName, "doc", String.valueOf(id)).setSource(new JsonObject().put("price", price)));
        }


        rxClient().rxBulk(bulkRequest)
                .test()
                .await()
                .assertNoErrors();

        {
            SearchRequest searchRequest = new SearchRequest(indexName);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.aggregation(histogram("prices").field("price").interval(5.0D));
            searchSourceBuilder.size(0);
            searchRequest.source(searchSourceBuilder);
            rxClient().rxSearch(SearchConverters.requestToDataObject(searchRequest))
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(searchResponse -> {
                        assertSearchHeader(searchResponse);
                        assertThat(searchResponse.getSuggest()).isNull();
                        assertThat(searchResponse.getProfileResults()).isNull();
                        assertThat(searchResponse.getHits().getTotalHits()).isEqualTo(docCount);
                        assertThat(searchResponse.getHits().getHits()).isEmpty();
                        assertThat(searchResponse.getHits().getMaxScore()).isEqualTo(0F);
                        assertThat(searchResponse.getAggregations()).hasSize(1);
                        Histogram histogram = searchResponse.getAggregationByName("prices");
                        assertThat(histogram.getBuckets()).isNotNull();
                        assertThat(histogram.getBuckets().size()).isBetween(1, 6);

                        for (Bucket bucket : histogram.getBuckets().values()) {
                            assertThat(bucket.getKey()).isIn("5.0", "10.0", "15.0", "20.0", "25.0", "30.0");
                            assertThat(bucket.getDocCount()).isIn(
                                    five.get(),
                                    ten.get(),
                                    fifteen.get(),
                                    twenty.get(),
                                    twentyFive.get(),
                                    thirty.get());
                            assertThat(bucket.getAggregations()).isNull();
                        }

                        return true;
                    });
        }

        {
            SearchRequest searchRequest = new SearchRequest(indexName);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.aggregation(histogram("prices").field("price").interval(5.0D).minDocCount(0L).extendedBounds(0.0D, 500.0D));
            searchSourceBuilder.size(0);
            searchRequest.source(searchSourceBuilder);
            rxClient().rxSearch(SearchConverters.requestToDataObject(searchRequest))
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(searchResponse -> {
                        assertSearchHeader(searchResponse);
                        assertThat(searchResponse.getSuggest()).isNull();
                        assertThat(searchResponse.getProfileResults()).isNull();
                        assertThat(searchResponse.getHits().getTotalHits()).isEqualTo(docCount);
                        assertThat(searchResponse.getHits().getHits()).isEmpty();
                        assertThat(searchResponse.getHits().getMaxScore()).isEqualTo(0F);
                        assertThat(searchResponse.getAggregations()).hasSize(1);
                        Histogram histogram = searchResponse.getAggregationByName("prices");
                        assertThat(histogram.getBuckets()).isNotNull();
                        assertThat(histogram.getBuckets()).hasSize(101);

                        for (Bucket bucket : histogram.getBuckets().values()) {
                            assertThat(bucket.getDocCount()).isIn(
                                    zero.get(),
                                    five.get(),
                                    ten.get(),
                                    fifteen.get(),
                                    twenty.get(),
                                    twentyFive.get(),
                                    thirty.get());
                            assertThat(bucket.getAggregations()).isNull();
                        }

                        return true;
                    });
        }

    }


    @Test
    void testSearchWithDateHistogram() throws InterruptedException {
        final String indexName = "events";
        JsonObject mapping = new JsonObject("{\n" +
                "      \"properties\": {\n" +
                "        \"timestamp\": {\n" +
                "          \"type\": \"date\" \n" +
                "        }\n" +
                "      }\n" +
                "    }");

        BulkRequest bulkRequest = new BulkRequest().setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL)
                .add(new IndexRequest(indexName, "doc", "1").setSource(new JsonObject().put("timestamp", "2018-07-30T12:37:23")))
                .add(new IndexRequest(indexName, "doc", "2").setSource(new JsonObject().put("timestamp", "2018-07-31T09:42:45")))
                .add(new IndexRequest(indexName, "doc", "3").setSource(new JsonObject().put("timestamp", "2018-07-31T11:45:17")))
                .add(new IndexRequest(indexName, "doc", "4").setSource(new JsonObject().put("timestamp", "2018-07-31T07:29:14")))
                .add(new IndexRequest(indexName, "doc", "5").setSource(new JsonObject().put("timestamp", "2018-08-02T11:02:57")))
                .add(new IndexRequest(indexName, "doc", "6").setSource(new JsonObject().put("timestamp", "2018-08-02T14:24:51")));
        rxClient().indices()
                .rxCreate(new CreateIndexRequest(indexName).addMapping("doc", mapping))
                .toCompletable()
                .andThen(rxClient().rxBulk(bulkRequest))
                .test()
                .await()
                .assertNoErrors();

        {
            SearchRequest searchRequest = new SearchRequest(indexName);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.aggregation(dateHistogram("events")
                    .field("timestamp")
                    .dateHistogramInterval(DateHistogramInterval.DAY)
                    .format("yyyy-MM-dd")
            );
            searchSourceBuilder.size(0);
            searchRequest.source(searchSourceBuilder);
            rxClient().rxSearch(SearchConverters.requestToDataObject(searchRequest))
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(searchResponse -> {
                        assertSearchHeader(searchResponse);
                        assertThat(searchResponse.getSuggest()).isNull();
                        assertThat(searchResponse.getProfileResults()).isNull();
                        assertThat(searchResponse.getHits().getTotalHits()).isEqualTo(6);
                        assertThat(searchResponse.getHits().getHits()).isEmpty();
                        assertThat(searchResponse.getHits().getMaxScore()).isEqualTo(0F);
                        assertThat(searchResponse.getAggregations()).hasSize(1);
                        Histogram histogram = searchResponse.getAggregationByName("events");
                        assertThat(histogram.getBuckets()).isNotNull();
                        assertThat(histogram.getBuckets()).hasSize(4);

                        for (Bucket bucket : histogram.getBuckets().values()) {
                            assertThat(bucket.getKey()).isIn(
                                    "2018-07-30",
                                    "2018-07-31",
                                    "2018-08-01",
                                    "2018-08-02"
                            );

                            assertThat(bucket.getDocCount()).isIn(
                                    1L,
                                    3L,
                                    0L,
                                    2L);
                            assertThat(bucket.getAggregations()).isNull();
                        }

                        return true;
                    });
        }


    }

    @Test
    void testSearchWithFiltersAggregation() throws InterruptedException {
        final String indexName = "logs-filter-test";
        JsonObject doc1 = new JsonObject("{ \"body\" : \"warning: page could not be rendered\" }");
        JsonObject doc2 = new JsonObject("{ \"body\" : \"authentication error\" }");
        JsonObject doc3 = new JsonObject("{ \"body\" : \"warning: connection timed out\" }");
        rxClient().rxBulk(new BulkRequest()
                .setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL)
                .add(new IndexRequest(indexName, "doc", "1").setSource(doc1))
                .add(new IndexRequest(indexName, "doc", "2").setSource(doc2))
                .add(new IndexRequest(indexName, "doc", "3").setSource(doc3))
        )
                .test()
                .await()
                .assertNoErrors();

        {
            SearchRequest searchRequest = new SearchRequest(indexName);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            FiltersAggregationBuilder filters = filters("messages",
                    new FiltersAggregator.KeyedFilter("errors", matchQuery("body", "error")),
                    new FiltersAggregator.KeyedFilter("warnings", matchQuery("body", "warning"))
            );
            searchSourceBuilder.aggregation(filters);
            searchSourceBuilder.size(0);
            searchRequest.source(searchSourceBuilder);
            rxClient().rxSearch(SearchConverters.requestToDataObject(searchRequest))
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(searchResponse -> {
                        assertSearchHeader(searchResponse);
                        assertThat(searchResponse.getSuggest()).isNull();
                        assertThat(searchResponse.getProfileResults()).isNull();
                        assertThat(searchResponse.getHits().getTotalHits()).isEqualTo(3L);
                        assertThat(searchResponse.getHits().getHits()).isEmpty();
                        assertThat(searchResponse.getHits().getMaxScore()).isEqualTo(0F);
                        assertThat(searchResponse.getAggregations()).hasSize(1);
                        Filters filtersAgg = searchResponse.getAggregationByName("messages");
                        assertThat(filtersAgg.getBuckets()).isNotNull();
                        assertThat(filtersAgg.getBuckets()).hasSize(2);

                        for (Bucket bucket : filtersAgg.getBuckets().values()) {
                            assertThat(bucket.getKey()).isIn("errors", "warnings");
                            assertThat(bucket.getDocCount()).isBetween(1L, 2L);
                            assertThat(bucket.getAggregations()).isNull();
                        }

                        return true;
                    });
        }

        {
            SearchRequest searchRequest = new SearchRequest(indexName);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            FiltersAggregationBuilder filters = filters("messages",
                    matchQuery("body", "error"),
                    matchQuery("body", "warning")
            );
            searchSourceBuilder.aggregation(filters);
            searchSourceBuilder.size(0);
            searchRequest.source(searchSourceBuilder);
            rxClient().rxSearch(SearchConverters.requestToDataObject(searchRequest))
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(searchResponse -> {
                        assertSearchHeader(searchResponse);
                        assertThat(searchResponse.getSuggest()).isNull();
                        assertThat(searchResponse.getProfileResults()).isNull();
                        assertThat(searchResponse.getHits().getTotalHits()).isEqualTo(3L);
                        assertThat(searchResponse.getHits().getHits()).isEmpty();
                        assertThat(searchResponse.getHits().getMaxScore()).isEqualTo(0F);
                        assertThat(searchResponse.getAggregations()).hasSize(1);
                        Filters filtersAgg = searchResponse.getAggregationByName("messages");
                        assertThat(filtersAgg.getBuckets()).isNotNull();
                        assertThat(filtersAgg.getBuckets()).hasSize(2);

                        for (Bucket bucket : filtersAgg.getBuckets().values()) {
                            assertThat(bucket.getKey()).isIn("0", "1");
                            assertThat(bucket.getDocCount()).isBetween(1L, 2L);
                            assertThat(bucket.getAggregations()).isNull();
                        }

                        return true;
                    });
        }

        {
            rxClient().rxIndex(new IndexRequest(indexName, "doc", "4")
                    .setSource(new JsonObject("{\"body\": \"info: user Bob logged out\"}"))
                    .setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL)
            )
                    .test()
                    .await()
                    .assertNoErrors();

            SearchRequest searchRequest = new SearchRequest(indexName);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            FiltersAggregationBuilder filters = filters("messages",
                    new FiltersAggregator.KeyedFilter("errors", matchQuery("body", "error")),
                    new FiltersAggregator.KeyedFilter("warnings", matchQuery("body", "warning"))
            )
                    .otherBucketKey("other_messages")
                    .otherBucket(true);
            searchSourceBuilder.aggregation(filters);
            searchSourceBuilder.size(0);
            searchRequest.source(searchSourceBuilder);
            rxClient().rxSearch(SearchConverters.requestToDataObject(searchRequest))
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(searchResponse -> {
                        assertSearchHeader(searchResponse);
                        assertThat(searchResponse.getSuggest()).isNull();
                        assertThat(searchResponse.getProfileResults()).isNull();
                        assertThat(searchResponse.getHits().getTotalHits()).isEqualTo(4L);
                        assertThat(searchResponse.getHits().getHits()).isEmpty();
                        assertThat(searchResponse.getHits().getMaxScore()).isEqualTo(0F);
                        assertThat(searchResponse.getAggregations()).hasSize(1);
                        Filters filtersAgg = searchResponse.getAggregationByName("messages");
                        assertThat(filtersAgg.getBuckets()).isNotNull();
                        assertThat(filtersAgg.getBuckets()).hasSize(3);

                        for (Bucket bucket : filtersAgg.getBuckets().values()) {
                            assertThat(bucket.getKey()).isIn("errors", "warnings", "other_messages");
                            assertThat(bucket.getDocCount()).isBetween(1L, 2L);
                            assertThat(bucket.getAggregations()).isNull();
                        }

                        return true;
                    });
        }
    }

    @Test
    void testSearchWithParentJoin() throws InterruptedException {
        final String indexName = "child_example";

        JsonObject parentMapping = new JsonObject("{\n" +
                "            \"properties\" : {\n" +
                "                \"qa_join_field\" : {\n" +
                "                    \"type\" : \"join\",\n" +
                "                    \"relations\" : { \"question\" : \"answer\" }\n" +
                "                }\n" +
                "    }" +
                "}");
        rxClient().indices().rxCreate(new CreateIndexRequest(indexName).addMapping("qa", parentMapping))
                .test()
                .await()
                .assertNoErrors();

        JsonObject questionDoc = new JsonObject("{\n" +
                "    \"body\": \"<p>I have Windows 2003 server and i bought a new Windows 2008 server...\",\n" +
                "    \"title\": \"Whats the best way to file transfer my site from server to a newer one?\",\n" +
                "    \"tags\": [\n" +
                "        \"windows-server-2003\",\n" +
                "        \"windows-server-2008\",\n" +
                "        \"file-transfer\"\n" +
                "    ],\n" +
                "    \"qa_join_field\" : \"question\"\n" +
                "}");

        JsonObject answerDoc1 = new JsonObject("{\n" +
                "    \"owner\": {\n" +
                "        \"location\": \"Norfolk, United Kingdom\",\n" +
                "        \"display_name\": \"Sam\",\n" +
                "        \"id\": 48\n" +
                "    },\n" +
                "    \"body\": \"<p>Unfortunately you're pretty much limited to FTP...\",\n" +
                "    \"qa_join_field\" : {\n" +
                "        \"name\" : \"answer\",\n" +
                "        \"parent\" : \"1\"\n" +
                "    },\n" +
                "    \"creation_date\": \"2009-05-04T13:45:37.030\"\n" +
                "}");
        JsonObject answerDoc2 = new JsonObject("{\n" +
                "    \"owner\": {\n" +
                "        \"location\": \"Norfolk, United Kingdom\",\n" +
                "        \"display_name\": \"Troll\",\n" +
                "        \"id\": 49\n" +
                "    },\n" +
                "    \"body\": \"<p>Use Linux...\",\n" +
                "    \"qa_join_field\" : {\n" +
                "        \"name\" : \"answer\",\n" +
                "        \"parent\" : \"1\"\n" +
                "    },\n" +
                "    \"creation_date\": \"2009-05-05T13:45:37.030\"\n" +
                "}");
        rxClient().rxBulk(new BulkRequest()
                .setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL)
                .add(new IndexRequest(indexName, "qa", "1").setSource(questionDoc))
                .add(new IndexRequest(indexName, "qa", "2").setSource(answerDoc1).setRouting("1"))
                .add(new IndexRequest(indexName, "qa", "3").setSource(answerDoc2).setRouting("1"))
        )
                .test()
                .await()
                .assertNoErrors();

        TermsAggregationBuilder leafTermAgg = new TermsAggregationBuilder("top-names", ValueType.STRING)
                .field("owner.display_name.keyword").size(10);
        ChildrenAggregationBuilder childrenAgg = new ChildrenAggregationBuilder("to-answers", "answer").subAggregation(leafTermAgg);
        TermsAggregationBuilder termsAgg = new TermsAggregationBuilder("top-tags", ValueType.STRING).field("tags.keyword")
                .size(10).subAggregation(childrenAgg);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.size(0).aggregation(termsAgg);
        SearchRequest searchRequest = new SearchRequest(indexName);
        searchRequest.source(searchSourceBuilder);

        rxClient().rxSearch(SearchConverters.requestToDataObject(searchRequest))
                .test()
                .await()
                .assertNoErrors()
                .assertValue(searchResponse -> {
                    assertSearchHeader(searchResponse);
                    assertThat(searchResponse.getSuggest()).isNull();
                    assertThat(searchResponse.getProfileResults()).isNull();
                    assertThat(searchResponse.getHits().getTotalHits()).isEqualTo(3L);
                    assertThat(searchResponse.getHits().getHits()).isEmpty();
                    assertThat(searchResponse.getHits().getMaxScore()).isEqualTo(0F);
                    assertThat(searchResponse.getAggregations()).hasSize(1);
                    Terms terms = searchResponse.getAggregationByName("top-tags");
                    assertThat(terms.getDocCountError()).isEqualTo(0);
                    assertThat(terms.getSumOfOtherDocCounts()).isEqualTo(0);
                    assertThat(terms.getBuckets()).hasSize(3);
                    for (TermsBucket bucket : terms.getBuckets().values()) {
                        assertThat(bucket.getKey()).isIn("file-transfer", "windows-server-2003", "windows-server-2008");
                        assertThat(bucket.getDocCount()).isEqualTo(1);
                        assertThat(bucket.getAggregations()).hasSize(1);
                        Children children = bucket.getAggregationByName("to-answers");
                        assertThat(children.getDocCount()).isEqualTo(2);
                        assertThat(children.getAggregations()).hasSize(1);
                        Terms leafTerms = children.getAggregationByName("top-names");
                        assertThat(leafTerms.getDocCountError()).isEqualTo(0);
                        assertThat(leafTerms.getSumOfOtherDocCounts()).isEqualTo(0);
                        assertThat(leafTerms.getBuckets()).hasSize(2);
                        TermsBucket sam = leafTerms.getBucketByKey("Sam");
                        assertThat(sam.getDocCount()).isEqualTo(1);
                        TermsBucket troll = leafTerms.getBucketByKey("Troll");
                        assertThat(troll.getDocCount()).isEqualTo(1);
                    }
                    return true;
                });
    }

    @Test
    void testSearchWithSuggest() throws InterruptedException {
        initData();
        SearchRequest searchRequest = new SearchRequest("index");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.suggest(new SuggestBuilder().addSuggestion("sugg1", new PhraseSuggestionBuilder("type"))
                .setGlobalText("type"));
        searchSourceBuilder.size(0);
        searchRequest.source(searchSourceBuilder);

        rxClient().rxSearch(SearchConverters.requestToDataObject(searchRequest))
                .test()
                .await()
                .assertNoErrors()
                .assertValue(searchResponse -> {
                    assertSearchHeader(searchResponse);
                    assertThat(searchResponse.getAggregations()).isNull();
                    assertThat(searchResponse.getProfileResults()).isNull();
                    assertThat(searchResponse.getHits().getTotalHits()).isEqualTo(0L);
                    assertThat(searchResponse.getHits().getMaxScore()).isEqualTo(0F);
                    assertThat(searchResponse.getHits().getHits()).isEmpty();
                    assertThat(searchResponse.getSuggest()).hasSize(1);

                    Suggestion suggestion = searchResponse.getSuggest().get("sugg1");
                    assertThat(suggestion.getName()).isEqualTo("sugg1");
                    assertThat(suggestion).isInstanceOf(PhraseSuggestion.class);
                    PhraseSuggestion sugg = (PhraseSuggestion) suggestion;
                    assertThat(sugg.getEntries()).hasSize(1);
                    for (PhraseEntry options : sugg.getEntries()) {
                        assertThat(options.getText()).isEqualTo("type");
                        assertThat(options.getOffset()).isEqualTo(0);
                        assertThat(options.getLength()).isEqualTo(4);
                        assertThat(options.getOptions()).hasSize(2);
                        for (PhraseOption option : options.getOptions()) {
                            assertThat(option.getScore()).isGreaterThan(0F);
                            assertThat(option.getText()).isIn("type1", "type2");
                        }
                    }
                    return true;
                });
    }


    @Test
    void testSearchWithWeirdScriptFields() throws InterruptedException {
        rxClient().rxIndex(new IndexRequest("test", "type", "1")
                .setSource(new JsonObject().put("field", "value"))
                .setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL))
                .test()
                .await()
                .assertNoErrors();

        {
            SearchRequest searchRequest = new SearchRequest("test")
                    .source(SearchSourceBuilder.searchSource()
                            .scriptField("result", new Script("null")));
            rxClient().rxSearch(SearchConverters.requestToDataObject(searchRequest))
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(searchResponse -> {
                        SearchHit searchHit = searchResponse.getHits().getAt(0);
                        JsonArray values = searchHit.getFields().get("result").getValues();
                        assertThat(values).isNotNull();
                        assertThat(values).hasSize(1);
                        assertThat(values.getValue(0)).isNull();
                        return true;
                    });
        }
        {
            SearchRequest searchRequest = new SearchRequest("test").source(SearchSourceBuilder.searchSource()
                    .scriptField("result", new Script("new HashMap()")));
            rxClient().rxSearch(SearchConverters.requestToDataObject(searchRequest))
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(searchResponse -> {
                        SearchHit searchHit = searchResponse.getHits().getAt(0);
                        JsonArray values = searchHit.getFields().get("result").getValues();
                        assertThat(values).isNotNull();
                        assertThat(values).hasSize(1);
                        assertThat(values.getValue(0)).isInstanceOf(JsonObject.class);
                        assertThat(values.getJsonObject(0)).hasSize(0);
                        return true;
                    });
        }
        {
            SearchRequest searchRequest = new SearchRequest("test").source(SearchSourceBuilder.searchSource()
                    .scriptField("result", new Script("new String[]{}")));
            rxClient().rxSearch(SearchConverters.requestToDataObject(searchRequest))
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(searchResponse -> {
                        SearchHit searchHit = searchResponse.getHits().getAt(0);
                        JsonArray values = searchHit.getFields().get("result").getValues();
                        assertThat(values).isNotNull();
                        assertThat(values).hasSize(1);
                        assertThat(values.getValue(0)).isInstanceOf(JsonArray.class);
                        assertThat(values.getJsonArray(0)).hasSize(0);
                        return true;
                    });
        }
    }

    @Test
    void testSearchScroll() throws InterruptedException {
        final AtomicReference<SearchResponse> ref = new AtomicReference<>();
        final AtomicLong counter = new AtomicLong(0);

        List<DocWriteRequest> indexRequests = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            IndexRequest indexRequest = new IndexRequest("test", "type1", String.valueOf(i));
            indexRequests.add(indexRequest.setSource(new JsonObject().put("field", i)));
        }
        rxClient().rxBulk(new BulkRequest().setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL).setRequests(indexRequests))
                .test()
                .await()
                .assertNoErrors();

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().size(35).sort("field", SortOrder.ASC);
        SearchRequest searchRequest = new SearchRequest("test").scroll(TimeValue.timeValueMinutes(2)).source(searchSourceBuilder);

        rxClient().rxSearch(SearchConverters.requestToDataObject(searchRequest))
                .test()
                .await()
                .assertNoErrors()
                .assertValue(searchResponse -> {
                    ref.set(searchResponse);
                    assertSearchHeader(searchResponse);
                    assertThat(searchResponse.getHits().getTotalHits()).isEqualTo(100L);
                    assertThat(searchResponse.getHits().getHits()).hasSize(35);
                    for (SearchHit hit : searchResponse.getHits()) {
                        assertThat(hit.getSortValues().getLong(0)).isEqualTo(counter.getAndIncrement());
                    }
                    return true;
                });

        rxClient().rxSearchScroll(new SearchScrollRequest(ref.get().getScrollId(), TimeValue.timeValueMinutes(2).millis()))
                .test()
                .await()
                .assertNoErrors()
                .assertValue(searchResponse -> {
                    ref.set(searchResponse);

                    assertThat(searchResponse.getHits().getTotalHits()).isEqualTo(100L);
                    assertThat(searchResponse.getHits().getHits()).hasSize(35);
                    for (SearchHit hit : searchResponse.getHits()) {
                        assertThat(hit.getSortValues().getLong(0)).isEqualTo(counter.getAndIncrement());
                    }

                    return true;
                });

        rxClient().rxSearchScroll(new SearchScrollRequest(ref.get().getScrollId(), TimeValue.timeValueMinutes(2).millis()))
                .test()
                .await()
                .assertNoErrors()
                .assertValue(searchResponse -> {
                    ref.set(searchResponse);

                    assertThat(searchResponse.getHits().getTotalHits()).isEqualTo(100L);
                    assertThat(searchResponse.getHits().getHits()).hasSize(30);
                    for (SearchHit hit : searchResponse.getHits()) {
                        assertThat(hit.getSortValues().getLong(0)).isEqualTo(counter.getAndIncrement());
                    }

                    return true;
                });

        rxClient().rxClearScroll(new ClearScrollRequest().addScrollId(ref.get().getScrollId()))
                .test()
                .await()
                .assertNoErrors()
                .assertValue(clearScrollResponse -> {
                    assertThat(clearScrollResponse.getNumFreed()).isGreaterThan(0);
                    assertThat(clearScrollResponse.isSucceeded()).isTrue();
                    return true;
                });

        rxClient().rxSearchScroll(new SearchScrollRequest(ref.get().getScrollId(), TimeValue.timeValueMinutes(2).millis()))
                .test()
                .await()
                .assertError(error -> {
                    assertThat(error).isInstanceOf(ElasticsearchException.class);
                    ElasticsearchException exception = (ElasticsearchException) error;

                    assertThat(exception.status()).isEqualTo(RestStatus.NOT_FOUND);
                    assertThat(exception.getRootCause()).isInstanceOf(ElasticsearchException.class);
                    ElasticsearchException rootCause = (ElasticsearchException) exception.getRootCause();
                    assertThat(rootCause.getMessage()).contains("No search context found for");

                    return true;
                });


    }

    @Test
    void testMultiSearch() throws InterruptedException {
        initData();
        MultiSearchRequest multiSearchRequest = new MultiSearchRequest();
        SearchRequest searchRequest1 = new SearchRequest("index1");
        searchRequest1.source().sort("_id", SortOrder.ASC);
        multiSearchRequest.add(searchRequest1);
        SearchRequest searchRequest2 = new SearchRequest("index2");
        searchRequest2.source().sort("_id", SortOrder.ASC);
        multiSearchRequest.add(searchRequest2);
        SearchRequest searchRequest3 = new SearchRequest("index3");
        searchRequest3.source().sort("_id", SortOrder.ASC);
        multiSearchRequest.add(searchRequest3);

        rxClient().rxMultiSearch(MultiSearchConverters.requestToDataObject(multiSearchRequest))
                .test()
                .await()
                .assertNoErrors()
                .assertValue(multiSearchResponse -> {

                    assertThat(multiSearchResponse.getResponses()).hasSize(3);

                    MultiSearchResponseItem response1 = multiSearchResponse.getResponses().get(0);
                    assertThat(response1.getFailure()).isNull();
                    assertThat(response1.isFailure()).isFalse();
                    assertSearchHeader(response1.getResponse());
                    assertThat(response1.getResponse().getHits().getTotalHits()).isEqualTo(2L);
                    assertThat(response1.getResponse().getHits().getAt(0).getId()).isEqualTo("1");
                    assertThat(response1.getResponse().getHits().getAt(1).getId()).isEqualTo("2");

                    MultiSearchResponseItem response2 = multiSearchResponse.getResponses().get(1);
                    assertThat(response2.getFailure()).isNull();
                    assertThat(response2.isFailure()).isFalse();
                    assertSearchHeader(response2.getResponse());
                    assertThat(response2.getResponse().getHits().getTotalHits()).isEqualTo(2L);
                    assertThat(response2.getResponse().getHits().getAt(0).getId()).isEqualTo("3");
                    assertThat(response2.getResponse().getHits().getAt(1).getId()).isEqualTo("4");

                    MultiSearchResponseItem response3 = multiSearchResponse.getResponses().get(2);
                    assertThat(response3.getFailure()).isNull();
                    assertThat(response3.isFailure()).isFalse();
                    assertSearchHeader(response3.getResponse());
                    assertThat(response3.getResponse().getHits().getTotalHits()).isEqualTo(2L);
                    assertThat(response3.getResponse().getHits().getAt(0).getId()).isEqualTo("5");
                    assertThat(response3.getResponse().getHits().getAt(1).getId()).isEqualTo("6");

                    return true;
                });
    }

    @Test
    void testMultiSearch_withAgg() throws InterruptedException {
        initData();
        MultiSearchRequest multiSearchRequest = new MultiSearchRequest();
        SearchRequest searchRequest1 = new SearchRequest("index1");
        searchRequest1.source().size(0).aggregation(new TermsAggregationBuilder("name", ValueType.STRING).field("field.keyword")
                .order(BucketOrder.key(true)));
        multiSearchRequest.add(searchRequest1);
        SearchRequest searchRequest2 = new SearchRequest("index2");
        searchRequest2.source().size(0).aggregation(new TermsAggregationBuilder("name", ValueType.STRING).field("field.keyword")
                .order(BucketOrder.key(true)));
        multiSearchRequest.add(searchRequest2);
        SearchRequest searchRequest3 = new SearchRequest("index3");
        searchRequest3.source().size(0).aggregation(new TermsAggregationBuilder("name", ValueType.STRING).field("field.keyword")
                .order(BucketOrder.key(true)));
        multiSearchRequest.add(searchRequest3);

        rxClient().rxMultiSearch(MultiSearchConverters.requestToDataObject(multiSearchRequest))
                .test()
                .await()
                .assertNoErrors()
                .assertValue(multiSearchResponse -> {

                    assertThat(multiSearchResponse.getResponses()).hasSize(3);

                    assertThat(multiSearchResponse.getResponses().get(0).getFailure()).isNull();
                    assertThat(multiSearchResponse.getResponses().get(0).isFailure()).isFalse();
                    assertSearchHeader(multiSearchResponse.getResponses().get(0).getResponse());
                    assertThat(multiSearchResponse.getResponses().get(0).getResponse().getHits().getTotalHits()).isEqualTo(2L);
                    assertThat(multiSearchResponse.getResponses().get(0).getResponse().getHits().getHits()).hasSize(0);
                    Terms terms = multiSearchResponse.getResponses().get(0).getResponse().getAggregationByName("name");
                    assertThat(terms.getBuckets()).hasSize(2);
                    assertThat(terms.getBucketByKey("value1").getKey()).isEqualTo("value1");
                    assertThat(terms.getBucketByKey("value2").getKey()).isEqualTo("value2");

                    assertThat(multiSearchResponse.getResponses().get(1).getFailure()).isNull();
                    assertThat(multiSearchResponse.getResponses().get(1).isFailure()).isFalse();
                    assertSearchHeader(multiSearchResponse.getResponses().get(0).getResponse());
                    assertThat(multiSearchResponse.getResponses().get(1).getResponse().getHits().getTotalHits()).isEqualTo(2L);
                    assertThat(multiSearchResponse.getResponses().get(1).getResponse().getHits().getHits()).hasSize(0);
                    terms = multiSearchResponse.getResponses().get(1).getResponse().getAggregationByName("name");
                    assertThat(terms.getBuckets()).hasSize(2);
                    assertThat(terms.getBucketByKey("value1").getKey()).isEqualTo("value1");
                    assertThat(terms.getBucketByKey("value2").getKey()).isEqualTo("value2");

                    assertThat(multiSearchResponse.getResponses().get(2).getFailure()).isNull();
                    assertThat(multiSearchResponse.getResponses().get(2).isFailure()).isFalse();
                    assertSearchHeader(multiSearchResponse.getResponses().get(0).getResponse());
                    assertThat(multiSearchResponse.getResponses().get(2).getResponse().getHits().getTotalHits()).isEqualTo(2L);
                    assertThat(multiSearchResponse.getResponses().get(2).getResponse().getHits().getHits()).hasSize(0);
                    terms = multiSearchResponse.getResponses().get(2).getResponse().getAggregationByName("name");
                    assertThat(terms.getBuckets()).hasSize(2);
                    assertThat(terms.getBucketByKey("value1").getKey()).isEqualTo("value1");
                    assertThat(terms.getBucketByKey("value2").getKey()).isEqualTo("value2");
                    return true;
                });
    }

    @Test
    void testMultiSearch_withQuery() throws InterruptedException {
        initData();
        MultiSearchRequest multiSearchRequest = new MultiSearchRequest();
        SearchRequest searchRequest1 = new SearchRequest("index1");
        searchRequest1.source().query(new TermsQueryBuilder("field", "value2"));
        multiSearchRequest.add(searchRequest1);
        SearchRequest searchRequest2 = new SearchRequest("index2");
        searchRequest2.source().query(new TermsQueryBuilder("field", "value2"));
        multiSearchRequest.add(searchRequest2);
        SearchRequest searchRequest3 = new SearchRequest("index3");
        searchRequest3.source().query(new TermsQueryBuilder("field", "value2"));
        multiSearchRequest.add(searchRequest3);

        rxClient().rxMultiSearch(MultiSearchConverters.requestToDataObject(multiSearchRequest))
                .test()
                .await()
                .assertNoErrors()
                .assertValue(multiSearchResponse -> {
                    assertThat(multiSearchResponse.getResponses()).hasSize(3);

                    MultiSearchResponseItem response1 = multiSearchResponse.getResponses().get(0);
                    assertThat(response1.getFailure()).isNull();
                    assertThat(response1.isFailure()).isFalse();
                    assertSearchHeader(response1.getResponse());
                    assertThat(response1.getResponse().getHits().getTotalHits()).isEqualTo(1L);
                    assertThat(response1.getResponse().getHits().getAt(0).getId()).isEqualTo("2");

                    MultiSearchResponseItem response2 = multiSearchResponse.getResponses().get(1);
                    assertThat(response2.getFailure()).isNull();
                    assertThat(response2.isFailure()).isFalse();
                    assertSearchHeader(response2.getResponse());
                    assertThat(response2.getResponse().getHits().getTotalHits()).isEqualTo(1L);
                    assertThat(response2.getResponse().getHits().getAt(0).getId()).isEqualTo("4");

                    MultiSearchResponseItem response3 = multiSearchResponse.getResponses().get(2);
                    assertThat(response3.getFailure()).isNull();
                    assertThat(response3.isFailure()).isFalse();
                    assertSearchHeader(response3.getResponse());
                    assertThat(response3.getResponse().getHits().getTotalHits()).isEqualTo(1L);
                    assertThat(response3.getResponse().getHits().getAt(0).getId()).isEqualTo("6");

                    return true;
                });

        searchRequest1.source().highlighter(new HighlightBuilder().field("field"));
        searchRequest2.source().highlighter(new HighlightBuilder().field("field"));
        searchRequest3.source().highlighter(new HighlightBuilder().field("field"));
        rxClient().rxMultiSearch(MultiSearchConverters.requestToDataObject(multiSearchRequest))
                .test()
                .await()
                .assertNoErrors()
                .assertValue(multiSearchResponse -> {
                    assertThat(multiSearchResponse.getResponses()).hasSize(3);

                    MultiSearchResponseItem response1 = multiSearchResponse.getResponses().get(0);
                    assertThat(response1.getFailure()).isNull();
                    assertThat(response1.isFailure()).isFalse();
                    assertSearchHeader(response1.getResponse());
                    assertThat(response1.getResponse().getHits().getTotalHits()).isEqualTo(1L);
                    assertThat(response1.getResponse().getHits().getAt(0).getId()).isEqualTo("2");
                    assertThat(response1.getResponse().getHits().getAt(0)
                            .getHighlightFields().get("field").get(0)
                    ).isEqualTo("<em>value2</em>");

                    MultiSearchResponseItem response2 = multiSearchResponse.getResponses().get(1);
                    assertThat(response2.getFailure()).isNull();
                    assertThat(response2.isFailure()).isFalse();
                    assertSearchHeader(response2.getResponse());
                    assertThat(response2.getResponse().getHits().getTotalHits()).isEqualTo(1L);
                    assertThat(response2.getResponse().getHits().getAt(0).getId()).isEqualTo("4");
                    assertThat(response2.getResponse().getHits().getAt(0)
                            .getHighlightFields().get("field").get(0)
                    ).isEqualTo("<em>value2</em>");

                    MultiSearchResponseItem response3 = multiSearchResponse.getResponses().get(2);
                    assertThat(response3.getFailure()).isNull();
                    assertThat(response3.isFailure()).isFalse();
                    assertSearchHeader(response3.getResponse());
                    assertThat(response3.getResponse().getHits().getTotalHits()).isEqualTo(1L);
                    assertThat(response3.getResponse().getHits().getAt(0).getId()).isEqualTo("6");
                    assertThat(response3.getResponse().getHits().getAt(0)
                            .getHighlightFields().get("field").get(0)
                    ).isEqualTo("<em>value2</em>");
                    return true;
                });
    }

    @Test
    void testMultiSearch_failure() throws InterruptedException {
        initData();
        MultiSearchRequest multiSearchRequest = new MultiSearchRequest();
        SearchRequest searchRequest1 = new SearchRequest("index1");
        searchRequest1.source().query(new ScriptQueryBuilder(new Script(ScriptType.INLINE, "invalid", "code", Collections.emptyMap())));
        multiSearchRequest.add(searchRequest1);
        SearchRequest searchRequest2 = new SearchRequest("index2");
        searchRequest2.source().query(new ScriptQueryBuilder(new Script(ScriptType.INLINE, "invalid", "code", Collections.emptyMap())));
        multiSearchRequest.add(searchRequest2);

        rxClient().rxMultiSearch(MultiSearchConverters.requestToDataObject(multiSearchRequest))
                .test()
                .await()
                .assertNoErrors()
                .assertValue(multiSearchResponse -> {
                    assertThat(multiSearchResponse.getResponses()).hasSize(2);

                    MultiSearchResponseItem response1 = multiSearchResponse.getResponses().get(0);
                    assertThat(response1.isFailure()).isTrue();
                    assertThat(response1.getFailure().getCause()).contains("search_phase_execution_exception");
                    assertThat(response1.getResponse()).isNull();

                    MultiSearchResponseItem response2 = multiSearchResponse.getResponses().get(1);
                    assertThat(response2.isFailure()).isTrue();
                    assertThat(response2.getFailure().getCause()).contains("search_phase_execution_exception");
                    assertThat(response2.getResponse()).isNull();
                    return true;
                });
    }

    private static void assertSearchHeader(SearchResponse searchResponse) {
        assertThat(searchResponse.getTookInMillis()).isGreaterThanOrEqualTo(0L);
        assertThat(searchResponse.getFailedShards()).isEqualTo(0);
        assertThat(searchResponse.getTotalShards()).isGreaterThan(0);
        assertThat(searchResponse.getSuccessfulShards()).isEqualTo(searchResponse.getTotalShards());
        assertThat(searchResponse.getShardFailures()).isNull();
        assertThat(searchResponse.getClusters()).isEqualTo(new Clusters(0, 0, 0));
    }
}
