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

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;

public class Form
{
    public static final Form[] EMPTY_ARRAY = new Form[0];

    private FormDefinition definition;

    private Map fields = ListOrderedMap.decorate(new HashMap());

    private String id;

    public Form(FormDefinition definition, String id)
    {
        this.id = id;
        this.definition = definition;
    }

    /**
     * @return Returns the definition.
     */
    public FormDefinition getDefinition()
    {
        return definition;
    }

    /**
     * @param fieldName
     * @return
     */
    public Field getField(String fieldName)
    {
        return (Field) fields.get(fieldName);
    }

    /**
     * @return Returns the id.
     */
    public String getId()
    {
        return id;
    }

    public boolean isDirty()
    {
        String[] fieldNames = getDefinition().getFieldNames();
        for (int i = 0; i < fieldNames.length; i++)
        {
            String fieldName = fieldNames[i];
            Field field = getField(fieldName);
            if (field != null && field.isDirty())
            {
                return true;
            }
        }
        return false;
    }
}
