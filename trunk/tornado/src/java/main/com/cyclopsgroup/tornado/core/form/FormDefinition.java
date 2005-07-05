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
package com.cyclopsgroup.tornado.core.form;

import java.util.Hashtable;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.lang.ArrayUtils;

public class FormDefinition
{
    public static final FormDefinition[] EMPTY_ARRAY = new FormDefinition[0];

    public static final String NAME = FormDefinition.class.getName();

    private Map fields = ListOrderedMap.decorate(new Hashtable());

    private String name;

    public FormDefinition(String name)
    {
        this.name = name;
    }

    public void addField(FieldDefinition field)
    {
        fields.put(field.getName(), field);
    }

    public FieldDefinition getField(String fieldName)
    {
        return (FieldDefinition) fields.get(fieldName);
    }

    /**
     * @return
     */
    public String[] getFieldNames()
    {
        return (String[]) fields.keySet()
                .toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }

    public FieldDefinition[] getFields()
    {
        return (FieldDefinition[]) fields.values().toArray(
                FieldDefinition.EMPTY_ARRAY);
    }

    /**
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }
}
