package com.cyclopsgroup.waterview;

import org.codehaus.plexus.PlexusTestCase;

import com.cyclopsgroup.waterview.jelly.JellyEngine;
import com.cyclopsgroup.waterview.spi.ModuleManager;

/**
 * @author jiaqi
 *
 */
public class ModuleManagerTest extends PlexusTestCase
{

    /**
     * @throws Exception
     */
    public void testGetPackage() throws Exception
    {
        lookup(JellyEngine.ROLE);
        ModuleManager mm = (ModuleManager) lookup(ModuleManager.ROLE);
        Path path = mm.parsePath("/waterview/x/Xyz.jelly");
        System.out.println(path);
    }
}
