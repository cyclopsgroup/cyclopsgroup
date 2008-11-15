package org.cyclopsgroup.waterview;

/**
 * Executable module called for view, page or action
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public interface WebModule
{
    /**
     * Prepare the module
     * 
     * @param context Context of module
     */
    void beforeRender( WebContext context );
}
