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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeMap;

import net.janino.ClassBodyEvaluator;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.MissingAttributeException;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.gearset.runtime.Context;

/**
 * TODO Add javadoc for class
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class JavaCompiledCodeTag extends AbstractJavaCodeTag
{
    private Method dynExecuteMethod;

    private HashSet imports = new HashSet();

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
    public void declareVariable(String variableName, String variableType)
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

        invokeBody(output);
        setResult();

        StringWriter stringWriter = new StringWriter();
        PrintWriter out = new PrintWriter(stringWriter);
        for (Iterator i = imports.iterator(); i.hasNext();)
        {
            String importExpression = (String) i.next();
            out.println(importExpression);
        }
        out
                .println("public static Object executeAction(com.cyclopsgroup.gearset.runtime.Context context");
        for (Iterator i = variables.keySet().iterator(); i.hasNext();)
        {
            String variableName = (String) i.next();
            String variableType = (String) variables.get(variableName);
            out.println(", " + variableType + " " + variableName);
        }
        out.println(") {");

        out.println(sourceCode);
        out.println("}");
        out.flush();
        String code = stringWriter.toString();
        System.out.println(code);
        try
        {
            Class dynActionClass = new ClassBodyEvaluator(code).evaluate();
            Method[] methods = dynActionClass.getMethods();
            for (int i = 0; i < methods.length; i++)
            {
                Method method = methods[i];
                Class[] paramTypes = method.getParameterTypes();
                if (method.getName().equals("executeAction")
                        && paramTypes.length == variables.size() + 1
                        && paramTypes[0] == Context.class)
                {
                    dynExecuteMethod = method;
                    break;
                }
            }
            if (dynExecuteMethod == null)
            {
                throw new RuntimeException(
                        "Failed to find entry method of dynamic class");
            }
        }
        catch (Exception e)
        {
            throw new JellyTagException(e);
        }
    }

    /**
     * Override method execute in super class of JavaCompiledCodeTag
     * 
     * @see com.cyclopsgroup.gearset.runtime.Action#execute(com.cyclopsgroup.gearset.runtime.Context)
     */
    public Object execute(Context ctx) throws Exception
    {
        Object[] params = new Object[variables.size() + 1];
        params[0] = ctx;
        int index = 0;
        for (Iterator i = variables.keySet().iterator(); i.hasNext();)
        {
            String variableName = (String) i.next();
            index++;
            params[index] = ctx.get(variableName);
        }
        return dynExecuteMethod.invoke(null, params);
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