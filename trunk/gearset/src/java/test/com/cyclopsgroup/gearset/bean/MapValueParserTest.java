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
package com.cyclopsgroup.gearset.bean;

import java.util.HashMap;

import com.cyclopsgroup.gearset.bean.MapValueParser;


import junit.framework.TestCase;

/**
 * Test case for MapValueParser
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class MapValueParserTest extends TestCase
{
    /**
     * Method testDoGetValue() in class MapValueParserTest
     */
    public void testDoGetValue()
    {
        HashMap map = new HashMap();
        map.put("a", new Integer(10));
        map.put("b", "abc");
        MapValueParser parser = new MapValueParser(map);
        assertEquals("10", parser.getString("a"));
        assertEquals("abc", parser.getStrings("b")[0]);
        assertEquals("", parser.getString("c"));
        assertEquals("aaa", parser.getString("d", "aaa"));
    }
}