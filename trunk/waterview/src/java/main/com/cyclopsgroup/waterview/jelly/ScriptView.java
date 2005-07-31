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

import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.Script;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.clib.lang.Context;
import com.cyclopsgroup.waterview.BaseModule;
import com.cyclopsgroup.waterview.Module;
import com.cyclopsgroup.waterview.PageRuntime;
import com.cyclopsgroup.waterview.spi.View;

/**
 * Script view
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class ScriptView extends BaseModule implements View
{

    private Script script;

    /**
     * Constructor for class ScriptView
     *
     * @param script Script object
     * @param module Module to run
     */
    public ScriptView(Script script, Module module)
    {
        if (script == null)
        {
            throw new NullPointerException("Script can not be null");
        }
        this.script = script;
        setModule(module);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.spi.View#render(com.cyclopsgroup.waterview.PageRuntime, com.cyclopsgroup.clib.lang.Context)
     */
    public void render(PageRuntime runtime, Context viewContext)
            throws Exception
    {
        if (getModule() != null)
        {
            getModule().execute(runtime, runtime.getPageContext());
        }
        JellyEngine je = (JellyEngine) runtime.getServiceManager().lookup(
                JellyEngine.ROLE);
        JellyContext jellyContext = je.createJellyContext(viewContext);
        XMLOutput output = XMLOutput.createXMLOutput(runtime.getOutput());
        try
        {
            script.run(jellyContext, output);
        }
        catch (Exception e)
        {
            runtime.getOutput().print("<div>");
            e.printStackTrace(runtime.getOutput());
            runtime.getOutput().println("</div>");
        }
        finally
        {
            output.flush();
        }
    }
}
