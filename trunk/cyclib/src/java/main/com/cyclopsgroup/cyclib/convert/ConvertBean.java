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
package com.cyclopsgroup.cyclib.convert;

import java.util.Hashtable;

/**
 * Convert bean
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class ConvertBean
{
    private Hashtable converters = new Hashtable();

    /**
     * TODO Add javadoc for this method
     *
     * @param type
     */
    public void deregisterConverter(Class type)
    {
        converters.remove(type);
    }

    /**
     * TODO Add javadoc for this method
     *
     * @param object
     * @return String expression
     */
    public String object2String(Object object)
    {
        return "";
    }

    /**
     * TODO Add javadoc for this method
     *
     * @param converter
     */
    public void registerConverter(Converter converter)
    {
        converters.put(converter.getType(), converter);
    }

    /**
     * TODO Add javadoc for this method
     *
     * @param str
     * @param type
     * @return Object instance
     */
    public Object string2Object(String str, Class type)
    {
        return null;
    }
}