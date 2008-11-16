package org.cyclopsgroup.waterview.impl.velocity;

import org.apache.commons.lang.Validate;
import org.apache.velocity.context.Context;
import org.cyclopsgroup.waterview.WebContext;

class VelocityContextAdapter
    implements Context
{
    private final WebContext context;

    /**
     * @param context
     */
    public VelocityContextAdapter( WebContext context )
    {
        Validate.notNull( context, "Source context can't be NULL" );
        this.context = context;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean containsKey( Object key )
    {
        return context.getVariable( key.toString() ) != null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Object get( String key )
    {
        return context.getVariable( key );
    }

    /**
     * @inheritDoc
     */
    @Override
    public Object[] getKeys()
    {
        return context.getVariableNames().toArray();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Object put( String name, Object value )
    {
        return context.setVariable( name, value );
    }

    /**
     * @inheritDoc
     */
    @Override
    public Object remove( Object name )
    {
        return context.setVariable( name.toString(), null );
    }
}
