package org.cyclopsgroup.waterview.impl.valve;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.cyclopsgroup.waterview.WebContext;
import org.cyclopsgroup.waterview.WebModule;
import org.cyclopsgroup.waterview.annotation.Layout;
import org.cyclopsgroup.waterview.annotation.Page;
import org.cyclopsgroup.waterview.impl.module.ModuleResolver;
import org.cyclopsgroup.waterview.impl.render.RuntimeRenderer;
import org.cyclopsgroup.waterview.ipa.Renderer;
import org.cyclopsgroup.waterview.ipa.Valve;
import org.cyclopsgroup.waterview.ipa.ValveContext;
import org.mortbay.log.Log;

/**
 * Valve to render page
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class RenderPageValve
    implements Valve
{
    private final ModuleResolver moduleResolver;

    private final Renderer renderer;

    /**
     * @param moduleResolver Module resolver
     * @param renderer Template renderer
     */
    public RenderPageValve( ModuleResolver moduleResolver, Renderer renderer )
    {
        Validate.notNull( moduleResolver, "Module resolver can't be NULL" );
        Validate.notNull( renderer, "Renderer can't be NULL" );
        this.moduleResolver = moduleResolver;
        this.renderer = renderer;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void invoke( ValveContext context )
        throws IOException
    {
        WebContext wc = context.getWebContext();
        Operations operations = (Operations) wc.getVariable( Operations.NAME );
        String page = operations.take();
        if ( Log.isDebugEnabled() )
        {
            Log.debug( "Page is determined: " + page );
        }
        WebModule pageModule = moduleResolver.findModule( page );
        String layoutName = "";
        if ( pageModule != null )
        {
            Page pageAnnotation = pageModule.getClass().getAnnotation( Page.class );
            layoutName = pageAnnotation == null ? "" : pageAnnotation.layout();
        }
        if ( StringUtils.isEmpty( layoutName ) )
        {
            layoutName = "defaultLayout";
        }
        WebModule layout = moduleResolver.findModule( layoutName );
        Layout layoutAnnotation = layout.getClass().getAnnotation( Layout.class );
        layout.beforeRender( context.getWebContext() );
        wc.setVariable( RuntimeRenderer.NAME, new RuntimeRenderer( renderer, moduleResolver, context.getWebContext() ) );
        renderer.render( context.getWebContext(), layoutAnnotation.template(),
                         context.getWebContext().getServletResponse().getWriter() );
    }
}
