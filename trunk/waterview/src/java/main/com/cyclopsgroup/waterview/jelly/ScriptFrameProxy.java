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
import com.cyclopsgroup.waterview.Frame;
import com.cyclopsgroup.waterview.ModuleManager;
import com.cyclopsgroup.waterview.Page;
import com.cyclopsgroup.waterview.PageRuntime;

/**
 * Proxy of script frame
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class ScriptFrameProxy extends BaseModule implements Frame
{
    private ScriptFrame frame;

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
     * @see com.cyclopsgroup.waterview.Frame#display(com.cyclopsgroup.waterview.Page, com.cyclopsgroup.waterview.PageRuntime)
     */
    public void display(Page page, PageRuntime runtime) throws Exception
    {
        synchronized (this)
        {
            if (frame == null)
            {
                JellyEngine je = (JellyEngine) runtime.getServiceManager()
                        .lookup(JellyEngine.ROLE);
                ModuleManager mm = (ModuleManager) runtime.getServiceManager()
                        .lookup(ModuleManager.ROLE);
                String path = "frame/" + framePath;
                frame = new ScriptFrame(je.getScript(path), mm.getModule(path));
            }
        }
        frame.display(page, runtime);
    }
}
