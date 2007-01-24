package com.cyclopsgroup.waterview.spi;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;

import com.cyclopsgroup.waterview.WebUIToolManager;

public class WebUIToolManagerImpl
    extends AbstractLogEnabled
    implements WebUIToolManager, Configurable, Serviceable, Initializable
{
    private ServiceManager serviceManager;

    private Map<String, WebUIToolFactory> toolFactories = new ConcurrentHashMap<String, WebUIToolFactory>();

    private Map<String, String> toolFactoryRoles = new HashMap<String, String>();

    public void configure( Configuration config )
        throws ConfigurationException
    {
        for ( Configuration toolConfig : config.getChildren( "tool" ) )
        {
            String name = toolConfig.getAttribute( "name" );
            toolFactoryRoles.put( name, toolConfig.getAttribute( "role" ) );
        }
    }

    public void destroyTools( HttpServlet servlet )
    {
        for ( WebUIToolFactory toolFactory : toolFactories.values() )
        {
            try
            {
                toolFactory.destroy( servlet );
            }
            catch ( Exception e )
            {
                getLogger().error( "Destroying tool with servlet failed", e );
            }
        }
    }

    /**
     * @see com.cyclopsgroup.waterview.WebUIToolManager#getTool(java.lang.String, javax.servlet.http.HttpServlet, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public Object getTool( String toolName, HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp )
    {
        WebUIToolFactory toolFactory = toolFactories.get( toolName );
        if ( toolFactory == null )
        {
            return null;
        }
        try
        {
            return toolFactory.getTool( servlet, req, resp );
        }
        catch ( Exception e )
        {
            getLogger().warn( "Getting tool " + toolName + " error", e );
            return null;
        }
    }

    public void initialize()
        throws Exception
    {
        for ( String name : toolFactoryRoles.keySet() )
        {
            String role = toolFactoryRoles.get( name );
            WebUIToolFactory toolFactory = (WebUIToolFactory) serviceManager.lookup( role );
            toolFactories.put( name, toolFactory );
        }
    }

    public void initTools( HttpServlet servlet )
    {
        for ( WebUIToolFactory toolFactory : toolFactories.values() )
        {
            try
            {
                toolFactory.init( servlet );
            }
            catch ( Exception e )
            {
                getLogger().error( "Initializing tool with servlet failed", e );
            }
        }
    }

    public void service( ServiceManager serviceManager )
        throws ServiceException
    {
        this.serviceManager = serviceManager;
    }
}
