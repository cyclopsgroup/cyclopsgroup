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
 * Comparison condition model
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public abstract class Comparison extends Condition
{

    /**
     * @uml.property name="comparator" 
     */
    private Comparator comparator;

    /**
     * @uml.property name="field" 
     */
    private Field field;

    /**
     * 
     * @uml.property name="value" 
     */
    private Object value;

    /**
     * Constructor for class ComparisonCondition
     *
     * @param field
     * @param value
     * @param comparator
     */
    public Comparison(Field field, Object value, Comparator comparator)
    {
        this.comparator = comparator;
        this.field = field;
        this.value = value;
    }

    /**
     * Constructor for class ComparisonCondition
     *
     * @param fieldName
     * @param value
     * @param comparator
     */
    public Comparison(String fieldName, Object value, Comparator comparator)
    {
        this(new Field(fieldName), value, comparator);
    }

    /**
     * Constructor for class ComparisonCondition
     *
     * @param fieldName
     * @param alias
     * @param value
     * @param comparator
     */
    public Comparison(String fieldName, String alias, Object value,
            Comparator comparator)
    {
        this(new Field(fieldName, alias), value, comparator);
    }

    /**
     * Getter method for comparator
     * 
     * @return Returns the comparator.
     * 
     * @uml.property name="comparator"
     */
    public Comparator getComparator()
    {
        return comparator;
    }

    /**
     * Getter method for field
     * 
     * @return Returns the field.
     * 
     * @uml.property name="field"
     */
    public Field getField()
    {
        return field;
    }

    /**
     * Getter method for value
     * 
     * @return Returns the value.
     * 
     * @uml.property name="value"
     */
    public Object getValue()
    {
        return value;
    }

}