/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.syntax.core;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.MissingAttributeException;
import org.apache.commons.jelly.TagSupport;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.gearset.beans.Condition;
import com.cyclopsgroup.gearset.beans.Context;
import com.cyclopsgroup.gearset.xml.ConditionSensible;
import com.cyclopsgroup.gearset.xml.SyntaxUtils;
import com.cyclopsgroup.petri.definition.Guard;

/**
 * Guard tag
 * 
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo </a>
 */
public class GuardTag extends TagSupport implements Guard, ConditionSensible
{
    private Condition condition;

    /**
     * Override method doTag() in super class
     * 
     * @see org.apache.commons.jelly.Tag#doTag(org.apache.commons.jelly.XMLOutput)
     */
    public void doTag(XMLOutput output) throws MissingAttributeException,
            JellyTagException
    {
        invokeBody(output);
        if (condition == null)
        {
            throw new JellyTagException("guard tag must not be empty");
        }
        SyntaxUtils.checkParent(this, TransitionTag.class);
        ((TransitionTag) getParent()).getTransition().setGuard(this);
    }

    /**
     * Override method evaluate in super class of GuardTag
     * 
     * @see com.cyclopsgroup.petri.definition.Guard#evaluate(com.cyclopsgroup.gearset.beans.Context)
     */
    public boolean evaluate(Context ctx) throws Exception
    {
        return condition.check(ctx);
    }

    /**
     * Override method acceptCondition in super class of GuardTag
     * 
     * @see com.cyclopsgroup.gearset.xml.ConditionSensible#acceptCondition(com.cyclopsgroup.gearset.beans.Condition)
     */
    public void acceptCondition(Condition c)
    {
        condition = c;
    }
}