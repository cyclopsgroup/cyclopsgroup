package org.cyclopsgroup.streammarker;

import org.apache.commons.lang.ObjectUtils;

public class ConstantProvider<T>
    implements Provider<T>
{
    public static <T> ConstantProvider<T> of( T object )
    {
        return new ConstantProvider<T>( object );
    }

    private final T object;

    private ConstantProvider( T object )
    {
        this.object = object;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean equals( Object obj )
    {
        if ( obj == null || ( !( obj instanceof ConstantProvider ) ) )
        {
            return false;
        }
        return ObjectUtils.equals( object, getClass().cast( obj ).object );
    }

    /**
     * @inheritDoc
     */
    @Override
    public int hashCode()
    {
        return object == null ? 0 : object.hashCode();
    }

    /**
     * @InheritDoc
     */
    @Override
    public T provide()
    {
        return object;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString()
    {
        return "Provider(" + object + ")";
    }
}
