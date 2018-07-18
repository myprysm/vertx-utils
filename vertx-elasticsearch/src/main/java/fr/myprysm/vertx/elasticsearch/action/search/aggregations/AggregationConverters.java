package fr.myprysm.vertx.elasticsearch.action.search.aggregations;

import fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.BucketConverters;
import fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.Children;
import fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.Filter;
import fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.Filters;
import fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.GeoHashGrid;
import fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.Range;
import fr.myprysm.vertx.elasticsearch.action.search.aggregations.bucket.Terms;
import fr.myprysm.vertx.elasticsearch.action.search.aggregations.matrix.MatrixConverters;
import fr.myprysm.vertx.elasticsearch.action.search.aggregations.matrix.MatrixStats;
import fr.myprysm.vertx.elasticsearch.converter.CommonConverters;
import io.vertx.core.json.JsonObject;
import org.elasticsearch.join.aggregations.ChildrenAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.SingleBucketAggregation;
import org.elasticsearch.search.aggregations.bucket.filter.FilterAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.filter.FiltersAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.geogrid.GeoGridAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.range.DateRangeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.range.GeoDistanceAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.range.IpRangeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.range.RangeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.DoubleTerms;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.UnmappedTerms;
import org.elasticsearch.search.aggregations.matrix.stats.MatrixStatsAggregationBuilder;

import static org.elasticsearch.search.aggregations.Aggregation.TYPED_KEYS_DELIMITER;

public interface AggregationConverters {

    static Aggregation fromJsonObject(JsonObject json) {
        if (json.getString("type") == null) {
            return new Aggregation(json);
        }

        switch (json.getString("type")) {
            case StringTerms.NAME:
            case UnmappedTerms.NAME:
            case LongTerms.NAME:
            case DoubleTerms.NAME:
                return new Terms(json);

            case DateRangeAggregationBuilder.NAME:
            case GeoDistanceAggregationBuilder.NAME:
            case RangeAggregationBuilder.NAME:
            case IpRangeAggregationBuilder.NAME:
                return new Range(json);

            case GeoGridAggregationBuilder.NAME:
                return new GeoHashGrid(json);

            case FilterAggregationBuilder.NAME:
                return new Filter(json);
            case FiltersAggregationBuilder.NAME:
                return new Filters(json);

            case ChildrenAggregationBuilder.NAME:
                return new Children(json);

            case MatrixStatsAggregationBuilder.NAME:
                return new MatrixStats(json);
            default:
                return new Aggregation(json);
        }
    }

    static Aggregation aggregationToDataObject(org.elasticsearch.search.aggregations.Aggregation esAggregation) {
        if (esAggregation instanceof org.elasticsearch.search.aggregations.bucket.terms.Terms) {
            return BucketConverters.termsToDataObject((org.elasticsearch.search.aggregations.bucket.terms.Terms) esAggregation);
        } else if (esAggregation instanceof org.elasticsearch.search.aggregations.bucket.range.Range) {
            return BucketConverters.rangeToDataObject((org.elasticsearch.search.aggregations.bucket.range.Range) esAggregation);
        } else if (esAggregation instanceof org.elasticsearch.search.aggregations.bucket.geogrid.GeoHashGrid) {
            return BucketConverters.geoHashGridToDataObject((org.elasticsearch.search.aggregations.bucket.geogrid.GeoHashGrid) esAggregation);
        } else if (esAggregation instanceof org.elasticsearch.search.aggregations.bucket.filter.Filter) {
            return BucketConverters.singleBucketAggregationToDataObject(new Filter(), (SingleBucketAggregation) esAggregation);
        } else if (esAggregation instanceof org.elasticsearch.search.aggregations.bucket.filter.Filters) {
            return BucketConverters.filtersToDataObject((org.elasticsearch.search.aggregations.bucket.filter.Filters) esAggregation);
        }

        // parent join plugin
        else if (esAggregation instanceof org.elasticsearch.join.aggregations.Children) {
            return BucketConverters.singleBucketAggregationToDataObject(new Children(), (SingleBucketAggregation) esAggregation);
        }


        // matrix plugin
        else if (esAggregation instanceof org.elasticsearch.search.aggregations.matrix.stats.MatrixStats) {
            return MatrixConverters.matrixStatsToDataObject((org.elasticsearch.search.aggregations.matrix.stats.MatrixStats) esAggregation);
        }
        // let some space for other implementations
        else {
            return aggregationToDataObjectRaw(esAggregation);
        }
    }

    static Aggregation aggregationToDataObjectRaw(org.elasticsearch.search.aggregations.Aggregation esAggregation) {
        Aggregation aggregation = new Aggregation();
        JsonObject rawAggreg = CommonConverters.fromXContent(esAggregation);
        return fillCommonAggregationDataObject(aggregation, esAggregation)
                .setData(rawAggreg.getJsonObject(aggregation.getType() + TYPED_KEYS_DELIMITER + aggregation.getName(), new JsonObject()));
    }

    static <T extends Aggregation, U extends org.elasticsearch.search.aggregations.Aggregation> T fillCommonAggregationDataObject(
            T aggregation,
            U esAggregation
    ) {
        if (esAggregation.getMetaData() != null && !esAggregation.getMetaData().isEmpty()) {
            aggregation.setMetaData(new JsonObject(esAggregation.getMetaData()));
        }

        aggregation.setName(esAggregation.getName()).setType(esAggregation.getType());
        return aggregation;
    }
}
