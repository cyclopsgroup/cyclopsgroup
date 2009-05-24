package org.cyclopsgroup.waterview.impl.module;

import org.cyclopsgroup.waterview.spi.WebContext;

/**
 * Internally used interface for rendering something
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public interface WebModule
{
    /**
     * @param context Current web context
     */
    void render(WebContext context);
    
    /**
     * @return Path this module is mapped to
     */
    String getPath();
    
    /**
     * @return Specific template path
     */
    String getTemplate();
}
