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

    /**
     * @throws Exception
     */
    public void testGetPackage()
        throws Exception
    {
        lookup( JellyEngine.ROLE );
        ModuleService mm = (ModuleService) lookup( ModuleService.ROLE );
        Path path = mm.parsePath( "/waterview/x/Xyz.jelly" );
        assertEquals( "/x/Xyz", path.getPathWithoutExtension() );
    }
}
