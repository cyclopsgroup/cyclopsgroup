package org.cyclopsgroup.waterview.impl.pipeline;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.cyclopsgroup.waterview.WebContext;
import org.cyclopsgroup.waterview.spi.Valve;

/**
 * Pipeline that executes valves
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class Pipeline
{
    private final List<Valve> valves;

    /**
     * @param valves List of valves
     */
    public Pipeline( List<Valve> valves )
    {
        Validate.notNull( valves, "Valves can't be NULL" );
        Validate.isTrue( !valves.isEmpty(), "Valves can't be empty" );
        this.valves = Collections.unmodifiableList( new ArrayList<Valve>( valves ) );
    }

    /**
     * @param webContext Web context to invoke
     * @throws IOException
     */
    public void invoke( WebContext webContext )
        throws IOException
    {
        DefaultValveContext context = new DefaultValveContext( valves, webContext );
        context.invokeNext( context );
    }
}
