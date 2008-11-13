package com.cyclopsgroup.waterview.impl;

import com.cyclopsgroup.waterview.spi.PipelineContext;
import com.cyclopsgroup.waterview.spi.ResourceRegistry;
import com.cyclopsgroup.waterview.spi.RunDataSpi;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Valve to render page into the HTTP output
 */
public class RenderPageWithLayoutValve
    extends RenderPageValve
{
    private String layoutPath;

    public RenderPageWithLayoutValve( String layoutPath, ResourceRegistry resourceRegistry )
    {
        super( resourceRegistry );
        this.layoutPath = layoutPath;
    }

    public String getLayoutPath()
    {
        return layoutPath;
    }

    @Override
    public void invoke( RunDataSpi data, PipelineContext context )
        throws Exception
    {
        renderPage( data, layoutPath, data.getRequestContext(), data.getOutput() );
        context.invokeNextValve( data );
    }
}