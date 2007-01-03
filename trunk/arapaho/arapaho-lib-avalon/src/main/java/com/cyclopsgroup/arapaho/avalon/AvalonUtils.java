package com.cyclopsgroup.arapaho.avalon;

import java.util.Properties;

import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.context.Context;
import org.apache.avalon.framework.context.ContextException;

public final class AvalonUtils
{
    public static final String INITIAL_PROPERTIES_KEY = AvalonUtils.class.getName() + "/initialProperties";

    private static final String EMPTY_STRING = "";

    public static final Properties getInitialProperties( Context avalonContext )
    {
        try
        {
            return (Properties) avalonContext.get( INITIAL_PROPERTIES_KEY );
        }
        catch ( ContextException e )
        {
            return null;
        }
    }

    public static Properties getProperties( Configuration propertiesNode )
        throws ConfigurationException
    {
        Properties p = new Properties();
        for ( Configuration c : propertiesNode.getChildren( "property" ) )
        {
            String value = c.getValue( EMPTY_STRING ).trim();
            p.setProperty( c.getAttribute( "name" ), value );
        }
        return p;
    }
}
