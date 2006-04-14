package com.cyclopsgroup.waterview.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.codehaus.plexus.PlexusTestCase;

import com.cyclopsgroup.waterview.utils.ServiceManagerAdapter;

public class WaterviewTestCase
    extends PlexusTestCase
{
    public static final String PLEXUS_COMPONENT_DESCRIPTOR = "src/main/webapp/WEB-INF/waterview-components.xml";

    /**
     * Create mock RunData
     * 
     * @return New create rundata
     */
    protected MockRunData createRunData()
    {
        MockRunData data = new MockRunData();
        data.setServiceManager( new ServiceManagerAdapter( getContainer() ) );

        return data;
    }

    /**
     * @see org.codehaus.plexus.PlexusTestCase#getCustomConfiguration()
     */
    protected InputStream getCustomConfiguration()
        throws Exception
    {
        File descriptor = new File( getConstomConfigurationPath() );
        if ( !descriptor.exists() )
        {
            System.out.println( "Plexus descriptor file " + descriptor.getAbsolutePath() + " doesn't exist" );
            return null;
        }
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
