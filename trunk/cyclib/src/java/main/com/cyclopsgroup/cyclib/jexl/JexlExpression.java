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
package com.cyclopsgroup.cyclib.jexl;

import java.util.Iterator;

import org.apache.commons.jexl.ExpressionFactory;
import org.apache.commons.jexl.JexlContext;
import org.apache.commons.jexl.context.HashMapContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyclopsgroup.cyclib.Context;
import com.cyclopsgroup.cyclib.Expression;

/**
 * Jexl implemented expression
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class JexlExpression implements Expression
{
    private static Log logger = LogFactory.getLog(JexlExpression.class);

    private org.apache.commons.jexl.Expression expression;

    /**
     * Constructor for class JexlExpression
     *
     * @param stringExpression String jexl expression
     * @throws Exception Throw it out
     */
    public JexlExpression(String stringExpression) throws Exception
    {
        expression = ExpressionFactory.createExpression(stringExpression);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.cyclib.Expression#evaluate(com.cyclopsgroup.cyclib.Context)
     */
    public Object evaluate(Context context) throws Exception
    {
        JexlContext ctx = new HashMapContext();
        if (context != null)
        {
            for (Iterator i = context.keys(); i.hasNext();)
            {
                String name = (String) i.next();
                Object object = context.get(name);
                try
                {
                    ctx.getVars().put(name, object);
                }
                catch (Exception e)
                {
                    //Ignore exception here
                }
            }
        }
        return expression.evaluate(ctx);
    }
}