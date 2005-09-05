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

import com.cyclopsgroup.waterview.Context;
import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.spi.BaseModuleRunnable;
import com.cyclopsgroup.waterview.spi.View;

/**
 * Script view
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class JellyView extends BaseModuleRunnable implements View
{

    private Script script;

    /**
     * Constructor for class ScriptView
     *
     * @param script Script object
     * @param modulePath Path of module
     */
    public JellyView(Script script, String modulePath)
    {
        super(modulePath);
        this.script = script;
    }

    /**
     * Overwrite or implement method render()
     * @see com.cyclopsgroup.waterview.spi.View#render(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.waterview.Context)
     */
    public void render(RuntimeData data, Context viewContext) throws Exception
    {
        runModule(data, viewContext);

        JellyEngine je = (JellyEngine) data.getServiceManager().lookup(
                JellyEngine.ROLE);
        JellyContext jellyContext = je.createJellyContext(viewContext);
        XMLOutput output = XMLOutput.createXMLOutput(data.getOutput());
        try
        {
            script.run(jellyContext, output);
        }
        catch (Exception e)
        {
            data.getOutput().print("<div>");
            e.printStackTrace(data.getOutput());
            data.getOutput().println("</div>");
        }
        finally
        {
            output.flush();
        }
    }
}
