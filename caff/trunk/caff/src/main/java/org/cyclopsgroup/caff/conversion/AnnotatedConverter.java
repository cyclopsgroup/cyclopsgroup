package org.cyclopsgroup.caff.conversion;

import java.lang.annotation.Annotation;

/**
 * Converter that converts based on rules defined in annotation
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 * @param <T> Type of value to converter from/to
 */
public class AnnotatedConverter<T>
    implements Converter<T>
{
    private final Converter<T> proxy;

    /**
     * @param type Value type to convert from/to
     * @param annotation Annotation that defines conversion rule
     */
    public AnnotatedConverter( Class<T> type, Annotation annotation )
    {
        if ( annotation == null )
        {
            throw new NullPointerException( "Input annotation can't be NULL" );
        }
        ConversionSupport support = annotation.annotationType().getAnnotation( ConversionSupport.class );
        if ( support == null )
        {
            throw new IllegalArgumentException( "Annotation " + annotation + " is not annotated with "
                + ConversionSupport.class );
        }
        try
        {
            proxy = support.factoryType().newInstance().getConverterFor( type, annotation );
        }
        catch ( InstantiationException e )
        {
            throw new RuntimeException( "Can't create converter for " + annotation, e );
        }
        catch ( IllegalAccessException e )
        {
            throw new RuntimeException( "Can't create converter for " + annotation, e );
        }
    }

    /**
     * @inheritDoc
     */
    public T fromCharacters( CharSequence text )
    {
        return proxy.fromCharacters( text );
    }

    /**
     * @inheritDoc
     */
    public CharSequence toCharacters( T value )
    {
        return proxy.toCharacters( value );
    }
}
