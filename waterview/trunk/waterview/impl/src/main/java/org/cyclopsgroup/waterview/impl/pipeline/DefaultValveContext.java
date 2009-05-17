package org.cyclopsgroup.waterview.impl.pipeline;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.cyclopsgroup.waterview.spi.Valve;
import org.cyclopsgroup.waterview.spi.ValveContext;
import org.cyclopsgroup.waterview.spi.WebContext;


/**
 * Default implementation of {@link ValveContext}. It's used to pass objects across valves in pipeline.
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class DefaultValveContext
    implements ValveContext
{
    private List<String> actions = Collections.emptyList();

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
    public List<String> getActions()
    {
        return actions;
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
    public boolean invokeNext()
        throws IOException
    {
        if ( valveIterator.hasNext() )
        {
            Valve next = valveIterator.next();
            next.invoke( this );
            return true;
        }
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setActions( List<String> actions )
    {
        Validate.notNull( actions, "Actions can't be NULL" );
        this.actions = actions;
    }
}
