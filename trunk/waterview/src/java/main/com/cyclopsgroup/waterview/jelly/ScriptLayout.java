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

import com.cyclopsgroup.waterview.BaseModule;
import com.cyclopsgroup.waterview.Module;
import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.spi.Layout;
import com.cyclopsgroup.waterview.spi.Page;

/**
 * Layout with a script support
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class ScriptLayout extends BaseModule implements Layout
{
    private Script script;

    /**
     * Constructor for class JellyScriptLayout
     *
     * @param script Jelly script object
     * @param module Module to run
     */
    public ScriptLayout(Script script, Module module)
    {
        this.script = script;
        if (script == null)
        {
            throw new NullPointerException("Script can not be null");
        }
        setModule(module);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.spi.Layout#render(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.waterview.spi.Page)
     */
    public synchronized void render(RuntimeData runtime, Page page)
            throws Exception
    {
        if (getModule() != null)
        {
            getModule().execute(runtime, runtime.getPageContext());
        }
        runtime.getPageContext().put(Page.NAME, page);
        runtime.getPageContext().put(NAME, this);
        runtime.getPageContext().put(JellyEngine.RENDERING, Boolean.TRUE);

        JellyEngine je = (JellyEngine) runtime.getServiceManager().lookup(
                JellyEngine.ROLE);
        JellyContext jellyContext = je.createJellyContext(runtime
                .getPageContext());
        XMLOutput output = XMLOutput.createXMLOutput(runtime.getOutput());
        script.run(jellyContext, output);
        runtime.getPageContext().put(Page.NAME, null);
        runtime.getPageContext().put(NAME, null);
        runtime.getPageContext().put(JellyEngine.RENDERING, null);
    }
}
