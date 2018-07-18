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
                .setToAsString(esBucket.getToAsString());
    }

    /**
     * Fills the aggregations from the elasticsearch bucket into the data object bucket.
     *
     * @param bucket   the data object bucket
     * @param esBucket the elasticsearch bucket
     * @param <T>      type of the data object bucket
     * @param <U>      type of the elasticsearch bucket
     * @return the bucket with the aggregations
     */
    static <T extends Bucket, U extends HasAggregations> T fillAggregations(T bucket, U esBucket) {
        bucket.setAggregations(extractAggregations(esBucket));
        return bucket;
    }

    /**
     * Fills the generic informations from the elasticsearch bucket into the data object bucket.
     *
     * @param bucket   the data object bucket
     * @param esBucket the elasticsearch bucket
     * @param <T>      type of the data object bucket
     * @param <U>      type of the elasticsearch bucket
     * @return the bucket with generic informations
     */
    static <T extends Bucket, U extends MultiBucketsAggregation.Bucket> T fillGenericBucket(T bucket, U esBucket) {
        bucket.setDocCount(esBucket.getDocCount()).setKey(esBucket.getKeyAsString());
        return bucket;
    }

    /**
     * Extracts the aggregations from the elasticsearch {@link HasAggregations} object.
     * <p>
     * Iterates over the list rather than the map to avoid to construct it twice (the elasticsearch component usually does it).
     *
     * @param esAggregations the object with aggregations
     * @return the aggregations as a map
     */
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

    /**
     * Fills a single aggregation data object from the elasticsearch object.
     *
     * @param agg   the data object
     * @param esAgg the elasticsearch object
     * @param <T>   the type of the data object
     * @param <U>   the type of the elasticsearch object
     * @return the data object with the aggregations and common information
     */
    @SuppressWarnings("unchecked")
    static <T extends SingleBucketAggregation,
            U extends org.elasticsearch.search.aggregations.bucket.SingleBucketAggregation>
    T singleBucketAggregationToDataObject(T agg, U esAgg) {
        return (T) fillCommonAggregationDataObject(agg, esAgg)
                .setAggregations(extractAggregations(esAgg))
                .setDocCount(esAgg.getDocCount());
    }

    static GeoHashGrid geoHashGridToDataObject(org.elasticsearch.search.aggregations.bucket.geogrid.GeoHashGrid esAgg) {
        GeoHashGrid agg = fillCommonAggregationDataObject(new GeoHashGrid(), esAgg);

        if (esAgg.getBuckets() != null && esAgg.getBuckets().size() > 0) {
            Map<String, Bucket> buckets = new HashMap<>();
            for (org.elasticsearch.search.aggregations.bucket.geogrid.GeoHashGrid.Bucket esBucket : esAgg.getBuckets()) {
                Bucket bucket = fillGenericBucket(new Bucket(), esBucket);
                fillAggregations(bucket, esBucket);

                buckets.put(esBucket.getKeyAsString(), bucket);
            }

            agg.setBuckets(buckets);
        }


        return agg;

    }


    static Filters filtersToDataObject(org.elasticsearch.search.aggregations.bucket.filter.Filters esAgg) {
        Filters agg = fillCommonAggregationDataObject(new Filters(), esAgg);

        if (esAgg.getBuckets() != null && esAgg.getBuckets().size() > 0) {
            Map<String, Bucket> buckets = new HashMap<>();
            for (org.elasticsearch.search.aggregations.bucket.filter.Filters.Bucket esBucket : esAgg.getBuckets()) {
                Bucket bucket = fillGenericBucket(new Bucket(), esBucket);
                fillAggregations(bucket, esBucket);

                buckets.put(esBucket.getKeyAsString(), bucket);
            }

            agg.setBuckets(buckets);
        }


        return agg;

    }
}
