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
 * Selected field query model
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public final class Field
{
    /** Empty selected field array */
    public static final Field[] EMPTY_ARRAY = new Field[0];

    private String alias;

    private String fieldName;

    /**
     * Constructor for class SelectedField
     *
     * @param fieldName
     */
    public Field(String fieldName)
    {
        super();
        this.fieldName = fieldName;
    }

    /**
     * Constructor for class SelectedField
     *
     * @param alias
     * @param fieldName
     */
    public Field(String fieldName, String alias)
    {
        this.alias = alias;
        this.fieldName = fieldName;
    }

    /**
     * Alias of table this field belongs to
     *
     * @return Alias name
     */
    public String getAlias()
    {
        return alias;
    }

    /**
     * Field name
     *
     * @return Field name
     */
    public String getFieldName()
    {
        return fieldName;
    }
}