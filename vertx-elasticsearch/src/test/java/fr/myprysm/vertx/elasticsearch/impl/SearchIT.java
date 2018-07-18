package fr.myprysm.vertx.elasticsearch.impl;

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
import fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.Children;
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
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.ScriptQueryBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.join.aggregations.ChildrenAggregationBuilder;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.aggregations.BucketOrder;
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

class SearchIT extends VertxESRestTestCase {

    @SuppressWarnings("unchecked")
    @Override
    void initES() throws InterruptedException {
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
        )
                .test()
                .await()
                .assertNoErrors();

        rxClient().rxBulk(
                new BulkRequest().setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL)
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
