/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.engine;

import com.cyclopsgroup.petri.definition.NetContext;

/**
 * Job interface in state machine
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public interface Job
{
    /**
     * Run the job
     *
     * @param stateMachine State machine object
     * @param jobQueue Queue this job belongs to
     * @param context Modifiable runtime context object
     * @param caseWrapper Corresponding case
     * @throws Exception throw it out
     */
    void run(StateMachine stateMachine, JobQueue jobQueue, NetContext context,
            StateMachineCase caseWrapper) throws Exception;
}