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

import com.cyclopsgroup.petri.definition.Context;
import com.cyclopsgroup.petri.definition.Task;
import com.evavi.common.syntax.Executable;
import com.evavi.common.syntax.SyntaxUtils;
import com.evavi.common.syntax.core.ExecutableReceivable;

/**
 * task tag
 * 
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo </a>
 */
public class TaskTag extends TagSupport implements ExecutableReceivable, Task
{
    private Executable executable;

    /**
     * Override method doTag() in super class
     * 
     * @see org.apache.commons.jelly.Tag#doTag(org.apache.commons.jelly.XMLOutput)
     */
    public void doTag(XMLOutput output) throws MissingAttributeException,
            JellyTagException
    {
        SyntaxUtils.checkParent(this, TransitionTag.class);
        invokeBody(output);
        if (executable == null)
        {
            throw new JellyTagException("task tag can not be empty");
        }
        SyntaxUtils.checkParent(this, TransitionTag.class);
        ((TransitionTag) getParent()).getTransition().setTask(this);
    }

    /**
     * Override method execute in super class of TaskTag
     * 
     * @see com.cyclopsgroup.petri.definition.Task#execute(com.cyclopsgroup.petri.definition.Context)
     */
    public void execute(Context ctx) throws Exception
    {
        executable.execute(new CommonContextAdapter(ctx));
    }

    /**
     * Override method receiveExecutable() in super class
     * 
     * @see com.evavi.common.syntax.core.ExecutableReceivable#receiveExecutable(com.evavi.common.syntax.Executable)
     */
    public void receiveExecutable(Executable e)
    {
        executable = e;
    }
}