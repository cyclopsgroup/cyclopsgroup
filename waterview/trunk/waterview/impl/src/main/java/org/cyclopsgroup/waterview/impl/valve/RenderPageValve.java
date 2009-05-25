package org.cyclopsgroup.waterview.impl.valve;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cyclopsgroup.waterview.impl.module.ModuleResolver;
import org.cyclopsgroup.waterview.impl.module.PageModule;
import org.cyclopsgroup.waterview.impl.module.WebModule;
import org.cyclopsgroup.waterview.impl.render.NullPageModule;
import org.cyclopsgroup.waterview.impl.render.RuntimePage;
import org.cyclopsgroup.waterview.impl.render.RuntimeRenderer;
import org.cyclopsgroup.waterview.spi.Renderer;
import org.cyclopsgroup.waterview.spi.Valve;
import org.cyclopsgroup.waterview.spi.ValveContext;
import org.cyclopsgroup.waterview.spi.WebContext;

/**
 * Valve to render page
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class RenderPageValve
    implements Valve

{
    private static final PageModule DEFAULT_PAGE = new NullPageModule();

    private static final Log LOG = LogFactory.getLog( RenderPageValve.class );

    private final String defaultLayout;

    private final ModuleResolver moduleResolver;

    private final Renderer renderer;

    private final boolean useLayout;

    /**
     * Constructor without layout
     * 
     * @param moduleResolver Module resolver
     * @param renderer Renderer to render page
     */
    public RenderPageValve( ModuleResolver moduleResolver, Renderer renderer )
    {
        this( moduleResolver, renderer, false, null );
    }

    /**
     * @param moduleResolver Module resolver
     * @param renderer Template renderer
     * @param useLayout True if use layout
     * @param defaultLayout Default template to render
     */
    public RenderPageValve( ModuleResolver moduleResolver, Renderer renderer, boolean useLayout, String defaultLayout )
    {
        Validate.notNull( moduleResolver, "Module resolver can't be NULL" );
        Validate.notNull( renderer, "Renderer can't be NULL" );
        this.moduleResolver = moduleResolver;
        this.renderer = renderer;
        this.useLayout = useLayout;
        this.defaultLayout = defaultLayout;
    }

    /**
     * Constructor with layout
     * 
     * @param moduleResolver Module resolver
     * @param renderer Renderer to render page
     * @param defaultLayout Default layout path
     */
    public RenderPageValve( ModuleResolver moduleResolver, Renderer renderer, String defaultLayout )
    {
        this( moduleResolver, renderer, true, defaultLayout );
    }

    /**
     * @inheritDoc
     */
    @Override
    public void invoke( ValveContext context )
        throws IOException
    {
        WebContext wc = context.getWebContext();
        List<String> actions = context.getActions();
        if ( actions.isEmpty() )
        {
            throw new IllegalStateException( "No action is specified" );
        }

        String page = actions.remove( actions.size() - 1 );
        if ( page == null )
        {
            throw new IllegalStateException( "Last action is NULL" );
        }
        if ( LOG.isDebugEnabled() )
        {
            LOG.debug( "Page is determined: " + page );
        }
        WebModule webModule = moduleResolver.findModule( page );
        PageModule pageModule =
            ( webModule != null && webModule instanceof PageModule ) ? (PageModule) webModule : DEFAULT_PAGE;
        HttpServletResponse response = wc.getServletResponse();

        if ( StringUtils.isNotEmpty( pageModule.getContentType() ) )
        {
            response.setContentType( pageModule.getContentType() );
        }

        // Direct render for raw page
        try
        {
            if ( pageModule.isRaw() )
            {
                renderRaw( response, context, pageModule );
            }
            else
            {
                renderLayout( response, context, pageModule, page );
            }
        }
        catch ( Throwable e )
        {
            response.addHeader( "content-type", "text/plain" );
            PrintWriter output = new PrintWriter( response.getWriter(), true );
            e.printStackTrace( output );
            output.flush();
        }
    }

    private void renderLayout( HttpServletResponse response, ValveContext context, PageModule pageModule, String page )
        throws IOException
    {
        String layoutTempalte;
        if ( useLayout )
        {
            String pageSpecificLayout = pageModule.getLayout();
            layoutTempalte = StringUtils.isEmpty( pageSpecificLayout ) ? defaultLayout : pageSpecificLayout;
        }
        else
        {
            layoutTempalte = page;
        }
        if ( LOG.isDebugEnabled() )
        {
            LOG.debug( "Rending template " + layoutTempalte + " for page " + page );
        }
        context.getWebContext().setVariable( RuntimePage.PAGE_NAME, new RuntimePage( page, pageModule ) );
        RuntimeRenderer r = new RuntimeRenderer( renderer, moduleResolver, context.getWebContext() );
        PrintWriter output = response.getWriter();
        try
        {
            r.render( layoutTempalte );
            context.invokeNext();
        }
        finally
        {
            output.flush();
        }
    }

    private void renderRaw( HttpServletResponse response, ValveContext context, PageModule pageModule )
        throws IOException
    {
        pageModule.render( context.getWebContext() );
        context.invokeNext();
    }
}
