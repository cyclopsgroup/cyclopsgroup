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

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.MissingAttributeException;
import org.apache.commons.jelly.TagSupport;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.cyclib.Condition;
import com.cyclopsgroup.cyclib.ConditionHandler;
import com.cyclopsgroup.cyclib.ExpressionHandler;
import com.cyclopsgroup.cyclib.jelly.ExpressionCondition;

/**
 * Jexl implemented java expression tag
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class JexlExpressionTag extends TagSupport
{
    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.commons.jelly.Tag#doTag(org.apache.commons.jelly.XMLOutput)
     */
    public void doTag(XMLOutput output) throws MissingAttributeException,
            JellyTagException
    {
        try
        {
            JexlExpression expr = new JexlExpression(getBodyText());

            if (parent instanceof ExpressionHandler)
            {
                ((ExpressionHandler) parent).handle(expr);
            }
            if (parent instanceof Condition)
            {
                ((ConditionHandler) parent)
                        .handle(new ExpressionCondition(expr));
            }
        }
        catch (Exception e)
        {
            throw new JellyTagException("Jexl expression error", e);
        }
    }
}
