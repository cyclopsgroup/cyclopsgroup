package org.cyclopsgroup.waterview.impl.pipeline;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.cyclopsgroup.waterview.ActionRedirection;
import org.cyclopsgroup.waterview.WebContext;
import org.cyclopsgroup.waterview.ipa.Valve;
import org.cyclopsgroup.waterview.ipa.ValveContext;

public class DefaultValveContext
    implements ValveContext
{
    private final Iterator<Valve> valveIterator;

    private final Map<String, Object> variables = new HashMap<String, Object>();

    private final WebContext webContext;

    private ActionRedirection actionRedirection;

    /**
     * @return
     */
    public final ActionRedirection getActionRedirection()
    {
        return actionRedirection;
    }

    /**
     * @inheritDoc
     */
    @Override
    public final void setActionRedirection( ActionRedirection actionRedirection )
    {
        this.actionRedirection = actionRedirection;
    }

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

    /**
     * @inheritDoc
     */
    @Override
    public Map<String, Object> variables()
    {
        return variables;
    }
}
