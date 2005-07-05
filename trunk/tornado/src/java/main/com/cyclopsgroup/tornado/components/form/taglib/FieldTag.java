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
package com.cyclopsgroup.tornado.components.form.taglib;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.MissingAttributeException;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.clib.lang.PropertyType;
import com.cyclopsgroup.clib.lang.xml.ClibTagSupport;
import com.cyclopsgroup.tornado.core.form.FieldDefinition;
import com.cyclopsgroup.tornado.core.form.FormDefinition;

public class FieldTag extends ClibTagSupport
{
    private String name;

    private boolean required;

    private String type = "string";

    public void doTag(XMLOutput output) throws MissingAttributeException,
            JellyTagException
    {
        FormDefinition fd = (FormDefinition) getContext().getVariable(
                FormDefinition.NAME);
        if (fd == null)
        {
            throw new JellyTagException("Field tag has to be inside a form tag");
        }
        requireAttribute("name");
        PropertyType propType = PropertyType.valueOf(getType());
        FieldDefinition field = new FieldDefinition(getName(), propType);
        field.setRequired(isRequired());
        fd.addField(field);
    }

    /**
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return Returns the type.
     */
    public String getType()
    {
        return type;
    }

    /**
     * @return Returns the required.
     */
    public boolean isRequired()
    {
        return required;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @param required The required to set.
     */
    public void setRequired(boolean required)
    {
        this.required = required;
    }

    /**
     * @param type The type to set.
     */
    public void setType(String type)
    {
        this.type = type;
    }
}
