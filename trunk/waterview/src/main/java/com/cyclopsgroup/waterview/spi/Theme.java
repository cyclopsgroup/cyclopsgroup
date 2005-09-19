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

import com.cyclopsgroup.waterview.Resource;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Theme interface
 */
public interface Theme
{
    /** Bare layout name */
    String LAYOUT_FOR_BARE = "layout.bare";

    /** Default layout name */
    String LAYOUT_FOR_DEFAULT = "layout.default";

    /** Max layout name */
    String LAYOUT_FOR_MAX = "layout.max";

    /** Popup layout name */
    String LAYOUT_FOR_POPUP = "layout.popup";

    /**
     * Get description of this theme
     *
     * @return Theme description
     */
    String getDescription();

    /**
     * Get icon set resource
     *
     * @return Resource object icon set
     */
    Resource getIconSet();

    /**
     * Get named layout
     *
     * @param layoutName Layout tname
     * @return Layout Layout object
     */
    Layout getLayout( String layoutName );

    /**
     * Get name of this theme
     *
     * @return Theme name
     */
    String getName();

    /**
     * Get theme property
     *
     * @param propertyName Name of the property
     * @return Value of the property
     */
    String getProperty( String propertyName );

    /**
     * Get stylesheet resource
     *
     * @return Resource of stylesheet
     */
    Resource getStyleSheet();
}