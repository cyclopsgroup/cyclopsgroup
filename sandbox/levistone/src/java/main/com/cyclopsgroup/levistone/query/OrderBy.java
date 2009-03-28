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
 * Order by model
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class OrderBy
{
    /** Empty order by  array */
    public static final OrderBy[] EMPTY_ARRAY = new OrderBy[0];

    /**
     * 
     * @uml.property name="descending" 
     */
    private boolean descending = false;

    /**
     * 
     * @uml.property name="field" 
     */
    private Field field;


    /**
     * Constructor for class OrderBy
     *
     * @param field
     * @param descending If order by descending
     */
    public OrderBy(Field field, boolean descending)
    {
        this.field = field;
        this.descending = descending;
    }

    /**
     * Constructor for class OrderBy
     *
     * @param fieldName Name of the order by field
     * @param descending If order by descending
     */
    public OrderBy(String fieldName, boolean descending)
    {
        this(new Field(fieldName), descending);
    }

    /**
     * Constructor for class OrderBy
     *
     * @param fieldName
     * @param alias
     * @param descending If order by descending
     */
    public OrderBy(String fieldName, String alias, boolean descending)
    {
        this(new Field(fieldName, alias), descending);
    }

    /**
     * Getter method for field
     * 
     * @return Returns the field.
     * 
     * @uml.property name="field"
     */
    public Field getField() {
        return field;
    }

    /**
     * Getter method for descending
     * 
     * @return Returns the descending.
     * 
     * @uml.property name="descending"
     */
    public boolean isDescending()
    {
        return descending;
    }
}