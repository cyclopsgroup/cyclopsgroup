package org.cyclopsgroup.waterview.impl.web;

import org.cyclopsgroup.waterview.RenderableModule;
import org.cyclopsgroup.waterview.WebContext;
import org.cyclopsgroup.waterview.annotation.Layout;

@Layout( name = "defaultLayout", template = "org/cyclopsgroup/waterview/web/DefaultLayout.vm" )
public class DefaultLayout
    implements RenderableModule
{
    /**
     * @inheritDoc
     */
    @Override
    public void beforeRender( WebContext context )
    {
    }
}
