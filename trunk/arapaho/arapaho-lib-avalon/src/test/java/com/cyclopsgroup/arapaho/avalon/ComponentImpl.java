package com.cyclopsgroup.arapaho.avalon;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;

public class ComponentImpl
    implements Configurable, Initializable, Component
{
    public static final String ROLE = ComponentImpl.class.getName();

    private boolean initialized;

    private String testValue;

    /**
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure( Configuration conf )
        throws ConfigurationException
    {
        testValue = conf.getChild( "test-value" ).getValue();
    }

    /**
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
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

    public void setTestValue( String testValue )
    {
        this.testValue = testValue;
    }

    public void reset()
    {
        initialized = false;
        testValue = "raw";
    }
}
