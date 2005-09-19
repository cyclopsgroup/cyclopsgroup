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

import com.cyclopsgroup.waterview.Link;
import com.cyclopsgroup.waterview.RuntimeData;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Base abstract theme class
 */
public class DefaultTheme
    implements Theme
{
    private String description;

    private Hashtable layouts = new Hashtable();

    private String name;

    private Properties properties = new Properties();

    private String resourceDirectory;

    /**
     * Constructor for class BaseTheme
     *
     * @param name Name of the theme
     * @param resourceDirectory Root of resource directory
     */
    public DefaultTheme( String name, String resourceDirectory )
    {
        this.name = name;
        this.resourceDirectory = resourceDirectory;
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
     * Overwrite or implement method getProperty()
     *
     * @see com.cyclopsgroup.waterview.spi.Theme#getProperty(java.lang.String)
     */
    public String getProperty( String propertyName )
    {
        return properties.getProperty( propertyName );
    }

    /**
     * Overwrite or implement method getResourceBaseUrl()
     *
     * @see com.cyclopsgroup.waterview.spi.Theme#getResourceBaseUrl(com.cyclopsgroup.waterview.RuntimeData)
     */
    public String getResourceBaseUrl( RuntimeData data )
    {
        Link link = (Link) data.getRequestContext().get( Link.NAME );
        return link.getResource( resourceDirectory );
    }

    /**
     * Overwrite or implement method getStylesheetUrl()
     *
     * @see com.cyclopsgroup.waterview.spi.Theme#getStylesheetUrl(com.cyclopsgroup.waterview.RuntimeData)
     */
    public String getStylesheetUrl( RuntimeData data )
    {
        return getResourceBaseUrl( data ) + "/style.css";
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
