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
package com.cyclopsgroup.cyclib;

import java.util.HashSet;

import junit.framework.TestCase;

import org.apache.commons.collections.CollectionUtils;

/**
 * Test case for default context
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class DefaultContextTest extends TestCase
{
    /**
     * Test getNames method
     */
    public void testGetNames()
    {
        DefaultContext parent = new DefaultContext();
        parent.put("a", "a");
        parent.put("b", "b");
        DefaultContext ctx = new DefaultContext(parent);
        ctx.put("a", "c");
        ctx.put("d", "d");
        HashSet names = new HashSet();
        CollectionUtils.addAll(names, ctx.keys());
        assertEquals(3, names.size());
    }
}