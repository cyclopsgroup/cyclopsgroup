/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.engine;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyclopsgroup.petri.definition.InputArc;
import com.cyclopsgroup.petri.definition.NetContext;
import com.cyclopsgroup.petri.definition.Place;
import com.cyclopsgroup.petri.definition.ScheduledTrigger;
import com.cyclopsgroup.petri.definition.TimerTrigger;
import com.cyclopsgroup.petri.definition.Transition;

/**
 * Job to clean schedules on states
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public class ClearScheduleJob implements Job
{
    private static Log logger = LogFactory.getLog(ClearScheduleJob.class);

    private Place state;

    /**
     * Constructor of class ClearScheduleJob
     *
     * @param state State
     */
    public ClearScheduleJob(Place state)
    {
        this.state = state;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.petri.engine.Job#run(com.cyclopsgroup.petri.engine.StateMachine, com.cyclopsgroup.petri.engine.JobQueue, com.cyclopsgroup.petri.definition.NetContext, com.cyclopsgroup.petri.engine.StateMachineCase)
     */
    public void run(StateMachine stateMachine, JobQueue jobQueue,
            NetContext context, StateMachineCase caseWrapper) throws Exception
    {
        InputArc[] arcs = state.getOutboundArcs();
        for (int j = 0; j < arcs.length; j++)
        {
            Transition transition = arcs[j].getTo();
            if ((transition.getTrigger() instanceof TimerTrigger)
                    || (transition.getTrigger() instanceof ScheduledTrigger))
            {
                String jobName = caseWrapper.getId() + "@" + transition.getId();
                try
                {
                    stateMachine.getScheduler().unscheduleJob(jobName,
                            StateMachine.JOB_GROUP_NAME);
                    stateMachine.getPersistenceManager().unschedule(transition,
                            caseWrapper);
                }
                catch (Exception e)
                {
                    logger.error("Can't unschedule workflow", e);
                }
            }
        }
    }
}