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
package com.cyclopsgroup.amulets.cyclops;

import java.util.List;

import junit.framework.TestCase;

/**
 * Test add method
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class BaggedListTest extends TestCase
{
    /**
     * Test add method
     */
    public void testAdd()
    {
        BaggedList bl = new BaggedList();
        bl.setBagSize(3);
        bl.add("a");
        bl.add("b");
        bl.add("c");
        bl.add("d");
        List bags = bl.getBags();
        assertEquals(2, bags.size());
        List list1 = (List) bags.get(0);
        List list2 = (List) bags.get(1);
        assertEquals(3, list1.size());
        assertEquals(1, list2.size());
        assertEquals(0, list1.indexOf("a"));
        assertEquals(1, list1.indexOf("b"));
        assertEquals(2, list1.indexOf("c"));
    }
}