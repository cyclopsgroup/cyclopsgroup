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

public class Field
{
    private FieldDefinition definition;

    private boolean dirty;

    private FieldValidationResult validationResult;

    private String value;

    public Field(FieldDefinition definition)
    {
        this.definition = definition;
    }

    /**
     * @return Returns the definition.
     */
    public FieldDefinition getDefinition()
    {
        return definition;
    }

    /**
     * @return Returns the validationResult.
     */
    public FieldValidationResult getValidationResult()
    {
        return validationResult;
    }

    /**
     * @return Returns the value.
     */
    public String getValue()
    {
        return value;
    }

    /**
     * @return Returns the dirty.
     */
    public boolean isDirty()
    {
        return dirty;
    }

    /**
     * @param dirty The dirty to set.
     */
    public void setDirty(boolean dirty)
    {
        this.dirty = dirty;
    }

    /**
     * @param validationResult The validationResult to set.
     */
    public void setValidationResult(FieldValidationResult validationResult)
    {
        this.validationResult = validationResult;
    }

    /**
     * @param value The value to set.
     */
    public void setValue(String value)
    {
        this.value = value;
    }
}
