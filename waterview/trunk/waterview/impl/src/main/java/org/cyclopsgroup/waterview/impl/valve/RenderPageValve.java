package org.cyclopsgroup.waterview.impl.valve;

import java.io.IOException;
import java.io.Writer;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.cyclopsgroup.waterview.RenderableModule;
import org.cyclopsgroup.waterview.annotation.Layout;
import org.cyclopsgroup.waterview.annotation.Page;
import org.cyclopsgroup.waterview.impl.module.ModuleResolver;
import org.cyclopsgroup.waterview.impl.render.Renderer;
import org.cyclopsgroup.waterview.ipa.Valve;
import org.cyclopsgroup.waterview.ipa.ValveContext;

public class RenderPageValve
    implements Valve
{
    private final ModuleResolver moduleResolver;

    private final Renderer renderer;

    public RenderPageValve( ModuleResolver moduleResolver, Renderer renderer )
    {
        Validate.notNull( moduleResolver, "Module resolver can't be NULL" );
        Validate.notNull( renderer, "Renderer can't be NULL" );
        this.moduleResolver = moduleResolver;
        this.renderer = renderer;
    }

    @Override
    public void invoke( ValveContext context )
        throws IOException
    {
        RenderableModule module = moduleResolver.findRenderableModule( "name" );
        Page pageAnnotation = module.getClass().getAnnotation( Page.class );

        String layoutName = pageAnnotation == null ? "" : pageAnnotation.layout();
        if ( StringUtils.isEmpty( layoutName ) )
        {
            layoutName = "defaultLayout";
        }
        RenderableModule layout = moduleResolver.findRenderableModule( layoutName );
        Layout layoutAnnotation = layout.getClass().getAnnotation( Layout.class );
        layout.beforeRender( context.getWebContext() );
        context.getWebContext().setVariable( "renderer", new Renderer()
        {
            @Override
            public void render( Writer output, String template )
                throws IOException
            {
                // TODO Auto-generated method stub

            }
        } );
        renderer.render( context.getWebContext().getServletResponse().getWriter(), layoutAnnotation.template() );
    }
}
