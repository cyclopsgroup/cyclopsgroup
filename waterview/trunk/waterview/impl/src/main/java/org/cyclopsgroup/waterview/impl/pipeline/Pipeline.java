package org.cyclopsgroup.waterview.impl.pipeline;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.cyclopsgroup.waterview.WebContext;
import org.cyclopsgroup.waterview.ipa.Valve;

public class Pipeline
{
    private final List<Valve> valves;

    public Pipeline( List<Valve> valves )
    {
        Validate.notNull( valves, "Valves can't be NULL" );
        Validate.isTrue( !valves.isEmpty(), "Valves can't be empty" );
        this.valves = Collections.unmodifiableList( new ArrayList<Valve>( valves ) );
    }

    public void invoke( WebContext webContext )
        throws IOException
    {
        DefaultValveContext context = new DefaultValveContext( valves, webContext );
        context.invokeNext( context );
    }
}
