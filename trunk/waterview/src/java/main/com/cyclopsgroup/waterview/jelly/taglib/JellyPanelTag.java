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
import org.apache.commons.jelly.Script;

import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.jelly.JellyEngine;
import com.cyclopsgroup.waterview.jelly.JellyPanel;
import com.cyclopsgroup.waterview.spi.CacheManager;
import com.cyclopsgroup.waterview.spi.ModuleManager;
import com.cyclopsgroup.waterview.spi.Panel;
import com.cyclopsgroup.waterview.spi.taglib.BasePanelTag;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Tag to show a script based panel
 */
public class JellyPanelTag extends BasePanelTag
{
    private String script;

    /**
     * Overwrite or implement method createPanel()
     * @see com.cyclopsgroup.waterview.spi.taglib.BasePanelTag#createPanel(org.apache.commons.jelly.JellyContext, com.cyclopsgroup.waterview.RuntimeData)
     */
    protected synchronized Panel createPanel(JellyContext context,
            RuntimeData data) throws Exception
    {
        requireAttribute("script");
        CacheManager cache = (CacheManager) data.getServiceManager().lookup(
                CacheManager.ROLE);
        JellyPanel panel = (JellyPanel) cache.get(getClass(), getScript());
        if (panel == null)
        {
            ModuleManager module = (ModuleManager) data.getServiceManager()
                    .lookup(ModuleManager.ROLE);
            ModuleManager.Path model = module.parsePath(getScript());
            JellyEngine je = (JellyEngine) data.getServiceManager().lookup(
                    JellyEngine.ROLE);
            Script script = je.getScript(model.getPackage(), "/panel"
                    + model.getPath());
            panel = new JellyPanel(script);
            cache.put(getClass(), getScript(), panel);
        }
        return panel;
    }

    /**
     * @return Returns the script.
     */
    public String getScript()
    {
        return script;
    }

    /**
     * @param script The script to set.
     */
    public void setScript(String script)
    {
        this.script = script;
    }
}
