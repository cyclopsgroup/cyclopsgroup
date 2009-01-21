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
package com.cyclopsgroup.waterview.core;

import com.cyclopsgroup.clib.lang.Context;
import com.cyclopsgroup.waterview.BaseModule;
import com.cyclopsgroup.waterview.PageRuntime;
import com.cyclopsgroup.waterview.View;

/**
 * Simple static view implementation
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class StaticView extends BaseModule implements View
{
    private String content;

    /**
     * Constructor for class StaticView
     *
     * @param content String content
     */
    public StaticView(String content)
    {
        this.content = content;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.View#render(com.cyclopsgroup.waterview.PageRuntime, com.cyclopsgroup.clib.lang.Context)
     */
    public void render(PageRuntime runtime, Context viewContext)
            throws Exception
    {
        runtime.getOutput().print(content);
    }
}