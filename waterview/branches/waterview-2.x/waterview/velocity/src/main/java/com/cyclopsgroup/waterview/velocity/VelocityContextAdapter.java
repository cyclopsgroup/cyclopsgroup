package com.cyclopsgroup.waterview.velocity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.cyclopsgroup.waterview.Context;

public class VelocityContextAdapter
    implements org.apache.velocity.context.Context
{
    private Context context;

    public VelocityContextAdapter( Context context )
    {
        this.context = context;
    }

    public boolean containsKey( Object key )
    {
        return context.get( key.toString() ) != null;
    }

    public Object get( String key )
    {
        return context.get( key );
    }

    public Object[] getKeys()
    {
        List<Object> keys = new ArrayList<Object>();
        CollectionUtils.addAll( keys, context.keys() );
        return keys.toArray();
    }

    public Object put( String key, Object value )
    {
        context.put( key, value );
        return value;
    }

    public Object remove( Object key )
    {
        Object ret = context.get( key.toString() );
        context.remove( key.toString() );
        return ret;
    }

}
