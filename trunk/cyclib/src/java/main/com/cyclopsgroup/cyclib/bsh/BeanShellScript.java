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
package com.cyclopsgroup.cyclib.bsh;

import java.util.Iterator;

import bsh.Interpreter;

import com.cyclopsgroup.cyclib.Context;
import com.cyclopsgroup.cyclib.Script;

/**
 * BeanShell implemented script
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class BeanShellScript implements Script
{
    private String script;

    /**
     * Constructor for class BeanShellScript
     *
     * @param script String script
     */
    public BeanShellScript(String script)
    {
        this.script = script;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.cyclib.Script#run(com.cyclopsgroup.cyclib.Context)
     */
    public Object run(Context context) throws Exception
    {
        Interpreter interpreter = new Interpreter();
        if (context != null)
        {
            for (Iterator i = context.keys(); i.hasNext();)
            {
                String name = (String) i.next();
                try
                {
                    interpreter.set(name, context.get(name));
                }
                catch (Exception ignored)
                {
                    //ignore exception
                }
            }
        }
        return interpreter.eval(script);
    }

}