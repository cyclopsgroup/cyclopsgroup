package org.cyclopsgroup.caff.ref;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Implementation of value reference that based on a public field
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 * @param <T> Type of value
 */
class FieldValueReference<T>
    implements ValueReference<T>
{
    /**
     * @param field Reflection field object
     */
    FieldValueReference( Field field )
    {
        if ( field == null )
        {
            throw new NullPointerException( "Field can't be NULL" );
        }
        if ( Modifier.isPublic( field.getModifiers() ) )
        {
            throw new IllegalArgumentException( "Only public field can be directly referenced" );
        }
        this.field = field;
    }

    private final Field field;

    /**
     * @inheritDoc
     */
    public String getName()
    {
        return field.getName();
    }

    /**
     * @inheritDoc
     */
    public boolean isReadable()
    {
        return true;
    }

    /**
     * @inheritDoc
     */
    public boolean isWritable()
    {
        return !Modifier.isFinal( field.getModifiers() );
    }

    /**
     * @inheritDoc
     */
    @SuppressWarnings( "unchecked" )
    public T readValue( Object owner )
        throws AccessFailureException
    {
        try
        {
            return (T) field.get( owner );
        }
        catch ( IllegalAccessException e )
        {
            throw new AccessFailureException( "Can't get field " + field + " of " + owner, e );
        }
    }

    /**
     * @inheritDoc
     */
    public void writeValue( T value, Object owner )
        throws AccessFailureException
    {
        try
        {
            field.set( owner, value );
        }
        catch ( IllegalAccessException e )
        {
            throw new AccessFailureException( "Can't set field " + field + " of " + owner + " to " + value, e );
        }
    }
}
