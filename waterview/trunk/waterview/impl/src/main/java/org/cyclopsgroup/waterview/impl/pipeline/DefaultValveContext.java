package org.cyclopsgroup.waterview.impl.pipeline;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.cyclopsgroup.waterview.WebContext;
import org.cyclopsgroup.waterview.spi.Valve;
import org.cyclopsgroup.waterview.spi.ValveContext;

/**
 * Default implementation of valve context
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class DefaultValveContext
    implements ValveContext
{
    private final Iterator<Valve> valveIterator;

    private final WebContext webContext;

    /**
     * @param valves List of valves
     * @param webContext Web context
     */
    public DefaultValveContext( List<Valve> valves, WebContext webContext )
    {
        Validate.notNull( valves, "Valves can't be NULL" );
        Validate.notNull( webContext, "Web context can't be NULL" );

        valveIterator = valves.iterator();
        this.webContext = webContext;
    }

    /**
     * @inheritDoc
     */
    @Override
    public WebContext getWebContext()
    {
        return webContext;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean invokeNext( ValveContext context )
        throws IOException
    {
        if ( valveIterator.hasNext() )
        {
            Valve next = valveIterator.next();
            next.invoke( context );
            return true;
        }
        else
        {
            return false;
        }
    }
}
