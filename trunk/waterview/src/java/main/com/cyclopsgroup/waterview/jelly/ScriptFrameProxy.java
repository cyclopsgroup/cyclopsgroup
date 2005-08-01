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
import com.cyclopsgroup.waterview.spi.Frame;
import com.cyclopsgroup.waterview.spi.ModuleManager;
import com.cyclopsgroup.waterview.spi.Page;

/**
 * Proxy of script frame
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class ScriptFrameProxy implements Frame
{
    private String framePath;

    /**
     * Constructor for class ScriptFrameProxy
     *
     * @param framePath Path of frame
     */
    public ScriptFrameProxy(String framePath)
    {
        this.framePath = framePath;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.spi.Frame#display(com.cyclopsgroup.waterview.spi.Page, com.cyclopsgroup.waterview.RuntimeData)
     */
    public void display(Page page, RuntimeData runtime) throws Exception
    {
        getFrame(runtime).display(page, runtime);
    }

    private ScriptFrame getFrame(RuntimeData runtime) throws Exception
    {
        JellyEngine je = (JellyEngine) runtime.getServiceManager().lookup(
                JellyEngine.ROLE);
        ModuleManager mm = (ModuleManager) runtime.getServiceManager().lookup(
                ModuleManager.ROLE);
        ModuleManager.PathModel model = mm.parsePath(framePath);
        String path = "/frame" + model.getPath();
        return new ScriptFrame(je.getScript(model.getPackage(), path));
    }
}
