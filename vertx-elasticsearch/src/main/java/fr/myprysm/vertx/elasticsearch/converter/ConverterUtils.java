package fr.myprysm.vertx.elasticsearch.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Conversion utilities.
 */
public interface ConverterUtils {

    /**
     * Converts the items in the collection with the converter.
     *
     * @param items     the items
     * @param converter the converter
     * @param <In>      the input type
     * @param <Out>     the output type
     * @return the list with converted items
     */
    static <In, Out> List<Out> convert(Collection<In> items, Converter<In, Out> converter) {
        List<Out> outList = new ArrayList<>(items.size());
        for (In item : items) {
            outList.add(converter.convert(item));
        }
        return outList;
    }

    /**
     * Convert the values in the map with the converter.
     *
     * @param map       the map
     * @param converter the converter
     * @param <In>      the input type
     * @param <Out>     the output type
     * @return the map with converted items
     */
    static <In, Out> Map<String, Out> convert(Map<String, In> map, Converter<In, Out> converter) {
        Map<String, Out> outMap = new HashMap<>(map.size());
        for (Map.Entry<String, In> entry : map.entrySet()) {
            outMap.put(entry.getKey(), converter.convert(entry.getValue()));
        }
        return outMap;
    }
}
