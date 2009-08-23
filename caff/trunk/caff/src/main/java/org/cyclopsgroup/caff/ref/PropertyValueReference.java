package org.cyclopsgroup.caff.ref;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Implementation of value holder based on reader and writer methods
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 *
 * @param <T> Type of property
 */
class PropertyValueReference<T>
    implements ValueReference<T>
{
    private static Method nullIfNotPublic( Method method )
    {
        if ( method == null )
        {
            return null;
        }
        return Modifier.isPublic( method.getModifiers() ) ? method : null;
    }

    private final String name;

    private final Method reader;

    private final Method writer;

    /**
     * @param descriptor Property descriptor of property
     */
    PropertyValueReference( PropertyDescriptor descriptor )
    {
        name = descriptor.getName();
        reader = nullIfNotPublic( descriptor.getReadMethod() );
        writer = nullIfNotPublic( descriptor.getWriteMethod() );
    }

    @Override
    public final String getName()
    {
        return name;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isReadable()
    {
        return reader != null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isWritable()
    {
        return writer != null;
    }

    /**
     * @inheritDoc
     */
    @SuppressWarnings( "unchecked" )
    @Override
    public T readValue( Object owner )
    {
        if ( reader == null )
        {
            throw new IllegalStateException( "Property " + name + " isn't readable" );
        }
        try
        {
            return (T) reader.invoke( owner );
        }
        catch ( IllegalAccessException e )
        {
            throw new AccessFailureException( "Can't read property " + name + " from object " + owner, e );
        }
        catch ( InvocationTargetException e )
        {
            throw new AccessFailureException( "Can't read property " + name + " from object " + owner, e );
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void writeValue( T value, Object owner )
    {
        if ( writer == null )
        {
            throw new IllegalStateException( "Property " + name + " isn't writable" );
        }
        try
        {
            writer.invoke( owner, value );
        }
        catch ( IllegalAccessException e )
        {
            throw new AccessFailureException( "Can't set property " + name + " of object " + owner + " to " + value, e );
        }
        catch ( InvocationTargetException e )
        {
            throw new AccessFailureException( "Can't set property " + name + " of object " + owner + " to " + value, e );
        }
    }
}
