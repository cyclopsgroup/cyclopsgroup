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

import java.util.HashMap;

import org.apache.avalon.framework.service.ServiceManager;
import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.clib.lang.Context;
import com.cyclopsgroup.clib.lang.DefaultContext;
import com.cyclopsgroup.waterview.PageRuntime;
import com.cyclopsgroup.waterview.jelly.AbstractTag;
import com.cyclopsgroup.waterview.spi.Page;
import com.cyclopsgroup.waterview.spi.PanelContent;
import com.cyclopsgroup.waterview.spi.View;

/**
 * Tag for panel definition in layout
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class PanelTag extends AbstractTag
{
    private String name;

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.jelly.AbstractTag#doTag(org.apache.avalon.framework.service.ServiceManager, org.apache.commons.jelly.XMLOutput)
     */
    public void doTag(ServiceManager serviceManager, XMLOutput output)
            throws Exception
    {
        requireAttribute("name");
        Page page = (Page) getContext().getVariable(Page.NAME);
        if (page == null)
        {
            throw new JellyTagException(
                    "Can not use panel without a runtime page");
        }
        PanelContent panelContent = page.getPanelContent(getName());
        if (panelContent == null)
        {
            invokeBody(output);
        }
        else
        {
            if (panelContent.isAppend())
            {
                invokeBody(output);
            }
            //TODO handle views
            View[] views = panelContent.getViews();
            for (int i = 0; i < views.length; i++)
            {
                View view = views[i];
                PageRuntime runtime = getRuntime();
                Context viewContext = new DefaultContext(new HashMap(), runtime
                        .getPageContext());
                try
                {
                    view.execute(runtime, viewContext);
                    view.render(runtime, viewContext);
                }
                catch (Exception e)
                {
                    runtime.getOutput().print("<pre>");
                    e.printStackTrace(runtime.getOutput());
                    runtime.getOutput().println("</pre>");
                }
            }
            //Panel panel = null;
            //panel.render(runtime, views);
        }
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
     * Setter method for name
     *
     * @param name The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }
}
