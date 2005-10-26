/* ==========================================================================
 * Copyright 2002-2005 Cyclops Group Community
 * 
 * Licensed under the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE
 * (CDDL) Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.opensource.org/licenses/cddl1.txt
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * =========================================================================
 */
package com.cyclopsgroup.waterview.web.taglib;

import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.Portlet;
import com.cyclopsgroup.waterview.jelly.taglib.BaseJellyControlTag;
import com.cyclopsgroup.waterview.spi.taglib.PortletAware;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class DisplayPortletTag extends BaseJellyControlTag implements
        PortletAware
{
    private int border = 1;

    private boolean maximizable;

    private Portlet portlet;

    private int shadow = 0;

    /**
     * Constructor for class WindowControlTag
     *
     */
    public DisplayPortletTag()
    {
        setScript("/waterview/control/PortletControl.jelly");
    }

    /**
     * Getter method for property border
     *
     * @return Returns the border.
     */
    public int getBorder()
    {
        return border;
    }

    /**
     * Getter method for field portlet
     *
     * @return Returns the portlet.
     */
    public Portlet getPortlet()
    {
        return portlet;
    }

    /**
     * Getter method for property shadow
     *
     * @return Returns the shadow.
     */
    public int getShadow()
    {
        return shadow;
    }

    /**
     * Getter method for maximizable
     *
     * @return Returns the maximizable.
     */
    public boolean isMaximizable()
    {
        return maximizable;
    }

    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.jelly.taglib.BaseJellyControlTag#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag(XMLOutput output) throws Exception
    {
        invokeBody(XMLOutput.createDummyXMLOutput());
        requireAttribute("portlet");
        super.processTag(output);
    }

    /**
     * Setter method for property border
     *
     * @param border The border to set.
     */
    public void setBorder(int border)
    {
        this.border = border;
    }

    /**
     * Setter method for maximizable
     *
     * @param maximizable The maximizable to set.
     */
    public void setMaximizable(boolean maximizable)
    {
        this.maximizable = maximizable;
    }

    /**
     * Setter method for field portlet
     *
     * @param portlet The portlet to set.
     */
    public void setPortlet(Portlet portlet)
    {
        this.portlet = portlet;
    }

    /**
     * Setter method for property shadow
     *
     * @param shadow The shadow to set.
     */
    public void setShadow(int shadow)
    {
        this.shadow = shadow;
    }

    /**
     * Overwrite or implement method doPortlet()
     *
     * @see com.cyclopsgroup.waterview.spi.taglib.PortletAware#doPortlet(com.cyclopsgroup.waterview.Portlet)
     */
    public void doPortlet(Portlet portlet)
    {
        setPortlet(portlet);
    }
}
