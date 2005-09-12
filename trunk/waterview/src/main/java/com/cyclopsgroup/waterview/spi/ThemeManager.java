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

import java.util.HashSet;
import java.util.Iterator;

import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Theme manager
 */
public class ThemeManager
    implements Configurable
{
    /** Role of this component*/
    public static final String ROLE = ThemeManager.class.getName();

    private String defaultThemeName;

    private HashSet themeProviders = new HashSet();

    /**
     * Overwrite or implement method configure()
     *
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure( Configuration conf )
        throws ConfigurationException
    {
        defaultThemeName = conf.getChild( "default-theme" ).getValue( "default" );
    }

    /**
     * Get default theme
     *
     * @return Default theme
     */
    public Theme getDefaultTheme()
    {
        return getTheme( defaultThemeName );
    }

    /**
     * Default theme name
     *
     * @return Default themeName
     */
    public String getDefaultThemeName()
    {
        return defaultThemeName;
    }

    /**
     * Get theme
     *
     * @param themeName Theme name
     * @return Theme object
     */
    public Theme getTheme( String themeName )
    {
        Theme theme = null;
        for ( Iterator i = themeProviders.iterator(); i.hasNext(); )
        {
            ThemeProvider provider = (ThemeProvider) i.next();
            theme = provider.getTheme( themeName );
            if ( theme != null )
            {
                break;
            }
        }
        return theme;
    }

    /**
     * Get all theme names
     *
     * @return Array of them names
     */
    public String[] getThemeNames()
    {
        HashSet names = new HashSet();
        for ( Iterator i = themeProviders.iterator(); i.hasNext(); )
        {
            ThemeProvider provider = (ThemeProvider) i.next();
            CollectionUtils.addAll( names, provider.getThemeNames() );
        }
        return (String[]) names.toArray( ArrayUtils.EMPTY_STRING_ARRAY );
    }

    /**
     * Register theme provider
     *
     * @param provider Theme provider
     */
    public void registerThemeProvider( ThemeProvider provider )
    {
        themeProviders.add( provider );
    }
}
