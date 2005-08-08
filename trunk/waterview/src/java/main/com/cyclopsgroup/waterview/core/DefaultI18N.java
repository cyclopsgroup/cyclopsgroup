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
package com.cyclopsgroup.waterview.core;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.I18N;
import com.cyclopsgroup.waterview.Path;
import com.cyclopsgroup.waterview.spi.BaseI18N;

class DefaultI18N extends BaseI18N
{
    private DefaultModuleManager modules;

    private List resourceBundles;

    /**
     * Constructor of it
     * 
     * @param locale Current locale
     * @param resourceBundles ResourceBundle list
     * @param modules Module manager
     */
    DefaultI18N(Locale locale, List resourceBundles,
            DefaultModuleManager modules)
    {
        super(locale);
        this.resourceBundles = resourceBundles;
        this.modules = modules;
    }

    /**
     * Overwrite or implement method doGetText()
     * @see com.cyclopsgroup.waterview.spi.BaseI18N#doGetText(java.lang.String)
     */
    protected String doGetText(String key) throws Exception
    {
        String text = null;
        for (Iterator i = resourceBundles.iterator(); i.hasNext();)
        {
            ResourceBundle resourceBundle = (ResourceBundle) i.next();
            try
            {
                text = resourceBundle.getString(key);
                if (StringUtils.isNotEmpty(text))
                {
                    break;
                }
            }
            catch (Exception ignored)
            {
            }
        }
        return text;
    }

    /**
     * Overwrite or implement method getInstance()
     * @see com.cyclopsgroup.waterview.I18N#getInstance(java.lang.String)
     */
    public I18N getInstance(String modulePath)
    {
        Path path = modules.parsePath(modulePath);
        return modules.getInternationalization(path, getLocale());
    }
}
