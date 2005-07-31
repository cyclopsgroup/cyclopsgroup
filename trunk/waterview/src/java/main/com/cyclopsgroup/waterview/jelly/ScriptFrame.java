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
import com.cyclopsgroup.waterview.PageRuntime;
import com.cyclopsgroup.waterview.spi.Frame;
import com.cyclopsgroup.waterview.spi.Page;

/**
 * Jelly script frame implementation
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class ScriptFrame extends BaseModule implements Frame
{
    private Script script;

    /**
     * Constructor for class ScriptFrame
     *
     * @param script Script object
     * @param module Module object
     */
    public ScriptFrame(Script script, Module module)
    {
        this.script = script;
        setModule(module);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.spi.Frame#display(com.cyclopsgroup.waterview.spi.Page, com.cyclopsgroup.waterview.PageRuntime)
     */
    public void display(Page page, PageRuntime runtime) throws Exception
    {
        JellyContext jc = (JellyContext) runtime.getPageContext().get(
                JellyEngine.JELLY_CONTEXT);
        runtime.getPageContext().put(JellyEngine.JELLY_CONTEXT, jc);
        jc.setVariable(Page.NAME, page);
        jc.setVariable(PageRuntime.NAME, runtime);
        XMLOutput output = XMLOutput.createXMLOutput(runtime.getOutput());
        runtime.getPageContext().put(JellyEngine.JELLY_OUTPUT, output);
        script.run(jc, output);
        output.flush();
    }
}
