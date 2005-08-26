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
import org.apache.velocity.Template;

import com.cyclopsgroup.waterview.spi.taglib.BaseTag;
import com.cyclopsgroup.waterview.velocity.VelocityEngine;
import com.cyclopsgroup.waterview.velocity.VelocityJellyContextAdapter;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Directly show a given velocity template
 */
public class VelocityTemplateTag extends BaseTag
{

    private String template;

    /**
     * Overwrite or implement method doTag()
     *
     * @see com.cyclopsgroup.waterview.spi.taglib.BaseTag#doTag(org.apache.avalon.framework.service.ServiceManager, org.apache.commons.jelly.XMLOutput)
     */
    protected void doTag(ServiceManager serviceManager, XMLOutput output)
            throws Exception
    {
        requireAttribute("template");
        VelocityEngine ve = (VelocityEngine) getRuntimeData()
                .getServiceManager().lookup(VelocityEngine.ROLE);
        Template t = ve.getTemplate(getTemplate());
        if (t == null)
        {
            getRuntimeData().getOutput().println(
                    "Velocity template " + getTemplate() + " is not found");
        }
        VelocityJellyContextAdapter adapter = new VelocityJellyContextAdapter(
                getContext());
        t.merge(adapter, getRuntimeData().getOutput());
        getRuntimeData().getOutput().flush();
    }

    /**
     * Getter method for field template
     *
     * @return Returns the template.
     */
    public String getTemplate()
    {
        return template;
    }

    /**
     * Setter method for field template
     *
     * @param template The template to set.
     */
    public void setTemplate(String template)
    {
        this.template = template;
    }

}
