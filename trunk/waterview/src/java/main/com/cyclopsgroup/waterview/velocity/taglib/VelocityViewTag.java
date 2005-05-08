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
package com.cyclopsgroup.waterview.velocity.taglib;

import org.apache.avalon.framework.service.ServiceManager;

import com.cyclopsgroup.waterview.ModuleManager;
import com.cyclopsgroup.waterview.View;
import com.cyclopsgroup.waterview.jelly.taglib.AbstractViewTag;
import com.cyclopsgroup.waterview.velocity.VelocityEngine;
import com.cyclopsgroup.waterview.velocity.VelocityView;

/**
 * Velocity view tag
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class VelocityViewTag extends AbstractViewTag
{
    private String template;

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.jelly.taglib.AbstractViewTag#doCreateView(org.apache.avalon.framework.service.ServiceManager)
     */
    protected View doCreateView(ServiceManager serviceManager) throws Exception
    {
        requireAttribute("template");
        VelocityEngine ve = (VelocityEngine) serviceManager
                .lookup(VelocityEngine.ROLE);
        ModuleManager mm = (ModuleManager) serviceManager
                .lookup(ModuleManager.ROLE);
        String path = "view/" + getTemplate();
        return new VelocityView(ve.getTemplate(path), mm.getModule(path));
    }

    /**
     * Getter method for template
     *
     * @return Returns the template.
     */
    public String getTemplate()
    {
        return template;
    }

    /**
     * Setter method for template
     *
     * @param template The template to set.
     */
    public void setTemplate(String template)
    {
        this.template = template;
    }
}
