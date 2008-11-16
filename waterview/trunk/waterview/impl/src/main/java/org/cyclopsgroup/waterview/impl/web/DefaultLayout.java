package org.cyclopsgroup.waterview.impl.web;

import org.cyclopsgroup.waterview.View;
import org.cyclopsgroup.waterview.WebContext;
import org.cyclopsgroup.waterview.WebModule;

/**
 * Internal default layout
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
@View( path = "/_layout_/default_layout.vm" )
public class DefaultLayout
    implements WebModule
{
    /**
     * @inheritDoc
     */
    @Override
    public void beforeRender( WebContext context )
    {
    }
}
