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
package com.cyclopsgroup.waterview;

import java.util.Locale;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Internationalization tool
 */
public interface I18N
{
    /** Name of it in context */
    String NAME = "i18n";

    /**
     * Get localized text
     * 
     * @param key Key of resource bundle
     * @return Localizzed text
     */
    String get(String key);

    /**
     * @param modulePath Path of module
     * @return I18N instance attached to given module
     */
    I18N getInstance(String modulePath);

    /**
     * Get current locale
     * 
     * @return Locale object
     */
    Locale getLocale();

    /**
     * Get localized text
     * 
     * @param key Key of resource bundle
     * @return Localizzed text
     */
    String getText(String key);

    /**
     * Get localized text with given default value
     * 
     * @param key Key of resource bundle
     * @param defaultValue Default value
     * @return Localizzed text
     */
    String getText(String key, String defaultValue);
}
