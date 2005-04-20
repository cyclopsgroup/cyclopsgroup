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
package com.cyclopsgroup.waterview;

import java.util.Vector;

/**
 * Panel content model
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class PanelContent
{
    private boolean append;

    private String name;

    private Vector views = new Vector();

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
     * Add window into panel
     *
     * @param view Window model
     */
    public void addView(View view)
    {
        views.add(view);
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
     * Getter method for windows
     *
     * @return Returns the windows.
     */
    public View[] getViews()
    {
        return (View[]) views.toArray(View.EMPTY_ARRAY);
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
}
