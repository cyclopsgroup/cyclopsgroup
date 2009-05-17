package org.cyclopsgroup.waterview.impl.web;

import org.cyclopsgroup.waterview.Module;

/**
 * Internal default layout
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class DefaultLayout
{
    /**
     * @return A string
     */
    @Module(path="_layout_/default_layout.vm", returnVariable = "layoutVariable")
    public String render()
    {
        return "fromDefaultLayout";
    }
}
