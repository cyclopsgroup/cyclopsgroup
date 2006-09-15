package com.cyclopsgroup.waterview.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.codehaus.plexus.PlexusTestCase;
import org.codehaus.plexus.component.repository.exception.ComponentLifecycleException;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;

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
        data.setServiceManager( new ServiceManager()
        {

            public boolean hasService( String key )
            {
                return getContainer().hasComponent( key );
            }

            public Object lookup( String key )
                throws ServiceException
            {
                try
                {
                    return getContainer().lookup( key );
                }
                catch ( ComponentLookupException e )
                {
                    throw new ServiceException( key, "lookup component error", e );
                }
            }

            public void release( Object component )
            {
                try
                {
                    getContainer().release( component );
                }
                catch ( ComponentLifecycleException e )
                {
                }
            }
        } );
        return data;
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

    /**
     * @see org.codehaus.plexus.PlexusTestCase#getCustomConfiguration()
     */
    @Override
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
     * Eliminate the problem of empty test case
     */
    public void testDummy()
    {
        //do nothing
    }
}
