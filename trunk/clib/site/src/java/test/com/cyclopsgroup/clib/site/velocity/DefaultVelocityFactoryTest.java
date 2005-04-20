/* ==========================================================================
 * Copyright 2002-2005 Cyclops Group Community
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
package com.cyclopsgroup.clib.site.velocity;

import java.io.InputStream;

import org.codehaus.plexus.PlexusTestCase;

/**
 * Test case for default velocity provider
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class DefaultVelocityFactoryTest extends PlexusTestCase
{
    /**
     * Override or implement method of parent class or interface
     *
     * @see org.codehaus.plexus.PlexusTestCase#getConfiguration()
     */
    protected InputStream getConfiguration() throws Exception
    {
        return getClass().getResourceAsStream("components.xml");
    }

    /**
     * Test if velocity engine is successfully provided
     *
     * @throws Exception Just throw it out
     */
    public void testGetVelocityEngine() throws Exception
    {
        VelocityFactory p = (VelocityFactory) lookup(VelocityFactory.ROLE);
        assertNotNull(p.getVelocityEngine());
    }
}
