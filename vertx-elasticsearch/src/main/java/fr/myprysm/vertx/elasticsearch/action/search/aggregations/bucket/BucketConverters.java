package fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket;

import fr.myprysm.vertx.elasticsearch.action.search.aggregations.Aggregation;
import fr.myprysm.vertx.elasticsearch.action.search.aggregations.AggregationConverters;
import org.elasticsearch.search.aggregations.HasAggregations;
import org.elasticsearch.search.aggregations.bucket.MultiBucketsAggregation;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedDoubleTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;

import java.util.HashMap;
import java.util.Map;

import static fr.myprysm.vertx.elasticsearch.action.search.aggregations.AggregationConverters.fillCommonAggregationDataObject;

public interface BucketConverters {

    static Terms termsToDataObject(org.elasticsearch.search.aggregations.bucket.terms.Terms esTerms) {
        Terms terms = new Terms();
        if (esTerms.getBuckets() != null && esTerms.getBuckets().size() > 0) {
            Map<String, TermsBucket> buckets = new HashMap<>();
            for (org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket bucket : esTerms.getBuckets()) {
                buckets.put(bucket.getKeyAsString(), termsBucketToDataObject(bucket));
            }

            terms.setBuckets(buckets);
        }


        return fillCommonAggregationDataObject(terms, esTerms)
                .setDocCountError(esTerms.getDocCountError())
                .setSumOfOtherDocCounts(esTerms.getSumOfOtherDocCounts());
    }

    static TermsBucket termsBucketToDataObject(org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket esBucket) {
        TermsBucket bucket = fillGenericBucket(new TermsBucket(), esBucket);

        if (esBucket instanceof ParsedLongTerms.ParsedBucket || esBucket instanceof ParsedDoubleTerms.ParsedBucket) {
            bucket.setKeyAsNumber(esBucket.getKeyAsNumber());
        }

        return fillAggregations(bucket, esBucket)
                .setDocCountError(esBucket.getDocCountError());
    }

    static Range rangeToDataObject(org.elasticsearch.search.aggregations.bucket.range.Range esRange) {
        Range range = new Range();

        if (esRange.getBuckets() != null && esRange.getBuckets().size() > 0) {
            Map<String, RangeBucket> buckets = new HashMap<>();
            for (org.elasticsearch.search.aggregations.bucket.range.Range.Bucket bucket : esRange.getBuckets()) {
                buckets.put(bucket.getKeyAsString(), rangeBucketToDataObject(bucket));
            }

            range.setBuckets(buckets);
        }

        return fillCommonAggregationDataObject(range, esRange);
    }

    static RangeBucket rangeBucketToDataObject(org.elasticsearch.search.aggregations.bucket.range.Range.Bucket esBucket) {
        RangeBucket bucket = fillGenericBucket(new RangeBucket(), esBucket);

        return fillAggregations(bucket, esBucket)
                .setFromAsString(esBucket.getFromAsString())
                .setToAsString(esBucket.getToAsString())
                .setKey(esBucket.getKeyAsString());
    }

    static <T extends Bucket, U extends HasAggregations> T fillAggregations(T bucket, U esBucket) {
        bucket.setAggregations(extractAggregations(esBucket));
        return bucket;
    }

    static <T extends Bucket, U extends MultiBucketsAggregation.Bucket> T fillGenericBucket(T bucket, U esBucket) {
        bucket.setDocCount(esBucket.getDocCount()).setKey(esBucket.getKeyAsString());
        return bucket;
    }

    static Map<String, Aggregation> extractAggregations(HasAggregations esAggregations) {
        Map<String, Aggregation> subs = null;
        if (esAggregations.getAggregations() != null && !esAggregations.getAggregations().asList().isEmpty()) {
            subs = new HashMap<>();
            for (org.elasticsearch.search.aggregations.Aggregation aggregation : esAggregations.getAggregations().asList()) {
                subs.put(aggregation.getName(), AggregationConverters.aggregationToDataObject(aggregation));
            }
        }

        return subs;
    }

    static Children childrenToDataObject(org.elasticsearch.join.aggregations.Children esChildren) {
        return fillCommonAggregationDataObject(new Children(), esChildren)
                .setAggregations(extractAggregations(esChildren))
                .setDocCount(esChildren.getDocCount());
    }
}
