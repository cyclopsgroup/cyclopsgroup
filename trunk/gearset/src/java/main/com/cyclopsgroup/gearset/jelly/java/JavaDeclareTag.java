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
package com.cyclopsgroup.gearset.jelly.java;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.MissingAttributeException;
import org.apache.commons.jelly.TagSupport;
import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.gearset.jelly.SyntaxUtils;

/**
 * declare tag
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class JavaDeclareTag extends TagSupport
{
    private String name;

    /**
     * Override method doTag in super class of JavaDeclareTag
     * 
     * @see org.apache.commons.jelly.Tag#doTag(org.apache.commons.jelly.XMLOutput)
     */
    public void doTag(XMLOutput output) throws MissingAttributeException,
            JellyTagException
    {
        SyntaxUtils.checkAttribute("name", getName());
        String typeName = getBodyText();
        if (StringUtils.isEmpty(typeName))
        {
            throw new JellyTagException("Body of declare tag must not be empty");
        }
        Class type = Object.class;
        try
        {
            type = Class.forName(typeName);
        }
        catch (Exception e)
        {
            throw new JellyTagException(
                    "Body of declare tag must be a valid java class name");
        }
        SyntaxUtils.checkParent(this, JavaCompiledCodeTag.class);
        ((JavaCompiledCodeTag) getParent()).declareVariable(getName(), type);
    }

    /**
     * Getter method for property name
     * 
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Setter method for property name
     * 
     * @param variableName The name to set.
     */
    public void setName(String variableName)
    {
        name = variableName;
    }

}