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

import com.cyclopsgroup.waterview.BaseModule;
import com.cyclopsgroup.waterview.Layout;
import com.cyclopsgroup.waterview.ModuleManager;
import com.cyclopsgroup.waterview.Page;
import com.cyclopsgroup.waterview.PageRuntime;

/**
 * Script layout which doesn't initial script object initially
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class ScriptLayoutProxy extends BaseModule implements Layout
{
    private ScriptLayout layout;

    private String layoutScript;

    /**
     * Constructor for class ShellScriptLayout
     *
     * @param layoutScript Layout script path
     */
    public ScriptLayoutProxy(String layoutScript)
    {
        this.layoutScript = layoutScript;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.Layout#render(com.cyclopsgroup.waterview.PageRuntime, com.cyclopsgroup.waterview.Page)
     */
    public void render(PageRuntime runtime, Page page) throws Exception
    {
        synchronized (this)
        {
            if (layout == null)
            {
                JellyEngine je = (JellyEngine) runtime.getServiceManager()
                        .lookup(JellyEngine.ROLE);
                ModuleManager mm = (ModuleManager) runtime.getServiceManager()
                        .lookup(ModuleManager.ROLE);
                String path = "layout/" + layoutScript;
                layout = new ScriptLayout(je.getScript(path), mm
                        .getModule(path));
            }
        }
        layout.render(runtime, page);
    }
}
