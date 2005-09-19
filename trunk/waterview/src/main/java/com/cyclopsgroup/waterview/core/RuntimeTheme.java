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

import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.spi.Theme;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Runtime instance of theme
 */
public class RuntimeTheme
{
    /** Name of this object */
    public static final String NAME = "theme";

    private RuntimeData data;

    private Theme theme;

    /**
     * Constructor for class RuntimeTheme
     *
     * @param theme Theme object
     * @param data Runtime data
     */
    public RuntimeTheme( Theme theme, RuntimeData data )
    {
        this.theme = theme;
        this.data = data;
    }

    /**
     * Get resource base url
     *
     * @return Resource base url
     */
    public String getIconSetUrl()
    {
        return theme.getIconSet().toURL( data );
    }

    /**
     * Get stylesheet url
     *
     * @return URL of stylesheet
     */
    public String getStylesheetUrl()
    {
        return theme.getStyleSheet().toURL( data );
    }

    /**
     * Get theme object
     *
     * @return Theme object
     */
    public Theme getTheme()
    {
        return theme;
    }
}