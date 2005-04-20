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
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.Page;
import com.cyclopsgroup.waterview.PanelContent;
import com.cyclopsgroup.waterview.jelly.WaterviewTagSupport;

/**
 * Panel content tag
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class PanelContentTag extends WaterviewTagSupport
{

    private boolean append;

    private String name;

    private PanelContent panelContent;

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.jelly.WaterviewTagSupport#doTag(org.apache.avalon.framework.service.ServiceManager, org.apache.commons.jelly.XMLOutput)
     */
    public void doTag(ServiceManager serviceManager, XMLOutput output)
            throws Exception
    {
        requireAttribute("name");
        requireParent(PageTag.class);

        panelContent = new PanelContent(getName());
        panelContent.setAppend(isAppend());
        invokeBody(output);
        Page page = ((PageTag) getParent()).getPage();
        page.addPanelContent(panelContent);
    }

    /**
     * Getter method for name
     *
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Getter method for panelContent
     *
     * @return Returns the panelContent.
     */
    public PanelContent getPanelContent()
    {
        return panelContent;
    }

    /**
     * Getter method for append
     *
     * @return Returns the append.
     */
    public boolean isAppend()
    {
        return append;
    }

    /**
     * Setter method for append
     *
     * @param append The append to set.
     */
    public void setAppend(boolean append)
    {
        this.append = append;
    }

    /**
     * Setter method for name
     *
     * @param name The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }
}
