package fr.myprysm.vertx.elasticsearch.converter;

/**
 * Converter functional interface.
 * <p>
 * Initially designed to map ElasticSearch items to Vert.x Data Objects (and vice-versa),
 * but can be used for any other purpose.
 *
 * @param <In>  the input type
 * @param <Out> the output type
 */
@FunctionalInterface
public interface Converter<In, Out> {

    /**
     * Converts the input object to the output type.
     *
     * @param input the input object
     * @return the converted object
     * @throws ConverterException when conversion fails.
     */
    Out convert(In input) throws ConverterException;
}
