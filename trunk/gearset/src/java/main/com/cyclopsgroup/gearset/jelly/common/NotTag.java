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

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.MissingAttributeException;
import org.apache.commons.jelly.TagSupport;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.gearset.jelly.ConditionSensible;
import com.cyclopsgroup.gearset.jelly.SyntaxUtils;
import com.cyclopsgroup.gearset.runtime.Condition;
import com.cyclopsgroup.gearset.runtime.Context;

/**
 * TODO Add javadoc for class
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class NotTag extends TagSupport implements ConditionSensible, Condition
{
    private Condition condition;

    /**
     * Override method acceptCondition in super class of NotTag
     * 
     * @see com.cyclopsgroup.gearset.jelly.ConditionSensible#acceptCondition(com.cyclopsgroup.gearset.runtime.Condition)
     */
    public void acceptCondition(Condition c)
    {
        condition = c;
    }

    /**
     * Override method check in super class of NotTag
     * 
     * @see com.cyclopsgroup.gearset.runtime.Condition#check(com.cyclopsgroup.gearset.runtime.Context)
     */
    public boolean check(Context ctx) throws Exception
    {
        return !condition.check(ctx);
    }

    /**
     * Override method doTag in super class of NotTag
     * 
     * @see org.apache.commons.jelly.Tag#doTag(org.apache.commons.jelly.XMLOutput)
     */
    public void doTag(XMLOutput output) throws MissingAttributeException,
            JellyTagException
    {
        invokeBody(output);
        SyntaxUtils.checkParent(this, ConditionSensible.class);
        ((ConditionSensible) getParent()).acceptCondition(this);
    }
}