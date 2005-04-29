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
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.PageRuntime;
import com.cyclopsgroup.waterview.PanelContent;
import com.cyclopsgroup.waterview.jelly.AbstractTag;
import com.cyclopsgroup.waterview.jelly.taglib.PanelContentTag;
import com.cyclopsgroup.waterview.velocity.VelocityEngine;
import com.cyclopsgroup.waterview.velocity.VelocityView;

/**
 * Velocity view tag
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class VelocityViewTag extends AbstractTag
{
    private String template;

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.jelly.AbstractTag#doTag(org.apache.avalon.framework.service.ServiceManager, org.apache.commons.jelly.XMLOutput)
     */
    public void doTag(ServiceManager serviceManager, XMLOutput output)
            throws Exception
    {
        requireAttribute("template");
        VelocityEngine ve = (VelocityEngine) serviceManager
                .lookup(VelocityEngine.ROLE);
        VelocityView view = new VelocityView(ve.getTemplate("view/"
                + getTemplate()));
        if (isRendering())
        {
            PageRuntime runtime = getRuntime();
            view.render(runtime, runtime.getPageContext());
        }
        else
        {
            requireParent(PanelContentTag.class);
            PanelContent panelContent = ((PanelContentTag) getParent())
                    .getPanelContent();
            panelContent.addView(view);
        }
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
