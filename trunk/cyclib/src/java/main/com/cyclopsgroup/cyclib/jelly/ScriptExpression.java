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
package com.cyclopsgroup.cyclib.jelly;

import com.cyclopsgroup.cyclib.Context;
import com.cyclopsgroup.cyclib.Expression;
import com.cyclopsgroup.cyclib.Script;

/**
 * Adapter for expression
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class ScriptExpression implements Expression
{
    private Script script;

    /**
     * Constructor for class ScriptExpression
     *
     * @param script
     */
    public ScriptExpression(Script script)
    {
        this.script = script;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.cyclib.Expression#evaluate(com.cyclopsgroup.cyclib.Context)
     */
    public Object evaluate(Context context) throws Exception
    {
        return script.run(context);
    }

}