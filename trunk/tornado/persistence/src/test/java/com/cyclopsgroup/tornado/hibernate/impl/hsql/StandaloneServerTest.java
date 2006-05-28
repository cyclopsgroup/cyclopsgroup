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
package com.cyclopsgroup.tornado.hibernate.impl.hsql;

import com.cyclopsgroup.tornado.impl.hsql.StandaloneServer;
import com.cyclopsgroup.waterview.test.WaterviewTestCase;

/**
 * Test case for StandaloneServer
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class StandaloneServerTest
    extends WaterviewTestCase
{
    /**
     * Test if the component is avaliable
     *
     * @throws Exception
     */
    public void testAvailability()
        throws Exception
    {
        StandaloneServer ss = (StandaloneServer) lookup( StandaloneServer.ROLE );
        assertNotNull( ss );
    }
}
