package com.cyclopsgroup.arapaho.avalon.servlet;

import java.io.FileReader;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.avalon.framework.service.ServiceManager;
import org.codehaus.plexus.DefaultPlexusContainer;
import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.util.StringUtils;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class PlexusHttpServlet
    extends HttpServlet
{
    private PlexusContainer plexusContainer;

    private ServiceManager serviceManager;

    /**
     * Create new plexus container instance
     * 
     * @return
     * @throws Exception
     */
    protected PlexusContainer createPlexusContainer()
        throws Exception
    {
        DefaultPlexusContainer dpc = null;
        String dedicated = getInitParameter( "plexus.container.dedicated" );
        if ( Boolean.valueOf( dedicated ) )
        {
            dpc = new DefaultPlexusContainer();
        }
        else
        {
            ServletContext servletContext = getServletContext();
            synchronized ( servletContext )
            {
                dpc = (DefaultPlexusContainer) servletContext.getAttribute( "plexus.container" );
                if ( dpc == null )
                {
                    dpc = new DefaultPlexusContainer();
                    servletContext.setAttribute( "plexus.container", dpc );
                }
            }
        }
        return dpc;
    }

    @Override
    public void destroy()
    {
        log( "Disposing plexus container" );
        plexusContainer.dispose();
        plexusContainer = null;
        serviceManager = null;
    }

    protected ServiceManager getServiceManager()
    {
        return serviceManager;
    }

    @Override
    public void init()
        throws ServletException
    {
        try
        {
            log( "Creating plexus container instance" );
            plexusContainer = createPlexusContainer();
            initPlexusContainer( plexusContainer );
            serviceManager = new ServiceManagerAdapter( plexusContainer );
        }
        catch ( Exception e )
        {
            throw new ServletException( "Couldn't initialize Plexus container", e );
        }
    }

    /**
     * Initialize plexus container instance
     * 
     * @param plexusContainer
     * @throws Exception
     */
    protected void initPlexusContainer( PlexusContainer plexusContainer )
        throws Exception
    {
        synchronized ( plexusContainer )
        {
            if ( plexusContainer.isStarted() )
            {
                return;
            }
            String plexusConfig = getInitParameter( "plexus.config" );
            if ( StringUtils.isNotEmpty( plexusConfig ) )
            {
                plexusConfig = getServletContext().getRealPath( plexusConfig );

                log( "Initializing plexus container with " + plexusConfig );
                plexusContainer.setConfigurationResource( new FileReader( plexusConfig ) );
                log( "Starting plexus container..." );
                plexusContainer.initialize();
                plexusContainer.start();
            }
        }
    }
}
