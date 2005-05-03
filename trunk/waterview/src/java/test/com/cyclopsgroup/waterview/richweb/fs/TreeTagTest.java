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
package com.cyclopsgroup.waterview.richweb.fs;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.Script;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.FakePageRuntime;
import com.cyclopsgroup.waterview.WaterviewTestCaseBase;
import com.cyclopsgroup.waterview.jelly.JellyEngine;

/**
 * Test case base
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class TreeTagTest extends WaterviewTestCaseBase
{
    /**
     * Test do tag
     *
     * @throws Exception Throw it out
     */
    public void testDoTag() throws Exception
    {
        JellyEngine je = (JellyEngine) lookup(JellyEngine.ROLE);
        Script script = je.getScript("TestTreeScript.jelly");
        JellyContext jc = new JellyContext(je.getGlobalContext());
        File root = new File("src/java/test/com/cyclopsgroup/waterview/richweb");
        jc.setVariable("tree", new DirectoryTree(root));
        jc.setVariable(FakePageRuntime.NAME, new FakePageRuntime(
                new PrintWriter(System.out)));
        StringWriter out = new StringWriter();
        script.run(jc, XMLOutput.createXMLOutput(out));
        String expected = "<p>richweb<p><p>fs<p><p>DirectoryTreeTest.java<p></p>"
                + "</p><p>TreeTagTest.java<p></p></p>" + "</p></p></p></p>";
        assertTrue(out.toString().indexOf(expected) != -1);
    }
}
