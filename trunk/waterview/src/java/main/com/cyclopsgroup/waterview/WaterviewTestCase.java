/* ==========================================================================
 * Copyright 2002-2004 Cyclops Group Community
 * 
 * Licensed under the Open Software License, Version 2.1 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://opensource.org/licenses/osl-2.1.php
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * =========================================================================
 */
package com.cyclopsgroup.waterview;

import java.io.File;

import junit.framework.TestCase;

import org.apache.avalon.framework.service.ServiceManager;

/**
 * Test case for waterview
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class WaterviewTestCase extends TestCase
{
    private static ServiceManager serviceManager;

    /**
     * Get service manager
     *
     * @return Service manager
     * @throws Exception Throw it out
     */
    protected synchronized static ServiceManager getServiceManager()
            throws Exception
    {
        if (serviceManager == null)
        {
            WaterviewContainer container = new WaterviewContainer();
            String basedir = new File("src/webapp").getAbsolutePath();
            System.out.println("Root directory is " + basedir);
            container.addContextValue("basedir", basedir);
            container.addContextValue("plexus.home", basedir);
            container.initialize();
            container.start();
            serviceManager = new ServiceManagerAdapter(container);
        }
        return serviceManager;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception
    {
        super.setUp();
        getServiceManager();
    }
}