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
import com.cyclopsgroup.waterview.Layout;
import com.cyclopsgroup.waterview.Page;
import com.cyclopsgroup.waterview.PageRuntime;

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
     */
    public ScriptLayout(Script script)
    {
        this.script = script;
        if (script == null)
        {
            throw new NullPointerException("Script can not be null");
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.Layout#render(com.cyclopsgroup.waterview.PageRuntime, com.cyclopsgroup.waterview.Page)
     */
    public synchronized void render(PageRuntime runtime, Page page)
            throws Exception
    {
        if (getModule() != null)
        {
            getModule().execute(runtime, runtime.getPageContext());
        }
        JellyContext jellyContext = (JellyContext) runtime.getPageContext()
                .get(JellyEngine.JELLY_CONTEXT);
        XMLOutput output = (XMLOutput) runtime.getPageContext().get(
                JellyEngine.JELLY_OUTPUT);
        jellyContext.setVariable(Page.NAME, page);
        jellyContext.setVariable(NAME, this);
        jellyContext.setVariable(JellyEngine.RENDERING, Boolean.TRUE);
        script.run(jellyContext, output);
        jellyContext.setVariable(Page.NAME, null);
        jellyContext.setVariable(NAME, null);
        jellyContext.setVariable(JellyEngine.RENDERING, null);
    }
}
