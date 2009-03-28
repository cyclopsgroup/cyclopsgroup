/* ==========================================================================
 * Copyright 2002-2006 Cyclops Group Software Foundation
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
package com.cyclopsgroup.tornado.hibernate.impl.hibernate;

import com.cyclopsgroup.tornado.PersistenceService;
import com.cyclopsgroup.waterview.test.WaterviewTestCase;

/**
 * Test case for hibernate persistence service
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class HibernatePersistenceServiceTest
    extends WaterviewTestCase
{
    /**
     * Test availability of hibernate persistence service
     *
     * @throws Exception Throw it out
     */
    public void testAvaliability()
        throws Exception
    {
        PersistenceService persist = (PersistenceService) lookup( PersistenceService.ROLE );
        assertNotNull( persist );
    }
}
