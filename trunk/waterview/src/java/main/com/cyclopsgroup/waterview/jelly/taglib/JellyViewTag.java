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
package com.cyclopsgroup.waterview.jelly.taglib;

import org.apache.commons.jelly.JellyContext;

import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.jelly.JellyEngine;
import com.cyclopsgroup.waterview.jelly.ScriptView;
import com.cyclopsgroup.waterview.spi.ModuleManager;
import com.cyclopsgroup.waterview.spi.View;
import com.cyclopsgroup.waterview.spi.taglib.BaseViewTag;

/**
 * Jelly view tag
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class JellyViewTag extends BaseViewTag
{
    private String script;

    /**
     * Overwrite or implement method createView()
     * @see com.cyclopsgroup.waterview.spi.taglib.BaseViewTag#createView(org.apache.commons.jelly.JellyContext, com.cyclopsgroup.waterview.RuntimeData)
     */
    protected View createView(JellyContext context, RuntimeData data)
            throws Exception
    {
        requireAttribute("script");
        JellyEngine jellyEngine = (JellyEngine) data.getServiceManager()
                .lookup(JellyEngine.ROLE);
        ModuleManager mm = (ModuleManager) data.getServiceManager().lookup(
                ModuleManager.ROLE);
        ModuleManager.PathModel model = mm.parsePath(getScript());
        String path = "/view" + model.getPath();
        return new ScriptView(jellyEngine.getScript(model.getPackage(), path));
    }

    /**
     * Getter method for script
     *
     * @return Returns the script.
     */
    public String getScript()
    {
        return script;
    }

    /**
     * Setter method for script
     *
     * @param script The script to set.
     */
    public void setScript(String script)
    {
        this.script = script;
    }
}
