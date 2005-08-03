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
package com.cyclopsgroup.waterview.jelly.deftaglib;

import org.apache.avalon.framework.service.ServiceManager;
import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.jelly.BaseTag;
import com.cyclopsgroup.waterview.spi.Layout;
import com.cyclopsgroup.waterview.spi.ModuleManager;

/**
 * Layout tag
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class LayoutTag extends BaseTag
{

    private String description;

    private String id;

    private Layout layout;

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.jelly.BaseTag#doTag(org.apache.avalon.framework.service.ServiceManager, org.apache.commons.jelly.XMLOutput)
     */
    public void doTag(ServiceManager serviceManager, XMLOutput output)
            throws Exception
    {
        requireAttribute("id");
        if (StringUtils.isEmpty(getDescription()))
        {
            setDescription("Layout [" + getId() + "]");
        }
        invokeBody(output);
        if (getLayout() == null)
        {
            throw new JellyTagException(
                    "There must be a layout defined in layout tag");
        }
        ModuleManager moduleManager = (ModuleManager) serviceManager
                .lookup(ModuleManager.ROLE);
        moduleManager.registerLayout(getId(), getLayout());
    }

    /**
     * Getter method for description
     *
     * @return Returns the description.
     */
    public String getDescription()
    {
        return description;
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
     * Getter method for layout
     *
     * @return Returns the layout.
     */
    public Layout getLayout()
    {
        return layout;
    }

    /**
     * Setter method for description
     *
     * @param description The description to set.
     */
    public void setDescription(String description)
    {
        this.description = description;
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

    /**
     * Setter method for layout
     *
     * @param layout The layout to set.
     */
    public void setLayout(Layout layout)
    {
        this.layout = layout;
    }
}
