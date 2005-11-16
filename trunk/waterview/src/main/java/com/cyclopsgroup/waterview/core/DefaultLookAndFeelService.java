/* ==========================================================================
 * Copyright 2002-2005 Cyclops Group Community
 *
 * Licensed under the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE
 * (CDDL) Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.opensource.org/licenses/cddl1.txt
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * =========================================================================
 */
package com.cyclopsgroup.waterview.core;

import java.util.Hashtable;

import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.spi.LookAndFeelService;
import com.cyclopsgroup.waterview.spi.NoSuchLookAndFeelException;
import com.cyclopsgroup.waterview.spi.Resource;
import com.cyclopsgroup.waterview.spi.Theme;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * implementation of look and feel service
 */
public class DefaultLookAndFeelService extends AbstractLogEnabled implements
        LookAndFeelService, Configurable
{
    private static final String THEME_KEY = DefaultLookAndFeelService.class
            .getName()
            + "/theme";

    private String defaultThemeName;

    private Hashtable iconSets = new Hashtable();

    private Hashtable layouts = new Hashtable();

    private Hashtable styleSheets = new Hashtable();

    private Hashtable themes = new Hashtable();

    /**
     * Overwrite or implement method configure()
     *
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure(Configuration conf) throws ConfigurationException
    {
        defaultThemeName = conf.getChild("default-theme").getValue(
                "waterview.theme.default");
    }

    /**
     * Overwrite or implement method getDefaultTheme()
     *
     * @see com.cyclopsgroup.waterview.spi.LookAndFeelService#getDefaultTheme()
     */
    public Theme getDefaultTheme() throws NoSuchLookAndFeelException
    {
        return getTheme(defaultThemeName);
    }

    /**
     * Overwrite or implement method getIconSet()
     *
     * @see com.cyclopsgroup.waterview.spi.LookAndFeelService#getIconSet(java.lang.String)
     */
    public Resource getIconSet(String name) throws NoSuchLookAndFeelException
    {
        Resource iconset = (Resource) iconSets.get(name);
        if (iconset == null)
        {
            throw new NoSuchLookAndFeelException("Icon Set", name);
        }
        return iconset;
    }

    /**
     * Overwrite or implement method getIconSetNames()
     *
     * @see com.cyclopsgroup.waterview.spi.LookAndFeelService#getIconSetNames()
     */
    public String[] getIconSetNames()
    {
        return (String[]) iconSets.keySet().toArray(
                ArrayUtils.EMPTY_STRING_ARRAY);
    }

    /**
     * Overwrite or implement method getLayout()
     *
     * @see com.cyclopsgroup.waterview.spi.LookAndFeelService#getLayout(java.lang.String)
     */
    public PredefinedLayout getLayout(String layoutName)
    {
        return (PredefinedLayout) layouts.get(layoutName);
    }

    /**
     * Overwrite or implement method getLayoutNames()
     *
     * @see com.cyclopsgroup.waterview.spi.LookAndFeelService#getLayoutNames()
     */
    public String[] getLayoutNames()
    {
        return (String[]) layouts.keySet().toArray(
                ArrayUtils.EMPTY_STRING_ARRAY);
    }

    /**
     * Overwrite or implement method getRuntimeTheme()
     *
     * @see com.cyclopsgroup.waterview.spi.LookAndFeelService#getRuntimeTheme(com.cyclopsgroup.waterview.RuntimeData)
     */
    public Theme getRuntimeTheme(RuntimeData data)
            throws NoSuchLookAndFeelException
    {
        Theme theme = (Theme) data.getSessionContext().get(THEME_KEY);
        if (theme == null)
        {
            theme = getDefaultTheme();
        }
        return theme;
    }

    /**
     * Overwrite or implement method getStylesheet()
     *
     * @see com.cyclopsgroup.waterview.spi.LookAndFeelService#getStyleSheet(java.lang.String)
     */
    public Resource getStyleSheet(String name)
            throws NoSuchLookAndFeelException
    {
        Resource stylesheet = (Resource) styleSheets.get(name);
        if (stylesheet == null)
        {
            throw new NoSuchLookAndFeelException("Stylesheet", name);
        }
        return stylesheet;
    }

    /**
     * Overwrite or implement method getStylesheetNames()
     *
     * @see com.cyclopsgroup.waterview.spi.LookAndFeelService#getStyleSheetNames()
     */
    public String[] getStyleSheetNames()
    {
        return (String[]) styleSheets.keySet().toArray(
                ArrayUtils.EMPTY_STRING_ARRAY);
    }

    /**
     * Overwrite or implement method getTheme()
     *
     * @see com.cyclopsgroup.waterview.spi.LookAndFeelService#getTheme(java.lang.String)
     */
    public Theme getTheme(String themeName) throws NoSuchLookAndFeelException
    {
        if (StringUtils.isEmpty(themeName))
        {
            return getDefaultTheme();
        }
        Theme theme = (Theme) themes.get(themeName);
        if (theme == null)
        {
            throw new NoSuchLookAndFeelException("Theme", themeName);
        }
        return theme;
    }

    /**
     * Overwrite or implement method getThemeNames()
     *
     * @see com.cyclopsgroup.waterview.spi.LookAndFeelService#getThemeNames()
     */
    public String[] getThemeNames()
    {
        return (String[]) themes.keySet()
                .toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }

    /**
     * Overwrite or implement method registerIconSet()
     *
     * @see com.cyclopsgroup.waterview.spi.LookAndFeelService#registerIconSet(java.lang.String, com.cyclopsgroup.waterview.spi.Resource)
     */
    public void registerIconSet(String name, Resource resource)
    {
        iconSets.put(name, resource);
    }

    /**
     * Overwrite or implement method registerLayout()
     *
     * @see com.cyclopsgroup.waterview.spi.LookAndFeelService#registerLayout(com.cyclopsgroup.waterview.spi.LookAndFeelService.PredefinedLayout)
     */
    public void registerLayout(PredefinedLayout layout)
    {
        layouts.put(layout.getName(), layout);
    }

    /**
     * Overwrite or implement method registerStylesheet()
     *
     * @see com.cyclopsgroup.waterview.spi.LookAndFeelService#registerStyleSheet(java.lang.String, com.cyclopsgroup.waterview.spi.Resource)
     */
    public void registerStyleSheet(String name, Resource stylesheet)
    {
        styleSheets.put(name, stylesheet);
    }

    /**
     * Overwrite or implement method registerTheme()
     *
     * @see com.cyclopsgroup.waterview.spi.LookAndFeelService#registerTheme(com.cyclopsgroup.waterview.spi.Theme)
     */
    public void registerTheme(Theme theme)
    {
        themes.put(theme.getName(), theme);
    }

    /**
     * Overwrite or implement method setRuntimeData()
     *
     * @see com.cyclopsgroup.waterview.spi.LookAndFeelService#setRuntimeTheme(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.waterview.spi.Theme)
     */
    public void setRuntimeTheme(RuntimeData data, Theme theme)
    {
        data.getSessionContext().put(THEME_KEY, theme);
    }
}