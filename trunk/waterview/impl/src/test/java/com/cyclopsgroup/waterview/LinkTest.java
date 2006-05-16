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
package com.cyclopsgroup.waterview;

import com.cyclopsgroup.waterview.test.MockRunData;
import com.cyclopsgroup.waterview.test.WaterviewTestCase;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Test case for link
 */
public class LinkTest
    extends WaterviewTestCase
{
    /**
     * TEst link
     */
    public void testLink()
        throws Exception
    {
        MockRunData data = createRunData();
        data.setPageBaseUrl( "http://localhost:8080/waterview/servlet/waterview" );
        data.setPage( "/aaa/Index.vm" );
        Link link = new Link( data );
        assertEquals( "http://localhost:8080/waterview/servlet/waterview", link.toString() );
        link.setPage( "/Bbb.vm" );
        assertEquals( "http://localhost:8080/waterview/servlet/waterview/!display!/Bbb.vm", link.toString() );
    }
}
