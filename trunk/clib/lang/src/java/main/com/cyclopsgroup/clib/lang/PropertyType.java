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

import java.util.Hashtable;

public final class PropertyType
{
    public static PropertyType BIGDECIMAL = new PropertyType("bigdecimal");

    public static PropertyType BOOLEAN = new PropertyType("boolean");

    public static PropertyType DATE = new PropertyType("date");

    public static PropertyType DATETIME = new PropertyType("datetime");

    public static PropertyType DOUBLE = new PropertyType("double");

    public static PropertyType[] EMPTY_ARRAY = new PropertyType[0];

    public static PropertyType FLOAT = new PropertyType("float");

    private static Hashtable instances = new Hashtable();

    public static PropertyType INT = new PropertyType("int");

    public static PropertyType LONG = new PropertyType("long");

    public static PropertyType SHORT = new PropertyType("short");

    public static PropertyType STRING = new PropertyType("string");

    public static PropertyType TIME = new PropertyType("time");

    /**
     * Get type object
     * 
     * @param name Name of the type
     * @return Type instance
     */
    public static PropertyType valueOf(String name)
    {
        return (PropertyType) instances.get(name);
    }

    private String name;

    private PropertyType(String name)
    {
        this.name = name;
        instances.put(name, this);
    }

    public PropertyType[] getInstances()
    {
        return (PropertyType[]) instances.values().toArray(
                PropertyType.EMPTY_ARRAY);
    }

    /**
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }
}
