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
import com.cyclopsgroup.waterview.spi.Panel;
import com.cyclopsgroup.waterview.spi.View;

/**
 * Script based panel
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class JellyPanel implements Panel
{
    private static final String VIEWS_NAME = "views";

    private Script script;

    /**
     * Constructor for class ScriptPanel
     *
     * @param script Jelly script object
     */
    public JellyPanel(Script script)
    {
        this.script = script;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.spi.Panel#render(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.waterview.spi.View[])
     */
    public void render(RuntimeData runtime, View[] views) throws Exception
    {
        JellyEngine je = (JellyEngine) runtime.getServiceManager().lookup(
                JellyEngine.ROLE);
        JellyContext jc = je.createJellyContext(runtime.getRequestContext());
        jc.setVariable(VIEWS_NAME, views);

        try
        {
            XMLOutput output = XMLOutput.createXMLOutput(runtime.getOutput());
            script.run(jc, output);
            output.flush();
        }
        catch (Exception e)
        {
            runtime.getOutput().println("<pre>");
            e.printStackTrace(runtime.getOutput());
            runtime.getOutput().println("</pre>");
        }
        finally
        {
            runtime.getOutput().flush();
        }
    }
}
