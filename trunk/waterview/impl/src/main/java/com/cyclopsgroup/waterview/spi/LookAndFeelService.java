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

import com.cyclopsgroup.waterview.RunData;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * To manage look and feel
 */
public interface LookAndFeelService
{
    /**
     * Icon set model
     */
    public interface IconSet
        extends SelectableItem
    {
        /** Empty array */
        IconSet[] EMPTY_ARRAY = new IconSet[0];

        /**
         * Get icon url
         *
         * @param data Runtime data
         * @param file File name
         * @param size Size of the icon
         * @return Url of image resource
         */
        String getUrl( RunData data, String file, int size );
    }

    /**
     * Predefined layout
     */
    public interface PredefinedLayout
        extends Layout, SelectableItem
    {
        /** Empty array */
        PredefinedLayout[] EMPTY_ARRAY = new PredefinedLayout[0];
    }

    /**
     * Style model
     */
    public interface Style
        extends SelectableItem
    {
        /** Empty array */
        Style[] EMPTY_ARRAY = new Style[0];

        /**
         * Get url of style sheet
         *
         * @param data Runtime data
         * @return Url of stylesheet resource
         */
        String getUrl( RunData data );
    }

    /** role name of service */
    String ROLE = LookAndFeelService.class.getName();

    /**
     * Get default theme
     *
     * @return Theme object
     * @throws NoSuchLookAndFeelException Theme is not found
     */
    Theme getDefaultTheme()
        throws NoSuchLookAndFeelException;

    /**
     * Get icon set
     *
     * @param name Icon set name
     * @return Icon set resource
     * @throws NoSuchLookAndFeelException If name doesnt exist
     */
    IconSet getIconSet( String name )
        throws NoSuchLookAndFeelException;

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
    PredefinedLayout getLayout( String layoutName );

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
    Theme getRuntimeTheme( RunData data )
        throws NoSuchLookAndFeelException;

    /**
     *Get stylesheet
     *
     * @param name Stylesheet name
     * @return Resource of stylesheet
     * @throws NoSuchLookAndFeelException If name doesn't exist
     */
    Style getStyle( String name )
        throws NoSuchLookAndFeelException;

    /**
     * Get stylesheet names
     *
     * @return Name array
     */
    String[] getStyleNames();

    /**
     * Get theme with given name
     *
     * @param themeName Theme name
     * @return Theme object
     * @throws NoSuchLookAndFeelException Theme is not found
     */
    Theme getTheme( String themeName )
        throws NoSuchLookAndFeelException;

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
     * @param iconSet Resource of icon set
     */
    void registerIconSet( String name, IconSet iconSet );

    /**
     * Register predefined layout
     *
     * @param layout Layout object
     */
    void registerLayout( PredefinedLayout layout );

    /**
     * Register stylesheet
     *
     * @param name Name of stylesheet
     * @param style Resource of stylesheet
     */
    void registerStyle( String name, Style style );

    /**
     * Register a theme
     *
     * @param theme Theme object
     */
    void registerTheme( Theme theme );

    /**
     * Set theme to runtime envionment
     *
     * @param data Runtime data
     * @param theme Theme object to set
     */
    void setRuntimeTheme( RunData data, Theme theme );
}
