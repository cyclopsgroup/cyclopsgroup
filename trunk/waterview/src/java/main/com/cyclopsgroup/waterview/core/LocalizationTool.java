/* ==========================================================================
 * Copyright 2002-2004 Cyclops Group Community
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
package com.cyclopsgroup.waterview.core;

import java.util.ResourceBundle;

/**
 * I18N object used in page
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class LocalizationTool
{
    private LocalizationTool parent;

    private ResourceBundle resourceBundle;

    /**
     * Constructor for class I18N
     *
     * @param parent Parent i18n object
     * @param bundle Input resource bundle
     */
    public LocalizationTool(LocalizationTool parent, ResourceBundle bundle)
    {
        this.parent = parent;
        this.resourceBundle = bundle;
    }

    /**
     * Constructor for class I18N
     *
     * @param bundle Input resource bundle
     */
    public LocalizationTool(ResourceBundle bundle)
    {
        this(null, bundle);
    }

    /**
     * Get localized resource string
     *
     * @param key Resource key
     * @return Localized resource string
     */
    public String get(String key)
    {
        String ret = resourceBundle.getString(key);
        if (ret == null && parent != null)
        {
            ret = parent.get(key);
        }
        return ret;
    }
}