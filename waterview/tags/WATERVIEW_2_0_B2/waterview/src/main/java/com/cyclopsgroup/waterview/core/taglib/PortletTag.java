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
package com.cyclopsgroup.waterview.core.taglib;

import org.apache.commons.jelly.JellyException;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.spi.View;
import com.cyclopsgroup.waterview.spi.ViewPortlet;
import com.cyclopsgroup.waterview.spi.taglib.PortletAware;
import com.cyclopsgroup.waterview.spi.taglib.ViewAware;
import com.cyclopsgroup.waterview.utils.TagSupport;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Tag to declare a portlet
 */
public class PortletTag extends TagSupport implements ViewAware
{
    private String description;

    private String title;

    private View view;

    /**
     * Overwrite or implement method doView()
     *
     * @see com.cyclopsgroup.waterview.spi.taglib.ViewAware#doView(com.cyclopsgroup.waterview.spi.View)
     */
    public void doView(View view)
    {
        this.view = view;
    }

    /**
     * Getter method for field description
     *
     * @return Returns the description.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Getter method for field title
     *
     * @return Returns the title.
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag(XMLOutput output) throws Exception
    {
        PortletAware pa = (PortletAware) findAncestorWithClass(PortletAware.class);
        if (pa == null)
        {
            throw new JellyException("Portlet must be in a proper tag");
        }
        invokeBody(XMLOutput.createDummyXMLOutput());
        if (view == null)
        {
            throw new JellyException("A view must be defined");
        }
        ViewPortlet portlet = new ViewPortlet(view);
        portlet.setTitle(getTitle());
        portlet.setDescription(getDescription());
        pa.doPortlet(portlet);
    }

    /**
     * Setter method for field description
     *
     * @param description The description to set.
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Setter method for field title
     *
     * @param title The title to set.
     */
    public void setTitle(String title)
    {
        this.title = title;
    }
}
