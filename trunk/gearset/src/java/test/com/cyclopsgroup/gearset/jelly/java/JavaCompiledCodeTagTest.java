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
package com.cyclopsgroup.gearset.jelly.java;

import java.lang.reflect.Method;
import java.util.ArrayList;

import junit.framework.TestCase;
import net.janino.ClassBodyEvaluator;

import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.gearset.runtime.MapContext;

/**
 * Test case for JavaCompiledCodeTag class
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class JavaCompiledCodeTagTest extends TestCase
{

    /**
     * Method testCompiler() in class JavaCompiledCodeTagTest
     *
     * @throws Exception
     */
    public void testCompiler() throws Exception
    {
        String script = ("import java.util.*;\n" + "\n"
                + "public static int add(int a, int b) {\n"
                + "    return a + b;\n" + "}\n");

        Class c = new ClassBodyEvaluator(script).evaluate();
        Method m = c.getMethod("add", new Class[]
        { Integer.TYPE, Integer.TYPE });
        Integer res = (Integer) m.invoke(null, new Object[]
        { new Integer(7), new Integer(11), });
        System.out.println("res = " + res);
    }

    /**
     * Method testExecute() in class JavaCompiledCodeTagTest
     * 
     * @throws Exception
     */
    public void testExecute() throws Exception
    {
        JavaCompiledCodeTag tag = new JavaCompiledCodeTag()
        {
            public void invokeBody(XMLOutput output)
            {

            }
        };
        tag.declareVariable("a", "int");
        tag.declareVariable("b", "java.util.List");
        tag.setSourceCode("return new Integer(a);");
        tag.doTag(XMLOutput.createDummyXMLOutput());
        MapContext ctx = new MapContext();
        ctx.put("a", new Integer(3));
        ctx.put("b", new ArrayList());
        System.out.println(tag.execute(ctx));
    }
}