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
package com.cyclopsgroup.gearset.jelly.common;

import java.util.Iterator;
import java.util.LinkedList;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.MissingAttributeException;
import org.apache.commons.jelly.TagSupport;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.gearset.jelly.ConditionAcceptable;
import com.cyclopsgroup.gearset.jelly.SyntaxUtils;
import com.cyclopsgroup.gearset.runtime.Condition;
import com.cyclopsgroup.gearset.runtime.Context;

/**
 * And relation between multiple conditions
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class AndTag extends TagSupport implements ConditionAcceptable, Condition
{

    private LinkedList conditions = new LinkedList();

    /**
     * Override method acceptCondition in super class of AndTag
     * 
     * @see com.cyclopsgroup.gearset.jelly.ConditionAcceptable#acceptCondition(com.cyclopsgroup.gearset.runtime.Condition)
     */
    public void acceptCondition(Condition condition)
    {
        conditions.add(condition);
    }

    /**
     * Override method check in super class of AndTag
     * 
     * @see com.cyclopsgroup.gearset.runtime.Condition#check(com.cyclopsgroup.gearset.runtime.Context)
     */
    public boolean check(Context ctx) throws Exception
    {
        boolean ret = true;
        for (Iterator i = conditions.iterator(); i.hasNext();)
        {
            Condition condition = (Condition) i.next();
            if (!condition.check(ctx))
            {
                ret = false;
                break;
            }
        }
        return ret;
    }

    /**
     * Override method doTag in super class of AndTag
     * 
     * @see org.apache.commons.jelly.Tag#doTag(org.apache.commons.jelly.XMLOutput)
     */
    public void doTag(XMLOutput output) throws MissingAttributeException,
            JellyTagException
    {
        invokeBody(output);
        SyntaxUtils.checkParent(this, ConditionAcceptable.class);
        ((ConditionAcceptable) getParent()).acceptCondition(this);
    }
}