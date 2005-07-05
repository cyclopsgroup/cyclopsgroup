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

public class FormValidationResult
{
    private FormDefinition definition;

    private Map fields = new HashMap();

    public FormValidationResult(FormDefinition definition)
    {
        this.definition = definition;
    }

    public void addField(FieldValidationResult field)
    {
        fields.put(field.getDefinition().getName(), field);
    }

    /**
     * @return Returns the definition.
     */
    public FormDefinition getDefinition()
    {
        return definition;
    }

    public FieldValidationResult getField(String fieldName)
    {
        return (FieldValidationResult) fields.get(fieldName);
    }

    /**
     * Check if any field is failed
     * 
     * @return True only if all fields are ok
     */
    public boolean isFailed()
    {
        String[] fieldNames = getDefinition().getFieldNames();
        for (int i = 0; i < fieldNames.length; i++)
        {
            String fieldName = fieldNames[i];
            FieldValidationResult field = getField(fieldName);
            if (field != null || field.isFailed())
            {
                return true;
            }
        }
        return false;
    }
}