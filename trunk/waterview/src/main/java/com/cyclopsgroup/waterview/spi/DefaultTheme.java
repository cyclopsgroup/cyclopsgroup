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
import java.util.Properties;

import com.cyclopsgroup.waterview.Resource;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Base abstract theme class
 */
public class DefaultTheme
    implements Theme
{
    private String description;

    private Resource iconSet;

    private Hashtable layouts = new Hashtable();

    private String name;

    private Properties properties = new Properties();

    private Resource styleSheet;

    /**
     * Constructor for class BaseTheme
     *
     * @param name Name of the theme
     * @param iconSet Root of resource directory
     * @param styleSheet Stylesheet resource
     */
    public DefaultTheme( String name, Resource iconSet, Resource styleSheet )
    {
        this.name = name;
        this.iconSet = iconSet;
        this.styleSheet = styleSheet;
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
     * Overwrite or implement method getIconSet()
     *
     * @see com.cyclopsgroup.waterview.spi.Theme#getIconSet()
     */
    public Resource getIconSet()
    {
        return iconSet;
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
     * Overwrite or implement method getProperty()
     *
     * @see com.cyclopsgroup.waterview.spi.Theme#getProperty(java.lang.String)
     */
    public String getProperty( String propertyName )
    {
        return properties.getProperty( propertyName );
    }

    /**
     * Overwrite or implement method getStylesheet()
     *
     * @see com.cyclopsgroup.waterview.spi.Theme#getStyleSheet()
     */
    public synchronized Resource getStyleSheet()
    {
        if ( styleSheet == null )
        {
            styleSheet = new Resource( Resource.INTERNAL, iconSet.getPath() + "/style.css" );
        }
        return styleSheet;
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
