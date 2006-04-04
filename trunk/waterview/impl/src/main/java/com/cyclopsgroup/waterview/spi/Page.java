/* ==========================================================================
 * Copyright 2002-2004 Cyclops Group Community
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
package com.cyclopsgroup.waterview.spi;

import java.util.Hashtable;
import java.util.Iterator;

/**
 * Page model is the model mapped to a particular URI
 * If a page is not found for given URI, default page will be applied
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class Page
{
    /** Default page model */
    public static final Page DEFAULT = new Page();

    /** Name of this model */
    public static final String NAME = Page.class.getName();

    private Layout layout;

    private Hashtable panelContents = new Hashtable();

    /**
     * Add panel content object
     *
     * @param panelContent Panel content object
     */
    public void addPanelContent( PanelContent panelContent )
    {
        panelContents.put( panelContent.getName(), panelContent );
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
     * Get panel content with given panel name
     *
     * @param panelName Name of panel
     * @return PanelContent object or null
     */
    public PanelContent getPanelContent( String panelName )
    {
        return (PanelContent) panelContents.get( panelName );
    }

    /**
     * Setter method for layout
     *
     * @param layout The layout to set.
     */
    public void setLayout( Layout layout )
    {
        this.layout = layout;
    }

    /**
     * @see java.lang.Object#clone()
     */
    public Object clone()
        throws CloneNotSupportedException
    {
        Page page = new Page();
        page.setLayout(layout);
        for ( Iterator i = panelContents.values().iterator(); i.hasNext(); )
        {
            PanelContent pc = (PanelContent) i.next();
            PanelContent pcCopy = (PanelContent) pc.clone();
            page.addPanelContent(pcCopy);
        }
        return page;
    }
}