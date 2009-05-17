package org.cyclopsgroup.waterview.spi;

import java.io.IOException;
import java.util.List;

/**
 * Context across all valves
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public interface ValveContext
{
    /**
     * @return List of web actions to execute in this pipeline
     */
    List<String> getActions();
    
    /**
     * @return Web context for processing
     */
    WebContext getWebContext();
    
    /**
     * Invoke next valve
     * 
     * @return True if there is next valve
     * @throws IOException
     */
    boolean invokeNext()
        throws IOException;
    
    /**
     * @param actions List of web actions to execute in this pipeline
     */
    void setActions(List<String> actions);
}
