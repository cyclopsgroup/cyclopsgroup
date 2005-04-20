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
package com.cyclopsgroup.jrepo.descriptor;

import org.apache.commons.lang.enum.Enum;

/**
 * Property type class
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public final class PropertyType extends Enum
{
    /**
     * Binary type
     */
    public static PropertyType BINARY = new PropertyType("binary");

    /**
     * Date type
     */
    public static PropertyType DATE = new PropertyType("date");

    /**
     * Date and time type
     */
    public static PropertyType DATE_TIME = new PropertyType("datetime");

    /**
     * Float type
     */
    public static PropertyType FLOAT = new PropertyType("float");

    /**
     * Integer type
     */
    public static PropertyType INT = new PropertyType("int");

    /**
     * Long integer type
     */
    public static PropertyType LONG = new PropertyType("long");

    /**
     * Normal string type
     */
    public static PropertyType STRING = new PropertyType("string");

    /**
     * Very long string type
     */
    public static PropertyType TEXT = new PropertyType("text");

    /**
     * Time with hour, minute and second
     */
    public static PropertyType TIME = new PropertyType("time");

    /**
     * Constructor for class PropertyType
     *
     * @param typeName
     */
    private PropertyType(String typeName)
    {
        super(typeName);
    }

    /**
     * Get property type associated with given type name
     *
     * @param typeName Type name
     * @return Property type object
     */
    public PropertyType valueOf(String typeName)
    {
        return (PropertyType) getEnum(PropertyType.class, typeName);
    }
}