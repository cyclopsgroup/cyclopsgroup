package com.cyclopsgroup.waterview;

import org.codehaus.plexus.PlexusTestCase;

import com.cyclopsgroup.waterview.jelly.JellyEngine;
import com.cyclopsgroup.waterview.spi.ModuleService;

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
        System.out.println( path.getPathWithoutExtension() );
    }
}
