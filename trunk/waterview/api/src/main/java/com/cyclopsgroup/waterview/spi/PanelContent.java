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

import java.util.ArrayList;
import java.util.List;


/**
 * Panel content model
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class PanelContent
{
    private String name;

    private List views = new ArrayList();

    public void addView(View view)
    {
        views.add(view);
    }

    /**
     * Get all views in this panel
     *
     * @return View array
     */
    public View[] getViews()
    {
        return (View[]) views.toArray(View.EMPTY_ARRAY);
    }

    /**
     * Get modifiable view list
     *
     * @return Modifiable list of views
     */
    public List getViewList()
    {
        return views;
    }

    /**
     * Constructor for class PanelContent
     *
     * @param name
     */
    public PanelContent(String name)
    {
        this.name = name;
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
     * @see java.lang.Object#clone()
     */
    public Object clone()
        throws CloneNotSupportedException
    {
        PanelContent pc = new PanelContent(name);
        pc.views.addAll(getViewList());
        return pc;
    }
}
