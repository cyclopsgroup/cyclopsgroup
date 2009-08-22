package org.cyclopsgroup.caff.conversion;

/**
 * Interface that manages what converter to use
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 * @param <T> Type of value to convert from/to
 */
public interface ConverterFactory<T>
{
    /**
     * @param hint A optional object, value is determined by implementation
     * @return Converter instance that does actual conversion
     */
    Converter<T> getConverterFor( Object hint );
}
