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
package com.cyclopsgroup.waterview.spi;

import com.cyclopsgroup.waterview.Context;
import com.cyclopsgroup.waterview.Portlet;
import com.cyclopsgroup.waterview.PortletEditor;
import com.cyclopsgroup.waterview.RuntimeData;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * View based portlet
 */
public class ViewPortlet implements Portlet
{
    private String description;

    private String title;

    private View view;

    /**
     * Constructor for class ViewPortlet
     *
     * @param view View object
     */
    public ViewPortlet(View view)
    {
        this.view = view;
    }

    /**
     * Overwrite or implement method getDescription()
     *
     * @see com.cyclopsgroup.waterview.Portlet#getDescription()
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Overwrite or implement method getEditor()
     *
     * @see com.cyclopsgroup.waterview.Portlet#getEditor()
     */
    public PortletEditor getEditor()
    {
        return null;
    }

    /**
     * Overwrite or implement method getTitle()
     *
     * @see com.cyclopsgroup.waterview.Portlet#getTitle()
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Overwrite or implement method render()
     *
     * @see com.cyclopsgroup.waterview.Portlet#render(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.waterview.Context)
     */
    public void render(RuntimeData data, Context context) throws Exception
    {
        view.render(data, context);
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
