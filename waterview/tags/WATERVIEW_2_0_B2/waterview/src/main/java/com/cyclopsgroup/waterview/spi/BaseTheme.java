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

import java.util.Hashtable;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Base abstract theme class
 */
public abstract class BaseTheme
    implements Theme
{
    private String description;

    private Hashtable layouts = new Hashtable();

    private String name;

    /**
     * Constructor for class BaseTheme
     *
     * @param name Name of the theme
     */
    protected BaseTheme( String name )
    {
        this.name = name;
        setDescription( "Theme [" + name + "]" );
    }

    /**
     * Overwrite or implement method getDescription()
     *
     * @see com.cyclopsgroup.waterview.spi.Theme#getDescription()
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Overwrite or implement method getLayout()
     *
     * @see com.cyclopsgroup.waterview.spi.Theme#getLayout(java.lang.String)
     */
    public Layout getLayout( String layoutName )
    {
        return (Layout) layouts.get( layoutName );
    }

    /**
     * Overwrite or implement method getName()
     *
     * @see com.cyclopsgroup.waterview.spi.Theme#getName()
     */
    public String getName()
    {
        return name;
    }

    /**
     * Setter method for field description
     *
     * @param description The description to set.
     */
    public void setDescription( String description )
    {
        this.description = description;
    }

    /**
     * Set layout
     *
     * @param name Layout name
     * @param layout Layout object
     */
    public void setLayout( String name, Layout layout )
    {
        layouts.put( name, layout );
    }
}
