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

import com.cyclopsgroup.clib.lang.PropertyType;

public class FieldDefinition
{
    public static final FieldDefinition[] EMPTY_ARRAY = new FieldDefinition[0];

    public static final String NAME = FieldDefinition.class.getName();

    private String name;

    private boolean required;

    private PropertyType type;

    private FieldValidator validator;

    public FieldDefinition(String name)
    {
        this(name, PropertyType.STRING);
    }

    public FieldDefinition(String name, PropertyType type)
    {
        this.name = name;
        this.type = type;
    }

    /**
     * @return
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return Returns the type.
     */
    public PropertyType getType()
    {
        return type;
    }

    /**
     * @return Returns the validator.
     */
    public FieldValidator getValidator()
    {
        return validator;
    }

    /**
     * @return Returns the required.
     */
    public boolean isRequired()
    {
        return required;
    }

    /**
     * @param required The required to set.
     */
    public void setRequired(boolean required)
    {
        this.required = required;
    }

    /**
     * @param validator The validator to set.
     */
    public void setValidator(FieldValidator validator)
    {
        this.validator = validator;
    }
}
