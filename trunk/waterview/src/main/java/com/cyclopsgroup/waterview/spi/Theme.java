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

import com.cyclopsgroup.waterview.spi.LookAndFeelService.IconSet;
import com.cyclopsgroup.waterview.spi.LookAndFeelService.Style;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Theme interface
 */
public interface Theme
    extends SelectableItem
{
    /** Default layout name */
    String LAYOUT_FOR_DEFAULT = "layout.default";

    /** Max layout name */
    String LAYOUT_FOR_MAX = "layout.max";

    /** Popup layout name */
    String LAYOUT_FOR_POPUP = "layout.popup";

    /** Name in context */
    String NAME = "pageTheme";

    /**
     * Get icon set resource
     *
     * @return Resource object icon set
     * @throws NoSuchLookAndFeelException name error
     */
    IconSet getIconSet()
        throws NoSuchLookAndFeelException;

    /**
     * Get name of icon set
     *
     * @return Name of iconset
     */
    String getIconSetName();

    /**
     * Get named layout
     *
     * @param layoutName Layout tname
     * @return Layout Layout object
     */
    Layout getLayout( String layoutName );

    /**
     * Get stylesheet resource
     *
     * @return Resource of stylesheet
     * @throws NoSuchLookAndFeelException name error
     */
    Style getStyle()
        throws NoSuchLookAndFeelException;

    /**
     * Get name of stylesheet
     *
     * @return Name of stylesheet
     */
    String getStyleName();
}