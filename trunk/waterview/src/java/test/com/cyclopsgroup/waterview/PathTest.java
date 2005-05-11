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

import junit.framework.TestCase;

import com.cyclopsgroup.waterview.Path;

/**
 * Test case for container
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class PathTest extends TestCase
{
    /**
     * Test parsePageRequest() method
     *
     * @throws Exception Throw it out
     */
    public void testParsePageRequest() throws Exception
    {
        Path pr = Path.parse("/Abc.vm");
        assertEquals("vm", pr.getExtension());
        assertEquals("", pr.getParentPath());
        assertEquals("Abc", pr.getShortName());
    }

    /**
     * Test parsePageRequest() method
     *
     * @throws Exception Throw it out
     */
    public void testParsePageRequest2() throws Exception
    {
        Path pr = Path.parse("/aaa/bbb/ccc/Abc.vm");
        assertEquals("vm", pr.getExtension());
        assertEquals("aaa/bbb/ccc/", pr.getParentPath());
        assertEquals("Abc", pr.getShortName());
    }

    /**
     * Test parsePageRequest() method
     *
     * @throws Exception Throw it out
     */
    public void testParsePageRequest3() throws Exception
    {
        Path pr = Path.parse("/Abc");
        assertEquals("", pr.getExtension());
        assertEquals("", pr.getParentPath());
        assertEquals("Abc", pr.getShortName());
    }
}