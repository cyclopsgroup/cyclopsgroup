package org.cyclopsgroup.waterview.impl.valve;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.cyclopsgroup.waterview.Constants;
import org.cyclopsgroup.waterview.spi.Valve;
import org.cyclopsgroup.waterview.spi.ValveContext;
import org.cyclopsgroup.waterview.spi.WebContext;

/**
 * Valve to set basic global variables
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class SetGlobalVariablesValve
    implements Valve
{
    private Map<String, Object> variables = Collections.emptyMap();

    /**
     * @inheritDoc
     */
    @Override
    public void invoke( ValveContext context )
        throws IOException
    {
        WebContext wc = context.getWebContext();
        wc.setVariable( Constants.REQUEST, wc.getServletRequest() );
        wc.setVariable( Constants.RESPONSE, wc.getServletResponse() );
        for(Map.Entry<String, Object> entry:variables.entrySet())
        {
            wc.setVariable( entry.getKey(), entry.getValue() );
        }
        context.invokeNext();
    }

    /**
     * @param variables A map of additional variables to set. Variable map is not synchronized.
     */
    public final void setVariables( Map<String, Object> variables )
    {
        this.variables = variables;
    }

}
