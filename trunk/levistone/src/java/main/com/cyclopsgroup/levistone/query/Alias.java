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
package com.cyclopsgroup.levistone.query;

/**
 * Alias model
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class Alias
{
    /** Empty alias model array */
    public static final Alias[] EMPTY_ARRAY = new Alias[0];

    /**
     * 
     * @uml.property name="name" 
     */
    private String name;

    /**
     * 
     * @uml.property name="type" 
     */
    private Class type;

    /**
     * Constructor for class Alias
     *
     * @param name
     * @param type
     */
    public Alias(String name, Class type)
    {
        this.name = name;
        this.type = type;
    }

    /**
     * Getter method for name
     * 
     * @return Returns the name.
     * 
     * @uml.property name="name"
     */
    public String getName()
    {
        return name;
    }

    /**
     * Getter method for type
     * 
     * @return Returns the type.
     * 
     * @uml.property name="type"
     */
    public Class getType()
    {
        return type;
    }

}