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
import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.lang.StringUtils;

import bsh.EvalError;
import bsh.Interpreter;

import com.cyclopsgroup.gearset.runtime.Context;

/**
 * BSH script tag
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class JavaBSHTag extends AbstractJavaCodeTag
{

    private String code;

    /**
     * Override method doTag in super class of JavaBSHTag
     * 
     * @see org.apache.commons.jelly.Tag#doTag(org.apache.commons.jelly.XMLOutput)
     */
    public void doTag(XMLOutput output) throws MissingAttributeException,
            JellyTagException
    {
        code = getBodyText(false);
        if (StringUtils.isEmpty(code))
        {
            throw new JellyTagException("Don't create empty bsh tag");
        }
        setResult();
    }

    /**
     * Override method execute in super class of JavaBSHTag
     * 
     * @see com.cyclopsgroup.gearset.runtime.Action#execute(com.cyclopsgroup.gearset.runtime.Context)
     */
    public Object execute(Context ctx) throws Exception
    {
        Interpreter interpreter = new Interpreter();
        String[] names = ctx.getNames();
        for (int i = 0; i < names.length; i++)
        {
            String name = names[i];
            Object value = ctx.get(name);
            try
            {
                interpreter.set(name, value);
            }
            catch (Exception ignored)
            {
                //ignore exception;
            }
        }
        try
        {
            return interpreter.eval(code);
        }
        catch (EvalError e)
        {
            throw new RuntimeException(e.getErrorSourceFile() + " @ line "
                    + e.getErrorLineNumber(), e.getCause());
        }
    }
}