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

import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.spi.BaseModuleRunnable;
import com.cyclopsgroup.waterview.spi.Frame;
import com.cyclopsgroup.waterview.spi.Page;

/**
 * Jelly script frame implementation
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class JellyFrame extends BaseModuleRunnable implements Frame
{
    private Script script;

    /**
     * Constructor for class ScriptFrame
     *
     * @param script Script object
     * @param modulePath Module path of this frame
     */
    public JellyFrame(Script script, String modulePath)
    {
        super(modulePath);
        this.script = script;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.spi.Frame#display(com.cyclopsgroup.waterview.spi.Page, com.cyclopsgroup.waterview.RuntimeData)
     */
    public void display(Page page, RuntimeData data) throws Exception
    {
        runModule(data, data.getRequestContext());
        data.getRequestContext().put(Page.NAME, page);
        JellyEngine je = (JellyEngine) data.getServiceManager().lookup(
                JellyEngine.ROLE);
        JellyContext jc = je.createJellyContext(data.getRequestContext());
        XMLOutput output = XMLOutput.createXMLOutput(data.getOutput());
        script.run(jc, output);
        output.flush();
    }
}
