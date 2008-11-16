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

import java.io.PrintWriter;

import junit.framework.TestCase;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Test case for link
 */
public class LinkTest extends TestCase
{
    /**
     * TEst link
     */
    public void testLink()
    {
        MockRuntimeData data = new MockRuntimeData(new PrintWriter(System.out));
        data.setPage("/aaa/Index.vm");
        Link link = new Link(data);
        link.setPage("/Bbb.vm");
        assertEquals(
                "http://localhost:8080/waterview/servlet/waterview/!show!/Bbb.vm",
                link.toString());
    }
}
