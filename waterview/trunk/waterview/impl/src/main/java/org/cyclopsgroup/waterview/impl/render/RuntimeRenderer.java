package org.cyclopsgroup.waterview.impl.render;

import java.io.IOException;
import java.io.PrintWriter;

import org.cyclopsgroup.waterview.impl.module.ModuleResolver;
import org.cyclopsgroup.waterview.impl.module.WebModule;
import org.cyclopsgroup.waterview.spi.Renderer;
import org.cyclopsgroup.waterview.spi.WebContext;

/**
 * Renderer used in pages. This object is the {@literal $renderer} that developer can refer in template.
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class RuntimeRenderer
{
    private static final String RENDERER_NAME = "renderer";

    private static final String SELF_NAME = "self";

    private final WebContext context;

    private final Renderer renderer;

    private final ModuleResolver resolver;

    /**
     * @param renderer
     * @param resolver
     * @param context
     */
    public RuntimeRenderer( Renderer renderer, ModuleResolver resolver, WebContext context )
    {
        this.renderer = renderer;
        this.context = context;
        this.resolver = resolver;
    }

    /**
     * @param path Path of view to render
     * @throws IOException Thrown for IO error
     */
    public void render( String path )
        throws IOException
    {
        WebModule module = resolver.findModule( path );
        WebContext childContext = new ChildWebContext( context, path );
        if ( module != null )
        {
            childContext.setVariable( SELF_NAME, new RuntimeView( path, module ) );
        }
        childContext.setVariable( RENDERER_NAME, new RuntimeRenderer( renderer, resolver, childContext ) );
        childContext.setVariable( WebContext.CONTEXT_NAME, childContext );
        PrintWriter output = context.getServletResponse().getWriter();
        output.flush();
        try
        {
            if ( module != null )
            {

                module.render( childContext );
            }
            renderer.render( childContext, path, output );
        }
        catch ( Throwable e )
        {
            output.print( "<pre class=\"errorTraceStack\">" );
            e.printStackTrace( output );
            output.print( "</pre>" );
        }
        finally
        {
            output.flush();
        }
    }

    /**
     * @param page Page POJO to render
     * @throws IOException
     */
    public void render( RuntimePage page )
        throws IOException
    {
        render( page.getPath() );
    }
}
