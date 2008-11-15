package org.cyclopsgroup.waterview.ipa;

import java.io.IOException;

import org.cyclopsgroup.waterview.WebContext;

/**
 * Context across all valves
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public interface ValveContext
{
    /**
     * @return Web context for processing
     */
    WebContext getWebContext();

    /**
     * Invoke next valve
     * 
     * @param context Web context
     * @return True if there is next valve
     * @throws IOException
     */
    boolean invokeNext( ValveContext context )
        throws IOException;
}
