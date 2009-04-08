package org.cyclopsgroup.waterview.impl.web;

import org.cyclopsgroup.waterview.Module;
import org.cyclopsgroup.waterview.WebContext;

/**
 * Internal default layout
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
@Module(path="_layout_/default_layout.vm")
public class DefaultLayout
{
    /**
     * @param context Current web context
     */
    public void render( WebContext context )
    {
        context.setVariable( "layoutVariable", "fromDefaultLayout" );
    }
}
