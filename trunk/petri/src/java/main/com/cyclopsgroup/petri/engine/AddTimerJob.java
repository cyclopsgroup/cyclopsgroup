/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.engine;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;

import com.cyclopsgroup.petri.definition.NetContext;
import com.cyclopsgroup.petri.definition.Transition;

/**
 * Job to add timer trigger waiter
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public class AddTimerJob implements Job
{

    private Date occurTime;

    private Transition transition;

    /**
     * Constructor of class AddTimerJob
     *
     * @param timerTriggeredTransition
     * @param occur
     */
    public AddTimerJob(Transition timerTriggeredTransition, long occur)
    {
        transition = timerTriggeredTransition;
        occurTime = new Date(occur);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.petri.engine.Job#run(com.cyclopsgroup.petri.engine.StateMachine, com.cyclopsgroup.petri.engine.JobQueue, com.cyclopsgroup.petri.definition.NetContext, com.cyclopsgroup.petri.engine.StateMachineCase)
     */
    public void run(StateMachine stateMachine, JobQueue jobQueue,
            NetContext context, StateMachineCase caseWrapper) throws Exception
    {
        if (System.currentTimeMillis() > occurTime.getTime())
        {
            stateMachine.getPersistenceManager().unschedule(transition,
                    caseWrapper);
            return;
        }
        String jobName = caseWrapper.getId() + "@" + transition.getId();
        SimpleTrigger st = new SimpleTrigger(jobName,
                StateMachine.JOB_GROUP_NAME, occurTime);
        JobDetail jobDetail = new JobDetail(jobName,
                StateMachine.JOB_GROUP_NAME, QuartzTriggerTransitionJob.class);
        jobDetail.getJobDataMap().put("stateMachine", stateMachine);
        jobDetail.getJobDataMap().put("transitionId", transition.getId());
        jobDetail.getJobDataMap().put("caseId", caseWrapper.getId());
        stateMachine.getScheduler().scheduleJob(jobDetail, st);
        stateMachine.getPersistenceManager().schedule(transition, caseWrapper,
                occurTime);
    }
}