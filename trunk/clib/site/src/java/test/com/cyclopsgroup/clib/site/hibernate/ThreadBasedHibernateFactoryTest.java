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
package com.cyclopsgroup.clib.site.hibernate;

import java.io.InputStream;

import org.codehaus.plexus.PlexusTestCase;
import org.hibernate.Session;

/**
 * Test case for hibernate provider
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class ThreadBasedHibernateFactoryTest extends PlexusTestCase
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
     * Override or implement method of parent class or interface
     *
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception
    {
        HibernateFactory hp = (HibernateFactory) lookup(HibernateFactory.ROLE);
        hp.closeCurrentSession();
        super.tearDown();
    }

    /**
     * Test getting session
     *
     * @throws Exception Throw it out
     */
    public void testGetSession() throws Exception
    {
        HibernateFactory hp = (HibernateFactory) lookup(HibernateFactory.ROLE);
        Session session = hp.getCurrentSession();
        assertNotNull(session);
    }
}
