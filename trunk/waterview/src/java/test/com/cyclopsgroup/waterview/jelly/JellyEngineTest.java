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

import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.Script;
import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.lang.StringUtils;
import org.codehaus.plexus.PlexusTestCase;

import com.cyclopsgroup.waterview.FakePageRuntime;
import com.cyclopsgroup.waterview.Page;
import com.cyclopsgroup.waterview.PageRuntime;

/**
 * Test case for jelly waterview
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class JellyEngineTest extends PlexusTestCase
{
    /**
     * Override or implement method of parent class or interface
     *
     * @see org.codehaus.plexus.PlexusTestCase#getConfiguration()
     */
    protected InputStream getConfiguration() throws Exception
    {
        return getClass().getResourceAsStream("components.xml");
    }

    /**
     * Test case to test init tag library method
     *
     * @throws Exception Throw it out
     */
    public void testInit() throws Exception
    {
        JellyEngine je = (JellyEngine) lookup(JellyEngine.ROLE);
        assertNotNull(je);
        Script script = je.getScript("layout/Waterview3ColumnLayout.jelly");
        assertNotSame(script, JellyEngine.DUMMY_SCRIPT);
        JellyContext jc = new JellyContext(je.getGlobalContext());
        jc.setVariable(JellyEngine.RENDERING, Boolean.TRUE);
        jc.setVariable(Page.NAME, new Page());
        StringWriter sw = new StringWriter();
        jc.setVariable(PageRuntime.NAME, new FakePageRuntime(
                new PrintWriter(sw)));
        script.run(jc, XMLOutput.createXMLOutput(sw));
        assertTrue(StringUtils.isNotEmpty(sw.toString()));
    }
}
