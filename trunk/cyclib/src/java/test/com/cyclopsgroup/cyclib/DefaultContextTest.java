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
package com.cyclopsgroup.cyclib;

import junit.framework.TestCase;

import org.apache.commons.jexl.Expression;
import org.apache.commons.jexl.ExpressionFactory;
import org.apache.commons.jexl.JexlContext;
import org.apache.commons.jexl.context.HashMapContext;

/**
 * Test case for default context
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class DefaultContextTest extends TestCase
{
    /**
     * Testing method for getNames()
     * @throws Exception Throw it to runner
     */
    public void testGetNames() throws Exception
    {
        Expression expr = ExpressionFactory
                .createExpression("a.toString() + b.toString()");
        JexlContext ctx = new HashMapContext();
        ctx.getVars().put("a", new Float(0.0f));
        ctx.getVars().put("b", "abccc");
        System.out.println(expr.evaluate(ctx));
    }
}