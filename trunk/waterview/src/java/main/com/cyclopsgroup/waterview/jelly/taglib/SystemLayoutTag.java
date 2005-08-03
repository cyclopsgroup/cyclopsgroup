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

import org.apache.avalon.framework.service.ServiceManager;
import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.jelly.BaseTag;
import com.cyclopsgroup.waterview.spi.Layout;
import com.cyclopsgroup.waterview.spi.ModuleManager;
import com.cyclopsgroup.waterview.spi.Page;

/**
 * System predefined layout tag
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class SystemLayoutTag extends BaseTag
{
    private String id;

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.jelly.BaseTag#doTag(org.apache.avalon.framework.service.ServiceManager, org.apache.commons.jelly.XMLOutput)
     */
    public void doTag(ServiceManager serviceManager, XMLOutput output)
            throws Exception
    {
        requireAttribute("id");
        Page page = (Page) context.getVariable(Page.NAME);
        if (page == null)
        {
            throw new JellyTagException("JellyLayout must be in a page");
        }
        ModuleManager mm = (ModuleManager) serviceManager
                .lookup(ModuleManager.ROLE);
        Layout layout = mm.getLayout(getId());
        if (layout == null)
        {
            throw new NullPointerException("Layout [" + getId()
                    + "] doesn't exist");
        }
        page.setLayout(layout);
    }

    /**
     * Getter method for id
     *
     * @return Returns the id.
     */
    public String getId()
    {
        return id;
    }

    /**
     * Setter method for id
     *
     * @param id The id to set.
     */
    public void setId(String id)
    {
        this.id = id;
    }
}
