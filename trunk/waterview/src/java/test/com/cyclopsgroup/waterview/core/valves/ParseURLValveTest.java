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
package com.cyclopsgroup.waterview.core.valves;

import java.io.PrintWriter;

import junit.framework.TestCase;

import com.cyclopsgroup.waterview.FakePageRuntime;
import com.cyclopsgroup.waterview.PageRuntime;
import com.cyclopsgroup.waterview.PipelineContext;

/**
 * Test case for ParseURLValve
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class ParseURLValveTest extends TestCase
{

    /**
     * Test invocation
     *
     * @throws Exception
     */
    public void testInvoke() throws Exception
    {
        FakePageRuntime runtime = new FakePageRuntime(new PrintWriter(
                System.out));
        runtime.setRequestPath("/do:aaa|do:bbb|do:ccc|ddd.jelly");
        ParseURLValve v = new ParseURLValve();
        v.invoke(runtime, new PipelineContext()
        {

            public void invokeNextValve(PageRuntime runtime) throws Exception
            {
                //do nothing
            }
        });
        assertEquals("ddd.jelly", runtime.getPage());
        assertEquals(3, runtime.getActions().size());
        assertEquals("aaa", runtime.getActions().get(0));
        assertEquals("bbb", runtime.getActions().get(1));
        assertEquals("ccc", runtime.getActions().get(2));
    }
}
