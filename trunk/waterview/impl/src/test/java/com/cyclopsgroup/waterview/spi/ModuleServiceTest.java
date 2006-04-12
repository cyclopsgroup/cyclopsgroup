package com.cyclopsgroup.waterview.spi;

import org.codehaus.plexus.PlexusTestCase;

import com.cyclopsgroup.waterview.Path;
import com.cyclopsgroup.waterview.jelly.JellyEngine;

/**
 * @author jiaqi
 *
 */
public class ModuleServiceTest
    extends PlexusTestCase
{
    private ModuleService moduleService;

    /**
     * @see org.codehaus.plexus.PlexusTestCase#setUp()
     */
    protected void setUp()
        throws Exception
    {
        super.setUp();
        lookup( JellyEngine.ROLE );
        moduleService = (ModuleService) lookup( ModuleService.ROLE );
    }

    public void testDefaultPage()
    {
        Page page = moduleService.createDefaultPage();
        assertNotNull( page );
    }

    /**
     * @throws Exception
     */
    public void testGetPackage()
        throws Exception
    {
        Path path = moduleService.parsePath( "/waterview/x/Xyz.jelly" );
        assertEquals( "/x/Xyz", path.getPathWithoutExtension() );
    }

    /**
     * Test the availability of layouts
     */
    public void testLayouts()
    {
        Layout defaultLayout = moduleService.getLayout( ModuleService.DEFAULT_LAYOUT_NAME );
        assertNotNull( defaultLayout );
    }
}
