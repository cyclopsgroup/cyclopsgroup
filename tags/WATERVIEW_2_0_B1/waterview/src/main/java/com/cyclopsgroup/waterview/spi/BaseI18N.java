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

import java.util.Locale;

import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.I18N;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Base implementation of I18N
 */
public abstract class BaseI18N implements I18N
{
    private Locale locale;

    /**
     * Constructor
     * 
     * @param locale Locale object
     */
    protected BaseI18N(Locale locale)
    {
        this.locale = locale;
    }

    /**
     * Get value of text
     * 
     * @param key Key of resource
     * @return String content
     * @throws Exception Throw it out
     */
    protected abstract String doGetText(String key) throws Exception;

    /**
     * Overwrite or implement method get()
     * @see com.cyclopsgroup.waterview.I18N#get(java.lang.String)
     */
    public String get(String key)
    {
        return getText(key);
    }

    /**
     * Overwrite or implement method getLocale()
     * @see com.cyclopsgroup.waterview.I18N#getLocale()
     */
    public Locale getLocale()
    {
        return locale;
    }

    /**
     * Overwrite or implement method getText()
     * @see com.cyclopsgroup.waterview.I18N#getText(java.lang.String)
     */
    public String getText(String key)
    {
        return getText(key, StringUtils.EMPTY);
    }

    /**
     * Overwrite or implement method getText()
     * @see com.cyclopsgroup.waterview.I18N#getText(java.lang.String, java.lang.String)
     */
    public String getText(String key, String defaultValue)
    {
        String ret = null;
        try
        {
            ret = doGetText(key);
            if (StringUtils.isEmpty(ret))
            {
                ret = defaultValue;
            }
        }
        catch (Exception e)
        {
            ret = defaultValue;
        }
        return ret;
    }

}
