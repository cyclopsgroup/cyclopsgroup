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

import java.util.HashSet;
import java.util.TreeMap;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.MissingAttributeException;
import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.gearset.runtime.Context;

/**
 * TODO Add javadoc for class
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class JavaCompiledCodeTag extends AbstractJavaCodeTag
{
    private HashSet imports = new HashSet();

    private String name;

    private String sourceCode;

    private TreeMap variables = new TreeMap();

    /**
     * Method addImport() in class JavaCompiledCodeTag
     * 
     * @param importExpression
     */
    public void addImport(String importExpression)
    {
        imports.add(importExpression);
    }

    /**
     * Method declareVariable() in class JavaCompiledCodeTag
     * 
     * @param variableName
     * @param variableType
     */
    public void declareVariable(String variableName, Class variableType)
    {
        variables.put(variableName, variableType);
    }

    /**
     * Override method doTag in super class of JavaCompiledCodeTag
     * 
     * @see org.apache.commons.jelly.Tag#doTag(org.apache.commons.jelly.XMLOutput)
     */
    public void doTag(XMLOutput output) throws MissingAttributeException,
            JellyTagException
    {
        if (StringUtils.isEmpty(getName()))
        {
            setName("DynaClass_" + hashCode());
        }
        invokeBody(output);
        setResult();
    }

    /**
     * Override method execute in super class of JavaCompiledCodeTag
     * 
     * @see com.cyclopsgroup.gearset.runtime.Action#execute(com.cyclopsgroup.gearset.runtime.Context)
     */
    public Object execute(Context ctx) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
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
     * @param className The name to set.
     */
    public void setName(String className)
    {
        name = className;
    }

    /**
     * Method setSourceCode() in class JavaCompiledCodeTag
     * 
     * @param code
     */
    public void setSourceCode(String code)
    {
        sourceCode = code;
    }
}