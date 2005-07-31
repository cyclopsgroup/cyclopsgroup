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
package com.cyclopsgroup.waterview.velocity;

import org.apache.velocity.Template;

import com.cyclopsgroup.clib.lang.Context;
import com.cyclopsgroup.waterview.BaseModule;
import com.cyclopsgroup.waterview.Module;
import com.cyclopsgroup.waterview.PageRuntime;
import com.cyclopsgroup.waterview.spi.View;

/**
 * Velocity view
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class VelocityView extends BaseModule implements View
{
    private Template template;

    /**
     * Constructor for class VelocityView
     *
     * @param template
     * @param module
     */
    public VelocityView(Template template, Module module)
    {
        this.template = template;
        setModule(module);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.spi.View#render(com.cyclopsgroup.waterview.PageRuntime, com.cyclopsgroup.clib.lang.Context)
     */
    public void render(PageRuntime runtime, Context viewContext)
            throws Exception
    {
        if (template == null)
        {
            return;
        }
        VelocityContextAdapter vca = new VelocityContextAdapter(viewContext);
        template.merge(vca, runtime.getOutput());
    }
}
