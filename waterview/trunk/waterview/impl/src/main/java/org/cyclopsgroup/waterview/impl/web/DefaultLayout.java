package org.cyclopsgroup.waterview.impl.web;

import org.cyclopsgroup.waterview.Mapping;
import org.cyclopsgroup.waterview.RenderOutput;

/**
 * Internal default layout
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
@Mapping( path = "_layout_/default_layout.vm" )
public class DefaultLayout
{
    /**
     * @return A string
     */
    public void render( RenderOutput output )
    {
        output.setVariable( "layoutVariable", "fromDefaultLayout" );
    }
}
