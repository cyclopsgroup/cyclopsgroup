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

    private List<View> views = new ArrayList<View>();

    /**
     * Constructor for class PanelContent
     *
     * @param name
     */
    public PanelContent( String name )
    {
        this.name = name;
    }

    public void addView( View view )
    {
        views.add( view );
    }

    /**
     * @see java.lang.Object#clone()
     */
    @Override
    public Object clone()
        throws CloneNotSupportedException
    {
        PanelContent pc = new PanelContent( name );
        pc.views.addAll( getViews() );
        return pc;
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
     * Get all views in this panel
     *
     * @return View array
     */
    public List<View> getViews()
    {
        return views;
    }
}
