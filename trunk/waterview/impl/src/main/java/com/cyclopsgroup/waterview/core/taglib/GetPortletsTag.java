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
package com.cyclopsgroup.waterview.core.taglib;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.set.ListOrderedSet;
import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.Portlet;
import com.cyclopsgroup.waterview.spi.Page;
import com.cyclopsgroup.waterview.spi.PanelContent;
import com.cyclopsgroup.waterview.spi.taglib.PortletAware;
import com.cyclopsgroup.waterview.spi.taglib.TagSupport;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class GetPortletsTag extends TagSupport implements PortletAware
{
    private String var = "portlets";

    private String panel;

    private Set defaultPortlets = ListOrderedSet.decorate(new HashSet());

    /**
     * Getter method for panel
     *
     * @return Returns the panel.
     */
    public String getPanel()
    {
        return panel;
    }

    /**
     * Setter method for panel
     *
     * @param panel The panel to set.
     */
    public void setPanel(String panel)
    {
        this.panel = panel;
    }

    /**
     * Getter method for var
     *
     * @return Returns the var.
     */
    public String getVar()
    {
        return var;
    }

    /**
     * Setter method for var
     *
     * @param var The var to set.
     */
    public void setVar(String var)
    {
        this.var = var;
    }

    /**
     * Override method processTag in class GetPanelComponentsTag
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag(XMLOutput output) throws Exception
    {
        requireAttribute("var");
        requireAttribute("panel");
        Page page = (Page) getContext().getVariable(Page.NAME);
        if (page == null)
        {
            throw new JellyTagException("Page is not found");
        }
        PanelContent content = page.getPanelContent(getPanel());

        List portlets;
        if (content == null)
        {
            invokeBody(XMLOutput.createDummyXMLOutput());
            portlets = new ArrayList(defaultPortlets);
        }
        else if (content.isAppend())
        {
            invokeBody(XMLOutput.createDummyXMLOutput());
            portlets = new ArrayList(defaultPortlets);
            CollectionUtils.addAll(portlets, content.getPortlets());
        }
        else
        {
            portlets = new ArrayList();
            CollectionUtils.addAll(portlets, content.getPortlets());
        }
        getContext().setVariable(getVar(), portlets);
    }

    /**
     * Overwrite or implement method doPortlet()
     *
     * @see com.cyclopsgroup.waterview.spi.taglib.PortletAware#doPortlet(com.cyclopsgroup.waterview.Portlet)
     */
    public void doPortlet(Portlet portlet)
    {
        defaultPortlets.add(portlet);
    }
}
