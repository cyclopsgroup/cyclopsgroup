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
package com.cyclopsgroup.levistone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cyclopsgroup.levistone.query.Alias;
import com.cyclopsgroup.levistone.query.Condition;
import com.cyclopsgroup.levistone.query.Field;
import com.cyclopsgroup.levistone.query.OrderBy;

/**
 * Query model object
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class Query
{
    /** Field name for all fields */
    public static final String ALL_FIELDS = "*";

    /**
     * @uml.property name="aliases" 
     */
    private HashMap aliases = new HashMap();

    /**
     * 
     * @uml.property name="condition" 
     */
    private Condition condition;

    /**
     * 
     * @uml.property name="orderBys" 
     */
    private List orderBys = new ArrayList();

    private HashMap selectedFields = new HashMap();

    private Class type;

    /**
     * Type of returned object
     *
     * @param type Class object
     */
    public Query(Class type)
    {
        this.type = type;
    }

    /**
     * Add new alias declaration
     *
     * @param name Name of alias
     * @param type Type of entity
     */
    public void addAlias(String name, Class type)
    {
        aliases.put(name, new Alias(name, type));
    }

    /**
     * Add ascending order by field
     *
     * @param fieldName Field name
     */
    public void addAscendingOrderBy(String fieldName)
    {
        orderBys.add(new OrderBy(fieldName, false));
    }

    /**
     * Add ascending order by field
     *
     * @param fieldName Field name
     * @param alias Alias name
     */
    public void addAscendingOrderBy(String fieldName, String alias)
    {
        orderBys.add(new OrderBy(fieldName, alias, false));
    }

    /**
     * Add descending order by field
     *
     * @param fieldName Field name
     */
    public void addDescendingOrderBy(String fieldName)
    {
        orderBys.add(new OrderBy(fieldName, true));
    }

    /**
     * Add descending order by field
     *
     * @param fieldName Field name
     * @param alias Alias name
     */
    public void addDescendingOrderBy(String fieldName, String alias)
    {
        orderBys.add(new OrderBy(fieldName, alias, true));
    }

    /**
     * Add selected field
     *
     * @param fieldName Field name
     */
    public void addSelectedField(String fieldName)
    {
        selectedFields.put(fieldName, new Field(fieldName));
    }

    /**
     * Add selected field
     *
     * @param fieldName Field name
     * @param alias Alias
     */
    public void addSelectedField(String fieldName, String alias)
    {
        String key = fieldName + "@" + alias;
        selectedFields.put(key, new Field(fieldName, alias));
    }

    /**
     * Get all aliases
     *
     * @return Alias model array
     */
    public Alias[] getAliases()
    {
        return (Alias[]) aliases.values().toArray(Alias.EMPTY_ARRAY);
    }

    /**
     * Getter method for condition
     * 
     * @return Returns the condition.
     * 
     * @uml.property name="condition"
     */
    public Condition getCondition()
    {
        return condition;
    }

    /**
     * Get all order by fields
     * 
     * @return Array of order by
     * 
     * @uml.property name="orderBys"
     */
    public OrderBy[] getOrderBys()
    {
        return (OrderBy[]) orderBys.toArray(OrderBy.EMPTY_ARRAY);
    }

    /**
     * Get all selected field
     *
     * @return Selected field models
     */
    public Field[] getSelectedFields()
    {
        return (Field[]) selectedFields.values().toArray(Field.EMPTY_ARRAY);
    }

    /**
     * Get type of returned object
     *
     * @return Class of returned object
     */
    public Class getType()
    {
        return type;
    }

    /**
     * Setter method for condition
     * 
     * @param condition The condition to set.
     * 
     * @uml.property name="condition"
     */
    public void setCondition(Condition condition)
    {
        this.condition = condition;
    }
}