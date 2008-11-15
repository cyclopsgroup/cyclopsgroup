package org.cyclopsgroup.waterview.impl.web;

import org.cyclopsgroup.waterview.WebContext;
import org.cyclopsgroup.waterview.WebModule;
import org.cyclopsgroup.waterview.annotation.Layout;

/**
 * Internal default layout
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
@Layout( name = "defaultLayout", template = "org/cyclopsgroup/waterview/web/DefaultLayout.vm" )
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
