package org.cyclopsgroup.waterview.impl.valve;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.lang.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cyclopsgroup.waterview.WebContext;
import org.cyclopsgroup.waterview.impl.module.ModuleResolver;
import org.cyclopsgroup.waterview.impl.render.RuntimePage;
import org.cyclopsgroup.waterview.impl.render.RuntimeRenderer;
import org.cyclopsgroup.waterview.ipa.Renderer;
import org.cyclopsgroup.waterview.ipa.Valve;
import org.cyclopsgroup.waterview.ipa.ValveContext;

/**
 * Valve to render page
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class RenderPageValve
    implements Valve

{
    private static final Log LOG = LogFactory.getLog( RenderPageValve.class );

    private final ModuleResolver moduleResolver;

    private final Renderer renderer;

    private final String defaultTemplate;

    /**
     * @param moduleResolver Module resolver
     * @param renderer Template renderer
     * @param defaultTemplate Default template to render
     */
    public RenderPageValve( ModuleResolver moduleResolver, Renderer renderer, String defaultTemplate )
    {
        Validate.notNull( moduleResolver, "Module resolver can't be NULL" );
        Validate.notNull( renderer, "Renderer can't be NULL" );
        this.moduleResolver = moduleResolver;
        this.renderer = renderer;
        this.defaultTemplate = defaultTemplate;
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
        if ( operations.values().isEmpty() )
        {
            LOG.info( "No page to render, exit" );
            context.invokeNext( context );
            return;
        }
        String page = operations.take();
        if ( LOG.isDebugEnabled() )
        {
            LOG.debug( "Page is determined: " + page );
        }
        if ( !operations.values().isEmpty() )
        {
            LOG.info( "These operations are ignored:" + operations.values() );
        }
        wc.setVariable( RuntimePage.PAGE_NAME, new RuntimePage( page, moduleResolver.findModule( page ) ) );
        wc.setVariable( "request", wc.getServletRequest() );
        wc.setVariable( "response", wc.getServletResponse() );
        RuntimeRenderer r = new RuntimeRenderer( renderer, moduleResolver, wc );
        PrintWriter output = wc.getServletResponse().getWriter();
        try
        {
            r.render( defaultTemplate );
        }
        finally
        {
            output.flush();
        }
    }
}
