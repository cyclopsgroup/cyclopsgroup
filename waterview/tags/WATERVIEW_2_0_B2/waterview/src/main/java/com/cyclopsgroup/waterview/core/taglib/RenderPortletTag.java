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

import java.util.HashMap;

import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.DefaultContext;
import com.cyclopsgroup.waterview.Portlet;
import com.cyclopsgroup.waterview.spi.JellyContextAdapter;
import com.cyclopsgroup.waterview.spi.taglib.TagSupport;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Tag to display given view
 */
public class RenderPortletTag extends TagSupport
{
    private Portlet portlet;

    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag(XMLOutput output) throws Exception
    {
        requireAttribute("portlet");
        JellyContextAdapter adapter = new JellyContextAdapter(getContext());
        DefaultContext ctx = new DefaultContext(new HashMap(), adapter);
        getPortlet().render(getRuntimeData(), ctx);
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
     * Setter method for field portlet
     *
     * @param portlet The portlet to set.
     */
    public void setPortlet(Portlet portlet)
    {
        this.portlet = portlet;
    }

}
