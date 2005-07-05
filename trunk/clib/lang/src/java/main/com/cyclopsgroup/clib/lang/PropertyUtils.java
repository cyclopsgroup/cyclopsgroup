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
package com.cyclopsgroup.clib.lang;

public final class PropertyUtils
{
    /**
     * @param value String value expression of this property
     * @param type Property type
     * @return Property object
     */
    public static Property createProperty(String value, PropertyType type)
    {
        return new Property(value, type);
    }

    public static Object convert(String value, PropertyType type)
    {
        Object ret = null;
        if (value == null)
        {
            return ret;
        }
        try
        {
            if (type == PropertyType.INT)
            {
                ret = new Integer(Integer.parseInt(value));
            }
        }
        catch (Exception e)
        {
            //do nothing
        }
        finally
        {
            return null;
        }
    }

    public static Object convert(Property prop)
    {
        return convert(prop.getValue(), prop.getType());
    }
}
