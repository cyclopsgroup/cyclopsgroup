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
package com.cyclopsgroup.waterview.jelly;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.Script;
import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.FakePageRuntime;
import com.cyclopsgroup.waterview.Page;
import com.cyclopsgroup.waterview.PageRuntime;
import com.cyclopsgroup.waterview.WaterviewTestCaseBase;

/**
 * Test case for jelly waterview
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class JellyEngineTest extends WaterviewTestCaseBase
{
    /**
     * Test case to test init tag library method
     *
     * @throws Exception Throw it out
     */
    public void testInit() throws Exception
    {
        JellyEngine je = (JellyEngine) lookup(JellyEngine.ROLE);
        assertNotNull(je);
        Script script = je.getScript("com.cyclopsgroup.waterview.ui",
                "layout/Waterview3ColumnLayout.jelly");
        assertNotSame(script, JellyEngine.DUMMY_SCRIPT);
        JellyContext jc = new JellyContext(je.getGlobalContext());

        jc.setVariable(JellyEngine.RENDERING, Boolean.TRUE);
        jc.setVariable(Page.NAME, new Page());
        StringWriter sw = new StringWriter();
        FakePageRuntime runtime = new FakePageRuntime(new PrintWriter(sw));
        XMLOutput output = XMLOutput.createXMLOutput(sw);
        runtime.getPageContext().put(JellyEngine.JELLY_CONTEXT, jc);
        runtime.getPageContext().put(JellyEngine.JELLY_OUTPUT, output);
        jc.setVariable(PageRuntime.NAME, runtime);
        script.run(jc, output);
        assertTrue(StringUtils.isNotEmpty(sw.toString()));
    }
}
