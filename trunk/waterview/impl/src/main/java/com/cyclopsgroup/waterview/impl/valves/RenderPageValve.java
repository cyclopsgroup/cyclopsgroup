package com.cyclopsgroup.waterview.impl.valves;

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
import com.cyclopsgroup.waterview.spi.Valve;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Valve to render page into the HTTP output
 */
public class RenderPageValve
    implements Valve
{
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
            renderPage( data, templatePath, context );
        }
    }

    private String layoutPath;

    private ResourceRegistry resourceRegistry;

    private ServiceManager serviceManager;

    private Map<String, TemplateEngine> templateEngines = new ConcurrentHashMap<String, TemplateEngine>();

    public RenderPageValve( String layoutPath, ResourceRegistry resourceRegistry )
    {
        this.layoutPath = layoutPath;
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
        renderPage( data, layoutPath, data.getRequestContext() );
    }

    public void renderPage( RunDataSpi data, String templatePath, Context context )
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
        viewContext.put( "templatePath", viewContext.getTemplatePath() );
        viewContext.put( "renderer", new ViewRenderer( data, viewContext ) );
        for ( String pattern : templateEngines.keySet() )
        {
            if ( Pattern.matches( pattern, viewContext.getTemplatePath() ) )
            {
                String fullPath = resourceRegistry.getFullResourcePath( viewContext.getTemplatePath() );
                TemplateEngine engine = templateEngines.get( pattern );
                engine.mergeTemplate( fullPath, viewContext, data.getOutput() );
                data.getOutput().flush();
                break;
            }
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