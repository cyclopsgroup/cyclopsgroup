package com.cyclopsgroup.arapaho.avalon;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;

@MBeanClass("Testing purpose only component implementation")
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

    @MBeanAttribute
    public boolean isInitialized()
    {
        return initialized;
    }

    @MBeanAttribute
    public String getTestValue()
    {
        return testValue;
    }

    @MBeanAttribute
    public void setTestValue( String testValue )
    {
        this.testValue = testValue;
    }

    @MBeanOperation("Reset the values")
    public void reset()
    {
        initialized = false;
        testValue = "raw";
    }
}
