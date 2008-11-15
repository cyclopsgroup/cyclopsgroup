package org.cyclopsgroup.waterview;

/**
 * Module for an action
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public interface ActionModule
{
    /**
     * Execute aciton
     * 
     * @param context Module context
     * @return Action execution result
     */
    ActionRedirection executeAction( WebContext context );
}
