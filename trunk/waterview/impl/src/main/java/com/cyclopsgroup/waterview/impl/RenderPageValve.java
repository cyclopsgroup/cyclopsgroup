package com.cyclopsgroup.waterview.impl;

import java.io.Writer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import com.cyclopsgroup.waterview.Context;
import com.cyclopsgroup.waterview.ServiceManager;
import com.cyclopsgroup.waterview.View;
import com.cyclopsgroup.waterview.ViewContext;
import com.cyclopsgroup.waterview.spi.PipelineContext;
import com.cyclopsgroup.waterview.spi.ResourceRegistry;
import com.cyclopsgroup.waterview.spi.RunDataSpi;
import com.cyclopsgroup.waterview.spi.TemplateEngine;
import com.cyclopsgroup.waterview.spi.TemplateNotFoundException;
import com.cyclopsgroup.waterview.spi.Valve;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Valve to render page into the HTTP output
 */
public class RenderPageValve
    implements Valve
{
    public class HttpError404
        extends HttpError
    {
        private static final long serialVersionUID = 1L;

        private HttpError404( String path )
        {
            super( 404, "Resource " + path + " not found!" );
        }
    }

    public class ViewRenderer
    {
        private Context context;

        private RunDataSpi data;

        private ViewRenderer( RunDataSpi data, Context context )
        {
            this.data = data;
            this.context = context;
        }

        public void renderView( String templatePath )
            throws Exception
        {
            try
            {
                renderPage( data, templatePath, context, data.getOutput() );
            }
            catch ( Exception e )
            {
                data.getOutput().print( "<pre>" );
                e.printStackTrace( data.getOutput() );
                data.getOutput().print( "</pre>" );
            }
        }
    }

    private ResourceRegistry resourceRegistry;

    private ServiceManager serviceManager;

    private Map<String, TemplateEngine> templateEngines = new ConcurrentHashMap<String, TemplateEngine>();

    public RenderPageValve( ResourceRegistry resourceRegistry )
    {
        this.resourceRegistry = resourceRegistry;
    }

    public void addTemplateEngine( String pattern, TemplateEngine templateEngine )
    {
        templateEngines.put( pattern, templateEngine );
    }

    public ResourceRegistry getResourceRegistry()
    {
        return resourceRegistry;
    }

    public ServiceManager getServiceManager()
    {
        return serviceManager;
    }

    public void invoke( RunDataSpi data, PipelineContext context )
        throws Exception
    {
        renderPage( data, data.getRequestPath(), data.getRequestContext(), data.getOutput() );
        context.invokeNextValve( data );
    }

    public void renderPage( RunDataSpi data, String templatePath, Context context, Writer output )
        throws Exception
    {
        ViewContext viewContext = new ViewContext( context );
        viewContext.setTemplatePath( templatePath );

        String viewClassName = resourceRegistry.getFullClassName( templatePath );
        try
        {
            View view = (View) Class.forName( viewClassName ).newInstance();
            view.setServiceManager( serviceManager );
            viewContext.put( "view", view );
            view.preRender( data, viewContext );
        }
        catch ( ClassNotFoundException e )
        {

        }

        String path = viewContext.getTemplatePath();
        viewContext.put( "templatePath", path );
        viewContext.put( "renderer", new ViewRenderer( data, viewContext ) );
        boolean renderred = false;
        for ( String pattern : templateEngines.keySet() )
        {
            if ( Pattern.matches( '^' + pattern + '$', path ) )
            {
                TemplateEngine engine = templateEngines.get( pattern );
                try
                {
                    engine.mergeTemplate( path, viewContext, output );
                }
                catch ( TemplateNotFoundException e )
                {
                    throw new HttpError404( path );
                }
                output.flush();
                renderred = true;
                break;
            }
        }
        if ( !renderred )
        {
            throw new HttpError404( path );
        }
    }

    public void setServiceManager( ServiceManager serviceManager )
    {
        this.serviceManager = serviceManager;
    }

    public void setTemplateEngines( Map<String, TemplateEngine> engines )
    {
        templateEngines.putAll( engines );
    }
}