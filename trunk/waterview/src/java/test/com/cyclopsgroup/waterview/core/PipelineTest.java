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
package com.cyclopsgroup.waterview.core;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.cyclopsgroup.waterview.FakePageRuntime;
import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.pipeline.Pipeline;
import com.cyclopsgroup.waterview.spi.PipelineContext;
import com.cyclopsgroup.waterview.spi.Valve;

/**
 * Test case for pipeline
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class PipelineTest extends TestCase
{
    private class TestValve implements Valve
    {
        private String value;

        private TestValve(String value)
        {
            this.value = value;
        }

        /**
         * Override or implement method of parent class or interface
         *
         * @see com.cyclopsgroup.waterview.spi.Valve#invoke(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.waterview.spi.PipelineContext)
         */
        public void invoke(RuntimeData runtime, PipelineContext context)
                throws Exception
        {
            List contents = (List) runtime.getPageContext().get("contents");
            contents.add(value);
            context.invokeNextValve(runtime);
        }
    }

    /**
     * Test invocation
     *
     * @throws Exception
     */
    public void testInvoke() throws Exception
    {
        Pipeline pipeline = new Pipeline();
        FakePageRuntime runtime = new FakePageRuntime(new PrintWriter(
                System.out));
        List contents = new ArrayList();
        runtime.getPageContext().put("contents", contents);
        pipeline.addValve(new TestValve("aaa"));
        pipeline.addValve(new TestValve("bbb"));
        pipeline.addValve(new TestValve("ccc"));
        pipeline.handleRuntime(runtime);
        assertEquals(3, contents.size());
        Object[] c = contents.toArray();
        assertEquals("aaa", c[0]);
        assertEquals("bbb", c[1]);
        assertEquals("ccc", c[2]);
    }
}
