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
package com.cyclopsgroup.waterview.spi;

import com.cyclopsgroup.waterview.RuntimeData;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * To manage look and feel
 */
public interface LookAndFeelService
{
    /**
     * Predefined layout
     */
    public interface PredefinedLayout extends Layout
    {
        /** Empty array */
        PredefinedLayout[] EMPTY_ARRAY = new PredefinedLayout[0];

        /**
         *@return Description of layout
         */
        String getDescription();

        /**
         *@return Name of layout
         */
        String getName();

        /**
         *@return Display title of layout
         */
        String getTitle();
    }

    /** role name of service */
    String ROLE = LookAndFeelService.class.getName();

    /**
     * Get default theme
     *
     * @return Theme object
     * @throws NoSuchLookAndFeelException Theme is not found
     */
    Theme getDefaultTheme() throws NoSuchLookAndFeelException;

    /**
     * Get icon set
     *
     * @param name Icon set name
     * @return Icon set resource
     * @throws NoSuchLookAndFeelException If name doesnt exist
     */
    Resource getIconSet(String name) throws NoSuchLookAndFeelException;

    /**
     * Get all icon set names
     *
     * @return Array of names
     */
    String[] getIconSetNames();

    /**
     * Get layout object
     *
     * @param layoutName Layout name
     * @return Layout object
     */
    PredefinedLayout getLayout(String layoutName);

    /**
     * Get layout names
     *
     * @return Layout names
     */
    String[] getLayoutNames();

    /**
     * Set runtime theme
     *
     * @param data Runtime data
     * @return Theme object
     * @throws NoSuchLookAndFeelException If name doesn't exist
     */
    Theme getRuntimeTheme(RuntimeData data) throws NoSuchLookAndFeelException;

    /**
     *Get stylesheet
     *
     * @param name Stylesheet name
     * @return Resource of stylesheet
     * @throws NoSuchLookAndFeelException If name doesn't exist
     */
    Resource getStyleSheet(String name) throws NoSuchLookAndFeelException;

    /**
     * Get stylesheet names
     *
     * @return Name array
     */
    String[] getStyleSheetNames();

    /**
     * Get theme with given name
     *
     * @param themeName Theme name
     * @return Theme object
     * @throws NoSuchLookAndFeelException Theme is not found
     */
    Theme getTheme(String themeName) throws NoSuchLookAndFeelException;

    /**
     * Get all theme names
     *
     * @return Array of theme names
     */
    String[] getThemeNames();

    /**
     * Register icon set
     *
     * @param name Name of icon set
     * @param resource Resource of icon set
     */
    void registerIconSet(String name, Resource resource);

    /**
     * Register predefined layout
     *
     * @param layout Layout object
     */
    void registerLayout(PredefinedLayout layout);

    /**
     * Register stylesheet
     *
     * @param name Name of stylesheet
     * @param stylesheet Resource of stylesheet
     */
    void registerStyleSheet(String name, Resource stylesheet);

    /**
     * Register a theme
     *
     * @param theme Theme object
     */
    void registerTheme(Theme theme);

    /**
     * Set theme to runtime envionment
     *
     * @param data Runtime data
     * @param theme Theme object to set
     */
    void setRuntimeTheme(RuntimeData data, Theme theme);
}
