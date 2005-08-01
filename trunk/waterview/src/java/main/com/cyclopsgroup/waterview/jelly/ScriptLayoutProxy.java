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

import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.spi.Layout;
import com.cyclopsgroup.waterview.spi.ModuleManager;
import com.cyclopsgroup.waterview.spi.Page;

/**
 * Script layout which doesn't initial script object initially
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class ScriptLayoutProxy implements Layout
{
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

    private ScriptLayout getLayout(RuntimeData runtime) throws Exception
    {
        JellyEngine je = (JellyEngine) runtime.getServiceManager().lookup(
                JellyEngine.ROLE);
        ModuleManager mm = (ModuleManager) runtime.getServiceManager().lookup(
                ModuleManager.ROLE);
        ModuleManager.PathModel model = mm.parsePath(layoutScript);
        String path = "/layout" + model.getPath();
        return new ScriptLayout(je.getScript(model.getPackage(), path));
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.spi.Layout#render(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.waterview.spi.Page)
     */
    public void render(RuntimeData runtime, Page page) throws Exception
    {
        getLayout(runtime).render(runtime, page);
    }
}
