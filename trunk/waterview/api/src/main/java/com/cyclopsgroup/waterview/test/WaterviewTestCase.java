package com.cyclopsgroup.waterview.test;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.avalon.framework.service.ServiceManager;
import org.codehaus.plexus.PlexusTestCase;

import com.cyclopsgroup.waterview.RunData;
import com.cyclopsgroup.waterview.utils.ServiceManagerAdapter;

public class WaterviewTestCase
    extends PlexusTestCase
{
    public static final String PLEXUS_COMPONENT_DESCRIPTOR = "src/main/webapp/WEB-INF/waterview-components.xml";

    protected ServiceManager serviceManager;

    /**
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp()
        throws Exception
    {
        super.setUp();
        serviceManager = new ServiceManagerAdapter( getContainer() );
    }

    /**
     * Create mock RunData
     * 
     * @return New create rundata
     */
    protected RunData createRunData()
    {
        MockRunData data = new MockRunData();
        data.setServiceManager( getServiceManager() );

        return data;
    }

    /**
     * Get avalon service manager
     * 
     * @return ServiceManager object
     */
    public ServiceManager getServiceManager()
    {
        return serviceManager;
    }

    /**
     * @see org.codehaus.plexus.PlexusTestCase#getCustomConfiguration()
     */
    protected InputStream getCustomConfiguration()
        throws Exception
    {
        return new FileInputStream( getConstomConfigurationPath() );
    }

    /**
     * Get the file path of custom plexus configuration
     * 
     * @return Path of custom configuration file
     */
    protected String getConstomConfigurationPath()
    {
        return PLEXUS_COMPONENT_DESCRIPTOR;
    }
}
