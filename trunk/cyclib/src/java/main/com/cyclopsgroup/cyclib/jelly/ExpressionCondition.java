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

import com.cyclopsgroup.cyclib.Condition;
import com.cyclopsgroup.cyclib.Context;
import com.cyclopsgroup.cyclib.Expression;

/**
 * Condition which is actaully an expression
 * Adapter for condition with expression
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class ExpressionCondition implements Condition
{
    private Expression expression;

    /**
     * Constructor for class ExpressionCondition
     *
     * @param expression
     */
    public ExpressionCondition(Expression expression)
    {
        this.expression = expression;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.cyclib.Condition#check(com.cyclopsgroup.cyclib.Context)
     */
    public boolean check(Context context) throws Exception
    {
        Object ret = expression.evaluate(context);
        if (ret != null && (ret instanceof Boolean))
        {
            return ((Boolean) ret).booleanValue();
        }
        return false;
    }
}
