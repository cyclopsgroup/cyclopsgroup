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
package com.cyclopsgroup.waterview;

import org.apache.commons.lang.StringUtils;

/**
 * Value parser object
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public abstract class ValueParser
{
    /**
     * Internally get string value
     * 
     * @param name Name of the variable
     * @return String value of the variable
     * @throws Exception Throw it out
     */
    protected abstract String doGetValue(String name) throws Exception;

    /**
     * Internally get string array value
     * 
     * @param name Name of the variable
     * @return String array value
     * @throws Exception Throw it out
     */
    protected String[] doGetValues(String name) throws Exception
    {
        return new String[]
        { doGetValue(name) };
    }

    /**
     * Get string value of an attribute
     * 
     * @param name Name of the variable
     * @return String value
     */
    public String getString(String name)
    {
        return getString(name, StringUtils.EMPTY);
    }

    /**
     * Get string value of attribute
     * 
     * @param name Name of the attribute
     * @param defaultValue Defaut string value
     * @return String value
     */
    public String getString(String name, String defaultValue)
    {
        try
        {
            String ret = doGetValue(name);
            if (ret == null)
            {
                return defaultValue;
            }
            return ret;
        }
        catch (Exception e)
        {
            return defaultValue;
        }
    }
}