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

import org.apache.commons.jelly.TagSupport;

import com.cyclopsgroup.gearset.jelly.ActionAcceptable;
import com.cyclopsgroup.gearset.jelly.ConditionAcceptable;
import com.cyclopsgroup.gearset.jelly.ExpressionAcceptable;
import com.cyclopsgroup.gearset.runtime.Action;
import com.cyclopsgroup.gearset.runtime.Condition;
import com.cyclopsgroup.gearset.runtime.Context;
import com.cyclopsgroup.gearset.runtime.Expression;

/**
 * BSH script tag
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public abstract class AbstractJavaCodeTag extends TagSupport implements
        Action, Expression, Condition
{
    /**
     * Override method check in super class of JavaBSHTag
     * 
     * @see com.cyclopsgroup.gearset.runtime.Condition#check(com.cyclopsgroup.gearset.runtime.Context)
     */
    public boolean check(Context ctx) throws Exception
    {
        Object retValue = execute(ctx);
        if (retValue == null || !(retValue instanceof Boolean))
        {
            throw new RuntimeException(
                    "Return value of bsh must be a valid Boolean object");
        }
        return ((Boolean) retValue).booleanValue();
    }

    /**
     * Override method evaluate in super class of JavaBSHTag
     * 
     * @see com.cyclopsgroup.gearset.runtime.Expression#evaluate(com.cyclopsgroup.gearset.runtime.Context)
     */
    public Object evaluate(Context ctx) throws Exception
    {
        return execute(ctx);
    }

    /**
     * Set result
     */
    protected void setResult()
    {
        if (getParent() instanceof ActionAcceptable)
        {
            ((ActionAcceptable) getParent()).acceptAction(this);
        }
        if (getParent() instanceof ExpressionAcceptable)
        {
            ((ExpressionAcceptable) getParent()).acceptExpression(this);
        }
        if (getParent() instanceof ConditionAcceptable)
        {
            ((ConditionAcceptable) getParent()).acceptCondition(this);
        }
    }
}