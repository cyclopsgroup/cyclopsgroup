package com.cyclopsgroup.arapaho.plexus;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;

public class Component
    implements Configurable, Initializable
{
    public static final String ROLE = Component.class.getName();

    private boolean initialized;

    private String testValue;

    public void configure( Configuration conf )
        throws ConfigurationException
    {
        testValue = conf.getChild( "test-value" ).getValue();
    }

    public void initialize()
        throws Exception
    {
        initialized = true;
    }

    public boolean isInitialized()
    {
        return initialized;
    }

    public String getTestValue()
    {
        return testValue;
    }
}
